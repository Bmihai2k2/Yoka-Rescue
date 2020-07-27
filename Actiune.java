package joc;

import javax.swing.JRadioButton;

public class Actiune {
    String cod;
    String text;
    int deltaApa, deltaMancare;

    public Actiune(String cod, String text, int deltaApa, int deltaMancare) {
        this.cod = cod;
        this.text = text;
        this.deltaApa = deltaApa;
        this.deltaMancare = deltaMancare;
    }
    
    void afiseaza(){
        System.out.println(cod+" --> "+text+", "+deltaApa+", "+deltaMancare);
    }
    
    void afiseaza(JRadioButton buton){
        buton.setText(text+", "+deltaApa+", "+deltaMancare);
        buton.setEnabled(true);
    }    
    
}
