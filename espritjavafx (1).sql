-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 16 mars 2025 à 14:03
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `espritjavafx`
--

-- --------------------------------------------------------

--
-- Structure de la table `adhd_responses`
--

CREATE TABLE `adhd_responses` (
  `id` int(11) NOT NULL,
  `category` varchar(50) DEFAULT NULL,
  `pregnancy_complicated` tinyint(1) DEFAULT NULL,
  `premature_birth` tinyint(1) DEFAULT NULL,
  `birth_complications` tinyint(1) DEFAULT NULL,
  `learning_delay` tinyint(1) DEFAULT NULL,
  `hyperactive` tinyint(1) DEFAULT NULL,
  `sleep_issues` tinyint(1) DEFAULT NULL,
  `family_tensions` tinyint(1) DEFAULT NULL,
  `family_adhd` tinyint(1) DEFAULT NULL,
  `screen_time` tinyint(1) DEFAULT NULL,
  `concentration_issue` tinyint(1) DEFAULT NULL,
  `loses_items` tinyint(1) DEFAULT NULL,
  `impatience` tinyint(1) DEFAULT NULL,
  `impulsive` tinyint(1) DEFAULT NULL,
  `emotional_issues` tinyint(1) DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `adhd_responses`
--

INSERT INTO `adhd_responses` (`id`, `category`, `pregnancy_complicated`, `premature_birth`, `birth_complications`, `learning_delay`, `hyperactive`, `sleep_issues`, `family_tensions`, `family_adhd`, `screen_time`, `concentration_issue`, `loses_items`, `impatience`, `impulsive`, `emotional_issues`, `user_id`) VALUES
(16, 'Parent', 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 123456);

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`admin_id`, `email`, `username`, `password`, `full_name`, `image`, `gender`, `date`) VALUES
(1, 'admin@gmail.com', 'admin', 'admin123', NULL, NULL, 'Male', '2025-02-16'),
(2, 'test', 'test', 'testfefeaz', NULL, NULL, NULL, '2025-02-16');

-- --------------------------------------------------------

--
-- Structure de la table `appointment`
--

