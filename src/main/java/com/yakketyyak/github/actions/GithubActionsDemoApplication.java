package com.yakketyyak.github.actions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@SpringBootApplication
@RestController
@RequestMapping(value = "/customers")
public class GithubActionsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubActionsDemoApplication.class, args);
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Set<Customer>>  getCustomerById(@PathVariable String customerId) {
		Customer response = Customer.builder().customerId("123").customerName("Name").build();
		Link link = linkTo(GithubActionsDemoApplication.class).slash(response.getCustomerId()).withSelfRel();
		response.add(link);
		Set<Customer> responses = new HashSet<>();
		responses.add(response);
		return new ResponseEntity<>(responses, HttpStatus.OK);
	}

}
