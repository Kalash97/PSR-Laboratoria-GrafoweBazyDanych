package com.actions.matchActions;

import com.actions.Action;
import com.entities.Match;
import com.persistenceManager.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowAllMatchesAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		String all = p.findAll(Match.class);
		cv.print(all);
	}

	@Override
	public String getName() {
		return "ShowAllMatches";
	}

}
