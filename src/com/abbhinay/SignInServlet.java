package com.abbhinay;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String mail=request.getParameter("email");
		String pass=request.getParameter("password");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Users", "root", "mtunes");
			PreparedStatement psmt=con.prepareStatement("select * from usersInfo where Name=? and Email=? and Password=?");
			psmt.setString(1, name);
			psmt.setString(2, mail);
			psmt.setString(3, pass);
			ResultSet a = psmt.executeQuery();
			RequestDispatcher rd=null;
			if(a.next()) {
				out.println("you are logged in");
			}else {
				out.println("<h1> incorrect username or password </h1>");
				rd=request.getRequestDispatcher("index.html");
				rd.include(request, response);
			}
			con.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}

}
