package Process;
/*
Title:ATM Management System
Author: Gokulprasath.K
created on:07-10-2022
updated on:08-08-2023
Reviewed on:22.10.2022
Reviewed by:Anushya Narayanan
*/

abstract class Machine {
	 abstract void checkpin();
	 abstract void menu();
	 abstract void checkBalance();
	 abstract void withdrawMoney();
	 abstract void depositMoney();
	 abstract void login();
}
