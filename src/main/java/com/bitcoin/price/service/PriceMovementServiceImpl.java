package com.bitcoin.price.service;

import static com.bitcoin.price.loader.AppContextLoader.getContextInstance;
import static com.bitcoin.price.util.Utility.getDateObject;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.bitcoin.price.bo.Data;
import com.bitcoin.price.loader.PriceHistoryLoader;

/**
 * This class is used to retrieve bitcoin price movements for certain period
 * @author suresh kumar
 *
 */
public class PriceMovementServiceImpl implements PriceMovementService {

	@Override
	public List<Data> getDataForLastWeek() throws Exception{
	
		return getDataForDates(getDateObject(-6), getDateObject(0));
	}

	@Override
	public List<Data> getDataForLastMonth() throws Exception {
		
		return getDataForDates(getDateObject(-30), getDateObject(0));
	}
	
	@Override
	public List<Data> getDataForLastYear() throws Exception {
		
		return getDataForDates(getDateObject(-365), getDateObject(0));
	}
	
	@Override
	public List<Data> getDataForDates(Date fromDate, Date toDate) throws Exception {
		PriceHistoryLoader priceHistoryLoader = (PriceHistoryLoader) getContextInstance().getBean("priceHistoryLoader");
		List<Data> dataList = priceHistoryLoader.getPriceInstance();		
		int fromIndex = Collections.binarySearch(dataList, new Data(null, fromDate));
		int toIndex = Collections.binarySearch(dataList, new Data(null, toDate));
		
		if (fromIndex < 0 || toIndex < 0) {
			throw new Exception("Pricelist is not available for given range");
		} else if (fromIndex > toIndex) {
			throw new Exception("Invalide range");
		}
		
		return dataList.subList(fromIndex, ++toIndex);
	}

}
