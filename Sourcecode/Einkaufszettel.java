public class Einkaufszettel
{
    private Benutzer zugBenutzer;
    private Produkt[] aktuelleListe;

    /**
     * Konstruktor für Objekte der Klasse Einkaufszettel
     */
    public Einkaufszettel(Benutzer pBenutzer)
    {
        zugBenutzer = pBenutzer;
    }
    
    public void istEingekauft(Produkt pProdukt)
    {
        zugBenutzer.zuLagerHinzufügen(pProdukt);
    }
    
    public void listeErstellen(int pTage)
    {
        aktuelleListe = zugBenutzer.gibZutatenXTage(pTage);
        listeUeberarbeiten();
        listeVergleichLager();
        listeUeberarbeiten2();
    }
    
    private void listeVergleichLager()
    {
        Speicher lager = this.zugBenutzer.gibLager();
        for(int i = 0; i < this.aktuelleListe.length; i++)
        {
            for(int j = 0; j < lager.gibGroeßeLager(); j++)
            {
                if(this.aktuelleListe[i].gibName().equals(lager.gibZutat(j).gibName()) == true)
                {
                    this.aktuelleListe[i].reduziereMenge(lager.gibZutat(j).gibMenge());
                }
                if (this.aktuelleListe[i].gibMenge() < 0)
                {
                    this.aktuelleListe[i].setzeMenge(0);
                }
            }
        }
    }
    
    private void listeUeberarbeiten()
    {
        int zaehler = 0;
        Produkt[] duplListe = new Produkt[this.listeUeberabeitetLänge()];
        for(int i = 0; i < this.aktuelleListe.length; i++)
        {
            if(this.lineareSucheIstWertVorhanden(duplListe, this.aktuelleListe[i].gibName()))
            {
                duplListe[zaehler] = this.aktuelleListe[i];
                duplListe[zaehler].setzeMenge(0);
                zaehler++;
            }
        }
        
        zaehler = 0;
        
        for(int i = 0; i < this.aktuelleListe.length; i++)
        {
            String aktuellerName = this.aktuelleListe[i].gibName();
            boolean gefunden = false;
            while(!gefunden)
            {
                if (duplListe[zaehler].gibName().equals(aktuellerName)== true)
                {
                    duplListe[zaehler].erhoeheMenge(this.aktuelleListe[i].gibMenge());
                }
                zaehler++;
            }
            zaehler = 0;
        }
        aktuelleListe = duplListe;
    }
    
    private int listeUeberabeitetLänge()
    {
        int zaehler = 0;
        Produkt[] duplListe = new Produkt[this.aktuelleListe.length];
        for(int i = 0; i < this.aktuelleListe.length; i++)
        {
            if(this.lineareSucheIstWertVorhanden(duplListe, this.aktuelleListe[i].gibName()))
            {
                duplListe[zaehler] = this.aktuelleListe[i];
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
    
    private void listeUeberarbeiten2()
    {
        int zaehler = 0;
        for(int i = 0; i < this.aktuelleListe.length; i++)
        {
            if(this.aktuelleListe[i].gibMenge() > 0)
            {
                zaehler++;
            }
        }
        Produkt[] temp = new Produkt[zaehler];
        zaehler = 0;
        for(int i = 0; i < this.aktuelleListe.length; i++)
        {
            if(this.aktuelleListe[i].gibMenge() > 0)
            {
                temp[zaehler] = this.aktuelleListe[i];
                zaehler++;
            }
        }
        this.aktuelleListe = temp;
    }
}