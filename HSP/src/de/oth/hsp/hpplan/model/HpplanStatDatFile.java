package de.oth.hsp.hpplan.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.oth.hsp.common.dat.AbstractConstraint;
import de.oth.hsp.common.dat.AbstractDatFile;
import de.oth.hsp.common.dat.DatEntry;
import de.oth.hsp.common.dat.NumericalType;
import de.oth.hsp.common.dat.constraint.ArrayConstraint;
import de.oth.hsp.common.dat.constraint.ThreeDimColConstraint;
import de.oth.hsp.common.dat.constraint.ThreeDimFieldConstraint;
import de.oth.hsp.common.dat.constraint.ThreeDimRowConstraint;
import de.oth.hsp.common.dat.constraint.TwoDimColConstraint;
import de.oth.hsp.common.dat.constraint.TwoDimRowConstraint;
import de.oth.hsp.common.dat.value.ArrayContent;
import de.oth.hsp.common.dat.value.SingleContent;
import de.oth.hsp.common.dat.value.ThreeDimFieldContent;
import de.oth.hsp.common.dat.value.TwoDimFieldContent;
import de.oth.hsp.common.utils.ArrayConverter;

/**
 * Describes a <i>dat</i> file for HPPLAN-Stat problem solving.
 * 
 * @author Thomas Butz
 *
 */
public class HpplanStatDatFile extends AbstractDatFile {
    private static final String MOD_NAME = "HPPLAN-Statisch";

    private final DatEntry<SingleContent> epgap = new DatEntry<>("CPLEX_EPGAP", new SingleContent(NumericalType.FLOAT));

    private final DatEntry<SingleContent> j = new DatEntry<>("J", new SingleContent(NumericalType.INTEGER));
    private final DatEntry<SingleContent> k = new DatEntry<>("K", new SingleContent(NumericalType.INTEGER));
    private final DatEntry<SingleContent> t = new DatEntry<>("T", new SingleContent(NumericalType.INTEGER));

    private final DatEntry<SingleContent> zMax = new DatEntry<>("ZMax", new SingleContent(NumericalType.INTEGER));

    private final DatEntry<TwoDimFieldContent> b = new DatEntry<>("b", new TwoDimFieldContent(NumericalType.INTEGER));
    private final DatEntry<ThreeDimFieldContent> f = new DatEntry<>("f", new ThreeDimFieldContent(NumericalType.FLOAT));
    private final DatEntry<TwoDimFieldContent> d = new DatEntry<>("d", new TwoDimFieldContent(NumericalType.INTEGER));
    private final DatEntry<ArrayContent> h = new DatEntry<>("h", new ArrayContent(NumericalType.FLOAT));
    private final DatEntry<TwoDimFieldContent> uMax = new DatEntry<>("Umax", new TwoDimFieldContent(
            NumericalType.INTEGER));
    private final DatEntry<TwoDimFieldContent> u = new DatEntry<>("u", new TwoDimFieldContent(NumericalType.FLOAT));
    private final DatEntry<ArrayContent> iInit = new DatEntry<>("Iinit", new ArrayContent(NumericalType.INTEGER));

    private final List<AbstractConstraint> constraints;

    public HpplanStatDatFile() {
        this.constraints = new ArrayList<>();

        constraints.add(new TwoDimRowConstraint(b, j));
        constraints.add(new TwoDimColConstraint(b, t));

        constraints.add(new ThreeDimFieldConstraint(f, j));
        constraints.add(new ThreeDimRowConstraint(f, k));
        constraints.add(new ThreeDimColConstraint(f, zMax, 1));

        constraints.add(new TwoDimRowConstraint(d, k));
        constraints.add(new TwoDimColConstraint(d, t));

        constraints.add(new ArrayConstraint(h, k));

        constraints.add(new TwoDimRowConstraint(uMax, j));
        constraints.add(new TwoDimColConstraint(uMax, t));

        constraints.add(new TwoDimRowConstraint(u, j));
        constraints.add(new TwoDimColConstraint(u, t));

        constraints.add(new ArrayConstraint(iInit, k));

        initialize();
    }

    public Number getJ() {
        Number num = j.getContent().getIntValue();
        return num;
    }

