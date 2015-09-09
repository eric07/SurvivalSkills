package com.web.sur;

public class Block {
	
	private String block_id, status, block_method, date, time, symbol;
	private int total_qty, open_qty, alloc_qty;
	
	public Block(String block_id, String status, String block_method,
			String date, String time, int total_qty,
			int open_qty, int alloc_qty, String symbol) {
		this.block_id = block_id;
		this.status = status;
		this.block_method = block_method;
		this.date = date;
		this.time = time;
		this.total_qty = total_qty;
		this.open_qty = open_qty;
		this.alloc_qty = alloc_qty;
		this.symbol = symbol;
	}
	public String getBlock_id() {
		return block_id;
	}
	public void setBlock_id(String block_id) {
		this.block_id = block_id;
	}
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBlock_method() {
		return block_method;
	}
	public void setBlock_method(String block_method) {
		this.block_method = block_method;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public int getTotal_qty() {
		return total_qty;
	}
	public void setTotal_qty(int total_qty) {
		this.total_qty = total_qty;
	}
	public int getOpen_qty() {
		return open_qty;
	}
	public void setOpen_qty(int open_qty) {
		this.open_qty = open_qty;
	}
	public int getAlloc_qty() {
		return alloc_qty;
	}
	public void setAlloc_qty(int alloc_qty) {
		this.alloc_qty = alloc_qty;
	}
	
	@Override
	public String toString() {
		return "Block [block_id=" + block_id + ", status=" + status
				+ ", block_method=" + block_method + ", date=" + date
				+ ", time=" + time + ", symbol=" + symbol + ", total_qty="
				+ total_qty + ", open_qty=" + open_qty + ", alloc_qty="
				+ alloc_qty + "]";
	}

}
