package com.tickle.dto;

public class Check {
	private Integer stepID;
	private boolean isChecked;
	private String title;
	private String description;
	private int workID;
	
	
	public Check() {
		super();
	}
	
	public Integer getStepID() {
		return stepID;
	}
	public void setStepID(Integer stepID) {
		this.stepID = stepID;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
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
	public int getWorkID() {
		return workID;
	}
	public void setWorkID(int workID) {
		this.workID = workID;
	}
	
	
}
