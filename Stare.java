package joc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import static joc.Interfata.jLabelApa;
import static joc.Interfata.jLabelMancare;
import static joc.Interfata.jLabelNrZi;
import static joc.Interfata.stare;

public class Stare {
    int apa, mancare, zi;
    ArrayList<Directiva> directive;
    Directiva directivaCurenta;
    Interfata interfata;
    
    Stare(Interfata interfata){
        apa = 46;
        mancare = 32;
        zi = 1;
        this.interfata=interfata;
    }
    
    Directiva cauta(String cod){
        for(Directiva d:directive)
            if(d.cod.equals(cod))
                return d;
        System.out.println("DIRECTIVA INEXISTENTA");//eroare in fisierul jocului
        return null;//nu are voie să ajungă aici
    }
    
    void citesteDirective() throws Exception{
        jLabelNrZi.setText("Ziua "+stare.zi);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("z"+zi+".txt"), "UTF8"));
        directive = new ArrayList();
        br.readLine();//Ziua 1.
        String linie;
        String[] cuvinte;
        do{
            if(br.readLine()==null)//==================
                return;
            linie = br.readLine();
            cuvinte = linie.split("~");

            Directiva directiva=new Directiva(cuvinte[0], cuvinte[1]);
            
            do{
                linie = br.readLine();
                if(!linie.startsWith("\\"))
                    break;
                directiva.adaugaLinie(linie.substring(1));
            }while(true);
            cuvinte = linie.split("~");

            directiva.setDelte(Integer.parseInt(cuvinte[0]),Integer.parseInt(cuvinte[1]));

            for(int i=0; i<4; i++){
                linie = br.readLine();
                if(linie.startsWith("-"))
                    continue;
                cuvinte = linie.split("~");
                
                directiva.adaugaActiune(new Actiune(cuvinte[0], cuvinte[1], 
                        Integer.parseInt(cuvinte[2]), Integer.parseInt(cuvinte[3])));
            }
            directiva.afiseaza();
            directive.add(directiva);
        }while(true);
    }
    
    void afiseaza(){
        int i=0;
        for(Directiva d:directive){
            System.out.println(i+" ");
            d.afiseaza();
            i++;
        }
    }
    
    void executaActiunea(Actiune a) throws Exception{
        apa+=a.deltaApa;
        mancare+=a.deltaMancare;
        
        if(a.cod.startsWith("@")){
            System.out.println("Schimbă ziua");
            zi++;
            citesteDirective();
            System.out.println("---urmeaza prima directiva din ziua "+zi);
            stare.executaDirectiva(stare.directive.get(0));
        }
        else if(a.cod.startsWith("&")){
            //Interfata.init();
            interfata.salveazaScorul();
            Pornire pornire = new Pornire();//("Joc Yock Rescue");
            pornire.init();
            pornire.setVisible(true);
            interfata.dispose();
        }
        else{
            directivaCurenta=cauta(a.cod);

            apa+=directivaCurenta.deltaApa;
            mancare+=directivaCurenta.deltaMancare;

            jLabelApa.setText(apa+"");
            jLabelMancare.setText(mancare+"");
            directivaCurenta.afiseaza();
        }
        
    }
    
    void executaDirectiva(Directiva d){
        directivaCurenta=d;
        apa+=d.deltaApa;
        mancare+=d.deltaMancare;
        jLabelApa.setText(apa+"");
        jLabelMancare.setText(mancare+"");
        d.afiseaza();
    }
    
}