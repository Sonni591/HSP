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

    @EntryDesc(position = 0, name = "T", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> t;

    @EntryDesc(position = 1, name = "K", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> k;

    @EntryDesc(position = 2, name = "J", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> j;

    @EntryDesc(position = 3, name = "M", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> m;

    @EntryDesc(position = 4, name = "b", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.FLOAT)
    private DatEntry<TwoDimFieldContent> b;

    @EntryDesc(position = 5, name = "d", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.INTEGER)
    private DatEntry<TwoDimFieldContent> d;

    @EntryDesc(position = 6, name = "h", conType = ContentType.ARRAY, numType = NumericalType.FLOAT)
    private DatEntry<ArrayContent> h;

    @EntryDesc(position = 7, name = "s", conType = ContentType.ARRAY, numType = NumericalType.FLOAT)
    private DatEntry<ArrayContent> s;

    @EntryDesc(position = 8, name = "tb", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.FLOAT)
    private DatEntry<TwoDimFieldContent> tb;

    @EntryDesc(position = 9, name = "tr", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.FLOAT)
    private DatEntry<TwoDimFieldContent> tr;

    @EntryDesc(position = 10, name = "z", conType = ContentType.ARRAY, numType = NumericalType.INTEGER)
    private DatEntry<ArrayContent> z;

    @EntryDesc(position = 11, name = "y0", conType = ContentType.ARRAY, numType = NumericalType.INTEGER)
    private DatEntry<ArrayContent> y0;

    @Override
    protected String getModResourcePath() {
        return MOD_PATH;
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

    public int getJ() {
        return j.getContent().getIntValue();
    }

    public void setJ(int j) {
        this.j.getContent().setValue(j);
    }

    public int getM() {
        return m.getContent().getIntValue();
    }

    public void setM(int m) {
        this.m.getContent().setValue(m);
    }

    public double[][] getB() {
        return b.getContent().getDoubleValues();
    }

    public void setB(double[][] b) {
        this.b.getContent().setValues(b);
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

    public double[] getS() {
        return s.getContent().getDoubleValues();
    }

    public void setS(double[] s) {
        this.s.getContent().setValues(s);
    }

    public double[][] getTb() {
        return tb.getContent().getDoubleValues();
    }

    public void setTb(double[][] tb) {
        this.tb.getContent().setValues(tb);
    }

    public double[][] getTr() {
        return tr.getContent().getDoubleValues();
    }

    public void setTr(double[][] tr) {
        this.tr.getContent().setValues(tr);
    }

    public int[] getZ() {
        return z.getContent().getIntValues();
    }

    public void setZ(int[] y0) {
        this.z.getContent().setValues(y0);
    }

    public int[] getY0() {
        return y0.getContent().getIntValues();
    }

    public void setY0(int[] y0) {
        this.y0.getContent().setValues(y0);
    }

    @Override
    protected void registerContstraints() {
        // TODO Dummy Code
    }
}
