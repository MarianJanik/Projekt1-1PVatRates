package cz.marianjanik.ekurz;

/**
 * Třída nastavuje způsob řazení (AZ_SORT=false - sestupně).
 */

public class Settings {

    final private static boolean AZ_SORT = false;

    public static boolean getAzSort(){
        return AZ_SORT;
    }
}
