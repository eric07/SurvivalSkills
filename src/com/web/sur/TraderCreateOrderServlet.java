package com.web.sur;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TraderCreateOrderServlet
 */
@WebServlet("/traderCreateOrderServlet")
public class TraderCreateOrderServlet extends HttpServlet {
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

        double randNum = (Math.random() * 1000);
        int primary = (int) randNum;
        String symbol = request.getParameter("symbol");
        String quantity = request.getParameter("quantity");
        String parameterValue = request.getParameter("parameterValue");
        String side = request.getParameter("side");
        String orderType = request.getParameter("orderType");
        String traderID = request.getParameter("tid");
        String portfolioID = request.getParameter("portfolioID");
        String comments = request.getParameter("comments");
        String securityType = "Equity";//request.getParameter("securityType");

        String sqlPmid = "Select pm_id from webtardb3.portfolios where port_id = '" +portfolioID+"'";
        String pmID = "";
        
        String qtyEntered = quantity;
        String qtyOwned = "0";
        String sqlSumQty = "select sum(qty) from webtardb3.orders where port_id = '" +portfolioID+"' and symbol = '" +symbol+"'";
        
        // check if portfolio holds the stock. Return to form if not
        String sqlSymbol = "select distinct symbol from webtardb3.orders where port_id = '"+ portfolioID + "'";
        
        ResultSet rs = null;
        ResultSet rsQty = null;
        ResultSet rsPid = null;
        
    /*    try {

        	 PreparedStatement ps3 = connection.prepareStatement(sqlPmid);
        	 rsPid = ps3.executeQuery();
        	 if(rsPid.next()){
        		String pmID = rsPid.getString("pm_id");
        		 System.out.println("PM id" +pmID);
        	 }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        
        
        try {
            PreparedStatement ps = connection.prepareStatement(sqlSymbol);
            PreparedStatement ps2 = connection.prepareStatement(sqlSumQty);
            PreparedStatement ps3 = connection.prepareStatement(sqlPmid);
            
            rs = ps.executeQuery();
            rsQty = ps2.executeQuery();
            rsPid = ps3.executeQuery();
            
            if(rsPid.next()){
        		pmID = rsPid.getString("pm_id");
        	 }
            
            ArrayList<String> shares = new ArrayList<String>();
            if (rsQty.next()){
                qtyOwned = rsQty.getString("sum(qty)");
            }
            while (rs.next()) {
                String symbolCheck = rs.getString("symbol");
                shares.add(symbolCheck);
            }
            boolean flag = false;
            if ((side.equals("Sell") && shares.contains(symbol) && qtyOwned.compareTo(qtyEntered) >= 0) || side.equals("Buy")) {
                flag = true;
                
            } else {
            	PrintWriter out = response.getWriter();
                System.out.println("Alert: no match");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Cannot sell security not owned');");
                out.println("location='PMorderView.jsp';");
                out.println("</script>");
//                System.out.println(side.equals("Sell"));
//                System.out.println(shares.contains(symbol));
//                System.out.println(qtyOwned.compareTo(qtyEntered));
            }

            /*if (flag && side.equals("Buy")) {
                String sql = "Insert into webtardb3.orders (order_id,symbol,qty,parameter_price,side,status,timestamp,type,pm_id,trader_id,port_id,block_id,comment,security_type) values ("
                        + "'o"
                        + primary
                        + "','"
                        + symbol
                        + "',"
                        + quantity
                        + ","
                        + parameterValue
                        + ",'"
                        + side
                        + "','"
                        + "New"
                        + "',"
                        + "CURRENT_TIMESTAMP"
                        + ",'"
                        + orderType
                        + "','"
                        + pmID
                        + "','"
                        + traderID
                        + "','"
                        + portfolioID
                        + "','"
                        + "b000'"
                        + ",'"
                        + comments
                        + "','"
                        + "Equity" + "');";
                System.out.println(sql);

                try {

                    java.sql.Statement stmt = connection.createStatement();
                    stmt.execute(sql);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("TraderDashBoard.jsp");
                    dispatcher.forward(request, response);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
            if (flag && side.equals("Sell")) {
                String sql = "Insert into webtardb3.orders (order_id,symbol,qty,parameter_price,side,status,timestamp,type,pm_id,trader_id,port_id,block_id,comment,security_type) values ("
                        + "'o"
                        + primary
                        + "','"
                        + symbol
                        + "',-"
                        + quantity
                        + ","
                        + parameterValue
                        + ",'"
                        + side
                        + "','"
                        + "New"
                        + "',"
                        + "CURRENT_TIMESTAMP"
                        + ",'"
                        + orderType
                        + "','"
                        + pmID
                        + "','"
                        + traderID
                        + "','"
                        + portfolioID
                        + "','"
                        + "b000'"
                        + ",'"
                        + comments
                        + "','"
                        + "Equity" + "');";

                try {

                    java.sql.Statement stmt1 = connection.createStatement();
                    stmt1.execute(sql);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("TraderDashBoard.jsp");
                    dispatcher.forward(request, response);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }*/


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try{
			System.out.println("inside try...");
			
			String sql="select order_id from orders order by order_id";
			PreparedStatement ps=connection.prepareStatement(sql);
			rs=ps.executeQuery();
			
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
			
			Order1 o =new Order1(neworderid,null, symbol, traderID, side, price,
					intquantity, null, orderType, fparametervalue, "New", portfolioID, pmID, comments, securityType);
			
			ServiceCreateOrder sco=new ServiceCreateOrder();
			
			sco.insertIntoDB(o, connection);
			RequestDispatcher dispatcher = request.getRequestDispatcher("TraderDashBoard.jsp");
            dispatcher.forward(request, response);
		 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("out of try...");
        
    }
}