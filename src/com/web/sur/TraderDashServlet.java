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
    public ArrayList<Order> getOrdersByBlock(Block block){
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			String sql = "SELECT * FROM orders WHERE block_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, block.getBlock_id());
			ResultSet rs = ps.executeQuery();
			ResultSet rs2 = null;
			while(rs.next()){
				//grab price from 'securities' table
				sql = "SELECT market_price FROM securities WHERE symbol = ?";
				ps = connection.prepareStatement(sql);
				ps.setString(1,  rs.getString(2));
				rs2 = ps.executeQuery();
				rs2.next();
				/*String[] datetime = rs.getString("timestamp").split(" ");
				String date = datetime[0];
				String time = datetime[1];*/
				orders.add(new Order(rs.getString("order_id"), rs.getString("block_id"),
						rs.getString("symbol"), rs.getString("trader_id"), rs.getString("side"),
						rs2.getFloat("market_price"), rs.getInt("qty"),
						rs.getString("timestamp"), rs.getString("type"), rs.getFloat("parameter_price"),
						rs.getString("status"), rs.getString("port_id")));
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
			ResultSet rs = ps.executeQuery();
			ResultSet rs2 = null;
			while(rs.next()){
				//
				sql = "SELECT market_price FROM securities WHERE symbol = ?";
				ps = connection.prepareStatement(sql);
				ps.setString(1,  rs.getString(2));
				rs2 = ps.executeQuery();
				rs2.next();
				/*String[] datetime = rs.getString("timestamp").split(" ");
				String date = datetime[0];
				String time = datetime[1];*/
				orders.add(new Order(rs.getString("order_id"), rs.getString("block_id"),
						rs.getString("symbol"), rs.getString("trader_id"), rs.getString("side"),
						rs2.getFloat("market_price"), rs.getInt("qty"),
						rs.getString("timestamp"), rs.getString("type"), rs.getFloat("parameter_price"),
						rs.getString("status"), rs.getString("port_id")));
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
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				/*String[] datetime = rs.getString("timestamp").split(" ");
				String date = datetime[0];
				String time = datetime[1];*/
				
				blocks.add(new Block(rs.getString("block_id"), rs.getString("status"),
						rs.getString("block_method"), rs.getString("timestamp"), 
						rs.getInt("total_qty"), rs.getInt("open_qty"),
						rs.getInt("alloc_qty"), rs.getString("symbol")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blocks;		
	}

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
		/*for(Order o: getOrdersByTrader(USERID)){
			System.out.println(o);
		}*/
		for(Block b: getBlocksByTrader(USERID)){
			System.out.println(b);
			ArrayList<Order> orders = getOrdersByBlock(b);
			for(Order o: orders)
				System.out.println(o);
			System.out.println("----------");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in doPost...");
	}

}
