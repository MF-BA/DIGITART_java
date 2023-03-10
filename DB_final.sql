-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 10 mars 2023 à 03:43
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
(11, 'La Joconde', -1, 'Leonard de Vinci', '1506-02-23', 'La Joconde, ou Portrait de Mona Lisa', 'http://localhost/images/Mona_Lisa,_by_Leonardo_da_Vinci,_from_C2RMF_natural_color.jpg', 2),
(28, 'starry night', -1, 'Van Gogh', '1807-03-02', 'starry night van gogh', 'http://localhost/images/starry-night-1093721_960_720.jpg', 14),
(33, 'Guernica', -1, 'Pablo Picasso', '1937-01-10', 'Guernica Pablo Picasso', 'http://localhost/images/Main_Guernica_BAT-10313.jpg', 11),
(34, 'Ulysse et les sirène', -1, 'Bardo', '2023-03-18', 'dzqdqzd', 'http://localhost/images/Bardo_Mosaic_Ulysses.jpg', 12),
(36, 'the scream', 63, ' Munch', '2023-03-24', 'the scream ', 'http://localhost/images/Edvard_Munch,_1893,_The_Scream,_oil,_tempera_and_pastel_on_cardboard,_91_x_73_cm,_National_Gallery_of_Norway.jpg', 2),
(37, 'Meduse', 63, ' Munch', '1638-03-23', 'Le buste de Méduse est une sculpture en marbre inspirée du personnage mythologique éponyme.', 'http://localhost/images/Medusa_head_attributed_to_Gianlorenzo_Bernini_(Roma).jpg', 2),
(38, 'louve capitoline', 63, ' Munch', '2013-03-16', 'Elle est un symbole associé à la mythique légende de Romulus et Rémus et à la fondation de Rome depuis l\'Antiquité', 'http://localhost/images/22400-roma-campidoglio-e-musei-capitolini-03-sculture_jpg_1200_630_cover_85.jpg', 12),
(50, 'Girl with mandolin', 63, 'Pablo Picasso', '2020-03-14', 'Pablo Picasso', 'http://localhost/images/9166uNbx9DL._AC_SL1500_.jpg', 4);

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
(39, 15, 6, '2023-03-16', 'joli', 36, NULL);

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
(1, '2023-03-08', 1000, 38, 2),
(2, '2023-03-10', 20, 39, 60);

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
(20, 'Art workshops or classes', '2023-03-11', '2023-03-19', '4', 'This Event is dedicated to people who love art.', 9, 4, 'http://localhost/images/eventp1.jpg'),
(25, 'Halloween event', '2023-03-17', '2023-03-24', '14', 'A halloween event where you can have fun with your friends', 15, 4, 'http://localhost/images/eventp2.jpg'),
(30, 'Art event', '2023-03-11', '2023-03-16', '14', 'An event for gifted artists to develop their skills', 9, 4, 'http://localhost/images/eventp3.jpg'),
(31, 'Summer event', '2023-06-08', '2023-06-15', '5', 'An awesome event ', 18, 4, 'http://localhost/images/eventp4.jpg'),
(32, 'Guided tours of exhibitions', '2023-03-16', '2023-03-17', '24', 'A workshop for rookies to develop', 7, 4, 'http://localhost/images/eventp6.jpg');

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
(29, 58, '2023-02-24', 4, 4, 3, 66),
(30, 58, '2023-02-25', 3, 2, 4, 119),
(33, 58, '2023-02-28', 2, 2, 2, 78),
(34, 58, '2023-02-27', 3, 4, 3, 127),
(35, 58, '2023-02-28', 1, 2, 3, 73),
(38, 58, '2023-03-19', 1, 1, 1, 48),
(39, 58, '2023-03-19', 1, 1, 2, 63),
(41, 58, '2023-03-20', 2, 0, 0, 40),
(42, 58, '2023-03-31', 2, 1, 1, 68),
(43, 58, '2023-04-09', 5, 4, 0, 150),
(44, 58, '2023-04-09', 0, 1, 0, 15),
(45, 58, '2023-04-09', 3, 0, 0, 54),
(46, 58, '2023-04-03', 2, 3, 0, 114),
(47, 58, '2023-04-09', 1, 0, 0, 24),
(48, 58, '2023-04-03', 1, 3, 0, 90),
(49, 58, '2023-04-20', 0, 2, 0, 44),
(50, 58, '2023-03-09', 1, 0, 0, 18),
(51, 58, '2023-03-30', 2, 2, 2, 120),
(53, 58, '2023-03-10', 4, 2, 0, 100);

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
(5555579, '2023-03-09', '2023-03-19', 18, 'Adult'),
(5555580, '2023-03-09', '2023-03-19', 14, 'Teen'),
(5555581, '2023-03-09', '2023-03-19', 16, 'Student'),
(5555582, '2023-03-26', '2023-03-31', 22, 'Adult'),
(5555583, '2023-03-26', '2023-03-31', 18, 'Teen'),
(5555584, '2023-03-26', '2023-03-31', 20, 'Student'),
(5555585, '2023-04-04', '2023-04-18', 24, 'Adult'),
(5555586, '2023-04-04', '2023-04-18', 22, 'Teen'),
(5555587, '2023-04-26', '2023-04-30', 30, 'Adult'),
(5555588, '2023-04-26', '2023-04-30', 28, 'Student'),
(5555589, '2023-05-05', '2023-05-09', 12, 'Adult');

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
(37, 12056996, 'mohamed aziz', 'loukil', 'mohamedazizloukil@gmail.com', '801add67bc22ff61beab8e41c71835d8ddfd6514039e802f90fefc90d8f9be8c', 'sokra', 22568930, '2001-02-23', 'Male', 'Subscriber', 'unblocked', 'http://localhost/images/aziz33_superman_b267cce2-5ef5-4db7-ae81-4271d2c94475.png', 'O746TGGCU2BPUA72'),
(58, 12056333, 'mohamed', 'Loukil', 'mohamedaziz.loukil@esprit.tn', '801add67bc22ff61beab8e41c71835d8ddfd6514039e802f90fefc90d8f9be8c', 'sokra', 22568933, '2023-03-06', 'Male', 'Artist', 'unblocked', 'http://localhost/images/aziz33_tarzan_ffc9f0c2-fff6-494d-bde5-683822493e43.png', 'PNRI6SJUAFIBBQZG'),
(59, 15062336, 'mohamed', 'laatar', 'laatarmomo@gmail.com', '4745650ac58c6724ffc4dca877da8d21fd9710dc2e9e0c1406d8e9e1e55e8e66', 'ghazela', 22568721, '2005-12-15', 'Male', 'Gallery manager', 'unblocked', 'http://localhost/images/333030573_1485429128655295_2971294285675876785_n.jpg', 'ULSPLOAKS7PODBG6'),
(60, 12056996, 'mohamed fedi', 'ben aoun', 'fedi12.benaoun@gmail.com', 'c281175401fe13710b9b59ff54c02ee6c5c75c0e982d1ed17458d1d4a5a036f8', 'benzart', 77956223, '2008-03-13', 'Male', 'Auction manager', 'unblocked', 'http://localhost/images/aziz33_soldier_ffb01c9c-ab87-400f-b5a4-b7f819bfa17c.png', 'ONXBMA36U5FSK5MZ'),
(63, 12056889, 'mohamed aziz', 'loukil', 'azouzloukil@gmail.com', '801add67bc22ff61beab8e41c71835d8ddfd6514039e802f90fefc90d8f9be8c', 'sokra', 99717964, '2001-07-26', 'Male', 'Admin', 'unblocked', 'http://localhost/images/aziz33_marine_captain_e752b03f-5c4d-464b-bd21-c8d68b90cb10.png', 'JWRFFVXDWUK6OGJB'),
(64, 12256889, 'koussay ', 'bedhief', 'bedhiefkoussay2015@gmail.com', 'da647e52af229e752ea5a9e68a588ddca0989449c24cc473060d72ef7e2e8af1', 'ben arous', 22456889, '2005-03-17', 'Male', 'Events manager', 'unblocked', 'http://localhost/images/325644093_580052893938483_341883584659727359_n.jpg', 'J75QYLOP77QJ6ILE'),
(65, 15263889, 'mohamed amine ', 'tlili', 'aminemehdi999@gmail.com', 'ebf173b0111b8539dbdb798c747365df73fda40bc5d2236ac2855f5bbf4575fd', 'ben arous', 22567812, '2013-03-15', 'Male', 'Tickets manager', 'unblocked', 'http://localhost/images/tlili.png', '25LFPTJHRNZFKUBV');

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
  MODIFY `id_art` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT pour la table `auction`
--
ALTER TABLE `auction`
  MODIFY `id_auction` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT pour la table `bid`
--
ALTER TABLE `bid`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `event`
--
ALTER TABLE `event`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT pour la table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT pour la table `room`
--
ALTER TABLE `room`
  MODIFY `id_room` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `ticket_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5555590;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

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
