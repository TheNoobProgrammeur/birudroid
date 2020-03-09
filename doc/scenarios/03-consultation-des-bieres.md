# Scenario 3 - Visualisation de la liste des bières

##  Description  : 

Consultation de l'ensemble des bières disponible dans l'aplication

## Résultat attendu :

Affichage de la liste des bières disponible

## Déroulement :

1. L'utilisateur arrive sur l'écran de consultation des bières
2. Les bières présentent en cache dans l'application s'affiche
3. L'application envoie une requête à l'API pour demander x bières(afin d'éviter des requêtes trop lourdes)
    * IN PROGRESS: l'application affiche une barre de chargement
    * SUCCESS: l'application renvoie les x premières bières
    * ERROR: l'application n'arrive pas a envoyer la requête -> message d'erreur
3. L'application traite la reponse
4. L'application affichage les bières correspondant à la réponse
5. L'utilisateur descend pour visualiser les bières
6. L'utilisateur arrive à la fin des bières chargées dans l'application -> **étape 3**
