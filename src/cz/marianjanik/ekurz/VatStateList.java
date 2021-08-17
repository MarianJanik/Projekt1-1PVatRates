package cz.marianjanik.ekurz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public String getAllInfoVat(){
        StringBuilder builder = new StringBuilder();
        for (VatState vatState:this.listOfStates) {
            builder.append(vatState.getInfoVat() + "\n");
        }
        return builder.toString();
    }

    public String getAllInfoVat(int vat){
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

    public String getAllInfoVatSorted(){
        StringBuilder builder = new StringBuilder();
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
            builder.append(index.getInfoVat() + "\n");
            copyOfList.remove(index);
        }
        return builder.toString();
    }
}
