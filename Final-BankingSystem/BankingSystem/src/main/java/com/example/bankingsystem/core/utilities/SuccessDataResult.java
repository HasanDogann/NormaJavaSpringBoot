package com.example.bankingsystem.core.utilities;

public class SuccessDataResult<T> extends com.example.bankingsystem.core.utilities.DataResult {


    public SuccessDataResult(T data, String message) {
        super(data, true,message);
    }

    public SuccessDataResult(T data){
        super(data,true);
    }
    public SuccessDataResult(String message) {
      super(null,true,message);
    }
    public SuccessDataResult(){
        super(null,true);
    }
}
