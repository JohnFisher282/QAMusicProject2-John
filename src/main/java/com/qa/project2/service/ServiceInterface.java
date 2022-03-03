package com.qa.project2.service;

import java.util.List;

public interface ServiceInterface<T> {
	
	T create(T t);
	
	List<T> getAll();
	
	T getIndividual(Integer id);
	
	T update(Integer id, T t);
	
	void delete(Integer id);

}
