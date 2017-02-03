# Notes Serveur/Client multithread (JB - TVS)

[Voir le code source correspondant à la tâche](https://gist.github.com/FildasPA/6fc7b16168a36055b4afb6b49ff1216f)

[Télécharger les fichiers](https://gist.github.com/FildasPA/6fc7b16168a36055b4afb6b49ff1216f/archive/a63acb3bd96b76a6a39dfeffa5dfca2156b3c385.zip)

(Si vous avez la flemme de tout lire et que vous comprenez les Socket et les Threads, vous pouvez vous contenter de voir [ce que fait le programme](#pour-réaliser-un-test) et de regarder directement le code)

## Sommaire

1. [Objet](#objet)
2. [Pour réaliser un test](#pour-réaliser-un-test)
3. [Fonctionnement](#fonctionnement)
4. [Explications plus complètes](#explications-plus-complètes)
5. [Intérêt](#intérêt)
6. [Notes générales](#notes-générales)
6. [Notes TVS](#notes-tvs)
7. [Tutoriel/sources](#tutorielssources)


## Objet

Note: cette tâche n'a pas encore d'identificateur.

Ce travail a été réalisé en prérequis de la tâche __Déroulement (TVS-16)__. Celle-ci gère la façon dont une session de vote se déroule.

Pour faire fonctionner FlashQuiz, nous avons besoin d'un serveur et d'une application cliente (dans notre cas, une appli PC et une Android). Une base, même sommaire, organisant la communication entre l'application et le serveur, est apparue comme un prérequis pour notre tâche.

Le but que je me suis fixé (en tant que membre du groupe TVS) était donc de réfléchir à l'organisation de cette structure serveur/client, et d'en faire une implémentation basique nous permettant de poursuivre nos tâches.

__Après l'avoir réalisé, c'est à dire un peu trop tard, je me suis rendu compte que cette tâche était plus du ressort du groupe RES-BDD que du miens. Cela est de ma faute, j'aurais dû faire une demande...__

Pour ne pas gaspiller ce travail, j'ai essayé dans ce document de vous en expliquer le fonctionnement, et j'ai fait quelques [propositions](#intérêt) quant à l'utilité de mon travail pour chaque groupe (donc à revoir en fonction de votre analyse).

__Je vois deux possibilités pour intégrer le travail: soit passer le code à RES-BDD, soit rattacher cette tâche à mon groupe. Je vous laisse en débattre.__

Gardez à l'esprit que ceci n'est qu'une base et peut donc tout à fait être modifié. Il reste d'ailleurs quelques fonctionnalités à ajouter. Je suis ouvert à toute proposition de modifications. Et indiquez-moi si vous trouvez des bugs!


## Pour réaliser un test

Il me paraît plus simple de commencer mes explications par un cas concret.

Compilation:

```
$javac Server.java Client.java
```

Démarrage du serveur:

```
$java Server
```

Dans un nouveau terminal, on lance l'application utilisateur. Le programme se connecte au serveur préalablement créé, et une invite de commande apparait:

```
$java Client
Bienvenue #0!
> toto
Envoyé: toto
Reçu:   TOTO
> Deux MoTs
Envoyé: Deux MoTs
Reçu:   DEUX MOTS
> q
Bye!
$
```

Le serveur peut gèrer plusieurs clients en même temps:

```
$java Client
Bienvenue #1!
> mdr
Envoyé: mdr
Reçu:   MDR
>
```

Dans un autre terminal:

```
$java Client
Bienvenue #2!
> bidule
Envoyé: bidule
Reçu:   BIDULE
>
```

## Fonctionnement

Il y a deux programmes exécutables: [Server](#server) et [Client](#client). Comme son nom l'indique, [Server](#server) est exécuté une seule fois sur le serveur. [Client](#client) est lancé par l'utilisateur (il s'agit bêtement de l'application FlashQuiz que l'on pourrait lancer à partir de son PC ou de son Android). Le serveur gère autant d'utilisateurs que nécessaires.

Lorsque [Client](#client) est exécuté, il établit une connexion avec le serveur. Il s'établit des flots d'entrée et de sortie de chaque côté, ce qui permet à [Client](#client) et à [Server](#server) de s'envoyer mutuellement des objets.

Comme vous l'aurez remarqué dans l'exemple, le client envoie au serveur une chaîne de caractères tapée par l'utilisateur, et le serveur lui renvoie cette chaîne en majuscules. L'application cliente et le serveur peuvent faire bien plus: interpréter ces commandes et effectuer les actions associées (comme par exemple se connecter en tant qu'utilisateur particulier (pseudo/mdp), ouvrir/rejoindre/... une session de vote etc.) Mais tout ceci reste à implémenter!


## Explications plus complètes

Voici maintenant une explication plus complète.

### Fichiers

1. [Server.java](https://gist.github.com/FildasPA/6fc7b16168a36055b4afb6b49ff1216f#file-1_server-java)
2. [ClientHandler.java](https://gist.github.com/FildasPA/6fc7b16168a36055b4afb6b49ff1216f#file-2_clienthandler-java)
3. [Client.java](https://gist.github.com/FildasPA/6fc7b16168a36055b4afb6b49ff1216f#file-3_client-java)
4. [SocketStreams.java](https://gist.github.com/FildasPA/6fc7b16168a36055b4afb6b49ff1216f#file-4_socketstreams-java)

### Organisation des classes

__Côté serveur__
- [Server](#server)
- [ClientHandler](#clienthandler)

__Côté client__
- [Client](#client)

__Des deux côtés__
- [SocketStreams](#socketstreams)

### Schéma

```txt
     Server
       │                                            COTE SERVEUR
       │
       │    always listening...
       │
       ├───────────────┬───── ... ─────┐            # Threads créés à chaque connexion établie
       │               │               │
       │               │               │
       │               │               │
  ClientHandler   ClientHandler   ClientHandler     # objets répertoriés dans le Map clients


       ^               ^               ^
       │               │               │
      Client          Client          Client        # applications clients
```

### Server

[Code](https://gist.github.com/FildasPA/6fc7b16168a36055b4afb6b49ff1216f#file-1_server-java)

Server se contente de créer un ServerSocket, qui permet d'établir des connexions avec d'autres appareils. La connexion n'étant pas du ressort de mon groupe, je ne m'y suis pas attardé et je me suis donc contenté de gérer des connexions en local. Mon objectif était plutôt de déterminer comment gérer l'utilisateur et les actions à effectuer, ce qui se fait principalement dans la classe suivante.

Pour finir avec Server, voici comment le serveur accepte de nouveaux clients: il boucle (ou écoute) (dans une boucle while infinie), et à chaque fois qu'un utilisateur ([Client](#client)) tente de se connecter, il crée un nouveau Socket (une nouvelle connexion) ainsi qu'un nouvel objet [ClientHandler](#clienthandler), qu'il exécute de manière asynchrone dans un nouveau Thread (thread = fil d'exécution, ça correspond à peu près à un fork).


### ClientHandler

[Code](https://gist.github.com/FildasPA/6fc7b16168a36055b4afb6b49ff1216f#file-2_clienthandler-java)

Un nouvel objet ClientHandler est créé à __chaque connexion__ au serveur.

#### Partie statique

La classe ClientHandler dispose de plusieurs variables et méthodes statiques. Elles permettent de partager des informations entres toutes les instances de ClientHandler.
- __Integer clientsNumber__ : correspond au nombre de clients qui se sont connectés depuis le démarrage du serveur
- __`Map<Integer,ClientHandler> clients`__ : la liste de tous les clients qui se sont connectés depuis le démarrage du serveur

Les maps sont initialisés à l'aide de la fonction statique __initialize__, appelée dans le main de Server. A noter:
- les entiers sont initialisés à 0
- les maps sont des objets ConcurrentHashMap. Cela permet (je ne l'ai pas testé) d'éviter les conflits lorsque l'objet se trouve appelé par plusieurs threads.

#### Partie objet

Le constructeur de ClientHandler reçoit en paramètre un socket, qui correspond à la connexion établie entre le serveur et le client. Chaque objet se voit attribué un numéro client, égal à clientsNumber (qui est ensuite incrémenté). Ensuite le constructeur ajoute l'objet [ClientHandler](#clienthandler) dans la liste des clients (variable statique clients), avec pour clé le numéro client attribué. Ainsi, on s'assure de bien identifier les clients connectés.

La classe ClientHandler implémente l'interface Runnable. Ceci permet, dès que Server démarre le Thread du nouveau client (clientThread.start()), d'exécuter la fonction run de ClientHandler. C'est là que l'on intéragit avec le client, que l'on peut envoyer/recevoir des objets, etc. Quand la  fonction run se termine (après que le client ai coupé la connexion par exemple), le thread correspondant s'arrête.


### Client

[Code](https://gist.github.com/FildasPA/6fc7b16168a36055b4afb6b49ff1216f#file-3_client-java)

L'application Client ne fait rien de compliqué. Premièrement, elle tente d'établir une connexion avec le serveur à l'aide d'un socket. Ensuite, elle boucle sur une saisie de chaîne de caractères à partir du clavier, qu'elle interprète. Dans l'exemple, si aucune commande ne correspond, elle envoie cette chaîne au serveur, qui lui renvoie la chaîne en majuscule.


### SocketStreams

[Code](https://gist.github.com/FildasPA/6fc7b16168a36055b4afb6b49ff1216f#file-4_socketstreams-java)

Les classes [ClientHandler](#clienthandler) et [Client](#client) créent toutes deux un objet SocketStreams. C'est à partir de cet objet que le client et le serveur peuvent s'échanger des données.

Le constructeur de cette classe reçoit en paramètre un socket, à partir duquel il créer des flots d'entrée et de sortie (outputStream et inputStream). Les fonctions sendObject et getObject permettent respectivement d'envoyer des données ou recevoir des données à partir des flots établis.


## Intérêt

Ces classes gèrent la communication client-serveur.

L'utilité que j'ai cru déceler pour chaque groupe:
- __RES-BDD__ : côté serveur, établir une connexion avec la base de données (à mettre en variable statique dans la classe ClientHandler).
- __EL__ : tester la création, l'édition, l'envoi et la réception d'objets tels que List, Question, etc.
- __IG__ : réfléchir, au niveau du client, à attendre un objet du serveur, à attendre ou annuler une saisie de la part de l'utilisateur (par exemple lors d'une session, le maître de session peut forcer le passage à la question suivante: la saisie d'un client n'ayant pas encore répondu doit donc être annulée), etc.
- __TVS__ : déroulement d'une session de vote, cad les intéractions de votants et d'un maître de session lors d'une session.

## TODO

La fonction sendObjet devrait se faire en asynchrone. Voir la classe java FutureTask. Je peux m'en charger.


## Notes générales

### Concernant les Threads côté serveur

Une bonne partie de la difficulté au niveau du serveur est de gérer les intéractions AVEC de multiples utilisateurs (le réseau est ici centralisé, les utilisateurs ne sont pas connectés entre eux). Ici, je l'ai fait en faisant fonctionner le serveur en multithread. Toutefois, un trop grand nombre de threads pourrait poser problème (ça dépend des capacités de la machine: elle peut gérer quelques centaines voire quelques milliers de threads en même temps). C'est d'autant plus important que, dans la base actuelle, des threads devront être créés pour autre chose que des gestionnaires clients, notamment pour vérifier l'envoi d'un objet de la part de l'application client (c'est-à-dire pour que la vérification du flot d'entrée ne bloque pas toutes les intéractions avec le client); je compte également attribuer un nouveau thread par session de vote en cours.

Ces intéractions Client/Serveur peuvent être gérés par d'autres moyens, comme des packages ou frameworks (j'en ai mis quelques liens plus bas) mais je n'ai pas pris le temps de bien les regarder, et encore moins de les comparer. Mon but était de créer une base de tests fonctionnelle, avec un nombre limité de clients connectés en même temps.


### Vérifier la liste des threads dans Server

Pour vérifier la liste des threads et leur état (en vie/terminé), ajouter
les lignes suivantes dans Server.java.
L'état d'un thread indique l'état de la connexion avec le client.

```java

// A mettre en attribut de classe
private static Map<Integer,Thread> clientsThreads;

// Dans la fonction listen, à la définition des variables:
clientsThreads = new ConcurrentHashMap<Integer,Thread>();
Thread client;

// Dans fonction listen, dans la boucle while, après avoir créé l'objet clientThread:
clients.put(clientHandler.getClientsNumber(),clientThread);
System.out.println("Liste des clients:");
System.out.println(clients.toString());
for(Integer i : clientsThreads.keySet()) {
  client = clients.get(i);
  System.out.println("Client #" + i + " is alive: " + client.isAlive());
}
```


## Notes TVS

### Concernant la session de vote (SessionServer)

[ClientHandler](#clienthandler) jouera le rôle d'intermédiaire entre la session de vote (SessionServer) et les sessions côté client (SessionMaster et SessionUser).

Ouvrir une nouvelle session de vote revient donc à:

__Client ouvrant la session:__
- __ClientHandler:__ créer un nouvel objet SessionServer, l'attribuer à un nouveau thread et l'insérer dans le map sessions. Le client est désigné chef de session.
- __Client:__ créer un nouvel objet SessionMaster.

__Client rejoignant la session:__
- __ClienHandler:__ lier le client (donc l'objet [ClientHandler](#clienthandler)) à la session désirée
- __Client:__ créer un nouvel objet SessionVoter.

Il faut bien comprendre qu'une SessionServer n'est pas dédiée à un seul client mais partagée avec tous les participants d'une même session de vote.
Le map SessionServer permet simplement d'avoir accès à la liste des sessions ouvertes ou en cours.


## Tutoriels/sources

Entre autres

### Général

- [Lesson: Socket Communications](http://www.oracle.com/technetwork/java/socket-140484.html)
- [Java Socket Programming Examples](http://cs.lmu.edu/~ray/notes/javanetexamples/)
- [Multithreading in Java](http://www.journaldev.com/1079/multithreading-in-java)
- [Stack Overflow - multiple client to server communication program](http://stackoverflow.com/questions/5419328/multiple-client-to-server-communication-program-in-java)
- [Stack Overflow - Listening to a socket with ObjectInputStream](http://stackoverflow.com/questions/7022063/java-listening-to-a-socket-with-objectinputstream)

### Remplacer le multithread

- [java.nio](https://docs.oracle.com/javase/7/docs/api/java/nio/package-summary.html) (exemple [ici](http://crunchify.com/java-nio-non-blocking-io-with-server-client-example-java-nio-bytebuffer-and-channels-selector-java-nio-vs-io/))
- [Stack Overflow - Asynchronous IO in Java](http://stackoverflow.com/questions/592303/asynchronous-io-in-java)
