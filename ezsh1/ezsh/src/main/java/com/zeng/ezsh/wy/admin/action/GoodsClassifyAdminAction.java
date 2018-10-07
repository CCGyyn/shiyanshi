package com.zeng.ezsh.wy.admin.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeng.ezsh.wy.entity.Building;
import com.zeng.ezsh.wy.entity.GoodsClassfy;
import com.zeng.ezsh.wy.entity.Management;
import com.zeng.ezsh.wy.entity.Room;
import com.zeng.ezsh.wy.service.GoodsClassifyService;
import com.zeng.ezsh.wy.utils.EasyTreeUtil;

@Controller
@RequestMapping("gdClassifyAd")
public class GoodsClassifyAdminAction {
	@Resource
	GoodsClassifyService goodsClassifyService;
	
	@RequestMapping(value="gtTree",produces="application/json;charset=utf-8")
	@ResponseBody
	public String getTree(@RequestParam(value="id",required=false,defaultValue="0") int classfyId){
		System.out.println("id>>"+classfyId);
		List<GoodsClassfy> list=goodsClassifyService.selectByPrimaryKey(classfyId);
		List<EasyTreeUtil> treeList=new  ArrayList<EasyTreeUtil>(); 
		System.out.println(JSONArray.fromObject(list).toString());
		for(GoodsClassfy goodsClassfyInfo:list){
			EasyTreeUtil tree = new EasyTreeUtil();
			tree.setId(goodsClassfyInfo.getClassfyId());//设置ID的形式为【0-管理处ID】
			tree.setText(goodsClassfyInfo.getClassfyName());
			tree.setChecked(false);
			tree.setState("closed");
			treeList.add(tree);
		}
		return JSONArray.fromObject(treeList).toString();
	}
}
