# WIP - Proposition pour les packages de l'application:

```cpp
src
├─── client
│    ├─── Client.java
│    ├─── controller
│    │    ├─── Edit.java            // EL
│    │    ├─── General.java         // Authentification, recherche session, stat utilisateur, sessions organisées
│    │    ├─── MainController.java
│    │    ├─── SessionMaster.java   // TVS
│    │    └─── SessionVoter.java    // TVS
│    ├─── view-android              // IG
│    │    └─── ...
│    └─── view-desktop              // IG
│         └─── ...
│
├─── lib
│    └─── net
│         ├─── Request.java
│         └─── SocketStreams.java
│
├─── model                          // Commun à EL et TVS
│    ├─── Answer.java
│    ├─── List.java
│    ├─── Media.java
│    ├─── Question.java
│    ├─── SessionBase.java
│    ├─── Session.java
│    ├─── User.java
│    ├─── UserAnswer.java
│    └─── ...
│
└─── server
     ├─── controller
     │    ├─── MainController.java
     │    ├─── SessionMaster.java    // TVS
     │    └─── SessionVoter.java     // TVS
     │
     ├─── lib
     │    ├─── SessionServer.java    // TVS
     │    └─── context
     │         ├─── ClientHandler.java
     │         └─── DB.java          // RES-BDD
     │
     ├─── model
     │    ├─── SessionServer.java    // TVS
     │    └─── table                 // RES-BDD
     │         ├─── AnswerTable.java
     │         ├─── ListTable.java
     │         ├─── QuestionTable.java
     │         ├─── SessionTable.java
     │         ├─── UserAnswerTable.java
     │         ├─── UserTable.java
     │         └─── ...
     │
     └─── Server.java
```
