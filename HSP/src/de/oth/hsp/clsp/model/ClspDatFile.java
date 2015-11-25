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

    public DatEntry<SingleContent> getCplex() {
        return cplex;
    }

    public void setCplex(DatEntry<SingleContent> cplex) {
        this.cplex = cplex;
    }

    public DatEntry<SingleContent> getT() {
        return t;
    }

    public void setT(DatEntry<SingleContent> t) {
        this.t = t;
    }

    public DatEntry<SingleContent> getK() {
        return k;
    }

    public void setK(DatEntry<SingleContent> k) {
        this.k = k;
    }

    public DatEntry<TwoDimFieldContent> getD() {
        return d;
    }

    public void setD(DatEntry<TwoDimFieldContent> d) {
        this.d = d;
    }

    public DatEntry<ArrayContent> getH() {
        return h;
    }

    public void setH(DatEntry<ArrayContent> h) {
        this.h = h;
    }

    public DatEntry<ArrayContent> getS() {
        return s;
    }

    public void setS(DatEntry<ArrayContent> s) {
        this.s = s;
    }

    public DatEntry<ArrayContent> getP() {
        return p;
    }

    public void setP(DatEntry<ArrayContent> p) {
        this.p = p;
    }

    public DatEntry<ArrayContent> getTb() {
        return tb;
    }

    public void setTb(DatEntry<ArrayContent> tb) {
        this.tb = tb;
    }

    public DatEntry<ArrayContent> getTr() {
        return tr;
    }

    public void setTr(DatEntry<ArrayContent> tr) {
        this.tr = tr;
    }

    public DatEntry<ArrayContent> getY0() {
        return y0;
    }

    public void setY0(DatEntry<ArrayContent> y0) {
        this.y0 = y0;
    }

    public DatEntry<ArrayContent> getB0() {
        return b0;
    }

    public void setB0(DatEntry<ArrayContent> b0) {
        this.b0 = b0;
    }

    public DatEntry<TwoDimFieldContent> getqIn() {
        return qIn;
    }

    public void setqIn(DatEntry<TwoDimFieldContent> qIn) {
        this.qIn = qIn;
    }

    public DatEntry<ArrayContent> getE() {
        return e;
    }

    public void setE(DatEntry<ArrayContent> e) {
        this.e = e;
    }

    public DatEntry<SingleContent> getC() {
        return c;
    }

    public void setC(DatEntry<SingleContent> c) {
        this.c = c;
    }

    public DatEntry<ArrayContent> getqMax() {
        return qMax;
    }

    public void setqMax(DatEntry<ArrayContent> qMax) {
        this.qMax = qMax;
    }
}
