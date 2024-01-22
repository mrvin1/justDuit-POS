package controller;

import java.util.Vector;

import model.Employee;

public class EmployeeController {
	
	public static Vector<Employee> getAllEmployee(){
		return Employee.getAllEmployee();
	}
	
	public static int logEmployeeIn (String username, String password) {
				Employee user = new Employee();
		user = Employee.getEmployeeByUsername(username);
		
		if(username.length() > 0 && password.length() > 0) {
			if(user.getPassword() != null && user.getStatus().equals("Active")&& user.getPassword().equals(password)) {
				return user.getRoleID();
			} else {
				return -1;			
			}			
		} else {
			return -2;
		}
	}

	public EmployeeController() {
		
	}
}
