package controller;

import java.util.Vector;

import model.Employee;
import model.Role;

public class HRDController {
	
	public static Vector<Employee> getAllEmployee(){
		return Employee.getAllEmployee();
	}

	public static String addUser(int id, int roleID, String name, String username, int salary, String status, String password) {
		if(roleID<0) return "roleID must not be empty";
		if(name.isEmpty()) return "name must not be empty";
		if(username.isEmpty()) return "username must not be empty";
		if(salary<=0) return "salary must not be 0 nor negaive value";
		if(password.isEmpty()) password = username;
		
		Employee e = new Employee(Employee.getAllEmployee().size()+1, roleID, name, username, salary, status, password);
		boolean isSuccess = e.add_user();		
		if(!isSuccess) return "add failed"; 
		else return null;
		
	}
	public static String editUser(int id, int roleID, String name, String username, int salary, String status, String password) {
		if(roleID<0) return "roleID must not be empty";
		if(name.isEmpty()) return "name must not be empty";
		if(username.isEmpty()) return "username must not be empty";
		if(salary<=0) return "salary must not be 0 nor negaive value";
		
		Employee e = new Employee(id, roleID, name, username, salary, status, password);
		boolean isSuccess = e.edit_user();
		
		if(!isSuccess) return "edit failed"; 
		else return null;
		}
	
	public static String deleteUser(int id) {
		
		Employee e = new Employee(id, null, null, null, null, "Not Active", null);
		boolean isSuccess = e.delete_user();
		
		if(!isSuccess) return "delete failed"; 
		else return null;		
	}
	
	
	public HRDController() {
		
	}
}
