package com.zeng.ezsh.wy.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeng.ezsh.wy.entity.TemplateSMS;
/**
 * @description 发送短信工具类
 *
 * @author qwc
 */
public class MsgUtil {
	private static Logger logger = Logger.getLogger(MsgUtil.class);

	public boolean isTest = Boolean
			.parseBoolean(SysConfigUtil.getInstance().getProperty("is_test"));
	public String server = SysConfigUtil.getInstance()
			.getProperty("rest_server");
	public String sslIP = SysConfigUtil.getInstance()
			.getProperty("http_ssl_ip");//
	public int sslPort = SysConfigUtil.getInstance()
			.getPropertyInt("http_ssl_port");//
	public String version = SysConfigUtil.getInstance().getProperty("version");//

	// 实体类转化json对象
	public static ObjectMapper objectMapper;
	private static String accountSid = "923eb6bad054e605c35fa966642a92f8";
	private static String authToken = "e6a19c948e64110710469d83d9f63525";
	private static String appId = "97e26c1e2d614c0f985e95c449279f41";
	private static String templateId = "109453";

	/**
	 * @description 生成验证参数
	 *
	 * @auhtor qwc 2017年7月30日下午10:46:37
	 * @param accountSid
	 * @param authToken
	 * @param timestamp
	 * @param encryptUtil
	 * @return String
	 * @throws Exception
	 */
	public String getSignature(String accountSid, String authToken,
			String timestamp, EncryptUtil encryptUtil) throws Exception {
		String sig = accountSid + authToken + timestamp;
		String signature = encryptUtil.md5Digest(sig);
		return signature;
	}

	/**
	 * @description 设置https请求链接头
	 *
	 * @auhtor qwc 2017年7月30日下午10:47:02
	 * @return StringBuffer
	 */
	public StringBuffer getStringBuffer() {
		StringBuffer sb = new StringBuffer("https://");
		sb.append(server);
		return sb;
	}

	/**
	 * @description 设置HTTP标准包头字段
	 *
	 * @auhtor qwc 2017年7月30日下午10:48:30
	 * @param cType
	 * @param accountSid
	 * @param authToken
	 * @param timestamp
	 * @param url
	 * @param httpclient
	 * @param encryptUtil
	 * @param body
	 * @return HttpResponse
	 * @throws Exception
	 */
	public HttpResponse post(String cType, String accountSid, String authToken,
			String timestamp, String url, CloseableHttpClient httpclient,
			EncryptUtil encryptUtil, String body) throws Exception {
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Accept", cType);
		httppost.setHeader("Content-Type", cType + ";charset=utf-8");
		String src = accountSid + ":" + timestamp;
		String auth = encryptUtil.base64Encoder(src);
		httppost.setHeader("Authorization", auth);
		BasicHttpEntity requestBody = new BasicHttpEntity();
		requestBody
				.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
		requestBody.setContentLength(body.getBytes("UTF-8").length);
		httppost.setEntity(requestBody);

		// 发生post请求
		HttpResponse response = httpclient.execute(httppost);
		return response;
	}

	/**
	 * @description 请求短信验证方法
	 *
	 * @auhtor qwc 2017年7月30日下午10:54:23
	 * @param telephoneNumber
	 * @param param
	 * @return String
	 * @throws IOException
	 */
	public String reqSMS(String telephoneNumber, String param)
			throws IOException {
		// TODO Auto-generated method stub
		String result = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// MD5加密
			EncryptUtil encryptUtil = new EncryptUtil();
			// 构造请求URL内容
			String timestamp = DateUtil.dateToStr(new Date(),
					DateUtil.DATE_TIME_NO_SLASH);// 时间戳
			String signature = getSignature(accountSid, authToken, timestamp,
					encryptUtil);
			String url = getStringBuffer().append("/").append(version)
					.append("/Accounts/").append(accountSid)
					.append("/Messages/templateSMS").append("?sig=")
					.append(signature).toString();
			TemplateSMS templateSMS = new TemplateSMS();
			templateSMS.setAppId(appId);
			templateSMS.setTemplateId(templateId);
			templateSMS.setTo(telephoneNumber);
			templateSMS.setParam(param);
			objectMapper = new ObjectMapper();
			String body = objectMapper.writeValueAsString(templateSMS);
			body = "{\"templateSMS\":" + body + "}";
			logger.info("timestamp=" + timestamp);
			logger.info(body);

			HttpResponse response = post("application/json", accountSid,
					authToken, timestamp, url, httpclient, encryptUtil, body);
			HttpEntity entity = response.getEntity();// 获取response响应对象
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			httpclient.close();
		}
		return result;
	}

}
