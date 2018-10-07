package com.zeng.ezsh.opendoor.callback.action;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeng.ezsh.wy.entity.GridRecord;
import com.zeng.ezsh.wy.entity.Room;
import com.zeng.ezsh.wy.entity.VisitorCode;
import com.zeng.ezsh.wy.entity.VisitorRecord;
import com.zeng.ezsh.wy.open.door.utils.CallBackPojo;
import com.zeng.ezsh.wy.service.VisitorRecordService;
import com.zeng.ezsh.wy.service.VisitorService;

@Controller
@RequestMapping("openDoor")
public class CallBackAction {
	@Resource
	VisitorService visitorService;
	@Resource
	VisitorRecordService visitorRecordService;

	@RequestMapping("callBack")
	public void callBackListener(HttpServletRequest request)
			throws NoSuchMethodException, SecurityException {
		System.out.println("有人操作门锁，我被回调了");
		Map propertiesMap = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = propertiesMap.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";

		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (valueObj == null) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value += values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put("name", value);
			System.out.println("name》》" + value);
		}

		String reString = value.substring(value.indexOf("{"));
		System.out.println("门锁回调参数》》" + reString);

		// 回调json
		JSONObject jsonObject = JSONObject.fromObject(reString);
		CallBackPojo callBackPojo = (CallBackPojo) JSONObject.toBean(
				jsonObject, CallBackPojo.class);
		if (callBackPojo.getRESULT().equals("1")
				&& callBackPojo.getPARAMS().size() == 6) { // 有人开门
			System.out.println("回调访问码>>" + callBackPojo.getPARAMS().get(3));
			String visitCode = (String) callBackPojo.getPARAMS().get(3)
					.subSequence(0, 6);
			// visitorService.
			VisitorRecord record = new VisitorRecord();
			VisitorCode visitorCode = new VisitorCode();
			visitorCode.setVisitorPassword(visitCode);
			record.setVisitorCode(visitorCode);
			int status = visitorRecordService.insertAuto(record);
			if (status == 1) {
				System.out.println("自动添加访客开门记录成功！");
			}
		}
	}
}
