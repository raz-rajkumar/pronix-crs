package com.pronix.entity;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8118938314551268468L;
	private String authority;
	
	@Override
	public String getAuthority() {
		
		return authority;
	}

}
