package in.sp.main.services;

import java.util.List;

import in.sp.main.dto.OrdersDTO;
import jakarta.transaction.Transactional;

public interface OrdersServices {

	

	List<OrdersDTO> getAllOrders();
	
	
	OrdersDTO getOrderById(long order_Id);
	
	
	@Transactional
	OrdersDTO addAnOrder(OrdersDTO ordersDTO);
	
	@Transactional
	
	 List<OrdersDTO> addMultipleOrders(List<OrdersDTO> ordersDTOs); 
	
	@Transactional
	public List<OrdersDTO> addMultipleOrdersForEmployee(Long employeeId, List<OrdersDTO> ordersDTOs);
	
	@Transactional
	void deleteAllOrders();
	
	@Transactional
	void deleteOrderById(Long orderId);
	
	
	@Transactional
	OrdersDTO updateOrdersPartially(OrdersDTO ordersDTO);
	



}
