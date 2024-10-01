package com.symon.msh.dto;

import lombok.Getter;

@Getter
public class VerificationEmail extends EmailDto{
    private String code;

    @Override
    public String getTemplate() {
        return "verification-email";
    }
}
