package de.oth.hsp.clsp.ilog;

import de.oth.hsp.common.ilog.ILogSolvingAlgorithm;
import de.oth.hsp.common.ilog.exception.NotSolvableException;

public interface ICLSPSolvingAlgorithm extends ILogSolvingAlgorithm<CLSPRequest, CLSPResponse> {

    @Override
    CLSPResponse solve(String pathToDatFile, String pathToDatDir, String pathExcelExport) throws NotSolvableException;

    @Override
    CLSPResponse solve(String pathToDatFile, String pathToDatDir) throws NotSolvableException;

    @Override
    CLSPResponse solve(CLSPRequest request) throws NotSolvableException;

    void printResult();

}
