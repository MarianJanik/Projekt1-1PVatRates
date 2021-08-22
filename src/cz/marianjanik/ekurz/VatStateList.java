package cz.marianjanik.ekurz;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * The class is used to work through the base class (VatState) with objects that are imported from a file.
 * Thanks to this class, objects can be added to the list and worked with.
 * methods for: add to list, remove from list, import from text file (csv), export to text file,
 * get info for output, methods for sort.
 */

public class VatStateList{
    List<VatState> listOfStates = new ArrayList<>();

    /**
     * Write the object to the list.
     * @param addState - object to add to the list.
     */
    public void addState(VatState addState){
        listOfStates.add(addState);
    }

    /**
     * Remove the object from the list.
     * @param removeState - object to delete from the list.
     */
    public void removeState (VatState removeState){
        listOfStates.remove(removeState);
    }

    /**
     * Remove the object from the list.
     * @param index - index of the object to be deleted from the list.
     */
    public void removeState (int index){
        listOfStates.remove(index);
    }

    /**
     * Methods for reading from a text file.
     * @param fileName - name text file,
     * @return - a list that was created from a text file.
     * @throws FileNotFoundException - exception for file existence,
     * @throws VatStateException - exception for parsing (newly created),
     * @throws NumberFormatException - exception for improperly entered numbers,
     * @throws ArrayIndexOutOfBoundsException - exception for non-existent data
     * in the input file (applies to the last column false / true).
     */

    public static VatStateList importFromTextFile(String fileName)
            throws FileNotFoundException, VatStateException, NumberFormatException, ArrayIndexOutOfBoundsException {
        VatStateList summary = new VatStateList();
        int vat1;
        double vat2;
        boolean specialVat=false;
        int abacus = 0;
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNextLine()) {
                abacus++;
                String inputLine = scanner.nextLine();
                String[] items = inputLine.split("\t");
                try {
                    vat1 = Integer.valueOf(items[2]);
                } catch (NumberFormatException e){
                    throw new NumberFormatException ("Došlo k chybě při čtení ve 3. sloupci \"Základní sazba DPH\" na řádku "
                            + abacus + ".\nHodnota není číslo. Bylo zadáno: \"" + items[2] + "\".");
                }
                try {
                    vat2 = Double.valueOf(items[3].replaceFirst(",", "."));
                } catch (NumberFormatException e){
                    throw new NumberFormatException ("Došlo k chybě při čtení ve 4. sloupci \"Snížená sazba DPH\" na řádku "
                            + abacus + ", stát: " + items[1] + " (" + items[0] + ")." + "\nHodnota není číslo. Bylo zadáno: \"" + items[3] + "\".");
                }
                try {
                    if (!("false").equals(items[4]) && !("true").equals(items[4])) {
                        throw new VatStateException("Došlo k chybě při čtení v 5. sloupci \"Speciální sazba DPH\" na řádku "
                                + abacus + ", stát: " + items[1] + " (" + items[0] + ")." + "\nHodnota \"false\" nebo \"true\" nenalezena. Bylo zadáno: " + items[4] + ".");
                    } else specialVat = Boolean.valueOf(items[4]);
                }catch (ArrayIndexOutOfBoundsException e){
                    throw new ArrayIndexOutOfBoundsException("Došlo k chybě při čtení v 5. sloupci \"Speciální sazba DPH\" na řádku "
                                + abacus + ", stát: " + items[1] + " (" + items[0] + "). Hodnota nenalezena.");
                }
                try {
                    summary.addState(new VatState(items[0],items[1],vat1,vat2,specialVat));
                } catch (VatStateException e) {
                    throw new VatStateException(e.getMessage() + "\nŘádek: " + abacus + ", stát: " + items[1] + " (" + items[0] + ").");
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Soubor pro načtení \"" + fileName + "\" nebyl nalezen.");

        }
        return summary;
    }

    /**
     * Method for writing the list to a text file.
     * @param filename - the name of output file,
     * @throws FileNotFoundException - exception for trouble saving a file.
     */
    public void exportToFile(String filename) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename))) {
            for (VatState vatState : this.listOfStates) {
                writer.println(vatState.getInfoVat());
            }
        }
    }

    /**
     * Method for writing text to a text file.
     * @param filename - the name of output file,
     * @param text - text for writing,
     * @throws FileNotFoundException - exception for trouble saving a file.
     */
    public static void exportToFile(String filename, String text) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename))) {
            writer.println(text);
        }
    }

    /**
     * Method prepares the text from list for output (screen, file) - according to the project assignment.
     * @return - prepare text.
     */
    public String getAllInfoVat(){
        StringBuilder builder = new StringBuilder();
        for (VatState vatState:this.listOfStates) {
            builder.append(vatState.getInfoVat() + "\n");
        }
        return builder.toString();
    }

    /**
     * Method prepares the text from list for output (screen, file) - all information.
     * @return
     */
    public String getAllInfoVat2(){
        StringBuilder builder = new StringBuilder();
        for (VatState vatState:this.listOfStates) {
            builder.append(vatState.getInfoVat2() + "\n");
        }
        return builder.toString();
    }

    /**
     * Method prepares the text from list for output (screen, file) - according to the project assignment.
     * @param vat - VAT option from the user,
     * @return - prepare text.
     */
    public String getAllInfoVat(double vat){
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        DecimalFormat myFormat = new DecimalFormat("#.#");
        for (VatState vatState:this.listOfStates) {
            if ((vatState.getFullVat() > vat) && !vatState.isSpecialVat())
                builder1.append(vatState.getInfoVat() + "\n");
            else builder2.append(vatState.getCountryAbbreviation() + ", ");
        }
            String text = builder1 + "===============================\n"
                    + "Sazba VAT " + myFormat.format(vat) + " % nebo nižší nebo používají speciální sazbu: " + builder2;
        return text;
    }

    /**
     * A method that allows you to sort data from the list according to VAT in ascending and descending order.
     * @param azSort - true-ascending sort, false-descending sort,
     * @return - sorted list.
     */
    public VatStateList getAllInfoVatSortedAZ(boolean azSort){
        VatStateList sortedList = new VatStateList();
        List<VatState>copyOfList = new ArrayList<>(listOfStates);
        if (!azSort) Collections.sort(copyOfList, new FullVatComparatorZa());
        else Collections.sort(copyOfList);
        copyOfList.forEach(n->{sortedList.addState(n);});
        return sortedList;
    }

    /**
     * A method that allows you to sort data from the list by VAT in ascending and descending order,
     * and by country name in ascending and descending order. It mainly uses comparators.
     * @param sort - number allowing selection:(1.country-ascending,2.country-descending,
     *             3.basic VAT rate-ascending,4.basic VAT rate-descending,
     * @return - sorted list.
     */
    public VatStateList getSorted(int sort){
        VatStateList sortedList = new VatStateList();
        List<VatState>copyOfList = new ArrayList<>(listOfStates);
        switch (sort){
            case 1:
                Collections.sort(copyOfList, new CountryComparatorAz());
                break;
            case 2:
                Collections.sort(copyOfList, new CountryComparatorZa());
                break;
            case 3:
                Collections.sort(copyOfList);
                break;
            case 4:
                Collections.sort(copyOfList, new FullVatComparatorZa());
                break;
        }
        copyOfList.forEach(n->{sortedList.addState(n);});
        return sortedList;
    }
}
