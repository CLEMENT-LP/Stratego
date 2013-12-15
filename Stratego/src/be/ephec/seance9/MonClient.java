package be.ephec.seance9;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.UnknownHostException;

public class MonClient extends Socket implements Runnable {
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private int num;


	public MonClient() throws UnknownHostException,
	IOException {
		super("127.0.0.1",2013);
		oos = new ObjectOutputStream(this.getOutputStream());
		ois = new ObjectInputStream(this.getInputStream());
	}


	public MonClient(String host, int port)throws UnknownHostException,
	IOException  {
		super(host, port);
		oos = new ObjectOutputStream(this.getOutputStream());
		ois = new ObjectInputStream(this.getInputStream());
	}



	public static void main(String[] args) {
		MonClient mc = null;
		try {

			mc = new MonClient();
			String s = (String)mc.ois.readObject();
			System.out.println("le client a reçu: "+s);
			mc.num =(int) mc.ois.readObject();
			System.out.println("ceci est la console du client numéro "+mc.num);

			Thread t = new Thread(mc);
			t.start();//exécute dans un proccesus en parallele
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}




		} catch (UnknownHostException e) {
			e.printStackTrace();
			try {
				mc.close();
				System.out.println("Le socket est fermé");
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

	}


	@Override
	public void run() {
		Livre s;
		while(!this.isClosed()){ // lecture bloquante 

			try {
				s= (Livre)this.ois.readObject();
				System.out.println("J'ai reçu : "+s);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				try {
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}


		}

	}

}
