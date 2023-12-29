package Process;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import Process.ConfigureManger;
import Process.DatabaseConnection;




public class User {
	private Connection connection;
	private ConfigureManger configureManager=new ConfigureManger();
	private static final Logger logger=Logger.getLogger(User.class.getName());
	
	public void checkpin() {
		System.out.println("Enter your pin:");
		Scanner sc =new Scanner(System.in);
		int enterpin=sc.nextInt();
		Properties properties=new Properties();
		try {
			String FilePath="C:\\Users\\ELCOT\\Desktop\\database.properties";
			FileInputStream fileInputStream=new FileInputStream(FilePath);
			properties.load(fileInputStream);
		}
		catch(IOException exception){


		}
		String DatabaseUrl=properties.getProperty("db.url");
		String DatabaseUsername=properties.getProperty("db.username");
		String DatabasePassword=properties.getProperty("db.password");
		String Pin=properties.getProperty("checkpin");
		
		try {
			
			
			Connection connection = DriverManager.getConnection(DatabaseUrl,DatabaseUsername,DatabasePassword);

			PreparedStatement preparedstatement = connection.prepareStatement(Pin);

			preparedstatement.setInt(1, enterpin);
			ResultSet resultset = preparedstatement.executeQuery();

			if(resultset.next()) {
				menu();
			}
			else {
				
				logger.log(Level.INFO, "Your pin is incorrect");
				
				
				logger.log(Level.INFO, "Please try again!!");
				
					checkpin();
				}
		
			
    		}
		catch(SQLException exception) {
			logger.log(Level.SEVERE,"Error occurred while checking correct pin number", exception);
		}
		
	}
	public void menu() {
		
		System.out.println("welcome to easy bank Enter your choice");
		System.out.println("1. Check Account Balance");
		System.out.println("2. Withdraw Money");
		System.out.println("3. Deposite Money");
		System.out.println("4. Change PIN");
		System.out.println("5. EXIT");
		
		Scanner sc =new Scanner(System.in);
		int opt=sc.nextInt();
		
		if(opt==1) {
			checkBalance();
		}
		else if(opt==2) {
			withdrawMoney();
		}
		else if(opt==3) {
			depositMoney();
		}
		
		else if(opt==4) {
			changePIN();
		}
		else if(opt==5) {
			logger.log(Level.INFO, "Thank you for spend your time with us");
			return;
		}
		else {
			logger.log(Level.INFO, "Enter a valid choice");
		}
	}
	public void checkBalance() {
		Scanner scanner=new Scanner(System.in);
		Properties properties=new Properties();
		try {
			String FilePath="C:\\Users\\ELCOT\\Desktop\\database.properties";
			FileInputStream fileInputStream=new FileInputStream(FilePath);
			properties.load(fileInputStream);
		}
		catch(IOException exception){
			logger.log(Level.SEVERE,"Error occurred while checking file", exception);
		}
		String accountNumber=properties.getProperty("AccountNumber");
		String DatabaseUrl=properties.getProperty("db.url");
		String DatabaseUsername=properties.getProperty("db.username");
		String DatabasePassword=properties.getProperty("db.password");
		String dbName=properties.getProperty("dbName");
		String checkBalance=properties.getProperty("checkBalance");
	
	try {

		Class.forName(dbName);

		Connection connection = DriverManager.getConnection(DatabaseUrl,DatabaseUsername,DatabasePassword);

		PreparedStatement preparedstatement= connection.prepareStatement(checkBalance);
		preparedstatement.setString(1, accountNumber);
		

		ResultSet resultset = preparedstatement.executeQuery();

		if(resultset.next()) {

		int balance=resultset.getInt("balance");
		
		System.out.println("-----------------------------------------");
		System.out.println("Your Account Balance is = "+balance);
		 System.out.println("-----------------------------------------");
		System.out.println("Do you want to continue?");
		
		System.out.println("1. Yes");
		System.out.println("2. No");
		
		int choice=scanner.nextInt();
		if(choice==1) {
			menu();
		}
		else if(choice==2) {
			return;
		}
		else {
			
    }

		}else {

			

		}

		

		}catch(Exception exception) {

			logger.log(Level.SEVERE,"Error occurred while admin login", exception);
		}
	
	}
	public void withdrawMoney() {
		System.out.println("Enter Amount to Withdraw: ");
		Scanner scanner =new Scanner(System.in);
		float amount=scanner.nextFloat();
		DatabaseConnectivity Database=new DatabaseConnectivity() ;
		Properties properties=new Properties();
		try {
			String FilePath="C:\\Users\\ELCOT\\Desktop\\database.properties";
			FileInputStream fileInputStream=new FileInputStream(FilePath);
			properties.load(fileInputStream);
		}
		catch(IOException exception){
			logger.log(Level.SEVERE,"Error occurred while checking file", exception);
		}
		String AccountNumber=properties.getProperty("AccountNumber");
		String DatabaseUrl=properties.getProperty("db.url");
		String DatabaseUsername=properties.getProperty("db.username");
		String DatabasePassword=properties.getProperty("db.password");
		String getAccount=properties.getProperty("getAccount");
		String UpdateBalance=properties.getProperty("updateAmount");
		String dbName=properties.getProperty("dbName");
		
		//Database.databasestore();
		try {

			Class.forName(dbName);

			Connection connection = DriverManager.getConnection(DatabaseUrl,DatabaseUsername,DatabasePassword);
			String selectQuery = getAccount;
	        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
	        selectStatement.setString(1, AccountNumber);
	        ResultSet resultSet = selectStatement.executeQuery();

	        if (resultSet.next()) {
	            int balance = resultSet.getInt("balance");
	            if(amount>40000) {
	            	System.out.println("Sorry daily limit is 40000 only");
	            }
	            else {
	            	
	            

	            // Check if the balance is sufficient
	            if (balance >= amount) {
	                int newBalance = (int) (balance - amount);

	                // Perform the withdrawal
	                String updateQuery = UpdateBalance;
	                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
	                updateStatement.setInt(1, newBalance);
	                updateStatement.setString(2, AccountNumber);
	                updateStatement.executeUpdate();

	                // Close database resources
	                updateStatement.close();
	                resultSet.close();
	                selectStatement.close();
	                connection.close();

	                
	                
	                System.out.println("Amount Withdraw Successfully");
	                System.out.println("1. Show Balance");
	        		System.out.println("2. Go to menu");
	        		int choice=scanner.nextInt();
	        		if(choice==1) {
	        			System.out.println("Current Balance:"+newBalance);
	        		}
	        		else if(choice==2) {
	        			menu();
	        		}
	        		else {
	        			
	            }
	                
	            } else {
	                // Insufficient balance
	            	logger.log(Level.INFO,"In Sufficient Balance");
	            }
	        }
	        }
	            else {
	           
	        	logger.log(Level.INFO,"No user found");
	        }

	        // Close database resources
	        resultSet.close();
	        selectStatement.close();
	        connection.close();
	    }
		catch(Exception exception) {

			logger.log(Level.SEVERE,"Error occurred while withdrae process", exception);

		}
	}
	public void depositMoney() {
		System.out.println("Enter to Amount");
		Scanner scanner =new Scanner(System.in);
		float amount=scanner.nextFloat();
		Properties properties=new Properties();
		try {
			String FilePath="C:\\Users\\ELCOT\\Desktop\\database.properties";
			FileInputStream fileInputStream=new FileInputStream(FilePath);
			properties.load(fileInputStream);
		}
		catch(IOException exception){
			logger.log(Level.SEVERE,"Error occurred while checking file", exception);
		}
		String AccountNumber=properties.getProperty("AccountNumber");
		String DatabaseUrl=properties.getProperty("db.url");
		String DatabaseUsername=properties.getProperty("db.username");
		String DatabasePassword=properties.getProperty("db.password");
		String getAccount=properties.getProperty("getAccount");
		String UpdateBalance=properties.getProperty("updateAmount");
		String dbName=properties.getProperty("dbName");
		try {

			Class.forName(dbName);

			Connection connection = DriverManager.getConnection( DatabaseUrl, DatabaseUsername,DatabasePassword);
			String selectQuery = getAccount;
	        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
	        selectStatement.setString(1, AccountNumber);
	        ResultSet resultSet = selectStatement.executeQuery();

	        if (resultSet.next()) {
	            int balance = resultSet.getInt("balance");

	            // Check if the balance is sufficient
	            
	                int newBalance = (int) (balance + amount);

	                // Perform the withdrawal
	                String updateQuery = UpdateBalance;
	                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
	                updateStatement.setInt(1, newBalance);
	                updateStatement.setString(2, AccountNumber);
	                updateStatement.executeUpdate();

	                // Close database resources
	                updateStatement.close();
	                resultSet.close();
	                selectStatement.close();
	                connection.close();

	                
	                System.out.println("Amount Deposited Successfully");
	                System.out.println("1. Show Balance");
	        		System.out.println("2. Go to menu");
	        		int choice=scanner.nextInt();
	        		if(choice==1) {
	        			System.out.println("Current Balance:"+newBalance);
	        		}
	        		else if(choice==2) {
	        			menu();
	        		}
	        		else {
	        			
	            }
	              
	            } 
			
	        else {
	        	logger.log(Level.INFO, "No Account Found");
	           
	        }

	        // Close database resources
	        resultSet.close();
	        selectStatement.close();
	        connection.close();
			}
		
		
		catch(Exception exception) {

			logger.log(Level.SEVERE,"Error occurred while deposit process ", exception);

		}

	}
	
