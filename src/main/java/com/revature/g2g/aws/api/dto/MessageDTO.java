package com.revature.g2g.aws.api.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MessageDTO {
	@NotNull
	private String message;
	
	public MessageDTO(String message) {
		super();
		this.message = message;
	}
}