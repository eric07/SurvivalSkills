package com.web.sur;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {

	String portName;
	ArrayList<Order> orderList = new ArrayList<Order>();
	ArrayList<Stock> stockList;
	public ArrayList<Positions> poslist ;

	public Portfolio(String portName, ArrayList<Order> orderlist) {
		this.portName = portName;
		this.orderList = orderlist;
		this.stockList = new ArrayList<Stock>();
	}

	public Portfolio(String portName) {
		this.portName = portName;
		this. poslist = new ArrayList<Positions>();
	}
	
	@Override
	public String toString() {
		return "Port [Portfolioname=" + portName + ", poslist=" + poslist
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
	
	public void addPos(Positions Position){
		this.poslist.add(Position);
	}

}