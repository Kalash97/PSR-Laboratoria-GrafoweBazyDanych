package com.actions.matchActions;

import com.actions.Action;
import com.entities.Match;
import com.persistenceManager.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteMatchAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Integer id = cv.getValidInt("podaj ID meczu");
		p.delete(id, Match.class);
	}

	@Override
	public String getName() {
		return "DeleteMatch";
	}

}
