package in.sp.main.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.dto.OrdersDTO;
import in.sp.main.entities.Orders;
import in.sp.main.entities.SalesEmployee;
import in.sp.main.mapper.OrdersMapper;
import in.sp.main.repository.OrdersRepository;
import in.sp.main.repository.SalesEmployeeRepository;

@Service
public class OrdersServiceImp implements OrdersServices {

	
	@Autowired
    private  OrdersRepository ordersRepository;
	
	@Autowired
	private SalesEmployeeRepository salesEmployeeRepository;



    @Override
    public List<OrdersDTO> getAllOrders() {
        List<Orders> allOrders = ordersRepository.findAll();
        List<OrdersDTO> orderDTOList = new ArrayList<>();

        for (Orders order : allOrders) {
            orderDTOList.add(OrdersMapper.toDTO(order));
        }

        return orderDTOList;
    }

    @Override
    public OrdersDTO getOrderById(long orderId) {
        Optional<Orders> optionalOrder = ordersRepository.findById(orderId);
        return optionalOrder.map(OrdersMapper::toDTO).orElse(null);
        // Alternatively: throw new ResourceNotFoundException("Order not found with ID: " + orderId);
    }

    @Override
    public OrdersDTO addAnOrder(OrdersDTO ordersDTO) {
        Orders orderEntity = OrdersMapper.toEntity(ordersDTO);
        Orders savedOrder = ordersRepository.save(orderEntity);
        return OrdersMapper.toDTO(savedOrder);
    }

    @Override
    public List<OrdersDTO> addMultipleOrders(List<OrdersDTO> ordersDTOs) {
        List<Orders> orderEntities = new ArrayList<>();

        for (OrdersDTO dto : ordersDTOs) {
            orderEntities.add(OrdersMapper.toEntity(dto));
        }

        List<Orders> savedOrders = ordersRepository.saveAll(orderEntities);
        List<OrdersDTO> savedOrderDTOs = new ArrayList<>();

        for (Orders order : savedOrders) {
            savedOrderDTOs.add(OrdersMapper.toDTO(order));
        }

        return savedOrderDTOs;
    }

	@Override
    public List<OrdersDTO> addMultipleOrdersForEmployee(Long employeeId, List<OrdersDTO> ordersDTOs) {
         SalesEmployee employee = salesEmployeeRepository.findById(employeeId)
            .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));

        List<Orders> orderEntities = new ArrayList<>();

        for (OrdersDTO dto : ordersDTOs) {
            Orders order = OrdersMapper.toEntity(dto);
            order.setSalesEmployee(employee); // Associate with the employee
            orderEntities.add(order);
        }

        List<Orders> savedOrders = ordersRepository.saveAll(orderEntities);

        List<OrdersDTO> savedDTOs = new ArrayList<>();
        for (Orders saved : savedOrders) {
            savedDTOs.add(OrdersMapper.toDTO(saved));
        }

        return savedDTOs;
    }
	
    @Override
    public void deleteAllOrders() {
        ordersRepository.deleteAll();
    }

    // ✅ Delete order by ID
    @Override
    public void deleteOrderById(Long orderId) {
        if (!ordersRepository.existsById(orderId)) {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
        ordersRepository.deleteById(orderId);
    }


    // ✅ Delete all orders for a specific employee

}
