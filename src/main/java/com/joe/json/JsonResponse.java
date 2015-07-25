package com.joe.json;

/**
 * Created by Joseph on 25/07/2015.
 */
public class JsonResponse {

    private String status = "";
    private String errorMessage = "";

    public JsonResponse(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
