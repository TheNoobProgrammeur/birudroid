#  Scenario 2 - Connexion d'un utilisateur

##  Description  : 
Connexion de l'utilisateur à l'application 

## Prérequis :
* Scenario 1 - Creation d'un compte 

## Résultat attendu :
Connexion d'un utilisateur

## Déroulement :

1. L'utilisateur lance l'application
2. L'utilisateur arrive sur l'écran de la liste des bières
3. L'utilisateur clique sur le bouton se connecter
4. L'utilisateur arrive sur l'écran de connexion
5. L'utilisateur à le choix entre se connecter avec son compte Google ou avec une adresse mail
    * Avec Google:
        1. L'utilisateur clique sur "connexion avec Google"
        2. Firebase verifie les informations de connexion:
            * SUCCESS: message succès inscription -> **étape 6**
            * ERROR: message d'erreur inscription -> **étape 4**
    * Avec adresse mail: 
        1. L'utilisateur clique sur "connexion avec une adresse mail"
        2. L'utilisateur renseigne son adresse mail
        3. Deux cas de figure:
            - L'adresse mail est utilisé:
                1. L'utilisateur renseigne un mot de passe
                2. L'utilisateur clique sur "Connexion":
                    * SUCCESS: message succès connexion -> **étape 6**
                    * ERROR: message d'erreur connexion -> **étape 4**
            - L'addresse mail n'est pas reconnu: 
                1. L'utilisateur renseigne un nom d'utilisateur
                2. L'utilisateur renseigne un mot de passe
                3. L'utilisateur clique sur "Connexion":
                    * SUCCESS: message succès inscription -> **étape 6**
                    * ERROR: message d'erreur inscription -> **étape 4**
6. L'application affiche la page principale

### Déroulement alternatif :
L'application a déjà un utilisateur de connecté

1. L'utilisateur lance l'application
2. L'application trouve un utilisateur en mémoire
3. L'application affiche la page principale
