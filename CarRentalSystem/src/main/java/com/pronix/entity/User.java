package com.pronix.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4943156629533899022L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Primary key for User
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	@OneToMany(mappedBy = "user") // One user can have multiple bookings
	private List<Booking> bookings; // List of bookings associated with the user

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "cust_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	Set<Role> roles = new HashSet<Role>();
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRole(Role role) {
		this.roles.add(role);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Authority> authorities = new HashSet<>();
		this.roles.forEach(userRole -> {
			authorities.add(new Authority(userRole.getRole()));
		});
		return authorities;
	}

	@Override
	public String getUsername() {
		
		return this.email;
	}
	
	
	
}
