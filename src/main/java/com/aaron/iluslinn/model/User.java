package com.aaron.iluslinn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name ="appuser")
@Data
public class User extends AbstractUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private String password;

	@Column(name = "isenabled")
	private boolean isEnabled;

	public User() {
		super(null, null);
	}

	public User(String username, String password, ArrayList<Role> roles) {
		super(username, roles);
		this.password = password;

	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}
	@Override
	public List<Role> getAuthorities() {
		return this.roles;
	}

	public List<Role> getRoles() {
		return roles;
	}


}
