package com.zeng.ezsh.shiro.action;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;




import com.zeng.ezsh.wy.entity.Admin;
import com.zeng.ezsh.wy.service.AdminService;
import com.zeng.ezsh.wy.utils.StringMd5Util;

@Component
public class ShiroAuthorizingRealmAction extends AuthorizingRealm{

	@Resource
	AdminService adminService;
	public static final String SESSION_USER_KEY = "admin"; 
	
	/*public ShiroAuthorizingRealmAction(CacheManager cacheManager, CredentialsMatcher matcher) {
        super(cacheManager, matcher);
    }*/
	
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		Admin adminInfo = (Admin) principals.getPrimaryPrincipal(); // 获取用户名
		Map<String, Set<String>> resourceMap=adminService.selectResourceMapByRoleId(adminInfo.getAdRoleId());
		Set<String> privilegeSet=null;
		if(resourceMap!=null){
			 privilegeSet = resourceMap.get("privileges");
		}
		Set<String> roleSet=new HashSet<String>();
		roleSet.add(String.valueOf(adminInfo.getAdRoleId()));
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleSet);// 角色
        authorizationInfo.setStringPermissions(privilegeSet);// 权限
        return authorizationInfo;
	}
	
	/**
	 * @author qwc
	 * 2017年11月29日下午9:58:59
	 * @param authcToken
	 * @return 认证
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		// TODO Auto-generated method stub
		Admin user = tokenToUser((UsernamePasswordToken) authcToken);
    	user=adminService.selectByAccount(user.getAdAccount());
        // 设置session  
        //Session session = SecurityUtils.getSubject().getSession();  
        //session.setAttribute(ShiroAuthorizingRealmAction.SESSION_USER_KEY, ui);
        //当前 Realm 的 name  
        String realmName = this.getName();
        System.out.println("realmName>>"+realmName);
        return new SimpleAuthenticationInfo(user, user.getAdPassword(),realmName);
        
	}
	
	private Admin tokenToUser(UsernamePasswordToken authcToken) {  
		Admin user = new Admin();  
        user.setAdAccount(authcToken.getUsername());
        user.setAdPassword(String.valueOf(authcToken.getPassword()));
        return user;  
    }
	
}
