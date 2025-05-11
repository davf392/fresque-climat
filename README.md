# Fresque Climat Animateur [![Kotlin Version](https://img.shields.io/badge/kotlin-2.1.0-blue.svg)](https://kotlinlang.org) [![API](https://img.shields.io/badge/API-35%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=35) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Ce projet d'application mobile est un prototype non officiel, inspiré du site web Fresque du Climat à des fins de développement et de démonstration avec des données factices. 
Elle est actuellement en phase de prototypage mais vous êtes invités à la tester et à me faire un retour d'expérience.

Veuillez noter qu'il ne s'agit en aucun cas d'une affiliation ou d'une copie officielle du site web de la [Fresque du Climat](https://association.climatefresk.org/)

Toutes les marques de commerce et droits d'auteur associés au site web appartiennent à leurs propriétaires respectifs.

## 📖 Description

Cette application a été conçue pour accompagner et améliorer l'expérience des animateurs de l'atelier « La Fresque du Climat »
L'application permet également aux personnes qui ne sont pas animateurs/animatrices de trouver un atelier de libre pour pouvoir s'y inscrire et y participer.

### Recherche d'ateliers existants
Une fonctionnalité permettant aux utilisateurs d'**explorer les prochains ateliers** Fresque Climat ou de **publier une session**.
Les utilisateurs peuvent rechercher des ateliers par ville, filtrer par rayon et par langue, et consulter les détails des sessions disponibles.

### Outil d'aide à l'animation d'ateliers
Un écran dédié fournit un **minuteur visuel pour chaque phase de l'atelier** (Introduction, Lot 1-5, Synthèse, Ordres de grandeur, Émotions, Débats, Conclusion).
L'animateur peut démarrer, mettre en pause, réinitialiser et naviguer entre les différentes parties de l'atelier, assurant une progression fluide et respectueuse du temps.
Le minuteur affiche le temps restant pour la phase actuelle et le temps restant total de l'animation.

### Connexion à l'espace animateur
Cet écran permet à l'animateur de rentrer ses identifiants afin de **se connecter à son espace personnel** d'animateur de la fresque.
L'espace animateur permet à l'animateur d'avoir un aperçu de son profil et son parcours de fresqueur ainsi que de gérer ses sessions d'animation passées et à venir.
Il permet également de modifier ses informations personnelles et sa photo de profil.

## 📸 Captures d'écran


## 📐 Architecture logicielle

- **Language:** Kotlin 2.1.0
- **Modèle d'architecture:** MVVM (Model-View-ViewModel) avec Jetpack Compose pour la couche UI.
  - **ViewModel:** Gère les données liées à l'interface utilisateur et la logique métier, exposant l'état aux Composables via `StateFlow`
  - **Composable (View):** UI déclarative implémentée avec Jetpack Compose
  - **Model:** Classes de données représentant les données de l'application.

## 🚀 Librairies utilisées

- **Jetpack Compose**: Kit UI déclaratif moderne
- **Kotlin Coroutines & Flow**: Gestion asynchrone
- **Hilt**: Injection de dépendances
- **Retrofit**: Accès réseau
- **Room**: Base de données
- **Coil**: Chargement d'images
- **Turbine**: Tests de Flows
- **JUnit & MockK**: Tests unitaires

## 📲 Guide d'installation

### 📝 Prérequis

Avant de commencer, assurez-vous d'avoir les éléments suivants :
- Un smartphone Android fonctionnel
- Accès au dépôt GitHub de l'application

### 📦 Installation

1. Téléchargez la dernière version de l'application à partir de la section "Releases" de ce dépôt GitHub.
2. Sur votre appareil Android, accédez aux paramètres de sécurité et activez l'option "Sources inconnues" ou "Installation à partir de sources inconnues". Cela vous permettra d'installer des applications depuis des sources autres que le Google Play Store.
3. Ouvrez le fichier APK téléchargé depuis votre gestionnaire de fichiers ou depuis la barre de notifications.
4. Suivez les instructions à l'écran pour installer l'application.
5. Une fois l'installation terminée, vous pouvez ouvrir l'application en appuyant sur son icône dans le lanceur d'applications de votre appareil.

### ℹ️ Remarques

- Cette version de l'application est une version de prototypage et peut contenir des bugs ou des fonctionnalités non finalisées.
- N'hésitez pas à me faire part de vos retours d'expérience en signalant tout bug que vous rencontrez ou toute suggestion d'amélioration que vous pourriez avoir (vous pouvez soumettre un bug via la section "Issues" de ce dépôt GitHub)


## 🤝 Contributing

### Voulez-vous contribuer au code ?
1. [Fork Fresque Climat ](https://github.com/davf392/fresque-climat/)
2. Créer une nouvelle branche ([avec GitHub](https://help.github.com/articles/creating-and-deleting-branches-within-your-repository/)) ou avec la commande `git checkout -b branch-name develop`)
3. [Créer une Pull Request](https://github.com/davf392/fresque-climat/compare)
