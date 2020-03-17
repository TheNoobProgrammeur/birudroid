# :fire: Spécifications de la base de données Firebase :fire:

## :bow_and_arrow:  Objectifes de la BDD :bow_and_arrow:

La base de donnée aura pour but de sauvegarder les données suivantes :
* Les utilisateurs
* Les bières
* Les commentaires
* Les marques de bière
* Les types de bière

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
* _midx est un id d'une marque de biere_
* _tidx est un id de type de bière_
* _bridx est un id de marque_
* _cidx est un id de commentaire_ 

## Utilisateur :person_with_blond_hair:

Chaque utilisateur authentifié a uuid unique généré par Firebase, il est retourné dans le résultat de chaque méthode d'authentification.
La collection `Users` permet de stocker ses informations tel que son nom d'utilisateur, son mail et ses bières favorites.

### Schéma de la collection **Users**

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
          "favoriteBeer": {
            "type": "array",
            "items":{
              "type": "string",
              "pattern": "[0-9A-z]{28}",
            }
          }
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
      "favoriteBeer": [
          "bid1",
          "bid7"
      ]
    },
    "uid2":{}
  }
}
```

## Bières :beer:

Les bières seront identifiées par un uuid.  
Informations liées à une bière:
  * nom de la bière
  * type de bière (Ex: Brune, Blonde, IPA...)
  * descrition
  * degré d'alcool
  * moyenne des notes données par la communauté
  * marque de la bière (nom et description)
  * information sur là où a été brassé la bière
  * commentaires de la communauté

Dans un premier temps les commentaires seront présent sans possibilité de réponse, aucun commentaire sera liée à un autre. 

### Schéma de la collection **Beers**

```json
{
  "beers":{
    "type": "object",
    "patternProperties":{
      "[0-9A-z]{28}": {
        "type": "object",
        "properties":{
          "name": { "type" : "string" },
          "typeBeerId": { 
            "type" : "string" ,
            "pattern" : "[0-9A-z]{28}"
          },
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
          "nbVote":{
            "type" : "integer",
            "minimum" : 0
          },
          "brandId": { 
            "type":"string",
            "pattern" : "[0-9A-z]{28}" 
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
      "nbVote": 5,
      "brandId": "brid1",
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

## Commentaires sur une bière

Pour éviter une trop grande surcharge lors de l'importation d'une bière nous avons desidé que les commentaires seront dans une collection propre.

### Schéma de la collection **Comments**

```json
{
  "comments":{
      "type":"objet",
      "patternProperties":{
          "[0-9A-z]{28}":{
              "properties":{
                  "idUser" :{ 
                    "type":"string",
                    "pattern" : "[0-9A-z]{28}" 
                  },
                  "idBeer":{
                    "type":"string",
                    "pattern" : "[0-9A-z]{28}" 
                  },
                  "message":{
                    "type":"string"
                  }
              }
          }
      },
      "required": ["idUser","idBeer","message"],
  }
}

```

### Exemple 

```json
{
  "comments":{
    "cid1":{
      "idUser":"uid1",
      "idBeer":"bid1",
      "message":"TODO"
    }
  }
}
```

## Collections annexe ℹ️ :

Pour améliorer la cohérence des données et leur filtrage nous avons sorti certain parametre de la collection biére pour créer des collections propres.

* Type de bière
* Marque de bière

## Type de bière :

### Schéma de la collection **TypeBeer**

```json
{
  "typeBeer":{
    "type":"object",
    "patternProperties":{
        "[0-9A-z]{28}":{
            "properties":{
                "name":{"type":"string"}
            },
            "required":["name"]
        }
    }
  }
}
```
### Exemple :

```json
{
  "typeBeer":{
      "tid1":{
          "name":"Blonde"
      }
  }
}
```

## Marque de la bière

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