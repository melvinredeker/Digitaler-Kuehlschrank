/**
 * @author Tristan Lippold 
 * @version 2019-05-17
 */
public class Benutzer
{
    private Ernaehrungsplan plan;
    private Speicher speicher;
    private Einkaufszettel zettel;
    private int aktuellerTag;
    
    /**
     * Konstruktor für Objekte der Klasse Benutzer
     */
    public Benutzer()
    {
        this.plan = new Ernaehrungsplan(5, 5);
        this.speicher = new Speicher();
        aktuellerTag = 0;
    }
    
    public void ernaehrungsplanErstellen()
    {
        this.zettel = new Einkaufszettel(this);
    }
    
    public Produkt[] gibZutatenXTage(int pTage)
    {
        return this.plan.gibBenötigteZutatenXTage(aktuellerTag, pTage);
    }
    
    public Speicher gibLager()
    {
        return speicher;
    }
    
    public void zuLagerHinzufügen(Produkt pProdukt)
    {
        speicher.zuLagerHinzufügen(pProdukt);
    }
}
