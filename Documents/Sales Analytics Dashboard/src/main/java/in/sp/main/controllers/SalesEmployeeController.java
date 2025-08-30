package in.sp.main.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.dto.OrdersDTO;
import in.sp.main.dto.SalesEmployeeDTO;
import in.sp.main.services.SalesEmployeeServices;


@CrossOrigin(origins = "*") 
@RestController
public class SalesEmployeeController 
{
	@Autowired
	SalesEmployeeServices salesEmployeeServices;
	
	
	@GetMapping("/get-api/sales-employees")
	
	List<SalesEmployeeDTO> getAllSalesEmployeesData()
	{
		
		return salesEmployeeServices.getAllEmployees();
	}
	
	@GetMapping("/get-api/sales-employee/{employee_id}")
	ResponseEntity<SalesEmployeeDTO> getSalesEmployeeById(@PathVariable long employee_id)
	{
		
		return ResponseEntity.ok(salesEmployeeServices.getEmployeeById(employee_id));
	}	
		
	
	@PostMapping("/post-api/sales-employee")
	ResponseEntity<SalesEmployeeDTO> insertSalesEmployeeDTO(@RequestBody SalesEmployeeDTO salesEmployeeDTO )
	{
		
		 SalesEmployeeDTO insertSalesEmployeeDTO = salesEmployeeServices.insertEmployeeDTO(salesEmployeeDTO);
		
		 return ResponseEntity.ok(insertSalesEmployeeDTO);
	}
	
	@PostMapping("/post-api/sales-employees")
	ResponseEntity<List<SalesEmployeeDTO>> insertSalesEmployeesDTO(@RequestBody List<SalesEmployeeDTO> salesEmployeesDTO )
	{
		
		 List<SalesEmployeeDTO> insertSalesEmployeeDTO = salesEmployeeServices.insertEmployeesDTO(salesEmployeesDTO);
		
		 return ResponseEntity.ok(insertSalesEmployeeDTO);
	}	
    @PutMapping("/put-api/sales-employee/{employeeId}/orders")
    public ResponseEntity<SalesEmployeeDTO> addOrdersToEmployee(@PathVariable("employeeId") long employeeId, @RequestBody Set<OrdersDTO> ordersDTOs) {
        
        SalesEmployeeDTO updatedEmployee = salesEmployeeServices.insertEmployeeOrdersById(employeeId, ordersDTOs);
        return ResponseEntity.ok(updatedEmployee);
    }
    
    @PatchMapping("/patch-api/sales-employee")
    
    public ResponseEntity<SalesEmployeeDTO> updateSalesEmployeePartially(@RequestBody SalesEmployeeDTO salesEmployeeDTO)
    {
    	
    	return ResponseEntity.ok(salesEmployeeServices.updateSalesEmployeePartially(salesEmployeeDTO));
    }

	@DeleteMapping("/delete-api/sales-employees")
	ResponseEntity<Void> deleteAllEmployees()
	{
		salesEmployeeServices.deleteAllEmployees();
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/delete-api/sales-employee/{employee_id}")
	ResponseEntity<Void> deleteEmployeeId(@PathVariable long employee_id)
	{
		salesEmployeeServices.deleteEmployeeById(employee_id);
		System.out.println("Deleting employee with ID: " + employee_id);

		return ResponseEntity.noContent().build();
	}	
	
	
	
	
	

}
