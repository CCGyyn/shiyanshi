package com.zeng.ezsh.alipay.config;

public class AlipayConfig {
	/* 加签字符集 */
	public static final String AliPaySignCharset = "utf-8";

	/* 卖家支付宝用户号 */
	public static final String SellerId = "2088721870737055";
	/* 支付宝支付应用e众智慧社区APPID */
	public static final String APPID = "2017082208323037";
	
	/* 支付宝支付应用e众智慧社区支付宝网关 */
	public static final String AliPayGateWay = "https://openapi.alipay.com/gateway.do";
	
	/* 支付宝支付应用e众智慧社区私钥 */
	public static final String AliPayPrivateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCZ72PdMt37eRojQ+Q+n6a4s5KpTu70ZuNm2erWFebcSLeCMhAuODWkiyHH0Cow7cvL01Urg80bxzzXLb8c165Dqrvy5L3sNhzNNKLWrkoo6Q30zgDFIsli9Fz5UfCN9touopqHGgd42kUnqSCaHMAbJEmARFaG8mraU0akXhjY1sYoLcAJQYsfKXY3rrPASdTJRIfejcSCbv8iCxBJiObM7a2PWTlDgYki8nXPE94LL3f+BkTGdc4qpS/9oemcWe07HAuyVO/9FvQYojZdA1m5nio33X7Zi8AHDQERfpeSbTgE4UrLsqEovGMG4b++GpPnTmAZgva1aiWA9f/c/6tZAgMBAAECggEBAIxKcGkYAtc892VMaqfoOLn8bp1/Iqf7XSRPVobmTxBT9Wri1XxKHhln89F3FfyhvCp24BhYdoXqfrhdKN/QcTGaWNB1R+IPLxCV+BJPpabDP8TsOidwHLnimxleZC3927rE1rBqOIUb6ArNSLkrmqkh2RrdLT6vkFKJssf6OgrjxXQtDQljguBDY3KBcyBpEa45xG93+rX/kZ7y86hYE67XPEdvwJoirzP4Fxbd2f/C9DtXkfEk0nolRZ3d89xGUacYq3qrtPUcy+eEg5ICVgVYFRDLS7AQod5Cfi3GabM5dDBm1OT708rYG0fazFg/0vkoA2U0hEZa0Pm5S/O6lQECgYEA4yJG8ECJLmNuZmR8YFEV9WgmxbhZVbbHaacq1zz38uP7ThaYfcf16kL+i87x2er6radgf0GEWoyHWzwilXzUVVLla9qMu5g1cQStElxHrAONlPl06RrZ1YJqrXrU+47VwsGU8kU2dW6O4I9NxzU5hvIf79TQFZZ9C1FQLqp2GZUCgYEArX+ds+n6WZAFHBM6Hp8nlpZaGGFVgxpn+tgNnCnvicpzmA3J9LPVXrnt+BA6OGRiptKVzfxOxSkomUkg49FeTq1l7o48cfkjV5TQITtXc8x/baLxEcDEoSUWPONhkiqHuNaOOeOcOjyyzFNGQSUvCzRub6m/ho1Fd6NLRT5OAbUCgYEAmxTNUL8Pqz+yonDbxzu1/YBmcLulK3QIBehgxONY/FuTO8cLyV3dV1z0xeq/kSXykNzmcxz9zIGE7dNKXb0EkVlvUcvAKVoMqyUhMpcVTKqEYgJNQ9fUggZw5hAnGbd11mssUprVSyr4nPTbizeZFr189tjcVtEQgci8MZCr4F0CgYAEZbCch7iVnoXSoVYewo/hTCw6Y/Uj3n52KBWoOJPenSfy2Z6duhUVUocKe3bOSRUK/1lWoCdq7dEemXDg9UR7n/9uXWKAzPytUpM31GYyBCPSpoePmKAjKWp33pdxeex4rc9q/i7Odw1dCltEeWnI0epeoCG/ikMRdi0JMzwM5QKBgQDISSdL93M/UOwdoBjzCii/6UFZdD4TqwyRwC/IT/SO17tH69qxSfkE2yryXqy4dWp3R8P6jX+itVIPV4QZZ+wrK2k6Y/HumNpIGaXSktzX2reBtHI0yAsNgYUWUM+BVDgxwEudBMH5r7bSICpOhXY99602z1eXQvQzZwu7ToW+Dg==";
	/* 支付宝支付应用e众智慧社区支付宝公钥 */
	public static final String AlipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoie8Wyteg4mHIlzN0M9qi5sl3XHeqDRIs2OoZH2ddceZAqFh470beqc5IDb1Gmph0I28IgUkN3P4A7sEzIv6Z0aCxrnNL1t87uvrGVMb1V4SovvSxC176oJhC21B5BTnmkg6NbP9P4Y8P5MkF8GVXpcYdEnmTOt84ad0dWnrdvP6u75lvYJBOjU9GgPq+dUAqZgyVpKsZJyVU573P0Jx8BafKr0JdoKoxbfb9HOmyZ37UnYX+uW3tZY7kKl7+2ULKQZqLzj2ZFM4Xm0aYonrilniZz4JY4HQw/al8zxFn/ZbwpX7XP1w6Duvj+wfRaHCSo3zcH0BaidLlDsslkDQwwIDAQAB";
	/* 商品支付异步通知地址 */
	public static final String VERIFY_ORDER_ASYN_URL = "https://szykcz.com/aliPayNotice/verifyOrderAsyn";

	/* 物业费支付异步通知地址 */
	public static final String VERIFY_PROPERTY_PAY_ASYN_URL = "https://szykcz.com/aliPayNotice/verifyPropertyPayAsyn";

	/* 家教平台费支付异步通知地址 */
	public static final String VERIFY_TEACHER_PAY_ASYN_URL = "https://szykcz.com/aliPayNotice/verifyTeacherPayAsyn";

	/* 公益基金费支付异步通知地址 */
	public static final String VERIFY_BENEFIT_PAY_ASYN_URL = "https://szykcz.com/aliPayNotice/verifyBenefitPayAsyn";
	
	/* 停车费支付异步通知地址 */
	public static final String VERIFY_PARKING_PAY_ASYN_URL = "https://szykcz.com/aliPayNotice/verifyParkingPayAsyn";

	/* 车位费支付异步通知地址 */
	public static final String VERIFY_BERTH_PAY_ASYN_URL = "https://szykcz.com/aliPayNotice/verifyBerthPayAsyn";
	
	public static final String QUIT_URL = "https://szykcz.com/base/goURL/platemanage/payto";
}


