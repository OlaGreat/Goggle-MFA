package com.example.demo.totp.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnableMfaResponse {
    private String qrCodeSecretKey;
    private String qrCodeUrl;
}
