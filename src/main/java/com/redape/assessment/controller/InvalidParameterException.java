package com.redape.assessment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bad parameter found")
public class InvalidParameterException extends RuntimeException {

}