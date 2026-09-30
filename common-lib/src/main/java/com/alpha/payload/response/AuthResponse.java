package com.alpha.payload.response;

import com.alpha.payload.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String JWT;
    private String message;
    private UserDTO user;
    private String title;
}
