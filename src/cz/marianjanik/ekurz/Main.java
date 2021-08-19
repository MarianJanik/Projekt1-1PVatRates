package cz.marianjanik.ekurz;

import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        final String FILE1 = "vat-eu.csv";
        final int VAT = 20;
        final String FILE2 = "vat-over-" + VAT + ".txt";

        VatStateList vatStateList;

        vatStateList = VatStateList.importFromTextFile(FILE1);

        System.out.println("\n\n----------------------------------1. Seznam všech států:");
        System.out.println(vatStateList.getAllInfoVat2());

        System.out.println("\n\n----------------------------------2. Seznam států, kde VAT>20% a nepoužívají speciální sazbu daně:");
        System.out.println(vatStateList.getAllInfoVat(VAT));

        System.out.println("\n\n----------------------------------3. Výpis (ad 1.) seřazen sestupně dle VAT: ");
        System.out.println((vatStateList.getAllInfoVatSorted()).getAllInfoVat());

        System.out.println("\n\n----------------------------------4. Výpis (ad 2.) seřazen sestupně dle VAT: ");
        System.out.println((vatStateList.getAllInfoVatSorted()).getAllInfoVat(VAT));

        System.out.println("\n\n----------------------------------5. Výpis (ad 2.) byl uložen do souboru :" + FILE2 + ".");
        VatStateList.exportToFile(FILE2, (vatStateList.getAllInfoVatSorted()).getAllInfoVat(VAT));

        System.out.println("\n\n----------------------------------6. Uživatel zadává z klávesnice sazbu: ");
        enterFromKeyboard(vatStateList);
    }

    private static void enterFromKeyboard(VatStateList vatStateList) throws FileNotFoundException {
    String file3;
    int repeat = 1;
        boolean control;
        while (repeat == 1){
            Scanner scanner = new Scanner(System.in);
            control = false;
            while (control==false){
                System.out.print("Zadej sazbu VAT (kladné celé číslo): ");
                double vat2 = Double.valueOf(scanner.nextLine());
                if (vat2>=0){
                    file3 = "vat-over-" + vat2 + ".txt";
                    control = true;
                    System.out.println((vatStateList.getAllInfoVatSorted()).getAllInfoVat(vat2));
                    VatStateList.exportToFile(file3,(vatStateList.getAllInfoVatSorted()).getAllInfoVat(vat2));
                }else {
                    System.out.println("\n\nNebylo zadáno kladné číslo. Bylo zadáno" + vat2 + ". Opakuj zadání.\n\n");
                }
            }
            System.out.print("\n\nChceš zadat jinou hodnotu VAT? (1-ano/0-ne): ");
            repeat = Integer.valueOf(scanner.nextLine());
        }
    }
}
