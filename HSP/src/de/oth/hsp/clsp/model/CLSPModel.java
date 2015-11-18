package de.oth.hsp.clsp.model;

public class CLSPModel {

    public CLSPModel() {

    }

    private int K;
    private int T;
    private String[][] d;

    public String[][] createdMatrix() {
        d = new String[T][K];
        int t = 0;
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < K; j++) {
                d[i][j] = String.valueOf(t);
                t++;
            }
        }
        return d;
    }

    /**
     * @return the d
     */
    public String[][] getD() {
        return d;
    }

    /**
     * @return the k
     */
    public int getK() {
        return K;
    }

    /**
     * @param k
     *            the k to set
     */
    public void setK(int k) {
        K = k;
    }

    /**
     * @return the t
     */
    public int getT() {
        return T;
    }

    /**
     * @param t
     *            the t to set
     */
    public void setT(int t) {
        T = t;
    }

}
