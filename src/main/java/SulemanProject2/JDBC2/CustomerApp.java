package SulemanProject2.JDBC2;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.jdbcrad.programs.Customer;
import com.jdbcrad.programs.CustomerDAOImplementation;
import com.jdbcrad.programs.CustomerDaoInterface;
import com.jdbcrad.programs.CustomerUtil;

public class CustomerApp {
	Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws ParseException, IOException, SQLException {
		int choice;

		CustomerApp customerApp = new CustomerApp();
		CustomerDaoInterface customerDao = new CustomerDAOImplementation();
		Scanner scanner = new Scanner(System.in);
		while(true){
		choice = customerApp.menu();
		switch (choice) {
		case 1: {
			Customer customer = customerApp.addCustomer();

			boolean flag = customerDao.createCustomer(customer);
			if (flag) {
				System.out.println("Customer Data inserted successfully!!");

			} else {
				System.out.println("Data insertion unsuccessful.");
			}
			break;
		}

		case 2: {
			List<Customer> customers = customerDao.viewAllCustomersData();
			if (customers.isEmpty()) {
				System.out.println("No employees found!");
			}else{
				System.out.println(customers.size()+" Customers found");
			for(Customer customer : customers){
				System.out.println(customer);
			}
			}
			break;
		}

		case 3: {
			System.out.println("Enter the ID of the customer to update");
			int oldID = scanner.nextInt();
			Customer customer = customerApp.addCustomer();
			Boolean flag = customerDao.updateCustomer(customer, oldID);
			if (flag) {
				System.out.println("Updated successfully.");
			}
			else{
				System.out.println("Invalid ID");
			}
			break;
		}
		
		case 4: {
			System.out.println("Enter the ID of the customer you want to delete");
			Boolean flag = customerDao.deleteCustomer(scanner.nextInt());
			if (flag) {
				System.out.println("Deleted successfully!");
			}
			else{
				System.out.println("ID doesnt exist");
			}
			break;
		}

		case 5: {
			System.out.println("Enter the ID of the Employee");
			List<Customer> customers = customerDao.viewCustomerByID(scanner.nextInt());
			if (customers.isEmpty()) {
				System.out.println("No employees found!");
			}else{
				System.out.println(customers.size()+" Customers found");
			for(Customer customer : customers){
				System.out.println(customer);
			}
			}
			break;
		}

		case 6: {
			System.out.println("Enter the name of the Employee");
			List<Customer> customers = customerDao.viewAllCustomers(scanner.next());
			if (customers.isEmpty()) {
				System.out.println("No employees found!");
			}else{
				System.out.println(customers.size()+" Customers found");
			for(Customer customer : customers){
				System.out.println(customer);
			}
			}
			break;
		}
		
		case 7: {
			System.out.println("Printing all customers sorted by name.");
			List<Customer> customersByName = customerDao.viewCustomerByName();
//			customersByName.forEach(p->System.out.println(p));
			break;
		}

		case 8: {
//			List<Customer> customers = customerDao.viewAllCustomersData();
//			Comparator<Customer> comparator = (s1,s2) -> {
//				if (s1.getCreditLimit()>s2.getCreditLimit()) {
//					return 1;
//				};
//				
//				if (s1.getCreditLimit()<s2.getCreditLimit()) {
//					return -1;
//				};
//				return 0;
//				
//			};
//			// Reversing the list so it doesn't return two statements
//			customers.sort(comparator.reversed());
//			CustomerUtil customerUtil = new CustomerUtil(customers);
//			CustomerUtil.HighCredit iterator1 = customerUtil.new HighCreditClass();
//			
//			while (iterator1.hasNext()) {
//				System.out.println(iterator1.next());
//			}
		}
		case 9: {
			scanner.close();
			System.exit(0);
			break;
		}

		default: {
			System.out.println("Enter number between [1-7]");
			break;
		}
		}
	}
}
	private Customer addCustomer() throws ParseException {

		System.out.println("Enter id.");
		int ID = scanner.nextInt();

		System.out.println("Enter name.");
		String name = scanner.next();

		System.out.println("Enter the address.");
		String address = scanner.next();

		System.out.println("Enter creditLimit.");
		float creditLimit = scanner.nextFloat();

		System.out.println("Enter enrollmentDate (MM/DD/YYYY).");
		Date enrollmentDate = CustomerApp.parseDate(scanner.next());

		return new Customer(ID, name, address, creditLimit, enrollmentDate);
	}

	private static Date parseDate(String date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/DD/YYYY");

		return simpleDateFormat.parse(date);
	}

	private int menu() {
		System.out.println("*****************CUSTOMER MANAGEMENT SYSTEM*****************");
		System.out.println("1.Add Customer\n2.View all customers\n3.Update Customer\n"
				+ "4.Delete customer\n5.View customer by ID\n6. View customer by Name\n7.View All customers by sorted by Name\n8.Highest credit Limit Customer\n9.Exit");
		System.out.println("************************************************************");
		System.out.println("Enter your choice between [1-7]");
		return scanner.nextInt();

	}
}
