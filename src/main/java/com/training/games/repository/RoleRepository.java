package com.training.games.repository;

import com.training.games.repository.entity.Role;
import com.training.games.repository.entity.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(RoleEnum role);
}
