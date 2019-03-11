package hm.core.action;

public class EchoAction extends Action{

	@Override
	public void doAction() {
		String echo = (String) getParam("text", getServerWebExchange().getRequest()).get(0);
		addViewAttribute("message", "Echo: "+echo);
	}

}
