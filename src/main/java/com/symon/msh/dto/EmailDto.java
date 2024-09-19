package com.symon.msh.dto;

import lombok.Getter;

@Getter
public class EmailDto {

    private String recipient;
    private String subject;
    private String message;
}
