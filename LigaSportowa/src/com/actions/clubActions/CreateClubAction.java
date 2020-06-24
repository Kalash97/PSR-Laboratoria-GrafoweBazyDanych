package com.actions.clubActions;

import com.actions.Action;
import com.entities.Club;
import com.persistenceManager.Persistence;
import com.view.ConsoleView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateClubAction implements Action{

	private Persistence p;
	private ConsoleView cv;
	
	@Override
	public void launch() {
		Integer id = cv.getValidInt("podaj ID");
		
		cv.print("podaj nazwê");
		String name = cv.read();
		
		Club c = new Club();
		
		c.setId(id);
		c.setName(name);
		
		p.save(c);
	}

	@Override
	public String getName() {
		return "CreateClub";
	}

}
