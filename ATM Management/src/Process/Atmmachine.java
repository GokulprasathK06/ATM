package Process;
import Process.Admin;
import Process.User;
import java.util.Properties;
/*
Title:ATM Management System
Author: Gokulprasath.K
created on:07-10-2022
updated on:08-08-2023
Reviewed on:22.10.2022
Reviewed by:Anushya Narayanan
*/
//import java.util.*;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

    class Atmmachine extends Machine {
	float Balance;
	private int pin;
	private int AccountNumber;
   
    private int password;
    public void setPassword(int password) {
		this.password=password;
	}
    public int getPassword() {
		return password;
	}
	public void setPin(int pin) {
		this.pin=pin;
	}	
	public int getPin() {
		return pin;
	}
	public void setAccNumber(int AccountNumber) {
		this.AccountNumber=AccountNumber;
	}
	public int getAccountNumber() {
		return AccountNumber;
	}
	public void login() {
		System.out.println("Enter your Choice ");
		System.out.println("1. User Login ");
		System.out.println("2. Admin Login ");
		Scanner scanner=new Scanner(System.in);
		int choice=scanner.nextInt();
		if(choice==1) {
			checkpin();
		}
		else if(choice==2) {
			Admin admin=new Admin();
			admin.adminLogin();
		}
		else {
			return;
		}
	}
	public void checkpin() {
		User user=new User();
		user.checkpin();
	}
	public void menu() {
		User user=new User();
		user.menu();
	}
	public void checkBalance() {
		User user=new User();
		user.checkBalance();
	}
	public void withdrawMoney() {
		User user=new User();
		user.withdrawMoney();
	}
	public void depositMoney() {
		User user=new User();
		user.depositMoney();
	}
	
	
    }


