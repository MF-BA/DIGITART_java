-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 11 fév. 2023 à 17:05
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `digitart`
--

-- --------------------------------------------------------

--
-- Structure de la table `artwork`
--

CREATE TABLE `artwork` (
  `id_art` int(11) NOT NULL,
  `artwork_name` varchar(255) NOT NULL,
  `id_artist` int(11) DEFAULT NULL,
  `artist_name` varchar(255) DEFAULT NULL,
  `date_art` date NOT NULL,
  `description` text DEFAULT NULL,
  `image_art` varchar(255) DEFAULT NULL,
  `id_room` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `room`
--

CREATE TABLE `room` (
  `id_room` int(11) NOT NULL,
  `name_room` varchar(255) NOT NULL,
  `area` int(11) NOT NULL,
  `state` varchar(255) NOT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `artwork`
--
ALTER TABLE `artwork`
  ADD PRIMARY KEY (`id_art`),
  ADD KEY `fk_art` (`id_room`);

--
-- Index pour la table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id_room`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `room`
--
ALTER TABLE `room`
  MODIFY `id_room` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `artwork`
--
ALTER TABLE `artwork`
  ADD CONSTRAINT `fk_art` FOREIGN KEY (`id_room`) REFERENCES `room` (`id_room`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
