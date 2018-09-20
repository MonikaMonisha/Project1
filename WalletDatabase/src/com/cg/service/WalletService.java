package com.cg.service;

import java.util.List;

import com.cg.bean.TransactionBean;
import com.cg.bean.WalletBean;
import com.cg.dao.WalletDao;

public class WalletService implements IWalletService {
WalletDao wdao=new WalletDao();
static String namePattern = "[A-Z]{1}[a-z]{2,10}";
static String numberPattern = "[6-9]{1}[0-9]{9}";
static String passwordPattern = "[A-Z]{1}[a-z]{2,6}(\\d){1,4}(\\W){1}";
	@Override
	public int accountCreation(WalletBean wb) {
		// TODO Auto-generated method stub
		
		return wdao.accountCreation(wb);
	}
	 public  boolean validateCustName(String name)
	 {	if(name.matches(namePattern))
	 		return true;
	 	else{
	 		System.out.println("Invalid name pattern\n(Example: Monika)");
	 		return false;
	 	}
	 }
	 
	 
	 public  boolean validateCustPhoneNumber(String number) {
			if(number.matches(numberPattern))
				return true;
			else{
				System.out.println("phone number should be 10 digits");
				return false;
		}
			}
	 
	 
public boolean validateCustAge(int age) {
	if(age<=110&&age>=1)
		return true;
	else
		System.out.println("Enter valid age");
	return false;
	
}

public  boolean validateCustPassword(String pwd) {
	if(pwd.matches(passwordPattern))
		return true;
	else
		System.out.println("Password should of pattern Monika12@");
	return false;
}

public  boolean validateCustBalance(int custBalance) {
if(custBalance>0)
	return true;
else
	System.out.println("Maintain minimum balance");
return false;
}
public WalletBean getAccDetails(int accNumber)
{
	return wdao.getAccDetails(accNumber);
	
}
@Override
public boolean isValidLogin(WalletBean wbean, String custPassword) {
	// TODO Auto-generated method stub
	return wdao.isValidLogin(wbean, custPassword);
}
@Override
public int depositAmount(int amount, int accNumber) {
	
	
	return wdao.depositAmount(amount, accNumber);
}
public int amountWithdraw(int amount, int accNumber) {
	// TODO Auto-generated method stub
	return wdao.amountWithdraw(amount, accNumber);
}
public void showBalance(int accNumber) {
	// TODO Auto-generated method stub
	wdao.showBalance(accNumber);
}
@Override
public boolean fundTransfer(int fromaccNumber, int toaccNumber, int amount) {
	// TODO Auto-generated method stub
	return wdao.fundTransfer(fromaccNumber, toaccNumber, amount);
}
@Override
public List<String> printTransaction(int accNumber) {
	// TODO Auto-generated method stub
	
	return wdao.printTransaction(accNumber);
}


}
