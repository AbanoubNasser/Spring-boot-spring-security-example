package com.mondiamedia.model.JTW;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtAuthenticationResponse {

    private final String token;
}
