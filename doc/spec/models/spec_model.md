# Specification Models 

## 🏗️ Objectifs 🏗️

Les models permet une representaiont des objets implementer dans le code.  
Ces representations ne represente que l'implementation coter client,   
les objets ils n'auront pas la même implementation dans la BDD.

## ✏️ Specifications ✏️

### 🧑 Utilisateur 🧑

L'utilisateur est definie par :
* un ID
* un nom
* une @mail
* une liste de bières favorie

L'utilisateur peux :

* Manipuler sa liste de bière favorit (add/update/delete)
* Ajouter un commentaire a une biere
* Noter une Biere

#### 🖥️ Representation UML 🖥️

### 🍺 Bière 🍺

Une bière est  definie par :
* un ID
* un nom
* une marque
* un type
* une description
* une moyenne
* nombre de personne qui on voter
* une liste de commentaire

Une bière peux :
* Metre a jour la moyenne

### ©️ Marque ©️

Une marque est definie par :
* Un ID
* un nom
* une liste de lieu 
* le nombre de bière que la marque propose
* une description