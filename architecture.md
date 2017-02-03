# WIP - Proposition pour les packages de l'application

ça devrait fonctionner __exactement__ comme l'application de conception web... (Non je n'étais pas parti pour le recopier ^^)


```cpp
src
├─── client
│    ├─── Client.java
│    │
│    ├─── controller
│    │    ├─── Edit.java            // EL
│    │    ├─── General.java         // Authentification, recherche session, stat utilisateur, sessions organisées
│    │    ├─── MainController.java
│    │    ├─── SessionMaster.java   // TVS
│    │    └─── SessionVoter.java    // TVS
│    │
│    ├─── view-android              // IG
│    │    └─── ...
│    └─── view-desktop              // IG
│         └─── ...
│
├─── lib
│    ├─── Ansi.java                 // Couleurs :)
│    │
│    └─── net
│         ├─── ClientHandler.java
│         ├─── Clients.java
│         ├─── DB.java.java         // RES-BDD
│         ├─── Request.java
│         ├─── Server.java
│         └─── SocketStreams.java
│
└─── model                          // Commun à EL et TVS
     ├─── Answer.java
     ├─── List.java
     ├─── Media.java
     ├─── Question.java
     ├─── SessionBase.java
     ├─── Session.java
     ├─── User.java
     ├─── UserAnswer.java
     │
     ├─── AnswerTable.java          // RES-BDD s'occupe des requêtes ?
     ├─── ListTable.java
     ├─── QuestionTable.java
     ├─── SessionTable.java
     ├─── UserAnswerTable.java
     ├─── UserTable.java
     └─── ...
```
