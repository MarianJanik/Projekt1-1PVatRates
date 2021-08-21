package cz.marianjanik.ekurz;

import java.io.FileNotFoundException;
import java.nio.channels.ScatteringByteChannel;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        final String FILE1 = "vat-eu.csv";
        final int VAT = 20;
        final String FILE2 = "vat-over-" + VAT + ".txt";

        VatStateList vatStateList = new VatStateList();

        try {
            vatStateList = VatStateList.importFromTextFile(FILE1);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (VatStateException e) {
            System.err.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("\n\n----------------------------------1. Seznam všech států (obě sazby): ");
        System.out.println(vatStateList.getAllInfoVat2());

        System.out.println("\n\n----------------------------------2. Seznam států, kde VAT>20% a nepoužívají speciální sazbu daně: ");
        System.out.println(vatStateList.getAllInfoVat(VAT));

        System.out.println("\n\n----------------------------------3. Výpis (ad 1.) seřazen sestupně dle VAT: ");

        System.out.println((vatStateList.getAllInfoVatSortedAZ(Settings.getAzSort())).getAllInfoVat());


        System.out.println("\n\n----------------------------------4. Výpis (ad 2.) seřazen sestupně dle VAT: ");
        System.out.println((vatStateList.getAllInfoVatSortedAZ(Settings.getAzSort())).getAllInfoVat(VAT));

        System.out.println("\n\n----------------------------------5. Výpis (ad 2.) byl uložen do souboru: " + FILE2 + ".");

        try {
            VatStateList.exportToFile(FILE2, (vatStateList.getAllInfoVatSortedAZ(Settings.getAzSort())).getAllInfoVat(VAT));

            System.out.println("\n\n----------------------------------6. Uživatel zadává z klávesnice sazbu: ");
            enterFromKeyboard(vatStateList);
        } catch (FileNotFoundException e) {
            System.err.println("Soubor nebylo možné uložit.");
        }

        System.out.println("\n\n----------------------------------7. Testování comparátorů (nebylo součásti zadání)");
        System.out.println("\n\n----------------------------------7a. Seřazení podle země vzestupně");
        System.out.println(vatStateList.getSorted(1).getAllInfoVat2());
        System.out.println("\n\n----------------------------------7b. Seřazení podle země sestupně");
        System.out.println(vatStateList.getSorted(2).getAllInfoVat2());
        System.out.println("\n\n----------------------------------7c. Seřazení podle sazby vzestupně");
        System.out.println(vatStateList.getSorted(3).getAllInfoVat2());
        System.out.println("\n\n----------------------------------7d. Seřazení podle sazby sestupně");
        System.out.println(vatStateList.getSorted(4).getAllInfoVat2());
    }


    private static void enterFromKeyboard(VatStateList vatStateList) throws FileNotFoundException {
    boolean repeat = true;
        while (repeat == true){
            Scanner scanner = new Scanner(System.in);
            inputFromKeyboard(vatStateList);
            System.out.print("\nChceš zadat jinou hodnotu VAT? (a-ano/n-ne): ");
            repeat = scanner.nextLine().equals("a");
        }
    }

    private static void inputFromKeyboard(VatStateList vatStateList) throws FileNotFoundException {
        String file3;
        DecimalFormat myFormat = new DecimalFormat("#.#");
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nZadej sazbu VAT (kladné celé číslo): ");
        String vat1 = scanner.nextLine();
        double vat2;
        try {
            if (vat1.isEmpty()) vat2 = 20;
            else vat2 = Double.valueOf(vat1);
            if ((vat2 >= 0) && (vat2 < 100)) {
                file3 = "vat-over-" + vat2 + ".txt";
                System.out.println((vatStateList.getAllInfoVatSortedAZ(Settings.getAzSort())).getAllInfoVat(vat2));
                VatStateList.exportToFile(file3, (vatStateList.getAllInfoVatSortedAZ(Settings.getAzSort())).getAllInfoVat(vat2));
            } else {
                System.err.println("\n\nNebylo zadáno číslo od 0 do 100. Bylo zadáno " + myFormat.format(vat2) + ".\n");
            }
        } catch (NumberFormatException e) {
            System.err.println("\n\nZadaná hodnota není číslo. Bylo zadáno: " + vat1 + ".\n");
        }
    }
}

