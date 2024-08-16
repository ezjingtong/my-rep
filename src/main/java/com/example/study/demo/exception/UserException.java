package com.example.study.demo.exception;

public class UserException extends RuntimeException{
    public UserException(){}

    public UserException(String msg){
        super(msg);
    }
}
