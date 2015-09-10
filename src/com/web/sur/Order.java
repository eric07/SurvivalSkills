package com.web.sur;
   	
   	import java.util.ArrayList;
   	
   	public class Order {
   		private String OrderID, BlockID, Symbol, TraderID, Side,timestamp, Tradetype,Status, PortID;
   		int Qty;
   		float Price;
   		float TradeParam;
   	
   		/**
   		 Constructor for Order
   		 */
   		public Order(String OrderID,String BlockID,String Symbol,String TraderID,
   			String Side,float Price,int Qty,String tp,String Tradetype,
   			float TradeParam,String Status, String PortID){
   			this.OrderID=OrderID;
   			this.BlockID=BlockID;
   			this.Symbol=Symbol;
   			this.TraderID=TraderID;
   			this.Side=Side;
   			this.Price=Price;
   			this.Qty=Qty;
   			this.timestamp=tp;
   			this.Tradetype=Tradetype;
   			this.TradeParam=TradeParam;
   			this.Status=Status;
   			this.PortID=PortID;
   		}
   		
		@Override
		public String toString() {
			return "Order [OrderID=" + OrderID + ", BlockID=" + BlockID
					+ ", Symbol=" + Symbol + ", TraderID=" + TraderID
					+ ", Side=" + Side + ", timestamp=" + timestamp
					+ ", Tradetype=" + Tradetype + ", Status=" + Status
					+ ", Qty=" + Qty + ", Price=" + Price + ", TradeParam="
					+ TradeParam + "]";
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

			public String[] getAttributes(){
      			Float p=Price;
      			Integer q=Qty;
      			Float tp=TradeParam;
      		
      			 String[] stringArr ={OrderID, BlockID, Symbol, TraderID, Side, p.toString(),
      				q.toString(),timestamp, Tradetype,tp.toString(),Status};
      			 return stringArr;
      			
      		}
      		
      		public String[] getAttributesforblockbiew(){
      			Float p=Price;
      			Integer q=Qty;
      			Float tp=TradeParam;
      			
      			float val=Price*Qty;
      			Float v=val;
     			 String[] stringArr ={OrderID,Symbol, Side, p.toString(),
     				q.toString(),v.toString(),Tradetype,tp.toString()};
     			 return stringArr;
     			
     		}
      		
      		
      		
      	
      	}