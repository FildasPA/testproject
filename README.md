# FlashQuiz

[Rapport pour le serveur](https://gist.github.com/FildasPA/b159860ab0877b423079701f133985b5)


__Compiler et exécuter serveur (à exécuter en premier) :__

```
make Serveur
```

__Compiler et exécuter client :__

```
make Client
```

__(Tout compiler, mais ne rien exécuter :)__

```
make
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
