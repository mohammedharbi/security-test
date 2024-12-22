package com.example.jparelationi.Api;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiException extends RuntimeException {

public ApiException(String message) {
    super(message);
}


}
