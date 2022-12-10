package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        Department obj = new Department(1, "books");

        System.out.println(obj);

        Seller seller = new Seller(21, "bob", "bob@gmail.com", new Date(), 3000.00, obj);

        SellerDao sellerdao = DaoFactory.createSellerDao();
        

        System.out.println(seller);
    }
}
