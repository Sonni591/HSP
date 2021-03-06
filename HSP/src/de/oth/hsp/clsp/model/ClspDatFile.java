package de.oth.hsp.clsp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.oth.hsp.common.dat.AbstractConstraint;
import de.oth.hsp.common.dat.AbstractDatFile;
import de.oth.hsp.common.dat.DatEntry;
import de.oth.hsp.common.dat.NumericalType;
import de.oth.hsp.common.dat.constraint.ArrayConstraint;
import de.oth.hsp.common.dat.constraint.TwoDimColConstraint;
import de.oth.hsp.common.dat.constraint.TwoDimRowConstraint;
import de.oth.hsp.common.dat.value.ArrayContent;
import de.oth.hsp.common.dat.value.SingleContent;
import de.oth.hsp.common.dat.value.TwoDimFieldContent;
import de.oth.hsp.common.utils.ArrayConverter;

/**
 * Describes a <i>dat</i> file for CLSP problem solving.
 * 
 * @author Thomas Butz
 *
 */
public class ClspDatFile extends AbstractDatFile {
    private static final String MOD_NAME = "CLSP";

    private final DatEntry<SingleContent> epgap = new DatEntry<>("CPLEX_EPGAP", new SingleContent(NumericalType.FLOAT));

    private final DatEntry<SingleContent> t = new DatEntry<>("T", new SingleContent(NumericalType.INTEGER));
    private final DatEntry<SingleContent> k = new DatEntry<>("K", new SingleContent(NumericalType.INTEGER));
    private final DatEntry<SingleContent> j = new DatEntry<>("J", new SingleContent(NumericalType.INTEGER));
    private final DatEntry<SingleContent> m = new DatEntry<>("M", new SingleContent(NumericalType.INTEGER));

    private final DatEntry<TwoDimFieldContent> b = new DatEntry<>("b", new TwoDimFieldContent(NumericalType.FLOAT));
    private final DatEntry<TwoDimFieldContent> d = new DatEntry<>("d", new TwoDimFieldContent(NumericalType.INTEGER));

    private final DatEntry<ArrayContent> h = new DatEntry<>("h", new ArrayContent(NumericalType.FLOAT));
    private final DatEntry<ArrayContent> s = new DatEntry<>("s", new ArrayContent(NumericalType.FLOAT));
    private final DatEntry<TwoDimFieldContent> tb = new DatEntry<>("tb", new TwoDimFieldContent(NumericalType.FLOAT));
    private final DatEntry<TwoDimFieldContent> tr = new DatEntry<>("tr", new TwoDimFieldContent(NumericalType.FLOAT));

    private final DatEntry<ArrayContent> z = new DatEntry<>("z", new ArrayContent(NumericalType.INTEGER));
    private final DatEntry<ArrayContent> y0 = new DatEntry<>("y0", new ArrayContent(NumericalType.INTEGER));
    private final DatEntry<ArrayContent> yT = new DatEntry<>("yT", new ArrayContent(NumericalType.INTEGER));

    private final List<AbstractConstraint> constraints;

    public ClspDatFile() {
        this.constraints = new ArrayList<>();

        constraints.add(new TwoDimRowConstraint(b, k));
        constraints.add(new TwoDimColConstraint(b, t));

        constraints.add(new TwoDimRowConstraint(d, k));
        constraints.add(new TwoDimColConstraint(d, t));

        constraints.add(new ArrayConstraint(h, k));

        constraints.add(new ArrayConstraint(s, k));

        constraints.add(new TwoDimRowConstraint(tb, k));
        constraints.add(new TwoDimColConstraint(tb, j));

        constraints.add(new TwoDimRowConstraint(tr, k));
        constraints.add(new TwoDimColConstraint(tr, j));

        constraints.add(new ArrayConstraint(z, k));

        constraints.add(new ArrayConstraint(y0, k));

        constraints.add(new ArrayConstraint(yT, k));

        initialize();
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

    public int[][] getBAsIntArray() {
        return b.getContent().getIntValues();
    }

    public float[][] getBAsFloatArray() {
        return ArrayConverter.convertTwoDimArray(b.getContent().getDoubleValues());
    }

    public void setB(Number[][] b) {
        this.b.getContent().setValues(b);
    }

    public Number[][] getD() {
        return d.getContent().getValues();
    }

    public int[][] getDAsIntArray() {
        return d.getContent().getIntValues();
    }

    public float[][] getDAsFloatArray() {
        return ArrayConverter.convertTwoDimArray(d.getContent().getDoubleValues());
    }

    public void setD(Number[][] d) {
        this.d.getContent().setValues(d);
    }

    public Number[] getH() {
        return h.getContent().getValues();
    }

    public int[] getHAsIntArray() {
        return h.getContent().getIntValues();
    }

    public float[] getHAsFloatArray() {
        return ArrayConverter.convertOneDimArray(h.getContent().getDoubleValues());
    }

    public void setH(Number[] h) {
        this.h.getContent().setValues(h);
    }

    public Number[] getS() {
        return s.getContent().getValues();
    }

    public int[] getSAsIntArray() {
        return s.getContent().getIntValues();
    }

    public float[] getSAsFloatArray() {
        return ArrayConverter.convertOneDimArray(s.getContent().getDoubleValues());
    }

    public void setS(Number[] s) {
        this.s.getContent().setValues(s);
    }

    public Number[][] getTb() {
        return tb.getContent().getValues();
    }

    public int[][] getTbAsIntArray() {
        return tb.getContent().getIntValues();
    }

    public float[][] getTbAsFloatArray() {
        return ArrayConverter.convertTwoDimArray(tb.getContent().getDoubleValues());
    }

    public void setTb(Number[][] tb) {
        this.tb.getContent().setValues(tb);
    }

    public Number[][] getTr() {
        return tr.getContent().getValues();
    }

    public int[][] getTrAsIntArray() {
        return tr.getContent().getIntValues();
    }

    public float[][] getTrAsFloatArray() {
        return ArrayConverter.convertTwoDimArray(tr.getContent().getDoubleValues());
    }

    public void setTr(Number[][] tr) {
        this.tr.getContent().setValues(tr);
    }

    public Number[] getZ() {
        return z.getContent().getValues();
    }

    public int[] getZAsIntArray() {
        return z.getContent().getIntValues();
    }

    public float[] getZAsFloatArray() {
        return ArrayConverter.convertOneDimArray(z.getContent().getDoubleValues());
    }

    public void setZ(Number[] y0) {
        this.z.getContent().setValues(y0);
    }

    public Number[] getY0() {
        return y0.getContent().getValues();
    }

    public int[] getY0AsIntArray() {
        return y0.getContent().getIntValues();
    }

    public float[] getY0AsFloatArray() {
        return ArrayConverter.convertOneDimArray(y0.getContent().getDoubleValues());
    }

    public void setY0(Number[] y0) {
        this.y0.getContent().setValues(y0);
    }

    public Number[] getYT() {
        return yT.getContent().getValues();
    }

    public int[] getYTAsIntArray() {
        return yT.getContent().getIntValues();
    }

    public float[] getYTAsFloatArray() {
        return ArrayConverter.convertOneDimArray(yT.getContent().getDoubleValues());
    }

    public void setYT(Number[] yT) {
        this.yT.getContent().setValues(yT);
    }

    @Override
    public List<DatEntry<?>> getEntries() {
        return Collections.unmodifiableList(Arrays.asList(epgap, t, k, j, m, b, d, h, s, tb, tr, z, y0, yT));
    }

    @Override
    public List<AbstractConstraint> getConstraints() {
        return Collections.unmodifiableList(constraints);
    }
}
