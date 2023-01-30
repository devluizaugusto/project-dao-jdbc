package application;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
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
				
				for(Seller obj : list) {
					System.out.println(obj);
					System.out.println();
				}
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
		}while(op == 'y');
		
		sc.close();
	}
}
