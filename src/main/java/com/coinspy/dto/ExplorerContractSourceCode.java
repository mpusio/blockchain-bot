package com.coinspy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ExplorerContractSourceCode {
    @JsonProperty
    private String status;
    @JsonProperty
    private String message;
    @JsonProperty
    private ExplorerResult[] result;

    @Getter
    public static class ExplorerResult{
        @JsonProperty("SourceCode")
        private String sourceCode;
        @JsonProperty("ABI")
        private String abi;
        @JsonProperty("ContractName")
        private String contractName;
        @JsonProperty("CompilerVersion")
        private String compilerVersion;
        @JsonProperty("OptimalizationUsed")
        private String optimalizationUsed;
        @JsonProperty("Runs")
        private String runs;
        @JsonProperty("ConstructorArguments")
        private String constructorArguments;
        @JsonProperty("EVMVersion")
        private String evmVersion;
        @JsonProperty("Library")
        private String library;
        @JsonProperty("LicenseType")
        private String licenseType;
        @JsonProperty("Proxy")
        private String proxy;
        @JsonProperty("Implementation")
        private String implementation;
        @JsonProperty("SwarmSource")
        private String swarmSource;
    }
}
