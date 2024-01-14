package com.chattercraze.Server.configs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse<T> {
    T data;
    Error error;
}
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Error {
    int code;
    String message;
    ArrayList<String> errorList;
}
