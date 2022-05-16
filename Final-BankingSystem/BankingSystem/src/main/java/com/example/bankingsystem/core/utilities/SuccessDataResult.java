package com.example.bankingsystem.core.utilities;

public class SuccessDataResult<T> {

    private final Result result;
    private final T data;




    public SuccessDataResult(Result result, T data){
        this.result = result;
        this.data=data;
    }

    public SuccessDataResult( T data){

        this.data = data;
        result = null;
    }

}
