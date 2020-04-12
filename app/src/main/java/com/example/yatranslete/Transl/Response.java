package com.example.yatranslete.Transl;

import java.util.Arrays;

public class Response {
    int code;
    String lang;
    String[] text;

    @Override
    public String toString() {
        return text[0];
    }
}