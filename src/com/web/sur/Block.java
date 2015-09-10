package com.web.sur;

import java.util.ArrayList;

public class Block  {
	private String BlockID;
	private String Symbol;
	private String Side;
	private String TradeType;
	private String TradeParam;
	private String Status;
	private int totalQty;
	private int price;
	private ArrayList<Order> orderlist;

	public Block(String BlockID, String symbol, String side, String tradeType, String typeParam) {
		this.BlockID=BlockID;
		this.Symbol = symbol;
		this.Side = side;
		this.TradeType = tradeType;
		this.TradeParam = typeParam;
		this.totalQty=0;
		this.price=0;
		this.orderlist = new ArrayList<Order>();
		
	}
	public int gettotalQty() {
		return this.totalQty;
	}
	public void settotalQty(String BlockID) {
		this.totalQty = totalQty;
	}
	public String getBlockID() {
		return this.BlockID;
	}
	public void setBlockID(String BlockID) {
		this.BlockID = BlockID;
	}
	public String getStatus() {
		return this.Status;
	}
	public void setStatus(String Status) {
		this.Status = Status;
	}

	public String getTradeParam() {
		return this.TradeParam;
	}
	public void setTradeParam(String typeParam) {
		this.TradeParam = typeParam;
	}
	public String getSymbol() {
		return this.Symbol;
	}
	public void setSymbol(String symbol) {
		this.Symbol = symbol;
	}
	public String getSide() {
		return this.Side;
	}
	public void setSide(String side) {
		this.Side = side;
	}
	public String getTradetype() {
		return this.TradeType;
	}
	public void setTradetype(String tradetype) {
		this.TradeType = tradetype;
	}
	public ArrayList<Order> getOrderList(){
		return this.orderlist;
	}
	
	
	public boolean OrderBelongsToBlock(Order order) {
		return (blockCheck(order.getSymbol(), order.getSide(), order.getTradetype(), order.getTradeParam()));
	}

	public void addOrder(Order order) {
		if (OrderBelongsToBlock(order)) {
			this.orderlist.add(order);
			this.addTotalQty(Integer.parseInt(order.getQty()));
			this.setAvgPrice();
		}
		else
			System.out.println("Error: Order Does not belong to this block");
	}
	public void addTotalQty(int amount){
		this.totalQty+=amount;
	}
	public void minusTotalQty(int amount){
		this.totalQty-=amount;
	}
	public void setAvgPrice(){
		int sum=0;
		for(Order order : orderlist){
			sum+=(Integer.parseInt((order.getPrice()))*Integer.parseInt((order.getQty())));
		}
		this.price=sum/this.totalQty;
	}

	@Override
	public String toString() {
		return "Block: "+ BlockID+" [symbol=" + Symbol + ", side=" + Side + ", tradeType=" + TradeType + ", typeParam=" + TradeParam
				+ ", totalQty=" + totalQty + ", price=" + price+"]";
	}
	public boolean blockCheck(String checkSymbol, String checkSide,String checkType,String checkParam){
			if(((this.getSymbol() == checkSymbol) && (this.getSide() == checkSide)
				&& (this.getTradetype() == checkType) && (this.getTradeParam() == checkParam))){
				return true;
			}
		return false;
	}
	
	
}
