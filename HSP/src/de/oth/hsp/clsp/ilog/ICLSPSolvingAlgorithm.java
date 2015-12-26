package de.oth.hsp.clsp.ilog;

import de.oth.hsp.common.ilog.exception.NotSolvableException;

public interface ICLSPSolvingAlgorithm {

    CLSPResponse solve(String pathToDatFile, String pathToDatDir, String pathExcelExport) throws NotSolvableException;

    CLSPResponse solve(String pathToDatFile, String pathToDatDir) throws NotSolvableException;

    CLSPResponse solve(CLSPRequest request) throws NotSolvableException;

    void printResult();

}
