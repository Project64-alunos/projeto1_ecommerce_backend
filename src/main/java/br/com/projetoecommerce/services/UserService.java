package br.com.projetoecommerce.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.projetoecommerce.entities.User;
import br.com.projetoecommerce.repositories.UserRepository;
import br.com.projetoecommerce.services.exceptions.DatabaseException;
import br.com.projetoecommerce.services.exceptions.DuplicateEmailException;
import br.com.projetoecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public List<User> findByName(String name) {
		return repository.findByNameContaining(name);
	}

	public Optional<User> findByEmailValidation(String email) {
		Optional<User> obj = repository.findByEmail(email);
		return obj;
	}

	public User insert(User obj) {
		if (findByEmailValidation(obj.getEmail()).isPresent()) {
			throw new DuplicateEmailException(obj.getEmail());
		} else {
			return repository.save(obj);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	public User update(Long id, User obj) {
		try {
			User entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());

	}

	public User findByEmail(String email) {
		return repository.findByEmailContaining(email);
	}
}
