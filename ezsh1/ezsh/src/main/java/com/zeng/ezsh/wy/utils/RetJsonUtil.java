package com.zeng.ezsh.wy.utils;

import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.expr.NewArray;

import org.apache.poi.ss.formula.functions.T;

import net.sf.json.JSONObject;

public class RetJsonUtil{
	private String status;

	private String message;

	private long total;
	
	private Map<String, Object> retMap;

	private List<?> list;
	
	private Object object;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Map<String, Object> getRetMap() {
		return retMap;
	}

	public void setRetMap(Map<String, Object> retMap) {
		this.retMap = retMap;
	}
	
	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	JSONObject retObject=new JSONObject();
	
	public String getRetJsonM(){
		retObject.put("status", this.status);
		retObject.put("message", this.message);
		if(retMap==null){
			retMap=new HashMap<String, Object>();
		}
		retObject.put("data", retMap);
		return JSONObject.fromObject(retObject).toString();
	};
	
	public String getRetJsonL(){
		retObject.put("status", this.status);
		retObject.put("message", this.message);
		if(list==null){
			list=new ArrayList<T>();
		}
		retObject.put("data", list);
		return JSONObject.fromObject(retObject).toString();
	};
	
	public String getRetJsonO(){
		retObject.put("status", this.status);
		retObject.put("message", this.message);
		if(object==null){
			object = new Object();
		}
		retObject.put("data", object);
		return JSONObject.fromObject(retObject).toString();
	};
	
	public String getRetJsonEasyGrid(){//返回easyui grid列表展示的数据
		retObject.put("total", this.total);
		if(list==null){
			list=new ArrayList<T>();
		}
		retObject.put("rows", list);
		return JSONObject.fromObject(retObject).toString();
	}
	//改一下，改成手机app那样显示每个月的收费
	public String getRetJsonEasyGrid1(){//返回easyui grid列表展示的数据
		retObject.put("total", this.total);
		if(list==null){
			list=new ArrayList<T>();
		}
		
		retObject.put("rows", list);
		return JSONObject.fromObject(retObject).toString();
	}
}
