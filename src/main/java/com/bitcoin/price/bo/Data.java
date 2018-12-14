package com.bitcoin.price.bo;

import java.util.Date;

/**
 * This class is stakeholder to store all price list 
 * @author suresh kumar
 *
 */
public class Data implements Comparable<Data> {

	private String price;
	
	private Date time;
	
	public Data(String price, Date time) {
		this.price = price;
		this.time = time;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public int compareTo(Data obj) {
		return getTime().compareTo(obj.getTime());
	}
		
}
