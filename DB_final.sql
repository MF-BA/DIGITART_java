-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 08 mars 2023 à 14:40
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

DELIMITER $$
--
-- Fonctions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `validate_bid_offer` (`id_bid` INT, `offer` INT) RETURNS TINYINT(1)  BEGIN
    DECLARE max_offer INT;
    DECLARE increment INT;
    SELECT MAX(offer), (SELECT increment FROM auction WHERE id_auction = id_bid) INTO max_offer, increment
    FROM bid
    WHERE id_auction = id_bid;
    IF max_offer IS NULL THEN
        RETURN TRUE;
    ELSE
        RETURN offer >= max_offer + increment;
    END IF;
END$$

DELIMITER ;

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
  `id_room` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `artwork`
--

INSERT INTO `artwork` (`id_art`, `artwork_name`, `id_artist`, `artist_name`, `date_art`, `description`, `image_art`, `id_room`) VALUES
(11, 'La Joconde', -1, 'Léonard de Vinci', '1506-02-23', 'La Joconde, ou Portrait de Mona Lisa', 'http://localhost/images/Mona_Lisa,_by_Leonardo_da_Vinci,_from_C2RMF_natural_color.jpg', 2),
(28, 'starry night', -1, 'Van Gogh', '1807-03-02', 'starry night van gogh', 'http://localhost/images/starry-night-1093721_960_720.jpg', 14),
(33, 'Guernica', -1, 'Pablo Picasso', '1937-01-10', 'Guernica Pablo Picasso', 'http://localhost/images/Main_Guernica_BAT-10313.jpg', 11),
(34, 'Ulysse et les sirène', -1, 'Bardo', '2023-03-18', 'dzqdqzd', 'http://localhost/images/Bardo_Mosaic_Ulysses.jpg', 12),
(36, 'the scream', 2, ' Munch', '2023-03-24', 'the scream ', 'http://localhost/images/Edvard_Munch,_1893,_The_Scream,_oil,_tempera_and_pastel_on_cardboard,_91_x_73_cm,_National_Gallery_of_Norway.jpg', 2),
(37, 'Meduse', 2, ' Munch', '1638-03-23', 'Le buste de Méduse est une sculpture en marbre inspirée du personnage mythologique éponyme.', 'http://localhost/images/Medusa_head_attributed_to_Gianlorenzo_Bernini_(Roma).jpg', 2),
(50, 'Girl with mandolin', 35, 'Pablo Picasso', '2020-03-14', 'Pablo Picasso', 'http://localhost/images/9166uNbx9DL._AC_SL1500_.jpg', 4),
(52, 'gauffrette', -1, 'CHOKOTOM', '2022-10-07', 'ma9tou33', 'http://localhost/images/Gaufrettes-CHOCOTOM-100g.jpg', 2),
(53, 'La Boheme', 35, 'lastname', '2023-03-08', 'tanja77', 'http://localhost/images/1665_Girl_with_a_Pearl_Earring.jpg', 14);

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
  `id_artwork` int(11) NOT NULL,
  `state` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `auction`
--

INSERT INTO `auction` (`id_auction`, `starting_price`, `increment`, `ending_date`, `description`, `id_artwork`, `state`) VALUES
(38, 1000, 10, '2023-03-10', 'test', 37, NULL);

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
-- Déchargement des données de la table `bid`
--

INSERT INTO `bid` (`ID`, `date`, `offer`, `id_auction`, `id_user`) VALUES
(1, '2023-03-08', 1000, 38, 2);

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
  `id_room` int(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`id`, `event_name`, `start_date`, `end_date`, `nb_participants`, `detail`, `start_time`, `id_room`, `image`) VALUES
