package br.com.projetoecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoecommerce.entities.EmailModel;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, Long> {
}
