package com.example.bankingsystem.core.utilities;

public class Result {

    private boolean isSuccess;

    private String message;

    public Result(boolean isSuccess){

        this.isSuccess=isSuccess;
    }
    public Result(boolean isSuccess,String message){
        this(isSuccess);
        this.message=message;
    }

    public boolean isSuccessful(){
        return this.isSuccess;
    }
    public String getMessage(){
        return this.message;
    }
}
