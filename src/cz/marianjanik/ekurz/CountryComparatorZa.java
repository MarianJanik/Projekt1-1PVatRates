package cz.marianjanik.ekurz;

import java.util.Comparator;

public class CountryComparatorZa implements Comparator<VatState> {

    @Override
    public int compare(VatState first, VatState second) {
        return second.getCountry().compareTo(first.getCountry());
    }
}

