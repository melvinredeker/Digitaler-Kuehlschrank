import java.util.ArrayList;
public class Speicher
{
    private ArrayList<Produkt> lager;
    
    public Speicher()
    {
        this.lager = new ArrayList<Produkt>();
    }
    
    public boolean istVorhanden(String pProduktname)
    {
        for (int i = 0; i < this.lager.size(); i++)
        {
            if (this.lager.get(i).gibName().equals(pProduktname) == true)
            {
                return true;
            }
        }
        return false;
    }
    
    public void druckeBestand()
    {
        System.out.println("Bestand:");
        Produkt aktuellesProdukt;
        for (int i = 0; i < this.lager.size(); i++)
        {
            aktuellesProdukt = this.lager.get(i);
            System.out.println(aktuellesProdukt.gibName() + " : " + aktuellesProdukt.gibMenge() + " " + aktuellesProdukt.gibEinheit());
        }
    }
    
    public void zuLagerHinzufügen(Produkt pProdukt)
    {
        this.lager.add(pProdukt);
        this.listeUeberarbeiten();
    }
    
    public int gibGroeßeLager()
    {
        return this.lager.size();
    }
    
    public Produkt gibZutat(int pZahl)
    {
        return this.lager.get(pZahl);
    }
    
    private void listeUeberarbeiten()
    {
        int zaehler = 0;
        Produkt[] duplListe = new Produkt[this.listeUeberabeitetLänge()];
        for(int i = 0; i < this.gibGroeßeLager(); i++)
        {
            if(this.lineareSucheIstWertVorhanden(duplListe, this.gibZutat(i).gibName()))
            {
                duplListe[zaehler] = this.gibZutat(i);
                duplListe[zaehler].setzeMenge(0);
                zaehler++;
            }
        }
        
        zaehler = 0;
        
        for(int i = 0; i < this.gibGroeßeLager(); i++)
        {
            String aktuellerName = this.gibZutat(i).gibName();
            boolean gefunden = false;
            while(!gefunden)
            {
                if (duplListe[zaehler].gibName().equals(aktuellerName)== true)
                {
                    duplListe[zaehler].erhoeheMenge(this.gibZutat(i).gibMenge());
                }
                zaehler++;
            }
            zaehler = 0;
        }
        lager.clear();
        for (int i = 0; i < duplListe.length; i++)
        {
            lager.add(duplListe[i]);
        }
    }
    
    private int listeUeberabeitetLänge()
    {
        int zaehler = 0;
        Produkt[] duplListe = new Produkt[this.gibGroeßeLager()];
        for(int i = 0; i < this.gibGroeßeLager(); i++)
        {
            if(this.lineareSucheIstWertVorhanden(duplListe, this.gibZutat(i).gibName()))
            {
                duplListe[zaehler] = this.gibZutat(i);
                zaehler++;
            }
        }
        return zaehler;
    }
    
    /**
     * Bestimmt, ob ein gesuchter Wert vorhanden ist.
     * 
     * @param   pWert   gesuchter Wert
     * 
     * @return  wahr, wenn Wert vorhanden
     */
    private boolean lineareSucheIstWertVorhanden(Produkt[] pListe, String pWert)
    {
        boolean vorhanden = false;
        for(int i = 0; i < pListe.length; i++)
        {
            if(pListe[i].gibName().equals(pWert) == true)
            {
                vorhanden = true;
            }
        }
        return vorhanden;
    }
}