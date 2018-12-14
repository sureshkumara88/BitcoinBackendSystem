package com.bitcoin.price.loader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This class is used to instantiate Application Context as singleton object
 * @author suresh kumar
 *
 */

public class AppContextLoader {

	private static ApplicationContext context;

	private AppContextLoader() {

	}

	static {
		try {
			context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ApplicationContext getContextInstance() {
		return context;
	}


}
