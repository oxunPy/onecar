package com.example.onecar.dto.records;

import java.util.Date;

public record AuthResponse (
    String token,
    Boolean authenticated,
    Date expireAt
) {}
