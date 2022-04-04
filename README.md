#Sfart : Semantic Field Automatic Read & Transform

Sfart est un outil qui permettra de prendre en entrée un texte libre (sans doute de quelques lignes, le nombre de lignes est à décider) et de générer un nuage de mots.
Pour ce faire, outre le texte en entrée Sfart va utiliser quelques ressources afin de construire un « dictionnaire » qui va permettre de traiter le texte initial passé en paramètre.
Pour construire le « dictionnnaire », je fais appel à 2 sites webs que je vais scrapper - rimessolides.com & lerobert.com - Le premier site (rimessolides) va permettre de réucpérer un champ lexical par rapport à un mot clé comme par exemple (https://www.rimessolides.com/motscles.aspx?m=marais). Le deuxième site (https://dictionnaire.lerobert.com/definition/marais) va permettre de récupérer la nature du mot et sa définition.

A partir de ce « dictionnaire » custom, on va créer un graphe (stocké dans Neo4J) que l’utilisera pour calculer le nuage de mots en utilisant un texte comme source et en le croisant avec le graphe.

## La stack logicielle :
- Back : Kotlin + Ktor 2
- Base de données : Neo4J
- Front : Svelte + D3
- Scrapping : SkapeIt (lib kotlin)

##Ressources :

- champ lexical : https://www.rimessolides.com/motscles.aspx?m=marais
- Définition & nature : https://dictionnaire.lerobert.com/definition/marais

##Docker command

docker run -d -p7474:7474 -p7687:7687 --volume=/Users/hervedarritchon/Programmation/Workspace/Perso/JdR/SemanticField/data:/data --vol
ume=/Users/hervedarritchon/Programmation/Workspace/Perso/JdR/SemanticField/logs:/logs -e NEO4J_AUTH=neo4j/s3cr3t neo4j

##Neo4j

###batch MERGE

https://community.neo4j.com/t/neo4j-merge-performance-optimization/16244/4

https://medium.com/neo4j/5-tips-tricks-for-fast-batched-updates-of-graph-structures-with-neo4j-and-cypher-73c7f693c8cc