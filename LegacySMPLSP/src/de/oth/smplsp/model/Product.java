package de.oth.smplsp.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An representation of a product of the lotscheduling problem.
 * 
 * @author Tobias Eichinger
 *
 */
public class Product {

    // Produktnummer
    private IntegerProperty k;
    // Nachfragerate
    private DoubleProperty d;
    // Produktionsrate
    private DoubleProperty p;
    // Ruestzeit
    private DoubleProperty tau;
    // Ruestkostensatz
    private DoubleProperty s;
    // Lagerkostensatz
    private DoubleProperty h;
    // Losgroesse
    private DoubleProperty q;
    // production time (Produktionsdauer)
    private DoubleProperty t;
    // Auslastung der Anlage
    private DoubleProperty roh;
    // Reichweite
    private DoubleProperty r;

    /**
     * standard constructor with all fields
     * 
     * @param k
     * @param d
     * @param p
     * @param tau
     * @param s
     * @param h
     * @param r
     */
    public Product(int k, double d, double p, double tau, double s, double h) {
	this.k = new SimpleIntegerProperty(k);
	this.d = new SimpleDoubleProperty(d);
	this.p = new SimpleDoubleProperty(p);
	this.tau = new SimpleDoubleProperty(tau);
	this.s = new SimpleDoubleProperty(s);
	this.h = new SimpleDoubleProperty(h);
    }

    /**
     * 
     * @param values
     *            contains the parameters of the product in with the following
     *            order:
     *            <p>
     *            int k, double d, double p, double tau, double s, double h
     *            </p>
     */
    public Product(String[] values) {

	// Make sure, that the order in this constructor is the same as in the
	// getAllSaveParametersAsString method.
	this.k = new SimpleIntegerProperty(Integer.parseInt(values[0]));
	this.d = new SimpleDoubleProperty(Double.parseDouble(values[1]));
	this.p = new SimpleDoubleProperty(Double.parseDouble(values[2]));
	this.tau = new SimpleDoubleProperty(Double.parseDouble(values[3]));
	this.s = new SimpleDoubleProperty(Double.parseDouble(values[4]));
	this.h = new SimpleDoubleProperty(Double.parseDouble(values[5]));
    }

    /**
     * constructor with 0-values except of the index k
     * 
     * @param k
     */
    public Product(int k) {
	super();
	this.k = new SimpleIntegerProperty(k);
	this.d = new SimpleDoubleProperty(0.0);
	this.p = new SimpleDoubleProperty(0.0);
	this.tau = new SimpleDoubleProperty(0.0);
	this.s = new SimpleDoubleProperty(0.0);
	this.h = new SimpleDoubleProperty(0.0);
    }

    /**
     * @return the k
     */
    public int getK() {
	return k.getValue();
    }

    /**
     * @return the k property
     */
    public IntegerProperty getKProperty() {
	return k;
    }

    /**
     * @param k
     *            the k to set
     */
    public void setK(int k) {
	this.k = new SimpleIntegerProperty(k);
    }

    /**
     * @param k
     *            the k property to set
     */
    public void setKProperty(IntegerProperty k) {
	this.k = k;
    }

    /**
     * @return the d
     */
    public double getD() {
	return d.getValue();
    }

    /**
     * @return the d property
     */
    public DoubleProperty getDProperty() {
	return d;
    }

    /**
     * @param d
     *            the d to set
     */
    public void setD(double d) {
	this.d = new SimpleDoubleProperty(d);
    }

    /**
     * @param d
     *            the d property to set
     */
    public void setDProperty(DoubleProperty d) {
	this.d = d;
    }

    /**
     * @return the p
     */
    public double getP() {
	return p.getValue();
    }

    /**
     * @return the p property
     */
    public DoubleProperty getPProperty() {
	return p;
    }

    /**
     * @param p
     *            the p to set
     */
    public void setP(double p) {
	this.p = new SimpleDoubleProperty(p);
    }

    /**
     * @param p
     *            the p property to set
     */
    public void setPProperty(DoubleProperty p) {
	this.p = p;
    }

