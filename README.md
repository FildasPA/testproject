# FlashQuiz

[Rapport pour le serveur](https://gist.github.com/FildasPA/b159860ab0877b423079701f133985b5)


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

```java
//---------------------------------------------------------------------------
// * Capitalize
//---------------------------------------------------------------------------
// Renvoie la chaîne de caractères envoyée par le client en majuscules.
//===========================================================================
public static void capitalize(ClientHandler client, String userInput)
{
		client.sendObject(userInput.toUpperCase());
}
```
