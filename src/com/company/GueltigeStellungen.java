package com.company;

// Created by indexplus on 24.10.2014.
// Now with Rochade, 8.12.2014
public class GueltigeStellungen {
    Stellung [] NeuePosition = new Stellung [99];
    boolean NurSchlagzuege = false;

    private int Position;
    private int PosSchlagzug;

    // liefert eine Liste von Stellungen aller gültigen weissen Züge zurück.

   private void Ziehen (Stellung Brett, int i, int j, int m, int n, char figur, boolean WeissAmZug){
       int Einfue;
       boolean SZ = false;

        if (this.NurSchlagzuege && ( Brett.feld [m][n] == ' ')) return; // Nur Schlagzüge werden geschrieben, daher RETURN

        if (Brett.feld [m][n] != ' '){  // Ein Schlagzug folgt

            NeuePosition[Position] = NeuePosition[PosSchlagzug]; // Der Nicht-Schlagzug wird nach hinten geschoben
            Einfue= PosSchlagzug;
            PosSchlagzug++;
            SZ = true; // Schlagzug
        }
        else
        {
            Einfue = Position;
        }
        NeuePosition[Einfue] = new Stellung();
        NeuePosition[Einfue] = Brett.clooney();
        NeuePosition[Einfue].Schlagzug = SZ;
        NeuePosition[Einfue].feld[i][j]= 32; //Figur rückt vor

        NeuePosition[Einfue].feld[m][n]= figur; //Figur am neuen Ort
        NeuePosition[Einfue].WeissAmZug = WeissAmZug;

        if (figur == 'K' && NeuePosition[Einfue].RochadeWeiss == 0) NeuePosition[Einfue].RochadeWeiss = 2; //keine Rochade mehr möglich
        if (figur == 'k' && NeuePosition[Einfue].RochadeSchwarz == 0) NeuePosition[Einfue].RochadeSchwarz = 2; //keine Rochade mehr möglich

        Position++;
        }

