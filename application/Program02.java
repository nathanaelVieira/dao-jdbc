package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program02 {

	public static void main(String[] args) {
		
		System.out.println("==== Test 01: departmen findAll()====");
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		List<Department> list = new ArrayList<>();
		list = departmentDao.findAll();
		list.forEach(e -> System.out.println(e));
		
		System.out.printf("%n==== Test 02: departmen findById()====%n");
		System.out.println(departmentDao.findById(1));
		
		
	}

}
