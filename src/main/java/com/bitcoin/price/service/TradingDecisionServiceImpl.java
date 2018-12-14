package com.bitcoin.price.service;

import static com.bitcoin.price.loader.AppContextLoader.getContextInstance;
import static com.bitcoin.price.util.Utility.getDateObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.bitcoin.price.bo.Data;

/**
 * This method is used to make a trading decision using Exponential Moving Averages Formula 
 * EMA = (closing price - previous day's EMA) × smoothing constant + previous day's EMA
 * @author suresh kumar
 *
 */
public class TradingDecisionServiceImpl implements TradingDecisionService {

	@Override
	public String makeTradeDecision(Integer days) throws Exception {
		PriceMovementService priceMovementService = (PriceMovementService) getContextInstance().getBean("priceMovementService");
		List<Data> dataList = priceMovementService.getDataForDates(getDateObject(-(days-1)), getDateObject(0));
		
		return emaDecision(dataList);
	}
	
	private String emaDecision(List<Data> dataList) {
		String decision = "";
		BigDecimal totalPrice = BigDecimal.ZERO;
		BigDecimal smoothCnst = BigDecimal.ZERO;
		BigDecimal previousDayPrice = BigDecimal.ZERO;
		BigDecimal closingDayPrice = BigDecimal.ZERO;
		
		for (Data data : dataList) {
			 previousDayPrice = closingDayPrice;
			 closingDayPrice = new BigDecimal(data.getPrice());
			 totalPrice = totalPrice.add(closingDayPrice);
		}
				
		smoothCnst = new BigDecimal(2).divide(new BigDecimal(dataList.size()+1), 6, RoundingMode.HALF_UP);
		
		BigDecimal ema = closingDayPrice.subtract(previousDayPrice).multiply(smoothCnst).add(previousDayPrice);
		
		if (ema.compareTo(closingDayPrice) > 0) {
			decision = "BUY";
		} else if (ema.compareTo(closingDayPrice) < 0) {
			decision = "SELL";
		} else {
			decision = "HOLD";
		}
		
		return decision;
	}

}
