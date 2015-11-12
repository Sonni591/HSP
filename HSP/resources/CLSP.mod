// Parameter, Teil 1:
int T = ...;                                  // Planungszeitraum.
int K = ...;                                  // Produkte.
int J = ...;                                  // Ressource.
int M = ...;                                  // Grosse Zahl.   

// Wertebereiche:
range Produkt = 1..K;
range Ressource = 1..J;
range Planungszeitraum = 1..T;
range PlanungszeitraumNull = 0..T;

// Variablen:
dvar float+ q[Produkt][Planungszeitraum];     // Losgroessen. 
dvar float+ y[Produkt][PlanungszeitraumNull]; // Lagerbestaende.
dvar boolean r[Produkt][Planungszeitraum];    // Ruestvariablen. 

// Parameter, Teil 2:
float b[Ressource][Planungszeitraum] = ...;   // Kapazitaeten. 
int	d[Produkt][Planungszeitraum] = ...;       // Nettobedarfe.
float h[Produkt] = ...;                       // Lagerkostensaetze.
float s[Produkt] = ...;                       // Ruestkostensaetze.
float tb[Produkt][Ressource] = ...;           // Stueckbearbeitungszeiten.
float tr[Produkt][Ressource] = ...;           // Ruestzeiten. 
int z[Produkt] = ...;                         // Mindestvorlaufzeiten.
int y0[Produkt] = ...;                        // Anfangslagerbestaende.

// Minimierung der Gesamtkosten
minimize                          
   sum (k in Produkt, t in Planungszeitraum) 
		(s[k]*r[k][t] + h[k]*y[k][t]);
   
constraints {
	// Lagerbilanzgleichungen:
	forall(k in Produkt){					  
		forall(t in 1..(1+z[k]-1)){
			y[k][t-1] - d[k][t] == y[k][t];
		}
		forall(t in (1+z[k])..T){
			y[k][t-1] + q[k][t-z[k]] - d[k][t] == y[k][t];
		}
	}
	// Kapazitaetsbedingungen:
	forall(j in Ressource, t in Planungszeitraum){	
		sum(k in Ressource)(tr[k][j]*r[k][t] + tb[k][j]*q[k][t]) <= b[j][t];
	}
	// Ruestbedingungen:
	forall(k in Produkt, t in Planungszeitraum){	
		q[k][t] - M*r[k][t] <= 0;
	}
	// Lageranfangs- und Lagerendbestaende:
	forall(k in Produkt){							
		y[k][0] == y0[k];	
	}
};