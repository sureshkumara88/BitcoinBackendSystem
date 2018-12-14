package com.bitcoin.price.loader;

import static com.bitcoin.price.util.Utility.getDateObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bitcoin.price.bo.Data;

/**
 * This class is used to load the coinbase json response into business object 
 * @author suresh kumar
 *
 */
public class PriceHistoryLoader {

	private String url;
	
	private List<Data> dataList;
	
	private Date dateLoaded;
		
	public List<Data> getPriceInstance() {		
		if (dataList == null || (dateLoaded != null && dateLoaded.compareTo(getDateObject(0)) != 0)) {
			dataList = loadDataList();
			dateLoaded = getDateObject(0);			
		}
		return dataList;
	}
	
	private List<Data> loadDataList() {
		List<Data> list = new ArrayList<Data>();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

			JSONArray jsonArray=new JSONArray(getResponse()); 

			for(int i=0;i<jsonArray.length();i++){

				//get the JSON Object 
				JSONObject mainArray = jsonArray.getJSONObject(i); 
				JSONObject dataArray = (JSONObject) mainArray.get("data");	      	       
				JSONArray priceArray = (JSONArray) dataArray.get("prices");

				for(int j=0;j<priceArray.length();j++) {
					JSONObject objs = priceArray.getJSONObject(j);
					Date time = sdf.parse(objs.get("time").toString());
					list.add(new Data(objs.getString("price"), time));
				}
				Collections.sort(list);
			}
		} catch (JSONException je) {
			je.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		}  catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	private String getResponse() throws Exception {
		URL website = new URL(url);
		URLConnection connection = website.openConnection();
		BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream(),"UTF8"));

		StringBuilder response = new StringBuilder();
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			response.append('['+inputLine+']');
		}   

		in.close();		
		
		return response.toString();
	}

	@Resource
	public void setUrl(String url) {
		this.url = url;
	}
	
}
