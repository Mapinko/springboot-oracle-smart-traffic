package br.com.fiap.smarttraffic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    private String deviceId;

    private boolean pushNotificationEnabled;
}