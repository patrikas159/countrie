
package com.countries.countries;

public class Countries {
    private String country;
    private String capital;
    private String region;
    private int population;

    public Countries(String country, String capital, String region, int population) {
        this.country = country;
        this.capital = capital;
        this.region = region;
        this.population = population;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Countries{" +
                "name='" + country + '\'' +
                ", capital='" + capital + '\'' +
                ", region=" + region +
                ", population=" + population +
                '}';
    }


}



