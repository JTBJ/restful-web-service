package com.in28minutes.rest.webservices.controller.example.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.in28minutes.rest.webservices.model.example.filter.SomeBean;

@RestController
public class FilterController {

	@GetMapping("/filter")
	public SomeBean retrieveSomeBean() {
		SomeBean someBean = new SomeBean("Value1", "Value2", "Value3");
		return someBean;
	}

	@GetMapping("/filter-list")
	public List<SomeBean> retrieveListOfSomeBean() {
		return Arrays.asList(new SomeBean("Value1", "Value2", "Value3"), new SomeBean("Value12", "Value23", "Value34"));
	}

	// == dynamic filtering
	// == "string", "string2"
	@GetMapping("/filter-dynamic")
	public MappingJacksonValue retrieveSomeBeanDynamically() {
		SomeBean someBean = new SomeBean("Value1", "Value2", "Value3");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("string", "string2");

		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("SomeBeanFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		return mapping;
	}

	// == dynamic filtering
	// == "string2"
	@GetMapping("/filter-list-dynamic")
	public MappingJacksonValue retrieveListOfSomeBeanDynamically() {
		List<SomeBean> list = Arrays.asList(new SomeBean("Value1", "Value2", "Value3"), new SomeBean("Value12", "Value23", "Value34"));

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("string2");

		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("SomeBeanFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		return mapping;
	}
}
