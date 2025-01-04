package com.foodapp.Controller;

import java.io.IOException;

import com.foodapp.daoimpl.UserDAOImpl;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String password=req.getParameter("password");
		if(password.equals(req.getParameter("cpassword"))) {
			password=SecureData.encrypt(req.getParameter("password"));
			String userName=SecureData.encrypt(req.getParameter("username"));
			String email=SecureData.encrypt(req.getParameter("email"));
			String address=SecureData.encrypt(req.getParameter("address"));
			UserDAOImpl udi=new UserDAOImpl();
			if(udi.insert(new User(userName,password,email,address))==1) {
				resp.sendRedirect("login.jsp");
			}
			else {
				resp.getWriter().print("Failed to register");
				resp.sendRedirect("register.jsp");
			}
			
		}
		else {
			resp.getWriter().print("password Mismatch");
			resp.sendRedirect("register.jsp");
		}
	}
}
