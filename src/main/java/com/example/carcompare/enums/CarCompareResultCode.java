package com.example.carcompare.enums;

import java.io.Serializable;

public enum CarCompareResultCode implements Serializable {
    SUCCESS("00000", "S", "Success"),
    NOT_FOUND("00001", "F", "Entry is not available "),
    DB_ERROR("00002", "F", "Error in Database"),
    FAIL("00003", "f", "failure"),
    PARAM_ILLEGAL("00004", "f", "param illegal");

    private String resultCode;
    private String status;
    private String message;

    private CarCompareResultCode(String resultCode, String status, String message) {
        this.resultCode = resultCode;
        this.status = status;
        this.message = message;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
