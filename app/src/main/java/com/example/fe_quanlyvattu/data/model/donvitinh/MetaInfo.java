package com.example.fe_quanlyvattu.data.model.donvitinh;

public class MetaInfo {
    private int currentPage;
    private int itemPerPage;
    private int totalItem;
    private int totalPages;

    public MetaInfo(int currentPage, int itemPerPage, int totalItem, int totalPages) {
        this.currentPage = currentPage;
        this.itemPerPage = itemPerPage;
        this.totalItem = totalItem;
        this.totalPages = totalPages;
    }

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

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
