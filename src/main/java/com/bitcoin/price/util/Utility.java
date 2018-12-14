package com.bitcoin.price.util;

import java.util.Calendar;
import java.util.Date;

/**
 * This class is for creating common methods that are used by different service classes
 * @author suresh kumar
 *
 */
public class Utility {

	public static Date getDateObject(Integer day) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
		cal.add(Calendar.DATE, day);
		Date date = cal.getTime();
		return date;
	}
	
}
