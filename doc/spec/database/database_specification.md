# :fire: Spécifications de la base de données Firebase :fire:

## :bow_and_arrow:  Objectifes de la BDD :bow_and_arrow:

La base de donnée aura pour but de sauvegarder les données suivantes :
* Les utilisateurs
* Les bières
* Les marques de bière

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

## Utilisateur :person_with_blond_hair:

Chaque utilisateur authentifié a uuid unique généré par Firebase, il est retourné dans le résultat de chaque méthode d'authentification.
La collection `Users` permet de stocker ses informations tel que son nom d'utilisateur, son mail et ses bières favorites.

### Schéma de la collection **User**

```json
{
  "users":{
    "type": "array",
    "items": {
      "type": "object",
      "patternProperties":{
        "[0-9A-z]{28}": {
          "type": "object",
          "properties":{
            "username": { "type" : "string" },
            "email": { "type" : "string" },
            "favorite_beer": {
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
}
```

### Exemple :

```json
{
  "users":{
    "uid1":{
      "username": "toto", 
      "email": "toto@toto.gouv", 
      "favorite_beer": [
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
    "type": "array",
    "items": {
      "type": "object",
      "patternProperties":{
        "[0-9A-z]{28}": {
          "type": "object",
          "properties":{
            "name": { "type" : "string" },
            "typeBeer": { "type" : "string" , "pattern" : "[0-9A-z]{28}"},
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
            "nb_vote":{
              "type" : "integer",
              "minimum" : 0
            },
            "brand": { "type":"string", "pattern" : "[0-9A-z]{28}" },
            "brewery": { "type":"string", "pattern" : "[0-9A-z]{28}" },
          },
          "additionalProperties": false
        }
      },
      "required": ["name", "type", "description", "degree"]
    }
  }
}
```

### Exemple :

```json
{
  "beers":{
    "bid1":{
      "name": "la bête",
      "type": "typeId",
      "description": "TODO",
      "degree": 8,
      "average": 2.5,
      "brand": "brandID",
      "brewery": "brewaryID"
    },
    "bid2": {}
  }
}
```

### colection anexe ℹ️ :

Pour amelioré la cohérance des données et leur filtrage nous avons sortie certain parametre de la colection biére pour crée des colection propre.

* Type de bière
* Marque de bière
* Braserie de la bière

#### Type de bière :

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
##### Exemple :

```json

{
    "typeBeer":{
        "typeID":{
            "name":"Blonde"
        }
    }
}


```

#### Marque de la bière

```json
{
    "brand": { 
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

##### Exemple :

```json
{
    "brand":{
        "brandId":{
            "name":"Delirium",
            "description":"Bières de caractères"
        }
    }
}

```

#### Braserie de la bière 

```json
{
  "brewery": {
    "type" : "object",
    "patternProperties":{
        "[0-9A-z]{28}":{
            "properties": {
              "name": { "type" : "string" },
              "address": { "type" : "string" },
              "creation_date": { 
                  "type" : "string",
                  "format": "date-time" 
              }, 
            }
        }
    },
      
    "required": ["name", "address", "creation_date"],
  }
}

```
##### Exemple

```json
"brewery": {
  "name" : "brasserie 1",
  "address":"adr 1",
  "creation_date":1990
}
```


## Comentaire sur une bière

Pour eviter une trop grande surcharge lors de l'importation d'une bière nous avons desidé que les commentaire soit dans une colection propre.

```json
{
    "comment":{
        "type":"objet",
        "patternProperties":{
            "[0-9A-z]{28}":{
                "properties":{
                    "idUser" : { "type":"string", "pattern" : "[0-9A-z]{28}" },
                    "idbeer":{ "type":"string", "pattern" : "[0-9A-z]{28}" },
                    "message":{"type":"string"}
                }
            }
        },
        "required": ["idUser","idBeer","message"],
    }
}

```

##### Exemple 

```json
{
  "comment":{
    "idMessage":{
      "idUser":"user1",
      "idbeer":"beer1",
      "message":"TODO"
    }
  }
}
```