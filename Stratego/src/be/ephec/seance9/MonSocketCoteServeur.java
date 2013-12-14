package be.ephec.seance9;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MonSocketCoteServeur {
	
	private Socket s;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private int num; 
	public ObjectInputStream getOis() {
		return ois;
	}

	public void setOis(ObjectInputStream ois) {
		this.ois = ois;
	}

	public ObjectOutputStream getOos() {
		return oos;
	}

	public void setOos(ObjectOutputStream oos) {
		this.oos = oos;
	}

	public MonSocketCoteServeur(Socket s, int num) {
		
		try {
			this.s = s;
			this.num = num;
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	

}
