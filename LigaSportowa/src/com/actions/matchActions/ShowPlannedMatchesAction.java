package com.actions.matchActions;

import com.actions.Action;
import com.entities.Match;
import com.persistenceManager.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowPlannedMatchesAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Integer id = cv.getValidInt("podaj id meczu");
		
		String attached = p.findAttached(id, Match.class);
		cv.print(attached);
		
	}

	@Override
	public String getName() {
		return "ShowPlannedMatches";
	}

}
