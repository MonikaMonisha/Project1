package com.cg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cg.bean.TransactionBean;
import com.cg.bean.WalletBean;
import com.cg.util.DBUtil;

public class WalletDao implements IWalletDao{
	Connection con;
	public WalletDao()
	{
		con=DBUtil.getConnect();
	}

	@Override
	public int accountCreation(WalletBean wb) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO wallet VALUES(?,?,?,?,?,?)";
		try{
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, wb.getAccNumber());
			pstmt.setString(2, wb.getCustName());
			pstmt.setInt(3, wb.getCustBalance());
			pstmt.setInt(4,wb.getCustAge());
			pstmt.setString(5, wb.getCustPhoneNo());
			pstmt.setString(6, wb.getCustPassword());
			int row=pstmt.executeUpdate();
		if(row>0)
		{
			System.out.println("Successfully inserted record");
			
		}
		}	
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	return wb.getAccNumber();
	
	}

	public boolean isValidLogin(WalletBean wbean, String custPassword) {
		
		try {
		if(wbean.getCustPassword().equals(custPassword))
		{
			return true;
			
					}
		}catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
		return false;	

}

	public int depositAmount(int amount, int accNumber) {
		// TODO Auto-generated method stub
		int custBalance = 0;
		String transDetails="Deposited Amount: "+amount;
		String query= "SELECT custBalance FROM wallet WHERE accNumber=?";
		String query1="UPDATE wallet SET custBalance=? WHERE accNumber=?";
		String query2="INSERT INTO transaction VALUES(?,?)";
		try
		{
			
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setInt(1, accNumber);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
			custBalance=(int) rs.getInt(1);
			}
			custBalance=custBalance+amount;
			PreparedStatement pstmt1=con.prepareStatement(query1);
			pstmt1.setInt(1, custBalance);
			pstmt1.setInt(2, accNumber);
			
			pstmt1.executeUpdate();
			
			PreparedStatement pstmt2=con.prepareStatement(query2);
			pstmt2.setInt(1, accNumber);
			pstmt2.setString(2, transDetails);
			pstmt2.executeUpdate();
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return custBalance;
			
		}

	public int amountWithdraw(int amount, int accNumber) {
		// TODO Auto-generated method stub
		int custBalance = 0;
		
		String transDetails="Withdrawn Amount: "+amount;
		String query= "SELECT custBalance FROM wallet WHERE accNumber=?";
		String query1="UPDATE wallet SET custBalance=? WHERE accNumber=?";
		String query2="INSERT INTO transaction VALUES(?,?)";
		try
		{
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setInt(1, accNumber);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
			custBalance=rs.getInt(1);
			}
			custBalance=custBalance-amount;
			PreparedStatement pstmt1=con.prepareStatement(query1);
			pstmt1.setInt(2, accNumber);
			pstmt1.setInt(1, custBalance);
			pstmt1.executeUpdate();
			
			PreparedStatement pstmt2=con.prepareStatement(query2);
			pstmt2.setInt(1, accNumber);
			pstmt2.setString(2, transDetails);
			pstmt2.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

		return custBalance;
			
		}

	public void showBalance(int accNumber) {
		// TODO Auto-generated method stub
		String query= "SELECT custBalance FROM wallet WHERE accNumber=?";
		try
		{
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setInt(1, accNumber);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
			int custBalance=rs.getInt(1);
			System.out.println(custBalance);
			}
	}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	
		
	}

	public WalletBean getAccDetails(int accNumber) {
		String query="SELECT * FROM wallet WHERE accNumber=?";
		 
		WalletBean wb = new WalletBean();
		try
		{
			
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setInt(1, accNumber);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				wb.setCustName(rs.getString(2));
				wb.setCustBalance(rs.getInt(3));
				wb.setCustAge(rs.getInt(4));
				wb.setCustPhoneNo(rs.getString(5));
				wb.setCustPassword(rs.getString(6));
				//System.out.println(wb.getCustPassword());
			}
			
			//System.out.println(rs);
			//WalletBean wb = rs;
			}
		// TODO Auto-generated method stub
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return wb;
}

	@Override
	public boolean fundTransfer(int fromaccNumber, int toaccNumber, int amount) {
		// TODO Auto-generated method stub
		int a = depositAmount(amount,  toaccNumber);
		 a = amountWithdraw(amount,  fromaccNumber);
		return true;
	}

	@Override
	public List<String> printTransaction(int accNumber) {
		// TODO Auto-generated method stub
		String query="SELECT transDetails FROM transaction WHERE accNumber=?";
		List<String> list=new ArrayList<String>();
		try {
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setInt(1, accNumber);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				//System.out.println(rs.getString(1));
				list.add(rs.getString(1));
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

		return list;
	}
	
	}
