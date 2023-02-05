package com.paraparp.realestatestats.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponseEntity {

    private Date timestamp;
    private String mensaje;
    private String detalles;
    private String httpCodeMsg;

}