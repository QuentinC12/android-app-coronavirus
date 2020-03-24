package fr.esilv2020.moneyroom;

public class ListCountries {

    private String country;

    private int cases;

    private int todayCases;

    private int deaths;

    private int todayDeaths;

    private int active;

    private int critical;

    private int casesPerOneMillion;

    public ListCountries(String country, int cases, int todayCases, int deaths, int todayDeaths, int active, int critical, int casesPerOneMillion) {
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.active = active;
        this.critical = critical;
        this.casesPerOneMillion = casesPerOneMillion;
    }

    public String getCountry() {
        return country;
    }

    public int getCases() {
        return cases;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public int getActive() {
        return active;
    }

    public int getCritical() {
        return critical;
    }

    public int getCasesPerOneMillion() {
        return casesPerOneMillion;
    }
}
