package com.web.sur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.*;
import org.apache.tomcat.jni.Time;

public class ServicePortfolio {

	Connection con;
	String user;
	public ServicePortfolio(Connection con1, String username) {
		con = con1;
		user = username;
	}

	ArrayList<Portfolio> getPortfolios() {
		ResultSet rs;
		ResultSet rs1;

		ArrayList<Order> olist = new ArrayList<Order>();
		ArrayList<Portfolio> plist = new ArrayList<Portfolio>();

		String sql = "select * from orders o left join securities s on o.symbol=s.symbol where o.pm_id =(select user_id from users where username = ?) order by port_id;";
		String sql1 = "select * from portfolios";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps.setString(1, user);
			rs = ps.executeQuery();
			rs1 = ps1.executeQuery();
			
			while (rs1.next()) {
				olist.clear();
				while (rs.next()) {
					if (rs.getString("port_id")
							.equals(rs1.getString("port_id"))) {
						String order_id = rs.getString("order_id");
						String Block_id = rs.getString("block_id");
						String symbol = rs.getString("symbol");
						String trader_id = rs.getString("trader_id");
						String side = rs.getString("side");
						
						String price = rs.getString("market_price");
						String qty = rs.getString("qty");
						if(qty.contains("-"))
						{
							qty=qty.substring(1);
						}
						String tradetype=rs.getString("type");
						String tradeparam=rs.getString("parameter_price");
						

						Date date = rs.getDate("timestamp");
					java.sql.Time time=rs.getTime("timestamp");
					String d1=date.toString();
					String t1=time.toString();
						
						String status = rs.getString("status");

						Order o = new Order(order_id, Block_id, symbol, trader_id, side, price, qty, d1,t1 , tradetype, tradeparam, status);
						olist.add(o);
					} else {
						rs.previous();
						break;
					}

					 

				}
//				System.out.println(olist);
				String pname = rs1.getString("port_name");
				
				ArrayList<Order> ol=new ArrayList<Order>();
				ol.addAll(olist);
//				System.out.println(ol);
				Portfolio p = new Portfolio(pname,ol);
				plist.add(p);
			

			}

//			System.out.println(plist);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return plist;

	}

}
