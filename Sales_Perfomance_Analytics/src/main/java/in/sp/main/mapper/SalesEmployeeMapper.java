package in.sp.main.mapper;

import java.util.stream.Collectors;

import in.sp.main.dto.SalesEmployeeDTO;
import in.sp.main.entities.SalesEmployee;

public class SalesEmployeeMapper {

    /**
     * Converts a SalesEmployee entity to a DTO.
     *
     * @param salesEmployee  the entity to convert
     * @param includeOrders  whether to include the associated orders in the DTO
     * @return the DTO representation
     */
    public static SalesEmployeeDTO toDTO(SalesEmployee salesEmployee, boolean includeOrders) {
        if (salesEmployee == null) {
            return null;
        }

        SalesEmployeeDTO dto = new SalesEmployeeDTO();

        dto.setEmployeeId(salesEmployee.getEmployeeId());
        dto.setEmployeeName(salesEmployee.getEmployeeName());
        dto.setEmployeeEmail(salesEmployee.getEmployeeEmail());
        dto.setJoinedDate(salesEmployee.getJoinedDate());
        dto.setCurrency(salesEmployee.getCurrency());
        dto.setManager(salesEmployee.getManager());
        dto.setStatus(salesEmployee.getStatus());
        dto.setTargetQuota(salesEmployee.getTargetQuota());

        if (includeOrders && salesEmployee.getOrders() != null) {
            dto.setOrders(
                salesEmployee.getOrders()
                    .stream()
                    .map(OrdersMapper::toDTO)  // assumes OrdersMapper.toDTO(Orders) exists
                    .collect(Collectors.toSet())
            );
        }

        return dto;
    }

    /**
     * Converts a SalesEmployee DTO to an entity.
     *
     * @param dto the DTO to convert
     * @return the entity representation
     */
    public static SalesEmployee toEntity(SalesEmployeeDTO dto) {
        if (dto == null) {
            return null;
        }

        SalesEmployee entity = new SalesEmployee();

        entity.setEmployeeId(dto.getEmployeeId());
        entity.setEmployeeName(dto.getEmployeeName());
        entity.setEmployeeEmail(dto.getEmployeeEmail());
        entity.setJoinedDate(dto.getJoinedDate());
        entity.setCurrency(dto.getCurrency());
        entity.setManager(dto.getManager());
        entity.setStatus(dto.getStatus());
        entity.setTargetQuota(dto.getTargetQuota());

        if (dto.getOrders() != null) {
            entity.setOrders(
                dto.getOrders()
                    .stream()
                    .map(orderDto -> {
                        var order = OrdersMapper.toEntity(orderDto);  // assumes OrdersMapper.toEntity(OrderDTO) exists
                        order.setSalesEmployee(entity); // Ensure bidirectional link is preserved
                        return order;
                    })
                    .collect(Collectors.toSet())
            );
        }

        return entity;
    }
}
