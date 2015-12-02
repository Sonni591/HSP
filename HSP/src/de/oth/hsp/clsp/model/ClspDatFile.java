package de.oth.hsp.clsp.model;

import de.oth.hsp.common.dat.AbstractDatFile;
import de.oth.hsp.common.dat.DatEntry;
import de.oth.hsp.common.dat.EntryDesc;
import de.oth.hsp.common.dat.desc.ContentType;
import de.oth.hsp.common.dat.desc.NumericalType;
import de.oth.hsp.common.dat.value.ArrayContent;
import de.oth.hsp.common.dat.value.SingleContent;
import de.oth.hsp.common.dat.value.TwoDimFieldContent;

/**
 * Describes a a <i>dat</i> file for CLSP problem solving.
 * 
 * @author Thomas Butz
 *
 */
public class ClspDatFile extends AbstractDatFile {
    private static final String MOD_PATH = "/resources/CLSP.mod";

    @EntryDesc(position = 0, name = "CPLEX_EPGAP", conType = ContentType.SINGLE, numType = NumericalType.FLOAT)
    private DatEntry<SingleContent> cplex;

    @EntryDesc(position = 1, name = "T", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> t;

    @EntryDesc(position = 2, name = "K", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> k;

    @EntryDesc(position = 3, name = "d", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.INTEGER)
    private DatEntry<TwoDimFieldContent> d;

    @EntryDesc(position = 4, name = "h", conType = ContentType.ARRAY, numType = NumericalType.INTEGER)
    private DatEntry<ArrayContent> h;

    @EntryDesc(position = 5, name = "s", conType = ContentType.ARRAY, numType = NumericalType.INTEGER)
    private DatEntry<ArrayContent> s;

    @EntryDesc(position = 6, name = "p", conType = ContentType.ARRAY, numType = NumericalType.INTEGER)
    private DatEntry<ArrayContent> p;

    @EntryDesc(position = 7, name = "tb", conType = ContentType.ARRAY, numType = NumericalType.INTEGER)
    private DatEntry<ArrayContent> tb;

    @EntryDesc(position = 8, name = "tr", conType = ContentType.ARRAY, numType = NumericalType.INTEGER)
    private DatEntry<ArrayContent> tr;

    @EntryDesc(position = 9, name = "y0", conType = ContentType.ARRAY, numType = NumericalType.INTEGER)
    private DatEntry<ArrayContent> y0;

    @EntryDesc(position = 10, name = "B0", conType = ContentType.ARRAY, numType = NumericalType.INTEGER)
    private DatEntry<ArrayContent> b0;

    @EntryDesc(position = 11, name = "qIn", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.INTEGER)
    private DatEntry<TwoDimFieldContent> qIn;

    @EntryDesc(position = 12, name = "E", conType = ContentType.ARRAY, numType = NumericalType.INTEGER)
    private DatEntry<ArrayContent> e;

    @EntryDesc(position = 13, name = "C", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> c;

    @EntryDesc(position = 14, name = "qMax", conType = ContentType.ARRAY, numType = NumericalType.INTEGER)
    private DatEntry<ArrayContent> qMax;

    @Override
    protected String getModResourcePath() {
        return MOD_PATH;
    }

    public double getCplex() {
        return cplex.getContent().getDoubleValue();
    }

    public void setCplex(double cplex) {
        this.cplex.getContent().setValue(cplex);
    }

    public int getT() {
        return t.getContent().getIntValue();
    }

    public void setT(int t) {
        this.t.getContent().setValue(t);
    }

    public int getK() {
        return k.getContent().getIntValue();
    }

    public void setK(int k) {
        this.k.getContent().setValue(k);
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

    public int[] getS() {
        return s.getContent().getIntValues();
    }

    public void setS(int[] s) {
        this.s.getContent().setValues(s);
    }

    public int[] getP() {
        return p.getContent().getIntValues();
    }

    public void setP(int[] p) {
        this.p.getContent().setValues(p);
    }

    public int[] getTb() {
        return tb.getContent().getIntValues();
    }

    public void setTb(int[] tb) {
        this.tb.getContent().setValues(tb);
    }

    public int[] getTr() {
        return tr.getContent().getIntValues();
    }

    public void setTr(int[] tr) {
        this.tr.getContent().setValues(tr);
    }

    public int[] getY0() {
        return y0.getContent().getIntValues();
    }

    public void setY0(int[] y0) {
        this.y0.getContent().setValues(y0);
    }

    public int[] getB0() {
        return b0.getContent().getIntValues();
    }

    public void setB0(int[] b0) {
        this.b0.getContent().setValues(b0);
    }

    public int[][] getqIn() {
        return qIn.getContent().getIntValues();
    }

    public void setqIn(int[][] qIn) {
        this.qIn.getContent().setValues(qIn);
    }

    public int[] getE() {
        return e.getContent().getIntValues();
    }

    public void setE(int[] e) {
        this.e.getContent().setValues(e);
    }

    public int getC() {
        return c.getContent().getIntValue();
    }

    public void setC(int c) {
        this.c.getContent().setValue(c);
    }

    public int[] getqMax() {
        return qMax.getContent().getIntValues();
    }

    public void setqMax(int[] qMax) {
        this.qMax.getContent().setValues(qMax);
    }

    @Override
    protected void registerContstraints() {
        // TODO Dummy Code
    }
}
