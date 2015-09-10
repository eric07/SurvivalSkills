package com.web.sur;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

//@WebServlet("/PositionServlet")
public class PositionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

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
	public PositionServlet() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		HttpSession hs=request.getSession();
		String pmname=(String) hs.getAttribute("username");
		ServicePosition sp1=new ServicePosition(connection,pmname);
		ArrayList<Serializable> pl=new ArrayList<Serializable>();
		pl=sp1.getPositions();
		
		request.setAttribute("Positionlist", pl);
		request.setAttribute("pname",pl.get(0));
		request.setAttribute("pmid", pl.get(1));
		
		request.setAttribute("pnlist", (ArrayList<String>)pl.get(2));
		request.setAttribute("Positionlist", (ArrayList<Positions>)pl.get(3));
//		System.out.println("Positions Servlet");
		out.println(request.getAttribute("Positionlist"));
		RequestDispatcher rd = request.getRequestDispatcher("Positions.jsp");
		rd.forward(request, response);
		
		

	}

}