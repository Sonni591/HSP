package de.oth.hsp.common.dat;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import de.oth.hsp.common.dat.desc.ContentType;
import de.oth.hsp.common.dat.desc.NumericalType;

/**
 * Used to describe {@link DatEntry} fields in child classes of
 * {@link AbstractDatFile}.
 * 
 * @author Thomas Butz
 *
 */
@Documented
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(java.lang.annotation.ElementType.FIELD)
public @interface EntryDesc {
    /** the position in the dat file (starting with zero) */
    int position();

    /** the name of the entry */
    String name();

    /** the type of content in the entry */
    ContentType conType();

    /** the numerical type of the value(s) */
    NumericalType numType();
}
