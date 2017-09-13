package cn.imexue.ec.common.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;

public class HttpClientFactory {

	private static PoolingHttpClientConnectionManager manager = null;
	
	private static CloseableHttpClient httpClient = null;
	
	static{
		Registry<ConnectionSocketFactory> socketFactory =
				RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", SSLConnectionSocketFactory.getSystemSocketFactory())
				.build();
		
		HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection>
			connFactory = new ManagedHttpClientConnectionFactory(
					DefaultHttpRequestWriterFactory.INSTANCE,
					DefaultHttpResponseParserFactory.INSTANCE
					);
		
		DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;
		
		manager = new PoolingHttpClientConnectionManager(socketFactory,connFactory,dnsResolver);
		
		SocketConfig defaultSocketconConfig = SocketConfig.custom()
				.setTcpNoDelay(true).build();
		manager.setDefaultSocketConfig(defaultSocketconConfig);
		manager.setMaxTotal(300);
		manager.setValidateAfterInactivity(5 * 1000);
		
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(2 * 1000)
				.setSocketTimeout(5 * 1000)
				.setConnectionRequestTimeout(2000)
				.build();
		
		httpClient = HttpClients.custom()
				.setConnectionManager(manager)
				.setConnectionManagerShared(false)
				.evictIdleConnections(60, TimeUnit.SECONDS)
				.setDefaultRequestConfig(requestConfig)
				.setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE)
				.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
				.setRetryHandler(new DefaultHttpRequestRetryHandler())
				.build();
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static CloseableHttpClient getHttpClient(){
		return httpClient;
	}
	
	
}
