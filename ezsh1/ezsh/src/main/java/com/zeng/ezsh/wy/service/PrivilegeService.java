package com.zeng.ezsh.wy.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public interface PrivilegeService {
    /*获取权限表中的所有权限*/
    public JSONArray selectAllPrivList(Map<Object,Object> paramPrivMap);
}
