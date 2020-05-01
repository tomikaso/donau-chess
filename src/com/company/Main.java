package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here21

        System.out.println("An der Donau am 24.10.2014");
        Stellung Brett1 = new Stellung();
        Stellung Gegenzug;
        Scanner sc = new Scanner(System.in);
        String spielen = "Go!";
        int Rechentiefe = 3;
        int MaxRT = 8;
        Variante NeuerZug = new Variante();

        Brett1.Grundstellung();
        System.out.println ("Grundstellung gesetzt");
        System.out.println ("Zugeingabe via normaler Notation, z.B. 'B7B5'. Schlagzeichen x weglassen");

        Brett1.StellungAusgeben();


        System.out.println("Bewertung = " + Brett1.getBewertung());

        //Neuen weissen Zug berechnen lassen ;-)

         NeuerZug.Tiefe = Rechentiefe; // Rechentiefe vorinitialisieren
         NeuerZug = NeuerZug.Berechnen(Brett1, 0, Rechentiefe, MaxRT); // true = weiss am Zug
         System.out.print("Erster Zug berechnet ;-) ");


        while (spielen.charAt(0) < 98 ) {


            // Erste Variante zeigen
            NeuerZug.VarianteAusgeben(Rechentiefe);

            System.out.print("Dein Zug ;-) ");
            String eingabe = sc.next();
            spielen = eingabe;
            if (eingabe.charAt(0)== 'a'){
                // automatisach spielen
                NeuerZug = NeuerZug.Berechnen(Brett1,0, Rechentiefe, MaxRT);
                Brett1 = NeuerZug.BesteZuege[1];
            }
            if (eingabe.charAt(0) < 73) { // Mein eigener Zug folgt hier
                Gegenzug = Eingabe.MeinZug(NeuerZug.BesteZuege[1], eingabe);
                Gegenzug.WeissAmZug = !Gegenzug.WeissAmZug; // Zugfolge umderehen :-)
                Gegenzug.StellungAusgeben();
                Brett1 = Gegenzug;
                NeuerZug = NeuerZug.Berechnen(Brett1, 0, Rechentiefe, MaxRT);
            }
        }
        System.out.print(" Uf widerluege :-)");
    }
}
