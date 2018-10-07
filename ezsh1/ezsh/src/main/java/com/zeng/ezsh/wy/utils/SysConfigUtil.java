package com.zeng.ezsh.wy.utils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
/**
 * @description 获取config.properties文件工具类
 *
 * @author qwc
 */
public class SysConfigUtil {
	private static Logger log = Logger.getLogger(SysConfigUtil.class);
	private Properties props = null;
	private static volatile SysConfigUtil conf;

	private SysConfigUtil() {
		props = new Properties();
		loadConfigProps();
	}

	public static SysConfigUtil getInstance() {
		if (conf == null) {
			synchronized (SysConfigUtil.class) {
				if (conf == null) {
					conf = new SysConfigUtil();
				}
			}
		}
		return conf;
	}

	/**
	 * @description 读取config.properties文件
	 *
	 * @auhtor qwc 2018年7月30日 下午3:33:25 void
	 */
	public void loadConfigProps() {
		InputStream is = null;
		try {
			is = getClass()
					.getResourceAsStream("/WEB-INF/classes/config.properties");
			if (is == null) {
				is = getClass().getResourceAsStream("/config.properties");
			}
			InputStreamReader reader = new InputStreamReader(is, "UTF-8");
			props.load(reader);
			Iterator<String> iter = props.stringPropertyNames().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				props.setProperty(key, props.getProperty(key));
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error("load config.properties error!please check the file!", e);
		} finally {
			if (is != null) {
				try {
					is.close();
					is = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getProperty(String key) {
		String tmp = props.getProperty(key);
		if (StringUtils.isNotEmpty(tmp)) { return tmp.trim(); }
		return tmp;
	}

	public String getProperty(String key, String defaultValue) {
		String tmp = props.getProperty(key, defaultValue);
		if (StringUtils.isNotEmpty(tmp)) { return tmp.trim(); }
		return tmp;
	}

	public int getPropertyInt(String key) {
		String tmp = props.getProperty(key);
		if (StringUtils
				.isNotEmpty(tmp)) { return Integer.parseInt(tmp.trim()); }
		return 0;

	}

	public int getPropertyInt(String key, int defaultValue) {
		String tmp = props.getProperty(key);
		if (StringUtils
				.isNotEmpty(tmp)) { return Integer.parseInt(tmp.trim()); }
		return defaultValue;
	}

	public long getPropertyLong(String key, long defaultValue) {
		String tmp = props.getProperty(key);
		if (StringUtils
				.isNotEmpty(tmp)) { return Integer.parseInt(tmp.trim()); }
		return defaultValue;
	}
}
