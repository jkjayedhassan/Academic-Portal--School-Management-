package com.idb.crud.dto;
import com.idb.crud.contstants.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private ResponseStatus status;
    private String message;
    private T data;

    public Response (ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}
