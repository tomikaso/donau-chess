package com.company;

/**
 *  Created by Thomas on 21.11.2014.
 */
public class Variante {
    public Stellung BesteZuege[] = new Stellung[10];
    public int Bewertung;
    public int Tiefe;
    public int BerechneteStellungen;
    public int BerechneteSchlagzuege;

    // Private Zwischenvariablen

    // Die rekursive Funktion Berechnen liefert die beste Variante von Level bis Rechentiefe zurück
    public Variante Berechnen(Stellung Ausgangslage, int Level, int Rechentiefe, int MaxRT) {

        // Variablen für Zwischenrechnungen
        Variante LokalerKnoten = new Variante();
        Variante AktuellerPfad;
        boolean WeissAmZug = Ausgangslage.WeissAmZug;
        int lokaleBewertung;

        //Wir speichern unseren Startpunkt :-) und zwar auf Level-Höhe
        LokalerKnoten.BesteZuege[Level] = Ausgangslage.clooney();

        lokaleBewertung = Ausgangslage.getBewertung();

        if (lokaleBewertung > 6000){ // Schwarz ist schachmatt
            LokalerKnoten.Bewertung = 19999;
            for (int i=Level+1 ; i<= Rechentiefe; i++ ){
                LokalerKnoten.BesteZuege[i] = Ausgangslage; // Auffüllen um Zero-Pointer zu vermeiden
            }
            return LokalerKnoten;  // Ast nicht weiter traversieren
        }

        if (lokaleBewertung < - 6000){ // Weiss ist schachmatt
            LokalerKnoten.Bewertung = -19999;
            for (int i=Level+1 ; i<= Rechentiefe; i++ ){
                LokalerKnoten.BesteZuege[i] = Ausgangslage; // Auffüllen um Zero-Pointer zu vermeiden
            }
            return LokalerKnoten; // Ast nicht weiter traversieren
        }

        if ((Level>=Rechentiefe & !Ausgangslage.Schlagzug) || Level>=MaxRT ){ // Wir sind am Rekursionsende und fast fertig.
            LokalerKnoten.Bewertung = lokaleBewertung;
            LokalerKnoten.BerechneteStellungen = 1;
            if (Ausgangslage.Schlagzug) LokalerKnoten.BerechneteSchlagzuege =1;
            return LokalerKnoten;
        }

        // Wir sind noch im Baum und müssen rekursiv aufrufen
        GueltigeStellungen ZugNeu = new GueltigeStellungen();

        if (Level>Rechentiefe) ZugNeu.NurSchlagzuege = true; // ab "Rechentiefe" nur noch Schlagzüge betrachten

        ZugNeu.getStellungen(Ausgangslage);// Berechne für diese Ausgangslage alle Möglichkeiten für weiss / oder schwarz

        LokalerKnoten.Bewertung = -20000; // Mit Pessimismus initialisieren
        if (!WeissAmZug) {
            LokalerKnoten.Bewertung = 20000; // Andersherum bei Schwarz am Zug
        }

        // Nun werden für alle Halbzüge die Bewertungen berechnet.
        for (int i = 0; i < ZugNeu.length(); i++) {
                AktuellerPfad = Berechnen(ZugNeu.NeuePosition[i], Level+1 , Rechentiefe, MaxRT); // Rekursiver Aufruf
                LokalerKnoten.BerechneteStellungen = LokalerKnoten.BerechneteStellungen + AktuellerPfad.BerechneteStellungen; // Zähler für Anzahl Stellungen
                LokalerKnoten.BerechneteSchlagzuege = LokalerKnoten.BerechneteSchlagzuege + AktuellerPfad.BerechneteSchlagzuege; // Zähler für Anzahl Stellungen

            if ((WeissAmZug && (AktuellerPfad.Bewertung > LokalerKnoten.Bewertung)) || (!WeissAmZug && (AktuellerPfad.Bewertung < LokalerKnoten.Bewertung))) {
                // Neuen stärkste Variante merken; Klonen!

                LokalerKnoten.Bewertung = AktuellerPfad.Bewertung;

                LokalerKnoten.Bewertung = AktuellerPfad.Bewertung;

                if (Rechentiefe > Level) System.arraycopy(AktuellerPfad.BesteZuege, Level + 1, LokalerKnoten.BesteZuege, Level + 1, Rechentiefe);
            }

        }
        return LokalerKnoten;
    }

    // Variante ausgeben
    public void VarianteAusgeben(int Tiefe) {
        for (int Vtiefe = Tiefe; Vtiefe > 0; Vtiefe--) {
            System.out.println("Variantenausgabe: Level: " + Vtiefe );
            BesteZuege[Vtiefe].StellungAusgeben();
        }
        double BewAusgabe = Bewertung;
        BewAusgabe = BewAusgabe/100;
        System.out.println(" V-Bewertung: " + BewAusgabe + " Anz. Stellungen: " + BerechneteStellungen + "Anz. Schlagzüge: " + BerechneteSchlagzuege );
    }
    }
