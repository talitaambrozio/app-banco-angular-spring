package com.desafio.ibm.demo.models.dtos.seguranca;

import com.desafio.ibm.demo.enums.Role;

public record CreateUserDTO(String email, String password, Role role) {
}
