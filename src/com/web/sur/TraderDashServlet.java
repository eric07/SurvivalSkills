package com.web.sur;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TraderDashServlet
 */
@WebServlet("/TraderDashServlet")
public class TraderDashServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
	
	/**
	 * this is a temporary userid for debugging. it will
	 * eventually be accessed as a session variable.
	 */
	private final String USERID = "t002";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraderDashServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * given a block, a list of corresponding orders is returned
	 */
	//public ArrayList<Order> getOrdersByBlock(String blockid){
    public ArrayList<Order> getOrdersByBlock(Block block){
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			//String sql = "SELECT * FROM orders WHERE trader_id = ?";
			String sql = "SELECT * FROM orders WHERE block_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, block.getBlock_id());
			//ps.setString(1, "blocks");
			ResultSet rs = ps.executeQuery();
			ResultSet rs2 = null;
			while(rs.next()){
				//price - get from 'securities' table
				/*
				 	1 orderid
					2 symbol
					3 qty
					4 paramprice
					5 side
					6 openqty
					7 alocqty
					8 status
					9 timestamp (convert to date/time)
					10 type
					11 pmid
					12 traderid
					13 portid
					14 blockid
					15 comment
					16 securitytype
				*/
				sql = "SELECT market_price FROM securities WHERE symbol = ?";
				ps = connection.prepareStatement(sql);
				ps.setString(1,  rs.getString(2));
				rs2 = ps.executeQuery();
				rs2.next();
				String[] datetime = rs.getString(9).split(" ");
				String date = datetime[0];
				String time = datetime[1];
				orders.add(new Order(rs.getString(1), rs.getString(14),
						rs.getString(2), rs.getString(12), rs.getString(5),
						rs2.getString(1), rs.getString(3), date,
						time, rs.getString(10), rs.getString(4), rs.getString(8)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;		
	}
    
    /**
	 * given an id for the current trader (session), a list of corresponding orders is returned
	 */
	public ArrayList<Order> getOrdersByTrader(String userid){
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			String sql = "SELECT * FROM orders WHERE trader_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, userid);
			//ps.setString(1, "blocks");
			ResultSet rs = ps.executeQuery();
			ResultSet rs2 = null;
			while(rs.next()){
				//price - get from 'securities' table
				/*
				 	1 orderid
					2 symbol
					3 qty
					4 paramprice
					5 side
					6 openqty
					7 alocqty
					8 status
					9 timestamp (convert to date/time)
					10 type
					11 pmid
					12 traderid
					13 portid
					14 blockid
					15 comment
					16 securitytype
				*/
				sql = "SELECT market_price FROM securities WHERE symbol = ?";
				ps = connection.prepareStatement(sql);
				ps.setString(1,  rs.getString(2));
				rs2 = ps.executeQuery();
				rs2.next();
				String[] datetime = rs.getString(9).split(" ");
				String date = datetime[0];
				String time = datetime[1];
				orders.add(new Order(rs.getString(1), rs.getString(14),
						rs.getString(2), rs.getString(12), rs.getString(5),
						rs2.getString(1), rs.getString(3), date,
						time, rs.getString(10), rs.getString(4), rs.getString(8)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;		
	}
	
	/**
	 * given an id for the current trader (session), a list of corresponding blocks is returned
	 */
	public ArrayList<Block> getBlocksByTrader(String userid){
		ArrayList<Block> blocks = new ArrayList<Block>();
		try {
			String sql = "SELECT DISTINCT blocks.* FROM blocks, orders WHERE trader_id = ? AND blocks.block_id = orders.block_id";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, userid);
			//ps.setString(1, "blocks");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				/*
				 	1 blockid
					2 status
					3 blockmethod
					4 timestamp
					5 totalqty
					6 openqty
					7 allocqty
					8 symbol
				*/
				String[] datetime = rs.getString(4).split(" ");
				String date = datetime[0];
				String time = datetime[1];
				
				blocks.add(new Block(rs.getString(1), rs.getString(2),
						rs.getString(3), date, time, 
						rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getString(8)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blocks;		
	}
	
	/**
	 * given a blockid, a map to corresponding lists of orders is returned
	 */
	/*public HashMap<Block, ArrayList<Order>> getBlocksOfOrders(String userid){
		HashMap<Block, ArrayList<Order>> blocksOfOrders = new HashMap<Block, ArrayList<Order>>(); 
		ArrayList<Block> blocks = getBlocksByTrader(userid);
		for(Block b: blocks){
			blocksOfOrders.put(b, getOrdersByBlock(b));
		}
		return blocksOfOrders;		
	}*/

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("in init...");
		ServletContext sc = config.getServletContext();
		String driverName = sc.getInitParameter("driverClass");
		String url = sc.getInitParameter("url");
		String user = sc.getInitParameter("username");
		String password = sc.getInitParameter("password");

		try {
			connection = DBconnection.getConnection(driverName, url, user,
					password);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in doGet...");
		/*for(Order o: getOrders(USERID)){
			System.out.println(o);
		}*/
		for(Block b: getBlocksByTrader(USERID)){
			System.out.println(b);
			ArrayList<Order> orders = getOrdersByBlock(b);
			for(Order o: orders)
				System.out.println(o);
			System.out.println("----------");
		}
		//for(Block b: getBlocksOfOrders(USERID).keySet()){
			//System.out.println(b + ":");
			//for(Order o: getBlocksOfOrders(USERID).get(b))
			//	System.out.println(o);
		//}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in doPost...");
	}

}
