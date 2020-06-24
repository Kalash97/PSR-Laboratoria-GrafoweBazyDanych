package com.actions.clubActions;

import com.actions.Action;
import com.entities.Club;
import com.persistenceManager.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowAllClubsAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		String all = p.findAll(Club.class);
		cv.print(all);
	}

	@Override
	public String getName() {
		return "ShowAllClubs";
	}

}
