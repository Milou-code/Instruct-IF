# Instruct'IF

Backend Java d’une application de mise en relation entre **élèves** et **professeurs particuliers**.

## Contexte pédagogique

Ce projet a été réalisé dans le cadre d’un **TP**.  
L’objectif était de développer la partie **backend** d’une application permettant :
- à des élèves de formuler des demandes de cours,
- à des professeurs d’y répondre et de gérer leurs disponibilités,
- de suivre la relation élève/professeur côté métier.

---

## Objectif de l’application

Instruct’IF fournit une API métier pour :
- gérer les comptes utilisateurs (élèves / professeurs),
- publier et traiter des demandes de cours,
- associer un élève à un professeur selon les critères métier,
- administrer les informations nécessaires au suivi pédagogique.

---

## Fonctionnalités principales

- Gestion des utilisateurs (création, authentification, rôles)
- Gestion des profils professeurs (matières, disponibilités, informations)
- Création et suivi des demandes de cours
- Mécanismes d’affectation / mise en relation élève-professeur
- Consultation des données métier (historique, statut des demandes, etc.)

---

## Architecture du projet

Projet backend Java organisé en couches classiques :

| Couche | Rôle |
|---|---|
| `modele` / entités | Représentation des objets métier |
| `dao` | Accès aux données (requêtes, persistance) |
| `service` | Logique métier et règles applicatives |
| `util` | Outils transverses (configuration, helpers, etc.) |

> Les noms exacts des packages peuvent varier selon votre implémentation, mais la logique reste la même.

---

## Utilisation (vue métier)

Flux typique :
1. Création des utilisateurs (élève/professeur)
2. Création des profils et informations pédagogiques
3. Dépôt d’une demande de cours par l’élève
4. Traitement / affectation à un professeur
5. Suivi du statut de la demande

---

## Travail réalisé pendant le TP

Dans ce TP, nous avons principalement :
- conçu et implémenté le **backend métier**,
- désigné les IHM afin qu'un autre groupe puissent les reproduire

---

## Auteurs

Projet réalisé dans un cadre pédagogique (TP).  
Équipe : Côme LAINE et Emilien CHAFFRAIX
