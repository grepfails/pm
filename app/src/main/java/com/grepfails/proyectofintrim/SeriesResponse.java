package com.grepfails.proyectofintrim;

import java.util.List;

/**
 * Created by grep on 12/12/2016.
 */

public class SeriesResponse {
    private String page;
    private List<Serie> results;
    private int total_results;
    private int total_pages;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Serie> getResults() {
        return results;
    }

    public void setResults(List<Serie> results) {
        this.results = results;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

}
