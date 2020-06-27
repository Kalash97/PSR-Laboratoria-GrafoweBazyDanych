package com.actions.footballerActions;

import com.actions.Action;
import com.entities.Footballer;
import com.persistenceManager.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowAllFootballersAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		String all = p.findAll(Footballer.class);
		cv.print(all);
	}

	@Override
	public String getName() {
		return "ShowAllFootballers";
	}

}
