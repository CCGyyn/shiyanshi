package com.zeng.ezsh.wy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeng.ezsh.wy.entity.City;
import com.zeng.ezsh.wy.entity.District;
import com.zeng.ezsh.wy.entity.Management;
import com.zeng.ezsh.wy.entity.Province;
import com.zeng.ezsh.wy.service.ManagementService;
import com.zeng.ezsh.wy.service.ProvCityDistrService;
import com.zeng.ezsh.wy.utils.AccessTokenUtil;

@Controller
@RequestMapping("prCiDistr")
public class ProvCityDistrAction {
	@Resource
	ManagementService managementServiceToPCD;
	@Resource
	ProvCityDistrService provCityDistrService;

	/**
	 * @author qwc 2017年8月5日下午1:37:17 void 获取省
	 * @throws IOException
	 */
	@RequestMapping("gtProvince")
	public void getPro(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject retJson = new JSONObject();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Province> provinceList = provCityDistrService.getProvinceList();
		// String token=request.getParameter("token");//门令，登录状态凭据
		retJson.put("status", 1);
		retJson.put("data", provinceList);
		retJson.put("message", "获取成功");
		out.write(JSONObject.fromObject(retJson).toString());
	}

	/**
	 * @author qwc 2017年8月5日下午1:41:13
	 * @param request void
	 * @throws IOException
	 */
	@RequestMapping("gtCity")
	public void getCity(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		JSONObject retJson = new JSONObject();
		String provinceId = request.getParameter("provinceId");
		List<City> cityList = provCityDistrService
				.getCityList(Integer.parseInt(provinceId));
		retJson.put("status", 1);
		retJson.put("data", cityList);
		retJson.put("message", "获取成功");
		out.write(JSONObject.fromObject(retJson).toString());
	}

	/**
	 * @author qwc 2017年8月5日下午1:41:16
	 * @param request void
	 * @throws IOException
	 */
	@RequestMapping("gtDistrict")
	public void getDistr(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		JSONObject retJson = new JSONObject();
		String cityId = request.getParameter("cityId");
		List<District> districtList = provCityDistrService
				.getDistrictList(Integer.parseInt(cityId));
		retJson.put("status", 1);
		retJson.put("data", districtList);
		retJson.put("message", "获取成功");
		out.write(JSONObject.fromObject(retJson).toString());
	}

	/**
	 * @author qwc 2017年8月5日下午4:01:19
	 * @param request
	 * @param response
	 * @throws IOException void 省市区级联获取小区
	 */
	@RequestMapping("gtCommunityByPCD")
	public void getCommunityByPCDistrict(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		JSONObject retJson = new JSONObject();
		String provinceId = request.getParameter("provinceId");
		String cityId = request.getParameter("cityId");
		String districtId = request.getParameter("districtId");
		paramMap.put("provinceId", Integer.parseInt(provinceId));
		paramMap.put("cityId", Integer.parseInt(cityId));
		paramMap.put("districtId", Integer.parseInt(districtId));
		List<Management> managementList = managementServiceToPCD
				.getManagerListByPCityDistrId(paramMap);
		retJson.put("status", 1);
		retJson.put("data", managementList);
		retJson.put("message", "获取成功");
		out.write(JSONObject.fromObject(retJson).toString());
	}

	/**
	 * @author qwc 2017年8月12日下午8:21:12 void 根据城市名和小区名来搜索小区
	 * @throws IOException
	 */
	@RequestMapping("searchByCMName")
	public void searchBycityNameOrManagerName(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		JSONObject retJson = new JSONObject();
		String managerName = request.getParameter("keyWord");
		String provinceName = request.getParameter("provinceName");
		String cityName = request.getParameter("cityName");
		Province province = new Province();
		City city = new City();
		int provinceId = 0;
		int cityId = 0;
		province = provCityDistrService
				.getProvinceIdByProvinceName(provinceName);
		city = provCityDistrService.getCityIdByCityName(cityName);
		if (province != null) {
			provinceId = province.getId();
		}
		if (city != null) {
			cityId = city.getId();
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (provinceId != 0) {
			paramMap.put("provinceId", provinceId);
		}
		if (cityId != 0) {
			paramMap.put("cityId", cityId);
		}
		if (managerName != null) {
			paramMap.put("keyWord", managerName);
		}
		List<Management> managementList = managementServiceToPCD
				.getManagerListByPCName(paramMap);
		retJson.put("status", 1);
		retJson.put("data", managementList);
		retJson.put("message", "获取成功");
		out.write(JSONObject.fromObject(retJson).toString());
	}

}
