package de.oth.hsp.common.ilog;

import de.oth.hsp.common.ilog.exception.NotSolvableException;

/**
 * generic interface for solving algorithm classes
 *
 * @param <E>
 * @param <T>
 */
public interface ILogSolvingAlgorithm<E extends ILogRequest, T extends ILogResponse> {

    /**
     * This method tries to solve the problem given by the values of a given
     * .dat file. An excel file is generated which contains the input parameters
     * and the results. This method throws a NotSolvableException if the problem
     * is not solvable
     * 
     * @param pathToDatFile
     * @param pathToDatDir
     * @param pathExcelExport
     * @return
     * @throws NotSolvableException
     */
    T solve(String pathToDatFile, String pathToDatDir, String pathExcelExport) throws NotSolvableException;

    /**
     * This method tries to solve the problem given by the values of a given
     * .dat file. This method throws a NotSolvableException if the problem is
     * not solvable
     * 
     * @param pathToDatFile
     * @param pathToDatDir
     * @return
     * @throws NotSolvableException
     */
    T solve(String pathToDatFile, String pathToDatDir) throws NotSolvableException;

    /**
     * This method tries to solve the problem given by the values of a given
     * request. This method throws a NotSolvableException if the problem is not
     * solvable
     * 
     * @param request
     * @return
     * @throws NotSolvableException
     */
    T solve(E request) throws NotSolvableException;
}
