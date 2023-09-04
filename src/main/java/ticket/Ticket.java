package ticket;

import java.math.BigDecimal;
import java.time.LocalDate;

/*
Consegna: creare una classe Biglietto con due attributi interi: km e età del passeggero.
Nel costruttore vanno valorizzati entrambi gli attributi, assicurandosi che abbiano valori validi (creare due metodi isValidKm e isValidEta che implementano questa logica).
In caso anche solo uno non sia valido, sollevare un’eccezione. Aggiungere tre costanti:
il costo chilometrico di 21 centesimi di €/km (BigDecimal),
lo sconto over 65 del 40 % (BigDecimal)
lo sconto minorenni del 20% (BigDecimal).
Implementare il metodo public che calcola il prezzo del biglietto comprensivo di eventuale sconto (calcolaPrezzo).
Per eseguire il calcolo dello sconto aggiungere un metodo private calcolaSconto, da chiamare dentro al metodo calcolaPrezzo.

Alla classe Biglietto aggiungere i seguenti attributi:
● data: LocalDate
● flessibile: boolean
Entrambi gli attributi vanno valorizzati nel costruttore.
Aggiungere due costanti:
● durata normale: 30 giorni (int)
● durata flessibile: 90 giorni (int)
Aggiungere un metodo (calcolaDataScadenza: LocalDate) che calcola la data di scadenza del biglietto, applicando la durata normale o flessibile in base al parametro flessibile(boolean).
Nel metodo che calcola il prezzo, se il biglietto è flessibile, maggiorare il prezzo del 10%.
 */
public class Ticket {
    // ATTRIBUTI
    private int km;
    private int age;
    private LocalDate date;
    private  boolean flexible;

    // valori costanti, uso static final
    private static final BigDecimal kmPrice= new BigDecimal(0.21);
    private static final BigDecimal discount65=new BigDecimal(0.40);
    private static final BigDecimal discountMinor = new BigDecimal(0.20);
    private static final int duration = 30;
    private static final int flexibleDuration = 90;
    private static final BigDecimal discountFlexible = new BigDecimal(0.10);


    // COSTRUTTORI
    // costruttore con eccezione

    public Ticket(int km, int age, boolean flexible) throws IllegalArgumentException {
        if (!isValidKm(km) || !isValidEta(age)){
            throw new IllegalArgumentException("Invalid km and age values");
        }
        this.km = km;
        this.age = age;
        this.date = LocalDate.now();
        this.flexible = flexible;
    }


    // GETTER E SETTER

    public int getKm() {
        return km;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isFlexible() {
        return flexible;
    }

    // METODI
    // validità km
    public boolean isValidKm( int km) {
        return km>0;
    }

    //validità età passeggero
    public boolean isValidEta(int age) {
        return age >= 0 && age <= 100;
    }

    // metodo del calcolo del prezzo biglietto

    public BigDecimal calcolaPrezzo() {
        BigDecimal initialPrice = kmPrice.multiply(new BigDecimal(km));
        BigDecimal discount = calcolaSconto();
        BigDecimal finalPrice = initialPrice.subtract(discount);

        // se il biglietto è flessibile, viene aggiunto uno sconto del 10%
        if (flexible){
             BigDecimal discount2 = initialPrice.multiply(discountFlexible);
             finalPrice = finalPrice.subtract(discount2);
        }

        return finalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
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

    // metodo per calcolare la scadenza del biglietto
    public LocalDate calcolaDataScadenza(){
        int expirationDate = flexible ? flexibleDuration : duration;
        return date.plusDays(expirationDate);
    }


}
