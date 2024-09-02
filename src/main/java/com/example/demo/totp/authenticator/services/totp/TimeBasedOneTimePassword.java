package com.example.demo.totp.authenticator.services.totp;


import com.example.demo.totp.dto.response.EnableMfaResponse;

public interface TimeBasedOneTimePassword {

    EnableMfaResponse createTotp(String userId);

    void validateTotp(int otp) throws IllegalAccessException;
}
