package Bordier.Gaubert.TAASpring.requestbody;

import Bordier.Gaubert.TAASpring.Events;
import Bordier.Gaubert.TAASpring.User;

public class FollowEventRequestBody {
	private User user;
	private Events event;
	
	public User getUser() {return user;}
	public Events getEvent() {return event;}
}
