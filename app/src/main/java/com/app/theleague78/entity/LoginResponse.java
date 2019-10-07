package com.app.theleague78.entity;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {


    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @SerializedName("statusCode")
    private Integer statusCode;

    @SerializedName("status")
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResponseObject getRespObject() {
        return respObject;
    }

    public void setRespObject(ResponseObject respObject) {
        this.respObject = respObject;
    }

    @SerializedName("respObject")
    private ResponseObject respObject;

    private class ResponseObject implements Serializable{

        @SerializedName("customer_id")
        private String customerId;

        @SerializedName("customer_name")
        private String customerName;

        @SerializedName("email")
        private String email;

        @SerializedName("phone_number")
        private String phoneNumber;

        @SerializedName("password")
        private String password;

        @SerializedName("status")
        private String status;

        @SerializedName("role")
        private String role;

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getResetPwdFlag() {
            return resetPwdFlag;
        }

        public void setResetPwdFlag(String resetPwdFlag) {
            this.resetPwdFlag = resetPwdFlag;
        }

        @SerializedName("reset_pwd_flag")
        private String resetPwdFlag;


    }
}
