package com.wn.loanapp.repository;

import java.io.Serializable;
import java.util.List;

public interface PrimaryGenericRepository <T, ID extends Serializable>{

	public List<T> findAll();
    
    public void saveOrUpdate(T object);
    
    public void saveAll(List<T> object);

    public T update(T object);

    public T get(Long id);

    public void delete(T object);

    public void insert(T object);

    public boolean exists(Long id);
	
}
