# FlashQuiz

__Compiler et exécuter:__

```
make
```

---

L'application peut jouer le rôle à la fois de serveur et de client:
Tapez `open-server 9090` ou `os`.
Dans un autre terminal, executez le programme puis:
```
join-server 9090
ou
js

capitalize Une chaîne de caractères
```

On reçoit:
```
UNE CHAÎNE DE CARACTÈRES
```

---

__Modèle type d'une action du contrôleur (MainController):__

```java
//---------------------------------------------------------------------------
// * Capitalize
//---------------------------------------------------------------------------
// Renvoie la chaîne de caractères envoyée par le client en majuscules.
//===========================================================================
public static void capitalize(String line)
{
	if(!checkRemoteServer()) return; // Vérifie si on est connecté à un serveur
	remoteServer.sendRequest("capitalize",(Object) line);
	String s = (String) remoteServer.getObject();
	System.out.println(s);
}
```

__Modèle type d'une action du contrôleur (ServerReplies):__

```java
//---------------------------------------------------------------------------
// * Capitalize
// Retourne la chaîne de caractères reçu en majuscules.
//---------------------------------------------------------------------------
public static void capitalize(ClientHandler client, String message)
{
	client.sendObject(message.toUpperCase());
}
```
Le serveur ne fait que renvoyer des objets (cela peut changer).
