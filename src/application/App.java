package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("************* TEST 1: seller findById *************");
        Seller seller = sellerDao.findById(7);
        System.out.println(seller);

        System.out.println();

        System.out.println("************* TEST 2: seller findByDepartment *************");
//      Suggestion to do it just by department ID
        List<Seller> sellers = sellerDao.findByDepartment(new Department(2, null));
        for(Seller item : sellers) {
            System.out.println(item);
        }

        System.out.println();

        System.out.println("************* TEST 3: seller findAll *************");
        List<Seller> allSellers = sellerDao.findAll();
        for(Seller item : allSellers) {
            System.out.println(item);
        }

    }
}
