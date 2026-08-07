package com.wexa.jobreferralnetwork.model;

public class ReferralRequest {

    private String fromId;
    private String toId;

    public ReferralRequest() {
    }

    public ReferralRequest(String fromId, String toId) {
        this.fromId = fromId;
        this.toId = toId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }
}