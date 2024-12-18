package com.pronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pronix.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	public Role findByRole(String role);
}
