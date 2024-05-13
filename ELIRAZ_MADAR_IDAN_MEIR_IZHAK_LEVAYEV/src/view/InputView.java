package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.IsExpress;
import model.Person;

public class InputView {
	private static Scanner scanner = new Scanner(System.in);

	public static String getStringInput(String message) {
		System.out.println(message);
		return scanner.nextLine();
	}

	public static int getIntInput(String message) {
	    System.out.println(message);
	    while (true) {
	        try {
	            String input = scanner.nextLine();
	            int number = Integer.parseInt(input);
	            // Input is a valid integer
	            return number;
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid input. Please enter a valid integer.");
	        }
	    }
	}
	
	public String getOrderDateFromUserInput() {
		System.out.println("Enter order date: (dd/mm/yyyy)");
		String orderDate = InputView.getStringInput("Enter order date: (dd/mm/yyyy)");
		Pattern pattern = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
		Matcher matcher = pattern.matcher(orderDate);
		if (!matcher.find()) {
			System.out.println("Invalid date format");
			return null;
		}
		return orderDate;
	}

	public static IsExpress getIsExpressInput(String message) {
		System.out.println(message);
		String input = scanner.nextLine();
		if (input.equals("yes")) {
			return IsExpress.EXPRESS;
		} else if (input.equals("no")) {
			return IsExpress.REGULAR;
		} else {
			return null;
		}
	}

	public static String readLineFromFile(File file) {
		if (file.exists()) {
			try {
				Scanner scanner = new Scanner(file);
				String content = "";
				if (scanner.hasNextLine()) {
					content += scanner.nextLine();
				}
				scanner.close();
				return content;
			} catch (FileNotFoundException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	public static String getMobileFromInput(String string) {
		do {
			System.out.println(string);
			String mobile = scanner.nextLine();
			if (Person.isValidMobile(mobile)) {
				return mobile;
			} else {
				System.out.println("Not a valid mobile number!");
				System.out.println("A valid mobile number should be in the format 05X-XXXXXXX.");
			}
		} while (true);
	}

	public static String getProductTypeInput(String string) {
		String productType = "";
		do {
			System.out.println(string);
			System.out.println("Please choos one of the following values:");
			System.out.println("1. Sold through website");
			System.out.println("2. sold in store");
			System.out.println("3. sold to wholesalers");
			productType = scanner.nextLine();
			
			if (productType.equals("1")) {
				return  productType;
			} else if (productType.equals("2")) {
				return productType;
			} else if (productType.equals("3")) {
				return productType;
			} else {
				System.out.println("Invalid input. Please try again.");
			}		
		} while (true);
	}
}