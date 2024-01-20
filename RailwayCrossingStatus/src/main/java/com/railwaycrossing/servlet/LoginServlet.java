package com.railwaycrossing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.railwaycrossing.dao.UserDatabaseOp;
import com.railwaycrossing.pojo.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UserDatabaseOp userDatabaseOp = new UserDatabaseOp();
		User user = userDatabaseOp.getUserByEmail(email);

		if (user != null && user.getPassword().equals(password)) {

			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			response.sendRedirect("userHome.jsp");
		} else {

			response.sendRedirect("userRegister.jsp?error=1");
		}
	}
}
