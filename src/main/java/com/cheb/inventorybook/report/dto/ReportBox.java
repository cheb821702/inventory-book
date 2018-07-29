package com.cheb.inventorybook.report.dto;

import com.cheb.inventorybook.report.ReportVariable;

import java.util.List;

public class ReportBox {

    private String number;
    private String tcount;
    private String tweight;
    private String tcost;
    private List<ReportItem> items;

    public ReportBox(String number, String tcount, String tweight, String tcost, List<ReportItem> items) {
        this.number = number;
        this.tcount = tcount;
        this.tweight = tweight;
        this.tcost = tcost;
        this.items = items;
    }

    @ReportVariable("number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @ReportVariable("tcount")
    public String getTcount() {
        return tcount;
    }

    public void setTcount(String tcount) {
        this.tcount = tcount;
    }

    @ReportVariable("tweight")
    public String getTweight() {
        return tweight;
    }

    public void setTweight(String tweight) {
        this.tweight = tweight;
    }

    @ReportVariable("tcost")
    public String getTcost() {
        return tcost;
    }

    public void setTcost(String tcost) {
        this.tcost = tcost;
    }

    @ReportVariable("item")
    public List<ReportItem> getItems() {
        return items;
    }

    public void setItems(List<ReportItem> items) {
        this.items = items;
    }
}
