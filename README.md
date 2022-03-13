# projectJava2

Le projet est composé d'un layout principal sous forme d'onglet permettant de se déplacer plus facilement à travers les différentes pages.

**Onglet: Home :**
L'onglet Home est l'accueil de notre programme
![image](https://user-images.githubusercontent.com/85236071/158071958-de1a6b16-8415-425f-9598-7f37ba4225ae.png)

**Onglet: Add a Person:**
L'onglet Add a Person permet l'accès à un formulaire de création de personne, dans lequel tous les champs sont nécessaires pour l'intégrer dans la database.
Une fois que les champs ont tous bien étaient remplies, la personne est ajouté à la database.
![image](https://user-images.githubusercontent.com/85236071/158072077-4e889ca1-e705-4070-b860-27c059644c28.png)

**Onglet: Research a Person:**
L'onglet Research a Person permet de rechercher des personnes dans la database selon :
	 * Le prénom et/ou le nom (un champs sur 2 est suffisant pour lancer la recherche)
	 * Le surnom
	 * Le numéro de téléphone
	 * L'adresse postale
	 * L'adresse email
	 * La database entière 
Pour pouvoir accéder à ces différents types de recherches, il faut dérouler le menu déroulant à côté de "Research Person By", remplir le ou les champs et appuyer sur *Search*
![image](https://user-images.githubusercontent.com/85236071/158072259-63c27eac-ac53-4c16-a5e0-7b8fd43708fb.png)

Après avoir lancé une recherche, il est possible de sélectionner une ligne relative à une personne dans la table, afin de pouvoir avoir accès à l'entièreté de ses informations, et pouvoir :
 * Supprimer la personne de la database
 * Modifier la personne en corrigeant directement les champs correspondants (sachant qu'on ne peut pas donner deux adresses emails ou deux numéros de téléphones identiques)
 * Exporter cette personne en Vcard
![image](https://user-images.githubusercontent.com/85236071/158072546-06e2bb7d-6b1b-4113-b834-a55aab1de514.png)

**Onglet: Import/Export Database:**
Dans l'onglet Import/Export Database, il est possible :
 * D'exporter la database selon le nom du ficher indiqué et le format sélectionné (txt, csv ou vcf), puis appuyer sur *Export*. Si vous choisissez vcf, le programme créera pour chaque personne de la database une vCard dans le dossier *vcard*
 * D'importer dans la database un ficher en format txt ou csv
![image](https://user-images.githubusercontent.com/85236071/158072791-c6b19d19-03f7-4d75-a32b-280c37ee4db7.png)
