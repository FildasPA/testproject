# Notes

### Compilation

```
javac -sourcepath src/ -classpath bin/ -d bin/ src/*/*.java
```


### Exécution

#### 1. Server

```
java -cp bin Server
```


#### 2. Client

```
java -cp bin Client
```


Je ne sais pas s'il y a des gens de RES-BDD ici, mais je poste quand même...

J'en ai parlé à Renaud aujourd'hui, pour les tâches de mon groupe (TVS) de ce semestre, j'avais besoin de comprendre la manière de communiquer d'un serveur et d'une application cliente. En effet, on doit gérer le déroulement d'une session de vote, ce qui implique de gérer et synchroniser de multiples utilisateurs par l'intermédiaire d'un serveur. J'ai donc fait ce serveur et une application client pouvant communiquer avec... Et je me suis rendu compte après-coup que c'était une tâche qui correspondait plus à votre groupe (oui je me suis vraiment précipité, j'aurais dû d'abord vous demander, vous pouvez me traiter d'idiot :) ).

J'aimerais savoir comment vous voulez qu'on intègre ce travail (puisque ça fonctionne, et que ça répond aux besoins de mon groupe): si vous voulez éviter les embrouilles avec les profs, vous assigner cette tâche et prendre le code, ou si je l'intègre comme une tâche de mon groupe pour ce semestre et que je le push sur notre branche (pour le premier incrément par exemple).

Si vous voulez plus de détails, j'avais fait un rapport présentant ce que j'ai fait ici:
https://gist.github.com/FildasPA/b159860ab0877b423079701f133985b5

Un lien mène vers le code, et vous pouvez dl les fichiers pour tester.

Voilà, désolé du dérangement
