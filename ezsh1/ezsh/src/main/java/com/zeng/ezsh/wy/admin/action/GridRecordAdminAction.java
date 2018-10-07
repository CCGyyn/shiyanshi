package com.zeng.ezsh.wy.admin.action;

import java.beans.IntrospectionException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zeng.ezsh.wy.dao.ChargeItemMapper;
import com.zeng.ezsh.wy.dao.ChargeRecordMapper;
import com.zeng.ezsh.wy.entity.ChargeItem;
import com.zeng.ezsh.wy.entity.ChargeRecord;
import javassist.expr.NewArray;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zeng.ezsh.wy.entity.Grid;
import com.zeng.ezsh.wy.entity.GridRecord;
import com.zeng.ezsh.wy.service.GridAdminService;
import com.zeng.ezsh.wy.service.GridRecordService;
import com.zeng.ezsh.wy.utils.RetJsonUtil;

/**
 * 
 * @author quanweicong 抄表记录后台管理
 */
@Controller
@RequestMapping("gridRecord")
public class GridRecordAdminAction {
	@Resource
	private GridRecordService gridRecordAdminService;
	@Resource
	private GridAdminService gridAdminService;

	@Resource
	private ChargeRecordMapper chargeRecordMapper;

	@Resource
	private ChargeItemMapper chargeItemMapper;

	/**
	 * @author qwc 2017年9月23日下午3:43:13
	 * @param response
	 * @param record
	 * @throws IOException
	 *             void
	 */
	@RequestMapping(value = "add")
	public void insertGridRecord(HttpServletResponse response, GridRecord record)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// 设置属性值
		Date date = new Date();
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setGridReadType("手工");// 设置抄表记录方式：手工方式
		record.setEnteringTime(time.format(date));// 设置抄表记录录入时间

		// 执行插入抄表记录操作
		int status = gridRecordAdminService.insert(record);

		// 封装返回结果
		if (status > 0) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("添加成功");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("添加失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年12月8日下午3:50:43 void 批量插入抄表记录（测试接口）
	 */
	@RequestMapping(value = "insertB")
	public void insertBatch() {
		gridRecordAdminService.insertGridRecordBatch();
	}

	/**
	 * @author qwc 2017年9月26日下午8:26:24
	 * @param response
	 * @param record
	 * @throws IOException
	 * void 更新抄表记录
	 */
	@RequestMapping("update")
	public void updateGridRecord(HttpServletResponse response, GridRecord record)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();

		// 执行更新记录操作
		int status = gridRecordAdminService.updateByPrimaryKeySelective(record);
		
		// 封装返回结果
		if (status > 0) {
			retJsonUtil.setStatus("1");
			retJsonUtil.setMessage("更新成功");
		} else {
			retJsonUtil.setStatus("0");
			retJsonUtil.setMessage("更新失败");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年9月26日下午10:34:09
	 * @param response
	 * @param record
	 * @throws IOException
	 * void 删除抄表记录
	 */
	@RequestMapping("delete")
	public void deleteGridRecord(HttpServletResponse response, GridRecord record)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		// 执行删除抄表记录操作
		int status = gridRecordAdminService.deletePrimaryKeySelective(record);
		
		// 封装返回结果
		if (status > 0) {
			retJsonUtil.setMessage("删除成功");
			retJsonUtil.setStatus("1");
		} else {
			retJsonUtil.setMessage("删除失败");
			retJsonUtil.setStatus("0");
		}
		out.write(retJsonUtil.getRetJsonM());
	}

	/**
	 * @author qwc 2017年9月23日下午5:11:35
	 * @param response
	 * @param record
	 * @throws IOException
	 * void 获取抄表记录
	 */
	@RequestMapping(value = "select")
	public void selectGridRecord(
			HttpServletResponse response,
			GridRecord record,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer startPage,
			@RequestParam(value = "rows", required = false, defaultValue = "10") Integer pageSize)
			throws IOException {
		PrintWriter out = response.getWriter();
		RetJsonUtil retJsonUtil = new RetJsonUtil();
		
		// 判断查询条件是否带上
		if (record.getpManagerId() == null || record.getpGridId() == null) {
			retJsonUtil.setTotal(0);
			out.write(retJsonUtil.getRetJsonEasyGrid());
			return;
		}

		// 分页获取
		PageHelper.startPage(startPage, pageSize);
		List<GridRecord> list = gridRecordAdminService.selectGridRecord(record);
		PageInfo<GridRecord> page = new PageInfo<GridRecord>(list);

		// 返回结果
		retJsonUtil.setTotal(page.getTotal());
		retJsonUtil.setList(list);
		out.write(retJsonUtil.getRetJsonEasyGrid());
	}

	/**
	 * @author qwc 2017年9月23日下午4:34:06
	 * @param response
	 * @param request
	 * @param record
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws IntrospectionException
	 * @throws ParseException
	 *             void 导出抄表记录
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("export")
	public void exportGridRecordExcel(HttpServletResponse response,
			HttpServletRequest request, GridRecord record)
			throws InvocationTargetException, ClassNotFoundException,
			IllegalAccessException, IntrospectionException, ParseException, UnsupportedEncodingException {
		response.reset(); // 清除buffer缓存
		//Map<String, Object> map = new HashMap<String, Object>();
		
		// 指定下载的文件名
		String fileName = "抄表管理";
		response.setHeader("Content-Disposition",
				"attachment;filename="+ new String(fileName.getBytes(), "ISO8859-1") +".xlsx");
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		// 导出Excel对象
		XSSFWorkbook workbook = null;
		workbook = gridRecordAdminService.exportGridRecord(record);
		
		// 检测Excel对象
		if (workbook != null) {
			OutputStream output;
			try {
				output = response.getOutputStream();
				BufferedOutputStream bufferedOutPut = new BufferedOutputStream(
						output);
				bufferedOutPut.flush();
				workbook.write(bufferedOutPut);
				bufferedOutPut.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("数据为空！");
		}
	}

	/**
	 * @author qwc 2017年9月23日下午10:51:01
	 * @param request
	 * @return
	 * @throws Exception
	 *             String 导入抄表记录
	 */
	@RequestMapping("import")
	public void impotrGridRecord(
			HttpServletRequest request, @RequestParam(value = "gridId", required = true) Integer gridId)
			throws Exception {
		/*Grid grid = new Grid();
		grid.setpManagerId(managerId);
		grid.setGridId(gridId);
		grid = gridAdminService.selectGrid(grid);*/
		//System.out.println(JSONObject.fromObject(grid).toString());
		ChargeItem chargeItem=chargeItemMapper.selectChargeItemsById(gridId);
		// 获取上传的文件
		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		MultipartFile file = multipart.getFile("upfile");
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String month = request.getParameter("month");
		InputStream in = file.getInputStream();
		// 数据导入
		gridRecordAdminService.importGridRecordExcel(in, chargeItem, file,
				dateFormat.format(new Date()));
		in.close();
	}

}
