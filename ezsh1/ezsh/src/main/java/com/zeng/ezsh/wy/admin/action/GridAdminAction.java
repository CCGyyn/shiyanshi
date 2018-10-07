package com.zeng.ezsh.wy.admin.action;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.dao.ChargeItemMapper;
import com.zeng.ezsh.wy.entity.ChargeItem;
import com.zeng.ezsh.wy.entity.Grid;
import com.zeng.ezsh.wy.service.GridAdminService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RequestMapping("grid")
@Controller
public class GridAdminAction {
	@Resource
    GridAdminService gridAdminService;

	@Resource
    ChargeItemMapper chargeItemMapper;

	/**
	 * @author qwc 
	 * 2017年9月23日上午10:20:38 
	 * void 添加表类别
	 * @throws IOException
	 */
	@RequestMapping(value = "add")
	public void addGrid(HttpServletRequest request,
			HttpServletResponse response, Grid grid) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		// 判断表计名称是否存在
		int isExist = gridAdminService.checkIsExist(grid);
		if (isExist > 0) {
			retJsonUtil.setStatus("2");
			retJsonUtil.setMessage("不能重复添加");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		
		// 执行添加操作
		int status = gridAdminService.insert(grid);
		
		// 封装返回结果
		if (status > 0) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("添加成功");
			out.write(retJsonUtil.getRetJsonM());
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("添加失败");
			out.write(retJsonUtil.getRetJsonM());
		}
	}

	/**
	 * @author qwc 2017年10月23日下午7:00:23
	 * @param request
	 * @param response
	 * @param grid
	 * @throws IOException
	 * void 删除表计类别
	 */
	@RequestMapping(value = "delete")
	public void deleteGrid(HttpServletRequest request,
			HttpServletResponse response, Grid grid) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		// 执行删除操作
		int status = gridAdminService.deleteGrid(grid);
		
		// 封装返回结果
		if (status > 0) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("删除成功");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("删除失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年9月23日下午12:16:04
	 * @param request
	 * @param response
	 * @param grid
	 * @throws IOException
	 * void 修改抄表类别
	 */
	@RequestMapping(value = "update")
	public void updateGrid(HttpServletRequest request,
			HttpServletResponse response, Grid grid) throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		// 判断表计名称是否存在
		int isExist = gridAdminService.checkIsExist(grid);
		if (isExist > 0) {
			retJsonUtil.setStatus("2");
			retJsonUtil.setMessage("表计名重复");
			out.write(retJsonUtil.getRetJsonM());
			return;
		}
		
		// 执行更新操作
		int status = gridAdminService.updateSelective(grid);
		
		// 封装返回结果
		if (status > 0) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("修改成功");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("修改失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年9月24日下午8:15:57
	 * @param request
	 * @param response
	 * @param grid
	 * @throws IOException
	 * void 分页条件获取表计类别集合
	 */
	@RequestMapping(value = "select")
	public void selectGrid(
			HttpServletRequest request,
			HttpServletResponse response,
			Grid grid,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "rows", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();// 创建json返回对象
		
		if (grid.getpManagerId() == null) {
			retJsonUtil.setTotal(0);
			out.write(retJsonUtil.getRetJsonEasyGrid());
			return;
		}
		
		// 分页获取
		PageHelper.startPage(startPage, pageSize);
		List<Grid> list = gridAdminService.selectSelective(grid);
		PageInfo<Grid> page = new PageInfo<Grid>(list);

		// 返回结果
		retJsonUtil.setList(list);
		retJsonUtil.setTotal(page.getTotal());
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}

	/**
	 * @author qwc 2017年9月24日下午10:16:41
	 * @param request
	 * @param response
	 * @param grid
	 * @throws IOException
	 * void 获取类别集合（简要。用于形成下拉框选项）
	 */
	@RequestMapping(value = "selectSimple")
	public void selectGridSimple(HttpServletRequest request,
			HttpServletResponse response, Grid grid) throws IOException {
		System.out.println("哇，你在搞什么？"+grid.getpManagerId());
		PrintWriter out = response.getWriter();
		//List<Grid> list = gridAdminService.selectSelectiveSimple(grid);
		List<ChargeItem> list=chargeItemMapper.selectByManagerId(grid.getpManagerId());
		out.write(JSONArray.fromObject(list).toString());
	}
}
