/*********************************************
 * OPL 12.4 Model
 * Author: Frederick
 * Creation Date: 31.10.2013 at 14:17:30
 *********************************************/ 

// PARAMETER:

float CPLEX_EPGAP = ...;				// EPGAP (relative Optimalit�tsl�cke)

int T = ...;							// Planungszeitraum
range Time = 1..T;						// Periodenindex

int K = ...;							// Anzahl der Produkte
range Product = 1..K;					// Produktindex

int d[Product][Time] = ...;				// Bedarfe je Periode
float h[Product] = ...;					// Lagerkostensatz je Produkt
float s[Product] = ...;					// R�stkostensatz je Produkt
float tb[Product] = ...;				// St�ckbearbeitungszeit je Produkt
float tr[Product] = ...;				// R�stzeit je Periode
int C = ...;							// verf�gbare Kapazit�t (Aggregiert)
float E[Time] = ...;					// Notwendige Zusatzkapazit�t;
float p[Product] = ...;					// Kosten f�r Backorders je Produkt;
int qMax[Product] = ...;				// Maximale Losgr��e je Produkt;
int B0[Product] = ...;					// Initiale Auftragsr�ckst�nde;
int qIn[Product][Time] = ...;			// Fertige Lose aus fr�heren Planungsl�ufen;
int y0[Product] = ...;					// Anfangslagerbestand;

// ENTSCHEIDUNGSVARIABLEN:
dvar int+ B[Product][0..T];				// Auftragsr�ckst�nde;
dvar int+ q[Product][Time];				// Lose je Periode
dvar int+ y[Product][0..T];				// Lagerbestand am Ende der Periode
dvar int+ V[Product][Time] in 0..1;		// Bin�re Entscheidungsvariable

execute CPX_PARAM {
  cplex.epgap = CPLEX_EPGAP;      
  cplex.tilim = 100;
}
 
// ZIELFUNKTION:
  
  minimize
  sum(t in Time)(sum (k in Product)( (s[k]*V[k][t]+y[k][t]*h[k] + B[k][t]*p[k])));

// RESTRIKTIONEN:
  subject to {
    		
// Kapazit�tsrestriktion:
	forall (t in Time)
	Kapazitaetsrestriktion:
	sum(k in Product)(tb[k]*q[k][t] + tr[k]*V[k][t]) + E[t] <= C;
	  			
// Maximale Losgr��e:
	forall (t in Time, k in Product)
	Losgrenze:
	q[k][t] <= qMax[k];
	 
// Lagerbilanzgleichung:
    forall (t in Time, k in Product)
    Lagerbilanzgleichung:
    y[k][t] == y[k][t-1] - B[k][t-1] + q[k][t] + qIn[k][t] - d[k][t] + B[k][t];

// R�stbedingung:
	forall (t in Time, k in Product)
	Ruestbedingung:
	q[k][t] - sum(n in Time)(d[k][n] + B0[K]) *V[k][t] <= 0;
	    
// Lageranfangsbestand:
	forall (k in Product)
	y[k][0] == y0[k];
	      
// Backorders zu Beginn
	forall (k in Product)	  
	B[k][0] == B0[k];
	  		
// Lagerendbestand:
	forall (k in Product)
	Lagerendbestand:
	y[k][T] ==0;
	  
// Nicht-Negativit�t:
	forall (t in Time, k in Product)
	Nicht_NEgativitaet_I:
	q[k][t] >= 0;    

      	forall (t in Time, k in Product)
        	Nicht_NEgativitaet_II:
	y[k][t] >= 0;
	
	forall (t in Time, k in Product)
        	Nicht_NEgativitaet_III:
	B[k][t] >= 0;  	
  };