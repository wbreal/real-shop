package com.shop.model.response.error;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class ErrorResponse {
    private String Message;

}
