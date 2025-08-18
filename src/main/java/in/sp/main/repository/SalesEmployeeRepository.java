package in.sp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entities.SalesEmployee;

public interface SalesEmployeeRepository extends JpaRepository<SalesEmployee, Long> {

}
