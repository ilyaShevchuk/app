package com.stadium.app.responses.Impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stadium.app.responses.IResponse;
import lombok.Getter;

public class DefaultErrorResponse implements IResponse {
    @JsonProperty("message")
    @Getter
    private final String message;

    public DefaultErrorResponse(String message) {
        this.message = message;
    }
}
