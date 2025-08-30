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

    @Override
    public OrdersDTO updateOrdersPartially(OrdersDTO ordersDTO) {
        Orders existingOrder = ordersRepository.findById(ordersDTO.getOrder_id())
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + ordersDTO.getOrder_id()));

        // Apply partial updates only if field is not null
        if (ordersDTO.getBilling_address() != null) {
            existingOrder.setBilling_address(ordersDTO.getBilling_address());
        }
        if (ordersDTO.getCreated_at() != null) {
            existingOrder.setCreated_at(ordersDTO.getCreated_at());
        }
        if (ordersDTO.getCurrency() != null) {
            existingOrder.setCurrency(ordersDTO.getCurrency());
        }
        if (ordersDTO.getItem_code() != null) {
            existingOrder.setItem_code(ordersDTO.getItem_code());
        }
        if (ordersDTO.getOrder_amount() != null) {
            existingOrder.setOrder_amount(ordersDTO.getOrder_amount());
        }
        if (ordersDTO.getOrder_code() != null) {
            existingOrder.setOrder_code(ordersDTO.getOrder_code());
        }
        if (ordersDTO.getOrder_type() != null) {
            existingOrder.setOrder_type(ordersDTO.getOrder_type());
        }
        if (ordersDTO.getOrderDate() != null) {
            existingOrder.setOrderDate(ordersDTO.getOrderDate());
        }
        if (ordersDTO.getPayment_method() != null) {
            existingOrder.setPayment_method(ordersDTO.getPayment_method());
        }
        if (ordersDTO.getProduct_name() != null) {
            existingOrder.setProduct_name(ordersDTO.getProduct_name());
        }
        if (ordersDTO.getShipping_cost() != null) {
            existingOrder.setShipping_cost(ordersDTO.getShipping_cost());
        }
        if (ordersDTO.getShipping_method() != null) {
            existingOrder.setShipping_method(ordersDTO.getShipping_method());
        }
        if (ordersDTO.getStatus() != null) {
            existingOrder.setStatus(ordersDTO.getStatus());
        }
        if (ordersDTO.getTracking_number() != null) {
            existingOrder.setTracking_number(ordersDTO.getTracking_number());
        }

        // Save the updated entity
        Orders savedOrder = ordersRepository.save(existingOrder);

        return OrdersMapper.toDTO(savedOrder);
    }



    // ✅ Delete all orders for a specific employee

}
