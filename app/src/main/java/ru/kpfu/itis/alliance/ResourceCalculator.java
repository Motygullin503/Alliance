package ru.kpfu.itis.alliance;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private double wallsPerimetr;
    private double buildingHeight;
    private double windowSquare;
    private int windowsCount;
    private double doorSquare;
    private int doorsCount;
    private int mode;

    public ResourceCalculator(double wallsPerimetr, double buildingHeight, double windowSquare, int windowsCount, double doorSquare, int doorsCount, int mode) {
        this.wallsPerimetr = wallsPerimetr;
        this.buildingHeight = buildingHeight;
        this.windowSquare = windowSquare;
        this.windowsCount = windowsCount;
        this.doorSquare = doorSquare;
        this.doorsCount = doorsCount;
        this.mode = mode;
    }

    public void parseJson() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("java\\ru\\kpfu\\itis\\alliance\\models\\coefficient.json"), "UTF-8"));

        StringBuilder text = new StringBuilder();
        String i;
        while ((i = br.readLine()) != null) {
            text.append(i);
        }

        data = new Gson().fromJson(text.toString(), Data.class);

    }

    public double getTotalArea() {
        double area;
        area = wallsPerimetr * buildingHeight - (doorSquare * doorsCount + windowSquare * windowsCount);
        return area;
    }

    public List<CalculationResult> getCalculationResults() throws IOException {
        parseJson();
        List<CalculationResult> list = new ArrayList<>();

        switch (mode) {
            case 0:
                for (Product product : data.getData().get(0).getProducts()) {
                    double resultValue = product.getCoefficient() * getTotalArea();

                    list.add(new CalculationResult(product.getName(), (int) Math.ceil(resultValue), new BigDecimal(product.getPrice() * resultValue).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
                }

            case 1:
                for (Product product : data.getData().get(1).getProducts()) {
                    double resultValue = product.getCoefficient() * getTotalArea();

                    list.add(new CalculationResult(product.getName(), (int) Math.ceil(resultValue), new BigDecimal(product.getPrice() * resultValue).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
                }

            case 2:
                for (Product product : data.getData().get(2).getProducts()) {
                    double resultValue = product.getCoefficient() * getTotalArea();

                    list.add(new CalculationResult(product.getName(), (int) Math.ceil(resultValue), new BigDecimal(product.getPrice() * resultValue).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
                }

            case 3:
                for (Product product : data.getData().get(3).getProducts()) {
                    double resultValue = product.getCoefficient() * getTotalArea();

                    list.add(new CalculationResult(product.getName(), (int) Math.ceil(resultValue), new BigDecimal(product.getPrice() * resultValue).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
                }

        }

        return list;
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
