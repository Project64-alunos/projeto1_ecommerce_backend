package br.com.projetoecommerce.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projetoecommerce.entities.User;
import br.com.projetoecommerce.services.EmailService;
import br.com.projetoecommerce.services.UserService;

@CrossOrigin(origins = "https://wcecommerce.netlify.app/")
@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;


	@PostMapping(value = "/all")
	public ResponseEntity<List<User>> findAll() {
		List<User> list = userService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@GetMapping(value = "/byId/{id}")
	public ResponseEntity<User> findById(@RequestBody @PathVariable Long id) {
		return ResponseEntity.ok(userService.findById(id));
	}

	@GetMapping(value = "/byName/{name}")
	public ResponseEntity<List<User>> findByName(@RequestBody @PathVariable String name) {
		return ResponseEntity.ok(userService.findByName(name));
	}

	@GetMapping(value = "/byEmail/{email}")
	public ResponseEntity<User> findByEmail(@RequestBody @PathVariable String email) {
		return ResponseEntity.ok(userService.findByEmail(email));
	}

	@PostMapping
	public ResponseEntity<User> insert(@Valid @RequestBody User obj) {
		obj = userService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		emailService.sendMailAuto(obj);
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
		obj = userService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

}
