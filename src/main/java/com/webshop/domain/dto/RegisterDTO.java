package com.webshop.domain.dto;

import com.webshop.domain.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
