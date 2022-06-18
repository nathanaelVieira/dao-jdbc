[33mcommit abfbee4048e18ccad17dd23659f12a670543486e[m[33m ([m[1;36mHEAD -> [m[1;32mmaster[m[33m, [m[1;31morigin/master[m[33m)[m
Author: nathanael <vieira_nathanael@hotmail.com>
Date:   Fri Jun 17 16:01:43 2022 -0300

    FindById implementation

[1mdiff --git a/application/Program.java b/application/Program.java[m
[1mindex 2bddb63..b1931e1 100644[m
[1m--- a/application/Program.java[m
[1m+++ b/application/Program.java[m
[36m@@ -1,21 +1,17 @@[m
 package application;[m
 [m
[31m-import java.util.Date;[m
[31m-[m
 import model.dao.DaoFactory;[m
 import model.dao.SellerDao;[m
[31m-import model.entities.Department;[m
 import model.entities.Seller;[m
 [m
 public class Program {[m
 [m
 	public static void main(String[] args) {[m
[31m-		Department obj = new Department(2, "Books");[m
[31m-		[m
[31m-		Seller seller = new Seller(21, "Bobzão", "bob@gmail.com", new Date(), 3000.0, obj); [m
[31m-		[m
[32m+[m
 		SellerDao sellerDao = DaoFactory.createSellerDao();[m
[31m-		[m
[32m+[m
[32m+[m		[32mSeller seller = sellerDao.findById(3);[m
[32m+[m
 		System.out.println(seller);[m
 	}[m
 [m
[1mdiff --git a/model/dao/DaoFactory.java b/model/dao/DaoFactory.java[m
[1mindex 0cc450a..9adc970 100644[m
[1m--- a/model/dao/DaoFactory.java[m
[1m+++ b/model/dao/DaoFactory.java[m
[36m@@ -1,9 +1,10 @@[m
 package model.dao;[m
 [m
[32m+[m[32mimport db.DB;[m
 import model.dao.impl.SellerDaoJDBC;[m
 [m
 public class DaoFactory {[m
 	public static SellerDao createSellerDao() {[m
[31m-		return new SellerDaoJDBC();[m
[32m+[m		[32mreturn new SellerDaoJDBC(DB.getConnection());[m
 	}[m
 }[m
[1mdiff --git a/model/dao/impl/SellerDaoJDBC.java b/model/dao/impl/SellerDaoJDBC.java[m
[1mindex cfc087b..2bc3953 100644[m
[1m--- a/model/dao/impl/SellerDaoJDBC.java[m
[1m+++ b/model/dao/impl/SellerDaoJDBC.java[m
[36m@@ -1,34 +1,81 @@[m
 package model.dao.impl;[m
 [m
[32m+[m[32mimport java.sql.Connection;[m
[32m+[m[32mimport java.sql.PreparedStatement;[m
[32m+[m[32mimport java.sql.ResultSet;[m
[32m+[m[32mimport java.sql.SQLException;[m
 import java.util.List;[m
 [m
[32m+[m[32mimport db.DB;[m
[32m+[m[32mimport db.DbException;[m
 import model.dao.SellerDao;[m
[32m+[m[32mimport model.entities.Department;[m
 import model.entities.Seller;[m
 [m
[31m-public class SellerDaoJDBC implements SellerDao{[m
[32m+[m[32mpublic class SellerDaoJDBC implements SellerDao {[m
[32m+[m
[32m+[m	[32mprivate Connection connection;[m
[32m+[m
[32m+[m	[32mpublic SellerDaoJDBC(Connection connection) {[m
[32m+[m		[32mthis.connection = connection;[m
[32m+[m	[32m}[m
 [m
 	@Override[m
 	public void insert(Seller obj) {[m
 		// TODO Auto-generated method stub[m
[31m-		[m
[32m+[m
 	}[m
 [m
 	@Override[m
 	public void update(Seller obj) {[m
 		// TODO Auto-generated method stub[m
[31m-		[m
[32m+[m
 	}[m
 [m
 	@Override[m
 	public void deleteById(Integer id) {[m
 		// TODO Auto-generated method stub[m
[31m-		[m
[32m+[m
 	}[m
 [m
 	@Override[m
 	public Seller findById(Integer id) {[m
[31m-		// TODO Auto-generated method stub[m
[31m-		return null;[m
[32m+[m
[32m+[m		[32mPreparedStatement prepareStatement = null;[m
[32m+[m		[32mResultSet resultSet = null;[m
[32m+[m
[32m+[m		[32mtry {[m
[32m+[m			[32mprepareStatement = connection.prepareStatement([m
[32m+[m					[32m"SELECT seller.*,department.Name as DepName "[m[41m [m
[32m+[m							[32m+ "FROM seller INNER JOIN department "[m
[32m+[m							[32m+ "ON seller.DepartmentId = department.Id "[m
[32m+[m							[32m+ "WHERE seller.Id = ?");[m
[32m+[m
[32m+[m			[32mprepareStatement.setInt(1, id);[m
[32m+[m			[32mresultSet = prepareStatement.executeQuery();[m
[32m+[m			[32mif (resultSet.next()) {[m
[32m+[m				[32mDepartment derp = new Department();[m
[32m+[m				[32mderp.setId(resultSet.getInt("DepartmentId"));[m
[32m+[m				[32mderp.setName(resultSet.getString("DepName"));[m
[32m+[m
[32m+[m				[32mSeller obj = new Seller();[m
[32m+[m				[32mobj.setId(resultSet.getInt("DepartmentId"));[m
[32m+[m				[32mobj.setName(resultSet.getString("Name"));[m
[32m+[m				[32mobj.setEmail(resultSet.getString("Email"));[m
[32m+[m				[32mobj.setBaseSalary(resultSet.getDouble("BaseSalary"));[m
[32m+[m				[32mobj.setBirthDate(resultSet.getDate("BirthDate"));[m
[32m+[m				[32mobj.setDepartment(derp);[m
[32m+[m
[32m+[m				[32mreturn obj;[m
[32m+[m			[32m}[m
[32m+[m[41m			[m
[32m+[m			[32mreturn null;[m
[32m+[m		[32m} catch (SQLException e) {[m
[32m+[m			[32mthrow new DbException(e.getMessage());[m
[32m+[m		[32m} finally {[m
[32m+[m			[32mDB.closeStatement(prepareStatement);[m
[32m+[m			[32mDB.closeResultSet(resultSet);[m
[32m+[m		[32m}[m
 	}[m
 [m
 	@Override[m
