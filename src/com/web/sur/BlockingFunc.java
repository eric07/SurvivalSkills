package com.web.sur;

import java.util.ArrayList;

public class BlockingFunc {

	public BlockingFunc() {
		// TODO Auto-generated constructor stub
	}

	public void sortOrdersToBlocks(ArrayList<Order> orders, ArrayList<Block> blocks) {
			int counter=0;
			String BlockID;
		for (Order order : orders) {

			String symbol = order.getSymbol();
			String side = order.getSide();
			String tradeType = order.getTradetype();
			String typeParam = order.getTradeParam();
			boolean blockexists= false;
			for (Block block : blocks){
				if(block.OrderBelongsToBlock(order)){
					blockexists=true;
					block.addOrder(order);
					break;
				}
			}
			if(!blockexists){
				System.out.println("In else");
				counter+=1;
				BlockID="B00"+counter;
				Block newblock = new Block(BlockID,order.getSymbol(),order.getSide(),order.getTradetype(),order.getTradeParam());
				newblock.addOrder(order);
				System.out.println(newblock);
				blocks.add(newblock);
				
			}
			
			
		}

	}

	public boolean containsBlockOfOrder(ArrayList<Block> list,Order block){
		for(Block Block : list){
			if(((Block.getSymbol() == block.getSymbol()) && (Block.getSide() == block.getSymbol())
				&& (Block.getTradetype() == block.getTradetype()) && (Block.getTradeParam() == block.getTradeParam()))){
				return true;
			}
			
		}
		return false;
	}

	public static void main(String[] args) {

		BlockingFunc func = new BlockingFunc();
		ArrayList<Block> blocklist = new ArrayList<Block>();
		Order order1 = new Order("O122", "B121", "APPLE", "T101", "SELL", "100", "60", "test1", "test1", "MARKET", "",
				"LOGGING");
		Order order2 = new Order("O122", "B121", "APPLE", "T101", "SELL", "100", "40", "test1", "test1", "MARKET", "",
				"LOGGING");
		Order order3 = new Order("O122", "B121", "SAPIENT", "T101", "BUY", "55", "100", "test1", "test1", "MARKET", "",
				"LOGGING");
		Order order4 = new Order("O122", "B121", "SAPIENT", "T101", "SELL", "50", "100", "test1", "test1", "LIMIT", "50",
				"LOGGING");
		Order order5 = new Order("O122", "B121", "SAPIENT", "T101", "SELL", "50", "100", "test1", "test1", "LIMIT", "60",
				"LOGGING");
		Order order6 = new Order("O122", "B121", "APPLE", "T101", "BUY", "60", "200", "test1", "test1", "MARKET", "",
				"LOGGING");
		Order order7 = new Order("O122", "B121", "APPLE", "T101", "BUY", "60", "100", "test1", "test1", "MARKET", "",
				"LOGGING");
		ArrayList<Order> orderlist = new ArrayList<Order>();
		orderlist.add(order1);
		orderlist.add(order2);
		orderlist.add(order3);
		orderlist.add(order4);
		orderlist.add(order5);
		orderlist.add(order6);
		orderlist.add(order7);

		func.sortOrdersToBlocks(orderlist, blocklist);

		System.out.println(blocklist);
		
	}
}
