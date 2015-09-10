package com.web.sur;

public class Positions {
	String symbol;
	String totalqty;
	String closingprice;
	String closingvalue;
	
	
	public Positions(String symbol, String totalqty, String closingprice,
			String closingvalue) {
		super();
		this.symbol = symbol;
		this.totalqty = totalqty;
		this.closingprice = closingprice;
		this.closingvalue = closingvalue;
		
		
	}
	public Positions() {
		// TODO Auto-generated constructor stub
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
		return this.totalqty;
	}
	
	public void setTotalqty(String totalqty) {
		
		this.totalqty = totalqty;
	}
	
	public String getclosingprice() {
		return closingprice;
	}
	
	public void setclosingprice (String cp) {
		
		this.closingprice = cp;
	}
	
	public String getclosingvalue() {
		return closingvalue;
	}
	public void setclosingvalue (String cv) {
		
		this.closingvalue = cv;
	}
	

	public String[] getAttributes(){
				 String[] stringArr ={symbol, totalqty,closingprice, closingvalue};
		 return stringArr;
	}
}