    public void setJ(int j) {
        this.j.getContent().setValue(j);
    }

    public Number getK() {
        Number num = k.getContent().getIntValue();
        return num;
    }

    public void setK(int k) {
        this.k.getContent().setValue(k);
    }

    public Number getT() {
        Number num = t.getContent().getIntValue();
        return num;
    }

    public int getTAsIntValue() {
        return t.getContent().getIntValue();
    }

    public void setT(int t) {
        this.t.getContent().setValue(t);
    }

    public Number getzMax() {
        Number num = zMax.getContent().getIntValue();
        return num;
    }

    public int getzMaxAsIntValue() {
        return zMax.getContent().getIntValue();
    }

    public void setzMax(int zMax) {
        this.zMax.getContent().setValue(zMax);
    }

    public Number[][] getB() {
        return b.getContent().getValues();
    }

    public int[][] getBAsIntArray() {
        return b.getContent().getIntValues();

    }

    public float[][] getBAsFloatArray() {
        return ArrayConverter.convertTwoDimArray(b.getContent().getDoubleValues());
    }

    public int[][][] getFAsIntArray() {
        return f.getContent().getIntValues();
    }

    public float[][][] getFAsFloatArray() {
        return ArrayConverter.convertThreeDimArray(f.getContent().getDoubleValues());
    }

    public Number[][][] getF() {
        return f.getContent().getValues();
    }

    public void setF(Number[][][] f) {
        this.f.getContent().setValues(f);
    }

    public float[][] getDAsFloatArray() {
        return ArrayConverter.convertTwoDimArray(d.getContent().getDoubleValues());
    }

    public int[][] getDAsIntArray() {
        return d.getContent().getIntValues();
    }

    public Number[][] getD() {
        return d.getContent().getValues();
    }

    public void setD(Number[][] d) {
        this.d.getContent().setValues(d);
    }

    public int[] getHAsIntArray() {
        return h.getContent().getIntValues();
    }

    public float[] getHAsFloatArray() {
        return ArrayConverter.convertOneDimArray(h.getContent().getDoubleValues());
    }

    public Number[] getH() {
        return h.getContent().getValues();
    }

    public void setH(Number[] h) {
        this.h.getContent().setValues(h);
    }

    public float[][] getUMaxAsFloatArray() {
        return ArrayConverter.convertTwoDimArray(uMax.getContent().getDoubleValues());
    }

    public int[][] getuMaxAsIntArray() {
        return uMax.getContent().getIntValues();
    }

    public Number[][] getuMax() {
        return uMax.getContent().getValues();
    }

    public void setuMax(Number[][] uMax) {
        this.uMax.getContent().setValues(uMax);
    }

    public float[][] getUAsFloatArray() {
        return ArrayConverter.convertTwoDimArray(u.getContent().getDoubleValues());
    }

    public int[][] getUAsIntArray() {
        return u.getContent().getIntValues();
    }

    public double[][] getUAsDoubleArray() {
        return u.getContent().getDoubleValues();
    }

    public Number[][] getU() {
        return u.getContent().getValues();
    }

    public void setU(Number[][] u) {
        this.u.getContent().setValues(u);
    }

    public float[] getIInitAsFloatArray() {
        return ArrayConverter.convertOneDimArray(iInit.getContent().getDoubleValues());
    }

    public int[] getiInitAsIntArray() {
        return iInit.getContent().getIntValues();
    }

    public Number[] getiInit() {
        return iInit.getContent().getValues();
    }

    public void setiInit(Number[] iInit) {
        this.iInit.getContent().setValues(iInit);
    }

    @Override
    public List<DatEntry<?>> getEntries() {
        return Collections.unmodifiableList(Arrays.asList(epgap, j, k, t, zMax, b, f, d, h, uMax, u, iInit));
    }

    public void setB(Number[][] b) {
        this.b.getContent().setValues(b);
    }

    @Override
    public List<AbstractConstraint> getConstraints() {
        return constraints;
    }

    @Override
    protected String getModName() {
        return MOD_NAME;
    }

    public double getEpgap() {
        return epgap.getContent().getDoubleValue();
    }

    public void setEpgap(Number epgap) {
        this.epgap.getContent().setValue(epgap);
    }

}