	public void changePIN() {
		System.out.println("Enter your old PIN Number");
		Scanner scanner =new Scanner(System.in);
		int enterpin=scanner.nextInt();
		Properties properties=new Properties();
		try {
			String FilePath="C:\\Users\\ELCOT\\Desktop\\database.properties";
			FileInputStream fileInputStream=new FileInputStream(FilePath);
			properties.load(fileInputStream);
		}
		catch(IOException exception){
			logger.log(Level.SEVERE,"Error occurred while checking file", exception);
		}
		String DatabaseUrl=properties.getProperty("db.url");
		String DatabaseUsername=properties.getProperty("db.username");
		String DatabasePassword=properties.getProperty("db.password");
		String Pin=properties.getProperty("checkpin");
		
		
		try {
			
			
			Connection connection = DriverManager.getConnection(DatabaseUrl,DatabaseUsername,DatabasePassword);

			PreparedStatement preparedstatement = connection.prepareStatement(Pin);

			preparedstatement.setInt(1, enterpin);
			ResultSet resultset = preparedstatement.executeQuery();

			if(resultset.next()) {
				ChangedPin();
			}
			else {
				
				logger.log(Level.INFO, "Your pin in incorrect");
				
				
				logger.log(Level.INFO,"please Enter a valid pin");
				
					
					checkpin();
				}
		
			
    		}
		catch(SQLException exception) {
			logger.log(Level.SEVERE,"Error occurred while changing PIN", exception);
		}
		
	
		
	}

	

    
    public void ChangedPin() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Enter your new PIN Number");
		int Newpin=scanner.nextInt();
		Properties properties=new Properties();
		try {
			String FilePath="C:\\Users\\ELCOT\\Desktop\\database.properties";
			FileInputStream fileInputStream=new FileInputStream(FilePath);
			properties.load(fileInputStream);
		}
		catch(IOException exception){
			logger.log(Level.SEVERE,"Error occurred while checking file", exception);
		}
		String AccountNumber=properties.getProperty("AccountNumber");
		String DatabaseUrl=properties.getProperty("db.url");
		String DatabaseUsername=properties.getProperty("db.username");
		String DatabasePassword=properties.getProperty("db.password");
		String UpdatePin=properties.getProperty("updatePin");
		String dbName=properties.getProperty("dbName");
		
