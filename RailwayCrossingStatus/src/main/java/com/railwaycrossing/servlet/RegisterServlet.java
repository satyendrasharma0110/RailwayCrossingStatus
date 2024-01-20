package com.railwaycrossing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.railwaycrossing.dao.UserDatabaseOp;
import com.railwaycrossing.pojo.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = new User(name, email, password);

		UserDatabaseOp userDatabaseOp = new UserDatabaseOp();
		userDatabaseOp.registerUser(user);

		response.sendRedirect("userLogin.jsp");
	}

}
