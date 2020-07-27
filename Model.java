package joc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class Model extends AbstractTableModel{
    
    ArrayList<Scor> scoruri;
    
    Model() throws FileNotFoundException, IOException{
        scoruri=new ArrayList();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("scoruri.txt")));
        String linie;
        String[] cuvinte;
        do{
            linie=br.readLine();
            if(linie==null)
                break;
            cuvinte=linie.split(", ");
            scoruri.add(new Scor(cuvinte[0], Integer.parseInt(cuvinte[1])));
        }while(true);
        br.close();
        this.fireTableDataChanged();
    }

    public String getColumnName(int c){
        if(c==0)
            return "Nume";
        return "Zi";
    }
    
    @Override
    public int getRowCount() {
        return scoruri.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Scor s=scoruri.get(rowIndex);
        if(columnIndex==0)
            return s.nume;
        return s.zi;
    }
    
}
