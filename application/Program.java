package application;

import java.util.Date;
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

		System.out.printf("%n=== Test 3: seller find all ===%n");
		list = sellerDao.findAll();
		list.forEach(e -> System.out.println(e));

		System.out.printf("%n=== Test 4: seller find all ===%n");
		Seller seller01 = new Seller(null, "Nathanael Lopes Vieira", "nathanael@eniac.edu.br", new Date(), 3000.0,
				department);
		sellerDao.insert(seller01);
		System.out.println("Inserted: New id = " + seller01.getId());

		System.out.printf("%n=== Test 5: seller update ===%n");
		seller = sellerDao.findById(1);
		seller.setName("Valmir Bento Vieira");
		sellerDao.update(seller);
		System.out.println("Update completed");

	}

}
