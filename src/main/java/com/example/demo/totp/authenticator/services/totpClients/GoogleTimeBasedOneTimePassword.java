package com.example.demo.totp.authenticator.services.totpClients;

import com.example.demo.totp.authenticator.services.totp.TimeBasedOneTimePassword;
import com.example.demo.totp.dto.response.EnableMfaResponse;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class GoogleTimeBasedOneTimePassword implements TimeBasedOneTimePassword {

    private final GoogleAuthenticator googleAuthenticator;


    @Override
    public EnableMfaResponse createTotp(String userId) {
        GoogleAuthenticatorKey credentials = googleAuthenticator.createCredentials();
        String secretKey = credentials.getKey();

        String otpAuthTotpURL = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL("KARRABO", "Olamilekan", credentials);

        return EnableMfaResponse.builder()
                .qrCodeSecretKey(secretKey)
                .qrCodeUrl(otpAuthTotpURL)
                .build();
    }


    @Override
    public void validateTotp(int otp) throws IllegalAccessException {
        if (googleAuthenticator.authorize("", otp)) System.out.println("Successfully authenticated");
        else throw new IllegalAccessException("Get away you impersonator");
    }



}
