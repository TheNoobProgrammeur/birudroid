# :fire: Spécifications de la base de données Firebase :fire:

## :bow_and_arrow:  Objectifes de la BDD :bow_and_arrow:

La base de donnée aura pour but de sauvegarder les données suivantes :
* Les utilisateurs
* Les bières
* Les commentaires
* Les marques de bière
* Les favories d'un utilisateur
* Les votes des utilisateurs

Pour ce projet nous avons choisi d'utiliser Firebase.  
Nous utiliserons le service d'authentification intégré à Firebase.  
Cela nous permettra de ne pas avoir à gérer l'identifications des utilisateurs.

La base de données Firebase est une base de données à plat(NoSQL).  
Les collections seront donc représentées en `Json`.

:warning:

_Les exemples sont donnés à titre indicatif,
il peut y avoir des changements entre les exemples et l'implémentation_

_Dans les exemples qui suivront :_
* _uidx est un id utilisateur_
* _bidx est un id d'une biere_
* _bridx est un id d'une marque de biere_
* _cidx est un id de commentaire_ 

## Utilisateur :person_with_blond_hair:

Chaque utilisateur authentifié a uid unique généré par Firebase.
Les utilsateurs seront référencées par un uid. 
Informations liées à un utilisateur:

* nom d'utilisateur 
* mail

### Schéma de la collection **users**

```json
{
  "users":{
    "type": "object",
    "patternProperties":{
      "[0-9A-z]{28}": {
        "type": "object",
        "properties":{
          "username": { "type" : "string" },
          "email": { "type" : "string" },
        },
        "additionalProperties": false
      }
    },
    "required": ["username", "email"]
  }
}
```

### Exemple :

```json
{
  "users":{
    "uid1":{
      "username": "toto", 
      "email": "toto@toto.gouv",
    },
    "uid2":{}
  }
}
```

## Bières :beer:

Cette collection ne contient pas toutes les informations liées à une bière(Voir collection **beer-detail**).
Les bières seront référencées par un uuid.  
Informations liées à une bière:
  * nom de la bière
  * type de bière (Ex: Brune, Blonde, IPA...)
  * degré d'alcool
  * moyenne des notes données par la communauté
 

### Schéma de la collection **beers**

```json
{
  "beers":{
    "type": "object",
    "patternProperties":{
      "[0-9A-z]{28}": {
        "type": "object",
        "properties":{
          "name": { "type" : "string" },
          "typeBeer": { "type" : "string" },
          "degree": {
            "type": "number",
            "minimum": 0
          },
          "average": { 
            "type" : "number",
            "minimum": 0,
            "maximum": 5
          },
        },
        "additionalProperties": false
      }
    },
    "required": ["name", "type", "description","degree"]
    }
}
```

### Exemple :

```json
{
  "beers":{
    "bid1":{
      "name": "la bête",
      "type": "tid2",
      "degree": 8,
      "average": 2.5,
    },
    "bid2": {}
  }
}
```

## Bières détaillées :beer:

Les bières seront référencées par un uuid.  
Informations liées à une bière détaillées:
  * nom de la bière
  * type de bière (Ex: Brune, Blonde, IPA...)
  * descrition
  * degré d'alcool
  * moyenne des notes données par la communauté
  * nombre de votant
  * marque de la bière (nom et description)
  * information sur là où a été brassé la bière

Dans un premier temps les commentaires seront présent sans possibilité de réponse, aucun commentaire sera liée à un autre. 

### Schéma de la collection **beers-detail**

```json
{
  "beers-detail":{
    "type": "object",
    "patternProperties":{
      "[0-9A-z]{28}": {
        "type": "object",
        "properties":{
          "name": { "type" : "string" },
          "typeBeer": { "type" : "string" },
          "description": { "type" : "string" },
          "degree": {
            "type": "number",
            "minimum": 0
          },
          "average": { 
            "type" : "number",
            "minimum": 0,
            "maximum": 5
          },
          "nbRate":{
            "type" : "integer",
            "minimum" : 0
          },
          "brand": { 
            "type":"object",
            "properties":{
              "id": {"type" : "string"},
              "name": { "type" : "string" },
              "description": { "type" : "string" },
            }
          },
          "brewery:":{
            "type" : "object",
            "properties": {
              "name": { "type" : "string" },
              "address": { "type" : "string" },
              "creationDate": { 
                  "type" : "string",
                  "format": "date-time" 
              },
            } 
          }
        },
        "additionalProperties": false
      }
    },
    "required": ["name", "type", "description","degree"]
    }
}
```

