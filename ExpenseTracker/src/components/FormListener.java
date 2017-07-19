package components;


import java.util.EventListener;
//listener which can be implemented as needed on each type of event.
public interface FormListener extends EventListener{
	public void formEventOccured(FormEvent e);
}