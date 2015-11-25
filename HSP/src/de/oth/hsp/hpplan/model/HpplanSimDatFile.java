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

public class HpplanSimDatFile extends AbstractDatFile {
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

    public DatEntry<SingleContent> getJ() {
        return j;
    }

    public void setJ(DatEntry<SingleContent> j) {
        this.j = j;
    }

    public DatEntry<SingleContent> getK() {
        return k;
    }

    public void setK(DatEntry<SingleContent> k) {
        this.k = k;
    }

    public DatEntry<SingleContent> getfBig() {
        return fBig;
    }

    public void setfBig(DatEntry<SingleContent> fBig) {
        this.fBig = fBig;
    }

    public DatEntry<SingleContent> gettStart() {
        return tStart;
    }

    public void settStart(DatEntry<SingleContent> tStart) {
        this.tStart = tStart;
    }

    public DatEntry<SingleContent> gettEnd() {
        return tEnd;
    }

    public void settEnd(DatEntry<SingleContent> tEnd) {
        this.tEnd = tEnd;
    }

    public DatEntry<TwoDimFieldContent> getB() {
        return b;
    }

    public void setB(DatEntry<TwoDimFieldContent> b) {
        this.b = b;
    }

    public DatEntry<TwoDimFieldContent> getuMax() {
        return uMax;
    }

    public void setuMax(DatEntry<TwoDimFieldContent> uMax) {
        this.uMax = uMax;
    }

    public DatEntry<TwoDimFieldContent> getU() {
        return u;
    }

    public void setU(DatEntry<TwoDimFieldContent> u) {
        this.u = u;
    }

    public DatEntry<SingleContent> getzMax() {
        return zMax;
    }

    public void setzMax(DatEntry<SingleContent> zMax) {
        this.zMax = zMax;
    }

    public DatEntry<SingleContent> getzMin() {
        return zMin;
    }

    public void setzMin(DatEntry<SingleContent> zMin) {
        this.zMin = zMin;
    }

    public DatEntry<ThreeDimFieldContent> getF() {
        return f;
    }

    public void setF(DatEntry<ThreeDimFieldContent> f) {
        this.f = f;
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

    public DatEntry<SingleContent> getiInit() {
        return iInit;
    }

    public void setiInit(DatEntry<SingleContent> iInit) {
        this.iInit = iInit;
    }

    public DatEntry<TwoDimFieldContent> getxInit() {
        return xInit;
    }

    public void setxInit(DatEntry<TwoDimFieldContent> xInit) {
        this.xInit = xInit;
    }

    public DatEntry<TwoDimFieldContent> getuInit() {
        return uInit;
    }

    public void setuInit(DatEntry<TwoDimFieldContent> uInit) {
        this.uInit = uInit;
    }

    public DatEntry<SingleContent> getP() {
        return p;
    }

    public void setP(DatEntry<SingleContent> p) {
        this.p = p;
    }

    public DatEntry<TwoDimFieldContent> getN() {
        return n;
    }

    public void setN(DatEntry<TwoDimFieldContent> n) {
        this.n = n;
    }

    public DatEntry<ArrayContent> getXagg() {
        return xagg;
    }

    public void setXagg(DatEntry<ArrayContent> xagg) {
        this.xagg = xagg;
    }

    public DatEntry<SingleContent> getUagg() {
        return uagg;
    }

    public void setUagg(DatEntry<SingleContent> uagg) {
        this.uagg = uagg;
    }

}
