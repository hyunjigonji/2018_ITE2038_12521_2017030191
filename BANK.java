package DBS_Assignment;

import java.sql.*;
import java.util.*;

public class BANK {
	public static void main(String args[]) {
		try {
			//1. Load JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

			//2. Open DBMS connection
			String url = "jdbc:mysql://localhost:3306/bank?characterEncoding=UTF-8&serverTimezone=UTC";
			String user = "root";
			String password = "";
			Connection connection = DriverManager.getConnection(url, user, password);

			System.out. println("[ HyunjiGonji Bank ]");

			while(true) {
				//Print menu
				System.out.println("1. Manager Menu");
				System.out.println("2. Customer Menu");
				System.out.println("3. Exit");
				System.out.print("Input: ");

				Scanner keyboard = new Scanner(System.in);
				int number1 = keyboard.nextInt();
				//-------------------------------------------------------------------------------------
				if(number1 == 3) { // Exit
					System.out.println();
					System.out.println("Bye.");
					keyboard.close();
					System.exit(0);
				}
				//-------------------------------------------------------------------------------------
				if(number1 == 1) { //Manager Menu
					while(true) {
						System.out.println(">> Manager Menu");
						System.out.println("1. Manager Management");
						System.out.println("2. Bank Management");
						System.out.println("3. Return to previous menu");
						System.out.println("4. Exit");
						System.out.print("Input: ");

						int number2 = keyboard.nextInt();
						//-------------------------------------------------------------------------------------
						if(number2 == 3) { //return to previous menu
							break;
						}
						//-------------------------------------------------------------------------------------
						if(number2 == 4){ // exit
							System.out.println();
							System.out.println("Bye.");
							keyboard.close();
							System.exit(0);
						}
						//-------------------------------------------------------------------------------------
						while(true) {
							if(number2 == 1) { //manager management
								System.out.println(">> Manager Management Menu");
								System.out.println("1. Register the new manager");
								System.out.println("2. Remove the manager");
								System.out.println("3. Modify the manager information");
								System.out.println("4. Return to previous menu");
								System.out.println("5. Exit");
								System.out.print("Input: ");

								int number3 = keyboard.nextInt();
								//-------------------------------------------------------------------------------------
								if(number3 == 4){ //return to previous menu
									break;
								}
								//-------------------------------------------------------------------------------------
								if(number3 == 5) { //exit
									System.out.println();
									System.out.println("Bye.");
									keyboard.close();
									System.exit(0);
								}
								while(true) {
									//-------------------------------------------------------------------------------------
									if(number3 == 1) { // register the new manager
										System.out.println("What is your ssn? (Your birthday date in 6 digits.)");
										System.out.println("If you want to return to previous menu, enter 9999.");
										System.out.print("Input: ");
										int Ssn = keyboard.nextInt();
										if(Ssn == 9999) break;

										Statement statement = connection.createStatement();
										ResultSet resultset = statement.executeQuery("SELECT Name FROM MANAGER WHERE Ssn = " + Ssn);
										if(resultset.isBeforeFirst()) {
											System.out.println("You are already our manager.");
											break;
										}

										System.out.println("What is your name? (First name Only)");
										System.out.print("Input: ");
										String Name = keyboard.next();

										System.out.println("What is your contact? (Not including 010)");
										System.out.print("Input: ");
										int Contact = keyboard.nextInt();

										System.out.println("How much salary do you get for a month? (won)");
										System.out.print("Input: ");
										int Salary = keyboard.nextInt();

										System.out.println("How many time do you work for a week?");
										System.out.print("Input: ");
										int WorkTime = keyboard.nextInt();

										System.out.println("Enter your password in 6 digits.");
										System.out.print("Input: ");
										int Password = keyboard.nextInt();

										statement.executeUpdate("insert into MANAGER values (" + Ssn + ", \"" + Name + "\", " + Contact + ", " + Salary + ", " + WorkTime + ", " + Password + ");");

										System.out.println("Thank you for working at HyunjiGonji Bank!");
										break;
									}
									//-------------------------------------------------------------------------------------
									if(number3 == 2) { // remove the manager
										System.out.println("What is your ssn? (Your birthday date in 6 digits)");
										System.out.println("If you want to return to previous menu, enter 9999.");
										System.out.print("Input: ");
										int Ssn = keyboard.nextInt();
										if(Ssn == 9999) break;
										Statement statement = connection.createStatement();
										ResultSet resultset = statement.executeQuery("SELECT Name FROM MANAGER WHERE Ssn = " + Ssn);

										String ManagerName = "";
										if(!resultset.isBeforeFirst()) {
											System.out.println("manager not found");
											break;
										}

										while(resultset.next()) {
											ManagerName = resultset.getString("Name");
										}
										resultset = statement.executeQuery("SELECT Password FROM MANAGER WHERE Ssn = " + Ssn);
										int ManagerPassword = 0;
										while(resultset.next()) {
											ManagerPassword = resultset.getInt(1);
										}

										System.out.println("Hi, " + ManagerName + "! :)");

										while(true) {
											System.out.println("Enter your password in 6 digits.");
											System.out.println("If you want to return to previous menu, enter 9999.");
											System.out.print("Input: ");
											int Password = keyboard.nextInt();
											if(Password == 9999) break;
											if(Password == ManagerPassword) {
												statement.executeUpdate("DELETE FROM MANAGER WHERE Ssn = " + Ssn);
												System.out.println("Your information is removed.");
												break;
											}
											else {
												System.out.println("Password you entered is wrong.");
												System.out.println("Try again.");
												continue;
											}
										}
										break;
									}
									//-------------------------------------------------------------------------------------
									if(number3 == 3) { // modify the manager
										System.out.println("What is your ssn? (Your birthday date in 6 digits)");
										System.out.println("If you want to return to previous menu, enter 9999.");
										System.out.print("Input: ");
										int Ssn = keyboard.nextInt();
										if(Ssn == 9999) break;

										Statement statement = connection.createStatement();
										ResultSet resultset;
										resultset = statement.executeQuery("SELECT Name FROM MANAGER WHERE Ssn = " + Ssn);
										String ManagerName = "";
										if(!resultset.isBeforeFirst()) {
											System.out.println("manager not found");
											break;
										}

										while(resultset.next()) {
											ManagerName = resultset.getString("Name");
										}
										resultset = statement.executeQuery("SELECT Password FROM MANAGER WHERE Ssn = " + Ssn);
										int Password = 0;
										while(resultset.next()) {
											Password = resultset.getInt(1);
										}

										System.out.println("Hi, " + ManagerName + "! :)");
										System.out.println("Which information do you want to modify?");
										System.out.println("1. Contact");
										System.out.println("2. Salary");
										System.out.println("3. WorkTime");
										System.out.println("4. Password");
										System.out.println("5. Return to previous menu");
										System.out.println("6. Exit");
										System.out.print("Input: ");

										int number4 = keyboard.nextInt();
										//-------------------------------------------------------------------------------------
										if(number4 == 5) { //return to previous menu
										break;
										}
										//-------------------------------------------------------------------------------------
										if(number4 == 6) { //exit
											System.out.println();
											System.out.println("Bye.");
											keyboard.close();
											System.exit(0);
										}
										while(true) {
											//-------------------------------------------------------------------------------------
											if(number4 == 1) { // modify contact
												System.out.println("Enter your new contact. (Not including 010)");
												System.out.println("If you want to return to previous menu, enter 9999.");
												System.out.print("Input: ");
												int Contact = keyboard.nextInt();
												if(Contact == 9999) break;
												statement.executeUpdate("UPDATE MANAGER Set Contact = " + Contact + " WHERE Ssn = " + Ssn);
												System.out.println("Your contact is updated to " + Contact + ".");
												break;
											}
											//-------------------------------------------------------------------------------------
											if(number4 == 2) { //modify salary
												System.out.println("Enter your new salary. (won)");
												System.out.println("If you want to return to previous menu, enter 9999.");
												System.out.print("Input: ");
												int Salary = keyboard.nextInt();
												if(Salary == 9999) break;
												statement.executeUpdate("UPDATE MANAGER Set Salary = " + Salary + " WHERE Ssn = " + Ssn);
												System.out.println("Your salary is updated to " + Salary + "won.");
												break;
											}
											//-------------------------------------------------------------------------------------
											if(number4 == 3) { //modify worktime
												System.out.println("Enter your new work time.");
												System.out.println("If you want to return to previous menu, enter 9999.");
												System.out.print("Input: ");
												int WorkTime = keyboard.nextInt();
												if(WorkTime == 9999) break;
												statement.executeUpdate("UPDATE MANAGER Set WorkTime = " + WorkTime + " WHERE Ssn = " + Ssn);
												System.out.println("Your work time is updated to " + WorkTime + "hours.");

												break;
											}
											//-------------------------------------------------------------------------------------
											if(number4 == 4) { //modify password
												while(true) {
													System.out.println("Enter your original password in 6 digits.");
													int Password2 = keyboard.nextInt();
													if(Password2 == Password) {
														System.out.println("Enter your new password in 6 digits.");
														int Password3 = keyboard.nextInt();
														statement.executeUpdate("UPDATE MANAGER Set Password = " + Password3 + " WHERE Ssn = " + Ssn);
														System.out.println("Your password is updated to " + Password3 + ".");
														break;
													}
													else {
														System.out.println("Password you entered is wrong.");
														System.out.println("Try again.");
														System.out.println();
													}
												}
												break;
											}
										}
										break;
									}
								}
							}
							//-------------------------------------------------------------------------------------
							if(number2 == 2) { //bank management menu
								System.out.println(">> Bank Managment Menu");
								System.out.println("1. User List");
								System.out.println("2. Manager List");
								System.out.println("3. Total Money");
								System.out.println("4. Return to previous menu");
								System.out.println("5. Exit");
								System.out.print("Input: ");

								int number3 = keyboard.nextInt();
								//-------------------------------------------------------------------------------------
								if(number3 == 4) { // return to previous menu
									break;
								}
								//-------------------------------------------------------------------------------------
								if(number3 == 5){ // exit
									System.out.println();
									System.out.println("Bye.");
									keyboard.close();

									System.exit(0);
								}
								//-------------------------------------------------------------------------------------
								while(true) {
									if(number3 == 1) { // user list
										Statement statement = connection.createStatement();
										ResultSet resultset = statement.executeQuery("SELECT Name, Contact FROM CUSTOMER");
										System.out.println("------------------");
										System.out.println("  Name  | Contact ");
										System.out.println("------------------");
										while(resultset.next()) {
											String Name = resultset.getString("Name");
											int Contact = resultset.getInt(2);
											System.out.printf("%7s | %8d\n", Name, Contact);
										}
										System.out.println("------------------");
										break;
									}
									//-------------------------------------------------------------------------------------
									if(number3 == 2) { // manager list
										Statement statement = connection.createStatement();
										ResultSet resultset = statement.executeQuery("SELECT Name, Contact, Salary, WorkTime FROM MANAGER");
										System.out.println("----------------------------------------");
										System.out.println("  Name  | Contact |  Salary  | WorkTime");
										System.out.println("----------------------------------------");
										while(resultset.next()) {
											String Name = resultset.getString("Name");
											int Contact = resultset.getInt(2);
											int Salary = resultset.getInt(3);
											int WorkTime = resultset.getInt(4);
											System.out.printf("%8s|%9d|%10d|%8d\n", Name, Contact, Salary, WorkTime);
										}
										System.out.println("----------------------------------------");
										break;
									}
									//-------------------------------------------------------------------------------------
									if(number3 == 3) { // total money
										Statement statement = connection.createStatement();
										ResultSet resultset = statement.executeQuery("SELECT SUM(Money) FROM ACCOUNT");

										int TotalMoney = 0;
										while(resultset.next()) {
											TotalMoney = resultset.getInt(1);
										}
										System.out.println("Total money in HyunjiGonji Bank is " + TotalMoney + "won.");
										break;
									}
								}
							}
						}
					}
				}
				//---------------------------------------------------------------------------------------------------------------------------------------------
				if(number1 == 2) { //Customer Menu
					while(true) {
						System.out.println(">> Customer Menu");
						System.out.println("1. Transfer");
						System.out.println("2. Customer Management");
						System.out.println("3. Account Management");
						System.out.println("4. Return to previous menu");
						System.out.println("5. Exit");
						System.out.print("Input: ");

						int number2 = keyboard.nextInt();
						//-------------------------------------------------------------------------------------
						if(number2 == 4){ // return
							break;
						}
						//-------------------------------------------------------------------------------------
						if(number2 == 5){ // exit
							System.out.println();
							System.out.println("Bye.");
							keyboard.close();
							System.exit(0);
						}
						//-------------------------------------------------------------------------------------
						while(true) {
							if(number2 == 1) { //transfer
								System.out.println(">> Transfer Menu");
								System.out.println("1. Put In");
								System.out.println("2. Withdrawal");
								System.out.println("3. Remittance");
								System.out.println("4. Return to previous menu");
								System.out.println("5. Exit");
								System.out.print("Input: ");

								int number3 = keyboard.nextInt();
								//-------------------------------------------------------------------------------------
								if(number3 == 4) { // return to previous menu
									break;
								}
								//-------------------------------------------------------------------------------------
								if(number3 == 5){ // exit
									System.out.println();
									System.out.println("Bye.");
									keyboard.close();
									System.exit(0);
								}
								//-------------------------------------------------------------------------------------
								while(true) {
									if(number3 == 1) { // deposit
										System.out.println("What is your account number? (Your birthday date in 6 digits)");
										System.out.println("If you want to return to previous menu, enter 9999.");
										System.out.print("Input: ");
										int Number = keyboard.nextInt();
										if(Number == 9999) break;

										Statement statement = connection.createStatement();
										ResultSet resultset;
										resultset = statement.executeQuery("SELECT Money FROM ACCOUNT WHERE Number = " + Number);
										int NowMoney = 0;
										if(!resultset.isBeforeFirst()) {
											System.out.println("account not found");
											break;
										}

										while(resultset.next()) {
											NowMoney = resultset.getInt(1);
										}

										System.out.println("How much do you want to put in?");
										System.out.print("Input: ");
										int Money = keyboard.nextInt();
										int TotalMoney = NowMoney + Money;
										statement.executeUpdate("UPDATE ACCOUNT SET Money = " + TotalMoney + " WHERE Number = " + Number);
										System.out.println("Your balance is " + TotalMoney + "won.");
										System.out.println("Successful deposit.");

										break;
									}
									//-------------------------------------------------------------------------------------
									if(number3 == 2) { // withdrawal
										System.out.println("What is your account number? (Your birthday date in 6 digits)");
										System.out.println("If you want to return to previous menu, enter 9999");
										System.out.print("Input: ");
										int Number = keyboard.nextInt();
										if(Number == 9999) break;

										Statement statement = connection.createStatement();
										ResultSet resultset;
										resultset = statement.executeQuery("SELECT Password FROM ACCOUNT WHERE Number = " + Number);
										if(!resultset.isBeforeFirst()) {
											System.out.println("account not found");
											break;
										}

										int Password = 0;
										while(resultset.next()) {
											Password = resultset.getInt(1);
										}
										resultset = statement.executeQuery("SELECT Money FROM ACCOUNT WHERE Number = " + Number);
										int NowMoney = 0;
										while(resultset.next()) {
											NowMoney = resultset.getInt(1);
										}

										while(true) {
											System.out.println("Enter the password.");
											System.out.print("Input: ");
											int CustomerPassword = keyboard.nextInt();
											if(CustomerPassword == Password) {
												while(true) {
													System.out.println("How much do you want to withdraw?");
													int Money = keyboard.nextInt();
													int TotalMoney = NowMoney - Money;
													if(TotalMoney < 0) {
														System.out.println("Your balnace is not enough.");
														System.out.println("Try again.");

														continue;
													}
													statement.executeUpdate("UPDATE ACCOUNT SET Money = " + TotalMoney + " WHERE Number = " + Number);
													System.out.println("Your balance is " + TotalMoney + "won.");
													System.out.println("Successful Withdraw.");
													break;
												}
											}
											else {
												System.out.println("Password you entered is wrong.");
												System.out.println("Try again.");
												System.out.println();
												continue;
											}
											break;
										}
										break;
									}
									//-------------------------------------------------------------------------------------
									if(number3 == 3) { // remittance
										System.out.println("What is your account number? (Your birthday date in 6 digits)");
										System.out.println("If you want to return to previous menu, enter 9999.");
										System.out.print("Input: ");
										int Number = keyboard.nextInt();
										if(Number == 9999) break;

										Statement statement = connection.createStatement();
										ResultSet resultset;
										resultset = statement.executeQuery("SELECT Password FROM ACCOUNT WHERE Number = " + Number);
										int Password = 0;
										if(!resultset.isBeforeFirst()) {
											System.out.println("account not found");
											break;
										}

										while(resultset.next()) {
											Password = resultset.getInt(1);
										}
										resultset = statement.executeQuery("SELECT Money FROM ACCOUNT WHERE Number = " + Number);
										int NowMoney = 0;
										while(resultset.next()) {
											NowMoney = resultset.getInt(1);
										}

										System.out.println("Enter the account number you want to remit.");
										System.out.println("If you want to return to previous menu, enter 9999.");
										System.out.print("Input: ");
										int AccountNum = keyboard.nextInt();
										if(AccountNum == 9999) break;
										resultset = statement.executeQuery("SELECT Money FROM ACCOUNT WHERE Number = " + AccountNum);
										int NowMoney2 = 0;
										if(!resultset.isBeforeFirst()) {
											System.out.println("account not found");
											break;
										}

										while(resultset.next()) {
											NowMoney2 = resultset.getInt(1);
										}

										while(true) {
											System.out.println("Enter the password.");
											int CustomerPassword = keyboard.nextInt();
											if(CustomerPassword == Password) {
												while(true) {
													System.out.println("How much do you want to remit?");
													int Money = keyboard.nextInt();
													int TotalMoney = NowMoney - Money;
													int TotalMoney2 = NowMoney2 + Money;
													if(TotalMoney < 0) {
														System.out.println("Your balnace is not enough.");
														System.out.println("Try again.");

														continue;
													}
													statement.executeUpdate("UPDATE ACCOUNT SET Money = " + TotalMoney + " WHERE Number = " + Number);
													statement.executeUpdate("UPDATE ACCOUNT SET Money = " + TotalMoney2 + " WHERE Number = " + AccountNum);
													System.out.println("Your balance is " + TotalMoney + "won.");
													System.out.println("Successful Remittance.");
													break;
												}
												break;
											}
											//Ʋ�� password�� ����
											else {
												System.out.println("Password you entered is wrong.");
												System.out.println("Try again.");
												System.out.println();
												continue;
											}
										}
										break;
									}
									break;
								}
							}
							//-------------------------------------------------------------------------------------
							if(number2 == 2) { // Customer management menu
								System.out.println(">> Customer Management Menu");
								System.out.println("1. Register the new customer");
								System.out.println("2. Remove the customer");
								System.out.println("3. Modify the customer information");
								System.out.println("4. Return to previous menu");
								System.out.println("5. Exit");
								System.out.print("Input: ");

								int number3 = keyboard.nextInt();
								//-------------------------------------------------------------------------------------
								if(number3 == 4) { // return to previous menu
									break;
								}
								//-------------------------------------------------------------------------------------
								if(number3 == 5){ // exit
									System.out.println();
									System.out.println("Bye.");
									keyboard.close();
									System.exit(0);
								}
								while(true) {
									//-------------------------------------------------------------------------------------
									if(number3 == 1) { // register new customer
										System.out.println("What is your ssn? (Your birthday date in 6 digits)");
										System.out.println("If you want to return to previous menu, enter 9999.");
										System.out.print("Input: ");
										int Ssn = keyboard.nextInt();
										if(Ssn == 9999) break;

										Statement statement = connection.createStatement();
										ResultSet resultset = statement.executeQuery("SELECT Name FROM CUSTOMER WHERE Ssn = " + Ssn);
										if(resultset.isBeforeFirst()) {
											System.out.println("You are already our customer.");
											break;
										}

										System.out.println("What is your name? (First name Only)");
										System.out.println("If you want to return to previous menu, enter 9999.");
										System.out.print("Input: ");
										String Name = keyboard.next();
										if(Name.compareTo("9999") == 0) break;

										System.out.println("What is your contact? (Not including 010)");
										System.out.println("If you want to return to previous menu, enter 9999.");
										System.out.print("Input: ");
										int Contact = keyboard.nextInt();
										if(Contact == 9999) break;

										System.out.println("Enter your password in 6 digits.");
										System.out.println("If you want to return to previous menu, enter 9999.");
										System.out.print("Input: ");
										int Password = keyboard.nextInt();
										if(Password == 9999) break;
										statement.executeUpdate("INSERT INTO CUSTOMER VALUES (" + Ssn + ", \"" + Name + "\", " + Contact + ", " + Password + ");");

										System.out.println("Thank you for joining in HyunjiGonji Bank!");
										break;
									}
									//-------------------------------------------------------------------------------------
									if(number3 == 2) { // remove the customer and the customer's account
										System.out.println("What is your ssn? (Your birthday date in 6 digits)");
										System.out.println("If you want to return to previous menu, enter 9999.");
										System.out.print("Input: ");
										int Ssn = keyboard.nextInt();
										if(Ssn == 9999) break;

										Statement statement = connection.createStatement();
										ResultSet resultset;
										resultset = statement.executeQuery("SELECT Password FROM CUSTOMER WHERE Ssn = " + Ssn);
										int Password = 0;
										if(!resultset.isBeforeFirst()) {
											System.out.println("customer not found");
											break;
										}

										while(resultset.next()) {
											Password = resultset.getInt(1);
										}

										while(true) {
											System.out.println("Enter the password.");
											int CustomerPassword = keyboard.nextInt();
											if(CustomerPassword == Password) {
												statement.executeUpdate("DELETE FROM CUSTOMER WHERE Ssn = " + Ssn);
												statement.executeUpdate("DELETE FROM ACCOUNT WHERE OwnerSsn = " + Ssn);

												System.out.println("Your information and account is removed.");
												break;
											}
											else {
												System.out.println("Password you entered is wrong.");
												System.out.println("Try again.");
												System.out.println();
												continue;
											}
										}
										break;
									}
									//-------------------------------------------------------------------------------------
									if(number3 == 3) { // modify the customer information
										System.out.println("What is your ssn? (Your birthday date in 6 digits)");
										System.out.println("If you want to return to previous menu, enter 9999.");
										System.out.print("Input: ");
										int Ssn = keyboard.nextInt();
										if(Ssn == 9999) break;

										Statement statement = connection.createStatement();
										ResultSet resultset;
										resultset = statement.executeQuery("SELECT Name FROM CUSTOMER WHERE Ssn = " + Ssn);
										String Name = "";
										if(!resultset.isBeforeFirst()) {
											System.out.println("customer not found");
											break;
										}

										while(resultset.next()) {
											Name = resultset.getString("Name");
										}
										resultset = statement.executeQuery("SELECT Password FROM CUSTOMER WHERE Ssn = " + Ssn);
										int Password = 0;
										while(resultset.next()) {
											Password = resultset.getInt(1);
										}

										System.out.println("Hi, " + Name + "! :)");
										System.out.println("Which information do you want to modify?");
										System.out.println("1. Contact");
										System.out.println("2. Password");
										System.out.println("3. Return to previous menu");
										System.out.println("4. Exit");
										System.out.print("Input: ");

										int number4 = keyboard.nextInt();
										//-------------------------------------------------------------------------------------
										if(number4 == 3) { //return to previous menu
											break;
										}
										//-------------------------------------------------------------------------------------
										if(number4 == 4) { //exit
											System.out.println();
											System.out.println("Bye.");
											keyboard.close();
											System.exit(0);
										}
										while(true) {
											//-------------------------------------------------------------------------------------
											if(number4 == 1) { // modify contact
												System.out.println("Enter your new contact.");
												System.out.println("If you want to return to previous menu, enter 9999.");
												System.out.print("Input: ");
												int Contact = keyboard.nextInt();
												if(Contact == 9999) break;
												Statement statement2 = connection.createStatement();
												statement2.executeUpdate("UPDATE CUSTOMER Set Contact = " + Contact + " WHERE Ssn = " + Ssn);

												System.out.println("Your contact number is updated to " + Contact + ".");
												break;
											}
											//-------------------------------------------------------------------------------------
											if(number4 == 2) { // modify password
												while(true) {
													System.out.println("Enter your original password.");
													int Password2 = keyboard.nextInt();
													if(Password2 == Password) {
														System.out.println("Enter your new password.");
														int Password3 = keyboard.nextInt();
														statement.executeUpdate("UPDATE CUSTOMER Set Password = " + Password3 + " WHERE Ssn = " + Ssn);
														System.out.println("Your password is updated to " + Password3 + ".");
														break;
													}
													else {
														System.out.println("Password you entered is wrong.");
														System.out.println("Try again.");
														System.out.println();
														continue;
													}
												}
												break;
											}
										}
										break;
									}
								}
							}
							//-------------------------------------------------------------------------------------
							if(number2 == 3) { // Account Management
								System.out.println(">> Account Management Menu");
								System.out.println("1. Create the new account");
								System.out.println("2. Remove the account");
								System.out.println("3. Check the balance");
								System.out.println("4. Return to previous menu");
								System.out.println("5. Exit");
								System.out.print("Input: ");

								int number3 = keyboard.nextInt();
								//-------------------------------------------------------------------------------------
								if(number3 == 4) { // return to previous menu
									break;
								}
								//-------------------------------------------------------------------------------------
								if(number3 == 5) { // exit
									System.out.println();
									System.out.println("Bye.");
									keyboard.close();
									System.exit(0);
								}
								while(true) {
									//-------------------------------------------------------------------------------------
									if(number3 == 1) { // create the new account
										System.out.println("**Keep it your mind that you should already join the bank!**");
										System.out.println();
										System.out.println("What is your ssn? (Your birthday date in 6 digits)");
										System.out.println("If you want to return to previous menu, enter 9999.");
										System.out.print("Input: ");
										int Ssn = keyboard.nextInt();
										if(Ssn == 9999) break;

										Statement statement = connection.createStatement();
										ResultSet resultset;
										resultset = statement.executeQuery("SELECT Password FROM CUSTOMER WHERE Ssn = " + Ssn);
										int Password = 0;
										if(!resultset.isBeforeFirst()) {
											System.out.println("customer not found");
											break;
										}

										while(resultset.next()) {
											Password = resultset.getInt(1);
										}

										System.out.println("What is your new account number? (in 6 digits)");
										System.out.println("If you want to return to previous menu, enter 9999.");
										System.out.print("Input: ");
										int Number = keyboard.nextInt();
										if(Number == 9999) break;

										resultset = statement.executeQuery("SELECT OwnerSsn FROM ACCOUNT WHERE  Number = " + Number);
										if(resultset.next()) {
											System.out.println("This number already exists.");
											break;
										}

										try {
											statement.executeUpdate("INSERT INTO ACCOUNT VALUES (" + Number + ", " + Password + ", " + 0 + ", " + Ssn + ");");
											System.out.println("Thank you for joining in HyunjiGonji Bank!");
										}
										catch(Exception e) {
											System.out.println("You haven't joined our bank,");
											System.out.println("so you cannot create your account.");
											System.out.println();
										}
										break;
									}
									//-------------------------------------------------------------------------------------
									if(number3 == 2) { // remove the account
										System.out.println("What is your account number? (in 6 digits)");
										System.out.println("If you want to return to previous menu, enter 9999.");
										System.out.print("Input: ");
										int Number = keyboard.nextInt();
										if(Number == 9999) break;

										Statement statement = connection.createStatement();
										ResultSet resultset;
										resultset = statement.executeQuery("SELECT Password FROM ACCOUNT WHERE Number = " + Number);
										int Password = 0;
										if(!resultset.isBeforeFirst()) {
											System.out.println("account not found");
											break;
										}

										while(resultset.next()) {
											Password = resultset.getInt(1);
										}

										while(true) {
											System.out.println("Enter the password.");
											int Password2 = keyboard.nextInt();
											if(Password2 == Password) {
												statement.executeUpdate("DELETE FROM ACCOUNT WHERE Number = " + Number);

												System.out.println("Your account is removed.");
												break;
											}
											else {
												System.out.println("Password you entered is wrorng.");
												System.out.println("Try again.");
												System.out.println();
												continue;
											}
										}
										break;
									}
									if(number3 == 3) { //check the balance
										System.out.println("What is your account number (in 6 digits)?");
										System.out.println("If you want to return to previous menu, enter 9999.");
										int Number = keyboard.nextInt();
										if(Number == 9999) break;

										Statement statement = connection.createStatement();
										ResultSet resultset;
										resultset = statement.executeQuery("SELECT Password FROM ACCOUNT WHERE Number = " + Number);
										int Password = 0;
										if(!resultset.isBeforeFirst()) {
											System.out.println("account not found");
											break;
										}

										while(resultset.next()) {
											Password = resultset.getInt(1);
										}

										while(true) {
											System.out.println("Enter your password.");
											int Password2 = keyboard.nextInt();
											if(Password2 == Password) {
												int Balance = 0;
												resultset = statement.executeQuery("SELECT Money FROM ACCOUNT WHERE Number = " + Number);
												while(resultset.next()) {
													Balance = resultset.getInt(1);
												}
												System.out.println("Your balance is " + Balance + "won.");
												break;
											}
											else {
												System.out.println("Password you entered is wrong.");
												System.out.println("Try again.");
												System.out.println();
												continue;
											}
										}
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
