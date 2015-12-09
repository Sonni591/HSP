package de.oth.hsp.hpplan.model;

import java.util.List;

import de.oth.hsp.common.dat.AbstractConstraint;
import de.oth.hsp.common.dat.AbstractDatFile;
import de.oth.hsp.common.dat.DatEntry;

/**
 * Describes a a <i>dat</i> file for HPPLAN-Stat problem solving.
 * 
 * @author Thomas Butz
 *
 */
public class HpplanStatDatFile extends AbstractDatFile {
    private static final String MOD_NAME = "HPPLAN-Statisch";
    //
    // @Entry(position = 0, name = "J", conType = ContentType.SINGLE, numType =
    // NumericalType.INTEGER)
    // private DatEntry<SingleContent> j;
    //
    // @Entry(position = 1, name = "K", conType = ContentType.SINGLE, numType =
    // NumericalType.INTEGER)
    // private DatEntry<SingleContent> k;
    //
    // @Entry(position = 2, name = "T", conType = ContentType.SINGLE, numType =
    // NumericalType.INTEGER)
    // private DatEntry<SingleContent> t;
    //
    // @Entry(position = 3, name = "ZMax", conType = ContentType.SINGLE, numType
    // = NumericalType.INTEGER)
    // private DatEntry<SingleContent> zMax;
    //
    // @Entry(position = 4, name = "f", conType = ContentType.THREE_DIM_FIELD,
    // numType = NumericalType.INTEGER)
    // private DatEntry<SingleContent> f;
    //
    // @Entry(position = 5, name = "d", conType = ContentType.TWO_DIM_FIELD,
    // numType = NumericalType.INTEGER)
    // private DatEntry<SingleContent> d;
    //
    // @Entry(position = 6, name = "h", conType = ContentType.ARRAY, numType =
    // NumericalType.INTEGER)
    // private DatEntry<SingleContent> h;
    //
    // @Entry(position = 7, name = "b", conType = ContentType.TWO_DIM_FIELD,
    // numType = NumericalType.INTEGER)
    // private DatEntry<SingleContent> b;
    //
    // @Entry(position = 8, name = "Umax", conType = ContentType.TWO_DIM_FIELD,
    // numType = NumericalType.INTEGER)
    // private DatEntry<SingleContent> uMax;
    //
    // @Entry(position = 9, name = "u", conType = ContentType.TWO_DIM_FIELD,
    // numType = NumericalType.FLOAT)
    // private DatEntry<SingleContent> u;
    //
    // @Entry(position = 10, name = "Iinit", conType = ContentType.ARRAY,
    // numType = NumericalType.INTEGER)
    // private DatEntry<SingleContent> iInit;
    //
    // @Override
    // protected String getModName() {
    // return MOD_PATH;
    // }
    //
    // public int getJ() {
    // return j.getContent().getIntValue();
    // }
    //
    // public void setJ(int j) {
    // this.j.getContent().setValue(j);
    // }
    //
    // public int getK() {
    // return k.getContent().getIntValue();
    // }
    //
    // public void setK(int k) {
    // this.k.getContent().setValue(k);
    // }
    //
    // public int getT() {
    // return t.getContent().getIntValue();
    // }
    //
    // public void setT(int t) {
    // this.t.getContent().setValue(t);
    // }
    //
    // public int getzMax() {
    // return zMax.getContent().getIntValue();
    // }
    //
    // public void setzMax(int zMax) {
    // this.zMax.getContent().setValue(zMax);
    // }
    //
    // public int getF() {
    // return f.getContent().getIntValue();
    // }
    //
    // public void setF(int f) {
    // this.f.getContent().setValue(f);
    // }
    //
    // public int getD() {
    // return d.getContent().getIntValue();
    // }
    //
    // public void setD(int d) {
    // this.d.getContent().setValue(d);
    // }
    //
    // public int getH() {
    // return h.getContent().getIntValue();
    // }
    //
    // public void setH(int h) {
    // this.h.getContent().setValue(h);
    // }
    //
    // public int getB() {
    // return b.getContent().getIntValue();
    // }
    //
    // public void setB(int b) {
    // this.b.getContent().setValue(b);
    // }
    //
    // public DatEntry<SingleContent> getuMax() {
    // return uMax;
    // }
    //
    // public void setuMax(int uMax) {
    // this.uMax.getContent().setValue(uMax);
    // }
    //
    // public double getU() {
    // return u.getContent().getDoubleValue();
    // }
    //
    // public void setU(double u) {
    // this.u.getContent().setValue(u);
    // }
    //
    // public int getiInit() {
    // return iInit.getContent().getIntValue();
    // }
    //
    // public void setiInit(int iInit) {
    // this.iInit.getContent().setValue(iInit);
    // }

    @Override
    public List<DatEntry<?>> getEntries() {
        // TODO Automatisch generierter Methodenstub
        return null;
    }

    @Override
    public List<AbstractConstraint> getConstraints() {
        // TODO Automatisch generierter Methodenstub
        return null;
    }

    @Override
    protected String getModName() {
        return MOD_NAME;
    }
}
