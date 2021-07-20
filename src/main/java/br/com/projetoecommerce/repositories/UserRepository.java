package br.com.projetoecommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoecommerce.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
	
	List<User> findByNameContaining(String name);
	Optional<User> findByEmail(String email);
	User findByEmailContaining(String email);
}
