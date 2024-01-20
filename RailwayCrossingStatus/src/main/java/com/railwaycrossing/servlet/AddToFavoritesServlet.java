package com.railwaycrossing.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.railwaycrossing.dao.RailwayCrossingDatabaseOp;

import java.io.IOException;

public class AddToFavoritesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String crossingId = request.getParameter("crossingId");
		if (crossingId != null && !crossingId.isEmpty()) {
			int railwayCrossingId = Integer.parseInt(crossingId);

			RailwayCrossingDatabaseOp crossingDAO = new RailwayCrossingDatabaseOp();
			crossingDAO.addToFavorites(railwayCrossingId); 
		}

		response.sendRedirect("userHome.jsp");
	}
}