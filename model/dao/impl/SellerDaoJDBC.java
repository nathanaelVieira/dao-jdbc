package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection connection;

	public SellerDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {

		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;

		try {
			prepareStatement = connection.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");

			prepareStatement.setInt(1, id);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				Department derp = intantiateDepartment(resultSet);
				Seller obj = intantiateSeller(resultSet, derp);

				return obj;
			}

			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(prepareStatement);
			DB.closeResultSet(resultSet);
		}
	}

	private Seller intantiateSeller(ResultSet resultSet, Department department) throws SQLException {

		Seller seller = new Seller();
		seller.setId(resultSet.getInt("Id"));
		seller.setName(resultSet.getString("Name"));
		seller.setEmail(resultSet.getString("Email"));
		seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
		seller.setBirthDate(resultSet.getDate("BirthDate"));
		seller.setDepartment(department);
		return seller;
	}

	private Department intantiateDepartment(ResultSet resultSet) throws SQLException {
		Department department = new Department();
		department.setId(resultSet.getInt("DepartmentId"));
		department.setName(resultSet.getString("DepName"));
		return department;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {

		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;

		try {
			prepareStatement = connection.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE DepartmentId = ? " + "ORDER BY Name ");

			prepareStatement.setInt(1, department.getId());

			resultSet = prepareStatement.executeQuery();
			
			List<Seller> listSeller = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (resultSet.next()) {

				Department dep = map.get(resultSet.getInt("DepartmentId"));
				if (dep == null) {
					dep = intantiateDepartment(resultSet);
					map.put(resultSet.getInt("DepartmentId"), dep);
				}

				Seller obj = intantiateSeller(resultSet, dep);
				listSeller.add(obj);
			}

			return listSeller;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(prepareStatement);
			DB.closeResultSet(resultSet);
		}
	}

}
