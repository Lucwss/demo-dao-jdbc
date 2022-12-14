package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

//        SellerDao sellerDao = DaoFactory.createSellerDao();
//
//        System.out.println("************* TEST 1: seller findById *************");
//        Seller seller = sellerDao.findById(7);
//        System.out.println(seller);
//
//        System.out.println();
//
//        System.out.println("************* TEST 2: seller findByDepartment *************");
////      Suggestion to do it just by department ID
//        Department dep = new Department(2, null);
//        List<Seller> sellers = sellerDao.findByDepartment(dep);
//        for(Seller item : sellers) {
//            System.out.println(item);
//        }
//
//        System.out.println();
//
//        System.out.println("************* TEST 3: seller findAll *************");
//        List<Seller> allSellers = sellerDao.findAll();
//        for(Seller item : allSellers) {
//            System.out.println(item);
//        }
//
//        System.out.println();
//
//        System.out.println("************* TEST 4: seller insert *************");
//        Seller newSeller = new Seller(null, "Lucas", "lucas@gmail.com", new Date(), 4500.00, dep);
//        sellerDao.insert(newSeller);
//        System.out.println("Inserted! new Id = " + newSeller.getId());
//
//        System.out.println();
//
//        System.out.println("************* TEST 5: seller update *************");
//        seller = sellerDao.findById(8);
//        seller.setName("Fausto Silva");
//        seller.getDepartment().setId(1);
//        sellerDao.update(seller);
//        System.out.println("The id " + seller.getId() + " was updated!");
//
//        System.out.println();
//
//        System.out.println("************* TEST 6: seller delete *************");
//        Integer id = 5;
//        seller = sellerDao.findById(id);
//        if(seller == null) System.out.println("The id dosen't exist");
//        else {
//            sellerDao.deleteById(id);
//            System.out.println("The id " + id + " was deleted!");
//        }

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        System.out.println("************* TEST 1: department findById *************");
        Department dep = departmentDao.findById(2);

        System.out.println(dep);

        System.out.println();

        System.out.println("************* TEST 2: department findAll *************");
        List<Department> departments = departmentDao.findAll();
        for(Department item : departments) {
            System.out.println(item);
        }

        System.out.println();

    }
}
