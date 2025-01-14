package com.miniapp.talks.app.converter;

import java.io.IOException;


public class ApiIOException extends IOException {

    public String code;

    public ApiIOException(String msg,String code) {
        super(msg);
        this.code = code;
    }

}
