package in.sp.main.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;


@Entity
@Table(
    name = "orders",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"order_code", "item_code"})
    }
)

public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long order_id;

    @Column(nullable = false)
    @NotBlank(message = "Order Type cannot be blank")
    private String order_type;

    @Column(nullable = false)
    @NotBlank(message = "Product name cannot be blank")
    private String product_name;

    @Column(nullable = false)
    @NotNull(message = "Order Date is required")
    @PastOrPresent(message = "Order date cannot be in the future")
    private Date orderDate;

    @Column(nullable = false)
    @NotBlank(message = "Order Code cannot be blank")
    private String order_code;

    @Column(nullable = false)
    @NotBlank(message = "Item Code cannot be blank")
    private String item_code;

    @Column(nullable = false)
    @NotNull(message = "Order Amount is required")
    private Double order_amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Status is required")
    private PaymentStatus status = PaymentStatus.PENDING;

    @Column
    private String payment_method;

    @Column
    private String billing_address;

    @Column
    private String shipping_method;

    @Column
    private Double shipping_cost;

    @Column
    private Long tracking_number;

    @Column
    @PastOrPresent(message = "Created date cannot be in the future")
    private Date created_at;

    @Column
    private Currency currency = Currency.USD;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "employeeId", name = "Employee_Id")
    private SalesEmployee salesEmployee;

    // Getters and Setters

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public Double getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(Double order_amount) {
        this.order_amount = order_amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getBilling_address() {
        return billing_address;
    }

    public void setBilling_address(String billing_address) {
        this.billing_address = billing_address;
    }

    public String getShipping_method() {
        return shipping_method;
    }

    public void setShipping_method(String shipping_method) {
        this.shipping_method = shipping_method;
    }

    public Double getShipping_cost() {
        return shipping_cost;
    }

    public void setShipping_cost(Double shipping_cost) {
        this.shipping_cost = shipping_cost;
    }

    public Long getTracking_number() {
        return tracking_number;
    }

    public void setTracking_number(Long tracking_number) {
        this.tracking_number = tracking_number;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public SalesEmployee getSalesEmployee() {
        return salesEmployee;
    }

    public void setSalesEmployee(SalesEmployee salesEmployee) {
        this.salesEmployee = salesEmployee;
    }
}
