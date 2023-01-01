package com.shop.model.request;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@ToString
@Getter
public class PointRequest {
	@NotNull
	@Positive
	private BigDecimal point;

}