		if(Newpin !=0) {
			try {
				Class.forName(dbName);

				Connection connection = DriverManager.getConnection(DatabaseUrl,DatabaseUsername,DatabasePassword);

				PreparedStatement preparedstatement = connection.prepareStatement(UpdatePin);
				preparedstatement.setLong(1, Newpin );
				preparedstatement.setString(2, AccountNumber);
				int rowcount = preparedstatement.executeUpdate();
				if(rowcount>0) {

					System.out.println("PIN Changed Successfully");
					System.out.println("1. Do you want continue");
	        		System.out.println("2. Exit");
	        		int choice=scanner.nextInt();
	        		if(choice==1) {
	        			checkpin();
	        		}
	        		else if(choice==2) {
	        			logger.log(Level.INFO,"Thank you for spend your time with us");
	        			return ;
	        		}
	        		else {
	        			
	            }

					}else {

						logger.log(Level.INFO,"PIN Doesn't Changed");

					}

					
					
				}catch(Exception exception) {

					logger.log(Level.SEVERE,"Error occurred while changing PIN", exception);

				}
			}
		
    }
    
    class DatabaseConnectivity {
    	  public void database(float balance) {
    	  try{
    	  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/atmmachine","root","gokul@06");
    	  Statement st=con.createStatement();
    	  String Query="update machine set balance="+balance;
    	  st.execute(Query);
    	  con.close();
    	  System.out.print("data was added");
    	  }
    	  catch(SQLException exception) {
    	    System.err.println(exception);
    	  }
    	  
    	  }
    	  public void databasestore() {
    	   		 
      		try {
      		    Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","gokul@06");
      		    Statement statement=connection.createStatement();
      		    String query="select * from Machine";
      		    ResultSet resultSet = null;
      		    resultSet=statement.executeQuery(query);
      			resultSet.next();
                  String AccountDetails = "Name : "+resultSet.getNString(1)+
                              "\nBalance : "+resultSet.getInt(2);
                              
                  System.out.println("-----------------------------------------");
                  
                  System.out.println(AccountDetails);
                  System.out.println("-----------------------------------------");
                  connection.close();
      		}
      		catch(SQLException exception) {
      			System.err.println(exception);
      		}
      	}
    	  public void databasecheck() {
    		  try {
    			  Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","gokul@06");
        		    Statement statement=connection.createStatement();
        		    String query="select pinnumber from Atmmachine";
        		    ResultSet resultSet = null;
        		    resultSet=statement.executeQuery(query);
        			resultSet.next();
        			int pin=resultSet.getInt(3);
    		  }
    		  catch(SQLException exception) {
        			System.err.println(exception);
        		}
    	  }
    }
    }

