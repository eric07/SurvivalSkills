package com.web.sur;


import java.util.ArrayList;

public class Portfolio {
	String portName;
	ArrayList<Order> orderList;
	
	public Portfolio(String portName ){
		this.portName = portName;
		this.orderList = new ArrayList<Order>();
	}
	
	
	
	public void addOrder(Order order){
		this.orderList.add(order);
	}
	
	public ArrayList<Order> getOrderList(){
		return this.orderList;
	}
	public String getName(){
		return this.portName;
	}
	
	

}