CREATE TABLE `appointment` (
  `id` int(11) NOT NULL,
  `appointment_id` int(50) NOT NULL,
  `patient_id` bigint(50) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  `diagnosis` varchar(255) DEFAULT NULL,
  `treatment` varchar(255) DEFAULT NULL,
  `mobile_number` bigint(50) NOT NULL,
  `address` varchar(500) NOT NULL,
  `date` date NOT NULL,
  `date_modify` date DEFAULT NULL,
  `date_delete` date DEFAULT NULL,
  `status` varchar(50) NOT NULL,
  `doctor` varchar(50) NOT NULL,
  `specialized` varchar(100) DEFAULT NULL,
  `schedule` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `appointment`
--

INSERT INTO `appointment` (`id`, `appointment_id`, `patient_id`, `name`, `gender`, `description`, `diagnosis`, `treatment`, `mobile_number`, `address`, `date`, `date_modify`, `date_delete`, `status`, `doctor`, `specialized`, `schedule`) VALUES
(1, 1, 123, 'test', 'male', 'tesssstttttttt', 'wwwwwwwwwwwww', 'TTTTTTTTTTTTTTTT', 444444444444444, '4FZEFS', '2025-02-20', NULL, NULL, 'Active', '123', 'TEST', '2025-02-28'),
(2, 2, 123, 'BHJJ', 'Female', 'TRDJFKG', 'DFJGH', 'FGHJ', 346576878, 'FJGH', '2025-02-19', NULL, NULL, 'Inactive', '123', NULL, '2025-02-01'),
(3, 3, 123, 'BHJJ', 'Female', 'TRDJFKG', 'DFJGH', 'FGHJ', 346576878, 'FJGH', '2025-02-19', '2025-02-19', NULL, 'Confirm', '123', NULL, '2025-02-20'),
(4, 4, 123, 'BHJJ', 'Female', 'TRDJFKG', 'DFJGH', 'FGHJ', 346576878, 'FJGH', '2025-02-19', NULL, NULL, 'Inactive', '123', NULL, '2025-02-01'),
(5, 5, 123, 'BHJJ', 'Female', 'TRDJFKG', 'DFJGH', 'FGHJ', 346576878, 'FJGH', '2025-02-19', NULL, '2025-02-19', 'Inactive', '123', NULL, '2025-02-01'),
(6, 6, 123, 'FEZFEZ', 'Female', 'dzdz', NULL, NULL, 53413234, 'YRZEDDB35654', '2025-02-22', NULL, NULL, 'Active', '1234', 'Dermatologist', '2025-02-22'),
(7, 7, 123, 'sdhfjghkl', 'Female', 'gfgfdgsd', NULL, NULL, 234567, 'GKL', '2025-03-11', NULL, NULL, 'Active', '123', 'Ophthalmologist', '2025-03-11'),
(8, 8, 123, 'sdhfjghkl', 'Female', 'nada slama', NULL, NULL, 234567, 'GKL', '2025-03-16', NULL, NULL, 'Active', '123', 'Ophthalmologist', '2025-03-16');

-- --------------------------------------------------------

--
-- Structure de la table `doctor`
--

CREATE TABLE `doctor` (
  `id` int(11) NOT NULL,
  `doctor_id` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `mobile_number` bigint(100) DEFAULT NULL,
  `specialized` varchar(100) DEFAULT NULL,
  `address` varchar(200) NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `date` date NOT NULL,
  `modify_date` date DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `doctor`
--

INSERT INTO `doctor` (`id`, `doctor_id`, `password`, `full_name`, `email`, `gender`, `mobile_number`, `specialized`, `address`, `image`, `date`, `modify_date`, `delete_date`, `status`) VALUES
(1, '123', 'doctor123', 'wwwwwwwww', 'fqs@fad.com', 'men', 9876543, 'Ophthalmologist', 'GEZRE323AZ', NULL, '2025-02-19', '2025-02-20', NULL, 'Active'),
(2, '1234', 'doctor1234', 'DOCTEST', 'LGGJK@JM.C', 'men', 5343, 'Dermatologist', 'FGDSFD', NULL, '2025-02-19', NULL, NULL, 'Inactive');

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE `patient` (
  `id` int(11) NOT NULL,
  `patient_id` bigint(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `mobile_number` bigint(50) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `diagnosis` varchar(200) DEFAULT NULL,
  `treatment` varchar(200) DEFAULT NULL,
  `doctor` varchar(100) DEFAULT NULL,
  `specialized` varchar(100) DEFAULT NULL,
  `date` date NOT NULL,
  `date_modify` date DEFAULT NULL,
  `date_delete` date DEFAULT NULL,
  `status` varchar(100) NOT NULL,
  `status_pay` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`id`, `patient_id`, `password`, `full_name`, `gender`, `mobile_number`, `address`, `image`, `description`, `diagnosis`, `treatment`, `doctor`, `specialized`, `date`, `date_modify`, `date_delete`, `status`, `status_pay`, `email`) VALUES
(18, 123, '123', 'nada slama', 'Female', 95885335, 'sousse', NULL, NULL, NULL, NULL, NULL, NULL, '2025-03-15', NULL, NULL, 'Active', NULL, 'nadaSlama @gmail.com'),
(31, 123456, '123456', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2025-03-16', NULL, NULL, 'Active', NULL, 'nada@slama'),
(32, 123852, '12852', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2025-03-16', NULL, NULL, 'Active', NULL, 'nada@slama');

-- --------------------------------------------------------

--
-- Structure de la table `payment`
--

CREATE TABLE `payment` (
  `Id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `doctor` varchar(250) DEFAULT NULL,
  `total_days` double NOT NULL,
  `total_price` double NOT NULL,
  `date` date NOT NULL,
  `date_checkout` date NOT NULL,
  `date_pay` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `payment`
--

INSERT INTO `payment` (`Id`, `patient_id`, `doctor`, `total_days`, `total_price`, `date`, `date_checkout`, `date_pay`) VALUES
(1, 4123231, NULL, -7, -143.5, '2025-02-20', '2025-02-13', '2025-02-23'),
(2, 321, NULL, 10, 205, '2025-02-20', '2025-03-02', '2025-02-23');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adhd_responses`
--
ALTER TABLE `adhd_responses`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- Index pour la table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `adhd_responses`
--
ALTER TABLE `adhd_responses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT pour la table `payment`
--
ALTER TABLE `payment`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
