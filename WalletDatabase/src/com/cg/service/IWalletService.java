package com.cg.service;

import java.util.List;

import com.cg.bean.TransactionBean;
import com.cg.bean.WalletBean;

public interface IWalletService {
	public int accountCreation(WalletBean wb);
	public boolean isValidLogin(WalletBean wbean, String custPassword);
	public int depositAmount(int amount, int accNumber);
	public int amountWithdraw(int amount, int accNumber);
	public void showBalance(int accNumber);
	public boolean fundTransfer(int fromaccNumber, int toaccNumber, int amount);
	public List<String> printTransaction(int accNumber);
	

}
