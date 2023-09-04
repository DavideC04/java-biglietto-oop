package ticket;

import java.math.BigDecimal;
import java.util.Scanner;

/*
Creare una classe Biglietteria, che contiene il metodo main in cui:
● chiedere all’utente di inserire il numero di km e l’età del passeggero
● con quei dati provare a creare un nuovo Biglietto (gestire eventuali eccezioni con try-catch)
● stampare a video il prezzo del biglietto calcolato
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

            // creo l'oggetto ticket
            Ticket ticket = new Ticket(km, age);
            // richiamo il metodo del prezzo
            BigDecimal price= ticket.calcolaPrezzo();
            System.out.println("Il costo del biglietto è : " + price + "€");
        } catch (RuntimeException e){
            System.out.println("Valori non validi. Inserire di nuovo i numeri. ");
        }

        // chiudo lo scanner
        input.close();

    }
}
