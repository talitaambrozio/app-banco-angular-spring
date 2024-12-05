package com.desafio.ibm.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID clienteId;
    private String nome;
    private Integer idade;
    private String email;
    private String password;
    @OneToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name="users_roles", joinColumns = @JoinColumn(name = "cliente_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<ModelRole> roles;
}
