package com.example.fe_quanlyvattu.data.model.phieunhap;

import java.util.List;

public class CreatePhieuNhapRequest {
    private Supplier supplier;
    private List<Item> items;
    private String dateOfReceived;
    private String dateOfOrder;
    private String note;

    public CreatePhieuNhapRequest(Supplier supplier, List<Item> items, String dateOfReceived, String dateOfOrder, String note) {
        this.supplier = supplier;
        this.items = items;
        this.dateOfReceived = dateOfReceived;
        this.dateOfOrder = dateOfOrder;
        this.note = note;
    }

    public static class Supplier {
        private int id;

        public Supplier(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class Item {
        private String code;
        private double price;
        private int quantity;

        public Item(String code, double price, int quantity) {
            this.code = code;
            this.price = price;
            this.quantity = quantity;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    // Getter & Setter
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getDateOfReceived() {
        return dateOfReceived;
    }

    public void setDateOfReceived(String dateOfReceived) {
        this.dateOfReceived = dateOfReceived;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

