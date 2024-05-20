package com.vp.vpcontactusfp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactUsModel {

    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String message;
    private boolean isTest;


}
