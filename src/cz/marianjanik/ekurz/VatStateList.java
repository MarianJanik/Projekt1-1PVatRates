package cz.marianjanik.ekurz;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class VatStateList {
    List<VatState> listOfStates = new ArrayList<>();

    public void addState(VatState addState){
        listOfStates.add(addState);
    }

    public void removeState (VatState removeState){
        listOfStates.remove(removeState);
    }

    public void removeState (int index){
        listOfStates.remove(index);
    }


    public static VatStateList importFromTextFile(String fileName) throws FileNotFoundException {
        VatStateList summary = new VatStateList();

        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                String[] items = inputLine.split("\t");
                int vat1 = Integer.valueOf(items[2]);
                double vat2 = Double.valueOf(items[3].replaceFirst(",","."));
                boolean specialVat = Boolean.valueOf(items[4]);
                summary.addState(new VatState(items[0],items[1],vat1,vat2,specialVat));
            }
        }
        return summary;
    }

    public void exportToFile(String filename) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename))) {
            for (VatState vatState : this.listOfStates) {
                writer.println(vatState.getInfoVat());
            }
        }
    }

    public static void exportToFile(String filename, String text) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename))) {
            writer.println(text);
        }
    }

    public String getAllInfoVat(){
        StringBuilder builder = new StringBuilder();
        for (VatState vatState:this.listOfStates) {
            builder.append(vatState.getInfoVat() + "\n");
        }
        return builder.toString();
    }

    public String getAllInfoVat2(){
        StringBuilder builder = new StringBuilder();
        for (VatState vatState:this.listOfStates) {
            builder.append(vatState.getInfoVat2() + "\n");
        }
        return builder.toString();
    }

    public String getAllInfoVat(double vat){
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        for (VatState vatState:this.listOfStates) {
            if ((vatState.getFullVat() > vat) && !vatState.isSpecialVat())
                builder1.append(vatState.getInfoVat() + "\n");
            else builder2.append(vatState.getCountryAbbreviation() + ", ");
        }
            String text = builder1.toString()+"===============================\n"
                    + "Sazba VAT " + vat + " % nebo nižší nebo používají speciální sazbu: " + builder2.toString();
        return text;
    }

    public VatStateList getAllInfoVatSorted(){
        VatStateList sortedList = new VatStateList();
        List<VatState>copyOfList = new ArrayList<>(listOfStates);
        while (copyOfList.size()>0){
            int max = 0;
            VatState index=null;
            for (VatState vatState: copyOfList) {
                if (vatState.getFullVat()>max) {
                    max=vatState.getFullVat();
                    index = vatState;
                }
            }
            sortedList.addState(index);
            copyOfList.remove(index);
        }
        return sortedList;
    }
}
