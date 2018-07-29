package com.cheb.inventorybook.report.dto;

import com.cheb.inventorybook.report.ReportVariable;

import java.util.List;

public class ReportBoxTableDto {

    private List<ReportBox> boxes;

    public ReportBoxTableDto(List<ReportBox> boxes) {
        this.boxes = boxes;
    }

    @ReportVariable("box")
    public List<ReportBox> getBoxes() {
        return boxes;
    }

    public void setBoxes(List<ReportBox> boxes) {
        this.boxes = boxes;
    }
}
