package com.zeng.ezsh.wy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.fabric.Response;
import com.zeng.ezsh.wy.service.PrivilegeService;

@Controller
@RequestMapping("priv")
public class PrivilegeAction {
	private static Logger logger = Logger.getLogger("PrivilegeAction");
	@Resource
	PrivilegeService privilegeService;

	/**
	 * @author qwc 2017年7月19日下午4:09:57 void 从权限表中获取权限信息
	 * @throws IOException（测试用）
	 */
	@RequestMapping("getPrivList")
	public void getPrivList(HttpServletResponse response) throws IOException {
		/* PageHelper.startPage(1, 10); */
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Map<Object, Object> paramPrivMap = new HashMap<Object, Object>();

		JSONArray json = privilegeService.selectAllPrivList(paramPrivMap);
		out.write(json.toString());
	}

}
