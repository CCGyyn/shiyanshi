package com.zeng.ezsh.wechat.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wechat.official.config.WechatOfficialConfig;
import com.zeng.ezsh.wy.entity.Teacher;
import com.zeng.ezsh.wy.entity.TeacherResume;
import com.zeng.ezsh.wy.service.TeacherResumeService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

@Controller
@RequestMapping(value = "teacherResumeT")
public class TeacherResumeAction {
	@Resource
	TeacherResumeService teacherResumeService;

	/**
	 * @author qwc 2017年11月28日下午10:02:01
	 * @param request
	 * @param response
	 * @param record
	 * @throws IOException void 更新个人简历
	 */
	@RequestMapping(value = "update")
	public void update(HttpServletRequest request, HttpServletResponse response,
			TeacherResume record, HttpSession session) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		int status = teacherResumeService.updateByPrimaryKeySelective(record);

		if (status > 0) {
			// 更新session
			Teacher teacher = (Teacher) session
					.getAttribute(WechatOfficialConfig.WECHAT_SESSION_ID);
			teacher.setResumeInfo(record);
			session.setAttribute(WechatOfficialConfig.WECHAT_SESSION_ID,
					teacher);

			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("修改成功");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("修改失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年11月28日下午10:08:37
	 * @param request
	 * @param response
	 * @param startPage
	 * @param pageSize
	 * @throws IOException void 获取家教老师集合
	 */
	@RequestMapping(value = "select")
	public void select(HttpServletRequest request, HttpServletResponse response,
			TeacherResume teacherResume,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "rows", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		// 创建json返回对象
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// 创建查询参数对象
		Map<String, Object> param = new HashMap<String, Object>();
		// 家教老师所在学校不为空
		if (StringUtils.isNotBlank(teacherResume.getPlaceSchool())) {
			param.put("placeSchool", teacherResume.getPlaceSchool());
		}
		// 所在省份
		if (StringUtils.isNotBlank(teacherResume.getWorkProvince())) {
			param.put("workProvince", teacherResume.getWorkProvince());
		}
		// 所在城市
		if (StringUtils.isNotBlank(teacherResume.getWorkCity())) {
			param.put("workCity", teacherResume.getWorkCity());
		}
		// 所期望课程
		if (StringUtils.isNotBlank(teacherResume.getExpectCourse())) {
			param.put("expectCourse", teacherResume.getExpectCourse());
		}

		// 执行分页获取家教老师信息列表
		PageHelper.startPage(startPage, pageSize);
		List<TeacherResume> list = teacherResumeService
				.selectByPrimaryKey(param);
		PageInfo<TeacherResume> page = new PageInfo<TeacherResume>(list);

		// 返回查询结果
		Map<String, Object> retMap = new HashMap<String, Object>();
		retJsonUtil.setStatus("1");
		retJsonUtil.setMessage("获取成功");
		retMap.put("totalPage", page.getTotal());
		retMap.put("teacherList", list);
		retJsonUtil.setRetMap(retMap);
		out.write(retJsonUtil.getRetJsonM());
	}
}
