package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Integer option = 0;

        SellerDao sellerDao = DaoFactory.createSellerDao();


            try (Scanner input = new Scanner(System.in)) {

                do {
                    System.out.print("type the seller id to check their informations: ");
                    Integer id = input.nextInt();
                    Seller seller = sellerDao.findById(id);
                    System.out.println(seller);
                    System.out.println();
                    System.out.print("Would you like to exit ? ( 0 -> yes || any number to check another one): ");
                    option = input.nextInt();
                } while (option != 0);

            } catch(InputMismatchException e) {
                e.printStackTrace();
            }

    }
}
