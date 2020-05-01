package com.company;


// Created by indexplus on 16.10.2014.
public class Stellung implements Cloneable {

        char  [][] feld = new char [8][8];
        boolean WeissAmZug;
        private boolean Schlagzug;
        int RochadeWeiss = 0; // 0 möglich, 1 rochiert, 2 nicht möglich
        int RochadeSchwarz = 0;

    public Stellung clooney() {

        Stellung tmp = new Stellung();

            for (int i = 0; i < 8; i++) {
                System.arraycopy(feld[i], 0, tmp.feld[i], 0, 8);
            }

        tmp.WeissAmZug = WeissAmZug;
        tmp.RochadeWeiss = RochadeWeiss;
        tmp.RochadeSchwarz = RochadeSchwarz;
        return tmp;}


    void Grundstellung() {

        for(int i=1; i<7; i++)
            for(int j=0; j<8; j++)
                feld[i][j] = 32;
        // setzt die leeren Felder in der Mitte

        for(int j=0; j<8; j++)
            feld[1][j] = 'B';
        // Reihe 2 Weisse Bauern

        for(int j=0; j<8; j++)
            feld[6][j] = 'b';
        // Reihe 7 Schwarze Bauern

        // Zwei schwarze Läufer
        feld[7][2]= 'l';
        feld[7][5]= 'l';
        // Zwei schwarze Springer
        feld[7][1]= 's';
        feld[7][6]= 's';
        // Zwei schwarze Türme
        feld[7][0]= 't';
        feld[7][7]= 't';
        // Schwarze Dame und König
        feld[7][3]= 'd';
        feld[7][4]= 'k';

        // Zwei weisse Läufer
        feld[0][2]= 'L';
        feld[0][5]= 'L';
        // Zwei weisse Springer
        feld[0][1]= 'S';
        feld[0][6]= 'S';
        // Zwei Weisse Türme
        feld[0][0]= 'T';
        feld[0][7]= 'T';
        // Weisse Dame und König
        feld[0][3]= 'D';
        feld[0][4]= 'K';

        WeissAmZug=true; //Wir beginnen mit Weiss

    }

/*    void Teststellung() {

        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                feld[i][j] = 32;
        // setzt die leeren Felder

        //feld[5][2]= 't';

        // Schwarze Dame und König
        feld[6][3]= 'b';
        feld[7][7]= 'k';
        feld[6][5]= 'l';


        // Zwei Weisse Türme
        // feld[1][1]= 'T';

        // Weisse Dame und König
        feld[2][5]= 'B';
        feld[0][0]= 'K';
        feld[1][4]= 'L';

        WeissAmZug=true; //Wir beginnen mit Weiss
    } */

    int getBewertung() {
        int Bewertung = 0;
        int GrundlinieW = 3;
        int GrundlinieS = 0;
        int MittelBauerBonus =0;

        for (int i = 0; i < 8; i++) {
            if (i > 0) GrundlinieW = 0;
            if (i == 7) GrundlinieS = 3;

            for (int j = 0; j < 8; j++) {

                if (j<4) MittelBauerBonus=j;
                if (j>=4) MittelBauerBonus= 7-j;

                if (feld[i][j] == 'B')
                    Bewertung = Bewertung + 100 +  i * MittelBauerBonus; //Vorrückende Bauern mit Bonus
                if (feld[i][j] == 'b')
                    Bewertung = Bewertung - 100 -  (7 - i) * MittelBauerBonus; //Vorrückende Bauern mit Bonus

                if (feld[i][j] == 'S')
                    Bewertung = Bewertung + 275 - GrundlinieW ; // Weisser Springer
                if (feld[i][j] == 's')
                    Bewertung = Bewertung - 275 + GrundlinieS ; // Schwarzer Springer

                if (feld[i][j] == 'L') Bewertung = Bewertung + 325; // Weisser Läufer
                if (feld[i][j] == 'l') Bewertung = Bewertung - 325; // Schwarzer Läufer

                if (feld[i][j] == 'T') Bewertung = Bewertung + 500; // Weisser Turm
                if (feld[i][j] == 't') Bewertung = Bewertung - 500; // Schwarzer Turm

                if (feld[i][j] == 'D') Bewertung = Bewertung + 900; // Weisse Dame
                if (feld[i][j] == 'd') Bewertung = Bewertung - 900; // Schwarze Dame

                if (feld[i][j] == 'K') Bewertung = Bewertung + 10000; // Weisser König
                if (feld[i][j] == 'k') Bewertung = Bewertung - 10000; // Schwarzer König
            }
        }

        if (RochadeWeiss == 1) Bewertung = Bewertung + 50; // + bei der Rochade
        if (RochadeSchwarz == 1) Bewertung = Bewertung - 50; // + bei der Rochade

        return Bewertung;
    }

        void StellungAusgeben() {
            for (int i = 7; i >= 0; i--)
            {
                System.out.print (i+1 + "  ");
            for (int j = 0; j < 8; j++)
                System.out.print(feld[i][j]+" ");
            System.out.println("");
            }
            System.out.println("");
            System.out.println("   A B C D E F G H  waz=" + WeissAmZug);
        // gibt die Felder aus
        }

    public boolean isSchlagzug() {
        return Schlagzug;
    }

    public void setSchlagzug(boolean schlagzug) {
        Schlagzug = schlagzug;
    }
}

