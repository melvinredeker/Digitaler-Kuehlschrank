public class Tag
{
    private int anzahlMahlzeiten;
    private Mahlzeit[] mahlzeiten;

    /**
     * Konstruktor für Objekte der Klasse Tag
     */
    public Tag(int pMahlzeiten)
    {
        this.anzahlMahlzeiten = pMahlzeiten;
        this.mahlzeiten = new Mahlzeit[this.anzahlMahlzeiten];
        for (int i = 0; i < this.mahlzeiten.length; i++)
        {
            this.mahlzeiten[i] = new Mahlzeit();
        }
    }

    /**
     *  Gibt Kalorien für ganzen Tag
     *  
     *  @return Kalorien am Tag
     */
    public int gibKalorienTag()
    {
        int zaehler = 0;
        for (int i = 0; i < this.mahlzeiten.length; i++)
        {
            zaehler = zaehler + this.mahlzeiten[i].gibKalorienMahlzeit();
        }
        return zaehler;
    }

    /**
     * 
     */
    public void zutatZuMahlzeitHinzufügen(int pMahlzeit, String pName, double pMenge, String pEinheit, int pKalorien)
    {
        this.mahlzeiten[pMahlzeit].zutatHinzufügen(pName, pMenge, pEinheit, pKalorien);
    }
    
    public int gibAnzahlZutatenTag()
    {
        int zaehler = 0;
        for (int i = 0; i < anzahlMahlzeiten; i++)
        {
            zaehler = zaehler + this.mahlzeiten[i].gibAnzahlZutatenMahlzeit();
        }
        return zaehler;
    }
    
    public int gibAnzahlZutatenMahlzeit(int pMahlzeit)
    {
        return this.mahlzeiten[pMahlzeit].gibAnzahlZutatenMahlzeit();
    }
    
    public Produkt gibZutatVonMahlzeit(int pMahlzeit, int pZutat)
    {
        return this.mahlzeiten[pMahlzeit].gibZutat(pZutat);
    }
}