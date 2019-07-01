import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
public class Ernaehrungsplan
{
    private int zielzufuhr;
    private int anzahlMahlzeiten;
    private Tag[] tage;
    private String fileName; 

    /**
     * Konstruktor für Objekte der Klasse Ernaehrungsplan
     */
    public Ernaehrungsplan(int pZielzufuhr, int pAnzahlMahlzeiten)
    {
        this.zielzufuhr = pZielzufuhr;
        this.anzahlMahlzeiten = pAnzahlMahlzeiten;
        this.tage = new Tag[7];
        for (int i = 0; i < this.tage.length; i++)
        {
            this.tage[i] = new Tag(pAnzahlMahlzeiten);
        }
    }

    /**
     *  Liest Datei ein
     */
    public void dateiEinlesen()
    {
        this.fileName = fileSelect();

        if (this.fileName == "")
        {
            System.out.println("Bitte Datei auswählen.");
            return;
        }

        int tag = 0;
        int mahlzeit = 0;
        String[] speicher;
        BufferedReader in = null;
        String zeile = null;
        try
        {
            in = new BufferedReader(new FileReader(fileName));
            while ((zeile = in.readLine()) != null)
            {
                if (zeile.equals("-"))
                {
                    mahlzeit++;
                    if (mahlzeit == this.anzahlMahlzeiten)
                    {
                        mahlzeit = 0;
                    }
                }
                else if (zeile.equals("="))
                {
                    tag++;
                    if (tag == 7)
                    {
                        tag = 0;
                    }
                    mahlzeit = 0;
                }
                else
                {
                    speicher = zeile.split(";");
                    this.tage[tag].zutatZuMahlzeitHinzufügen(mahlzeit, speicher[0], Double.parseDouble(speicher[1]), speicher[2], Integer.parseInt(speicher[3]));
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Bestimmt den Dateipfad.
     * 
     * @return  Pfad der Dartei
     */
    private String fileSelect()
    {
        String dateiPfad;

        FileFilter filter = new FileNameExtensionFilter("Textfile", "txt");
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(filter);
        int rueckgabeWert = chooser.showDialog(null, "Import");

        if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
        {
            dateiPfad = chooser.getCurrentDirectory() + "\\" + chooser.getSelectedFile().getName();
            return dateiPfad;
        }
        else
        {
            return "";
        }
    }
    
    private int gibAnzahlZutatenXTage(int pAktuellerTag, int pTage)
    {
        int zaehler = 0;
        for (int i = 0; i < pTage; i++)
        {
            int zahl = pAktuellerTag + i;
            if (zahl > 6)
            {
                zahl = zahl % 6;
            }
            zaehler = zaehler + this.tage[zahl].gibAnzahlZutatenTag();
        }
        return zaehler;
    }
    
    public Produkt[] gibBenötigteZutatenXTage(int pAktuellerTag, int pTage)
    {
        Produkt[] benZutaten = new Produkt[this.gibAnzahlZutatenXTage(pAktuellerTag, pTage)];
        int zaehler = 0;
        for (int i = 0; i < pTage; i++)
        {
            int zahl = pAktuellerTag + i;
            if (zahl > 6)
            {
                zahl = zahl % 6;
            }
            for(int j = 0; j < this.anzahlMahlzeiten; j++)
            {
                for (int k = 0; k < this.tage[zahl].gibAnzahlZutatenMahlzeit(j); k++)
                {
                    benZutaten[zaehler] = this.tage[zahl].gibZutatVonMahlzeit(j, k);
                    zaehler++;
                }
            }
        }
        return benZutaten;
    }
}