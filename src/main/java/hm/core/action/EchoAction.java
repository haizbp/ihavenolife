package hm.core.action;

public class EchoAction extends Action{

	@Override
	public void doAction() {
		String echo = String.valueOf(getItem("text"));
		addItem("return", "Echo: "+echo);
	}

}
