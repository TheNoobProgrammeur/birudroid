#  Scénario 1 - Inscription de l'utilisateur

##  Description  : 
Inscription de l'utilisateur à l'application 

## Résultat attendu :
Création d'un compte utilisateur:
* création de l'utilisateur dans la base de données

## Déroulement :

1. L'utilisateur lance l'application
2. L'utilisateur arrive sur l'écran de connection
3. L'utilisateur va sur l'interface d'inscription en cliquant sur le bouton "Créer un compte"
4. L'utilisateur rentre les informations pour la création de son compte
5. L'application vérifie si tout les champs obligatoires sont renseignés 
6. L'application envoie les elements à firebase
7. L'API verifie si l'utilisateur n'est pas déjà inscrit
    * SUCCESS : l'utilisateur n'est pas déjà inscrit -> message succès inscription -> **étape 8**
    * ERROR : l'utilisateur est déjà inscrit -> message d'erreur inscription -> **étape 2**
8. L'application affiche la page principal
   

