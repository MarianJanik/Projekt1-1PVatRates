package cz.marianjanik.ekurz;

import java.text.DecimalFormat;


public class VatState implements Comparable<VatState>{
private String countryAbbreviation;
    private String country;
    private int fullVat;
    private double reducedVat;
    private boolean specialVat;

    DecimalFormat myFormat = new DecimalFormat("#.#");

    public VatState(String countryAbbreviation, String country, int fullVat, double reducedVat, boolean specialVat) throws VatStateException {
        this.countryAbbreviation = countryAbbreviation;
        this.country = country;
        setFullVat(fullVat);
        setReducedVat(reducedVat);
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

    public void setFullVat(int fullVat) throws VatStateException{
        if ((fullVat>=0)&&(fullVat<100)) this.fullVat = fullVat;
        else throw new VatStateException("Zadaná hodnota ve sloupci DPH/VAT \"Plná sazba daně\" je mimo rozsah (rozsah je 0-99%). "
                + "Bylo zadáno: " + fullVat + " %. ");
    }

    public double getReducedVat() {
        return reducedVat;
    }

    public void setReducedVat(double reducedVat) throws VatStateException {
        if ((reducedVat>=0)&&(reducedVat<100)) this.reducedVat = reducedVat;
        else throw new VatStateException("Zadaná hodnota ve sloupci DPH/VAT \"Snížená sazba daně\" je mimo rozsah (rozsah je 0-99%). "
                + "Bylo zadáno: " + myFormat.format(reducedVat) + " %. ");
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

    public String getInfoVat2(){
        String text;
        text = this.country + " (" + this.countryAbbreviation + "): " + this.fullVat + " %, " + myFormat.format(this.reducedVat) + " %, " + specialVat;
        return text;
    }

    @Override
    public int compareTo(VatState second) {
        return this.fullVat - second.fullVat;
    }

}
