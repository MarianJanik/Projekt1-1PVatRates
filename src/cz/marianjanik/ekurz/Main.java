package cz.marianjanik.ekurz;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {
        VatStateList exampleList = new VatStateList();

        VatState record1 = new VatState("AT","Austria",20,10,true);
        VatState record2 = new VatState("BE","Belgium",21,12,true);
        VatState record3 = new VatState("BG","Bulgaria",20,9,false);
        VatState record4 = new VatState("CY","Cyprus",19,9,false);
        VatState record5 = new VatState("CZ","Czech Republic",21,15,false);

        exampleList.addState(record1);
        exampleList.addState(record2);
        exampleList.addState(record3);
        exampleList.addState(record4);
        exampleList.addState(record5);

        System.out.println("\n\n----------------------------------1. Seznam všech států:");
        System.out.println(exampleList.getAllInfoVat());

        System.out.println("\n\n----------------------------------2. Seznam států, kde VAT>20% a nepoužívají speciální sazbu daně:");
        System.out.println(exampleList.getAllInfoVat(19));

        System.out.println("\n\n----------------------------------3. Výpis seřazen sestupně dle VAT: ");
        System.out.println(exampleList.getAllInfoVatSorted());


    }
}
