package com.cg.bean;

public class TransactionBean {
	private int accNumber;
	private String transDetails;
	public int getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(int accNumber) {
		this.accNumber = accNumber;
	}
	public String getTransDetails() {
		return transDetails;
	}
	public void setTransDetails(String transDetails) {
		this.transDetails = transDetails;
	}
	@Override
	public String toString() {
		return "TransactionBean [accNumber=" + accNumber + ", transDetails=" + transDetails + "]";
	}
	

}
