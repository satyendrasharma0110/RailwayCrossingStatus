package com.railwaycrossing.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.railwaycrossing.dao.RailwayCrossingDatabaseOp;
import com.railwaycrossing.pojo.RailwayCrossing;

@WebServlet("/adminHomePage")
public class AdminHomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RailwayCrossingDatabaseOp crossingDAO;

	public void init() {
		crossingDAO = new RailwayCrossingDatabaseOp();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<RailwayCrossing> crossings = crossingDAO.getAllCrossings();


		request.setAttribute("crossings", crossings);
		request.getRequestDispatcher("adminHome.jsp").forward(request, response);
	}
}