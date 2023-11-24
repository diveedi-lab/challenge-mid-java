package com.diveedi.rest.pagination;

import java.util.List;

public class PaginatedResponse<T> {
    private List<T> data;
    private int page;
    private long totalPages;
    private long totalItems;

    public PaginatedResponse() {
    }

    public PaginatedResponse(List<T> data, int page, long totalPages, long totalItems) {
        this.data = data;
        this.page = page;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }
}
