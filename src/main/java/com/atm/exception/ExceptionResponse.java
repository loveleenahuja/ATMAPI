package com.atm.exception;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionResponse {
    private HttpStatus status;
    private String message;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExceptionResponse(String message, HttpStatus status){
        this.status = status;
        this.message = message;
    }

    public ResponseEntity<Object> build(){
        JSONObject resp = new JSONObject();
        resp.put("error", this.getMessage());
        return new ResponseEntity<>(resp, this.getStatus());
    }
}
