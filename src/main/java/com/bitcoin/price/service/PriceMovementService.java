package com.bitcoin.price.service;

import java.util.Date;
import java.util.List;

import com.bitcoin.price.bo.Data;

public interface PriceMovementService {

	public List<Data> getDataForLastWeek() throws Exception;
	
	public List<Data> getDataForLastMonth() throws Exception;
	
	public List<Data> getDataForLastYear() throws Exception;
	
	public List<Data> getDataForDates(Date fromDate, Date toDate) throws Exception;
}
