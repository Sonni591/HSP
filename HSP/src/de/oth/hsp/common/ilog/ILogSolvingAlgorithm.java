package de.oth.hsp.common.ilog;

import de.oth.hsp.common.ilog.exception.NotSolvableException;

public interface ILogSolvingAlgorithm<E extends ILogRequest, T extends ILogResponse> {

    T solve(String pathToDatFile, String pathToDatDir, String pathExcelExport) throws NotSolvableException;

    T solve(String pathToDatFile, String pathToDatDir) throws NotSolvableException;

    T solve(E request) throws NotSolvableException;
}
