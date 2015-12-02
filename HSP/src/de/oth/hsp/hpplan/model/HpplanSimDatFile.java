package de.oth.hsp.hpplan.model;

import de.oth.hsp.common.dat.AbstractDatFile;
import de.oth.hsp.common.dat.DatEntry;
import de.oth.hsp.common.dat.EntryDesc;
import de.oth.hsp.common.dat.desc.ContentType;
import de.oth.hsp.common.dat.desc.NumericalType;
import de.oth.hsp.common.dat.value.ArrayContent;
import de.oth.hsp.common.dat.value.SingleContent;
import de.oth.hsp.common.dat.value.ThreeDimFieldContent;
import de.oth.hsp.common.dat.value.TwoDimFieldContent;

/**
 * Describes a a <i>dat</i> file for HPPLAN-Sim problem solving.
 * 
 * @author Thomas Butz
 *
 */
public class HpplanSimDatFile extends AbstractDatFile {
    private static final String MOD_PATH = "/resources/HPPLAN-PPS.mod";

    @EntryDesc(position = 0, name = "J", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> j;

    @EntryDesc(position = 1, name = "K", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> k;

    @EntryDesc(position = 2, name = "F", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> fBig;

    @EntryDesc(position = 3, name = "TStart", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> tStart;

    @EntryDesc(position = 4, name = "TEnd", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> tEnd;

    @EntryDesc(position = 5, name = "b", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.INTEGER)
    private DatEntry<TwoDimFieldContent> b;

    @EntryDesc(position = 6, name = "Umax", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.INTEGER)
    private DatEntry<TwoDimFieldContent> uMax;

    @EntryDesc(position = 7, name = "u", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.FLOAT)
    private DatEntry<TwoDimFieldContent> u;

    @EntryDesc(position = 8, name = "ZMax", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> zMax;

    @EntryDesc(position = 9, name = "ZMin", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> zMin;

    @EntryDesc(position = 10, name = "f", conType = ContentType.THREE_DIM_FIELD, numType = NumericalType.FLOAT)
    private DatEntry<ThreeDimFieldContent> f;

    @EntryDesc(position = 11, name = "d", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.INTEGER)
    private DatEntry<TwoDimFieldContent> d;

    @EntryDesc(position = 12, name = "h", conType = ContentType.ARRAY, numType = NumericalType.INTEGER)
    private DatEntry<ArrayContent> h;

    @EntryDesc(position = 13, name = "Iinit", conType = ContentType.ARRAY, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> iInit;

    @EntryDesc(position = 14, name = "xinit", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.INTEGER)
    private DatEntry<TwoDimFieldContent> xInit;

    @EntryDesc(position = 15, name = "Uinit", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.INTEGER)
    private DatEntry<TwoDimFieldContent> uInit;

    @EntryDesc(position = 16, name = "P", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> p;

    @EntryDesc(position = 17, name = "n", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.INTEGER)
    private DatEntry<TwoDimFieldContent> n;

    @EntryDesc(position = 18, name = "xagg", conType = ContentType.ARRAY, numType = NumericalType.INTEGER)
    private DatEntry<ArrayContent> xagg;

    @EntryDesc(position = 19, name = "Uagg", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> uagg;

    @Override
    protected String getModResourcePath() {
        return MOD_PATH;
    }

    public int getJ() {
        return j.getContent().getIntValue();
    }

    public void setJ(int j) {
        this.j.getContent().setValue(j);
    }

    public int getK() {
        return k.getContent().getIntValue();
    }

    public void setK(int k) {
        this.k.getContent().setValue(k);
    }

    public int getfBig() {
        return fBig.getContent().getIntValue();
    }

    public void setfBig(int fBig) {
        this.fBig.getContent().setValue(fBig);
    }

    public int gettStart() {
        return tStart.getContent().getIntValue();
    }

    public void settStart(int tStart) {
        this.tStart.getContent().setValue(tStart);
    }

    public int gettEnd() {
        return tEnd.getContent().getIntValue();
    }

    public void settEnd(int tEnd) {
        this.tEnd.getContent().setValue(tEnd);
    }

    public int[][] getB() {
        return b.getContent().getIntValues();
    }

    public void setB(int[][] b) {
        this.b.getContent().setValues(b);
    }

    public int[][] getuMax() {
        return uMax.getContent().getIntValues();
    }

    public void setuMax(int[][] uMax) {
        this.uMax.getContent().setValues(uMax);
    }

    public double[][] getU() {
        return u.getContent().getDoubleValues();
    }

    public void setU(double[][] u) {
        this.u.getContent().setValues(u);
    }

    public int getzMax() {
        return zMax.getContent().getIntValue();
    }

    public void setzMax(int zMax) {
        this.zMax.getContent().setValue(zMax);
    }

    public int getzMin() {
        return zMin.getContent().getIntValue();
    }

    public void setzMin(int zMin) {
        this.zMin.getContent().setValue(zMin);
    }

    public double[][][] getF() {
        return f.getContent().getDoubleValues();
    }

    public void setF(double[][][] f) {
        this.f.getContent().setValues(f);
    }

    public int[][] getD() {
        return d.getContent().getIntValues();
    }

    public void setD(int[][] d) {
        this.d.getContent().setValues(d);
    }

    public int[] getH() {
        return h.getContent().getIntValues();
    }

    public void setH(int[] h) {
        this.h.getContent().setValues(h);
    }

    public int getiInit() {
        return iInit.getContent().getIntValue();
    }

    public void setiInit(int iInit) {
        this.iInit.getContent().setValue(iInit);
    }

    public int[][] getxInit() {
        return xInit.getContent().getIntValues();
    }

    public void setxInit(int[][] xInit) {
        this.xInit.getContent().setValues(xInit);
    }

    public int[][] getuInit() {
        return uInit.getContent().getIntValues();
    }

    public void setuInit(int[][] uInit) {
        this.uInit.getContent().setValues(uInit);
    }

    public int getP() {
        return p.getContent().getIntValue();
    }

    public void setP(int p) {
        this.p.getContent().setValue(p);
    }

    public int[][] getN() {
        return n.getContent().getIntValues();
    }

    public void setN(int[][] n) {
        this.n.getContent().setValues(n);
    }

    public int[] getXagg() {
        return xagg.getContent().getIntValues();
    }

    public void setXagg(int[] xagg) {
        this.xagg.getContent().setValues(xagg);
    }

    public int getUagg() {
        return uagg.getContent().getIntValue();
    }

    public void setUagg(int uagg) {
        this.uagg.getContent().setValue(uagg);
    }

    @Override
    protected void registerContstraints() {
        // TODO Dummy Code
    }

}
