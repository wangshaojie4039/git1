package cn.imexue.ec.web.web.controller.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.util.JsonObject;
import cn.imexue.ec.common.util.StringUtil;
import cn.imexue.ec.common.util.TimeUtil;
import cn.imexue.ec.web.util.WebConstants;

import com.fasterxml.jackson.databind.JsonNode;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;

@Api
@RestController
@RequestMapping("/image")
public class ImageController {

	private static final Logger log = LoggerFactory.getLogger(ImageController.class);
	
	/**
	 * 图片上传的格式
	 */
	@Value("${app.access.img.type}")
	private String[] imgTypes;
	
	@Value("${cos.appid}")
	private long cosAppid;
	
	@Value("${cos.secret.id}")
	private String cosSecretId;
	
	@Value("${cos.secret.key}")
	private String cosSecretKey;
	
	@Value("${cos.region}")
	private String cosRegion;
	
	@Value("${cos.bucket.name}")
	private String cosBucketName;
	
	@Value("${cos.file.root.url}")
	private String cosFileRootUrl;
	
	private static final String CODE_URL = "/image/codeImage?_=";
	
	/**
	 * @api {GET} /image/url 获取验证码地址
	 * @apiGroup common
	 * @apiName imageUrl
	 */
	@RequestMapping(value="/url",method=RequestMethod.GET)
	public RespJson getCodeUrl(){
		return RespJsonFactory.buildSuccess(CODE_URL+RandomUtils.nextInt());
	}
	
	
	/**
	 * @api {GET} /image/codeImage 验证码
	 * @apiGroup common
	 * @apiName imageCodeImage
	 */
	@RequestMapping(value="/codeImage",method=RequestMethod.GET)
	public void codeImage(HttpSession session, HttpServletResponse response) throws IOException {
		response.reset();
		int width = 120, height = 46;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ITALIC, 50));
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String ra = "";
			char rand = getRandChar();
			ra += rand;
			sRand += rand;
			g.setColor(getRandColor(20, 130));
			g.drawString(ra, 23 * i + 10, 36);
		}
//		System.out.println(session.getAttribute(Constants.SESSION_RANDOM_CODE));
		session.setAttribute(WebConstants.SESSION_USER_VERIFY_CODE, sRand);
//		System.out.println(session.getAttribute(Constants.SESSION_RANDOM_CODE));
		g.dispose();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
		ImageIO.write(image, "JPEG", imageOut);
		imageOut.close();
		
		response.setContentType("image/png");
		
        OutputStream stream = response.getOutputStream();
        stream.write(output.toByteArray());
        stream.flush();
        stream.close();
	}

	private char getRandChar() {
		Random random = new Random();
		String s = "23456789abcdefghijkmnpqistuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
		return s.charAt(random.nextInt(56));
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
	/**
	 * @api {POST} /image/uploadImg 上传图片
	 * @apiGroup common
	 * @apiName imageUploadImg
	 * 
	 * @apiParam {File} file 图片(类型:jpg,jpeg,gif,png,bmp,大小:1M)
	 * 
	 * @apiSuccess {String} data 图片相对路径
	 * 
	 * @apiError 2004  图片类型错误
	 */
	@ApiOperation("uploadImg")
	@ApiImplicitParams({
		@ApiImplicitParam(dataType="file",name="file",required=true,paramType="form")
	})
	@RequestMapping(value="/uploadImg",method=RequestMethod.POST)
	public RespJson  uploadImg(@RequestParam("file") MultipartFile file) throws IOException {
		String fileName=file.getOriginalFilename();
		String fileType=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
		if(!validateImgType(fileType))
			throw new AppChkException(2004,"app.file.type.err",fileType,imgTypes); 
		
		Credentials cred = new Credentials(cosAppid, cosSecretId, cosSecretKey);
		  // 初始化客户端配置
		ClientConfig clientConfig = new ClientConfig();
	        // 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
		clientConfig.setRegion(cosRegion);
		COSClient cosClient = new COSClient(clientConfig, cred);
		UploadFileRequest uploadFileRequest = new UploadFileRequest(cosBucketName, createUploadPath(fileType) , file.getBytes());
		String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
		JsonNode json = JsonObject.getJson(uploadFileRet);
		
		String absoluteUrl=json.get("data").get("source_url").asText();
		if(StringUtil.isNotEmpty(absoluteUrl)){
			absoluteUrl = absoluteUrl.replace(cosFileRootUrl, "");
		}
		log.info("文件{}上传成功",absoluteUrl);

		return  RespJsonFactory.buildSuccess(absoluteUrl); 
	}
	
	/**
	 * 判断文件格式
	 * @param type
	 * @return
	 */
	private boolean validateImgType(String type){
		for(int i=0;i<imgTypes.length;i++){
			if(imgTypes[i].equalsIgnoreCase(type)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 创建上传路径
	 * @param fileType
	 * @return
	 */
	private String createUploadPath(String fileType){
		return "/image/"+TimeUtil.getYear()+"/"+TimeUtil.getMonth()+"/"+TimeUtil.getDay()+"/"+ java.util.UUID.randomUUID().toString()+"."+fileType;
	}
	
}
