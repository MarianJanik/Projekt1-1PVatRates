package cz.marianjanik.ekurz;

import java.util.Comparator;

/**
 * The comparator allows descending sorting by basic VAT rate.
 */

public class FullVatComparatorZa implements Comparator<VatState> {

    @Override
    public int compare(VatState first, VatState second) {
        return second.getFullVat() - first.getFullVat();
    }
}
