package com.web.sur;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/pmCreateOrderServlet")
public class PMCreateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		ServletContext sc = config.getServletContext();
		String driverName = sc.getInitParameter("driverClass");
		String url = sc.getInitParameter("url");
		String user = sc.getInitParameter("username");
		String password = sc.getInitParameter("password");

		try {
			connection = DBconnection.getConnection(driverName, url, user,
					password);
			System.out.println(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("destroy triggered");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * String username = request.getParameter("username"); String password =
		 * request.getParameter("password");
		 */
		
		System.out.println("in dopost...");

		double randNum = (Math.random() * 1000);
		int primary = (int) randNum;
		String symbol = request.getParameter("symbol");
		String quantity = request.getParameter("quantity");
		String parameterValue = request.getParameter("parameterValue");
		String side = request.getParameter("side");
		String orderType = request.getParameter("orderType");
		String traderID = request.getParameter("traderID");
		String portfolioID = request.getParameter("portfolioID");
		String comments = request.getParameter("comments");
		String securityType = request.getParameter("securityType");
		
		/*System.out.println(symbol);
		System.out.println(quantity);
		System.out.println(parameterValue);
		System.out.println(side);
		System.out.println(orderType);
		System.out.println(traderID);
		System.out.println(portfolioID);
		System.out.println(comments);
		System.out.println(securityType);*/
		
		try{
			System.out.println("inside try...");
			
			String sql="select order_id from orders order by order_id";
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			String currentid = "";
			int max = 0;
			int curnum = 0;
			while(rs.next()){
				 currentid = rs.getString("order_id");
				 curnum = Integer.parseInt(currentid.substring(1));
				 if(curnum > max)
					 max = curnum;
			}
			max++;
			String neworderid = "o" + max;
			
			sql = "SELECT market_price FROM securities WHERE symbol = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, symbol);
			rs = ps.executeQuery();
			rs.next();
			float price=rs.getFloat("market_price");
			System.out.println("market price: " + price);
			
			int intquantity=Integer.parseInt(quantity);
			
			float fparametervalue = 0;
			System.out.println("param equals null: " + parameterValue.equals("Null"));
			if(!parameterValue.equals("Null"))
				fparametervalue=Float.parseFloat(parameterValue);
			
			Order o =new Order(neworderid,null, symbol, traderID, side, price,
					intquantity, "11-11-11 11:11:11", orderType, fparametervalue, "New", portfolioID);
			
			ServiceCreateOrder sco=new ServiceCreateOrder();
			
			String pname=(String)request.getAttribute("pname");
			//TODO get user name from session, or pass userid to be used in ServiceCreateOrder
			pname = "jtye";
			
			sco.insertIntoDB(o, connection,pname);
		 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("out of try...");
	}
}
