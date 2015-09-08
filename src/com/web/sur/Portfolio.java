package com.web.sur;

import java.util.ArrayList;

public class Portfolio {
	String portName;
	ArrayList<Order> orderList;
	ArrayList<Stock> stockList;

	public Portfolio(String portName) {
		this.portName = portName;
		this.orderList = new ArrayList<Order>();
		this.stockList = new ArrayList<Stock>();
	}

	public void addStock(Stock stock) {
		this.stockList.add(stock);
	}

	public void addOrder(Order order) {
		this.orderList.add(order);
	}

	public ArrayList<Stock> getStockList(){
		return this.stockList;
	}

	public ArrayList<Order> getOrderList() {
		return this.orderList;
	}

	public String getName() {
		return this.portName;
	}

}