package com.zeng.ezsh.kaptcha.action;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
@Controller
@RequestMapping("kaptcha")
public class KaptchaController {
	private Logger logger = Logger.getLogger(KaptchaController.class);
	@Resource
	private Producer kaptchaProducer;

	/**
	 * @description 获取验证码图片
	 *
	 * @auhtor qwc 2017年10月11日下午5:53:04
	 * @param model
	 * @param request
	 * @param response
	 * @throws IOException void
	 */
	@RequestMapping(value = "/getKaptchaImage", method = RequestMethod.GET)
	public void getCaptchaImage(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
		String capText = kaptchaProducer.createText();
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,
				capText);// 保存到session中
		logger.info("验证码的内容为：" + capText);
		BufferedImage img = kaptchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(img, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
	}
}
