package com.tickle.dto;

import java.sql.Date;

public class Work {
	private Integer workID;
	private Integer workStatus;
	private Boolean paymentDone;
	private Integer employerID;
	private Integer employeeID;
	
	private String title;
	private String description;
	private Integer category;
	private double pay;
	private Date dueDate;
	
	public Work() {
		super();
	}
	
	public Integer getWorkID() {
		return workID;
	}
	public void setWorkID(Integer workID) {
		this.workID = workID;
	}
	public Integer getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(Integer workStatus) {
		this.workStatus = workStatus;
	}
	public Boolean getPaymentDone() {
		return paymentDone;
	}
	public void setPaymentDone(Boolean paymentDone) {
		this.paymentDone = paymentDone;
	}
	public Integer getEmployerID() {
		return employerID;
	}
	public void setEmployerID(Integer employerID) {
		this.employerID = employerID;
	}
	public Integer getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public double getPay() {
		return pay;
	}

	public void setPay(double pay) {
		this.pay = pay;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
}
