package com.web.sur;

import java.util.ArrayList;

public class Order {
	private String OrderID, BlockID, Symbol, TraderID, Side, Price,
	Qty,Date, Time, Tradetype,TradeParam,Status;

	/**
	 Constructor for Order
	 */
	public Order(String OrderID,String BlockID,String Symbol,String TraderID, 
		String Side,String Price,String Qty,String Date,String Time,String Tradetype,
		String TradeParam,String Status){
		this.OrderID=OrderID;
		this.BlockID=BlockID;
		this.Symbol=Symbol;
		this.TraderID=TraderID;
		this.Side=Side;
		this.Price=Price;
		this.Qty=Qty;
		this.Date=Date;
		this.Time=Time;
		this.Tradetype=Tradetype;
		this.TradeParam=TradeParam;
		this.Status=Status;
	}
	public String getOrderID(){
		return this.OrderID;
	}
	public void setOrderID(String orderID) {
		OrderID = orderID;
	}
	public String getBlockID() {
		return this.BlockID;
	}
	public void setBlockID(String blockID) {
		this.BlockID = blockID;
	}
	public String getSymbol() {
		return this.Symbol;
	}
	public void setSymbol(String symbol) {
		this.Symbol = symbol;
	}
	public String getTraderID() {
		return this.TraderID;
	}
	public void setTraderID(String traderID) {
		this.TraderID = traderID;
	}
	public String getSide() {
		return this.Side;
	}
	public void setSide(String side) {
		this.Side = side;
	}
	public String getPrice() {
		return this.Price;
	}
	public void setPrice(String price) {
		this.Price = price;
	}
	public String getQty() {
		return this.Qty;
	}
	public void setQty(String qty) {
		this.Qty = qty;
	}
	public String getDate() {
		return this.Date;
	}
	public void setDate(String date) {
		this.Date = date;
	}
	public String getTime() {
		return this.Time;
	}
	public void setTime(String time) {
		this.Time = time;
	}
	public String getTradetype() {
		return this.Tradetype;
	}
	public void setTradetype(String tradetype) {
		this.Tradetype = tradetype;
	}
	public String getTradeParam() {
		return this.TradeParam;
	}
	public void setTradeParam(String tradeParam) {
		this.TradeParam = tradeParam;
	}
	public String getStatus() {
		return this.Status;
	}
	public void setStatus(String status) {
		this.Status = status;
	}
	public String[] getAttributes(){
		 String[] stringArr ={OrderID, BlockID, Symbol, TraderID, Side, Price,
			Qty,Date, Time, Tradetype,TradeParam,Status};
		 return stringArr;
		
	}
	
	

}
