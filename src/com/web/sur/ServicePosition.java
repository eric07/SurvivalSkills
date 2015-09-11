package com.web.sur;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.*;

import org.apache.tomcat.jni.Time;
import org.omg.PortableInterceptor.USER_EXCEPTION;

public class ServicePosition {

	Connection con;
	String pmname;

	public ServicePosition(Connection con1, String username) {
		this.con = con1;
		this.pmname = username;
	}

	ArrayList<Portfolio> getPositions() {
		ResultSet rsUser;
		ResultSet rsQty;
		ResultSet rsPort;

		ArrayList<Portfolio> portlist = new ArrayList<Portfolio>();
		ArrayList<String> portnames = new ArrayList<String>();
		Positions position = new Positions();

		String symbol;	String qty;	String price;	
		Float ivalue;	String value;
		String user_id = null; String port_id = null;

		String sqlQty = "select p.pm_id, p.port_name, o.order_id, o.symbol, sum(o.qty) as totalQty, s.closing_price from webtardb3.portfolios p,webtardb3.orders o, webtardb3.securities s where o.pm_id =? and o.pm_id = p.pm_id and p.port_id = o.port_id and p.port_id = ? and o.symbol=s.symbol group by o.symbol";
		String sqlUser = "select user_id from users where username=?";
		String sqlPort = "select port_id, port_name from portfolios where pm_id=?";
		int i = 0;
		try {
			// Get user_id of current user
			PreparedStatement psUser = con.prepareStatement(sqlUser);
			psUser.setString(1, pmname);
			rsUser = psUser.executeQuery();
			if (rsUser.next()) {
				user_id = rsUser.getString("user_id");
//				System.out.println(user_id);
			}
			// Get portfolios of current user
			PreparedStatement psPort = con.prepareStatement(sqlPort);
			psPort.setString(1, user_id);
			rsPort = psPort.executeQuery();
			while (rsPort.next()) {
				Portfolio p = new Portfolio(rsPort.getString("port_name"));
				port_id = rsPort.getString("port_id");
				portnames.add(rsPort.getString("port_name"));
//				System.out.println(port_id);
				PreparedStatement psQty = con.prepareStatement(sqlQty);
				psQty.setString(1, user_id);
				psQty.setString(2, port_id);
				rsQty = psQty.executeQuery();
				while (rsQty.next()) {
					System.out.println(rsQty.getString("o.symbol"));
					symbol = rsQty.getString("o.symbol");
					qty = rsQty.getString("totalQty");
					price = rsQty.getString("s.closing_price");
					ivalue = Float.parseFloat(price) * Float.parseFloat(qty);
					value = ivalue.toString();
					if (!value.equals("0")) {
						position = new Positions (symbol, qty, price, value);;
						p.addPos(position);
					}
				}
//				System.out.println(portlist.get(i));
				i ++;
				portlist.add(p);
//				System.out.println(portlist);
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		 System.out.println(portlist);

		return portlist;

	}

}
