package cz.marianjanik.ekurz;

/**
 * The exception is the restriction of user input when entering the value of VAT.
 */

public class VatStateException extends Exception {
    public VatStateException (String message){
        super (message);
    }
}
