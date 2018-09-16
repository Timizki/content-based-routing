package io.vksn.dummy.ws;

import io.spring.guides.gs_producing_web_service.Country;
import io.spring.guides.gs_producing_web_service.Currency;

public class CountryBuilder {

	private String name;
	private int population;
	private Currency currency;
	
	private CountryBuilder(Currency currency) {
		
	}
	
	public static CountryBuilder EUR() {
		return new CountryBuilder(Currency.EUR);
	}
	
	public CountryBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public CountryBuilder withPopulation(int population) {
		this.population = population;
		return this;
	}
	
	public Country build() {
		Country country = new Country();
		country.setName(this.name);
		country.setPopulation(this.population);
		country.setCurrency(this.currency);
		
		return country;
	}
	
}
