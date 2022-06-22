package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection connection;

	public DepartmentDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Department obj) {

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO department " + "(Id, Name) " + "VALUES " + "(?, ?)", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, obj.getId());
			preparedStatement.setString(2, obj.getName());

			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					obj.setId(resultSet.getInt(1));
				}
				DB.closeResultSet(resultSet);
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public void update(Department obj) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement("UPDATE department SET Name = ? WHERE Id = ? ");
			preparedStatement.setInt(1, obj.getId());
			preparedStatement.setString(2, obj.getName());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM department WHERE Id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM department WHERE Id = ? ");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return instantiateDepartment(resultSet);
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public List<Department> findAll() {
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		try {
			prepareStatement = connection.prepareStatement("select * from department " + "order by Name");
			resultSet = prepareStatement.executeQuery();

			List<Department> listDepartment = new ArrayList<>();

			while (resultSet.next()) {
				listDepartment.add(instantiateDepartment(resultSet));
			}

			return listDepartment;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
		Department department = new Department();
		department.setId(resultSet.getInt("Id"));
		department.setName(resultSet.getString("Name"));
		return department;
	}

}
