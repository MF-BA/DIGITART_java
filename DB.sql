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

--
-- Index pour les tables déchargées
--

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
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bid`
--
ALTER TABLE `bid`
  ADD CONSTRAINT `fk_id_auction` FOREIGN KEY (`id_auction`) REFERENCES `auction` (`id_auction`);
COMMIT;


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
-- Index pour les tables déchargées
--

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

-- --------------------------------------------------------

--
-- Structure de la table `payment`
--

CREATE TABLE `payment` (
  `payment_id` int(255) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `purchase_date` date NOT NULL,
  `nb_adult` int(20) DEFAULT NULL,
  `nb_teenager` int(20) DEFAULT NULL,
  `nb_student` int(20) DEFAULT NULL,
  `total_payment` int(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `payment`
--

INSERT INTO `payment` (`payment_id`, `user_id`, `purchase_date`, `nb_adult`, `nb_teenager`, `nb_student`, `total_payment`) VALUES
(17, NULL, '2023-02-23', 4, 4, 4, 360),
(21, NULL, '2023-02-22', 2, 4, 5, 300);

-- --------------------------------------------------------

--
-- Structure de la table `ticket`
--

CREATE TABLE `ticket` (
  `ticket_id` int(50) NOT NULL,
  `ticket_date` date DEFAULT NULL,
  `ticket_edate` date DEFAULT NULL,
  `price` int(50) NOT NULL,
  `ticket_type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `ticket`
--

INSERT INTO `ticket` (`ticket_id`, `ticket_date`, `ticket_edate`, `price`, `ticket_type`) VALUES
(1, '2023-02-22', '2023-02-28', 20, 'Student'),
(2, '2023-02-22', '2023-02-28', 30, 'Teen'),
(3, '2023-02-22', '2023-02-28', 40, 'Adult'),
(5555565, '2023-03-01', '2023-03-31', 100, 'Adult'),
(5555566, '2023-03-01', '2023-03-31', 200, 'Teen'),
(5555568, '2023-03-01', '2023-03-31', 300, 'Student');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `pk` (`user_id`);

--
-- Index pour la table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`ticket_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT pour la table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `ticket_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5555569;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `pk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

CREATE TABLE `event` (
  `id` varchar(255) NOT NULL,
  `event_name` varchar(255) NOT NULL,
  `start_date` varchar(255) NOT NULL,
  `end_date` varchar(255) NOT NULL,
  `nb_participants` varchar(255) NOT NULL,
  `detail` varchar(255) NOT NULL,
  `start_time` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`id`, `event_name`, `start_date`, `end_date`, `nb_participants`, `detail`, `start_time`) VALUES
('1', 'event 1', '14/12/2023', '18/12/2023', '25', 'this event is awesome', '14:00');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
