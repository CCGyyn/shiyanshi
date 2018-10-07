package com.zeng.ezsh.wy.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;
/**
 * @description 上传文件工具类
 *
 * @author qwc
 */
public class UploadFileUtil {
	/**
	 * @description 多张图片同时上传
	 *
	 * @auhtor qwc 2017年12月6日下午10:18:46
	 * @param files
	 * @param folderName
	 * @param id
	 * @return String
	 */
	public static String fileUploadImgs(MultipartFile[] files,
			String folderName, int id) {
		String saveTosqlurl = null;// 保存到数据库中的路径
		String bootUrl = "/usr";// 保存的根目录
		String uploadUrl = "/ezsh/upload/images/" + folderName + "/" + id + "/";
		String returnUrl = null;
		File dir = new File(bootUrl + uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 判断file数组不能为空并且长度大于0
		if (files != null && files.length > 0) {
			// 循环获取file数组中得文件
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				if (!file.isEmpty()) {
					try {
						saveTosqlurl = uploadUrl
								+ DateUtil.dateToStr(new Date(),
										DateUtil.DATE_TIME_NO_SLASH)
								+ i + ".jpg";
						file.transferTo(new File(bootUrl + saveTosqlurl));// 保存到服务器中

						if (returnUrl == null) {
							returnUrl = saveTosqlurl;
						} else {
							returnUrl = returnUrl + "," + saveTosqlurl;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return returnUrl;
	}
}
