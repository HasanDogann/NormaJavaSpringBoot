package com.example.bankingsystem.core.utilities;

public class SuccessResult extends com.example.bankingsystem.core.utilities.Result {
    public SuccessResult(){
        super(true);
    }
    public SuccessResult(String message){
        super(true,message);
    }

}
