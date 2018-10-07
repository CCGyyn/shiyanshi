package com.zeng.ezsh.wechat.action;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.StaticApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 微信回调
 * @author quanweicong
 *
 */
@Controller
@RequestMapping("wechat")
public class WechatCallbackAction {
	private static  String TOKEN="szykcz2017hzu";
	/**
	 * @author qwc
	 * 2017年10月28日下午4:00:37
	 * @param request
	 * @param response 
	 * void 确认请求来至微信
	 * @throws IOException 
	 */
	@RequestMapping(value="callback")
	public void callback(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 微信加密签名
        String signature = request.getParameter("signature");
        System.out.println("signature>>"+signature);
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");

        String[] str = { TOKEN, timestamp, nonce };
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        // SHA1加密
       
        String digest = null;
		try {
			digest = getSHA1HexString(bigStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // 确认请求来至微信
        if (digest.equals(signature)) {
            response.getWriter().print(echostr);
        }
	}
	
	public static String getSHA1HexString(String str) throws Exception {  
        // SHA1签名生成  
        MessageDigest md = MessageDigest.getInstance("SHA-1");  
        md.update(str.getBytes());  
        byte[] digest = md.digest();  
  
        StringBuffer hexstr = new StringBuffer();  
        String shaHex = "";  
        for (int i = 0; i < digest.length; i++) {  
            shaHex = Integer.toHexString(digest[i] & 0xFF);  
            if (shaHex.length() < 2) {  
                hexstr.append(0);  
            }  
            hexstr.append(shaHex);  
        }  
        return hexstr.toString();  
    }

}
