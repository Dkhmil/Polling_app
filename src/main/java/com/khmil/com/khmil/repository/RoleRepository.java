package com.khmil.com.khmil.repository;

import com.khmil.com.khmil.model.Role;
import com.khmil.com.khmil.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}