   GueltigeStellungen getStellungen(Stellung aktuellesBrett) {
        Position = 0;
        boolean WeissAmZug;
        int WegLO ;
        int WegO;
        int WegRO;
        int WegR;
        int WegRU;
        int WegU;
        int WegLU;
        int WegL;
       // Hilfsvariablen zu möglichen 8 Himmelsrichtungen
       WeissAmZug = aktuellesBrett.WeissAmZug;


        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                // Schleife über alle Felder der Stellung
                WegLO = 1; // Vorinitialisierung aller Wegvariablen.
                WegRO = 1;
                WegRU = 1;
                WegLU = 1;
                WegL = 1;
                WegR = 1;
                WegO = 1;
                WegU = 1;

                if (WeissAmZug) {

                    //NUN kommen alle weissen Figuren und Zugmöglichkeiten

                    if (aktuellesBrett.feld [i][j]  == 'B') {
                        //Ein weisser Bauer!
                        if (aktuellesBrett.feld [i+1][j] == 32 && i < 7) {
                            // der Bauer kann vorrücken!
                           if (i < 6) {
                                Ziehen(aktuellesBrett,i,j,i+1,j,'B', !WeissAmZug); //Bauer am neuen Ort
                            } else {
                                Ziehen(aktuellesBrett,i,j,i+1,j,'D', !WeissAmZug); //;-) Umwandlung zur Dame
                            }
                        }
                        //Am Anfang darf er sogar 2 gehen ;-)
                        if (i == 1 && aktuellesBrett.feld [i+1][j]  == 32 && aktuellesBrett.feld [i+2][j] == 32) {
                            Ziehen(aktuellesBrett,i,j,i+2,j,'B', !WeissAmZug); // Bauer am neuen Ort
                            }
                        if (i < 7 && j < 7) {
                            if (aktuellesBrett.feld [i+1][j+1] > 97) {
                                // der Bauer kann nach rechts schlagen!

                                if (i < 6) {
                                    Ziehen(aktuellesBrett,i,j,i+1,j+1,'B', !WeissAmZug); // Bauer am neuen Ort
                                } else {
                                    Ziehen(aktuellesBrett,i,j,i+1,j+1,'D', !WeissAmZug); //;-) Umwandlung zur Dame
                                }
                            }
                        }
                        if (i < 7 && j > 0) {
                            if (aktuellesBrett.feld [i+1][j-1] > 97) {
                                // der Bauer kann nach links schlagen!

                                if (i < 6) {
                                    Ziehen(aktuellesBrett,i,j,i+1,j-1,'B', !WeissAmZug); // Bauer am neuen Ort
                                } else {
                                    Ziehen(aktuellesBrett,i,j,i+1,j-1,'D', !WeissAmZug); //;-) Umwandlung zur Dame
                                }
                            }
                        }
                    }
                    if (aktuellesBrett.feld [i][j] == 'S') {
                        //Ein weisser Springer !
                        if (j >= 2) {
                            // kann ganz nach links springen
                            if (i <= 6) {
                                // kann eins nach oben springen
                                if (aktuellesBrett.feld [i+1][j-2] == 32 || aktuellesBrett.feld [i+1][j-2] > 97) {
                                   Ziehen(aktuellesBrett,i,j,i+1,j-2,'S', !WeissAmZug); // Springer am neuen Ort
                                }
                            }
                            if (i > 0) {
                                // kann eins nach oben springen
                                if (aktuellesBrett.feld [i-1][j-2] == 32 || aktuellesBrett.feld [i-1][j-2] > 97) {
                                    Ziehen(aktuellesBrett,i,j,i-1,j-2,'S', !WeissAmZug); // Springer am neuen Ort
                                }
                            }

                        }
                        if (j <= 5) {
                            // kann ganz nach rechts springen
                            if (i <= 6) {
                                // kann eins nach oben springen
                                if (aktuellesBrett.feld [i+1][j+2] == 32 || aktuellesBrett.feld [i+1][j+2] > 97) {
                                    Ziehen(aktuellesBrett,i,j,i+1,j+2,'S', !WeissAmZug); // Springer am neuen Ort
                                }
                            }
                            if (i > 0) {
                                // kann eins nach oben springen
                                if (aktuellesBrett.feld [i-1][j+2] == 32 || aktuellesBrett.feld [i-1][j+2] > 97) {
                                    Ziehen(aktuellesBrett,i,j,i-1,j+2,'S', !WeissAmZug); // Springer am neuen Ort
                                }
                            }

                        }
                        if (i <=5) {
                            // kann ganz nach oben springen
                            if (j <= 6) {
                                // kann eins nach rechts springen
                                if (aktuellesBrett.feld [i+2][j+1] == 32 || aktuellesBrett.feld [i+2][j+1] > 97) {
                                    Ziehen(aktuellesBrett,i,j,i+2,j+1,'S', !WeissAmZug); // Springer am neuen Ort
                                }
                            }
                            if (j > 0) {
                                // kann eins nach links springen
                                if (aktuellesBrett.feld [i+2][j-1] == 32 || aktuellesBrett.feld [i+2][j-1] > 97) {
                                    Ziehen(aktuellesBrett,i,j,i+2,j-1,'S', !WeissAmZug); // Springer am neuen Ort
                                }
                            }

                        }
                        if (i >= 2) {
                            // kann ganz nach unten springen
                            if (j <= 6) {
                                // kann eins nach rechts springen
                                if (aktuellesBrett.feld [i-2][j+1] == 32 || aktuellesBrett.feld [i-2][j+1] > 97) {
                                    Ziehen(aktuellesBrett,i,j,i-2,j+1,'S', !WeissAmZug); // Springer am neuen Ort
                                }
                            }
                            if (j > 0) {
                                // kann eins nach oben springen
                                if (aktuellesBrett.feld [i-2][j-1] == 32 || aktuellesBrett.feld [i-2][j-1] > 97) {
                                    Ziehen(aktuellesBrett,i,j,i-2,j-1,'S', !WeissAmZug); // Springer am neuen Ort
                                }
                            }

                        }
                    }
                    // Weisser Läufer
                    if (aktuellesBrett.feld [i][j] == 'L') {
                        //Ein weisser Läufer!
                        //Weg-Variablen zurücksetzen, maximal bis n=7 Schritte

                        for (int n = 1; n <= 7; n++) {
                            // Läufer hat vier Laufrichtungen, wir beginnen mit Links Oben
                            if (WegLO == 1 && i + n <= 7 && j - n >= 0 && (aktuellesBrett.feld [i+n][j-n] == 32 || aktuellesBrett.feld [i+n][j-n] > 96)) {
                                // der Läufer kann nach links oben vorrücken!

                                if (aktuellesBrett.feld [i+n][j-n] > 96) {
                                    WegLO = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett,i,j,i+n ,j-n,'L', !WeissAmZug); // Läufer am neuen Ort
                            } else {
                                WegLO = 0;
                            }// Aber nun ist der Weg blockiert ;-)
                            // Wir fahren fort mit Rechts Oben
                            if (WegRO == 1 && i + n <= 7 && j + n <= 7 && (aktuellesBrett.feld [i+n][j+n] == 32 || aktuellesBrett.feld [i+n][j+n] > 96)) {
                                // der Läufer kann nach rechts oben vorrücken!
                                if (aktuellesBrett.feld [i+n][j+n] > 96) {
                                    WegRO = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett,i,j,i+n ,j+n,'L', !WeissAmZug); // Läufer am neuen Ort
                            } else {
                                WegRO = 0;
                            }// Aber nun ist der Weg blockiert ;-)

                            // Wir fahren fort mit Links Unten
                            if (WegLU == 1 && i - n >= 0 && j - n >= 0 && (aktuellesBrett.feld [i-n][j-n] == 32 || aktuellesBrett.feld [i-n][j-n] > 97)) {
                                // der Läufer kann nach links unten vorrücken!
                                if (aktuellesBrett.feld [i-n][j-n] > 96) {
                                    WegLU = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett,i,j,i-n ,j-n,'L', !WeissAmZug); // Läufer am neuen Ort
                            } else {
                                WegLU = 0;
                            }// Aber nun ist der Weg blockiert ;-)

                            // Und zuletzt rechts unten
                            if (WegRU == 1 && i - n >= 0 && j + n <= 7 && (aktuellesBrett.feld [i-n][j+n] == 32 || aktuellesBrett.feld [i-n][j+n] > 97)) {
                                // der Läufer kann nach rechts unten vorrücken!
                                if (aktuellesBrett.feld [i-n][j+n] > 96) {
                                    WegRU = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett,i,j,i-n ,j+n,'L', !WeissAmZug); // Läufer am neuen Ort
                            } else {
                                WegRU = 0;
                            }// Aber nun ist der Weg blockiert ;-)
                        }
                    }
                        // Weisser Turm
                    if (aktuellesBrett.feld [i][j] == 'T') {
                        //Ein weisser Turm!
                        // maximal bis n=7 Schritt
                        for (int n = 1; n <= 7; n++) {
                            // Turm hat vier Laufrichtungen, wir beginnen mit Links
                            if (WegL == 1 && j - n >= 0 && (aktuellesBrett.feld [i][j-n] == 32 || aktuellesBrett.feld [i][j-n] > 96)) {
                                // der Turm kann nach links vorrücken!
                                if (aktuellesBrett.feld [i][j-n] > 96) {
                                    WegL = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett, i, j, i, j - n, 'T', !WeissAmZug); // Turm am neuen Ort
                            } else {
                                WegL = 0;
                            }// Aber nun ist der Weg blockiert ;-)
                            // Wir fahren fort mit Rechts
                            if (WegR == 1 && j + n <= 7 && (aktuellesBrett.feld [i][j+n] == 32 || aktuellesBrett.feld [i][j+n] > 96)) {
                                // der Läufer kann nach links oben vorrücken!
                                if (aktuellesBrett.feld [i][j+n] > 96) {
                                    WegR = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett, i, j, i, j + n, 'T', !WeissAmZug); // Läufer am neuen Ort
                            } else {
                                WegR = 0;
                            }// Aber nun ist der Weg blockiert ;-)

                            // Wir fahren fort mit Unten
                            if (WegU == 1 && i - n >= 0 && (aktuellesBrett.feld [i-n][j] == 32 || aktuellesBrett.feld [i-n][j] > 97)) {
                                // der Turm kann nach unten vorrücken!
                                if (aktuellesBrett.feld [i-n][j] > 96) {
                                    WegU = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett, i, j, i - n, j, 'T', !WeissAmZug); // Turm am neuen Ort
                            } else {
                                WegU = 0;
                            }// Aber nun ist der Weg blockiert ;-)

                            // Und zuletzt oben
                            if (WegO == 1 && i + n <= 7 && (aktuellesBrett.feld [i+n][j] == 32 || aktuellesBrett.feld [i+n][j] > 97)) {
                                // der Turm kann nach oben vorrücken!
                                if (aktuellesBrett.feld [i+n][j] > 96) {
                                    WegO = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett, i, j, i + n, j, 'T', !WeissAmZug); // Turm am neuen Ort
                            } else {
                                WegO = 0;
                            }// Aber nun ist der Weg blockiert ;-)
                        }
                    }
                        // Weisse Dame
                    if (aktuellesBrett.feld [i][j] == 'D') {
                            //Ein weisser Turm!
                            // maximal bis n=7 Schritt
                            for (int n = 1; n <= 7; n++) {
                                // Die Dame ist Läufer und Turm wir beginnen mit dem Turm
                                if (WegL == 1 && j - n >= 0 && (aktuellesBrett.feld [i][j-n]== 32 || aktuellesBrett.feld [i][j-n] > 96)) {
                                    // die Dame kann nach links vorrücken!
                                    if (aktuellesBrett.feld [i][j-n] > 96) {
                                        WegL = 0;
                                    } // Das wird ein Schlagzug, danach ist Schluss
                                    Ziehen(aktuellesBrett,i,j,i ,j-n,'D', !WeissAmZug); // Dame am neuen Ort
                                }
                                else {
                                    WegL = 0;
                                }// Aber nun ist der Weg blockiert ;-)
                                // Wir fahren fort mit Rechts
                                if (WegR == 1 && j + n <= 7 && (aktuellesBrett.feld [i][j+n] == 32 || aktuellesBrett.feld [i][j+n] > 96)) {
                                    // die Dame kann nach rechts vorrücken!
                                    if (aktuellesBrett.feld [i][j+n] > 96) {
                                        WegR = 0;
                                    } // Das wird ein Schlagzug, danach ist Schluss
                                    Ziehen(aktuellesBrett,i,j,i ,j+n,'D', !WeissAmZug); // Dame am neuen Ort
                                } else {
                                    WegR = 0;
                                }// Aber nun ist der Weg blockiert ;-)

                                // Wir fahren fort mit Unten
                                if (WegU == 1 && i - n >= 0 && (aktuellesBrett.feld [i-n][j] == 32 || aktuellesBrett.feld [i-n][j] > 97)) {
                                    // die Dame kann nach unten vorrücken!
                                    if (aktuellesBrett.feld [i-n][j] > 96) {
                                        WegU = 0;
                                    } // Das wird ein Schlagzug, danach ist Schluss
                                    Ziehen(aktuellesBrett,i,j,i-n ,j,'D', !WeissAmZug); // Dame am neuen Ort
                                } else {
                                    WegU = 0;
                                }// Aber nun ist der Weg blockiert ;-)

                                // Und zuletzt oben
                                if (WegO == 1 && i + n <= 7 && (aktuellesBrett.feld [i+n][j] == 32 || aktuellesBrett.feld [i+n][j] > 97)) {
                                    // die Dame kann nach oben vorrücken!
                                    if (aktuellesBrett.feld [i + n][j] > 96) {
                                        WegO = 0;
                                    } // Das wird ein Schlagzug, danach ist Schluss
                                    Ziehen(aktuellesBrett,i,j,i+n ,j,'D', !WeissAmZug); // Dame am neuen Ort
                                } else {
                                    WegO = 0;
                                }// Aber nun ist der Weg blockiert ;-)

                                // Nun kommen die Damen-Läuferzüge. Vier Laufrichtungen, wir beginnen mit Links Oben
                                if (WegLO == 1 && i + n <= 7 && j - n >= 0 && (aktuellesBrett.feld [i+ n][j- n] == 32 || aktuellesBrett.feld [i+ n][j- n]> 96)) {
                                    // der Dame kann nach links oben vorrücken!

                                    if (aktuellesBrett.feld [i+ n][j- n] > 96) {
                                        WegLO = 0;
                                    } // Das wird ein Schlagzug, danach ist Schluss
                                    Ziehen(aktuellesBrett,i,j,i+n ,j-n,'D', !WeissAmZug); // Dame am neuen Ort
                                } else {
                                    WegLO = 0;
                                }// Aber nun ist der Weg blockiert ;-)
                                // Wir fahren fort mit Rechts Oben
                                if (WegRO == 1 && i + n <= 7 && j + n <= 7 && (aktuellesBrett.feld [i+ n][j+ n] == 32 || aktuellesBrett.feld [i+ n][j+ n] > 96)) {
                                    // der Dame kann nach links oben vorrücken!
                                    if (aktuellesBrett.feld [i+ n][j+ n] > 96) {
                                        WegRO = 0;
                                    } // Das wird ein Schlagzug, danach ist Schluss
                                    Ziehen(aktuellesBrett,i,j,i+n ,j+n,'D', !WeissAmZug); // Dame am neuen Ort
                                } else {
                                    WegRO = 0;
                                }// Aber nun ist der Weg blockiert ;-)

                                // Wir fahren fort mit Links Unten
                                if (WegLU == 1 && i - n >= 0 && j - n >= 0 && (aktuellesBrett.feld [i-n][j-n] == 32 || aktuellesBrett.feld [i-n][j-n] > 97)) {
                                    // der Dame kann nach links unten vorrücken!
                                    if (aktuellesBrett.feld [i-n][j-n] > 96) {
                                        WegLU = 0;
                                    } // Das wird ein Schlagzug, danach ist Schluss
                                    Ziehen(aktuellesBrett,i,j,i-n ,j-n,'D', !WeissAmZug); // Dame am neuen Ort
                                } else {
                                    WegLU = 0;
                                }// Aber nun ist der Weg blockiert ;-)

                                // Und zuletzt rechts unten
                                if (WegRU == 1 && i - n >= 0 && j + n <= 7 && (aktuellesBrett.feld [i-n][j+n] == 32 || aktuellesBrett.feld [i-n][j+n] > 97)) {
                                    // Dame kann nach rechts unten vorrücken!
                                    if (aktuellesBrett.feld [i-n][j+n] > 96) {
                                        WegRU = 0;
                                    } // Das wird ein Schlagzug, danach ist Schluss
                                    Ziehen(aktuellesBrett,i,j,i-n ,j+n,'D', !WeissAmZug); // Dame am neuen Ort
                                } else {
                                    WegRU = 0;
                                }// Aber nun ist der Weg blockiert ;-)
                            }
                    }
                    // Weisser König
                    if (aktuellesBrett.feld [i][j] == 'K') {
                        //Ein weisser König ist Läufer und Turm, geht aber nur einen Schritt
                            if (j - 1 >= 0 && (aktuellesBrett.feld [i][j-1] == 32 || aktuellesBrett.feld [i][j-1] > 96)) {
                                // der König kann nach links vorrücken!
                                Ziehen(aktuellesBrett,i,j,i ,j-1,'K', !WeissAmZug); // Dame am neuen Ort
                            }
                            // Wir fahren fort mit Rechts
                            if ( j + 1 <= 7 && (aktuellesBrett.feld [i][j+1] == 32 || aktuellesBrett.feld [i][j+1] > 96)) {
                                Ziehen(aktuellesBrett, i, j, i, j + 1, 'K', !WeissAmZug); // König am neuen Ort
                            }
                            // Wir fahren fort mit Unten
                            if ( i - 1 >= 0 && (aktuellesBrett.feld [i-1][j] == 32 || aktuellesBrett.feld [i-1][j] > 96)) {
                                Ziehen(aktuellesBrett, i, j, i - 1, j , 'K', !WeissAmZug); // König am neuen Ort
                            }
                            // Wir fahren fort mit Oben
                            if ( i + 1 <= 7 && (aktuellesBrett.feld [i+1][j] == 32 || aktuellesBrett.feld [i+1][j] > 96)) {
                                Ziehen(aktuellesBrett, i, j, i + 1, j , 'K', !WeissAmZug); // König am neuen Ort
                            }
                        if (j - 1 >= 0 && i + 1 <= 7 && (aktuellesBrett.feld [i+1][j-1] == 32 || aktuellesBrett.feld [i+1][j-1] > 96)) {
                            // der König kann nach links oben vorrücken!
                            Ziehen(aktuellesBrett,i,j,i+1 ,j-1,'K', !WeissAmZug); // Dame am neuen Ort
                        }
                        // Wir fahren fort mit Rechts oben
                        if ( j + 1 <= 7 && i + 1 <= 7 && (aktuellesBrett.feld [i+1][j+1] == 32 || aktuellesBrett.feld [i+1][j+1] > 96)) {
                            Ziehen(aktuellesBrett, i, j, i+1, j + 1, 'K', !WeissAmZug); // König am neuen Ort
                        }
                        // Wir fahren fort mit Unten Rechts
                        if ( j + 1 <= 7 && i - 1 >= 0 &&  (aktuellesBrett.feld [i-1][j+1] == 32 || aktuellesBrett.feld [i-1][j+1] > 96)) {
                            Ziehen(aktuellesBrett, i, j, i - 1, j +1 , 'K', !WeissAmZug); // König am neuen Ort
                        }
                        // Wir fahren fort mit Oben
                        if ( j - 1 >= 0 && i - 1 >= 0 && (aktuellesBrett.feld [i-1][j-1] == 32 || aktuellesBrett.feld [i-1][j-1] > 96)) {
                            Ziehen(aktuellesBrett, i, j, i - 1, j - 1 , 'K', !WeissAmZug); // König am neuen Ort
                        }
                    }
                }
                 else {
                 // Hier folgen alle schwarzen Züge
                    if (aktuellesBrett.feld [i][j] == 'b') {
                        //Ein Schwarzer Bauer!
                        if ( i>0 && aktuellesBrett.feld [i-1][j] == 32) {
                            // der Bauer kann vorrücken!
                            if (i > 1) {
                                Ziehen(aktuellesBrett,i,j,i-1,j,'b', !WeissAmZug); //Bauer am neuen Ort
                            }
                            else {
                                Ziehen(aktuellesBrett,i,j,i-1,j,'d', !WeissAmZug); //;-) Umwandlung zur Dame
                            }
                        }
                        //Am Anfang darf er sogar 2 gehen ;-)
                        if (i==6 && aktuellesBrett.feld [i-1][j]  == 32 && aktuellesBrett.feld [i-2][j] == 32) {
                            Ziehen(aktuellesBrett,i,j,i-2,j,'b', !WeissAmZug);
                        }
                        if (i>0 && j<7){
                            if (aktuellesBrett.feld [i-1][j+1] < 97 && aktuellesBrett.feld [i-1][j+1] > 65) {
                                // der Bauer kann nach rechts schlagen!
                                if (i > 1) {
                                    Ziehen(aktuellesBrett,i,j,i-1,j+1,'b', !WeissAmZug); //Bauer am neuen Ort
                                }
                                else {
                                    Ziehen(aktuellesBrett,i,j,i-1,j+1,'d', !WeissAmZug);//;-) Umwandlung zur Dame
                                }
                            }
                        }
                        if (i>0 && j>0){
                            if (aktuellesBrett.feld [i-1][j-1] < 97 && aktuellesBrett.feld [i-1][j-1] > 65) {
                                // der Bauer kann nach links schlagen!

                                if (i > 1) {
                                    Ziehen(aktuellesBrett,i,j,i-1,j-1,'b', !WeissAmZug); //Bauer am neuen Ort
                                }
                                else {
                                    Ziehen(aktuellesBrett,i,j,i-1,j-1,'d', !WeissAmZug);//;-) Umwandlung zur Dame
                                }

                            }
                        }
                    }
                    if (aktuellesBrett.feld [i][j] == 's') {
                        //Ein schwarzer Springer !
                        if (j >= 2) {
                            // kann ganz nach links springen
                            if (i <= 6) {
                                // kann eins nach oben springen
                                if (aktuellesBrett.feld [i+1][j-2] < 96) {
                                    Ziehen(aktuellesBrett,i,j,i+1,j-2,'s', !WeissAmZug); // Springer am neuen Ort
                                }
                            }
                            if (i > 0) {
                                // kann eins nach oben springen
                                if (aktuellesBrett.feld [i-1][j-2] <96) {
                                    Ziehen(aktuellesBrett,i,j,i-1,j-2,'s', !WeissAmZug); // Springer am neuen Ort
                                }
                            }

                        }
                        if (j <= 5) {
                            // kann ganz nach rechts springen
                            if (i <= 6) {
                                // kann eins nach oben springen
                                if (aktuellesBrett.feld [i+1][j+2] < 96) {
                                    Ziehen(aktuellesBrett,i,j,i+1,j+2,'s', !WeissAmZug); // Springer am neuen Ort
                                }
                            }
                            if (i > 0) {
                                // kann eins nach oben springen
                                if (aktuellesBrett.feld [i-1][j+2] <96) {
                                    Ziehen(aktuellesBrett,i,j,i-1,j+2,'s', !WeissAmZug); // Springer am neuen Ort
                                }
                            }

                        }
                        if (i <= 5) {
                            // kann ganz nach oben springen
                            if (j <= 6) {
                                // kann eins nach rechts springen
                                if (aktuellesBrett.feld [i+2][j+1] <96) {
                                    Ziehen(aktuellesBrett,i,j,i+2,j+1,'s', !WeissAmZug); // Springer am neuen Ort
                                }
                            }
                            if (j > 0) {
                                // kann eins nach links springen
                                if (aktuellesBrett.feld [i+2][j-1] <96) {
                                    Ziehen(aktuellesBrett,i,j,i+2,j-1,'s', !WeissAmZug); // Springer am neuen Ort
                                }
                            }

                        }
                        if (i >= 2) {
                            // kann ganz nach unten springen
                            if (j <= 6) {
                                // kann eins nach rechts springen
                                if (aktuellesBrett.feld [i-2][j+1] <96) {
                                    Ziehen(aktuellesBrett,i,j,i-2,j+1,'s', !WeissAmZug); // Springer am neuen Ort
                                }
                            }
                            if (j > 0) {
                                // kann eins nach oben springen
                                if (aktuellesBrett.feld [i-2][j-1] <96) {
                                    Ziehen(aktuellesBrett,i,j,i-2,j-1,'s', !WeissAmZug); // Springer am neuen Ort
                                }
                            }

                        }
                    }
                    // schwarzer Läufer
                    if (aktuellesBrett.feld [i][j] == 'l') {
                        //Ein schwarzer Läufer!
                        //Weg-Variablen zurücksetzen, maximal bis n=7 Schritte

                        for (int n = 1; n <= 7; n++) {
                            // Läufer hat vier Laufrichtungen, wir beginnen mit Links Oben
                            if (WegLO == 1 && i + n <= 7 && j - n >= 0 && aktuellesBrett.feld [i+n][j-n] <96) {
                                // der Läufer kann nach links oben vorrücken!

                                if (aktuellesBrett.feld [i+n][j-n] > 65) {
                                    WegLO = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett,i,j,i+n ,j-n,'l', !WeissAmZug); // Läufer am neuen Ort
                            } else {
                                WegLO = 0;
                            }// Aber nun ist der Weg blockiert ;-)
                            // Wir fahren fort mit Rechts Oben
                            if (WegRO == 1 && i + n <= 7 && j + n <= 7 && aktuellesBrett.feld [i+n][j+n] <96) {
                                // der Läufer kann nach links oben vorrücken!
                                if (aktuellesBrett.feld [i+n][j+n] > 65) {
                                    WegRO = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett,i,j,i+n ,j+n,'l', !WeissAmZug); // Läufer am neuen Ort
                            } else {
                                WegRO = 0;
                            }// Aber nun ist der Weg blockiert ;-)

                            // Wir fahren fort mit Links Unten
                            if (WegLU == 1 && i - n >= 0 && j - n >= 0 && aktuellesBrett.feld [i-n][j-n] < 96) {
                                // der Läufer kann nach links unten vorrücken!
                                if (aktuellesBrett.feld [i-n][j-n] > 65) {
                                    WegLU = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett,i,j,i-n ,j-n,'l', !WeissAmZug); // Läufer am neuen Ort
                            } else {
                                WegLU = 0;
                            }// Aber nun ist der Weg blockiert ;-)

                            // Und zuletzt rechts unten
                            if (WegRU == 1 && i - n >= 0 && j + n <= 7 && aktuellesBrett.feld [i-n][j+n] < 96) {
                                // der Läufer kann nach rechts unten vorrücken!
                                if (aktuellesBrett.feld [i-n][j+n] > 65) {
                                    WegRU = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett,i,j,i-n ,j+n,'l', !WeissAmZug); // Läufer am neuen Ort
                            } else {
                                WegRU = 0;
                            }// Aber nun ist der Weg blockiert ;-)
                        }
                    }
                    // schwarzer Turm
                    if (aktuellesBrett.feld [i][j] == 't') {
                        //Ein schwarzer Turm!
                        // maximal bis n=7 Schritt
                        for (int n = 1; n <= 7; n++) {
                            // Turm hat vier Laufrichtungen, wir beginnen mit Links
                            if (WegL == 1 && j - n >= 0 && aktuellesBrett.feld [i][j-n] < 96) {
                                // der Turm kann nach links vorrücken!
                                if (aktuellesBrett.feld [i][j-n] > 65) {
                                    WegL = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett, i, j, i, j - n, 't', !WeissAmZug); // Turm am neuen Ort
                            } else {
                                WegL = 0;
                            }// Aber nun ist der Weg blockiert ;-)
                            // Wir fahren fort mit Rechts
                            if (WegR == 1 && j + n <= 7 && aktuellesBrett.feld [i][j+n] <96) {
                                // der Turm kann rechts vorrücken!
                                if (aktuellesBrett.feld [i][j+n] > 65) {
                                    WegR = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett, i, j, i, j + n, 't', !WeissAmZug); // Läufer am neuen Ort
                            } else {
                                WegR = 0;
                            }// Aber nun ist der Weg blockiert ;-)

                            // Wir fahren fort mit Unten
                            if (WegU == 1 && i - n >= 0 && aktuellesBrett.feld [i-n][j] < 96) {
                                // der Turm kann nach unten vorrücken!
                                if (aktuellesBrett.feld [i-n][j] > 65) {
                                    WegU = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett, i, j, i - n, j, 't', !WeissAmZug); // Turm am neuen Ort
                            } else {
                                WegU = 0;
                            }// Aber nun ist der Weg blockiert ;-)

                            // Und zuletzt oben
                            if (WegO == 1 && i + n <= 7 && aktuellesBrett.feld [i+n][j] <96) {
                                // der Turm kann nach oben vorrücken!
                                if (aktuellesBrett.feld [i+n][j] > 65) {
                                    WegO = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett, i, j, i + n, j, 't', !WeissAmZug); // Turm am neuen Ort
                            } else {
                                WegO = 0;
                            }// Aber nun ist der Weg blockiert ;-)
                        }
                    }
                    // schwarze Dame
                    if (aktuellesBrett.feld [i][j] == 'd') {
                        //Ein schwarzer Turm!
                        // maximal bis n=7 Schritt
                        for (int n = 1; n <= 7; n++) {
                            // Die Dame ist Läufer und Turm wir beginnen mit dem Turm
                            if (WegL == 1 && j - n >= 0 && aktuellesBrett.feld [i][j-n] < 96) {
                                // die Dame kann nach links vorrücken!
                                if (aktuellesBrett.feld [i][j-n] > 65) {
                                    WegL = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett,i,j,i ,j-n,'d', !WeissAmZug); // Dame am neuen Ort
                            }
                            else {
                                WegL = 0;
                            }// Aber nun ist der Weg blockiert ;-)
                            // Wir fahren fort mit Rechts
                            if (WegR == 1 && j + n <= 7 && aktuellesBrett.feld [i][j+n] < 96) {
                                // die Dame kann nach rechts vorrücken!
                                if (aktuellesBrett.feld [i][j+n] > 65) {
                                    WegR = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett,i,j,i ,j+n,'d', !WeissAmZug); // Dame am neuen Ort
                            } else {
                                WegR = 0;
                            }// Aber nun ist der Weg blockiert ;-)

                            // Wir fahren fort mit Unten
                            if (WegU == 1 && i - n >= 0 && aktuellesBrett.feld [i-n][j] <96) {
                                // die Dame kann nach unten vorrücken!
                                if (aktuellesBrett.feld [i-n][j] > 65) {
                                    WegU = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett,i,j,i-n ,j,'d', !WeissAmZug); // Dame am neuen Ort
                            } else {
                                WegU = 0;
                            }// Aber nun ist der Weg blockiert ;-)

                            // Und zuletzt oben
                            if (WegO == 1 && i + n <= 7 && aktuellesBrett.feld [i+n][j] <96) {
                                // die Dame kann nach oben vorrücken!
                                if (aktuellesBrett.feld [i+n][j] > 65) {
                                    WegO = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett,i,j,i+n ,j,'d', !WeissAmZug); // Dame am neuen Ort
                            } else {
                                WegO = 0;
                            }// Aber nun ist der Weg blockiert ;-)

                            // Nun kommen die Damen-Läuferzüge. Vier Laufrichtungen, wir beginnen mit Links Oben
                            if (WegLO == 1 && i + n <= 7 && j - n >= 0 && aktuellesBrett.feld [i+n][j-n] <96) {
                                // der Dame kann nach links oben vorrücken!

                                if (aktuellesBrett.feld [i+n][j-n] > 65) {
                                    WegLO = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett,i,j,i+n ,j-n,'d', !WeissAmZug); // Dame am neuen Ort
                            } else {
                                WegLO = 0;
                            }// Aber nun ist der Weg blockiert ;-)
                            // Wir fahren fort mit Rechts Oben
                            if (WegRO == 1 && i + n <= 7 && j + n <= 7 && aktuellesBrett.feld [i+n][j+n] <96) {
                                // der Dame kann nach links oben vorrücken!
                                if (aktuellesBrett.feld [i+n][j+n] > 65) {
                                    WegRO = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett,i,j,i+n ,j+n,'d', !WeissAmZug); // Dame am neuen Ort
                            } else {
                                WegRO = 0;
                            }// Aber nun ist der Weg blockiert ;-)

                            // Wir fahren fort mit Links Unten
                            if (WegLU == 1 && i - n >= 0 && j - n >= 0 && aktuellesBrett.feld [i-n][j-n] <96) {
                                // der Dame kann nach links unten vorrücken!
                                if (aktuellesBrett.feld [i-n][j-n] > 65) {
                                    WegLU = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett,i,j,i-n ,j-n,'d', !WeissAmZug); // Dame am neuen Ort
                            } else {
                                WegLU = 0;
                            }// Aber nun ist der Weg blockiert ;-)

                            // Und zuletzt rechts unten
                            if (WegRU == 1 && i - n >= 0 && j + n <= 7 && aktuellesBrett.feld [i-n][j+n] <96) {
                                // Dame kann nach rechts unten vorrücken!
                                if (aktuellesBrett.feld [i-n][j+n] > 65) {
                                    WegRU = 0;
                                } // Das wird ein Schlagzug, danach ist Schluss
                                Ziehen(aktuellesBrett,i,j,i-n ,j+n,'d', !WeissAmZug); // Dame am neuen Ort
                            } else {
                                WegRU = 0;
                            }// Aber nun ist der Weg blockiert ;-)
                        }
                    }
                    // schwarzer König
                    if (aktuellesBrett.feld [i][j]== 'k') {
                        //Ein schwarzer König ist Läufer und Turm, geht aber nur einen Schritt
                        if (j - 1 >= 0 && aktuellesBrett.feld [i][j-1] < 96)  {
                            // der König kann nach links vorrücken!
                            Ziehen(aktuellesBrett, i, j, i, j - 1, 'k', !WeissAmZug); // Dame am neuen Ort
                        }
                        // Wir fahren fort mit Rechts
                        if (j + 1 <= 7 && aktuellesBrett.feld [i][j+1] < 96 ) {
                            Ziehen(aktuellesBrett, i, j, i, j + 1, 'k', !WeissAmZug); // König am neuen Ort
                        }
                        // Wir fahren fort mit Unten
                        if (i - 1 >= 0 && aktuellesBrett.feld [i-1][j] < 96) {
                            Ziehen(aktuellesBrett, i, j, i - 1, j, 'k', !WeissAmZug); // König am neuen Ort
                        }
                        // Wir fahren fort mit Oben
                        if (i + 1 <= 7 && aktuellesBrett.feld [i+1][j] < 96) {
                            Ziehen(aktuellesBrett, i, j, i + 1, j, 'k', !WeissAmZug); // König am neuen Ort
                        }
                        if (j - 1 >= 0 && i + 1 <= 7 && aktuellesBrett.feld [i+1][j-1] <96) {
                            // der König kann nach links oben vorrücken!
                            Ziehen(aktuellesBrett, i, j, i + 1, j - 1, 'k', !WeissAmZug); // Dame am neuen Ort
                        }
                        // Wir fahren fort mit Rechts oben
                        if (j + 1 <= 7 && i + 1 <= 7 && aktuellesBrett.feld [i+1][j+1] < 96) {
                            Ziehen(aktuellesBrett, i, j, i + 1, j + 1, 'k', !WeissAmZug); // König am neuen Ort
                        }
                        // Wir fahren fort mit Unten Rechts
                        if (j + 1 <= 7 && i - 1 >= 0 && aktuellesBrett.feld [i-1][j+1] < 96) {
                            Ziehen(aktuellesBrett, i, j, i - 1, j + 1, 'k', !WeissAmZug); // König am neuen Ort
                        }
                        // Wir fahren fort mit Oben
                        if (j - 1 >= 0 && i - 1 >= 0 && aktuellesBrett.feld [i-1][j-1] < 96) {
                            Ziehen(aktuellesBrett, i, j, i - 1, j - 1, 'k', !WeissAmZug); // König am neuen Ort
                        }

                    }
                 }
            }
       // Rochaden
       if (WeissAmZug) {
           if (aktuellesBrett.RochadeWeiss == 0 && aktuellesBrett.feld[0][4] == 'K' && Position > 0) { // die Rochade ist im Prinzip möglich
               if ((aktuellesBrett.feld[0][7] == 'T') && (aktuellesBrett.feld[0][5] == 32) && (aktuellesBrett.feld[0][6] == 32)) { // kleine Rochade
                   Ziehen(aktuellesBrett, 0, 4, 0, 6, 'K', !WeissAmZug); // König am neuen Ort, ein 2er Schritt
                   NeuePosition[Position - 1].feld[0][7]= ' '; //Alten Turm löschen
                   NeuePosition[Position - 1].feld[0][5]= 'T'; //Turm links vom König
                   NeuePosition[Position - 1].RochadeWeiss = 1; // Rochade getan
               }
               if ((aktuellesBrett.feld[0][0] == 'T') && (aktuellesBrett.feld[0][1] == 32) && (aktuellesBrett.feld[0][2] == 32)&& (aktuellesBrett.feld[0][3] == 32)) { // grosse Rochade
                   Ziehen(aktuellesBrett, 0, 4, 0, 2, 'K', !WeissAmZug); // König am neuen Ort, ein 2er Schritt
                   NeuePosition[Position - 1].feld[0][0]= ' '; //Alten Turm löschen
                   NeuePosition[Position - 1].feld[0][3]= 'T'; //Turm rechts vom König
                   NeuePosition[Position - 1].RochadeWeiss = 1; // Rochade getan
               }
           }
       }
       else{
           if (aktuellesBrett.RochadeSchwarz == 0 && aktuellesBrett.feld[7][4] == 'k' && Position > 0) { // die Rochade ist im Prinzip möglich
               if ((aktuellesBrett.feld[7][7] == 't') && (aktuellesBrett.feld[7][5] == 32) && (aktuellesBrett.feld[7][6] == 32)) { // kleine Rochade
                   Ziehen(aktuellesBrett, 7, 4, 7, 6, 'k', !WeissAmZug); // König am neuen Ort, ein 2er Schritt
                   NeuePosition[Position - 1].feld[7][7]= ' '; //Alten Turm löschen
                   NeuePosition[Position - 1].feld[7][5]= 't'; //Turm links vom König
                   NeuePosition[Position - 1].RochadeSchwarz = 1; // Rochade getan
               }
               if ((aktuellesBrett.feld[7][0] == 't') && (aktuellesBrett.feld[7][3] == 32) && (aktuellesBrett.feld[7][2] == 32) && (aktuellesBrett.feld[7][1] == 32)) { // grosse Rochade
                   Ziehen(aktuellesBrett, 7, 4, 7, 2, 'k', !WeissAmZug); // König am neuen Ort, ein 2er Schritt
                   NeuePosition[Position - 1].feld[7][0]= ' '; //Alten Turm löschen
                   NeuePosition[Position - 1].feld[7][3]= 't'; //Turm links vom König
                   NeuePosition[Position - 1].RochadeSchwarz = 1; // Rochade getan
               }
           }

       }
    return GueltigeStellungen.this;
    }
    int length (){ return Position;}
}
