package com.main;

import java.util.ArrayList;
import java.util.List;

import com.actions.Action;
import com.actions.AttachClubsToMatchAction;
import com.actions.AttachFootballerToClubAction;
import com.actions.ExitAction;
import com.actions.clubActions.CreateClubAction;
import com.actions.clubActions.DeleteClubAction;
import com.actions.clubActions.ShowFullClubInfoAction;
import com.actions.clubActions.ShowAllClubsAction;
import com.actions.clubActions.UpdateClubAction;
import com.actions.footballerActions.CreateFootballerAction;
import com.actions.footballerActions.DeleteFootballerAction;
import com.actions.footballerActions.ShowAllFootballersAction;
import com.actions.footballerActions.UpdateFootballerAction;
import com.actions.matchActions.CreateMatchAction;
import com.actions.matchActions.DeleteMatchAction;
import com.actions.matchActions.ShowAllMatchesAction;
import com.actions.matchActions.ShowPlannedMatchesAction;
import com.persistenceManager.Neo4jPersistence;
import com.persistenceManager.Persistence;
import com.view.ConsoleView;

public class Main {

	private static List<Action> actions;
	private static ConsoleView cv;
	
	public static void main(String[] args) {
		
		init();
		
		while (true) {
			cv.print("Lista dostępnych akcji:");
			showActions();
			cv.print("");
			cv.print("Podaj akcję");
			runAction(cv.read());
		}
		
	}
	
	public static void init() {
		
		actions = new ArrayList<Action>();
		cv = new ConsoleView();
		
		Persistence p = new Neo4jPersistence();

		actions.add(new CreateClubAction(p, cv));
		actions.add(new ShowAllClubsAction(p, cv));
		actions.add(new UpdateClubAction(p, cv));
		actions.add(new DeleteClubAction(p, cv));
		
		actions.add(new CreateFootballerAction(p, cv));
		actions.add(new ShowAllFootballersAction(p, cv));
		actions.add(new UpdateFootballerAction(p, cv));
		actions.add(new DeleteFootballerAction(p, cv));
		
		actions.add(new CreateMatchAction(p, cv));
		actions.add(new DeleteMatchAction(p, cv));
		actions.add(new ShowAllMatchesAction(p, cv));
		actions.add(new ShowPlannedMatchesAction(p, cv));
		
		actions.add(new AttachClubsToMatchAction(p, cv));
		actions.add(new AttachFootballerToClubAction(p, cv));
		actions.add(new ShowFullClubInfoAction(p, cv));	
		
		actions.add(new ExitAction(p, cv));
		
	}
	
	private static void runAction(String name) {
		for (Action a : actions) {
			if (name.equals(a.getName())) {
				launchAction(a);
				return;
			}
		}
		cv.print("Nie ma takiej akcji: " + name);
	}
	
	private static void launchAction(Action a) {
			a.launch();
	}
	
	private static void showActions() {
		for (Action a : actions) {
			cv.print(" " + a.getName());
		}
	}
}
