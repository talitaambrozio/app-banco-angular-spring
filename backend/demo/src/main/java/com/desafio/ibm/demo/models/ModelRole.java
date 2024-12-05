package com.desafio.ibm.demo.models;


import com.desafio.ibm.demo.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="roles")
public class ModelRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID roleId;

    @Enumerated(EnumType.STRING)
    private Role name;

}
