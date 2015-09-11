package com.web.sur;

import java.util.ArrayList;

public class Order1 {
	private String OrderID, BlockID, Symbol, TraderID, Side, timestamp,
			Tradetype, Status, PortID, pmID, comments, securityType;
	int Qty;
	float Price;
	float TradeParam;

	/**
	 * Constructor for Order
	 */
	public Order1(String OrderID, String BlockID, String Symbol,
			String TraderID, String Side, float Price, int Qty, String tp,
			String Tradetype, float TradeParam, String Status, String PortID,
			String pmID, String comments, String securityType) {
		this.OrderID = OrderID;
		this.BlockID = BlockID;
		this.Symbol = Symbol;
		this.TraderID = TraderID;
		this.Side = Side;
		this.Price = Price;
		this.Qty = Qty;
		this.timestamp = tp;
		this.Tradetype = Tradetype;
		this.TradeParam = TradeParam;
		this.Status = Status;
		this.PortID = PortID;
		this.pmID = pmID;
		this.comments = comments;
		this.securityType = securityType;
	}

	@Override
	public String toString() {
		return "Order [OrderID=" + OrderID + ", BlockID=" + BlockID
				+ ", Symbol=" + Symbol + ", TraderID=" + TraderID + ", Side="
				+ Side + ", timestamp=" + timestamp + ", Tradetype="
				+ Tradetype + ", Status=" + Status + ", Qty=" + Qty
				+ ", Price=" + Price + ", TradeParam=" + TradeParam + "]";
	}

	public String getPmID() {
		return pmID;
	}

	public void setPmID(String pmID) {
		this.pmID = pmID;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getSecurityType() {
		return securityType;
	}

	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}

	public String getOrderID() {
		return OrderID;
	}

	public void setOrderID(String orderID) {
		OrderID = orderID;
	}

	public String getBlockID() {
		return BlockID;
	}

	public void setBlockID(String blockID) {
		BlockID = blockID;
	}

	public String getSymbol() {
		return Symbol;
	}

	public void setSymbol(String symbol) {
		Symbol = symbol;
	}

	public String getTraderID() {
		return TraderID;
	}

	public void setTraderID(String traderID) {
		TraderID = traderID;
	}

	public String getSide() {
		return Side;
	}

	public void setSide(String side) {
		Side = side;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTradetype() {
		return Tradetype;
	}

	public void setTradetype(String tradetype) {
		Tradetype = tradetype;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getPortID() {
		return PortID;
	}

	public void setPortID(String portID) {
		PortID = portID;
	}

	public int getQty() {
		return Qty;
	}

	public void setQty(int qty) {
		Qty = qty;
	}

	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
	}

	public float getTradeParam() {
		return TradeParam;
	}

	public void setTradeParam(float tradeParam) {
		TradeParam = tradeParam;
	}

	public String[] getAttributes() {
		Float p = Price;
		Integer q = Qty;
		Float tp = TradeParam;

		String[] stringArr = { OrderID, BlockID, Symbol, TraderID, Side,
				p.toString(), q.toString(), timestamp, Tradetype,
				tp.toString(), Status };
		return stringArr;

	}

	public String[] getAttributesforblockbiew() {
		Float p = Price;
		Integer q = Qty;
		Float tp = TradeParam;

		float val = Price * Qty;
		Float v = val;
		String[] stringArr = { OrderID, Symbol, Side, p.toString(),
				q.toString(), v.toString(), Tradetype, tp.toString() };
		return stringArr;

	}

	public String[] getBlockAttributes() {
		String[] stringArr = { OrderID, Status, Symbol, Side, Tradetype,
				"" + TradeParam, "" + Qty };
		return stringArr;

	}

}