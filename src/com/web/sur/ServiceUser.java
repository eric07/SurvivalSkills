package com.web.sur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

public class ServiceUser {
 
	Connection con;
	String username;
	String password;
	
	ServiceUser(Connection con1,String username1,String userpassword){
		con=con1;
		username=username1;
		password=userpassword;
		
		
	}
	public String login(){
		String sql="select * from users where username=?  and password=?";
//		boolean flag=false;
		ResultSet rs = null;
				try{
					PreparedStatement ps=con.prepareStatement(sql);
					ps.setString(1, username);
					ps.setString(2, password);
				     rs=ps.executeQuery();
				    
					if(rs.next()){
						String role=rs.getString("role");
						
				        if(role.equals("pm"))
				        {
						return "pm";
				        }
			            else if(role.equals("pmtd"))
				        {
						return "pmtd";
				        }
				        else if(role.equals("trader"))
				        {
						return "trader";
				        }
					}
					
						
			
					
				}
				catch(SQLException e){
					e.printStackTrace();
				}
				
				return "error";
				
		
	}
	
}
