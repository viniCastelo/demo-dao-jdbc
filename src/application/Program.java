package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.print("Buscar o vendedor pelo id: ");
        Seller seller = sellerDao.findById(sc.nextInt());
        System.out.println("{" + seller + "}");
        sc.close();
    }
}