package com.web.sur;

public class Stock {
	private String Symbol, Qty, Price, MarketValue;
	/**
	 Constructor for Stock
	 */
	public Stock(String Symbol,String  Qty,String Price,String MarketValue){
		this.Symbol=Symbol;
		this.Qty=Qty;
		this.Price=Price;
		this.MarketValue=MarketValue;
	
		
	}
	public String getSymbol() {
		return this.Symbol;
	}
	public void setSymbol(String symbol) {
		this.Symbol = symbol;
	}
	public String getQty() {
		return this.Qty;
	}
	public void setQty(String qty) {
		this.Qty = qty;
	}
	public String[] getAttributes(){
		 String[] stringArr ={Symbol, Qty,Price,MarketValue};
		 return stringArr;
		
	}
}