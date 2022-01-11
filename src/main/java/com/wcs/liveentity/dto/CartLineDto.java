package com.wcs.liveentity.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CartLineDto {

	@NotNull
	@Min(1)
	private Integer quantity;

	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 6, fraction = 2)
	private Float total;
}
