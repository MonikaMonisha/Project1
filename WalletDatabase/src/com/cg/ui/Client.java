package com.cg.ui;

import java.util.Scanner;

import com.cg.bean.TransactionBean;
import com.cg.bean.WalletBean;
import com.cg.exception.WalletException;
import com.cg.service.WalletService;
public class Client {
	public static void main(String[] args) throws WalletException{
	Scanner scan = new Scanner(System.in);
int choice;
String custName,  custPhoneNo, custPassword;
int custAge,custBalance;
int amount = 0;
int fromaccNumber=0;
int toaccNumber=0;
WalletService service = new WalletService();
WalletBean wbean=new WalletBean();
		do {
			System.out.println("\n************* Payment Wallet *************");
			System.out.println("1. Create an Account");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.println("******************************");
			System.out.print("\nEnter Your Choice : ");
			choice = scan.nextInt();
			switch(choice)
			{
			
				
				case 1 :
					
							do{
							System.out.println("Enter the name of the Customer :");
							 custName=scan.next();
					
							}while(service.validateCustName(custName)== false);
				
						//Customer Age
							do{
						System.out.println("Enter Customer age : ");
						
						 custAge = scan.nextInt();
						
						}while(service.validateCustAge(custAge)==false);
						//Customer Phone Number
						do{
						
							System.out.println("Enter Customer phone number : ");
							 custPhoneNo = scan.next();
						}while(service.validateCustPhoneNumber(custPhoneNo)==false);
						
						do{
							System.out.println("Enter the initial amount to be deposited : ");
							 custBalance = scan.nextInt();
						}while(service.validateCustBalance(custBalance)==false);
						
						do{
							System.out.println("Set password for your account : ");
							 custPassword = scan.next();
						}while(service.validateCustPassword(custPassword)==false);
						WalletBean wb = new WalletBean();
						
						wb.setCustName(custName);
						wb.setCustAge(custAge);
						wb.setCustPhoneNo(custPhoneNo);
						wb.setCustBalance(custBalance);
						wb.setCustPassword(custPassword);
						wb.setAccNumber();
						int accNumber = service.accountCreation(wb);
						System.out.println("Account created successfully");
						System.out.println("Your Account number is "+accNumber);
						break;
						
				case 2:
						do {
							System.out.println("Enter Account Number :");
						
							accNumber = scan.nextInt();
							System.out.println("Enter the Password");
						
							custPassword = scan.next();
							WalletBean wb1 = service.getAccDetails(accNumber);
								if(service.isValidLogin(wb1, custPassword)==true)
								{
									do {
									
									System.out.println("Menu");
									System.out.println("1. Deposit Amount");
									System.out.println("2. Withdraw Amount");
									System.out.println("3. Show Balance");
									System.out.println("4. Fund Transfer");
									System.out.println("5. Print Transactions");
									System.out.println("6. Exit");
									
									System.out.print("\nPlease Enter a Choice : ");
									choice = scan.nextInt();
									
									switch(choice)
									{
									case 1:
										do
										{
											System.out.println("Enter the Amount to be deposited :");
											amount = scan.nextInt();
																					
										}while(service.validateCustBalance(amount)==false);
										custBalance = service.depositAmount(amount, accNumber);
										System.out.println("Your current balance is :"+custBalance);
										break;
									case 2:
										do
										{

										System.out.println("Enter the Amount to withdraw :");
										amount = scan.nextInt();
																				
									}while(service.validateCustBalance(amount)==false);
									custBalance = service.amountWithdraw(amount, accNumber);
									System.out.println("Your current balance is :"+custBalance);
									break;
									case 3:
										
										service.showBalance(accNumber);
										break;
									case 4:
										System.out.println("Enter From Account Number:");
										fromaccNumber=scan.nextInt();
										System.out.println("Enter To Account Number:");
										toaccNumber=scan.nextInt();
										System.out.println("Enter Amount:");
										amount=scan.nextInt();
										service.fundTransfer(fromaccNumber, toaccNumber, amount);
										System.out.println("Transfer Successfull");
									break;
									case 5:
										//TransactionBean tbean=new TransactionBean();
										//tbean.setAccNumber();
										System.out.println(service.printTransaction(accNumber));
										
										
									}
									
								
									}while(choice!=0);
								
									
									}		
						
	}while(choice==1);
								}
						}while(choice!=0);
	}
}
	
	

