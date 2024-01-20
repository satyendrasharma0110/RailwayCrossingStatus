package com.railwaycrossing.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.railwaycrossing.dao.RailwayCrossingDatabaseOp;
import com.railwaycrossing.pojo.RailwayCrossing;

@WebServlet(name = "AddCrossingServlet", urlPatterns = { "/addCrossing" })
public class AddCrossingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String landmark = request.getParameter("landmark");
		String trainSchedules = request.getParameter("trainSchedules");
		String personInCharge = request.getParameter("personInCharge");
		String status = request.getParameter("status");

		RailwayCrossing crossing = new RailwayCrossing();
		crossing.setName(name);
		crossing.setAddress(address);
		crossing.setLandmark(landmark);
		crossing.setTrainSchedule(trainSchedules);
		crossing.setPersonInCharge(personInCharge);
		crossing.setStatus(status);

		RailwayCrossingDatabaseOp crossingDAO = new RailwayCrossingDatabaseOp();
		crossingDAO.addCrossing(crossing);

		response.sendRedirect("adminHome.jsp");
	}
}
