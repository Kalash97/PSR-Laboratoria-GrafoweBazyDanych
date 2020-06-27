package com.persistenceManager;

import com.entities.Entity;

public interface Persistence {

	public void save(Entity e);
	
	public String findAll(Class<? extends Entity> entity);
	
	public void delete(Integer id, Class<? extends Entity> entity);
	
	public void update(Integer id, Class<? extends Entity> entity, String fieldName, Object newValue);
	
	public void attach(Integer id1, Integer id2, Class<? extends Entity> entity1, Class<? extends Entity> entity2);
	
	public String findAttached(Integer id, Class<? extends Entity> entity);
	
	public void exit();
}
