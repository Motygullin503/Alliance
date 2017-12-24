package ru.kpfu.itis.alliance.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Bulat on 05.12.2017 at 19:48.
 */

public class CalculationResult implements Serializable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("result_value")
    @Expose
    private Integer resultValue;
    @SerializedName("total_price")
    @Expose
    private Double totalPrice;

    public CalculationResult(String title, Integer resultValue, Double totalPrice) {
        this.title = title;
        this.resultValue = resultValue;
        this.totalPrice = totalPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getResultValue() {
        return resultValue;
    }

    public void setResultValue(Integer resultValue) {
        this.resultValue = resultValue;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
