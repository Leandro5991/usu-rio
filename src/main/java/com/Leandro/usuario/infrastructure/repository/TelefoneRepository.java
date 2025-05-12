package com.Leandro.usuario.infrastructure.repository;

import com.Leandro.usuario.infrastructure.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

;

@Repository
public interface TelefoneRepository extends JpaRepository <Telefone, Long> {

}
