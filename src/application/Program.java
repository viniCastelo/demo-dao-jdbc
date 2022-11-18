package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        // SELLER OPERATIONS:
        /*
        SellerDao sellerDao = DaoFactory.createSellerDao();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        */

        // FindById:
        /*
        System.out.println();

        System.out.println("=== TESTE 01 - Seller FindById ===");
        System.out.print("Buscar pelo código do vendedor: ");
        Seller seller = sellerDao.findById(sc.nextInt());
        sc.nextLine();

        System.out.println();

        System.out.println(seller);

        System.out.println();
        */

        // FindByDepartment:
        /*
        System.out.println("=== TESTE 02 - Seller FindByDepartment ===");
        System.out.print("Buscar vendedor pelo código do departamento: ");
        Department department = new Department(sc.nextInt());
        sc.nextLine();

        List<Seller> list = sellerDao.findByDepartment(department);
        System.out.println();
        list.forEach(System.out::println);

        System.out.println();
        */

        // FindAll:
        /*
        System.out.println("=== TESTE 03 - Seller FindAll ===");
        list = sellerDao.findAll();
        System.out.println();
        list.forEach(System.out::println);

        System.out.println();
        */

        // Insert:
        /*
        System.out.println("=== TESTE 04 - Seller Insert ===");
        System.out.print("Digite o nome do vendedor: ");
        String name = sc.nextLine();
        System.out.print("Digite o e-mail: ");
        String email = sc.nextLine();
        System.out.print("Digite a data de nascimento (dd/mm/aaaa): ");
        Date birthDate = new Date(sdf.parse(sc.nextLine()).getTime());
        System.out.print("Digite o salário base: ");
        Double baseSalary = sc.nextDouble();
        System.out.print("Digite o código do departamento: ");
        Integer DepartmentId = sc.nextInt();
        Department dp = new Department(DepartmentId);
        Seller sl = new Seller(null, name, email, birthDate, baseSalary, dp);
        sellerDao.insert(sl);
        */

        // Update:
        /*
        System.out.println("=== TESTE 05 - Seller Update ===");
        System.out.print("Digite o código do vendedor: ");
        Integer id = sc.nextInt();
        sc.nextLine();
        System.out.print("Digite o nome do vendedor: ");
        String name = sc.nextLine();
        System.out.print("Digite o e-mail: ");
        String email = sc.nextLine();
        System.out.print("Digite a data de nascimento (dd/mm/aaaa): ");
        Date birthDate = new Date(sdf.parse(sc.nextLine()).getTime());
        System.out.print("Digite o salário base: ");
        Double baseSalary = sc.nextDouble();
        System.out.print("Digite o código do departamento: ");
        Integer DepartmentId = sc.nextInt();
        Department dp = new Department(DepartmentId);
        Seller sl = new Seller(id, name, email, birthDate, baseSalary, dp);
        sellerDao.update(sl);

        System.out.println();

        System.out.println("Os dados do vendedor de código (" + sl.getId() + ") foi alterado!");
    */

        // Delete:
        /*
        System.out.println("=== TESTE 05 - Seller Delete ===");
        System.out.print("Digite o código do vendedor: ");
        Integer id = sc.nextInt();
        sc.nextLine();
        sellerDao.deleteById(id);

        System.out.println();

        System.out.println("Vendedor deletado com sucesso!");
        */

        // DEPARTMENT OPERATIONS:

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();


        // FindById:
        /*
        System.out.printLn("TESTE 01 - Department findById:");
        System.out.print("Informe o código do departamento: ");
        Department dp = departmentDao.findById(sc.nextInt());
        sc.nextLine();
        System.out.println();
        System.out.println(dp);
        */

        // FindAll:
        /*
        System.out.println("TESTE 02 - Department findAll:");
        System.out.println();
        List<Department> list = departmentDao.findAll();
        list.forEach(System.out::println);
        */

        // Insert:
        /*
        System.out.println("TESTE 03 - Department Insert:");
        System.out.println();
        System.out.print("Digite o nome do novo departamento: ");
        Department obj = new Department(sc.nextLine());
        departmentDao.insert(obj);
        System.out.println("Departamento criado com sucesso:");
        System.out.println(obj);
        */

        // Delete:
        /*
        System.out.println("TESTE 03 - Department Delete:");
        System.out.println();
        System.out.print("Digite o código do departamento: ");
        departmentDao.deleteById(sc.nextInt());
        sc.nextLine();
        System.out.println("Departamento excluido!");
        List<Department> list = departmentDao.findAll();
        System.out.println();
        list.forEach(System.out::println);
        */

        // Update:

        System.out.println("TESTE 05 - Department Update:");
        System.out.println();
        List<Department> list = departmentDao.findAll();
        list.forEach(System.out::println);
        System.out.println();
        System.out.print("Informe o código do departamento: ");
        Integer id = sc.nextInt();
        sc.nextLine();
        System.out.print("Informe o nome do departamento: ");
        Department obj = new Department(id, sc.nextLine());
        departmentDao.update(obj);
        list = departmentDao.findAll();
        System.out.println();
        System.out.println("Departamento (" + obj.getId() + "-" + obj.getName() + ") Alterado com sucesso:");
        System.out.println();
        System.out.println("Lista atualizada:");
        list.forEach(System.out::println);

        sc.close();
    }
}