package de.oth.hsp.clsp.model;

import java.util.List;

import de.oth.hsp.common.dat.AbstractDatFile;
import de.oth.hsp.common.dat.Constraint;
import de.oth.hsp.common.dat.DatEntry;
import de.oth.hsp.common.dat.Entry;
import de.oth.hsp.common.dat.NumericalType;
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
    private static final String MOD_NAME = "CLSP";

    @Entry(position = 0, name = "T", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private final DatEntry<SingleContent> t = new DatEntry<>("T", 0, new SingleContent(NumericalType.INTEGER));

    @Entry(position = 1, name = "K", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> k;

    @Entry(position = 2, name = "J", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> j;

    @Entry(position = 3, name = "M", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> m;

    @Entry(position = 4, name = "b", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.FLOAT)
    private DatEntry<TwoDimFieldContent> b;

    @Entry(position = 5, name = "d", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.INTEGER)
    private DatEntry<TwoDimFieldContent> d;

    @Entry(position = 6, name = "h", conType = ContentType.ARRAY, numType = NumericalType.FLOAT)
    private DatEntry<ArrayContent> h;

    @Entry(position = 7, name = "s", conType = ContentType.ARRAY, numType = NumericalType.FLOAT)
    private DatEntry<ArrayContent> s;

    @Entry(position = 8, name = "tb", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.FLOAT)
    private DatEntry<TwoDimFieldContent> tb;

    @Entry(position = 9, name = "tr", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.FLOAT)
    private DatEntry<TwoDimFieldContent> tr;

    @Entry(position = 10, name = "z", conType = ContentType.ARRAY, numType = NumericalType.INTEGER)
    private DatEntry<ArrayContent> z;

    @Entry(position = 11, name = "y0", conType = ContentType.ARRAY, numType = NumericalType.INTEGER)
    private DatEntry<ArrayContent> y0;

    @Override
    protected String getModName() {
        return MOD_NAME;
    }

    public int getT() {
        return t.getContent().getIntValue();
    }

    public void setT(Number t) {
        this.t.getContent().setValue(t);
    }

    public int getK() {
        return k.getContent().getIntValue();
    }

    public void setK(Number k) {
        this.k.getContent().setValue(k);
    }

    public int getJ() {
        return j.getContent().getIntValue();
    }

    public void setJ(Number j) {
        this.j.getContent().setValue(j);
    }

    public int getM() {
        return m.getContent().getIntValue();
    }

    public void setM(Number m) {
        this.m.getContent().setValue(m);
    }

    public Number[][] getB() {
        return b.getContent().getValues();
    }

    public void setB(Number[][] b) {
        this.b.getContent().setValues(b);
    }

    public Number[][] getD() {
        return d.getContent().getValues();
    }

    public void setD(Number[][] d) {
        this.d.getContent().setValues(d);
    }

    public Number[] getH() {
        return h.getContent().getValues();
    }

    public void setH(Number[] h) {
        this.h.getContent().setValues(h);
    }

    public Number[] getS() {
        return s.getContent().getValues();
    }

    public void setS(Number[] s) {
        this.s.getContent().setValues(s);
    }

    public Number[][] getTb() {
        return tb.getContent().getValues();
    }

    public void setTb(Number[][] tb) {
        this.tb.getContent().setValues(tb);
    }

    public Number[][] getTr() {
        return tr.getContent().getValues();
    }

    public void setTr(Number[][] tr) {
        this.tr.getContent().setValues(tr);
    }

    public Number[] getZ() {
        return z.getContent().getValues();
    }

    public void setZ(Number[] y0) {
        this.z.getContent().setValues(y0);
    }

    public Number[] getY0() {
        return y0.getContent().getValues();
    }

    public void setY0(Number[] y0) {
        this.y0.getContent().setValues(y0);
    }

    @Override
    protected void registerConstraints(List<Constraint<?>> constraints) {
        // TODO Dummy Code
    }
}
