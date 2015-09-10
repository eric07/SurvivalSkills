package com.web.sur;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {

	String portName;
	ArrayList<Order> orderList = new ArrayList<Order>();
	ArrayList<Stock> stockList;

	public Portfolio(String portName, ArrayList<Order> orderlist) {
		this.portName = portName;
		this.orderList = orderlist;
		this.stockList = new ArrayList<Stock>();
	}

	@Override
	public String toString() {
		return "Port [Portfolioname=" + portName + ", orderlist=" + orderList
				+ "]";
	}

	// public void addOrder(Order order){
	// this.orderList.add(order);
	// }

	public ArrayList<Order> getOrderList() {
		return this.orderList;
	}

	public String getName() {
		return this.portName;
	}

	public void addStock(Stock stock) {
		this.stockList.add(stock);
	}

	public void addOrder(Order order) {
		this.orderList.add(order);
	}

	public ArrayList<Stock> getStockList() {
		return this.stockList;
	}

}