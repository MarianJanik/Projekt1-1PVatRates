package cz.marianjanik.ekurz;

import java.util.Comparator;

/**
 * The comparator allows ascending sorting by country.
 */

public class CountryComparatorAz implements Comparator<VatState> {

    @Override
    public int compare(VatState first, VatState second) {
        return first.getCountry().compareTo(second.getCountry());
    }
}
