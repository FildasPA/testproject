# FlashQuiz

__Compilation:__

```
make
```

__Exécution serveur:__

```
java -cp bin Serveur
```

__Exécution client(s):__

```
java -cp bin Client
```

---

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
		client.sendObject(userInput.toUpperCase());
	}
}
```
