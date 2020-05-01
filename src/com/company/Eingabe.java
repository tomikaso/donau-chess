package com.company;

// Created by indexplus on 21.10.2014.

public class Eingabe {
    public static Stellung MeinZug (Stellung Ausgangslage, String Eingabe){
        char koordinate1;
        char koordinate2;
        char figur;
        int x;
        int y;
        // hole Startkoordinaten es beginnt mit einem Buchstaben:
        koordinate1 = Eingabe.charAt(0);
        koordinate2 = Eingabe.charAt(1);
        y = koordinate1-65;
        x = koordinate2-49;
        figur = Ausgangslage.feld[x][y];
        Ausgangslage.feld[x][y] = ' ';

        // Nun das Zielfeld berechnen und mit der Figur besetzten ;-)
        koordinate1 = Eingabe.charAt(2);
        koordinate2 = Eingabe.charAt(3);
        y = koordinate1-65;
        x = koordinate2-49;
        Ausgangslage.feld[x][y] = figur;
    return Ausgangslage;
    }
}
