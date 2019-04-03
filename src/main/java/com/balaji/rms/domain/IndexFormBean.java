package com.balaji.rms.domain;

import com.balaji.rms.validators.DateCheck;

import javax.validation.constraints.NotNull;

@DateCheck.List(
        {
                @DateCheck(
                        startDate = "startDate",
                        endDate = "endDate"
                )
        }
)
public class IndexFormBean {

    @NotNull
    private String startDate;
    @NotNull
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
