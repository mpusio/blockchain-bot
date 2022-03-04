package com.coinspy.token;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Security {
    private boolean verified;
    private String contractName;
    private String compilerVersion;
    private String licenseType;
}
