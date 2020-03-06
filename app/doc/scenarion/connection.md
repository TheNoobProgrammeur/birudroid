#  Scenario 2 - Connection du user

##  Description  : 
Connection de l'utilisateur à l'application 

## Prérequis 
* Scenario 1 - Creation d'un compte 

## Resultat attendu :
Conection d'un  utilisateur:

## Deroulement 

1. L'utilisateur lance l'application
2. application affiche l'interface de conection
3. L'utilisateur rentre les informations pour sa conection
4. L'application envoie les elements a  fierebase
5. L'fierebase  verifie si l'utilisateur est inscrit
    . ERREUR : l'utilisateur n'est pas inscrit :
        1. fierbase revenoie un message d'erreur
        2. l'application affiche une erreur
5. L'fierebase  verifie si l'utilisateur a mis le bon mdp
    * ERREUR : l'utilisateur est déjà inscrit :
        1. fierbase revenoie un message d'erreur
        2. l'application affiche une erreur
7. fierebase renvoie un sucer a l'application
8. fierebase envoie un token de conection a l'application 
9. l'application affiche la page principale

## Deroulement Alternatif :

### Cause :
* L'application a un token

1. L'utilisateur lance l'application
2. L'application trouve un token
3. L'application envoie le token a fierebase
4. fierebase verifie la validiter du token
5. Si token valide :
    * L'application affiche la page principale 
6. Sinon repris du senario principale a l'etape 2
