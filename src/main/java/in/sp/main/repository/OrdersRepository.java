package in.sp.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import in.sp.main.entities.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    // 1. Total revenue
    @Query("SELECT SUM(o.order_amount) FROM Orders o")
    Double getTotalRevenue();

    // 2. Average sales per sales rep
    @Query("SELECT AVG(o.order_amount) FROM Orders o GROUP BY o.salesEmployee.employeeId")
    List<Double> getAverageSalesPerRep();

    // 3. Most sold product by frequency
    @Query("SELECT o.product_name FROM Orders o GROUP BY o.product_name ORDER BY COUNT(o) DESC")
    List<String> getMostSoldProduct(Pageable pageable);

    // 4. Best performing salesperson by total revenue
    @Query("SELECT o.salesEmployee.employeeName FROM Orders o GROUP BY o.salesEmployee.employeeName ORDER BY SUM(o.order_amount) DESC")
    List<String> getBestSalesPerson(Pageable pageable);
}
