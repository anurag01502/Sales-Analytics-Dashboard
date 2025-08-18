package in.sp.main.mapper;

import in.sp.main.dto.OrdersDTO;
import in.sp.main.entities.Orders;

public class OrdersMapper {

    /**
     * Converts an Orders entity to its DTO representation.
     *
     * @param orders the Orders entity
     * @return OrdersDTO
     */
    public static OrdersDTO toDTO(Orders orders) {
        if (orders == null) {
            return null;
        }

        OrdersDTO dto = new OrdersDTO();

        dto.setOrder_id(orders.getOrder_id());
        dto.setOrder_code(orders.getOrder_code());
        dto.setOrder_type(orders.getOrder_type());
        dto.setItem_code(orders.getItem_code());
        dto.setBilling_address(orders.getBilling_address());
        dto.setCreated_at(orders.getCreated_at());
        dto.setCurrency(orders.getCurrency());
        dto.setOrder_amount(orders.getOrder_amount());
        dto.setOrderDate(orders.getOrderDate());
        dto.setPayment_method(orders.getPayment_method());
        dto.setShipping_cost(orders.getShipping_cost());
        dto.setShipping_method(orders.getShipping_method());
        dto.setStatus(orders.getStatus());
        dto.setTracking_number(orders.getTracking_number());
        dto.setProduct_name(orders.getProduct_name());

        if (orders.getSalesEmployee() != null) {
            dto.setSalesEmployeeDTO(
                SalesEmployeeMapper.toDTO(orders.getSalesEmployee(), false)
            );
        }

        return dto;
    }

    /**
     * Converts an OrdersDTO to its entity representation.
     *
     * @param dto the OrdersDTO
     * @return Orders entity
     */
    public static Orders toEntity(OrdersDTO dto) {
        if (dto == null) {
            return null;
        }

        Orders entity = new Orders();

        entity.setOrder_id(dto.getOrder_id());
        entity.setOrder_code(dto.getOrder_code());
        entity.setOrder_type(dto.getOrder_type());
        entity.setItem_code(dto.getItem_code());
        entity.setBilling_address(dto.getBilling_address());
        entity.setCreated_at(dto.getCreated_at());
        entity.setCurrency(dto.getCurrency());
        entity.setOrder_amount(dto.getOrder_amount());
        entity.setOrderDate(dto.getOrderDate());
        entity.setPayment_method(dto.getPayment_method());
        entity.setShipping_cost(dto.getShipping_cost());
        entity.setShipping_method(dto.getShipping_method());
        entity.setStatus(dto.getStatus());
        entity.setTracking_number(dto.getTracking_number());
        entity.setProduct_name(dto.getProduct_name());

        if (dto.getSalesEmployeeDTO() != null) {
            entity.setSalesEmployee(SalesEmployeeMapper.toEntity(dto.getSalesEmployeeDTO()));
        }

        return entity;
    }
}
