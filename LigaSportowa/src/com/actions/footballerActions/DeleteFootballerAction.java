package com.actions.footballerActions;

import com.actions.Action;
import com.entities.Footballer;
import com.persistenceManager.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteFootballerAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Integer id = cv.getValidInt("podaj ID pi³karza");
		p.delete(id, Footballer.class);
	}

	@Override
	public String getName() {
		return "DeleteFootballer";
	}

}
