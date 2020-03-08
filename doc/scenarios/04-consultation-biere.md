# Scenario 4 - Consultation de la fiche d'une bière

##  Description  : 

Consultation de la fiche descriptive d'une bière

## Prérequis :

* [Scenario 2]- Connection

## Résultat attendu :

Affichage de la fiche d'une bière

## Déroulement :

1. L'utilisateur arrive sur l'écran de consultation des bières
2. L'utilisateur clique sur une bière présente dans la liste des bières de l'application
3. L'utilisateur arrive sur la page de consultation d'une bière
4. L'application envoie une requête à l'API pour récupérer la fiche de la bière
    - SUCCESS: L'API renvoie la fiche de la bière -> **etape 5**
    - ERROR: L'API ne trouve pas la fiche de la bière -> message d'erreur -> **etape 1**
5. L'application affiche la fiche descriptive de la bière
