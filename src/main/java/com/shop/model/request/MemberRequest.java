package com.shop.model.request;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
public class MemberRequest {
	@NotBlank
	private String name;

}
