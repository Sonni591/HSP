package de.oth.hsp.hpplan.model;

import de.oth.hsp.common.dat.AbstractDatFile;
import de.oth.hsp.common.dat.DatEntry;
import de.oth.hsp.common.dat.EntryDesc;
import de.oth.hsp.common.dat.desc.ContentType;
import de.oth.hsp.common.dat.desc.NumericalType;
import de.oth.hsp.common.dat.value.SingleContent;

/**
 * Describes a a <i>dat</i> file for HPPLAN-Stat problem solving.
 * 
 * @author Thomas Butz
 *
 */
public class HpplanStatDatFile extends AbstractDatFile {
    private static final String MOD_PATH = "/resources/HPPLAN-Statisch.mod";

    @EntryDesc(position = 0, name = "J", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> j;

    @EntryDesc(position = 1, name = "K", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> k;

    @EntryDesc(position = 2, name = "T", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> t;

    @EntryDesc(position = 3, name = "ZMax", conType = ContentType.SINGLE, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> zMax;

    @EntryDesc(position = 4, name = "f", conType = ContentType.THREE_DIM_FIELD, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> f;

    @EntryDesc(position = 5, name = "d", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> d;

    @EntryDesc(position = 6, name = "h", conType = ContentType.ARRAY, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> h;

    @EntryDesc(position = 7, name = "b", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> b;

    @EntryDesc(position = 8, name = "Umax", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> uMax;

    @EntryDesc(position = 9, name = "u", conType = ContentType.TWO_DIM_FIELD, numType = NumericalType.FLOAT)
    private DatEntry<SingleContent> u;

    @EntryDesc(position = 10, name = "Iinit", conType = ContentType.ARRAY, numType = NumericalType.INTEGER)
    private DatEntry<SingleContent> iInit;

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

    public int getT() {
        return t.getContent().getIntValue();
    }

    public void setT(int t) {
        this.t.getContent().setValue(t);
    }

    public int getzMax() {
        return zMax.getContent().getIntValue();
    }

    public void setzMax(int zMax) {
        this.zMax.getContent().setValue(zMax);
    }

    public int getF() {
        return f.getContent().getIntValue();
    }

    public void setF(int f) {
        this.f.getContent().setValue(f);
    }

    public int getD() {
        return d.getContent().getIntValue();
    }

    public void setD(int d) {
        this.d.getContent().setValue(d);
    }

    public int getH() {
        return h.getContent().getIntValue();
    }

    public void setH(int h) {
        this.h.getContent().setValue(h);
    }

    public int getB() {
        return b.getContent().getIntValue();
    }

    public void setB(int b) {
        this.b.getContent().setValue(b);
    }

    public DatEntry<SingleContent> getuMax() {
        return uMax;
    }

    public void setuMax(int uMax) {
        this.uMax.getContent().setValue(uMax);
    }

    public double getU() {
        return u.getContent().getDoubleValue();
    }

    public void setU(double u) {
        this.u.getContent().setValue(u);
    }

    public int getiInit() {
        return iInit.getContent().getIntValue();
    }

    public void setiInit(int iInit) {
        this.iInit.getContent().setValue(iInit);
    }

    @Override
    protected void registerContstraints() {
        // TODO Dummy Code
    }
}
