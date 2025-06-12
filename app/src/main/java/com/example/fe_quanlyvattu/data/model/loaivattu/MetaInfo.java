package com.example.fe_quanlyvattu.data.model.loaivattu;

public class MetaInfo {
    private int currentPage;
    private int itemPerPage;
    private int totalItems;
    private int totalPages;

    public MetaInfo(int currentPage, int itemPerPage, int totalItems, int totalPages) {
        this.currentPage = currentPage;
        this.itemPerPage = itemPerPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }
// Getters và setters

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}

