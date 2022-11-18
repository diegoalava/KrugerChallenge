package com.example.kruger.model;

import org.springframework.http.HttpStatus;

/**
 *
 * @author Diego.Alava
 */
public class Response {

    private Object info;
    private HttpStatus status;

    public Response() {
    }

    public Response(Object info, HttpStatus status) {
        this.info = info;
        this.status = status;
    }
    
    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
