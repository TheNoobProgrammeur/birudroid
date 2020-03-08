# Scenario 10 - Noter & commenter une bière

##  Description  : 
L'utilisateur peut noter et commenter une bière 

## Prérequis :

* [Scenario 4] - Consultation d'une bière

## Résultat attendu :

La note et le commentaire de l'utilisateur est enregistrée

## Déroulement

1. L'utilisateur arrive sur la page de consultation d'une bière
2. L'utilisateur clique sur "donner un avis"
3. L'application affiche une popup ou l'utilisateur peut donner une note et mettre un commentaire
4. L'utilisateur clique sur l'étoile qui correspond à sa note grâce à un icon
5. L'utilisateur ajoute un commentaire grâce à une zone de texte
6. L'utilisateur enregistre son avis en cliquant sur "Poster"
7. L'application envoie une requête à l'api pour ajouter le commentaire et la note sur la bière correspondante
    - SUCCES: l'avis est ajouté à la bière -> message de succés -> **etape 8**
    - ERROR: l'avis ne peut pas être ajouté -> message d'erreur -> **etape 8**
8. La popup se ferme
