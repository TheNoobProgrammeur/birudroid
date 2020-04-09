# Scenario 11 - Ajouter une bière à l'application

##  Description  : 

Ajouter une bière dans l'application avec tout les informations nécessaires pour la bière

## Prérequis :

* [Scenario 2]- Connection

## Résultat attendu :

La bière est ajouté à l'application 

## Déroulement :

1. L'utilisateur arrive sur l'écran de consultation des bières
2. L'utilisateur clique sur l'icon ajouter une bière présent dans sa toolbar
3. L'utilisateur arrive sur le formulaire d'ajout avec les champs nécessaires à l'ajout d'une bière(Voir ci-dessous **Informations pour l'ajout d'une bière**)
4. L'utilisateur rempli les champs nécessaire à l'ajout d'une bière
5. L'utilisateur clique sur le bouton enregister
6. L'application envoie une requête à l'API pour ajouter une bière
    - SUCCESS: la bière est ajouter -> message de succés -> **etape 1**
    - ERROR: la bière ne peut pas être ajouté -> message d'erreur -> **etape 1**

## Déroulement particulier
Ajout d'une marque:
L'utilisateur dispose d'un menu déroulant contenant toutes les marques déjà présente dans l'application,
si l'utilisateur ne trouve pas la marque qu'il cherche il peut en ajouter une nouvelle.
 
## Informations nécessaire à l'ajout d'une bière :

### Required fields:
- nom
- type (Blonde, IPA, Brune)
- description
- images
- degrès

## optional fields
- brasserie
- marque

