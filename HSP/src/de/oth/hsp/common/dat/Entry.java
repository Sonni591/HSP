package de.oth.hsp.common.dat;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Used to mark {@link DatEntry} fields in child classes of
 * {@link AbstractDatFile}.
 * 
 * @author Thomas Butz
 *
 */
@Documented
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(java.lang.annotation.ElementType.FIELD)
public @interface Entry {
}
