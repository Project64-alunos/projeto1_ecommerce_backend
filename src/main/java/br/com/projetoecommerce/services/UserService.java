package br.com.projetoecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.projetoecommerce.entities.User;
import br.com.projetoecommerce.repositories.UserRepository;
import br.com.projetoecommerce.services.exceptions.DatabaseException;
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
		return new ArrayList<>(repository.findAll().stream().map(user -> user)
				.filter(user -> user.getName().toUpperCase().contains(name.toUpperCase()))
				.collect(Collectors.toList()));
	}

	public User findByEmail(String email) {
		User obj = null;
		for (User user : repository.findAll()) {
			if (email.contains(user.getEmail())) {
				obj = user; // repository.findById(user.getId());
			}
		}
		return obj;// .orElseThrow(() -> new ResourceNotFoundException(email));
	}
//		return new ArrayList<>(repository.findAll().stream()
//				.map(user -> user)
//				.filter(user -> user.getEmail().toUpperCase().contains(email.toUpperCase()))
//				.collect(Collectors.toList()));
//	}

	public User insert(User obj) {
		return repository.save(obj);
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
}
