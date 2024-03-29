package transport;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import manager.Account_manager;
import manager.DB_locale_manager;
import manager.Network_manager;
import model.Message;
import model.User;
import view.ChatWindow;
import view.NotificationPseudo;


public class Traitement_Messages implements Runnable {

	Socket clientSocket ;
	ServerSocket serverSocket ;
	TCP serverTCP ; 
	
	
	public Traitement_Messages(Socket client, ServerSocket serverSocket, TCP serverTCP) {
		this.clientSocket = client ;
		this.serverSocket = serverSocket ;
		this.serverTCP = serverTCP ; 
	}

	public static void differencier_msg(Message msg) throws UnknownHostException, IOException {
		Message.TypeMessage tmsg = msg.getType();
		
		switch(tmsg){
		
		case MESSAGE_CONV :
			Network_manager.message_recu(msg);
			break;
		
		case CONNECTE :
			System.out.println("[Traitement_mess] dans CONNECTE");

			if (msg.getSender().getId() == User.getMyId()) {
				System.out.println("[MESSAGE] je ne renvoie pas le pseudo car c'est moi "+User.getMyId());
			} 
			else {
				DB_locale_manager.add_user_annuaire(msg.getSender().getId(),msg.getSender().getPseudo(),1,msg.getSender().getIp());
				Message nmsg = new Message(User.getMoi(),"", Message.TypeMessage.RENVOIE_PSEUDO);
				TCP.envoyer_msg_tcp(msg.getSender(), nmsg);
				System.out.println("[MESSAGE] envoie du message -> ok bienvenue, voici mon pseudo, tu peux m'ajouter"+User.getMyId());
				if (ChatWindow.getInstance()==null){
					System.out.println("[Traitement_mes CONNECTE] getinstance null");
				} else {
					System.out.println("[Traitement_mes CONNECTE] getinstance : "+ChatWindow.getInstance());
					ChatWindow.afficher_tous_les_contacts(ChatWindow.getInstance());

				}

			}
			System.out.println("[Traitement_mess] fin CONNECTE");
			//Objects.requireNonNull(ChatWindow.get_contact_item_by_user(msg.getSender().getId())).setBStyle(1);
			break;

		case DECONNECTE :
			//Objects.requireNonNull(ChatWindow.get_contact_item_by_user(msg.getSender().getId())).setBStyle(0);
			//ChatWindow.afficher_tous_les_contacts(ChatWindow.getInstance());
			if (ChatWindow.getInstance()==null){
				System.out.println("[Traitement_mes DECONNECTE] getinstance null");
			} else {
				System.out.println("[Traitement_mes DECONNECTE] getinstance : "+ChatWindow.getInstance());
				ChatWindow.afficher_tous_les_contacts(ChatWindow.getInstance());

			}
			Network_manager.deconnection();
			DB_locale_manager.deconnecter_user(msg.getSender().getId());
			break;
			
		case RENVOIE_PSEUDO :
			System.out.println("[Traitement_mess] dans RENVOIE_PSEUDO");
			DB_locale_manager.add_user_annuaire(msg.getSender().getId(),msg.getSender().getPseudo(),1,msg.getSender().getIp());
			if (ChatWindow.getInstance()==null){
				System.out.println("[Traitement_mes RENVOIE_PSEUDO] getinstance null");
			} else {
				System.out.println("[Traitement_mes RENVOIE_PSEUDO] getinstance : "+ChatWindow.getInstance());
				ChatWindow.afficher_tous_les_contacts(ChatWindow.getInstance());

			}
			System.out.println("[Traitement_mess] fin RENVOIE_PSEUDO");

			break;
			
		case CHANGE_PSEUDO :
			//DB_locale_manager.maj_pseudo(msg.getSender().getPseudo(), msg.getSender().getId());
			DB_locale_manager.update_pseudo_annuaire(msg.getSender().getPseudo(),msg.getSender().getId());
			ChatWindow.handle_notification_pseudo_change(msg.getContenu(),msg.getSender());
			NotificationPseudo.setNew_contact(msg.getSender());
			if (ChatWindow.getInstance()==null){
				System.out.println("[Traitement_mes CHANGE_PSEUDO] getinstance null");
			} else {
				System.out.println("[Traitement_mes CHANGE_PSEUDO] getinstance : "+ChatWindow.getInstance());
				ChatWindow.afficher_tous_les_contacts(ChatWindow.getInstance());

			}
			break;
			
		case PSEUDO_DISPO:
			String pseudo = msg.getContenu();
			String Monpseudo = User.getMoi().getPseudo();
			if (pseudo.equals(Monpseudo)) {
				Account_manager.pseudoPasDispo();
			}
			else { }
			break;
	
		}
	}

	public void run() { //quand le thread recoit quelque chose 
		BufferedReader input ;
		
		try {
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String recu = input.readLine();
			
			Message msg = Message.deconstruire_message(recu, User.getMoi());
			differencier_msg(msg);
			
			input.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}


}
