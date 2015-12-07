package de.oth.hsp.common.dat.desc;

/**
 * Defines the type of content of associated with a {@link EntryDesc}.
 * 
 * @author Thomas Butz
 * 
 */
public enum ContentType {
    /** describes a single integer/float value */
    SINGLE,
    /** describes a one-dimensional collection of integer/float values */
    ARRAY,
    /** describes a two-dimensional collection of integer/float values */
    TWO_DIM_FIELD,
    /** describes a three-dimensional collection of integer/float values */
    THREE_DIM_FIELD;
}
