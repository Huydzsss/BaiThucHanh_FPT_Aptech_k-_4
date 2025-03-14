package org.example.employee.Controller;

import jakarta.validation.Valid;
import org.example.employee.Model.Employee;
import org.example.employee.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping
    public String getEmployees(Model model) {
        model.addAttribute("employees", service.getAllEmployees());
        return "employee_list";
    }

    @GetMapping("/new")
    public String newEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee_form";
    }

    @PostMapping
    public String addEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "employee_form";
        }
        service.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        model.addAttribute("employee", service.getEmployeeById(id));
        return "employee_form";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") @Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "employee_form";
        }
        Employee updatedEmployee = service.updateEmployee(id, employee);
        if (updatedEmployee == null) {
            return "redirect:/employees?error=notfound";
        }
        return "redirect:/employees";
    }


    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return "redirect:/employees";
    }

    @GetMapping("/search")
    public String searchEmployee(@RequestParam String name, Model model) {
        model.addAttribute("employees", service.searchEmployees(name));
        return "employee_list";
    }
}
