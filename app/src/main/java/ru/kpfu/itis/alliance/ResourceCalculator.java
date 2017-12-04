package ru.kpfu.itis.alliance;

/**
 * Created by Bulat on 05.12.2017 at 00:14.
 */

public class ResourceCalculator {

    private double wallsPerimetr;
    private double buildingHeight;
    private double windowSquare;
    private int windowsCount;
    private double doorSquare;
    private int doorsCount;
    int mode;

    public ResourceCalculator(double wallsPerimetr, double buildingHeight, double windowSquare, int windowsCount, double doorSquare, int doorsCount, int mode) {
        this.wallsPerimetr = wallsPerimetr;
        this.buildingHeight = buildingHeight;
        this.windowSquare = windowSquare;
        this.windowsCount = windowsCount;
        this.doorSquare = doorSquare;
        this.doorsCount = doorsCount;
        this.mode = mode;
    }

    public void parseJson(){

    }

    public double getTotalArea(){
        double area;
        area = wallsPerimetr*buildingHeight - (doorSquare*doorsCount + windowSquare*windowsCount);
        return area;
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
