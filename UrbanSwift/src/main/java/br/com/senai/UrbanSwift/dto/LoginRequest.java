package br.com.senai.UrbanSwift.dto;

import lombok.Data;

@Data
public class LoginRequest {
    public String senha;
    public String email;
}
