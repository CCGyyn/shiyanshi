package com.zeng.ezsh.wy.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
/**
 * @description http请求工具类
 *
 * @author qwc
 */
public class HttpUrlConnectionUtil {
	/**
	 * @description 客服端post请求,参数以url形式发送到服务端的数据
	 *
	 * @auhtor qwc 2018年1月10日 下午3:24:28
	 * @param pathUrl 请求url
	 * @param param 请求参数
	 * @return String
	 */
	public static String urlPost(String pathUrl, Map<String, Object> param) {
		String resposeString = null;
		try {
			// 建立连接
			URL url = new URL(pathUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();

			// //设置连接属性
			httpConn.setDoOutput(true);// 使用 URL 连接进行输出
			httpConn.setDoInput(true);// 使用 URL 连接进行输入
			httpConn.setUseCaches(false);// 忽略缓存
			httpConn.setRequestMethod("POST");// 设置URL请求方法
			// String requestString = "客服端要以以流方式发送到服务端的数据...";
			StringBuffer requestString = new StringBuffer();
			if (param != null) {
				for (Entry<String, Object> e : param.entrySet()) {
					requestString.append(e.getKey());
					requestString.append("=");
					requestString.append(e.getValue());
					requestString.append("&");
				}
				requestString = requestString
						.deleteCharAt(requestString.length() - 1);
			}

			// 设置请求属性
			// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
			httpConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpConn.setRequestProperty("Charset", "UTF-8");

			// 建立输出流，并写入数据
			System.out.println("requestString>>" + requestString);
			DataOutputStream dos = new DataOutputStream(
					httpConn.getOutputStream());
			dos.writeBytes(requestString.toString());
			dos.flush();
			dos.close();
			// 获得响应状态
			int responseCode = httpConn.getResponseCode();

			if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功
				// 当正确响应时处理数据
				StringBuffer sb = new StringBuffer();
				String readLine;
				BufferedReader responseReader;
				// 处理响应流，必须与服务器响应流输出的编码一致
				responseReader = new BufferedReader(new InputStreamReader(
						httpConn.getInputStream(), "UTF-8"));
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine).append("\n");
				}
				responseReader.close();
				resposeString = sb.toString();
				System.out.println("sb.toString()>>" + sb.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resposeString;

	}

	/**
	 * @description 客服端post请求,参数以流的形式发送到服务端的数据
	 *
	 * @auhtor qwc 2017年10月29日下午9:30:29
	 * @param pathUrl 请求路径url
	 * @param param 请求参数
	 * @param encode 编码字符集
	 * @return String
	 */
	public static String urlPost(String pathUrl, String param, String encode) {
		String resposeString = null;
		try {
			// 建立连接
			URL url = new URL(pathUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();

			// //设置连接属性
			httpConn.setDoOutput(true);// 使用 URL 连接进行输出
			httpConn.setDoInput(true);// 使用 URL 连接进行输入
			httpConn.setUseCaches(false);// 忽略缓存
			httpConn.setRequestMethod("POST");// 设置URL请求方法

			// 设置请求属性
			// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
			httpConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpConn.setRequestProperty("Charset", "UTF-8");

			// 建立输出流，并写入数据
			DataOutputStream dos = new DataOutputStream(
					httpConn.getOutputStream());
			dos.write(param.getBytes("UTF-8"));
			dos.flush();
			dos.close();
			// 获得响应状态
			int responseCode = httpConn.getResponseCode();

			if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功
				// 当正确响应时处理数据
				StringBuffer sb = new StringBuffer();
				String readLine;
				BufferedReader responseReader;
				// 处理响应流，必须与服务器响应流输出的编码一致
				responseReader = new BufferedReader(new InputStreamReader(
						httpConn.getInputStream(), encode));
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine).append("\n");
				}
				responseReader.close();
				resposeString = sb.toString();
				System.out.println("sb.toString()>>" + sb.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resposeString;
	}

	/**
	 * @description 用于微信支付发起请求
	 *
	 * @auhtor qwc 2018年1月21日 下午9:55:08
	 * @param pathUrl 请求接口地址
	 * @param param 请求参数
	 * @param encode 编码字符集
	 * @return String
	 */
	public static String urlPostWeChatPay(String pathUrl, String param,
			String encode) {
		String resposeString = null;
		try {
			// 建立连接
			URL url = new URL(pathUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();

			// //设置连接属性
			httpConn.setDoOutput(true);// 使用 URL 连接进行输出
			httpConn.setDoInput(true);// 使用 URL 连接进行输入
			httpConn.setUseCaches(false);// 忽略缓存
			httpConn.setRequestMethod("POST");// 设置URL请求方法

			// 设置请求属性
			// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
			httpConn.setRequestProperty("Content-Type", "text/xml");
			httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpConn.setRequestProperty("User-Agent", "wxpay sdk java v1.0");
			httpConn.setRequestProperty("Charset", "UTF-8");

			// 建立输出流，并写入数据
			DataOutputStream dos = new DataOutputStream(
					httpConn.getOutputStream());
			dos.write(param.getBytes("UTF-8"));
			dos.flush();
			dos.close();
			// 获得响应状态
			int responseCode = httpConn.getResponseCode();

			if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功
				// 当正确响应时处理数据
				StringBuffer sb = new StringBuffer();
				String readLine;
				BufferedReader responseReader;
				// 处理响应流，必须与服务器响应流输出的编码一致
				responseReader = new BufferedReader(new InputStreamReader(
						httpConn.getInputStream(), encode));
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine).append("\n");
				}
				responseReader.close();
				resposeString = sb.toString();
				System.out.println("sb.toString()>>" + sb.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resposeString;
	}

	/**
	 * @description 发送get请求
	 *
	 * @auhtor qwc 2017年11月10日下午9:56:18
	 * @param path 请求路径
	 * @param encode 编码字符集
	 * @return String
	 */
	public static String urlGet(String path, String encode) {
		try {
			URL url = new URL(path.trim());
			// 打开连接
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();

			if (200 == urlConnection.getResponseCode()) {
				// 得到输入流
				InputStream is = urlConnection.getInputStream();
				ByteArrayOutputStream datas = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while (-1 != (len = is.read(buffer))) {
					datas.write(buffer, 0, len);
					datas.flush();
				}
				return datas.toString(encode);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
