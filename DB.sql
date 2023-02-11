-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 11 fév. 2023 à 15:52
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
-- Structure de la table `auction`
--

CREATE TABLE `auction` (
  `id_auction` int(11) NOT NULL,
  `starting_price` int(11) NOT NULL,
  `increment` int(11) NOT NULL DEFAULT 10,
  `ending_date` date NOT NULL,
  `description` text NOT NULL,
  `id_artwork` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `auction`
--

INSERT INTO `auction` (`id_auction`, `starting_price`, `increment`, `ending_date`, `description`, `id_artwork`) VALUES
(1, 100, 10, '2023-03-10', 'new edition', 1),
(3, 100, 10, '2023-03-10', 'new edition', 1),
(4, 100, 10, '2023-03-10', 'new edition', 1),
(5, 100, 10, '2023-03-10', 'new edition', 1),
(6, 100, 10, '2023-03-10', 'new edition', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `auction`
--
ALTER TABLE `auction`
  ADD PRIMARY KEY (`id_auction`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `auction`
--
ALTER TABLE `auction`
  MODIFY `id_auction` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
