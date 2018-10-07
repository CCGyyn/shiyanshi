package com.zeng.ezsh.wy.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
/**
 * @description xml转化工具类
 *
 * @author qwc
 */
public class XStreamUtil {

	/**
	 * @description xml转java对象
	 *
	 * @auhtor qwc 2018年1月20日 下午3:36:09
	 * @param xml
	 * @param clas
	 * @return Object
	 */
	public static Object xmlToBean(String xml, Class<?> clas) {
		XStream xStream = new XStream();
		xStream.alias("xml", clas);
		return xStream.fromXML(xml);
	}

	/**
	 * @description java对象转xml
	 *
	 * @auhtor qwc 2018年1月20日 下午3:35:57
	 * @param obj
	 * @param clas
	 * @return String
	 */
	public static String beanToXml(Object obj, Class<?> clas) {
		XStream xStream = new XStream();
		xStream.alias("xml", clas);
		return xStream.toXML(obj);
	}

	/**
	 * @description 把返回来的xml数据解析成map对象
	 *
	 * @auhtor qwc 2018年1月26日 下午10:50:14
	 * @param request
	 * @return Map<String,String>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> parseReqXml(HttpServletRequest request)
			throws Exception {
		/*
		 * String test = "test inputStream"; InputStream inputStream = new
		 * ByteArrayInputStream(test.getBytes());
		 */

		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();

		// 读取输入流
		SAXReader reader = new SAXReader();

		Document document = reader.read(inputStream);

		String requestXml = document.asXML();
		String subXml = requestXml.split(">")[0] + ">";
		requestXml = requestXml.substring(subXml.length());
		/* System.out.println(requestXml); */

		// 得到xml根元素
		Element root = document.getRootElement();

		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();
		// 得到根元素的全部子节点
		List<Element> elementList = root.elements();
		// 遍历全部子节点
		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
		}
		inputStream.close();
		inputStream = null;
		return map;
	}

	/**
	 * @description 把返回来的xml数据解析成map对象
	 *
	 * @auhtor qwc 2018年1月26日 下午10:49:55
	 * @param request
	 * @return Map<String,String>
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request)
			throws IOException, DocumentException {
		HashMap<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();

		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);

		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> list = (List<Element>) root.elements();

		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
}
