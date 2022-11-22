package com.example.demo6_1.bean;

public class ControlLine {
    private static final long serialVersionUID = 7397428155350981454L;
    private int controlLine;
    private String areaName;
    private String batchName;
    private String categoryName;
    private int controlYear;

    public int getControlLine(){
        return controlLine;
    }
    public void setControlLine(int controlLine){
        this.controlLine = controlLine;
    }
    public String getAreaName(){
        return areaName;
    }
    public void setAreaName(String areaName){
        this.areaName = areaName;
    }
    public String getBatchName(){
        return batchName;
    }
    public void setBatchName(String batchName){
        this.batchName = batchName;
    }
    public String getCategoryName(){
        return categoryName;
    }
    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }
    public int getControlYear(){
        return controlYear;
    }
    public void setControlYear(int controlYear){
        this.controlYear = controlYear;
    }

    @Override
    public String toString(){
        return "ControlLine [controlLine=" + controlLine + ", areaName=" + areaName + ",batchName=" +
                batchName + ",controlYear=" + controlLine + "]";
    }
}
