
	package com.railwaycrossing.dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;

import com.railwaycrossing.dbutil.DatabaseConnection;
import com.railwaycrossing.pojo.RailwayCrossing;

	public class RailwayCrossingDatabaseOp {
		private Connection connection;

		public RailwayCrossingDatabaseOp() {
			
			connection = DatabaseConnection.getConnection();
		}


		public List<RailwayCrossing> getAllCrossings() {
			List<RailwayCrossing> crossings = new ArrayList<>();
			try {
				String query = "SELECT * FROM railway_crossing";
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					RailwayCrossing crossing = new RailwayCrossing();
					crossing.setId(resultSet.getInt("railway_crossing_id"));
					crossing.setName(resultSet.getString("name"));
					crossing.setAddress(resultSet.getString("address"));
					crossing.setLandmark(resultSet.getString("landmark"));
					crossing.setTrainSchedule(resultSet.getString("train_schedule"));
					crossing.setPersonInCharge(resultSet.getString("person_in_charge"));
					crossing.setStatus(resultSet.getString("status"));
					crossings.add(crossing);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return crossings;
		}

		public RailwayCrossing getCrossingById(int crossingId) {
			RailwayCrossing crossing = null;
			try {
				String query = "SELECT * FROM railway_crossing WHERE railway_crossing_id = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setInt(1, crossingId);
				ResultSet resultSet = statement.executeQuery();
				if (resultSet.next()) {
					crossing = new RailwayCrossing();
					crossing.setId(resultSet.getInt("railway_crossing_id"));
					crossing.setName(resultSet.getString("name"));
					crossing.setAddress(resultSet.getString("address"));
					crossing.setLandmark(resultSet.getString("landmark"));
					crossing.setTrainSchedule(resultSet.getString("train_schedule"));
					crossing.setPersonInCharge(resultSet.getString("person_in_charge"));
					crossing.setStatus(resultSet.getString("status"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return crossing;
		}

		public void updateCrossing(RailwayCrossing crossing) {
			try {
				String query = "UPDATE railway_crossing SET name=?, address=?, landmark=?, train_schedule=?, person_in_charge=?, status=? WHERE railway_crossing_id=?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, crossing.getName());
				statement.setString(2, crossing.getAddress());
				statement.setString(3, crossing.getLandmark());
				statement.setString(4, crossing.getTrainSchedule());
				statement.setString(5, crossing.getPersonInCharge());
				statement.setString(6, crossing.getStatus());
				statement.setInt(7, crossing.getId());
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public void deleteCrossing(int crossingId) {
			try {
				connection.setAutoCommit(false); 
				
				String deleteFavoriteCrossingsQuery = "DELETE FROM favorite_crossing WHERE railway_crossing_id = ?";
				try (PreparedStatement deleteFavoriteCrossingsStatement = connection
						.prepareStatement(deleteFavoriteCrossingsQuery)) {
					deleteFavoriteCrossingsStatement.setInt(1, crossingId);
					deleteFavoriteCrossingsStatement.executeUpdate();
				}


				String deleteRailwayCrossingQuery = "DELETE FROM railway_crossing WHERE railway_crossing_id = ?";
				try (PreparedStatement deleteRailwayCrossingStatement = connection
						.prepareStatement(deleteRailwayCrossingQuery)) {
					deleteRailwayCrossingStatement.setInt(1, crossingId);
					deleteRailwayCrossingStatement.executeUpdate();
				}

				connection.commit(); 
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException rollbackException) {
					rollbackException.printStackTrace();
				}
				e.printStackTrace();
			}
		}

		public void addCrossing(RailwayCrossing crossing) {
			try {
				String query = "INSERT INTO railway_crossing (name, address, landmark, train_schedule, person_in_charge, status) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, crossing.getName());
				statement.setString(2, crossing.getAddress());
				statement.setString(3, crossing.getLandmark());
				statement.setString(4, crossing.getTrainSchedule());
				statement.setString(5, crossing.getPersonInCharge());
				statement.setString(6, crossing.getStatus());
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public List<RailwayCrossing> getFavoriteCrossings() {
			List<RailwayCrossing> favoriteCrossings = new ArrayList<>();

			try (Connection connection = DatabaseConnection.getConnection()) {
				String query = "SELECT rc.* FROM railway_crossing rc "
						+ "JOIN favorite_crossing fc ON rc.railway_crossing_id = fc.railway_crossing_id";
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery();

				while (resultSet.next()) {
					RailwayCrossing crossing = new RailwayCrossing();
					crossing.setId(resultSet.getInt("railway_crossing_id"));
					crossing.setName(resultSet.getString("name"));
					crossing.setAddress(resultSet.getString("address"));
					crossing.setLandmark(resultSet.getString("landmark"));
					crossing.setTrainSchedule(resultSet.getString("train_schedule"));
					crossing.setPersonInCharge(resultSet.getString("person_in_charge"));
					crossing.setStatus(resultSet.getString("status"));

					favoriteCrossings.add(crossing);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return favoriteCrossings;
		}

		public void addToFavorites(int crossingId) {
			try (Connection connection = DatabaseConnection.getConnection()) {
				String sql = "INSERT INTO favorite_crossing (railway_crossing_id) VALUES (?)";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, crossingId);
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}

		public void removeFromFavorites(int crossingId) {
			try (Connection connection = DatabaseConnection.getConnection()) {
				String sql = "DELETE FROM favorite_crossing WHERE railway_crossing_id = ?";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, crossingId);
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}

	}


