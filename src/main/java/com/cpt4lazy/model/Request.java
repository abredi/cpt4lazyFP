package com.cpt4lazy.model;

import com.cpt4lazy.utility.LocalDateDeserializer;
import com.cpt4lazy.utility.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;

public class Request {
    private String refStatus;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate reqDate;
    private String postedBy;
    private String requestSentBy;

    public Request(){}
    public Request(String refStatus, LocalDate reqDate, String postedBy, String requestSentBy) {
        this.refStatus = refStatus;
        this.reqDate = reqDate;
        this.postedBy = postedBy;
        this.requestSentBy = requestSentBy;
    }

    public String getRefStatus() {
        return refStatus;
    }

    public void setRefStatus(String refStatus) {
        this.refStatus = refStatus;
    }

    public LocalDate getReqDate() {
        return reqDate;
    }

    public void setReqDate(LocalDate reqDate) {
        this.reqDate = reqDate;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getRequestSentBy() {
        return requestSentBy;
    }

    public void setRequestSentBy(String requestSentBy) {
        this.requestSentBy = requestSentBy;
    }

    @Override
    public String toString() {
        return "Request{" +
                "refStatus='" + refStatus + '\'' +
                ", reqDate=" + reqDate +
                ", postedBy='" + postedBy + '\'' +
                ", requestSentBy='" + requestSentBy + '\'' +
                '}';
    }
}
