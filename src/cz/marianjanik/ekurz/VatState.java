package cz.marianjanik.ekurz;

import java.text.DecimalFormat;

/**
 * Basic class for project solution. The class solves the basic instance of the objects that are used in the project.
 * 5 fields, 1 constructor, getters and setters, VatStateExceptions - exceptions for numerical constraints.
 */

public class VatState implements Comparable<VatState>{
    private String countryAbbreviation;
    private String country;
    private int fullVat;
    private double reducedVat;
    private boolean specialVat;

    DecimalFormat myFormat = new DecimalFormat("#.#");

    /**
     * constructor
     * @param countryAbbreviation - abbreviation for state,
     * @param country - country,
     * @param fullVat - full percentage of value added tax,
     * @param reducedVat - reduced percentage of value added tax,
     * @param specialVat - information on whether the country uses a special VAT rate for some products,
     * @throws VatStateException - exception for numerical definition of tax from 0 to 100.
     */

    public VatState(String countryAbbreviation, String country, int fullVat, double reducedVat, boolean specialVat) throws VatStateException {
        this.countryAbbreviation = countryAbbreviation;
        this.country = country;
        setFullVat(fullVat);
        setReducedVat(reducedVat);
        this.specialVat = specialVat;
    }

    /**
     * getters and setters
     */

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

    /**
     * This setter is used in the constructor and sets the basic VAT rate.
     * @param fullVat - full percentage of value added tax,
     * @throws VatStateException - exception for numerical definition of tax from 0 to 100.
     */
    public void setFullVat(int fullVat) throws VatStateException{
        if ((fullVat>=0)&&(fullVat<100)) this.fullVat = fullVat;
        else throw new VatStateException("Zadaná hodnota ve sloupci DPH/VAT \"Plná sazba daně\" je mimo rozsah (rozsah je 0-99%). "
                + "Bylo zadáno: " + fullVat + " %. ");
    }

    public double getReducedVat() {
        return reducedVat;
    }

    /**
     * This setter is used in the constructor and sets the reduced VAT rate.
     * @param reducedVat - reduced percentage of value added tax,
     * @throws VatStateException - exception for numerical definition of tax from 0 to 100.
     */
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

    /**
     * Method prepare text for output (screen, file) - according to the project assignment.
     */
    public String getInfoVat(){
        String text;
        text = this.country + " (" + this.countryAbbreviation + "): " + this.fullVat + " %";
        return text;
    }

    /**
     * Method prepare text for output (screen, file) - all information.
     */
    public String getInfoVat2(){
        String text;
        text = this.country + " (" + this.countryAbbreviation + "): " + this.fullVat + " %, " + myFormat.format(this.reducedVat) + " %, " + specialVat;
        return text;
    }

    /**
     * Method adapted for sorting by basic VAT rate.
     */
    @Override
    public int compareTo(VatState second) {
        return this.fullVat - second.fullVat;
    }
}
