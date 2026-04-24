# INSTRUCT'IF - Réseau d'Aide aux Devoirs

## À propos du projet
[cite_start]INSTRUCT'IF est une plateforme de mise en relation permettant d'offrir du soutien scolaire aux élèves de collège. [cite_start]Lorsqu'un élève est bloqué sur un devoir ou a besoin d'explications sur un chapitre, il peut faire une demande ciblée sur le site. [cite_start]L'application se charge alors de trouver un intervenant bénévole qualifié et disponible pour l'accompagner lors d'une session de tutorat en visioconférence[cite: 90, 128].

## Fonctionnalités Principales
* [cite_start]**Gestion des Inscriptions :** Création de compte pour les élèves avec recherche automatique de leur établissement scolaire via son code UAI[cite: 134].
* [cite_start]**Diversité des Intervenants :** Le système supporte plusieurs profils d'intervenants (Étudiant, Enseignant, Autre) avec une gestion de leurs niveaux scolaires d'intervention (de la 6ème à la 3ème)[cite: 70, 76, 77, 81].
* [cite_start]**Demandes de Soutien :** Les élèves peuvent formuler des demandes en sélectionnant une matière (ex: Histoire) et un thème précis (ex: Moyen-Âge) accompagnés d'une description de leur problème[cite: 140, 141].
* [cite_start]**Attribution Intelligente :** Lorsqu'une demande est validée, l'application recherche le bénévole disponible ayant effectué le moins d'interventions pour garantir une répartition équitable[cite: 90].
* [cite_start]**Intégration d'APIs de l'Éducation Nationale :** Utilisation des services web `data.education.gouv.fr` pour récupérer les coordonnées géographiques, l'appellation et l'Indice de Position Sociale (IPS) de l'établissement de l'élève[cite: 13, 14, 31, 32].
* [cite_start]**Simulations de Communication :** Génération de notifications (SMS) pour alerter les intervenants et d'e-mails pour confirmer les inscriptions ou envoyer les bilans de fin de séance[cite: 117].

## Technologies Utilisées
[cite_start]Le projet est construit autour d'une architecture logicielle en couches (Modèle, DAO, Service)[cite: 106].
* [cite_start]**Langages :** Java 11 [cite: 62]
* [cite_start]**Persistance / ORM :** JPA (Java Persistence API) implémenté avec EclipseLink [cite: 60]
* [cite_start]**Base de données :** MariaDB [cite: 59]
* [cite_start]**Outil de Build :** Maven [cite: 61]
* [cite_start]**Format d'échange :** JSON (librairie Jakarta JSON) pour la lecture des APIs externes[cite: 62].

## Architecture du Code Source
Le code source (`src/main/java/`) est découpé selon les packages suivants :
* `metier.modele` : Les classes entités persistées en base de données (ex: `Eleve`, `Intervenant`, `Soutien`, `Matiere`, `Theme`, `Etablissement`).
* [cite_start]`dao` : Les classes d'accès aux données (Data Access Object) gérant les requêtes JPQL, ainsi que la classe utilitaire `JpaUtil` pour gérer le contexte de persistance et les transactions[cite: 66].
* `metier.service` : L'orchestration de la logique métier de l'application :
  * `ServiceCompte` : Méthodes d'inscription et d'authentification.
  * `ServiceSoutien` : Méthodes de création, d'assignation et de clôture des demandes d'aide.
  * `ServiceInitialisation` : Scripts d'injection des données de test (intervenants et matières) au démarrage.
* [cite_start]`util` : Composants transverses, incluant `EtablissementOutils` (requêtes HTTP vers les APIs gouvernementales), `Message` (simulation d'envois) et `Saisie` (interactions console)[cite: 66].
* `console` : Contient l'application console `Instructif.java` permettant de lancer et tester le flux principal de bout en bout.
