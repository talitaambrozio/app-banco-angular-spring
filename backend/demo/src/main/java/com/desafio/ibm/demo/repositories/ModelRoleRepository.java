package com.desafio.ibm.demo.repositories;

import com.desafio.ibm.demo.enums.Role;
import com.desafio.ibm.demo.models.ModelRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ModelRoleRepository extends JpaRepository<ModelRole, UUID> {

    ModelRole findByName(Role name);
}
