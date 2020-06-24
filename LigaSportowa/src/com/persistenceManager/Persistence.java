package com.persistenceManager;

import com.entities.Entity;

public interface Persistence {

	public void save(Entity e);
	
	public String findAll(Class<? extends Entity> entity);
	
	void delete(Integer id, Class<? extends Entity> entity);
	
	void update(Integer id, Class<? extends Entity> entity, String fieldName, Object newValue);
	
	public void exit();
}
