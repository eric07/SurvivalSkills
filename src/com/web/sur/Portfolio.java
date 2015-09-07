package com.web.sur;


import java.util.ArrayList;
import java.util.HashMap;

public class Portfolio {
	String portName;
	ArrayList<HashMap<String, String>> orderList;
	
	public Portfolio(String portName ){
		this.portName = portName;
		this.orderList = new ArrayList<HashMap<String, String>>();
	}
	
	
	
	public void addOrder(HashMap order){
		this.orderList.add(order);
	}
	
	public ArrayList<HashMap<String, String>> getOrderList(){
		return this.orderList;
	}
	public String getName(){
		return this.portName;
	}
	
	

}
