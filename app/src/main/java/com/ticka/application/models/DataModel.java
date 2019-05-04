package com.ticka.application.models;

import android.support.annotation.NonNull;

import java.io.File;
import java.io.Serializable;

public class DataModel implements Serializable {

    ////////////////////
    // General Detail //
    ////////////////////
    private String title = "";
    private String state = "";
    private String city = "";
    private String address = "";
    private String descriptionGeneral = "";

    //////////////////
    // upload photo //
    //////////////////
    private File photo1 = null;
    private File photo2 = null;
    private File photo3 = null;

    //////////////////
    // minor detail //
    //////////////////
    private String buildingType = "";
    private String areaType = "";
    private String buildingTip = "";
    private String roomCount = "";
    private String buildingArea = "";
    private String landArea = "";
    private String minDay = "";
    private String maxDay = "";

    /////////////////////
    // capacity detail //
    /////////////////////
    private String standardCapacity = "";
    private String maxCapacity = "";
    private String singleBedCapacity = "";
    private String doubleBedCapacity = "";
    private String descriptionCapacity = "";

    //////////////////////////
    // possibilities detail //
    //////////////////////////
    private String jsonPossibilities = "";
    private String descriptionPossibilities = "";

    //////////////////
    // rules detail //
    //////////////////
    private String jsonRules = "";
    private String descriptionRules = "";

    //////////////////
    // price detail //
    //////////////////
    private String fromDate = "";
    private String toDate = "";
    private String price = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescriptionGeneral() {
        return descriptionGeneral;
    }

    public void setDescriptionGeneral(String descriptionGeneral) {
        this.descriptionGeneral = descriptionGeneral;
    }

    public File getPhoto1() {
        return photo1;
    }

    public void setPhoto1(File photo1) {
        this.photo1 = photo1;
    }

    public File getPhoto2() {
        return photo2;
    }

    public void setPhoto2(File photo2) {
        this.photo2 = photo2;
    }

    public File getPhoto3() {
        return photo3;
    }

    public void setPhoto3(File photo3) {
        this.photo3 = photo3;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getBuildingTip() {
        return buildingTip;
    }

    public void setBuildingTip(String buildingTip) {
        this.buildingTip = buildingTip;
    }

    public String getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(String roomCount) {
        this.roomCount = roomCount;
    }

    public String getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(String buildingArea) {
        this.buildingArea = buildingArea;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }

    public String getMinDay() {
        return minDay;
    }

    public void setMinDay(String minDay) {
        this.minDay = minDay;
    }

    public String getMaxDay() {
        return maxDay;
    }

    public void setMaxDay(String maxDay) {
        this.maxDay = maxDay;
    }

    public String getStandardCapacity() {
        return standardCapacity;
    }

    public void setStandardCapacity(String standardCapacity) {
        this.standardCapacity = standardCapacity;
    }

    public String getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(String maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getSingleBedCapacity() {
        return singleBedCapacity;
    }

    public void setSingleBedCapacity(String singleBedCapacity) {
        this.singleBedCapacity = singleBedCapacity;
    }

    public String getDoubleBedCapacity() {
        return doubleBedCapacity;
    }

    public void setDoubleBedCapacity(String doubleBedCapacity) {
        this.doubleBedCapacity = doubleBedCapacity;
    }

    public String getDescriptionCapacity() {
        return descriptionCapacity;
    }

    public void setDescriptionCapacity(String descriptionCapacity) {
        this.descriptionCapacity = descriptionCapacity;
    }

    public String getJsonPossibilities() {
        return jsonPossibilities;
    }

    public void setJsonPossibilities(String jsonPossibilities) {
        this.jsonPossibilities = jsonPossibilities;
    }

    public String getDescriptionPossibilities() {
        return descriptionPossibilities;
    }

    public void setDescriptionPossibilities(String descriptionPossibilities) {
        this.descriptionPossibilities = descriptionPossibilities;
    }

    public String getJsonRules() {
        return jsonRules;
    }

    public void setJsonRules(String jsonRules) {
        this.jsonRules = jsonRules;
    }

    public String getDescriptionRules() {
        return descriptionRules;
    }

    public void setDescriptionRules(String descriptionRules) {
        this.descriptionRules = descriptionRules;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @NonNull
    @Override
    public String toString() {
        return "DataModel{" + "title='" + title + '\'' + ", state='" + state + '\'' + ", city='" + city + '\'' + ", address='" + address + '\'' + ", descriptionGeneral='" + descriptionGeneral + '\'' + ", photo1=" + photo1 + ", photo2=" + photo2 + ", photo3=" + photo3 + ", buildingType='" + buildingType + '\'' + ", areaType='" + areaType + '\'' + ", buildingTip='" + buildingTip + '\'' + ", roomCount='" + roomCount + '\'' + ", buildingArea='" + buildingArea + '\'' + ", landArea='" + landArea + '\'' + ", minDay='" + minDay + '\'' + ", maxDay='" + maxDay + '\'' + ", standardCapacity='" + standardCapacity + '\'' + ", maxCapacity='" + maxCapacity + '\'' + ", singleBedCapacity='" + singleBedCapacity + '\'' + ", doubleBedCapacity='" + doubleBedCapacity + '\'' + ", descriptionCapacity='" + descriptionCapacity + '\'' + ", jsonPossibilities='" + jsonPossibilities + '\'' + ", descriptionPossibilities='" + descriptionPossibilities + '\'' + ", jsonRules='" + jsonRules + '\'' + ", descriptionRules='" + descriptionRules + '\'' + ", fromDate='" + fromDate + '\'' + ", toDate='" + toDate + '\'' + ", price='" + price + '\'' + '}';
    }

}