    /**
     * @return the tau
     */
    public double getTau() {
	return tau.getValue();
    }

    /**
     * @return the tau property
     */
    public DoubleProperty getTauProperty() {
	return tau;
    }

    /**
     * @param tau
     *            the tau to set
     */
    public void setTau(double tau) {
	this.tau = new SimpleDoubleProperty(tau);
    }

    /**
     * @param tau
     *            the tau property to set
     */
    public void setTauProperty(DoubleProperty tau) {
	this.tau = tau;
    }

    /**
     * @return the s
     */
    public double getS() {
	return s.getValue();
    }

    /**
     * @return the s property
     */
    public DoubleProperty getSProperty() {
	return s;
    }

    /**
     * @param s
     *            the s to set
     */
    public void setS(double s) {
	this.s = new SimpleDoubleProperty(s);
    }

    /**
     * @param s
     *            the s to set
     */
    public void setSProperty(DoubleProperty s) {
	this.s = s;
    }

    /**
     * @return the h
     */
    public double getH() {
	return h.getValue();
    }

    /**
     * @return the h property
     */
    public DoubleProperty getHProperty() {
	return h;
    }

    /**
     * @param h
     *            the h to set
     */
    public void setH(double h) {
	this.h = new SimpleDoubleProperty(h);
    }

    /**
     * @param h
     *            the h property to set
     */
    public void setHProperty(DoubleProperty h) {
	this.h = h;
    }

    /**
     * @return the lot size
     */
    public double getQ() {
	return q.getValue();
    }

    /**
     * @return the lot size property
     */
    public DoubleProperty getQProperty() {
	return q;
    }

    /**
     * @param q
     *            set the lot size
     */
    public void setQ(double q) {
	this.q = new SimpleDoubleProperty(q);
    }

    /**
     * @param q
     *            set the lot size property
     */
    public void setQProperty(DoubleProperty q) {
	this.q = q;
    }

    /**
     * @return t
     */
    public double getT() {
	return t.getValue();
    }

    /**
     * @return the t property
     */
    public DoubleProperty getTProperty() {
	return t;
    }

    /**
     * @param t
     */
    public void setT(double t) {
	this.t = new SimpleDoubleProperty(t);
    }

    /**
     * @param t
     *            the t property to set
     */
    public void setTProperty(DoubleProperty t) {
	this.t = t;
    }

    /**
     * @return roh
     */
    public double getRoh() {
	return roh.getValue();
    }

    /**
     * @return the roh property
     */
    public DoubleProperty getRohProperty() {
	return roh;
    }

    /**
     * @param roh
     *            the roh to set
     */
    public void setRoh(double roh) {
	this.roh = new SimpleDoubleProperty(roh);
    }

    /**
     * @param roh
     *            the roh property to set
     */
    public void setRohProperty(DoubleProperty roh) {
	this.roh = roh;
    }

    /**
     * @return r
     */
    public double getR() {
	return r.getValue();
    }

    /**
     * @return the r property
     */
    public DoubleProperty getRProperty() {
	return r;
    }

    /**
     * @param r
     *            the r to set
     */
    public void setR(double r) {
	this.r = new SimpleDoubleProperty(r);
    }

    /**
     * @param r
     *            the r property to set
     */
    public void setRProperty(DoubleProperty r) {
	this.r = r;
    }

    public Product clone() {
	return new Product(this.getK(), this.getD(), this.getP(),
		this.getTau(), this.getS(), this.getH());

    }

    /**
     * This method returns all parameters of a product, which should be saved in
     * a file.
     * 
     * @return
     */
    public String[] getAllSaveParametersAsString() {
	String[] productValues = new String[6];

	// Make sure, that the order in this method is the same as in the
	// constructor of this class.
	productValues[0] = Integer.toString(getK());
	productValues[1] = Double.toString(getD());
	productValues[2] = Double.toString(getP());
	productValues[3] = Double.toString(getTau());
	productValues[4] = Double.toString(getS());
	productValues[5] = Double.toString(getH());

	return productValues;
    }

}
