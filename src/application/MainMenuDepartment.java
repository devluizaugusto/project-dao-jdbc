package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class MainMenuDepartment {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

		char op;

		do {
			System.out.println("1 - DEPARTMENT FIND BY ID");
			System.out.println("2 - DEPARTMENT FIND ALL");
			System.out.println("3 - DEPARTMENT INSERT");
			System.out.println("4 - DEPARTMENT UPDATE");
			System.out.println("5 - DEPARTMENT DELETE");
			System.out.println("6 - CLOSE");
			System.out.print("CHOOSE AN OPTION: ");
			int option = sc.nextInt();

			System.out.println();

			switch (option) {
			case 1: {
				System.out.print("ENTER WITH DEPARTMENT ID: ");
				int id = sc.nextInt();

				Department dep = departmentDao.findById(id);

				System.out.println("-----------------------------------------------------");

				System.out.println(dep);

				break;
			}
			
			case 2: {
				List<Department> list = departmentDao.findAll();
				for (Department dep : list) {
					System.out.println("\n" + dep);
				}
			}
			
			case 3: {
				System.out.println("### ENTER DEPARTMENT DATE FOR INSERT ###");
				System.out.println();
				System.out.print("DEPARTMENT NAME: ");
				String name = sc.next();
				
				Department dep = new Department(null, name);
				
				departmentDao.insert(dep);
				
				System.out.println("------------------------------------------------------------");
				System.out.println("DEPARTMENT INSERTED!" + "\n");
				
				System.out.println(dep);
				
				break;
			}
		}
			System.out.println();

			System.out.print("DO YOU WANT TO RETURN TO THE MAIN MENU: ");
			op = sc.next().charAt(0);

			System.out.println();
		} while (op == 'y');

		sc.close();
	}
}
