package com.cheb.inventorybook.report.dto;

import com.cheb.inventorybook.report.ReportVariable;

public class ReportItem {

    private String number;
    private String title;
    private String count;
    private String invnum;
    private String country;
    private String weight;
    private String cost;

    public ReportItem(String number, String title, String count, String invnum, String country, String weight, String cost) {
        this.number = number;
        this.title = title;
        this.count = count;
        this.invnum = invnum;
        this.country = country;
        this.weight = weight;
        this.cost = cost;
    }

    @ReportVariable("num")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @ReportVariable("title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ReportVariable("count")
    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @ReportVariable("invnum")
    public String getInvnum() {
        return invnum;
    }

    public void setInvnum(String invnum) {
        this.invnum = invnum;
    }

    @ReportVariable("country")
    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    @ReportVariable("weight")
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @ReportVariable("cost")
    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
