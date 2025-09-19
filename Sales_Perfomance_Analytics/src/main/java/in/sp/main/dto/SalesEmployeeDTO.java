package in.sp.main.dto;

import java.util.Date;
import java.util.Set;

import in.sp.main.entities.Currency;

import in.sp.main.entities.Status;


public class SalesEmployeeDTO {


    private long employeeId;

 
    private String employeeName;


    private String employeeEmail;

 // ✅ Default value

    private Date joinedDate;


    private Double targetQuota;


    private Status status;


    private String manager;
    
    
    private Currency currency=Currency.USD; 

    // Getters and Setters
    
    
   
    private  Set<OrdersDTO> orders;

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public Double getTargetQuota() {
		return targetQuota;
	}

	public void setTargetQuota(Double targetQuota) {
		this.targetQuota = targetQuota;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Set<OrdersDTO> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrdersDTO> orders) {
		this.orders = orders;
	}
    
    
    
}
