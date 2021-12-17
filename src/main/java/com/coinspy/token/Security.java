package com.coinspy.token;

import com.coinspy.entity.Type;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Security {
    private boolean verified;
    private String contractName;
    private String compilerVersion;
    private String licenseType;
    private Type contractType;
}
