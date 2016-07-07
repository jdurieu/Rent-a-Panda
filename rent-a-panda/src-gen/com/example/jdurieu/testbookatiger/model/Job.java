package com.example.jdurieu.rentapanda.model;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "JOB".
 */
public class Job {

    private String __status;
    private String customer_name;
    private String distance;
    private java.util.Date job_date;
    private String extras;
    private String order_duration;
    private String order_id;
    private String order_time;
    private String payment_method;
    private String price;
    private Integer recurrency;
    private String job_city;
    private String job_latitude;
    private String job_longitude;
    private Integer job_postalcode;
    private String job_street;
    private String status;

    public Job() {
    }

    public Job(String order_id) {
        this.order_id = order_id;
    }

    public Job(String __status, String customer_name, String distance, java.util.Date job_date, String extras, String order_duration, String order_id, String order_time, String payment_method, String price, Integer recurrency, String job_city, String job_latitude, String job_longitude, Integer job_postalcode, String job_street, String status) {
        this.__status = __status;
        this.customer_name = customer_name;
        this.distance = distance;
        this.job_date = job_date;
        this.extras = extras;
        this.order_duration = order_duration;
        this.order_id = order_id;
        this.order_time = order_time;
        this.payment_method = payment_method;
        this.price = price;
        this.recurrency = recurrency;
        this.job_city = job_city;
        this.job_latitude = job_latitude;
        this.job_longitude = job_longitude;
        this.job_postalcode = job_postalcode;
        this.job_street = job_street;
        this.status = status;
    }

    public String get__status() {
        return __status;
    }

    public void set__status(String __status) {
        this.__status = __status;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public java.util.Date getJob_date() {
        return job_date;
    }

    public void setJob_date(java.util.Date job_date) {
        this.job_date = job_date;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getOrder_duration() {
        return order_duration;
    }

    public void setOrder_duration(String order_duration) {
        this.order_duration = order_duration;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getRecurrency() {
        return recurrency;
    }

    public void setRecurrency(Integer recurrency) {
        this.recurrency = recurrency;
    }

    public String getJob_city() {
        return job_city;
    }

    public void setJob_city(String job_city) {
        this.job_city = job_city;
    }

    public String getJob_latitude() {
        return job_latitude;
    }

    public void setJob_latitude(String job_latitude) {
        this.job_latitude = job_latitude;
    }

    public String getJob_longitude() {
        return job_longitude;
    }

    public void setJob_longitude(String job_longitude) {
        this.job_longitude = job_longitude;
    }

    public Integer getJob_postalcode() {
        return job_postalcode;
    }

    public void setJob_postalcode(Integer job_postalcode) {
        this.job_postalcode = job_postalcode;
    }

    public String getJob_street() {
        return job_street;
    }

    public void setJob_street(String job_street) {
        this.job_street = job_street;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
