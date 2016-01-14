package de.oth.hsp.hpplan.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.oth.hsp.common.ilog.exception.NotSolvableException;
import de.oth.hsp.hpplan.ilog.HPPlanStatischRequest;
import de.oth.hsp.hpplan.ilog.HPPlanStatischResponse;
import de.oth.hsp.hpplan.ilog.HPPlanStatischSolvingAlgorithm;
import de.oth.hsp.hpplan.ilog.Product;
import de.oth.hsp.hpplan.ilog.Productionsegment;

public class TestHPPlanILOGAnbindung {

    public static void main(String[] args) {
        // Fall 1: Verwendung der .dat-Datei
        computeHPPlanWithDATFile();

        // Fall 2: Verwenden der Daten aus Eingabe
        computeHPPlanWithParameters();

    }

    public static void computeHPPlanWithDATFile() {

        try {
            HPPlanStatischSolvingAlgorithm alg = new HPPlanStatischSolvingAlgorithm();
            HPPlanStatischResponse response = alg.solve("HPPLAN-Statisch", "src/resources/");
            alg.printResult();

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Es ist ein Fehler aufgetreten!");
        }
    }

    public static void computeHPPlanWithParameters() {
        float epgap = 0.0001f;
        int T = 12;
        int ZMax = 2;

        int[][] b = {
                { 1209600, 1209600, 1209600, 1209600, 1209600, 1209600, 1209600, 1209600, 1209600, 1209600, 1209600,
                        1209600 },
                { 806400, 806400, 806400, 806400, 806400, 806400, 806400, 806400, 806400, 806400, 806400, 806400 },
                { 1209600, 1209600, 1209600, 1209600, 1209600, 1209600, 1209600, 1209600, 1209600, 1209600, 1209600,
                        1209600 },
                { 403200, 403200, 403200, 403200, 403200, 403200, 403200, 403200, 403200, 403200, 403200, 403200 } };
        float[][][] f = {
                { { 402f, 7944f, 450f }, { 402f, 7194f, 360f }, { 312f, 6564f, 360f }, { 450f, 8280f, 450f },
                        { 360f, 6900f, 360f } },
                { { 8100f, 600f, 0f }, { 7560f, 540f, 0f }, { 6360f, 540f, 0f }, { 9300f, 600f, 0f },
                        { 8160f, 540f, 0f } },
                { { 888f, 12426f, 630f }, { 888f, 11544f, 504f }, { 777f, 10767f, 504f }, { 888f, 12426f, 630f },
                        { 777f, 10767f, 504f } },
                { { 120f, 3990f, 450f }, { 120f, 3360f, 360f }, { 105f, 3255f, 360f }, { 120f, 3990f, 450f },
                        { 105f, 3255f, 360f } } };

        int[][] d = { { 0, 0, 0, 0, 0, 0, 0, 11, 31, 33, 30, 0 }, { 0, 0, 0, 0, 0, 0, 0, 12, 37, 39, 25, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 10, 41, 41, 29, 0 }, { 0, 0, 0, 0, 0, 0, 0, 16, 32, 34, 27, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 15, 44, 38, 29, 0 } };
        float[] h = { 42f, 40f, 35f, 50f, 48f };
        int[][] Umax = {
                { 604800, 604800, 604800, 604800, 604800, 604800, 604800, 604800, 604800, 604800, 604800, 604800 },
                { 403200, 403200, 403200, 403200, 403200, 403200, 403200, 403200, 403200, 403200, 403200, 403200 },
                { 604800, 604800, 604800, 604800, 604800, 604800, 604800, 604800, 604800, 604800, 604800, 604800 },
                { 201600, 201600, 201600, 201600, 201600, 201600, 201600, 201600, 201600, 201600, 201600, 201600 } };
        float[][] u = { { 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f },
                { 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f },
                { 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f },
                { 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f } };
        int[] Iinit = { 0, 80, 40, 5, 70 };

        List<Product> products = new ArrayList<>();
        for (int numberOfProd = 0; numberOfProd < 5; numberOfProd++) {
            Map<Integer, Integer> demand = new HashMap<>();
            for (int i = 0; i < d[numberOfProd].length; i++) {
                demand.put(i, d[numberOfProd][i]);
            }
            Product product = new Product(demand, h[numberOfProd], Iinit[numberOfProd]);
            products.add(product);
        }

        List<Productionsegment> segments = new ArrayList<>();
        for (int numberOfSeg = 0; numberOfSeg < 4; numberOfSeg++) {
            Productionsegment segment = new Productionsegment(b[numberOfSeg], Umax[numberOfSeg], u[numberOfSeg]);
            segments.add(segment);
        }

        HPPlanStatischSolvingAlgorithm alg = new HPPlanStatischSolvingAlgorithm();
        HPPlanStatischRequest request = new HPPlanStatischRequest(epgap, products, segments, T, ZMax, f);
        try {
            HPPlanStatischResponse response = alg.solve(request);
            alg.printResult();
        } catch (NotSolvableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
