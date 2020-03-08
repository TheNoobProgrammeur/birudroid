# Scenario 8 - Ajout d'une bière à ses favories

##  Description  : 
L'utilisateur veut ajouter une bière à ses favories

## Prérequis :

* [Scenario 2] - Connection

## Résultat attendu :

L'utilisateur ajoute une bière à ses favories

## Déroulement

1. L'utilisateur arrive sur l'écran de consultation des bières
2. L'utilisateur clique sur une bière 
3. L'application affiche la description de la bière
4. L'utlisateur clique sur un icon pour ajouter la bière a ses favories
5. L'application envoie une requête à l'API pour ajouter la bière à ses favories
    * SUCCESS: la bière est ajouté à la liste des favories de l'utilisateur -> message de succés d'ajout aux favories
    * ERROR: la bière n'a pas pu être ajouté aux favories de l'utilisateur -> message d'erreur de l'ajout aux favories