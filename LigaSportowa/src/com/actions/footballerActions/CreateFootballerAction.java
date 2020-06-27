package com.actions.footballerActions;

import com.actions.Action;
import com.entities.Footballer;
import com.persistenceManager.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateFootballerAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Integer id = cv.getValidInt("podaj ID");
		String name = cv.read("Podaj imiê");
		String lastName = cv.read("podaj nazwisko");
		
		Footballer f = new Footballer();
		
		f.setId(id);
		f.setName(name);
		f.setLastName(lastName);
		
		p.save(f);
	}

	@Override
	public String getName() {
		return "CreateFootballer";
	}

}
