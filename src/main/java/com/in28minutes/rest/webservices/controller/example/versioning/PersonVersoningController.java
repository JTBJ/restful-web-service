package com.in28minutes.rest.webservices.controller.example.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.model.example.versioning.Name;
import com.in28minutes.rest.webservices.model.example.versioning.PersonV1;
import com.in28minutes.rest.webservices.model.example.versioning.PersonV2;

@RestController
public class PersonVersoningController {

	// == versioning via uri endpoints
	@GetMapping("/v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Bob Charlie");
	}

	// == versioning via uri endpoints
	@GetMapping("/v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	// == versioning via request parameters
	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Bob Charlie");
	}

	// == versioning via request parameters
	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	// == versioning via header parameters
	@GetMapping(value = "/person/headers", headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Bob Charlie");
	}

	// == versioning via header parameters
	@GetMapping(value = "/person/headers", headers = "X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	// == versioning via produces A.K.A Content Negotiation or Accept versioning
	@GetMapping(value = "/person/produces", produces = "application/my.company.app-v1+json")
	public PersonV1 producerrV1() {
		return new PersonV1("Bob Charlie");
	}

	// == versioning via produces A.K.A Content Negotiation or Accept versioning
	// == produces defines the type of request to be returned {applicaiton/...+json
	@GetMapping(value = "/person/produces", produces = "application/my.company.app-v2+json")
	public PersonV2 producerV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
}
