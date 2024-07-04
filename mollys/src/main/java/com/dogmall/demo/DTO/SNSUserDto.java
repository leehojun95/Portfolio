package com.dogmall.demo.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SNSUserDto {

    private String sns_id;
    private String sns_nickname;
    private String sns_email;
    private String sns_type;
}
