package de.oth.hsp.clsp.model;

public class CLSPModel {

    public CLSPModel() {

    }

    private int K;
    private int T;
    private int J;
    private int M;
    private Number[][] d;
    private Number[][] h;
    private Number[][] s;
    private Number[][] tb;
    private Number[][] tr;
    private Number[][] b;

    public Number[][] createdTestMatrixD() {
        d = new Number[T][K];
        int t = 0;
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < K; j++) {
                d[i][j] = t;
                t++;
            }
        }
        return d;
    }

    public Number[][] createdTestMatrixH() {
        h = new Number[1][K];
        int t = 0;
        for (int j = 0; j < K; j++) {
            h[1][j] = t;
            t++;
        }
        return h;
    }

    public Number[][] createdTestMatrixS() {
        s = new Number[1][K];
        int t = 0;
        for (int j = 0; j < K; j++) {
            s[1][j] = t;
            t++;
        }
        return s;
    }

    public Number[][] createdTestMatrixTB() {
        tb = new Number[K][J];
        int t = 0;
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < J; j++) {
                tb[i][j] = t;
                t++;
            }
        }
        return tb;
    }

    public Number[][] createdTestMatrixTR() {
        tr = new Number[K][J];
        int t = 0;
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < J; j++) {
                tr[i][j] = t;
                t++;
            }
        }
        return tr;
    }

    public Number[][] createdTestMatrixB() {
        b = new Number[J][T];
        int t = 0;
        for (int i = 0; i < J; i++) {
            for (int j = 0; j < T; j++) {
                b[i][j] = t;
                t++;
            }
        }
        return b;
    }

    /**
     * @param k
     *            the k to set
     */
    public void setK(int k) {
        K = k;
    }

    /**
     * @param t
     *            the t to set
     */
    public void setT(int t) {
        T = t;
    }

    /**
     * @return the j
     */
    public int getJ() {
        return J;
    }

    /**
     * @param j
     *            the j to set
     */
    public void setJ(int j) {
        J = j;
    }

    /**
     * @return the m
     */
    public int getM() {
        return M;
    }

    /**
     * @param m
     *            the m to set
     */
    public void setM(int m) {
        M = m;
    }

    /**
     * @return the d
     */
    public Number[][] getD() {
        return d;
    }

    /**
     * @param d
     *            the d to set
     */
    public void setD(Number[][] d) {
        this.d = d;
    }

    /**
     * @return the h
     */
    public Number[][] getH() {
        return h;
    }

    /**
     * @param h
     *            the h to set
     */
    public void setH(Number[][] h) {
        this.h = h;
    }

    /**
     * @return the s
     */
    public Number[][] getS() {
        return s;
    }

    /**
     * @param s
     *            the s to set
     */
    public void setS(Number[][] s) {
        this.s = s;
    }

    /**
     * @return the tb
     */
    public Number[][] getTb() {
        return tb;
    }

    /**
     * @param tb
     *            the tb to set
     */
    public void setTb(Number[][] tb) {
        this.tb = tb;
    }

    /**
     * @return the tr
     */
    public Number[][] getTr() {
        return tr;
    }

    /**
     * @param tr
     *            the tr to set
     */
    public void setTr(Number[][] tr) {
        this.tr = tr;
    }

    /**
     * @return the b
     */
    public Number[][] getB() {
        return b;
    }

    /**
     * @param b
     *            the b to set
     */
    public void setB(Number[][] b) {
        this.b = b;
    }

    /**
     * @return the k
     */
    public int getK() {
        return K;
    }

    /**
     * @return the t
     */
    public int getT() {
        return T;
    }

}
