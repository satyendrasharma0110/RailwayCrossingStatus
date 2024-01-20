package com.railwaycrossing.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.railwaycrossing.dao.RailwayCrossingDatabaseOp;

/**
 * Servlet implementation class RemoveFromFavoritesServlet
 */
public class RemoveFromFavoritesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String crossingId = request.getParameter("crossingId");
		if (crossingId != null && !crossingId.isEmpty()) {
			int railwayCrossingId = Integer.parseInt(crossingId);

			RailwayCrossingDatabaseOp crossingDAO = new RailwayCrossingDatabaseOp();
			crossingDAO.removeFromFavorites(railwayCrossingId); 
		}

		response.sendRedirect("userHome.jsp");
	}

}
