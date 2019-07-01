import java.util.ArrayList;
public class Mahlzeit
{
    private ArrayList<Produkt> zutaten;
    
    /**
     * 
     */
    public Mahlzeit()
    {
        this.zutaten = new ArrayList<Produkt>();
    }
    
    /**
     * 
     */
    public int gibKalorienMahlzeit()
    {
        int zaehler = 0;
        for (int i = 0; i < this.zutaten.size(); i++)
        {
            zaehler = zaehler + this.zutaten.get(i).gibKalorien();;
        }
        return zaehler;
    }
    
    /**
     * 
     */
    public void zutatHinzufügen(String pName, double pMenge, String pEinheit, int pKalorien)
    {
        this.zutaten.add(new Produkt(pName, pMenge, pEinheit, pKalorien));
    }
    
    /**
     * 
     */
    public int gibAnzahlZutatenMahlzeit()
    {
        return this.zutaten.size();
    }
    
    public Produkt gibZutat(int pZahl)
    {
        return this.zutaten.get(pZahl);
    }
}
