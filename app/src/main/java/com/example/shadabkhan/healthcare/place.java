package com.example.shadabkhan.healthcare;

public class place {
    String admin_name1,country_code,
    latitude,longitude,malaria_cause,malaria_deaths,malaria_risk_factor
            ,malaria_risk_index,malnutrition_cause,
    malnutrition_deaths,malnutrition_risk_factor,malnutrition_risk_index,
    place_name,population,postal_code,tuberculosis_cause,
    tuberculosis_deaths,tuberculosis_risk_factor,tuberculosis_risk_index;
    public place(){
    }
    public place(String admin_name1, String country_code, String latitude,
                 String longitude, String malaria_cause, String malaria_deaths,
                 String malaria_risk_factor, String malaria_risk_index, String malnutrition_cause,
                 String malnutrition_deaths, String malnutrition_risk_factor,
                 String malnutrition_risk_index, String place_name, String population,
                 String postal_code, String tuberculosis_cause, String tuberculosis_deaths,
                 String tuberculosis_risk_factor, String tuberculosis_risk_index) {
        this.admin_name1 = admin_name1;
        this.country_code = country_code;
        this.latitude = latitude;
        this.longitude = longitude;
        this.malaria_cause = malaria_cause;
        this.malaria_deaths = malaria_deaths;
        this.malaria_risk_factor = malaria_risk_factor;
        this.malaria_risk_index = malaria_risk_index;
        this.malnutrition_cause = malnutrition_cause;
        this.malnutrition_deaths = malnutrition_deaths;
        this.malnutrition_risk_factor = malnutrition_risk_factor;
        this.malnutrition_risk_index = malnutrition_risk_index;
        this.place_name = place_name;
        this.population = population;
        this.postal_code = postal_code;
        this.tuberculosis_cause = tuberculosis_cause;
        this.tuberculosis_deaths = tuberculosis_deaths;
        this.tuberculosis_risk_factor = tuberculosis_risk_factor;
        this.tuberculosis_risk_index = tuberculosis_risk_index;
    }

    public String getAdmin_name1() {
        return admin_name1;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getMalaria_cause() {
        return malaria_cause;
    }

    public String getMalaria_deaths() {
        return malaria_deaths;
    }

    public String getMalaria_risk_factor() {
        return malaria_risk_factor;
    }

    public String getMalaria_risk_index() {
        return malaria_risk_index;
    }

    public String getMalnutrition_cause() {
        return malnutrition_cause;
    }

    public String getMalnutrition_deaths() {
        return malnutrition_deaths;
    }

    public String getMalnutrition_risk_factor() {
        return malnutrition_risk_factor;
    }

    public String getMalnutrition_risk_index() {
        return malnutrition_risk_index;
    }

    public String getPlace_name() {
        return place_name;
    }

    public String getPopulation() {
        return population;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getTuberculosis_cause() {
        return tuberculosis_cause;
    }

    public String getTuberculosis_deaths() {
        return tuberculosis_deaths;
    }

    public String getTuberculosis_risk_factor() {
        return tuberculosis_risk_factor;
    }

    public String getTuberculosis_risk_index() {
        return tuberculosis_risk_index;
    }
}
