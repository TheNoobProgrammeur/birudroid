#  Scenario 1 - Inscription du user

##  Description  : 
Inscription de l'utilisateur à l'application 

## Resultat attendu :
Création d'un compte utilisateur:
* création du utilisateur dans la base de donner

## Deroulement 

1. L'utilisateur lance l'application
2. L'utilisateur va sur l'interface d'inscription
3. L'utilisateur rentre les informations pour la création de son compte
4. L'application envoie les element a  fierebase
5. L'fierebase  verifie si l'utilisateur n'est pas inscrit
    * ERREUR : l'utilisateur est déjà inscrit :
        1. fierbase revenoie un message d'erreur
        2. l'application affiche une erreur
6. fierebase renvoie un sucer a l'application
7. l'application affiche la page principale
   

