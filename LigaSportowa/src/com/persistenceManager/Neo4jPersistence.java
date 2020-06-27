package com.persistenceManager;

import static org.neo4j.driver.internal.types.InternalTypeSystem.TYPE_SYSTEM;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.Value;
import org.neo4j.driver.util.Pair;

import com.entities.Entity;

public class Neo4jPersistence implements Persistence {

	private Session session;

	public Neo4jPersistence() {
		Driver driver = GraphDatabase.driver("neo4j://localhost:7687", AuthTokens.basic("neo4j", "admin"));
		session = driver.session();
	}

	@Override
	public void save(Entity e) {

		String cmd = "CREATE (:" + e.getClass().getSimpleName() + "{";
		Field[] fields = e.getClass().getDeclaredFields();

		Map<String, Object> parameters = new HashMap<>();

		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];

			try {
				boolean accessible = f.isAccessible();
				f.setAccessible(true);

				String fieldName = f.getName();

				cmd += fieldName + ":$" + fieldName + ",";

				parameters.put(fieldName, f.get(e));
				f.setAccessible(accessible);

			} catch (IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
		}

		cmd = StringUtils.chop(cmd);
		cmd += "})";

		try (Transaction tx = session.beginTransaction()) {
			tx.run(cmd, parameters);
			tx.commit();
			tx.close();
		}
	}

	@Override
	public String findAll(Class<? extends Entity> entity) {

		String res = "";

		String cmd = "MATCH (" + getFirstLetter(entity) + ":" + entity.getSimpleName() + ") RETURN "
				+ getFirstLetter(entity);

		Transaction tx = session.beginTransaction();

		Result result = tx.run(cmd);

		while (result.hasNext()) {
			Record record = result.next();
			List<Pair<String, Value>> fields = record.fields();

			for (Pair<String, Value> field : fields) {
				// Dla NODE TO DO: Dla relations
				res += "Entity type = : " + field.value().asNode().labels() + ", fields = "
						+ field.value().asNode().asMap() + "\n";
			}
		}

		tx.close();
		return res;
	}

	@Override
	public void exit() {
		session.close();
	}

	@Override
	public void delete(Integer id, Class<? extends Entity> entity) {
		String cmd = "MATCH (" + entity.getSimpleName().charAt(0) + ":" + entity.getSimpleName() + ") WHERE "
				+ getFirstLetter(entity) + ".id = " + id + " DETACH DELETE " + getFirstLetter(entity);

		try (Transaction tx = session.beginTransaction()) {
			tx.run(cmd);
			tx.commit();
			tx.close();
		}
	}

	@Override
	public void update(Integer id, Class<? extends Entity> entity, String fieldName, Object newValue) {
		String cmd = "MATCH (" + getFirstLetter(entity) + "{id: " + id + "})" + "SET " + getFirstLetter(entity) + "."
				+ fieldName + " = ";

		if (newValue instanceof String) {
			cmd += "'" + newValue + "'";
		} else {
			cmd += newValue;
		}

		try (Transaction tx = session.beginTransaction()) {
			tx.run(cmd);
			tx.commit();
			tx.close();
		}

	}

	private char getFirstLetter(Class<? extends Entity> entity) {
		return entity.getSimpleName().charAt(0);
	}

	@Override
	public void attach(Integer id1, Integer id2, Class<? extends Entity> entity1, Class<? extends Entity> entity2) {
		String cmd = "MATCH (" + getFirstLetter(entity1) + ":" + entity1.getSimpleName() + ") , ("
				+ getFirstLetter(entity2) + ":" + entity2.getSimpleName() + ") " + "WHERE " + getFirstLetter(entity1)
				+ ".id = " + id1 + " AND " + getFirstLetter(entity2) + ".id = " + id2 + " CREATE ("
				+ getFirstLetter(entity1) + ")-[r:IS_IN]->(" + getFirstLetter(entity2) + ") RETURN type(r)";

		try (Transaction tx = session.beginTransaction()) {
			tx.run(cmd);
			tx.commit();
			tx.close();
		}
	}

	@Override
	public String findAttached(Integer id, Class<? extends Entity> entity) {
		String res="";
		
		String cmd = "MATCH ("+getFirstLetter(entity)+":"+entity.getSimpleName()+")-[r]-(n) "
		+" WHERE "+getFirstLetter(entity)+".id = " + id 
		+" RETURN "+getFirstLetter(entity)+", r, n"; 
		
		Transaction tx = session.beginTransaction();
		Result result = tx.run(cmd);
		while(result.hasNext()) {
			Record record = result.next();
			List<Pair<String, Value>> fields = record.fields();
			for (Pair<String, Value> field : fields) {
				Value value = field.value();
				if(TYPE_SYSTEM.NODE().isTypeOf(value)) {
				res += "Entity type = : " + field.value().asNode().labels() + ", fields = "
						+ field.value().asNode().asMap() + "\n";
				}
				if(TYPE_SYSTEM.RELATIONSHIP().isTypeOf(value)) {
					res+="RELATIONSHIP = "+field.value().asRelationship().type() + "\n";
				}
			}
		}
		
		tx.close();
		return res;
	}

}
