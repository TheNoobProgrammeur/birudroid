# Scenario - 3 : consultation des bières

##  Description  : 
Consultation de l'ensemble des bières disponible dans l'aplication

## Prérequis 
* Scenario 2 - Connection

## Resultat attendu :
Affichage de la liste des bières disponible

## Deroulement 
1. L'utilisateur effectue le [Scenario 2](https://github.com/TheNoobProgrammeur/birudroid/blob/master/app/doc/scenarion/connection.md)
2. L'application envoie une req a fierebase pour demander les x premier bières 
    * L'application ne demande que x bières pour ne pas surcharger l'affichage
    * Erreur - L'application n'arrive pas a envoyé la requéte :
      1. l'application affiche un message d'erreur
3. fierebase envoie les x premier bières
4. l'application traite la reponse et gére l'affichage des bières
5. l'utilisateur peux voir les biére
6. l'utilisateur peux desencdre et delmander les x prochaines bières
