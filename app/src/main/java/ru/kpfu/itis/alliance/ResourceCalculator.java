package ru.kpfu.itis.alliance;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ru.kpfu.itis.alliance.models.CalculationResult;
import ru.kpfu.itis.alliance.models.Data;
import ru.kpfu.itis.alliance.models.Product;

/**
 * Created by Bulat on 05.12.2017 at 00:14.
 */

public class ResourceCalculator {

    Data data;
    Context context;
    Double totalPrice;
    List<CalculationResult> list;
    private double wallsPerimetr;
    private double buildingHeight;
    private double windowSquare;
    private int windowsCount;
    private double doorSquare;
    private int doorsCount;
    private int mode;


    public ResourceCalculator(Context context, double wallsPerimetr, double buildingHeight, double windowSquare, int windowsCount, double doorSquare, int doorsCount, int mode) {
        this.context = context;
        this.wallsPerimetr = wallsPerimetr;
        this.buildingHeight = buildingHeight;
        this.windowSquare = windowSquare;
        this.windowsCount = windowsCount;
        this.doorSquare = doorSquare;
        this.doorsCount = doorsCount;
        this.mode = mode;
    }

    public void parseJson() throws IOException {

        String json;
        InputStream is = context.getAssets().open("products.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");

        data = new Gson().fromJson(json, Data.class);

    }

    public double getTotalArea() {
        double area;
        area = wallsPerimetr * buildingHeight - (doorSquare * doorsCount + windowSquare * windowsCount);
        if (area > 0)
            return area;
        else {

            return 0;
        }
    }

    public List<CalculationResult> getCalculationResults() throws IOException {
        list = new ArrayList<>();
        switch (mode) {
            case 0:

                for (Product product : data.getData().get(0).getProducts()) {
                    double resultValue = product.getCoefficient() * getTotalArea();

                    list.add(new CalculationResult(product.getName(), (int) Math.ceil(resultValue), new BigDecimal(product.getPrice() * resultValue).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
                }

                break;

            case 1:

                for (Product product : data.getData().get(1).getProducts()) {
                    double resultValue = product.getCoefficient() * getTotalArea();

                    list.add(new CalculationResult(product.getName(), (int) Math.ceil(resultValue), new BigDecimal(product.getPrice() * resultValue).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
                }

                break;

            case 2:

                for (Product product : data.getData().get(2).getProducts()) {
                    double resultValue = product.getCoefficient() * getTotalArea();

                    list.add(new CalculationResult(product.getName(), (int) Math.ceil(resultValue), new BigDecimal(product.getPrice() * resultValue).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
                }

                break;

            case 3:

                for (Product product : data.getData().get(3).getProducts()) {
                    double resultValue = product.getCoefficient() * getTotalArea();

                    list.add(new CalculationResult(product.getName(), (int) Math.ceil(resultValue), new BigDecimal(product.getPrice() * resultValue).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
                }

                break;

        }

        return list;
    }

    public Double getPricePerM() {
        if (getTotalArea() == 0.0) {
            Toast.makeText(context, "Общая площадь отрицательная или равна нулю", Toast.LENGTH_SHORT).show();
            return BigDecimal.ZERO.doubleValue();
        } else {
            Double pricePerM = totalPrice / getTotalArea();
            return new BigDecimal(pricePerM).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    }

    public Double getTotalSum() {
        Double sum = 0.0;
        for (CalculationResult c : list) {
            sum += c.getTotalPrice();
        }
        totalPrice = new BigDecimal(sum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return totalPrice;
    }


    public double getWallsPerimetr() {
        return wallsPerimetr;
    }

    public void setWallsPerimetr(double wallsPerimetr) {
        this.wallsPerimetr = wallsPerimetr;
    }

    public double getBuildingHeight() {
        return buildingHeight;
    }

    public void setBuildingHeight(double buildingHeight) {
        this.buildingHeight = buildingHeight;
    }

    public double getWindowSquare() {
        return windowSquare;
    }

    public void setWindowSquare(double windowSquare) {
        this.windowSquare = windowSquare;
    }

    public int getWindowsCount() {
        return windowsCount;
    }

    public void setWindowsCount(int windowsCount) {
        this.windowsCount = windowsCount;
    }

    public double getDoorSquare() {
        return doorSquare;
    }

    public void setDoorSquare(double doorSquare) {
        this.doorSquare = doorSquare;
    }

    public int getDoorsCount() {
        return doorsCount;
    }

    public void setDoorsCount(int doorsCount) {
        this.doorsCount = doorsCount;
    }


}
