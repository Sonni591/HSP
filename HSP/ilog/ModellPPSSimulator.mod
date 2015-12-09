int J = ...;
range ProdSegs = 1..J;

int K = ...;
range Products = 1..K;

int P = ...;
range Types = 1..P;

int F = ...;
int TStart = ...;
int TEnd = ...;

int ZMax = ...;
int ZMin = ...;
range Vorlauf = ZMin..ZMax;

range TimePlan = TStart..TEnd;
range TimeFrozen = (TStart-F)..(TStart-1);
range TimeTotal = (TStart-F)..TEnd;
range TimeInventory = (TStart-F-1)..TEnd;
range TimeCR = (TStart-F)..(TEnd-ZMax);

int b[ProdSegs] [TimeTotal] =...;
float f[ProdSegs][Products][Vorlauf] =...;
int d[Products] [TimeTotal] =...;
float h[Products] =...;
int Umax[ProdSegs][TimePlan] =...;
float Uagg =...;
float u[ProdSegs] [TimePlan] =...;
int Iinit[Products] =...;
int xinit[Products][TimeFrozen] = ...;
float xagg[Types] = ...;
float Uinit[ProdSegs][TimeFrozen] = ...;
int n[Types][Products] = ...;

int cM = 999999999;

// Entscheidungsvariablen
dvar float U[ProdSegs][TimeTotal];
dvar float Udelta[ProdSegs][TimeTotal];
dvar float x[Products][TimeTotal];
dvar float I[Products][TimeInventory];

// Zielfunktion:
minimize 
   sum(t in TimePlan) 
      (
         sum(k in Products) 
            (h[k] * I[k][t])
      ) 
   + sum(t in TimePlan) 
      (
         sum(j in ProdSegs) 
            (u[j][t] * U[j][t])
      )
    + sum(t in TimeTotal)
      (
         sum(j in ProdSegs)
           (cM * Udelta[j][t])
      );

// Restriktionen:
constraints {
   forall (k in Products, t in TimeTotal)
      Lagerbilanzgleichung:
        x[k][t] + I[k][t-1] - I[k][t] == d[k][t];
        
   forall (j in ProdSegs, t in TimeCR) 
      Kapazitaetsrestriktion:
        sum(k in Products)(sum(z in Vorlauf)(f[j][k][z] * x[k][t+z])) - U[j][t] - Udelta[j][t] <= b[j][t];
        
   forall (j in ProdSegs, t in TimePlan)
      BegrenzungderZusatzkapazitaet: 
      	U[j][t] <= Umax[j][t];
      	
   forall (j in ProdSegs, t in TimePlan)
      NichtnegativitaetsbedingungzurZusatzkapazitaet:
      	U[j][t] >= 0;
     
   forall (j in ProdSegs, t in TimeTotal)
      NichtnegativitaetsbedingungzurRelaxationsvariablenzurZusatzkapazitaet:
      	Udelta[j][t] >= 0;

   forall (k in Products, t in TimePlan) 
      NichtnegativitaetsbedingungderProduktionsmengen:
        x[k][t] >= 0;
        
   forall (k in Products, t in TimePlan) 
      NichtnegativitaetsbedingungderBestaende:
        I[k][t] >= 0;
        
   forall (k in Products) 
      UebergabederAnfangslagerbestaende:
        I[k][TStart-F-1] == Iinit[k];
        
   forall (k in Products, t in TimeFrozen)
      UebergabedergefrorenenProduktionsmengen:
        x[k][t] == xinit[k][t];
        
   forall (j in ProdSegs, t in TimeFrozen)
     UebergabedergefrorenenZusatzkapazitaeten:
       U[j][t] == Uinit[j][t];
     
   forall (p in Types)
     FestsetzungderMindestproduktionsmengen:
       sum(k in Products, t in TimeTotal)(n[p][k]*x[k][t]) >= xagg[p];
   
   FestsetzungderHoechstzusatzkapazitaet:
     sum(j in ProdSegs, t in TimeTotal)(U[j][t]) <= Uagg + sum(j in ProdSegs, t in TimeTotal)(Udelta[j][t]);
};

main{
  var def = thisOplModel.modelDefinition;   
  var cplex = new IloCplex();
  cplex.baralg = 1;
  var opl = new IloOplModel(def, cplex);
  var data = new IloOplDataSource("Parameter.dat");
  opl.addDataSource(data);
  opl.generate();
  var ofile = new IloOplOutputFile("ILOG_Lösung.txt");
  if (cplex.solve()) {
	  var t;
	  var k;
	  var j;
	  //Schreibe Produktionsmengen
	  ofile.write('Name');
	  ofile.write('\t');
	  for (t=opl.TStart; t <= opl.TEnd; t++)
	  {
	    ofile.write(t);
	    ofile.write('\t');
	  }
	  ofile.writeln();
	  for (k=1; k <= opl.K; k++)
	  {
	    ofile.write(k);
	    ofile.write('\t');
	    
	  	for (t=opl.TStart; t <= opl.TEnd; t++)
	  	{
	  	  ofile.write(Math.round(opl.x[k][t]));
	  	  ofile.write('\t');
	  	}
	  	ofile.writeln();
	  }
	  
	  //Schreibe Zusatzkapazitäten
	  //Kopfzeile
	  ofile.write('0');
	  ofile.write('\t');
	  for (t=opl.TStart; t <= opl.TEnd; t++)
	  {
	    ofile.write(t);
	    ofile.write('\t');
	  }
	  ofile.writeln();
	  //Zusatzkapazitäten
	  for (j=1; j <= opl.J; j++)
	  {
	    ofile.write(j);
	    ofile.write('\t');
		  
		for (t=opl.TStart; t <= opl.TEnd; t++)
		{
		  ofile.write(Math.round(opl.U[j][t]));
		  ofile.write('\t');
		}  	  
		ofile.writeln();
	  }
	  
	  //Schreibe Strafkapazitäten
	  //Kopfzeile
	  ofile.write('0');
	  ofile.write('\t');
	  for (t=opl.TStart-opl.F; t <= opl.TEnd; t++)
	  {
	    ofile.write(t);
	    ofile.write('\t');
	  }
	  ofile.writeln();
	  
	  //Strafkapazitäten
	  for (j=1; j <= opl.J; j++)
	  {
	    ofile.write(j)
	    ofile.write('\t');
		  
		for (t=opl.TStart-opl.F; t <= opl.TEnd; t++)
		{
		  ofile.write(Math.round(opl.Udelta[j][t]));
		  ofile.write('\t');
		}  	  
		ofile.writeln();
	  }
	}else{
	  ofile.write('Keine Loesung ermittelt');
	}    
  opl.end();
  cplex.end();
}
