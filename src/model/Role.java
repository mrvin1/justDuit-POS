package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import connect.Connect;

public class Role {
	
	private Integer id;
	private String name;
	private Connect con;
	
	public Role(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static Vector<Role> getAllRole(){
		Vector<Role> results = new Vector<Role>();
		try {
			Statement st = Connect.connect().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM role");
			
			while(rs.next()) {
				results.add(new Role(
							rs.getInt("id"),
							rs.getString("name")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return results;
	}
	
	public static Role getRole(int id) {
		Vector<Role> results = new Vector<Role>();
		String query = "SELECT * FROM role WHERE id = ? ";	
		
		try {
			PreparedStatement ps = Connect.connect().prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				results.add(new Role(
						rs.getInt("id"),
						rs.getString("name")
					));				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results.get(0);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
