package console;
import java.io.FileNotFoundException;
import java.text.ParseException;

import javax.swing.SwingUtilities;

public class App {
public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable(){

	//creating instance of frames in run method.		
			public void run() {
				try {
					new MainFrames();
				} catch (FileNotFoundException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
			
		});
		
		

	}

}
