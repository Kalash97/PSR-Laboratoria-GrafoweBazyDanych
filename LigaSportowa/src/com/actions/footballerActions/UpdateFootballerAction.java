package com.actions.footballerActions;

import com.actions.Action;
import com.entities.Club;
import com.persistenceManager.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateFootballerAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Integer id = cv.getValidInt("podaj Id pi�karza");
		String name = cv.read("Podaj imi�");
		String lastName = cv.read("Podaj nazwisko");
		
		if(!"".equals(name)) {
			p.update(id, Club.class, "name", name);
		}
		
		if(!"".equals(lastName)) {
			p.update(id, Club.class, "lastName", lastName);
		}
	}

	@Override
	public String getName() {
		return "UpdateFootballer";
	}

}
