package com.jun.spitter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,
reason = "SpittleNotFound")
public class SpittleNotFoundException extends RuntimeException {

}
