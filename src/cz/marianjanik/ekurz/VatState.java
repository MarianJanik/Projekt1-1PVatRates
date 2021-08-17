package cz.marianjanik.ekurz;

public class VatState {
    private String countryAbbreviation;
    private String country;
    private int fullVat;
    private int reducedVat;
    private boolean specialVat;

    public VatState(String countryAbbreviation, String country, int fullVat, int reducedVat, boolean specialVat) {
        this.countryAbbreviation = countryAbbreviation;
        this.country = country;
        this.fullVat = fullVat;
        this.reducedVat = reducedVat;
        this.specialVat = specialVat;
    }

    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }

    public void setCountryAbbreviation(String countryAbbreviation) {
        this.countryAbbreviation = countryAbbreviation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getFullVat() {
        return fullVat;
    }

    public void setFullVat(int fullVat) {
        this.fullVat = fullVat;
    }

    public int getReducedVat() {
        return reducedVat;
    }

    public void setReducedVat(int reducedVat) {
        this.reducedVat = reducedVat;
    }

    public boolean isSpecialVat() {
        return specialVat;
    }

    public void setSpecialVat(boolean specialVat) {
        this.specialVat = specialVat;
    }

    public String getInfoVat(){
        String text;
        text = this.country + " (" + this.countryAbbreviation + "): " + this.fullVat + " %";
        return text;
    }

}
