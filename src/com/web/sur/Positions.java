package com.web.sur;

public class Positions {
	String symbol;
	int totalqty;
float closingprice;
	float closingvalue;
	
	
	public Positions(String symbol, int totalqty, float closingprice,
			float closingvalue) {
		super();
		this.symbol = symbol;
		this.totalqty = totalqty;
		this.closingprice = closingprice;
		this.closingvalue = closingvalue;
		
		
	}
	@Override
	public String toString() {
		return "Positions [symbol=" + symbol + ", totalqty=" + totalqty
				+ ", closingprice=" + closingprice + ", closingvalue="
				+ closingvalue + "]";
	}
	
	public String getSymbol() {
		return this.symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String getTotalqty() {
		Integer tq=totalqty;
		
		return tq.toString();
	}
	
	public String getclosingprice() {
		Float cp=closingprice;
		
		return cp.toString();
	}
	
	public String getclosingvalue() {
		Float cv=closingvalue;
		
		return cv.toString();
	}
	
	public void setTotalqty(int totalqty) {
		
		this.totalqty = totalqty;
	}
	public String[] getAttributes(){
		 Integer tq=totalqty;
		
		 Float cp=closingprice;
		
		 Float cv=closingvalue;
		 
		 String[] stringArr ={symbol, tq.toString(),cp.toString(),cv.toString()};
		 return stringArr;
		
	}
	
	
	
}
