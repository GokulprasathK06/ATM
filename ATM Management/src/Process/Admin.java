package Process;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.xdevapi.Statement;

import Process.ConfigureManger;
import Process.DatabaseConnection;

public class Admin {
	private static final Logger logger=Logger.getLogger(Admin.class.getName());
	public void adminLogin() {
		System.out.println("Enter Admin name");
		Scanner scanner=new Scanner(System.in);
		//System.out.println("Enter Admin name");
		String name=scanner.next();
		System.out.println("Enter Admin password");
		int Password=scanner.nextInt();
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
		String admin=properties.getProperty("Admin");
		String dbName=properties.getProperty("db.Name");
		try {


			Connection connection = DriverManager.getConnection(DatabaseUrl,DatabaseUsername,DatabasePassword);

			PreparedStatement preparedstatement = connection.prepareStatement(admin);

			preparedstatement.setString(1, name);
			preparedstatement.setInt(2, Password);
			ResultSet resultset = preparedstatement.executeQuery();


			

			if(resultset.next()) {

			adminMenu();

			}else {

				logger.log(Level.INFO,"Please enter valid credential");
			adminLogin();

			}

			

			}catch(Exception exception) {

				logger.log(Level.SEVERE,"Error occurred while admin login", exception);
			}
		
	}
	public void adminMenu() {
		System.out.println("Enter your choice");
		System.out.println("1. ATM Balance ");
		System.out.println("2. Deposit Amount ");
		Scanner scanner=new Scanner(System.in);
		int choice=scanner.nextInt();
		if(choice==1) {
			checkBalance();
		}
		else if(choice==2) {
			depositAmount();
		}
		else {
			return;
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
		String DatabaseUrl=properties.getProperty("db.url");
		String DatabaseUsername=properties.getProperty("db.username");
		String DatabasePassword=properties.getProperty("db.password");
		String dbName=properties.getProperty("dbName");
		String checkBalance=properties.getProperty("adminCheckbalance");
	
	try {

	

		Connection connection = DriverManager.getConnection(DatabaseUrl,DatabaseUsername,DatabasePassword);

		PreparedStatement preparedstatement= connection.prepareStatement(checkBalance);

		

		ResultSet resultset = preparedstatement.executeQuery();

		if(resultset.next()) {

		int balance=resultset.getInt("balance");
		
		System.out.println("This ATM Balance is ="+balance);
		
		System.out.println("Do you want to continue?");
		
		System.out.println("1. Yes");
		System.out.println("2. No");
		
		int choice=scanner.nextInt();
		if(choice==1) {
			adminMenu();
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
	
	public void depositAmount() {
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
		String MachineId=properties.getProperty("machineId");
		String DatabaseUrl=properties.getProperty("db.url");
		String DatabaseUsername=properties.getProperty("db.username");
		String DatabasePassword=properties.getProperty("db.password");
		String getAccount=properties.getProperty("getAtm");
		String UpdateBalance=properties.getProperty("updateBalance");
		String dbName=properties.getProperty("dbName");
		try {

			

			Connection connection = DriverManager.getConnection( DatabaseUrl, DatabaseUsername,DatabasePassword);
			
	        PreparedStatement selectStatement = connection.prepareStatement(getAccount);
	        selectStatement.setString(1, MachineId);
	        ResultSet resultSet = selectStatement.executeQuery();

	        if (resultSet.next()) {
	            int balance = resultSet.getInt("balance");

	            // Check if the balance is sufficient
	            
	                int newBalance = (int) (balance + amount);

	                // Perform the withdrawal
	                
	                PreparedStatement updateStatement = connection.prepareStatement(UpdateBalance);
	                updateStatement.setInt(1, newBalance);
	                updateStatement.setString(2, MachineId);
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
	        			adminMenu();
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
				
				
				logger.log(Level.INFO,"please Enter a valid pin");
				
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
		catch(IOException e){
			e.printStackTrace();
		}
		String AccountNumber=properties.getProperty("AccountNumber");
		String DatabaseUrl=properties.getProperty("db.url");
		String DatabaseUsername=properties.getProperty("db.username");
		String DatabasePassword=properties.getProperty("db.password");
		String UpdatePin=properties.getProperty("updatePin");
		
		if(Newpin !=0) {
			try {

				Class.forName("com.mysql.cj.jdbc.Driver");

				Connection connection = DriverManager.getConnection(DatabaseUrl,DatabaseUsername,DatabasePassword);

				PreparedStatement preparedstatement = connection.prepareStatement(UpdatePin);
				preparedstatement.setLong(1, Newpin );
				preparedstatement.setString(2, AccountNumber);
				int rowcount = preparedstatement.executeUpdate();
				if(rowcount>0) {

					System.out.println("PIN Changed Successfully");
					
					}else {

					System.out.println("PIN Doesn't Changed");

					}

					
					
				}catch(Exception exception) {

				exception.printStackTrace();

				}
			}
		
    }
   
}