(20, 'event78', '2023-03-11', '2023-03-19', '4', '77', 9, 4, 'http://localhost/images/Mona_Lisa,_by_Leonardo_da_Vinci,_from_C2RMF_natural_color.jpg'),
(25, 'event10', '2023-03-17', '2023-03-24', '13', 'awesome', 15, 4, 'http://localhost/images/Mona_Lisa,_by_Leonardo_da_Vinci,_from_C2RMF_natural_color.jpg'),
(26, 'event13', '2023-03-16', '2023-03-25', '50', 'test', 6, 4, 'http://localhost/images/Mona_Lisa,_by_Leonardo_da_Vinci,_from_C2RMF_natural_color.jpg'),
(27, 'event14', '2023-03-16', '2023-03-25', '50', 'test', 4, 4, 'http://localhost/images/Mona_Lisa,_by_Leonardo_da_Vinci,_from_C2RMF_natural_color.jpg'),
(28, 'event89', '2023-03-09', '2023-03-18', '14', 'teest', 7, 4, 'http://localhost/images/Mona_Lisa,_by_Leonardo_da_Vinci,_from_C2RMF_natural_color.jpg'),
(29, 'tlili', '2023-03-09', '2023-03-23', '100', 'test', 17, 11, 'http://localhost/images/333193552_2431029213712333_5205244347491257198_n.jpg');

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
(1, 20, 'firstname', 'lastname', 'address', ' gender'),
(35, 27, 'aziz', 'loukil', 'soukra', 'Male');

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
(21, NULL, '2023-02-22', 2, 4, 5, 300),
(22, 1, '2026-03-25', 0, 0, 10, 500);

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
(1, 'room 1', 12, 'available', NULL),
(2, 'Carthage', 260, 'Available', 'salle principale'),
(4, 'Paris', 3333, 'Available', 'qdqzdqzd testazdazdaz'),
(10, 'elyssa', 1000, 'Available', 'mezyena barcha'),
(11, 'Hanibal', 333, 'Unvailable', 'qdzqdzqd'),
(12, 'Cleopatre', 222, 'Unvailable', 'qdzqdzqd'),
(14, 'jalta', 56, 'Available', 'salle equipé par...');

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
(5555569, '2026-03-02', '2026-08-04', 50, 'Student');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `cin` int(11) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone_num` int(11) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT 'Subscriber',
  `status` varchar(255) NOT NULL DEFAULT 'unblocked',
  `image` varchar(255) DEFAULT NULL,
  `secretcode` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `cin`, `firstname`, `lastname`, `email`, `password`, `address`, `phone_num`, `birth_date`, `gender`, `role`, `status`, `image`, `secretcode`) VALUES
(1, 12056998, 'mohamed amine', 'tlili', 'tliliamine@gmail.com', 'ebf173b0111b8539dbdb798c747365df73fda40bc5d2236ac2855f5bbf4575fd', 'ben arous', 96332258, '2020-02-21', 'Female', 'Admin', 'unblocked', '', NULL),
(2, 12056889, 'koussay', 'bedhief', 'bedhiefkoussay2015@gmail.com', '6bb5f0d8fa58579312fd302f80f28fea70c7e9ece2e0b4a31a632c603dd19159', 'mourouj', 58963366, '2023-03-14', 'Male', 'Artist', 'unblocked', '', '4UQWPTI5GQPZ2YGU'),
(16, 12056778, 'mohamed', 'aziz', 'azizlk@gmail.com', '50a6bd8bb1879fa3359cbe61ad7d623925671ecdb39980008cdd0dafca08db90', 'sokra', 58963364, '2023-02-08', 'Male', 'Subscriber', 'unblocked', '', NULL),
(32, 12256889, 'mohamed', 'loukil', 'azouzloukil@gmail.com', '801add67bc22ff61beab8e41c71835d8ddfd6514039e802f90fefc90d8f9be8c', 'sokra', 25899455, '2001-07-26', 'Male', 'Admin', 'unblocked', '', 'ZDTHDOPLGWVDXKQ3'),
(35, 12056889, 'Mohamed aziz', 'Loukil', 'mohamedazizloukil@gmail.com', '801add67bc22ff61beab8e41c71835d8ddfd6514039e802f90fefc90d8f9be8c', 'ariana', 99709666, '2001-07-26', 'Male', 'Artist', 'unblocked', '', NULL);

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
  ADD PRIMARY KEY (`id_auction`),
  ADD KEY `fk_artwork` (`id_artwork`);

--
-- Index pour la table `bid`
--
ALTER TABLE `bid`
  ADD PRIMARY KEY (`ID`);

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
-- Index pour la table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `pk` (`user_id`);

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
-- AUTO_INCREMENT pour la table `artwork`
--
ALTER TABLE `artwork`
  MODIFY `id_art` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT pour la table `auction`
--
ALTER TABLE `auction`
  MODIFY `id_auction` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT pour la table `bid`
--
ALTER TABLE `bid`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `event`
--
ALTER TABLE `event`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT pour la table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT pour la table `room`
--
ALTER TABLE `room`
  MODIFY `id_room` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `ticket_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5555570;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `artwork`
--
ALTER TABLE `artwork`
  ADD CONSTRAINT `fk_art` FOREIGN KEY (`id_room`) REFERENCES `room` (`id_room`);

--
-- Contraintes pour la table `auction`
--
ALTER TABLE `auction`
  ADD CONSTRAINT `fk_artwork` FOREIGN KEY (`id_artwork`) REFERENCES `artwork` (`id_art`);

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

--
-- Contraintes pour la table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `pk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
