package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
					"SELECT seller.*,department.Name as DepName " 
							+ "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id "
							+ "WHERE seller.Id = ?");

			prepareStatement.setInt(1, id);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				Department derp = new Department();
				derp.setId(resultSet.getInt("DepartmentId"));
				derp.setName(resultSet.getString("DepName"));

				Seller obj = new Seller();
				obj.setId(resultSet.getInt("DepartmentId"));
				obj.setName(resultSet.getString("Name"));
				obj.setEmail(resultSet.getString("Email"));
				obj.setBaseSalary(resultSet.getDouble("BaseSalary"));
				obj.setBirthDate(resultSet.getDate("BirthDate"));
				obj.setDepartment(derp);

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

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
