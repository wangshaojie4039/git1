package cn.imexue.ec.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;

public class HttpUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    public static final String GET_WEATHER_URL = "http://api.map.baidu.com/telematics/v3/weather?location=%s&output=%s&ak=%s";

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl
     *            请求地址
     * @param requestMethod
     *            请求方式（GET、POST）
     * @param parameter
     *            提交的数据
     * @return JSONObject
     */
    public static JsonNode httpsRequest(String requestUrl,
	    String requestMethod, String parameter) {

	JsonNode jsonObject = null;
	StringBuffer buffer = new StringBuffer();
	try {
	    // 创建SSLContext对象，并使用我们指定的信任管理器初始化
	    TrustManager[] tm = { new MyX509TrustManager() };
	    SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	    sslContext.init(null, tm, new java.security.SecureRandom());
	    // 从上述SSLContext对象中得到SSLSocketFactory对象
	    SSLSocketFactory ssf = sslContext.getSocketFactory();

	    URL url = new URL(requestUrl);
	    HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
		    .openConnection();
	    httpUrlConn.setSSLSocketFactory(ssf);
	    httpUrlConn.setDoOutput(true);
	    httpUrlConn.setDoInput(true);
	    httpUrlConn.setUseCaches(false);
	    // 设置请求方式（GET/POST）
	    httpUrlConn.setRequestMethod(requestMethod.toUpperCase());
	    if ("GET".equalsIgnoreCase(requestMethod)) {
		httpUrlConn.connect();
	    }
	    // 当有数据需要提交时
	    if (null != parameter) {
		OutputStream outputStream = httpUrlConn.getOutputStream();
		// 注意编码格式，防止中文乱码
		outputStream.write(parameter.getBytes("UTF-8"));
		outputStream.close();
	    }
	    // 将返回的输入流转换成字符串
	    InputStream inputStream = httpUrlConn.getInputStream();
	    InputStreamReader inputStreamReader = new InputStreamReader(
		    inputStream, "utf-8");
	    BufferedReader bufferedReader = new BufferedReader(
		    inputStreamReader);
	    String str = null;
	    while ((str = bufferedReader.readLine()) != null) {
		buffer.append(str);
	    }
	    bufferedReader.close();
	    inputStreamReader.close();
	    // 释放资源
	    inputStream.close();
	    inputStream = null;
	    httpUrlConn.disconnect();
	    jsonObject = JsonObject.getJson(buffer.toString());
	} catch (ConnectException ce) {
	    log.error("https connection timed out.");
	} catch (Exception e) {
	    log.error("https request error:" + e.getMessage(), e);
	}
	return jsonObject;
    }

    public static JsonNode httpRequestGet(String url) throws IOException {

	JsonNode jsonObject = null;
	try {
	    // 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码
	    // URLEncoder.encode("fat man", " utf-8 ");
	    URL getUrl = new URL(url);
	    // 根据拼凑的URL，打开连接，URL.openConnection()函数会根据
	    // URL的类型，返回不同的URLConnection子类的对象，在这里我们的URL是一个http，因此它实际上返回的是HttpURLConnection
	    HttpURLConnection connection = (HttpURLConnection) getUrl
		    .openConnection();
	    // 建立与服务器的连接，并未发送数据
	    connection.connect();
	    // 发送数据到服务器并使用Reader读取返回的数据
	    BufferedReader reader = new BufferedReader(new InputStreamReader(
		    connection.getInputStream(), "UTF-8"));
	    StringBuffer buffer = new StringBuffer();
	    String line = null;
	    while ((line = reader.readLine()) != null) {
		buffer.append(line);
	    }
	    reader.close();
	    // 断开连接
	    connection.disconnect();
	    jsonObject = JsonObject.getJson(buffer.toString());
	} catch (Exception e) {
	    log.error("http request error:" + e.getMessage(), e);
	}
	return jsonObject;
    }

    public static String get(String urlStr, String charset) {

	BufferedReader br = null;
	StringBuilder content = new StringBuilder();
	InputStream in = null;
	try {
	    URL url = new URL(urlStr);
	    HttpURLConnection urlConn = (HttpURLConnection) url
		    .openConnection();
	    urlConn.setDoInput(true);
	    urlConn.setDoOutput(true);
	    urlConn.setRequestMethod("GET");
	    urlConn.setAllowUserInteraction(true); // 设置可以进行交互操作
	    urlConn.setRequestProperty("User-Agent",
		    "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	    urlConn.setUseCaches(false);
	    urlConn.setRequestProperty("Cache-Control", "no-cache");
	    urlConn.setInstanceFollowRedirects(false);
	    urlConn.setConnectTimeout(30000);
	    urlConn.setReadTimeout(30000);
	    urlConn.connect();
	    if (urlConn.getResponseCode() == 200) {
		if (StringUtil.isNotEmpty(urlConn.getContentEncoding())) {
		    String encode = urlConn.getContentEncoding().toLowerCase();
		    if (StringUtil.isNotEmpty(encode)
			    && encode.indexOf("gzip") >= 0) {
			in = new GZIPInputStream(urlConn.getInputStream());
		    }
		}

		if (null == in) {
		    in = urlConn.getInputStream();
		}
		if (null != in) {
		    br = new BufferedReader(new InputStreamReader(in, charset));
		    String line = "";
		    while ((line = br.readLine()) != null) {
			content.append(line);
		    }
		}
	    }
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    if (null != in) {
		try {
		    in.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		in = null;
	    }
	    if (null != br) {
		try {
		    br.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		in = null;
	    }
	}
	return content.toString();
    }

    public static String post(String urlStr, String charset,
	    Map<String, String> map) {

	BufferedReader br = null;
	StringBuilder content = new StringBuilder();
	InputStream in = null;
	try {
	    URL url = new URL(urlStr);
	    HttpURLConnection urlConn = (HttpURLConnection) url
		    .openConnection();
	    urlConn.setDoInput(true);
	    urlConn.setDoOutput(true);
	    urlConn.setRequestMethod("POST");
	    urlConn.setAllowUserInteraction(true); // 设置可以进行交互操作
	    urlConn.setRequestProperty("User-Agent",
		    "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	    urlConn.setUseCaches(false);
	    urlConn.setRequestProperty("Cache-Control", "no-cache");
	    urlConn.setInstanceFollowRedirects(false);
	    urlConn.setConnectTimeout(30000);
	    urlConn.setReadTimeout(30000);
	    StringBuffer params = new StringBuffer();
	    Set<String> set = map.keySet();
	    Iterator<String> i = set.iterator();
	    while (i.hasNext()) {
		String paraName = i.next();
		String value = URLEncoder.encode(map.get(paraName), "utf-8");
		String paraValue = value;
		params.append(paraName + "=" + paraValue + "&");
	    }
	    urlConn.getOutputStream().write(
		    params.toString().substring(0, params.length() - 1)
			    .getBytes());
	    urlConn.connect();
	    if (urlConn.getResponseCode() == 200) {
		if (StringUtil.isNotEmpty(urlConn.getContentEncoding())) {
		    String encode = urlConn.getContentEncoding().toLowerCase();
		    if (StringUtil.isNotEmpty(encode)
			    && encode.indexOf("gzip") >= 0) {
			in = new GZIPInputStream(urlConn.getInputStream());
		    }
		}

		if (null == in) {
		    in = urlConn.getInputStream();
		}
		if (null != in) {
		    br = new BufferedReader(new InputStreamReader(in, charset));
		    String line = "";
		    while ((line = br.readLine()) != null) {
			content.append(line);
		    }
		}
	    }
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    if (null != in) {
		try {
		    in.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		in = null;
	    }
	    if (null != br) {
		try {
		    br.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		in = null;
	    }
	}
	return content.toString();
    }

    public static JsonNode httpPost(String url, Object data) {

	HttpPost httpPost = new HttpPost(url);
	CloseableHttpResponse response = null;
	CloseableHttpClient httpClient = HttpClients.createDefault();
	RequestConfig requestConfig = RequestConfig.custom().
		setSocketTimeout(10000).setConnectTimeout(10000).build();
	httpPost.setConfig(requestConfig);
	httpPost.addHeader("Content-Type", "application/json");

	try {
	    StringEntity requestEntity = new StringEntity(data.toString(), "utf-8");
	    httpPost.setEntity(requestEntity);

	    response = httpClient.execute(httpPost, new BasicHttpContext());

	    if (response.getStatusLine().getStatusCode() != 200) {

		log.error("request url failed, http code=" + response.getStatusLine().getStatusCode()
			+ ", url=" + url);
		return null;
	    }
	    HttpEntity entity = response.getEntity();
	    if (entity != null) {
		String resultStr = EntityUtils.toString(entity, "utf-8");
		return JsonObject.getJson(resultStr);
	    }
	} catch (IOException e) {
	    log.error("request url=" + url + ", exception, msg=" + e.getMessage(), e);
	} finally {
	    if (response != null) {
		try {
		    response.close();
		} catch (IOException e) {
		    log.error(e.getMessage(), e);
		}
	    }
	}

	return null;
    }

}
