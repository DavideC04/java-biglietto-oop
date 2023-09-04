package ticket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

/*
Creare una classe Biglietteria, che contiene il metodo main in cui:
● chiedere all’utente di inserire il numero di km e l’età del passeggero
● con quei dati provare a creare un nuovo Biglietto (gestire eventuali eccezioni con try-catch)
● stampare a video il prezzo del biglietto calcolato
Modificare la classe Biglietteria in modo che, alla creazione del Biglietto, valorizzi la data con la data odierna e il parametro flessibile in base alla scelta dell’utente.
Dopo aver stampato il prezzo del biglietto, stampare a video anche la data di scadenza.
 */
public class TicketOffice {
    public static void main(String[] args) {
        // inizializzo lo scanner
        Scanner input = new Scanner(System.in);

        // uso un try-catch per gestire eventuali eccezioni
        try {

            // chiedo le informazioni all'utente
            System.out.println("Inserisci il numero di km: ");
            int km = Integer.parseInt(input.nextLine());
            System.out.println("Inserisci l'età del passeggero: ");
            int age = Integer.parseInt(input.nextLine());
            System.out.println("Il biglietto è flessibile? (true/false): ");
            boolean flexible = input.nextBoolean();


            // creo l'oggetto ticket
            Ticket ticket = new Ticket(km, age, flexible);
            // richiamo il metodo del prezzo
            BigDecimal price= ticket.calcolaPrezzo();
            // aggiungo la data di scadenza del biglietto
            LocalDate expirationDate = ticket.calcolaDataScadenza();
            // stampo a video le informazioni sul prezzo e data scadenza
            System.out.println("Il costo del biglietto è : " + price + "€");
            System.out.println("La data di scadenza del biglietto è: " + expirationDate);
        } catch (RuntimeException e){
            System.out.println("Valori non validi. Inserire di nuovo i numeri. ");
        }

        // chiudo lo scanner
        input.close();

    }
}
