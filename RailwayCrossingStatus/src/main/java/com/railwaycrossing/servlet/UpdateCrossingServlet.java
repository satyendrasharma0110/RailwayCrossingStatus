package com.railwaycrossing.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.railwaycrossing.dao.RailwayCrossingDatabaseOp;
import com.railwaycrossing.pojo.RailwayCrossing;

@WebServlet("/updateCrossing")
public class UpdateCrossingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String idParam = request.getParameter("crossingId");

			if (idParam != null && !idParam.isEmpty()) {
				int crossingId = Integer.parseInt(idParam);

				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String landmark = request.getParameter("landmark");
				String trainSchedules = request.getParameter("trainSchedules");
				String personInCharge = request.getParameter("personInCharge");
				String status = request.getParameter("status");

				RailwayCrossing updatedCrossing = new RailwayCrossing();
				updatedCrossing.setId(crossingId);
				updatedCrossing.setName(name);
				updatedCrossing.setAddress(address);
				updatedCrossing.setLandmark(landmark);
				updatedCrossing.setTrainSchedule(trainSchedules);
				updatedCrossing.setPersonInCharge(personInCharge);
				updatedCrossing.setStatus(status);

				RailwayCrossingDatabaseOp crossingDAO = new RailwayCrossingDatabaseOp();
				crossingDAO.updateCrossing(updatedCrossing);

				response.sendRedirect("adminHome.jsp");
			} else {
				throw new ServletException("Crossing ID is missing.");
			}

		} catch (NumberFormatException e) {
			throw new ServletException("Invalid Crossing ID format.", e);
		} catch (Exception e) {
			throw new ServletException("An error occurred during the crossing update.", e);
		}
	}
}