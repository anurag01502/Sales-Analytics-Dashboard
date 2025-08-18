package in.sp.main.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
@Table(name = "sales_employee")
@CrossOrigin(origins = "*") 
public class SalesEmployee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeId;

    @NotBlank(message = "Employee Name cannot be blank")
    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    @Column(name = "employee_email", unique = true, nullable = false)
    private String employeeEmail;

    @PastOrPresent(message = "Join date can't be in the future")
    @Column(name = "joined_date")
    private Date joinedDate;

    @Column(name = "target_quota")
    private Double targetQuota;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Status is required")
    private Status status;

    @Column
    private String manager;

    @Column
    private Currency currency = Currency.USD;

    // One-to-Many relationship with Orders
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "salesEmployee")
    private Set<Orders> orders;

    // Getters and Setters

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

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }
}
