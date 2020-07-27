package joc;

import java.util.ArrayList;
import static joc.Interfata.jTextArea1;
import static joc.Interfata.stare;
import static joc.Interfata.tabButoane;

public class Directiva {
    String cod;
    String text;
    int deltaApa, deltaMancare;
    ArrayList<Actiune> actiuni;

    public Directiva(String cod, String text) {
        this.cod = cod;
        this.text = text;
        actiuni = new ArrayList();
    }
    
    void adaugaLinie(String linie){
        text += "\n";
        text += linie;
    }
    
    void setDelte(int dA, int dM){
        deltaApa = dA;
        deltaMancare = dM;
    }
    
    void adaugaActiune(Actiune a){
        actiuni.add(a);
    }
    
    void afiseaza(){
        System.out.println("---directiva---");
        System.out.println(cod+" "+text);
        System.out.println("deltaApa : "+deltaApa+", deltaMancare: "+deltaMancare);
        System.out.println("acțiuni:");
        
        //jLabelStare.setText(text);
        jTextArea1.setText(text);

        tabButoane[0].setSelected(true);
        for(int i=0; i<4; i++){
            tabButoane[i].setEnabled(false);
            tabButoane[i].setText("");
        }

        int i=0;
        //Directiva d;
        for(Actiune a:actiuni){
            //System.out.println(i+" --- ");
            //d=stare.cauta(a.cod);
            //System.out.println("----Caut----"+a.cod);
            a.afiseaza(tabButoane[i]);
            a.afiseaza();
            i++;
        }
            
    }
    
}
