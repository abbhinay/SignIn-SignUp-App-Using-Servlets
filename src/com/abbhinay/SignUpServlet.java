package com.abbhinay;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String mail=request.getParameter("email");
		String pass=request.getParameter("password");
		String repeatPass=request.getParameter("RepeatPassword");
		if(pass.contentEquals(repeatPass)) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Users", "root", "mtunes");
				PreparedStatement psmt=con.prepareStatement("insert into usersInfo values(?,?,?)");
				psmt.setString(1, name);
				psmt.setString(2, mail);
				psmt.setString(3, pass);
				int a=psmt.executeUpdate();
				if(a>0)
					out.println("successfully inserted");
				else
					out.println("registration failed");
				con.close();
			}catch(Exception e)
			{
				System.out.println(e);
			}
		}else {
			out.println("the two passwords did not match");
		}
		
	}

}
