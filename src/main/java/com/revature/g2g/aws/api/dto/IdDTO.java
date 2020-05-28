package com.revature.g2g.aws.api.dto;

import lombok.Data;

@Data
public class IdDTO {
	private String id;
	private String token;
	public IdDTO(String id, String token) {
		super();
		this.id = id;
		this.token = token;
	}
}