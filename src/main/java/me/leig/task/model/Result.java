package me.leig.task.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result<T> {

    private T t;

    private String errorCode;

    private String errorMessage;
}
