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
        String traderID = request.getParameter("traderID");
        String portfolioID = request.getParameter("portfolioID");
        String comments = request.getParameter("comments");
        String securityType = request.getParameter("securityType");

        String qtyEntered = quantity;
        String qtyOwned = "0";
        String sqlSumQty = "select sum(qty) from webtardb3.orders where port_id = '" +portfolioID+"' and symbol = '" +symbol+"'";
        
        
        // check if portfolio holds the stock. Return to form if not
        String sqlSymbol = "select distinct symbol from webtardb3.orders where port_id = '"
                + portfolioID + "'";
        ResultSet rs = null;
        ResultSet rsQty = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sqlSymbol);
            PreparedStatement ps2 = connection.prepareStatement(sqlSumQty);
            rs = ps.executeQuery();
            rsQty = ps2.executeQuery();
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

            if (flag && side.equals("Buy")) {
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
                        + "p001"
                        + "','"
                        + traderID
                        + "','"
                        + portfolioID
                        + "','"
                        + "b000'"
                        + ",'"
                        + comments
                        + "','"
                        + securityType + "');";
                System.out.println(sql);

                try {

                    java.sql.Statement stmt = connection.createStatement();
                    stmt.execute(sql);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/PMOrderViewServlet");
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
                        + "p001"
                        + "','"
                        + traderID
                        + "','"
                        + portfolioID
                        + "','"
                        + "b000'"
                        + ",'"
                        + comments
                        + "','"
                        + securityType + "');";

                try {

                    java.sql.Statement stmt1 = connection.createStatement();
                    stmt1.execute(sql);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/PMOrderViewServlet");
                    dispatcher.forward(request, response);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}