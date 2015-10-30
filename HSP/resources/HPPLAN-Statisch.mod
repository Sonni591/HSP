int J = ...;
range ProdSegs = 1..J;

int K = ...;
range Products = 1..K;

int T = ...;

int ZMax = ...;
range Vorlauf = 0..ZMax;

range TimePlan = 1..T;
range TimeInventory = 0..T;
range TimeCR = 1..(T-ZMax);

int b[ProdSegs] [TimePlan] =...;
float f[ProdSegs][Products][Vorlauf] =...;
int d[Products] [TimePlan] =...;
float h[Products] =...;
int Umax[ProdSegs][TimePlan] =...;
float u[ProdSegs] [TimePlan] =...;
int Iinit[Products] =...;

// Entscheidungsvariablen
dvar float+ U[ProdSegs][TimePlan];
dvar float+ x[Products][TimePlan];
dvar float+ I[Products][TimeInventory];

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
      );

// Restriktionen:
constraints {
   forall (k in Products, t in TimePlan)
      Lagerbilanzgleichung:
        x[k][t] + I[k][t-1] - I[k][t] == d[k][t];
        
   forall (j in ProdSegs, t in TimeCR) 
      Kapazitaetsrestriktion:
        sum(k in Products)(sum(z in Vorlauf)(f[j][k][z] * x[k][t+z])) - U[j][t] <= b[j][t];
        
   forall (j in ProdSegs, t in TimePlan)
      BegrenzungderZusatzkapazitaet: 
      	U[j][t] <= Umax[j][t];
        
   forall (k in Products) 
      UebergabederAnfangslagerbestaende:
        I[k][0] == Iinit[k];
};