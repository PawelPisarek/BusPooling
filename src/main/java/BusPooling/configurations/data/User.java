/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package BusPooling.configurations.data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
public class User {
	private String id;

	private String username;

	private String password;

	private Set<Role> roles = new HashSet<Role>();

	private String birthdate;

	private String gender;

	private ArrayList interests = new ArrayList();

	private ArrayList culinaryPreferences = new ArrayList();

	private boolean active = false;

	public User() {
	}

	public User(String id, String username, String password, String birthdate, String gender, ArrayList interests, ArrayList culinaryPreferences, boolean active) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.birthdate = birthdate;
		this.gender = gender;
		this.interests = interests;
		this.culinaryPreferences = culinaryPreferences;
		this.active = active;
	}

	public User(User user) {
		super();
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.roles = user.getRoles();
		this.birthdate = user.getBirthdate();
		this.gender = user.getGender();
		this.interests = user.getInterests();
		this.culinaryPreferences = user.getCulinaryPreferences();
		this.active = user.active;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		roles.add(role);
	}

	public ArrayList getInterests() {
		return interests;
	}

	public void setInterests(ArrayList interests) {
		this.interests = interests;
	}

	public ArrayList getCulinaryPreferences() {
		return culinaryPreferences;
	}

	public void setCulinaryPreferences(ArrayList culinaryPreferences) {
		this.culinaryPreferences = culinaryPreferences;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
