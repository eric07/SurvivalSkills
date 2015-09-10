package com.web.sur;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/PMOrderViewServlet")
public class PMOrderViewServlet extends HttpServlet {
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
		String uID = hs.getAttribute("username").toString();
		
		
		ServicePortfolio sp=new ServicePortfolio(connection, uID);
		ArrayList<Portfolio> pl=sp.getPortfolios();
		
		hs.setAttribute("Portlist", pl);
		request.setAttribute("Portlist", pl);
		
		RequestDispatcher rd = request.getRequestDispatcher("PMorderView.jsp");
		rd.forward(request, response);
	
		
		

	}

}
