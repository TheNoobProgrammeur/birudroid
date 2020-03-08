# Scenario 9 - Supprimer une bière de ses favories

##  Description  : 
L'utilisateur peut supprimer une bière de ses favories

## Prérequis :

* [Scenario 7] - Consultation des favories

## Résultat attendu :

La bière est supprimé de la liste des favories de l'utilisateur

## Déroulement

1. L'utilisateur arrive sur sa page de favorie
2. L'utilisateur clique sur l'icon favorie a côté de la bière qu'il veut supprimer de ses favories
3. L'application envoie une popup de confirmation de suppression de ses favories:
    - REFUSE: popup se ferme -> aucune action -> **etape 1**
    - ACCEPT: popup se ferme
5. L'application envoie une requête à l'API pour supprimer la bière de ses favories
    - SUCCESS: la bière est supprimée des favories -> message de succés -> **etape 1**
    - ERROR: la bière ne peut pas être supprimée des favories -> message d'erreur -> **etape 1** 