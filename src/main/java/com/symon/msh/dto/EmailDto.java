package com.symon.msh.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.ToString;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = VerificationEmail.class, name = "verification-email")
})
@Getter
@ToString
public abstract class EmailDto {
    private String recipient;
    private String subject;
    private String lang;

    public abstract String getTemplate();
}
