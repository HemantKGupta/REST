import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class JacksonParserExample {

	public JacksonParserExample() {

		this.users = new HashMap<String, User>();
		this.roles = new HashMap<String, Role>();
		this.permissions = new HashMap<String, Permission>();
	}

	public static void main(String[] args) throws JsonParseException,
			IOException {
		JacksonParserExample x = new JacksonParserExample();
		x.readdata();
	}

	public void readdata() throws JsonParseException, IOException {
		JsonFactory jsonfactory = new JsonFactory();

		JsonParser jsonParser = jsonfactory
				.createParser(new File(
						"/media/Hemant/GitRepos/J2EE_WS/TestJavaProject/src/permissions.json"));

		while (jsonParser.nextToken() != null) {
			
			if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {

				Permission permission = new Permission();
				jsonParser.nextToken();
				String fieldname = jsonParser.getCurrentName();
				if ("id".equals(fieldname)) {
					jsonParser.nextToken();
					permission.setId(jsonParser.getText());
				}
				jsonParser.nextToken();
				fieldname = jsonParser.getCurrentName();
				if ("name".equals(fieldname)) {
					jsonParser.nextToken();
					permission.setName(jsonParser.getText());
				}
				jsonParser.nextToken();
				this.permissions.put(permission.getId(), permission);
			}

		}

		jsonParser.close();

		jsonParser = jsonfactory
				.createParser(new File(
						"/media/Hemant/GitRepos/J2EE_WS/TestJavaProject/src/roles.json"));

		while (jsonParser.nextToken() != null) {
			
			if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {

				Role role = new Role();
				jsonParser.nextToken();
				String fieldname = jsonParser.getCurrentName();
				String idValue = null;
				if ("id".equals(fieldname)) {
					jsonParser.nextToken();
					idValue = jsonParser.getText();
					role.setId(idValue);
				}
				jsonParser.nextToken();
				fieldname = jsonParser.getCurrentName();
				if ("permissions".equals(fieldname)) {
					jsonParser.nextToken();
					while (jsonParser.nextToken() != JsonToken.END_ARRAY) {						
						String permissionId = jsonParser.getText();
						System.out.println("permissionId is: " + permissionId);
						Permission permission = this.permissions.get(permissionId);
						if (permission == null) {
							System.out.println("while building permission null");
						} else {
							role.getPermissions().add(permission);
						}
					}
				}
				jsonParser.nextToken();
				this.roles.put(role.getId(), role);
			}

		}
		
		for(Role role: roles.values()){
			System.out.println("in role map role id is :"+role.getId());
			for(Permission permission:role.getPermissions()){
				System.out.println("in role map permission id is :"+permission.getId()+ " and name is: "+permission.getName());
			}
		}
		
		jsonParser.close();

		jsonParser = jsonfactory
				.createParser(new File(
						"/media/Hemant/GitRepos/J2EE_WS/TestJavaProject/src/users.json"));

		while (jsonParser.nextToken() != null) {

			if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
				User user = new User();
				jsonParser.nextToken();
				String fieldname = jsonParser.getCurrentName();
				String idValue = null;
				if ("id".equals(fieldname)) {
					jsonParser.nextToken();
					idValue = jsonParser.getText();
					user.setId(idValue);
				}
				jsonParser.nextToken();
				fieldname = jsonParser.getCurrentName();
				if ("roles".equals(fieldname)) {
					jsonParser.nextToken();
					while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
						String roleId = jsonParser.getText();
						System.out.println("roleId is: " + roleId);
						Role role = this.roles.get(roleId);
						if (role == null) {
							System.out.println("while building role null");
						} else {
							user.getRoles().add(role);
						}
					}
				}
				jsonParser.nextToken();
				this.users.put(user.getId(), user);
			}
		}
		for(User user: users.values()){
			System.out.println("in user map user id is :"+user.getId());
			for(Role role:user.getRoles()){
				System.out.println("in user map permission id is :"+role.getId());{
					for(Permission permission:role.getPermissions()){
						System.out.println("in user map permission id is :"+permission.getId()+ " and name is: "+permission.getName());
					}
				}
			}
		}
		jsonParser.close();

	}

	private Map<String, User> users;
	private Map<String, Role> roles;
	private Map<String, Permission> permissions;

	private String realpath;

	public String getRealpath() {
		return realpath;
	}

	public void setRealpath(String realpath) {
		this.realpath = realpath;
	}

	public Map<String, User> getUsers() {
		return users;
	}

	public void setUsers(Map<String, User> users) {
		this.users = users;
	}

	public Map<String, Role> getRoles() {
		return roles;
	}

	public void setRoles(Map<String, Role> roles) {
		this.roles = roles;
	}

	public Map<String, Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Map<String, Permission> permissions) {
		this.permissions = permissions;
	}

}
