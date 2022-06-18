package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("=== Test 1: seller by id ===");
		Seller seller = sellerDao.findById(1);
		System.out.println(seller);

		System.out.printf("%n=== Test 2: seller find by department ===%n");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		list.forEach(e -> System.out.println(e));

	}

}
