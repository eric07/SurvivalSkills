package com.web.sur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.HashSet;

public class ServiceCreateOrder {

	
	/**
	 * inserts an order into DB
	 * @param order
	 */
	public void insertIntoDB(Order1 order,Connection connection){
		System.out.println("inserting into db...");
		try {
			
			String sql = "SELECT * FROM orders WHERE trader_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, order.getTraderID());
			ResultSet rs = ps.executeQuery();
			ResultSet rs2;
			boolean similarorderfound=false;
			while(rs.next()){
				if(rs.getString("symbol").equals(order.getSymbol()) &&
					rs.getString("trader_id").equals(order.getTraderID()) &&
					rs.getString("side").equals(order.getSide()) &&
					rs.getString("type").equals(order.getTradetype()) &&
					rs.getFloat("parameter_price") == order.getTradeParam() &&
					rs.getString("status").equals(order.getStatus())){
					     similarorderfound=true;	
					
					break;
					
				}
			}
			if(similarorderfound){
				order.setBlockID(rs.getString("block_id"));
				sql = "UPDATE blocks SET total_qty = total_qty + ? WHERE block_id = ?";
				ps = connection.prepareStatement(sql);
				ps.setInt(1, order.getQty());
				ps.setString(2, order.getBlockID());
				ps.execute();
			}
			else{
					System.out.println("generating unique block id...");
					sql="select block_id from blocks order by block_id";
					ps=connection.prepareStatement(sql);
					rs2=ps.executeQuery();
					
					String currentid = "";
					int max = 0;
					int curnum = 0;
					while(rs2.next()){
						 currentid = rs2.getString("block_id");
						 curnum = Integer.parseInt(currentid.substring(1));
						 if(curnum > max)
							 max = curnum;
					}
					max++;
					order.setBlockID("b" + max);
					
					sql = "INSERT INTO blocks VALUES (?,?,?,?,?,?,?,?)";
					ps = connection.prepareStatement(sql);
					ps.setString(1, order.getBlockID());
					ps.setString(2, "Pending");
					ps.setString(3, "auto");
					//ps.setString(4, "11-11-11 11:11:11");
					ps.setTimestamp(4, new Timestamp(new Date().getTime()));
					ps.setInt(5, order.getQty());
					ps.setNull(6, Types.NULL);
					ps.setNull(7, Types.NULL);
					ps.setString(8, order.getSymbol());
					ps.execute();
					
			}				
			
			
			/*sql = "select user_id from users where username=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, pname);
			rs=ps.executeQuery();
			rs.next();*/
			//String userid = pmID;//rs.getString("user_id");
			
			sql = "INSERT INTO orders VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = connection.prepareStatement(sql);			
			
			ps.setString(1, order.getOrderID());
			ps.setString(2, order.getSymbol());
			if (order.getSide().equals("Sell")){
				ps.setInt(3, -order.getQty());
			}
			else{
				ps.setInt(3, order.getQty());
			}
			ps.setFloat(4, order.getTradeParam());
			ps.setString(5, order.getSide());
			ps.setNull(6, Types.NULL);
			ps.setNull(7, Types.NULL);
			ps.setString(8, order.getStatus());
			ps.setTimestamp(9, new Timestamp(new Date().getTime()));
			ps.setString(10, order.getTradetype());
			//ps.setString(11, userid);
			ps.setString(11, order.getPmID());
			ps.setString(12, order.getTraderID());
			ps.setString(13, order.getPortID());
			ps.setString(14, order.getBlockID());
			ps.setString(15, order.getComments());
			ps.setString(16, order.getSecurityType());
			
			System.out.println(ps.execute());
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
