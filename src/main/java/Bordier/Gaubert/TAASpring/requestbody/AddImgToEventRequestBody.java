package Bordier.Gaubert.TAASpring.requestbody;


import Bordier.Gaubert.TAASpring.Events;

public class AddImgToEventRequestBody {
	private Events event;
	private byte[] img;
	
	public Events getEvent() {return event;}
	public byte[] getImg() {return img;}
}
