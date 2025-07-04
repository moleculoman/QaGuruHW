package tests.demoqa.PracticeForm.model;

import java.util.List;

public class CityModel {

        private String city;
        private int population;
        private int founded;
        private int area_km2;
        private List<String> landmarks;
        private String river;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getFounded() {
        return founded;
    }

    public void setFounded(int founded) {
        this.founded = founded;
    }

    public int getArea_km2() {
        return area_km2;
    }

    public void setArea_km2(int area_km2) {
        this.area_km2 = area_km2;
    }

    public List<String> getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(List<String> landmarks) {
        this.landmarks = landmarks;
    }

    public String getRiver() {
        return river;
    }

    public void setRiver(String river) {
        this.river = river;
    }
}








