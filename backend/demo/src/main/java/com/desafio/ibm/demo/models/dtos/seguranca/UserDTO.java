package com.desafio.ibm.demo.models.dtos.seguranca;

import com.desafio.ibm.demo.enums.Role;

import java.util.List;
import java.util.UUID;

public record UserDTO(UUID id, String email, List<Role> roles) {
}
