package com.example.jrmy.velove;

import java.io.Serializable;

public class Station implements Serializable {
    private int ID;

    public Station(String name) {
        this.name = name;
    }

    private String name;
    private String adress;
    private String adress2;
    private String commune;
    private int numArrondissement;
    private String bonus;
    private String pole;
    private float latitude;
    private float longitude;
    private int bikeStands;
    private String status;
    private int availableBikeStands;
    private int availableBikes;
    private int availabilityCode;
    private String availability;
    private int banking;
    private int gid;
    private String lastUpdate;
    private String lastUpdateFme;
    private int codeInsee;
    private String langue;
    private String state;
    private String nature;
    private String title;
    private String Description;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getAdress2() {
        return adress2;
    }

    public void setAdress2(String adress2) {
        this.adress2 = adress2;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public int getNumArrondissement() {
        return numArrondissement;
    }

    public void setNumArrondissement(int numArrondissement) {
        this.numArrondissement = numArrondissement;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getPole() {
        return pole;
    }

    public void setPole(String pole) {
        this.pole = pole;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getBikeStands() {
        return bikeStands;
    }

    public void setBikeStands(int bikeStands) {
        this.bikeStands = bikeStands;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAvailableBikeStands() {
        return availableBikeStands;
    }

    public void setAvailableBikeStands(int availableBikeStands) {
        this.availableBikeStands = availableBikeStands;
    }

    public int getAvailableBikes() {
        return availableBikes;
    }

    public void setAvailableBikes(int availableBikes) {
        this.availableBikes = availableBikes;
    }

    public int getAvailabilityCode() {
        return availabilityCode;
    }

    public void setAvailabilityCode(int availabilityCode) {
        this.availabilityCode = availabilityCode;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public int getBanking() {
        return banking;
    }

    public void setBanking(int banking) {
        this.banking = banking;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateFme() {
        return lastUpdateFme;
    }

    public void setLastUpdateFme(String lastUpdateFme) {
        this.lastUpdateFme = lastUpdateFme;
    }

    public int getCodeInsee() {
        return codeInsee;
    }

    public void setCodeInsee(int codeInsee) {
        this.codeInsee = codeInsee;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

}
