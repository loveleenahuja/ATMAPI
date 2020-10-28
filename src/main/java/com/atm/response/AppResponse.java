package com.atm.response;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AppResponse {
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

    public AppResponse(String message, HttpStatus status){
        this.status = status;
        this.message = message;
    }

    public ResponseEntity<Object> build(){
        JSONObject resp = new JSONObject();
        resp.put("message", this.getMessage());
        return new ResponseEntity<>(resp, this.getStatus());
    }
}