### Exemple :

```json
{
  "beers":{
    "bid1":{
      "name": "la bête",
      "type": "tid2",
      "description": "TODO",
      "degree": 8,
      "average": 2.5,
      "nbRate": 5,
      "brandId": {
        "id": "brid1",
        "name": "Delirium",
        "description": "L'éléphant alcoolique"
      },
      "brewery": {
        "name": "brasserie",
        "adress": "12 rue du Lys",
        "creationDate": "01-01-2001"
      }
    },
    "bid2": {}
  }
}
```

## Commentaires sur une bière 💬

Pour éviter une trop grande surcharge lors de l'importation d'une bière nous avons desidé que les commentaires seront dans une collection propre.

Les commentaires seront référencées par un bid.  
Informations liées à un commentaire:

* username de la personne ayant laissé le commentaire
* message
* date de crétion

### Schéma de la collection **comments**

```json
{
  "comments":{
    "type":"objet",
    "patternProperties":{
      "[0-9A-z]{28}":{
        "type": "array",
        "items": {
          "type": "object",
          "properties":{
            "username" :{ 
              "type":"string",
              "pattern" : "[0-9A-z]{28}" 
            },
            "message":{
              "type":"string"
            },
            "creationDate": { 
              "type" : "string",
              "format": "date-time" 
            },
          }
        }
      }
    }
  }
}

```

### Exemple 

```json
{
  "comment":{
    "bid1":[
      {
      "username":"paolito",
      "message":"nul",
      "creationDate":"11-08-2020"
      },
      {
      "username":"jeannine",
      "message":"pas mal",
      "creationDate":"11-01-2020"
      },
    ]
  }
}
```


## Bières favorites d'un utilisateur 🌟

Les infos minimales des bières favorites d'un utilsateur seront renseigné dans cette collection.

Les listes de bières favorites seront référencées par un uid. 
Chaque uid aura une liste de bière comme valeur, correpondant aux bières favorites que l'utisateur(uid) aura séléctionné.

### Schéma de la collection **user-fav-beer**

```json
{
  "user-fav-beer":{
    "type":"objet",
    "patternProperties":{
      "[0-9A-z]{28}":{
        "type": "array",
        "items": {
          "type": "object",
          "properties":{
            "name": { "type" : "string" },
            "typeBeer": { "type" : "string" },
            "degree": {
              "type": "number",
              "minimum": 0
            },
            "average": { 
              "type" : "number",
              "minimum": 0,
              "maximum": 5
            },
          },
          "additionalProperties": false
        },
        "required": ["name", "type", "description","degree"]
      }
    }
  }
}

```

### Exemple 

```json
{
  "favoriteBeers":{
    "uid1":[
      {
        "id": "bid1",
        "name": "la bête",
        "type": "tid2",
        "degree": 8,
        "average": 2.5,
      },
      {}
    ]
  }
}
```

## Votes d'un utilisateur 🗳️

Les votes de chaque utilisateur seront renseigné dans cette collection

Les listes de votes seront référencées par un uid.
Les listes comporteront des objects ayant pour propriété:
- id de bière
- note

### Schéma de la collection **user-rating**

```json
{
  "user-rating":{
    "type":"objet",
    "patternProperties":{
      "[0-9A-z]{28}":{
        "type": "array",
        "items": {
          "type": "object",
          "properties":{
            "idBeer": { "type" : "string" },
            "rate": {
              "type": "number",
              "minimum": 0
            }
          },
          "additionalProperties": false
        },
        "required": ["name", "type", "description","degree"]
      }
    }
  }
}

```

### Exemple 

```json
{
  "user-rating":{
    "uid1":[
      {
        "idBeer":"bid1",
        "rate": "5"
      }
    ]
  }
}
```

## Marque de la bière :copyright:

### Schéma de la collection **Brands**

```json
{
  "brands": { 
    "type": "object",
    "patternProperties":{
        "[0-9A-z]{28}":{
            "properties": {
                "name": { "type": "string"},
                "description": { "type" : "string"}
            },
            "required":["name", "description"]
        }
    }
  }
}
```

#### Exemple :

```json
{
  "brand":{
      "brid1":{
          "name":"Delirium",
          "description":"Bières de caractères"
      }
  }
}

```
