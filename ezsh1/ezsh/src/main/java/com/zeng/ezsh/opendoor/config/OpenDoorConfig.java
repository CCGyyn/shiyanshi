package com.zeng.ezsh.opendoor.config;

public class OpenDoorConfig {
	/* 检测失败是否在线url */
	public static String CheckDeviceOnLineStatusUrl = "https://szykcz.com/dhpkgcomm_v1.2.6/app/checkDeviceOnLineStatus";

	/* 添加门禁钥匙  */
	public static String ServiceAddKey = "https://szykcz.com/dhpkgcomm_v1.2.6/app/serviceAddKey.do";
	
	/* 通过网络远程开门 */
	public static String OpenDoorRemoteUrl = "https://szykcz.com/dhpkgcomm_v1.2.6/app/openRemoteDeviceLock.do";
}
