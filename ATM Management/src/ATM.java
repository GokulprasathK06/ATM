import java.util.Scanner;
 abstract class Machine{
	 abstract void checkpin();
	 abstract void menu();
	 abstract void checkBalance();
	 abstract void withdrawMoney();
 }
 class Atmmachine extends Machine{
	float Balance;
	private int pin;
	private int AccountNumber;
	private String name;
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
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
	public void checkpin() {
		System.out.println("Enter your pin:");
		Scanner sc =new Scanner(System.in);
		int enterpin=sc.nextInt();
		if(enterpin==pin) {
			menu();
		}
		else {
			System.out.println("Your pin in incorrect");
			System.out.println("please Enter a valid pin");
		}
	}
	public void menu() {
		System.out.println("welcome"+" "+name+" "+"Enter your choice");
		System.out.println("1. Check Account Balance");
		System.out.println("2. Withdraw Money");
		System.out.println("3. Deposite Money");
		System.out.println("4. EXIT");
		
		Scanner sc =new Scanner(System.in);
		int opt=sc.nextInt();
		
		if(opt==1) {
			checkBalance();
		}
		else if(opt==2) {
			withdrawMoney();
		}
		else if(opt==3) {
			depositeMoney();
		}
		
		else if(opt==4) {
			return;
		}
		else {
			System.out.println("Enter a valid choice");
		}
	}
	public void checkBalance() {
		System.out.println("Balance: "+Balance);
		menu();
	}
	public void withdrawMoney() {
		System.out.println("Enter Amount to Withdraw: ");
		Scanner sc =new Scanner(System.in);
		float amount=sc.nextFloat();
		if(amount>Balance) {
			System.out.println("Insufficient Balance");
			menu();
		}
		else {
			Balance-=amount;
			System.out.println("Money Withdrawl Sucessfully");
			System.out.println("1. Show Balance");
			System.out.println("2. EXIT");
			Scanner ge =new Scanner(System.in);
			int choice=ge.nextInt();
			if(choice==1) {
				System.out.println("Current Balance:"+Balance);
			}
			else {
				menu();
			}
		}
	}
	public void depositeMoney() {
		System.out.println("Enter to Amount");
		Scanner sc =new Scanner(System.in);
		float amount=sc.nextFloat();
		Balance+=amount;
		System.out.println(amount+" "+"Money Deposited Sucessfully");
		System.out.println("1. Show Balance");
		System.out.println("2. EXIT");
		Scanner ge =new Scanner(System.in);
		int choice=ge.nextInt();
		if(choice==1) {
			System.out.println("Current Balance:"+Balance);
		}
		else {
			menu();
		}
		menu();
	}
	
}
 
public class ATM {

	public static void main(String[] args) {
		/*Machine ATM=new Atmmachine();
		ATM.checkpin();*/
		Atmmachine atm=new Atmmachine();
		atm.setName("Gokul");
		atm.setPin(1234);
		atm.checkpin();
		/*try {
			atm.setPin(3214);
			menu();
		}
		catch(Exception exception) {
			System.out.println("Enter correct pin");
		}*/
		

	}

}
