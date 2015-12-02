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

    public double getJ() {
        return j.getContent().getDoubleValue();
    }

    public void setJ(double j) {
        this.j.getContent().setValue(j);
    }

    public DatEntry<SingleContent> getK() {
        return k;
    }

    public void setK(DatEntry<SingleContent> k) {
        this.k = k;
    }

    public DatEntry<SingleContent> getT() {
        return t;
    }

    public void setT(DatEntry<SingleContent> t) {
        this.t = t;
    }

    public DatEntry<SingleContent> getzMax() {
        return zMax;
    }

    public void setzMax(DatEntry<SingleContent> zMax) {
        this.zMax = zMax;
    }

    public DatEntry<SingleContent> getF() {
        return f;
    }

    public void setF(DatEntry<SingleContent> f) {
        this.f = f;
    }

    public DatEntry<SingleContent> getD() {
        return d;
    }

    public void setD(DatEntry<SingleContent> d) {
        this.d = d;
    }

    public DatEntry<SingleContent> getH() {
        return h;
    }

    public void setH(DatEntry<SingleContent> h) {
        this.h = h;
    }

    public DatEntry<SingleContent> getB() {
        return b;
    }

    public void setB(DatEntry<SingleContent> b) {
        this.b = b;
    }

    public DatEntry<SingleContent> getuMax() {
        return uMax;
    }

    public void setuMax(DatEntry<SingleContent> uMax) {
        this.uMax = uMax;
    }

    public DatEntry<SingleContent> getU() {
        return u;
    }

    public void setU(DatEntry<SingleContent> u) {
        this.u = u;
    }

    public DatEntry<SingleContent> getiInit() {
        return iInit;
    }

    public void setiInit(DatEntry<SingleContent> iInit) {
        this.iInit = iInit;
    }

    @Override
    protected void registerContstraints() {
        // TODO Dummy Code
    }
}
