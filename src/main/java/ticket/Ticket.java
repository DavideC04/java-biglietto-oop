package ticket;

import java.math.BigDecimal;

/*
Consegna: creare una classe Biglietto con due attributi interi: km e età del passeggero.
Nel costruttore vanno valorizzati entrambi gli attributi, assicurandosi che abbiano valori validi (creare due metodi isValidKm e isValidEta che implementano questa logica).
In caso anche solo uno non sia valido, sollevare un’eccezione. Aggiungere tre costanti:
il costo chilometrico di 21 centesimi di €/km (BigDecimal),
lo sconto over 65 del 40 % (BigDecimal)
lo sconto minorenni del 20% (BigDecimal).
Implementare il metodo public che calcola il prezzo del biglietto comprensivo di eventuale sconto (calcolaPrezzo).
Per eseguire il calcolo dello sconto aggiungere un metodo private calcolaSconto, da chiamare dentro al metodo calcolaPrezzo.
 */
public class Ticket {
    // ATTRIBUTI
    private int km;
    private int age;

    // valori costanti, uso static final
    private static final BigDecimal kmPrice= new BigDecimal(0.21);
    private static final BigDecimal discount65=new BigDecimal(0.40);
    private static final BigDecimal discountMinor = new BigDecimal(0.20);


    // COSTRUTTORI
    // costruttore con eccezione

    public Ticket(int km, int age) throws IllegalArgumentException {
        if (!isValidKm() || !isValidEta()){
            throw new IllegalArgumentException("Invalid km and age values");
        }
        this.km = km;
        this.age = age;
    }


    // GETTER E SETTER

    public int getKm() {
        return km;
    }

    public int getAge() {
        return age;
    }

    // METODI
    // validità km
    public boolean isValidKm() {
        return km>=0;
    }

    //validità età passeggero
    public boolean isValidEta() {
        return age >= 2 && age <= 100;
    }

    // metodo del calcolo del prezzo biglietto

    public BigDecimal calcolaPrezzo() {
        BigDecimal initialPrice = kmPrice.multiply(new BigDecimal(km));
        BigDecimal discount = calcolaSconto();
        return initialPrice.subtract(discount);
    }



    // metodo privato per calcolare lo sconto
    private BigDecimal calcolaSconto(){
            BigDecimal discount= BigDecimal.valueOf(0);
                // se l'età del passeggero è maggiore o uguale a 65 anni, applico uno sconto del 40%
            if (age>=65){
                    discount = kmPrice.multiply(new BigDecimal(km)).multiply(discount65);
            } else if (age <18){
                    // se invece il passeggero ha meno di 18 anni, applico uno sconto del 20%
                    discount = kmPrice.multiply(new BigDecimal(km)).multiply(discountMinor);
            }
            return discount;
    }



}
