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
	public ServicePosition(Connection con1,String username) {
		this.con = con1;
		this.pmname=username;
	}

	ArrayList<Serializable> getPositions() {
		ResultSet rs;
		ResultSet rs1;
		ResultSet rs2;
		
	
		ArrayList<Positions> plist = new ArrayList<Positions>();

		ArrayList<Serializable> poslist = new ArrayList<Serializable>();
		ArrayList<String> pnlist = new ArrayList<String>();
		
		String pmid;
		String sql = "select s.symbol,s.closing_price,sum(o.qty) as totalqty from (orders o left outer join securities s on o.symbol=s.symbol) where o.pm_id=? group by s.symbol having sum(o.qty)";
		String sql1="select user_id from users where username=?";
		String sql2="select port_name from portfolios where pm_id=?";
		int index=0;
		try {
						
						PreparedStatement ps1 = con.prepareStatement(sql1);
						ps1.setString(1, pmname);
						rs1 = ps1.executeQuery();
						rs1.next();
						pmid=rs1.getString("user_id");
						
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, pmid);
						rs = ps.executeQuery();
						
						PreparedStatement ps2 = con.prepareStatement(sql2);
						ps2.setString(1, pmid);
						rs2 = ps2.executeQuery();
						
			
				while (rs.next()) {
					
						String symbol=rs.getString("symbol");
						int qty=rs.getInt("totalqty");
						float cp=rs.getFloat("closing_price");
						float cv=qty*cp;
						
						
					Positions p=new Positions(symbol, qty, cp, cv);
					
						plist.add(p);
						
						
					} 
			
				while(rs2.next())
				{
					String pn1=rs2.getString("port_name");
					pnlist.add(pn1);
				}
				
				poslist.add(index++, pmname);
				poslist.add(index++,pmid);
				poslist.add(index++,pnlist);
				
				poslist.add(index++, plist);
				System.out.println(pmid);
				System.out.println(pmname);
				System.out.println(plist);
				
					 

		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println(poslist);
		

		return poslist;

	}

}
