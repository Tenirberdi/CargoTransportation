package com.cargotransportation.dto.response;

import com.cargotransportation.constants.ResponseState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    ResponseState state;
    int errorCode;
    String message;
    Object result;

    public Response(ResponseState state, int errorCode, Object result) {
        this.state = state;
        this.errorCode = errorCode;
        this.result = result;
    }

    public Response(ResponseState state, int errorCode) {
        this.state = state;
        this.errorCode = errorCode;
    }
}
