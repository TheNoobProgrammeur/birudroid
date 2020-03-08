# Scenario 6 - Filtrer des bières

##  Description  : 
L'utilisateur à besoin de filtere les bières en fonction de ses goûts, pour cela l'application lui propose un système de filtre

## Prérequis :

* [Scenario 2] - Connection

## Résultat attendu :

L'application affiche les bières correspondant aux filtres appliqués

## Déroulement

1. L'utilisateur arrive sur l'écran de consultation des bières
2. L'utilisateur clique sur le bouton ou l'icon "Filtrer"
3. L'application affiche un écran de filtrage avec les différentes caractèristiques d'une bière(Voir **filtre** ci-dessous)
4. L'utilisateur séléctionne les filtres qu'il souhaite
5. L'utilisateur clique sur le bouton "Filtrer"
6. L'application envoie une requête à l'API pour trouver les bières correspondant aux filtres
7. L'API envoie une liste de bières à l'application
8. L'application affiche les bières retournées par l'API

## Filtre :

- nom
- type
- brasserie
- degrès 