package com.web.sur;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Servlet implementation class PMOrderViewServlet

@WebServlet("/PMOrderViewServlet")
public class PMOrderViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	
	private final String USERID = "t002";
	
	/**
	 * given an order, the set of orders with the same traderid is
	 * traversed. if one with the same fingerprint is found, the
	 * order is assigned the corresponding blockid. if not, a
	 * unique blockid is generated and assigned.
	 * @param order
	 */
	public void assignBlock(Order order){
		try {
			String sql = "SELECT * FROM orders WHERE trader_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, order.getTraderID());
			ResultSet rs = ps.executeQuery();
			HashSet<String> blockIds = new HashSet<String>();
			while(rs.next()){
				blockIds.add(rs.getString(14));
				if(rs.getString(2).equals(order.getSymbol()) &&
					rs.getString(12).equals(order.getTraderID()) &&
					rs.getString(5).equals(order.getSide()) &&
					rs.getString(10).equals(order.getTradetype()) &&
					rs.getString(4).equals(order.getTradeParam()) &&
					rs.getString(8).equals(order.getStatus())){
					
					order.setBlockID(rs.getString(14));
					return;
				}
			}
			
			//unique blockid generation: blockid will be one higher
			//than highest existing blockid
			int uniqueId = 0;
			for(String id: blockIds){
				int cur = Integer.parseInt(id.substring(1));
				if(cur > uniqueId)
					uniqueId = cur + 1;
			}
			order.setBlockID("b" + uniqueId);
			//TODO insert proper block entry to DB
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * inserts an order into DB
	 * @param order
	 */
	/*public void insertIntoDB(Order order){
		try {
			String sql = "INSERT INTO orders VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, order.getOrderID());
			ps.setString(2, order.getSymbol());
			ps.setInt(3, Integer.parseInt(order.getQty()));
			ps.setFloat(4, Float.parseFloat(order.getTradeParam()));
			ps.setString(5, order.getSide());
			ps.setNull(6, Types.NULL);
			ps.setNull(7, Types.NULL);
			ps.setString(8, order.getStatus());
			ps.setString(9, order.getDate() + " " + order.getTime());
			ps.setString(10, order.getTradetype());
			ps.setString(11, "p001");
			ps.setString(12, order.getTraderID());
			ps.setString(13, "port001");
			ps.setString(14, order.getBlockID());
			ps.setString(15, "comment");
			ps.setString(16, "Equity");
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	
	public void submitOrder(Order order){
		assignBlock(order);
		//insertIntoDB(order);
		//TODO add order to DB
	}

	public void init(ServletConfig config) throws ServletException {
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
	 * @see HttpServlet#HttpServlet()
	 */
	public PMOrderViewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in get...");
		/*
		 * com.web.sur.Order.Order(String OrderID, String BlockID, String Symbol,
		 * String TraderID, String Side, String Price, String Qty, String Date,
		 * String Time, String Tradetype, String TradeParam, String Status)
		 */
		//Order testorder = new Order(o11, BlockID, Symbol, TraderID, Side, Price, Qty, tp, Tradetype, TradeParam, Status)
		//this.insertIntoDB(testorder);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		String uId = session.getAttribute("username").toString();
		System.out.println(uId);

		String sql = "select * from webtardb22.order where pm_id='p001'";
		// boolean flag=false;
		ResultSet rs = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
//			ps.setString(1, uId);
			// ps.setString(2, password);
			rs = ps.executeQuery();

			if (rs.next()) {
				String orderID = rs.getString("order_id");
				System.out.println(orderID);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
