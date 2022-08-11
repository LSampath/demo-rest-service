package com.example.restservice;

import java.util.List;

public class ResponseDTO<T> {
    private int status;
    private String message;
    private List<T> data;

    public ResponseDTO(int status, List<T> data) {
        this.status = status;
        this.data = data;
        this.message = "Successful";
    }

    public ResponseDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
