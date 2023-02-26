-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 26 fév. 2023 à 22:57
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
(6, 500, 50, '2023-02-15', 'new edition', 1),
(7, 100, 10, '2023-03-10', 'new edition', 1),
(8, 100, 10, '2023-03-10', 'new edition', 1),
(9, 100, 10, '2023-03-10', 'new edition', 1);

-- --------------------------------------------------------

--
-- Structure de la table `bid`
--

CREATE TABLE `bid` (
  `ID` int(11) NOT NULL,
  `date` date NOT NULL,
  `offer` int(11) NOT NULL,
  `id_auction` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

CREATE TABLE `event` (
  `id` int(11) NOT NULL,
  `event_name` varchar(255) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `nb_participants` varchar(255) NOT NULL,
  `detail` varchar(255) NOT NULL,
  `start_time` int(255) NOT NULL,
  `id_room` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`id`, `event_name`, `start_date`, `end_date`, `nb_participants`, `detail`, `start_time`, `id_room`) VALUES
(13, 'ii', '2023-02-01', '2023-02-08', '4', 'i', 47, 4);

-- --------------------------------------------------------

--
-- Structure de la table `participants`
--

CREATE TABLE `participants` (
  `id_user` int(11) NOT NULL,
  `id_event` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `adress` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `participants`
--

INSERT INTO `participants` (`id_user`, `id_event`, `first_name`, `last_name`, `adress`, `gender`) VALUES
(87, 13, 'koussay', 'bedhief', 'm', 'male'),
(88, 13, 'aziz', 'loukil', 'soukra', 'Male');

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
-- Déchargement des données de la table `room`
--

INSERT INTO `room` (`id_room`, `name_room`, `area`, `state`, `description`) VALUES
(4, 'room1', 25, 'yy', 'yy');

-- --------------------------------------------------------

--
-- Structure de la table `ticket`
--

CREATE TABLE `ticket` (
  `ticket_id` int(50) NOT NULL,
  `ticket_date` date NOT NULL DEFAULT current_timestamp(),
  `ticket_type` varchar(50) NOT NULL,
  `price` int(50) NOT NULL,
  `ticket_nb` int(50) NOT NULL,
  `ticket_desc` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `ticket`
--

INSERT INTO `ticket` (`ticket_id`, `ticket_date`, `ticket_type`, `price`, `ticket_nb`, `ticket_desc`) VALUES
(12, '2023-02-09', 'Teen', 12, 12, 'ezez'),
(14, '2023-02-18', 'Adult', 14, 15, 'test');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `cin` int(11) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone_num` int(11) NOT NULL,
  `birth_date` date NOT NULL,
  `gender` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `cin`, `firstname`, `lastname`, `email`, `password`, `address`, `phone_num`, `birth_date`, `gender`, `role`) VALUES
(87, 12345678, 'koussay', 'bedhief', 'kb@gmail.com', '123', 'm', 12345678, '2023-02-08', 'male', 'Admin'),
(88, 12345678, 'aziz', 'loukil', 'azizloukil@gmail.com', '123', 'soukra', 12345678, '2023-02-01', 'Male', 'Artist');

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
-- Index pour la table `auction`
--
ALTER TABLE `auction`
  ADD PRIMARY KEY (`id_auction`);

--
-- Index pour la table `bid`
--
ALTER TABLE `bid`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fk_id_auction` (`id_auction`);

--
-- Index pour la table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_room` (`id_room`);

--
-- Index pour la table `participants`
--
ALTER TABLE `participants`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `id_event_const` (`id_event`);

--
-- Index pour la table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id_room`);

--
-- Index pour la table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`ticket_id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `auction`
--
ALTER TABLE `auction`
  MODIFY `id_auction` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `bid`
--
ALTER TABLE `bid`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `event`
--
ALTER TABLE `event`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `room`
--
ALTER TABLE `room`
  MODIFY `id_room` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `ticket_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1235;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `artwork`
--
ALTER TABLE `artwork`
  ADD CONSTRAINT `fk_art` FOREIGN KEY (`id_room`) REFERENCES `room` (`id_room`);

--
-- Contraintes pour la table `bid`
--
ALTER TABLE `bid`
  ADD CONSTRAINT `fk_id_auction` FOREIGN KEY (`id_auction`) REFERENCES `auction` (`id_auction`);

--
-- Contraintes pour la table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `id_room` FOREIGN KEY (`id_room`) REFERENCES `room` (`id_room`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `participants`
--
ALTER TABLE `participants`
  ADD CONSTRAINT `id_event_const` FOREIGN KEY (`id_event`) REFERENCES `event` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_user_const` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
