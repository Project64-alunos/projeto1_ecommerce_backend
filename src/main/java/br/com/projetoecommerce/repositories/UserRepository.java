package br.com.projetoecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetoecommerce.entities.User;


public interface UserRepository extends JpaRepository<User, Long>  {

}
