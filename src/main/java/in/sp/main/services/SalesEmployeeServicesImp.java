package in.sp.main.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.customexceptions.EmailIsNullException;
import in.sp.main.customexceptions.JoinedDateNullException;
import in.sp.main.customexceptions.NameIsNullException;
import in.sp.main.dto.OrdersDTO;
import in.sp.main.dto.SalesEmployeeDTO;
import in.sp.main.entities.Orders;
import in.sp.main.entities.SalesEmployee;
import in.sp.main.mapper.OrdersMapper;
import in.sp.main.mapper.SalesEmployeeMapper;
import in.sp.main.repository.OrdersRepository;
import in.sp.main.repository.SalesEmployeeRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SalesEmployeeServicesImp implements SalesEmployeeServices {

	
	@Autowired
	OrdersRepository orderRepository;
	
	@Autowired
	SalesEmployeeRepository salesEmployeeRepository;

	
	@Autowired
	OrdersServices ordersServices;
  
	
	@Override
	public SalesEmployeeDTO insertEmployeeDTO(SalesEmployeeDTO salesEmployeeDTO) throws NameIsNullException {
		
		
		
	
		SalesEmployee salesEmployee = SalesEmployeeMapper.toEntity(salesEmployeeDTO);	
		
		if(salesEmployee.getEmployeeName().isBlank() || salesEmployee.getEmployeeName().isEmpty() || salesEmployee.getEmployeeName()==null)
		{
			throw new NameIsNullException("Name cannot be Blank, Null (or) Empty");
		}
		
		SalesEmployee insertSalesEmployee = salesEmployeeRepository.save(salesEmployee);
				
		return  SalesEmployeeMapper.toDTO(insertSalesEmployee, true);
	}

	@Override
	public List<SalesEmployeeDTO> insertEmployeesDTO(List<SalesEmployeeDTO> salesEmployeesDTO) {
		
		
		List<SalesEmployee> ListOfSalesEmployees = new ArrayList<SalesEmployee>();
		
		for(SalesEmployeeDTO eachline:salesEmployeesDTO)
		{
			ListOfSalesEmployees.add(SalesEmployeeMapper.toEntity(eachline));
		}
		
		List<SalesEmployee> insertSalesEmployees = salesEmployeeRepository.saveAll(ListOfSalesEmployees);
		
		List<SalesEmployeeDTO> insertEmployeeDTOs = new ArrayList<SalesEmployeeDTO>();
		
		for(SalesEmployee eachline :insertSalesEmployees)
		{
			insertEmployeeDTOs.add(SalesEmployeeMapper.toDTO(eachline,true));
		}
		return insertEmployeeDTOs;
	}

	@Override
	public List<SalesEmployeeDTO> getAllEmployees() {
		
		List<SalesEmployee> salesEmployees = salesEmployeeRepository.findAll();
		
		List<SalesEmployeeDTO> salesEmployeeDTOs = new ArrayList<SalesEmployeeDTO>();
		
		
		for(SalesEmployee lineitem:salesEmployees)
		{
			salesEmployeeDTOs.add(SalesEmployeeMapper.toDTO(lineitem,true));
		}
		return salesEmployeeDTOs;
	}

	@Override
	public SalesEmployeeDTO getEmployeeById(long employee_id) {
		
		SalesEmployee salesEmployee = salesEmployeeRepository.findById(employee_id).get();
		
		
		return SalesEmployeeMapper.toDTO(salesEmployee, true);
	}

	@Override
	public void deleteAllEmployees() {
		
		
		salesEmployeeRepository.deleteAll();
		
	}

	@Override
	public void deleteEmployeeById(long employeeId) {
	    if (!salesEmployeeRepository.existsById(employeeId)) {
	        throw new EntityNotFoundException("SalesEmployee with ID " + employeeId + " not found.");
	    }

	    salesEmployeeRepository.deleteById(employeeId);
	    System.out.println("Deleted employee with ID: " + employeeId);
	}


	@Override
	public SalesEmployeeDTO insertEmployeeOrdersById(long employee_id, Set<OrdersDTO> ordersDTOs) {

	    Optional<SalesEmployee> optionalEmployee = salesEmployeeRepository.findById(employee_id);

	    if (optionalEmployee.isPresent()) {
	        SalesEmployee salesEmployee = optionalEmployee.get();

	        // Convert DTOs to Entities and set salesEmployee
	        Set<Orders> orderEntities = ordersDTOs.stream().map(dto -> {
	            Orders order = OrdersMapper.toEntity(dto);
	            order.setSalesEmployee(salesEmployee);
	            return order;
	        }).collect(Collectors.toSet());

	        // Add new orders to existing orders
	        if (salesEmployee.getOrders() != null) {
	            salesEmployee.getOrders().addAll(orderEntities);
	        } else {
	            salesEmployee.setOrders(orderEntities);
	        }

	        // Save updated employee with new orders
	        SalesEmployee updatedEmployee = salesEmployeeRepository.save(salesEmployee);

	        return SalesEmployeeMapper.toDTO(updatedEmployee, false);
	    }

	    throw new EntityNotFoundException("SalesEmployee with ID " + employee_id + " not found.");
	}

	@Override
	public SalesEmployeeDTO updateSalesEmployeePartially(SalesEmployeeDTO salesEmployeeDTO) throws NameIsNullException, EmailIsNullException, JoinedDateNullException {

	    SalesEmployee salesEmployee = salesEmployeeRepository.findById(salesEmployeeDTO.getEmployeeId())
	            .orElseThrow(() -> new RuntimeException(
	                    "Employee not found with ID: " + salesEmployeeDTO.getEmployeeId()));

	    
	    if (salesEmployeeDTO.getEmployeeName() == null 
	            || salesEmployeeDTO.getEmployeeName().isBlank() 
	            ) {
	        throw new NameIsNullException("Name cannot be Blank, Null or Empty!");
	    }

	    
	    
	    if(salesEmployeeDTO.getEmployeeEmail()==null || salesEmployeeDTO.getEmployeeName().isEmpty())
	    {
	    	
	    	throw new EmailIsNullException("Email cnnot be blank or Null!");
	    }
	    if(salesEmployeeDTO.getJoinedDate().equals(null))
	    {
	    	throw new JoinedDateNullException("Joined date cannot be Null");
	    	
	    }
	    
	    
	    
	    Set<OrdersDTO> ordersDTO = new HashSet<>();
	    if (salesEmployeeDTO.getCurrency() != null) {
	        salesEmployee.setCurrency(salesEmployeeDTO.getCurrency());
	    }
	    if (salesEmployeeDTO.getEmployeeEmail() != null) {
	        salesEmployee.setEmployeeEmail(salesEmployeeDTO.getEmployeeEmail());
	    }
	    if (salesEmployeeDTO.getEmployeeName() != null) {
	        salesEmployee.setEmployeeName(salesEmployeeDTO.getEmployeeName());
	    }
	    if (salesEmployeeDTO.getJoinedDate() != null) {
	        salesEmployee.setJoinedDate(salesEmployeeDTO.getJoinedDate());
	    }
	    if (salesEmployeeDTO.getManager() != null) {
	        salesEmployee.setManager(salesEmployeeDTO.getManager());
	    }
	    if (salesEmployeeDTO.getStatus() != null) {
	        salesEmployee.setStatus(salesEmployeeDTO.getStatus());
	    }
	    if (salesEmployeeDTO.getTargetQuota() != null) {  // changed from != 0
	        salesEmployee.setTargetQuota(salesEmployeeDTO.getTargetQuota());
	    }

	    if (!salesEmployeeDTO.getOrders().isEmpty()) {
	        
	        for (OrdersDTO lineitems : salesEmployeeDTO.getOrders()) {
	            
	            ordersDTO.add(ordersServices.updateOrdersPartially(lineitems));
	        }
	        
	    }
	    salesEmployeeDTO.setOrders(ordersDTO);
	    salesEmployeeRepository.save(SalesEmployeeMapper.toEntity(salesEmployeeDTO));
	    return salesEmployeeDTO;
	}


}