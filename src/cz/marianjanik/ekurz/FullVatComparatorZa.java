package cz.marianjanik.ekurz;

import java.util.Comparator;

public class FullVatComparatorZa implements Comparator<VatState> {

    @Override
    public int compare(VatState first, VatState second) {
        return second.getFullVat() - first.getFullVat();
    }
}
