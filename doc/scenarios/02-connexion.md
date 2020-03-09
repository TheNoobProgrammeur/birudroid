#  Scenario 2 - Connexion d'un utilisateur

##  Description  : 
Connection de l'utilisateur à l'application 

## Prérequis :
* Scenario 1 - Creation d'un compte 

## Résultat attendu :
Connection d'un utilisateur

## Déroulement :

1. L'utilisateur lance l'application
2. L'utilisateur arrive sur l'écran de connection
3. L'utilisateur rentre ses informations de connection
4. L'utilisateur appuie sur le bouton "Connexion"
5. L'application envoie les elements à l'API
6. L'API verifie les informations de connexion
    * SUCCESS : les informations de connexion sont bonnes -> l'API renvoie un succés -> message de succés -> **étape 7**
    * ERROR : les informations de connexion sont mauvaises -> l'API renvoie une erreur -> message d'erreur -> **étape 2**
7. L'API envoie un token de conection à l'application 
8. L'application affiche la page principale

### Cause :
L'application a un token

1. L'utilisateur lance l'application
2. L'application trouve un token
3. L'application envoie le token à l'API
4. L'API verifie la validité du token
    * SUCCESS: **étape 8**
    * ERROR: **étape 2**
