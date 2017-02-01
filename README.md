# FlashQuiz

Remarque:
contrairement au projet de conception web, les contrôleurs dans server sont instanciables (sauf Main)

Question:
Les contrôleurs SessionTruc, Edit, ... doivent-ils êtres instanciables ou non?


Modèle type d'une action d'un contrôleur:

```
//---------------------------------------------------------------------------
// * Capitalize
//---------------------------------------------------------------------------
// Renvoie la chaîne de caractères envoyée par le client en majuscules.
//---------------------------------------------------------------------------
// Note: Cette fonction est définie par la classe Runnable. Elle est appelée
// lorsque l'on démarre le Thread du client (cad clientThread.start() dans
// Server.java).
//===========================================================================
public static void capitalize(ClientHandler client, Object object) {
	public void run()
	{
		// Pour Server : On peut utiliser les classes context! (Sessions & Clients)
		String userInput;
		userInput = (String) client.getObject();
		client.sendObject(userInput.toUpperCase());
	}
}
```
