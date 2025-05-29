package com.example.fe_quanlyvattu.data.model.phieunhap;

public class PhieuNhap {
    private int id;
    private String dateOfOrder;
    private String dateOfReceived;
    private String dateOfActualReceived;
    private String status;
    private String note;
    private Supplier supplier;
    private RequestedUser requestedUser;

    public PhieuNhap(int id, String dateOfOrder, String dateOfReceived, String dateOfActualReceived, String status, String note, Supplier supplier, RequestedUser requestedUser) {
        this.id = id;
        this.dateOfOrder = dateOfOrder;
        this.dateOfReceived = dateOfReceived;
        this.dateOfActualReceived = dateOfActualReceived;
        this.status = status;
        this.note = note;
        this.supplier = supplier;
        this.requestedUser = requestedUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getDateOfReceived() {
        return dateOfReceived;
    }

    public void setDateOfReceived(String dateOfReceived) {
        this.dateOfReceived = dateOfReceived;
    }

    public String getDateOfActualReceived() {
        return dateOfActualReceived;
    }

    public void setDateOfActualReceived(String dateOfActualReceived) {
        this.dateOfActualReceived = dateOfActualReceived;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public RequestedUser getRequestedUser() {
        return requestedUser;
    }

    public void setRequestedUser(RequestedUser requestedUser) {
        this.requestedUser = requestedUser;
    }
}