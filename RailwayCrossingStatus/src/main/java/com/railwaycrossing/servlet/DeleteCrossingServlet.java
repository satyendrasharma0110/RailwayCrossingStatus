package com.railwaycrossing.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.railwaycrossing.dao.RailwayCrossingDatabaseOp;

@WebServlet("/deleteCrossing")
public class DeleteCrossingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int crossingId = Integer.parseInt(request.getParameter("id"));

		RailwayCrossingDatabaseOp crossingDAO = new RailwayCrossingDatabaseOp();
		crossingDAO.deleteCrossing(crossingId);

		response.sendRedirect("adminHome.jsp");
	}
}
