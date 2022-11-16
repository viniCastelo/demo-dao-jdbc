package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println();

        System.out.println("=== TESTE 01 - Seller FindById ===");
        System.out.print("Buscar pelo código do vendedor: ");
        Seller seller = sellerDao.findById(sc.nextInt());
        sc.nextLine();

        System.out.println();

        System.out.println(seller);

        System.out.println();

        System.out.println("=== TESTE 02 - Seller FindByDepartment ===");
        System.out.print("Buscar vendedor pelo código do departamento: ");
        Department department = new Department(sc.nextInt());

        List<Seller> list = sellerDao.findByDepartment(department);
        System.out.println();
        list.forEach(System.out::println);

        list.clear();

        System.out.println();

        System.out.println("=== TESTE 03 - Seller FindAll ===");
        list = sellerDao.findAll();
        System.out.println();
        list.forEach(System.out::println);

        sc.close();
    }
}