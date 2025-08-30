package in.sp.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import in.sp.main.dto.OrdersDTO;
import in.sp.main.services.OrdersServices;

@CrossOrigin(origins = "*")
@RestController
// Optional prefix for all routes
public class OrdersController {

    @Autowired
    private OrdersServices ordersServices;

    // ✅ Get all orders
    @GetMapping("/get-api/orders")
    public ResponseEntity<List<OrdersDTO>> getAllOrders() {
        return ResponseEntity.ok(ordersServices.getAllOrders());
    }

    // ✅ Get order by ID
    @GetMapping("/get-api/order/{orderId}")
    public ResponseEntity<OrdersDTO> getOrderById(@PathVariable("orderId") Long orderId) {
        OrdersDTO order = ordersServices.getOrderById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    // ✅ Add a single order
    @PostMapping("/post-api/order")
    public ResponseEntity<OrdersDTO> addOrder(@RequestBody OrdersDTO ordersDTO) {
        OrdersDTO savedOrder = ordersServices.addAnOrder(ordersDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    // ✅ Add multiple orders
    @PostMapping("/post-api/orders")
    public ResponseEntity<List<OrdersDTO>> addMultipleOrders(@RequestBody List<OrdersDTO> ordersDTOs) {
        List<OrdersDTO> savedOrders = ordersServices.addMultipleOrders(ordersDTOs);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrders);
    }

    // ✅ Add multiple orders for a specific employee
    @PostMapping("/post-api/salesemployees/{employeeId}/orders")
    public ResponseEntity<List<OrdersDTO>> addOrdersForEmployee(
            @PathVariable("employeeId") Long employeeId,
            @RequestBody List<OrdersDTO> ordersDTOs) {
        List<OrdersDTO> savedOrders = ordersServices.addMultipleOrdersForEmployee(employeeId, ordersDTOs);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrders);
    }

    // ✅ Delete all orders
    @DeleteMapping("/delete-api/orders")
    public ResponseEntity<Void> deleteAllOrders() {
        ordersServices.deleteAllOrders();
        return ResponseEntity.noContent().build();
    }

    // ✅ Delete order by ID
    @DeleteMapping("/delete-api/order/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable("orderId") Long orderId) {
        try {
            ordersServices.deleteOrderById(orderId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @PatchMapping("/patch-api/order")
    public ResponseEntity<OrdersDTO> updateOrdersPatially(@RequestBody OrdersDTO ordersDTO)
    {
    	
    	
		return ResponseEntity.ok(ordersServices.updateOrdersPartially(ordersDTO));
    	
    	
    }
}
