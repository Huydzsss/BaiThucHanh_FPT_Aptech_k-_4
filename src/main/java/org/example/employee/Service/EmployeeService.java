package org.example.employee.Service;

import org.example.employee.Model.Employee;
import org.example.employee.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existingEmployee = repository.findById(id).orElse(null);
        if (existingEmployee != null) {
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setSalary(updatedEmployee.getSalary());
            existingEmployee.setAge(updatedEmployee.getAge());
            return repository.save(existingEmployee);
        }
        return null;
    }


    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    public List<Employee> searchEmployees(String name) {
        return repository.searchByName(name);
    }
}
