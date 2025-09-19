package in.sp.main.services;

import java.util.List;
import java.util.Set;

import in.sp.main.customexceptions.EmailIsNullException;
import in.sp.main.customexceptions.JoinedDateNullException;
import in.sp.main.customexceptions.NameIsNullException;
import in.sp.main.dto.OrdersDTO;
import in.sp.main.dto.SalesEmployeeDTO;
import jakarta.transaction.Transactional;

public interface SalesEmployeeServices {

	
	@Transactional
	SalesEmployeeDTO insertEmployeeDTO(SalesEmployeeDTO salesEmployeeDTO ) throws NameIsNullException;
	
	@Transactional
	List<SalesEmployeeDTO> insertEmployeesDTO(List<SalesEmployeeDTO> salesEmployeeDTO);
	
	
	@Transactional
	void deleteAllEmployees();
	
	List<SalesEmployeeDTO> getAllEmployees();
	
	
	@Transactional
	void deleteEmployeeById(long employee_Id);
	
	SalesEmployeeDTO getEmployeeById(long employee_id);
	
	@Transactional
	
	SalesEmployeeDTO insertEmployeeOrdersById(long employee_id, Set<OrdersDTO> ordersDTOs);
	
	
	@Transactional
	SalesEmployeeDTO updateSalesEmployeePartially(SalesEmployeeDTO salesEmployeeDTO ) throws NameIsNullException, EmailIsNullException, JoinedDateNullException;
	
	
}
