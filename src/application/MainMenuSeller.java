package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class MainMenuSeller {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);

		SellerDao sellerDao = DaoFactory.createSellerDao();

		char op;

		do {
			System.out.println("1 - SELLER FIND BY ID");
			System.out.println("2 - SELLER FIND BY DEPARTMENT");
			System.out.println("3 - SELLER FIND ALL");
			System.out.println("4 - SELLER INSERT");
			System.out.println("5 - SELLER UPDATE");
			System.out.println("6 - SELLER DELETE");
			System.out.println("7 - CLOSE");
			System.out.print("CHOOSE AN OPTION: ");
			int option = sc.nextInt();

			System.out.println();

			switch (option) {
			case 1: {
				System.out.print("ENTER WITH SELLER ID: ");
				int id = sc.nextInt();

				Seller seller = sellerDao.findById(id);

				System.out.println("-----------------------------------------------------");

				System.out.println(seller);

				break;
			}

			case 2: {
				System.out.print("ENTER WITH DEPARTMENT ID FOR SEARCH SELLER: ");
				int id = sc.nextInt();

				Department dep = new Department(id, null);

				List<Seller> list = sellerDao.findByDepartment(dep);

				System.out.println("--------------------------------------------------");

				for (Seller obj : list) {
					System.out.println("\n" + obj);
				}
			}

			case 3: {
				List<Seller> list = sellerDao.findAll();
				for (Seller obj : list) {
					System.out.println("\n" + obj);
				}
			}

			case 4: {
				System.out.println("### ENTER SELLER DATE FOR INSERT ###");
				System.out.println();
				System.out.print("SELLER NAME: ");
				sc.nextLine();
				
				String name = sc.nextLine();
				System.out.print("SELLER EMAIL: ");
				
				String email = sc.next();
				System.out.print("SELLER BITRH DATE(dd/MM/yyyy): ");
				
				Date birthDate = sdf.parse(sc.next());
				System.out.print("SELLER BASE SALARY: U$");
				
				double baseSalary = sc.nextDouble();
				System.out.print("SELLER DEPARTMENT ID: ");
				int sellerDepId = sc.nextInt();
				
				System.out.print("SELLER DEPARTMENT NAME: ");
				String sellerDepName = sc.next();

				Department dep = new Department(sellerDepId, sellerDepName);

				Seller seller = new Seller(null, name, email, birthDate, baseSalary, dep);

				sellerDao.insert(seller);
				
				System.out.println("------------------------------------------------------------");
				System.out.println("SELLER INSERTED!" + "\n");
				
				System.out.println(seller);
				
				break;
			}
			
			case 5: {
				System.out.println("### ENTER SELLER DATE FOR UPDATE ###");
				
				System.out.println();
				System.out.print("ENTER SELLER ID: ");
				int id = sc.nextInt();
				
				Seller seller = sellerDao.findById(id);
				
				System.out.print("SELLER NAME: ");
				sc.nextLine();
				String name = sc.nextLine();
				
				seller.setName(name);
				
				System.out.print("SELLER EMAIL: ");
				String email = sc.next();
				
				seller.setEmail(email);

				System.out.print("SELLER BITRH DATE(dd/MM/yyyy): ");
				Date birthDate = sdf.parse(sc.next());
				
				seller.setBirthDate(birthDate);
				
				System.out.print("SELLER BASE SALARY: U$");
				double baseSalary = sc.nextDouble();
				
				seller.setBaseSalary(baseSalary);
				
				System.out.print("SELLER DEPARTMENT ID: ");
				int sellerDepId = sc.nextInt();
				
				seller.getDepartment().setId(sellerDepId);
				
				System.out.print("SELLER DEPARTMENT NAME: ");
				String sellerDepName = sc.next();
				
				seller.getDepartment().setName(sellerDepName);
				
				sellerDao.update(seller);
				
				System.out.println("----------------------------------------------------------");
				
				System.out.println("SELLER UPDATE!" + "\n");
				
				System.out.println(seller);
				
				break;
			}
			
			case 6: {
				System.out.println("### ENTER SELLER ID FOR DELETE ###");
				System.out.print("SELLER ID: ");
				int id = sc.nextInt();
				
				sellerDao.deleteById(id);
				
				System.out.println("-----------------------------------------------------------");
				System.out.println("SELLER DELETED!");
				
				break;
			}

			case 7: {
				System.out.println("THANK YOU FOR USING OUR SERVICE!");
				break;
			}

			default:
				System.out.println("INVALID OPTION!");
			}

			System.out.println();

			System.out.print("DO YOU WANT TO RETURN TO THE MAIN MENU: ");
			op = sc.next().charAt(0);

			System.out.println();
		} while (op == 'y');

		sc.close();
	}
}
