package be.ephec.seance9;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MonServeur extends ServerSocket {
	private static int numPort=2013; 
	private ArrayList<MonSocketCoteServeur> listeClients = new ArrayList<MonSocketCoteServeur>();

	public MonServeur() throws IOException {
		super(numPort);

	}

	public MonServeur(int arg0) throws IOException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}



	public static void main(String[] args) {
		do{
			try {
				MonServeur ms= new MonServeur();
				System.out.println("le serveur tourne sur le port : "+numPort);
				
				do{
					Socket s = ms.accept();
					ms.listeClients.add(new MonSocketCoteServeur(s,ms.listeClients.size()+1));
					ms.listeClients.get(ms.listeClients.size()-1).getOos().writeObject("Bienvenue sur le serveur");
					ms.listeClients.get(ms.listeClients.size()-1).getOos().writeObject(ms.listeClients.size());
					for (MonSocketCoteServeur mscs : ms.listeClients) {
						mscs.getOos().writeObject("Nouveau client(num= "+ms.listeClients.size()+ ") sur le serveur");
					
					}
					
				}while(!ms.isClosed());
			} catch (IOException e) {
				e.printStackTrace();
			}

			break;
		}while (true);

	}
}
