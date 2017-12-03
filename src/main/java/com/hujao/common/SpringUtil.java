package com.hujao.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

//import com.hujao.dto.UserInfoSimple_Response_inner;
//import com.hujao.netty.NettyClient;

@Component
public class SpringUtil implements ApplicationContextAware{
	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if(SpringUtil.applicationContext == null){
			SpringUtil.applicationContext  = applicationContext;
		}
		System.out.println("---------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------");
		System.out.println("---------------SpringUtil------------------------------------------------------");
		System.out.println("========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象,applicationContext="+SpringUtil.applicationContext+"========");
		System.out.println("---------------------------------------------------------------------");
		
		try {
			//NettyClient.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 启动时,初始化systemtoken
//		System.out.println("启动时,初始化systemtoken");
//		String str = JedisManager.getInstance().Get("systemid");
//		if (str == null) {
//			JedisManager.getInstance().Set("systemid", "system|systemtoken");
//		}
//		String str2 = JedisManager.getInstance().Get("systemtoken");
//		if (str2 == null) {
//			UserInfoSimple_Response_inner m = new UserInfoSimple_Response_inner();
//			m.setUser_id("systemid");
//			m.setNick("system");
//			m.setHead_portrait("");
//			m.setSexuality("");
//			m.setMotto("");
//			m.setUser_name("system");
//			JedisManager.getInstance().Set("systemtoken",JSON.toJson(m));
//		}
	}

	//获取applicationContext
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	//通过name获取 Bean.
	public static Object getBean(String name){
		return getApplicationContext().getBean(name);
	}

	//通过class获取Bean.
	public static <T> T getBean(Class<T> clazz){
		return getApplicationContext().getBean(clazz);
	}

	//通过name,以及Clazz返回指定的Bean
	public static <T> T getBean(String name,Class<T> clazz){
		return getApplicationContext().getBean(name, clazz);
	}
}
