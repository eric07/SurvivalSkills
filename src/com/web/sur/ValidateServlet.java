package com.web.sur;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/validateServlet")
public class ValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
       private Connection connection;
    
    public ValidateServlet(){
        super();
      
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		ServletContext sc=config.getServletContext();
		String driverName=sc.getInitParameter("driverClass");
		String url=sc.getInitParameter("url");
		String user=sc.getInitParameter("username");
		String password=sc.getInitParameter("password");
		
		try {
			connection=DBconnection.getConnection(driverName, url, user, password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	public void destroy() {
		System.out.println("inside destroy");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		
		
		ServiceUser su=new ServiceUser(connection, username, password);
		String status=su.login();
		
		if(status.equals("pm"))
		{
			
			HttpSession hsession=request.getSession();
			hsession.setAttribute("username",username);
			hsession.setAttribute("password",password);
			RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);
			
		}
		else if(status.equals("pmtd"))
		{
			HttpSession hsession=request.getSession();
		
			hsession.setAttribute("username",username);
			hsession.setAttribute("password",password);
			RequestDispatcher dispatcher = request.getRequestDispatcher("mainpm.jsp");
			dispatcher.forward(request, response);
			
		}
		else if(status.equals("trader"))
		{
			HttpSession hsession=request.getSession();
			hsession.setAttribute("username",username);
			hsession.setAttribute("password",password);
			RequestDispatcher dispatcher = request.getRequestDispatcher("maintd.jsp");
			dispatcher.forward(request, response);
			
		}
		else if(status.equals("error"))
		{
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
			
		}
	}
		
	
//		boolean flag=false;
		
				

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	

}
