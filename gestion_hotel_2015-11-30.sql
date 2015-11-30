# ************************************************************
# Sequel Pro SQL dump
# Version 4135
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.5.38)
# Database: gestion_hotel
# Generation Time: 2015-11-30 08:37:27 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table categorie
# ------------------------------------------------------------

DROP TABLE IF EXISTS `categorie`;

CREATE TABLE `categorie` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `categorie` WRITE;
/*!40000 ALTER TABLE `categorie` DISABLE KEYS */;

INSERT INTO `categorie` (`id`, `libelle`)
VALUES
	(1,'Chambre classique vue Cap'),
	(2,'Chambre classique vue Mer'),
	(3,'Chambre supérieure vue Mer'),
	(4,'Chambre luxe vue Mer'),
	(5,'Suite luxe vue Mer');

/*!40000 ALTER TABLE `categorie` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table chambre
# ------------------------------------------------------------

DROP TABLE IF EXISTS `chambre`;

CREATE TABLE `chambre` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `hotel_id` int(11) DEFAULT NULL,
  `categorie_id` int(10) DEFAULT NULL,
  `num_chambre` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKChambre571180` (`categorie_id`),
  KEY `FKChambre20131` (`hotel_id`),
  CONSTRAINT `FKChambre20131` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`),
  CONSTRAINT `FKChambre571180` FOREIGN KEY (`categorie_id`) REFERENCES `categorie` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `chambre` WRITE;
/*!40000 ALTER TABLE `chambre` DISABLE KEYS */;

INSERT INTO `chambre` (`id`, `hotel_id`, `categorie_id`, `num_chambre`)
VALUES
	(1,1,1,1),
	(2,1,1,2),
	(3,1,1,3),
	(4,1,1,4),
	(5,1,1,5),
	(6,1,1,6),
	(7,1,1,7),
	(8,1,1,8),
	(9,1,1,9),
	(10,1,1,10),
	(11,1,1,11),
	(12,1,1,12),
	(13,1,1,13),
	(14,1,1,14),
	(15,1,1,15),
	(16,1,1,16),
	(17,1,1,17),
	(18,1,1,18),
	(19,1,1,19),
	(20,1,1,20),
	(21,1,1,21),
	(22,1,1,22),
	(23,1,1,23),
	(24,1,1,24),
	(25,1,1,25),
	(26,1,1,26),
	(27,1,1,27),
	(28,1,1,28),
	(29,1,1,29),
	(30,1,1,30),
	(31,1,1,31),
	(32,1,1,32),
	(33,1,1,33),
	(34,1,1,34),
	(35,1,1,35),
	(36,1,1,36),
	(37,1,1,37),
	(38,1,1,38),
	(39,1,1,39),
	(40,1,2,40),
	(41,1,2,41),
	(42,1,2,42),
	(43,1,2,43),
	(44,1,2,44),
	(45,1,2,45),
	(46,1,2,46),
	(47,1,2,47),
	(48,1,2,48),
	(49,1,2,49),
	(50,1,2,50),
	(51,1,2,51),
	(52,1,2,52),
	(53,1,2,53),
	(54,1,2,54),
	(55,1,2,55),
	(56,1,2,56),
	(57,1,2,57),
	(58,1,2,58),
	(59,1,2,59),
	(60,1,2,60),
	(61,1,2,61),
	(62,1,2,62),
	(63,1,2,63),
	(64,1,2,64),
	(65,1,2,65),
	(66,1,2,66),
	(67,1,2,67),
	(68,1,2,68),
	(69,1,2,69),
	(70,1,2,70),
	(71,1,2,71),
	(72,1,2,72),
	(73,1,2,73),
	(74,1,2,74),
	(75,1,2,75),
	(76,1,2,76),
	(77,1,2,77),
	(78,1,2,78),
	(79,1,2,79),
	(80,1,2,80),
	(81,2,1,1),
	(82,2,1,2),
	(83,2,1,3),
	(84,2,1,4),
	(85,2,1,5),
	(86,2,1,6),
	(87,2,1,7),
	(88,2,1,8),
	(89,2,1,9),
	(90,2,1,10),
	(91,2,1,11),
	(92,2,1,12),
	(93,2,1,13),
	(94,2,1,14),
	(95,2,1,15),
	(96,2,1,16),
	(97,2,1,17),
	(98,2,1,18),
	(99,2,1,19),
	(100,2,1,20),
	(101,2,1,21),
	(102,2,1,22),
	(103,2,1,23),
	(104,2,1,24),
	(105,2,1,25),
	(106,2,1,26),
	(107,2,1,27),
	(108,2,1,28),
	(109,2,1,29),
	(110,2,1,30),
	(111,2,1,31),
	(112,2,1,32),
	(113,2,1,33),
	(114,2,1,34),
	(115,2,1,35),
	(116,2,1,36),
	(117,2,1,37),
	(118,2,1,38),
	(119,2,1,39),
	(120,2,1,40),
	(121,2,2,41),
	(122,2,2,42),
	(123,2,2,43),
	(124,2,2,44),
	(125,2,2,45),
	(126,2,2,46),
	(127,2,2,47),
	(128,2,2,48),
	(129,2,2,49),
	(130,2,2,50),
	(131,2,2,51),
	(132,2,2,52),
	(133,2,2,53),
	(134,2,2,54),
	(135,2,2,55),
	(136,2,2,56),
	(137,2,2,57),
	(138,2,2,58),
	(139,2,2,59),
	(140,2,2,60),
	(141,2,2,61),
	(142,2,2,62),
	(143,2,2,63),
	(144,2,2,64),
	(145,2,2,65),
	(146,2,2,66),
	(147,2,2,67),
	(148,2,2,68),
	(149,2,2,69),
	(150,2,2,70),
	(151,2,2,71),
	(152,2,2,72),
	(153,2,2,73),
	(154,2,2,74),
	(155,2,2,75),
	(156,2,2,76),
	(157,2,2,77),
	(158,2,2,78),
	(159,2,2,79),
	(160,2,2,80),
	(161,3,1,1),
	(162,3,1,2),
	(163,3,1,3),
	(164,3,1,4),
	(165,3,1,5),
	(166,3,1,6),
	(167,3,1,7),
	(168,3,1,8),
	(169,3,1,9),
	(170,3,1,10),
	(171,3,1,11),
	(172,3,1,12),
	(173,3,1,13),
	(174,3,1,14),
	(175,3,1,15),
	(176,3,1,16),
	(177,3,1,17),
	(178,3,1,18),
	(179,3,1,19),
	(180,3,1,20),
	(181,3,1,21),
	(182,3,1,22),
	(183,3,1,23),
	(184,3,1,24),
	(185,3,1,25),
	(186,3,1,26),
	(187,3,1,27),
	(188,3,1,28),
	(189,3,1,29),
	(190,3,1,30),
	(191,3,1,31),
	(192,3,1,32),
	(193,3,1,33),
	(194,3,1,34),
	(195,3,1,35),
	(196,3,1,36),
	(197,3,1,37),
	(198,3,1,38),
	(199,3,1,39),
	(200,3,1,40),
	(201,3,2,41),
	(202,3,2,42),
	(203,3,2,43),
	(204,3,2,44),
	(205,3,2,45),
	(206,3,2,46),
	(207,3,2,47),
	(208,3,2,48),
	(209,3,2,49),
	(210,3,2,50),
	(211,3,2,51),
	(212,3,2,52),
	(213,3,2,53),
	(214,3,2,54),
	(215,3,2,55),
	(216,3,2,56),
	(217,3,2,57),
	(218,3,2,58),
	(219,3,2,59),
	(220,3,2,60),
	(221,3,2,61),
	(222,3,2,62),
	(223,3,2,63),
	(224,3,2,64),
	(225,3,2,65),
	(226,3,2,66),
	(227,3,2,67),
	(228,3,2,68),
	(229,3,2,69),
	(230,3,2,70),
	(231,3,2,71),
	(232,3,2,72),
	(233,3,2,73),
	(234,3,2,74),
	(235,3,2,75),
	(236,3,2,76),
	(237,3,2,77),
	(238,3,2,78),
	(239,3,2,79),
	(240,3,2,80),
	(241,4,1,1),
	(242,4,1,2),
	(243,4,1,3),
	(244,4,1,4),
	(245,4,1,5),
	(246,4,1,6),
	(247,4,1,7),
	(248,4,1,8),
	(249,4,1,9),
	(250,4,1,10),
	(251,4,1,11),
	(252,4,1,12),
	(253,4,1,13),
	(254,4,1,14),
	(255,4,1,15),
	(256,4,1,16),
	(257,4,1,17),
	(258,4,1,18),
	(259,4,1,19),
	(260,4,1,20),
	(261,4,2,21),
	(262,4,2,22),
	(263,4,2,23),
	(264,4,2,24),
	(265,4,2,25),
	(266,4,2,26),
	(267,4,2,27),
	(268,4,2,28),
	(269,4,2,29),
	(270,4,2,30),
	(271,4,2,31),
	(272,4,2,32),
	(273,4,2,33),
	(274,4,2,34),
	(275,4,2,35),
	(276,4,2,36),
	(277,4,2,37),
	(278,4,2,38),
	(279,4,2,39),
	(280,4,2,40),
	(281,4,3,41),
	(282,4,3,42),
	(283,4,3,43),
	(284,4,3,44),
	(285,4,3,45),
	(286,4,3,46),
	(287,4,3,47),
	(288,4,3,48),
	(289,4,3,49),
	(290,4,3,50),
	(291,4,3,51),
	(292,4,3,52),
	(293,4,3,53),
	(294,4,3,54),
	(295,4,3,55),
	(296,4,3,56),
	(297,4,3,57),
	(298,4,3,58),
	(299,4,3,59),
	(300,4,3,60),
	(301,4,4,61),
	(302,4,4,62),
	(303,4,4,63),
	(304,4,4,64),
	(305,4,4,65),
	(306,4,4,66),
	(307,4,4,67),
	(308,4,4,68),
	(309,4,4,69),
	(310,4,4,70),
	(311,4,4,71),
	(312,4,4,72),
	(313,4,4,73),
	(314,4,4,74),
	(315,4,4,75),
	(316,4,4,76),
	(317,4,4,77),
	(318,4,4,78),
	(319,4,4,79),
	(320,4,4,80),
	(321,5,1,1),
	(322,5,1,2),
	(323,5,1,3),
	(324,5,1,4),
	(325,5,1,5),
	(326,5,1,6),
	(327,5,1,7),
	(328,5,1,8),
	(329,5,1,9),
	(330,5,1,10),
	(331,5,1,11),
	(332,5,1,12),
	(333,5,1,13),
	(334,5,1,14),
	(335,5,1,15),
	(336,5,1,16),
	(337,5,1,17),
	(338,5,1,18),
	(339,5,1,19),
	(340,5,1,20),
	(341,5,2,21),
	(342,5,2,22),
	(343,5,2,23),
	(344,5,2,24),
	(345,5,2,25),
	(346,5,2,26),
	(347,5,2,27),
	(348,5,2,28),
	(349,5,2,29),
	(350,5,2,30),
	(351,5,2,31),
	(352,5,2,32),
	(353,5,2,33),
	(354,5,2,34),
	(355,5,2,35),
	(356,5,2,36),
	(357,5,2,37),
	(358,5,2,38),
	(359,5,2,39),
	(360,5,2,40),
	(361,5,3,41),
	(362,5,3,42),
	(363,5,3,43),
	(364,5,3,44),
	(365,5,3,45),
	(366,5,3,46),
	(367,5,3,47),
	(368,5,3,48),
	(369,5,3,49),
	(370,5,3,50),
	(371,5,3,51),
	(372,5,3,52),
	(373,5,3,53),
	(374,5,3,54),
	(375,5,3,55),
	(376,5,3,56),
	(377,5,3,57),
	(378,5,3,58),
	(379,5,3,59),
	(380,5,3,60),
	(381,5,4,61),
	(382,5,4,62),
	(383,5,4,63),
	(384,5,4,64),
	(385,5,4,65),
	(386,5,4,66),
	(387,5,4,67),
	(388,5,4,68),
	(389,5,4,69),
	(390,5,4,70),
	(391,5,4,71),
	(392,5,4,72),
	(393,5,4,73),
	(394,5,4,74),
	(395,5,4,75),
	(396,5,4,76),
	(397,5,4,77),
	(398,5,4,78),
	(399,5,4,79),
	(400,5,4,80),
	(401,6,1,1),
	(402,6,1,2),
	(403,6,1,3),
	(404,6,1,4),
	(405,6,1,5),
	(406,6,1,6),
	(407,6,1,7),
	(408,6,1,8),
	(409,6,1,9),
	(410,6,1,10),
	(411,6,1,11),
	(412,6,1,12),
	(413,6,1,13),
	(414,6,1,14),
	(415,6,1,15),
	(416,6,1,16),
	(417,6,1,17),
	(418,6,1,18),
	(419,6,1,19),
	(420,6,1,20),
	(421,6,2,21),
	(422,6,2,22),
	(423,6,2,23),
	(424,6,2,24),
	(425,6,2,25),
	(426,6,2,26),
	(427,6,2,27),
	(428,6,2,28),
	(429,6,2,29),
	(430,6,2,30),
	(431,6,2,31),
	(432,6,2,32),
	(433,6,2,33),
	(434,6,2,34),
	(435,6,2,35),
	(436,6,2,36),
	(437,6,2,37),
	(438,6,2,38),
	(439,6,2,39),
	(440,6,2,40),
	(441,6,3,41),
	(442,6,3,42),
	(443,6,3,43),
	(444,6,3,44),
	(445,6,3,45),
	(446,6,3,46),
	(447,6,3,47),
	(448,6,3,48),
	(449,6,3,49),
	(450,6,3,50),
	(451,6,3,51),
	(452,6,3,52),
	(453,6,3,53),
	(454,6,3,54),
	(455,6,3,55),
	(456,6,3,56),
	(457,6,3,57),
	(458,6,3,58),
	(459,6,3,59),
	(460,6,3,60),
	(461,6,4,61),
	(462,6,4,62),
	(463,6,4,63),
	(464,6,4,64),
	(465,6,4,65),
	(466,6,4,66),
	(467,6,4,67),
	(468,6,4,68),
	(469,6,4,69),
	(470,6,4,70),
	(471,6,4,71),
	(472,6,4,72),
	(473,6,4,73),
	(474,6,4,74),
	(475,6,4,75),
	(476,6,4,76),
	(477,6,4,77),
	(478,6,4,78),
	(479,6,4,79),
	(480,6,4,80),
	(481,7,1,1),
	(482,7,1,2),
	(483,7,1,3),
	(484,7,1,4),
	(485,7,1,5),
	(486,7,1,6),
	(487,7,1,7),
	(488,7,1,8),
	(489,7,1,9),
	(490,7,1,10),
	(491,7,1,11),
	(492,7,1,12),
	(493,7,1,13),
	(494,7,1,14),
	(495,7,1,15),
	(496,7,1,16),
	(497,7,1,17),
	(498,7,1,18),
	(499,7,1,19),
	(500,7,1,20),
	(501,7,1,21),
	(502,7,1,22),
	(503,7,1,23),
	(504,7,1,24),
	(505,7,1,25),
	(506,7,1,26),
	(507,7,1,27),
	(508,7,1,28),
	(509,7,1,29),
	(510,7,1,30),
	(511,7,1,31),
	(512,7,1,32),
	(513,7,1,33),
	(514,7,1,34),
	(515,7,1,35),
	(516,7,1,36),
	(517,7,1,37),
	(518,7,1,38),
	(519,7,1,39),
	(520,7,1,40),
	(521,7,2,41),
	(522,7,2,42),
	(523,7,2,43),
	(524,7,2,44),
	(525,7,2,45),
	(526,7,2,46),
	(527,7,2,47),
	(528,7,2,48),
	(529,7,2,49),
	(530,7,2,50),
	(531,7,2,51),
	(532,7,2,52),
	(533,7,2,53),
	(534,7,2,54),
	(535,7,2,55),
	(536,7,2,56),
	(537,7,2,57),
	(538,7,2,58),
	(539,7,2,59),
	(540,7,2,60),
	(541,7,2,61),
	(542,7,2,62),
	(543,7,2,63),
	(544,7,2,64),
	(545,7,2,65),
	(546,7,2,66),
	(547,7,2,67),
	(548,7,2,68),
	(549,7,2,69),
	(550,7,2,70),
	(551,7,2,71),
	(552,7,2,72),
	(553,7,2,73),
	(554,7,2,74),
	(555,7,2,75),
	(556,7,2,76),
	(557,7,2,77),
	(558,7,2,78),
	(559,7,2,79),
	(560,7,2,80),
	(561,8,1,1),
	(562,8,1,2),
	(563,8,1,3),
	(564,8,1,4),
	(565,8,1,5),
	(566,8,1,6),
	(567,8,1,7),
	(568,8,1,8),
	(569,8,1,9),
	(570,8,1,10),
	(571,8,1,11),
	(572,8,1,12),
	(573,8,1,13),
	(574,8,1,14),
	(575,8,1,15),
	(576,8,2,16),
	(577,8,2,17),
	(578,8,2,18),
	(579,8,2,19),
	(580,8,2,20),
	(581,8,2,21),
	(582,8,2,22),
	(583,8,2,23),
	(584,8,2,24),
	(585,8,2,25),
	(586,8,2,26),
	(587,8,2,27),
	(588,8,2,28),
	(589,8,2,29),
	(590,8,2,30),
	(591,8,3,31),
	(592,8,3,32),
	(593,8,3,33),
	(594,8,3,34),
	(595,8,3,35),
	(596,8,3,36),
	(597,8,3,37),
	(598,8,3,38),
	(599,8,3,39),
	(600,8,3,40),
	(601,8,3,41),
	(602,8,3,42),
	(603,8,3,43),
	(604,8,3,44),
	(605,8,3,45),
	(606,8,3,46),
	(607,8,3,47),
	(608,8,4,48),
	(609,8,4,49),
	(610,8,4,50),
	(611,8,4,51),
	(612,8,4,52),
	(613,8,4,53),
	(614,8,4,54),
	(615,8,4,55),
	(616,8,4,56),
	(617,8,4,57),
	(618,8,4,58),
	(619,8,4,59),
	(620,8,4,60),
	(621,8,4,61),
	(622,8,4,62),
	(623,8,5,63),
	(624,8,5,64),
	(625,8,5,65),
	(626,8,5,66),
	(627,8,5,67),
	(628,8,5,68),
	(629,8,5,69),
	(630,8,5,70),
	(631,8,5,71),
	(632,8,5,72),
	(633,8,5,73),
	(634,8,5,74),
	(635,8,5,75),
	(636,8,5,76),
	(637,8,5,77),
	(638,8,5,78),
	(639,8,5,79),
	(640,8,5,80);

/*!40000 ALTER TABLE `chambre` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table classe
# ------------------------------------------------------------

DROP TABLE IF EXISTS `classe`;

CREATE TABLE `classe` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nb_etoiles` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `classe` WRITE;
/*!40000 ALTER TABLE `classe` DISABLE KEYS */;

INSERT INTO `classe` (`id`, `nb_etoiles`)
VALUES
	(1,2),
	(2,3),
	(3,4);

/*!40000 ALTER TABLE `classe` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table client
# ------------------------------------------------------------

DROP TABLE IF EXISTS `client`;

CREATE TABLE `client` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `adresse` varchar(50) NOT NULL,
  `ville` varchar(25) NOT NULL,
  `naissance` varchar(25) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;

INSERT INTO `client` (`id`, `nom`, `prenom`, `adresse`, `ville`, `naissance`)
VALUES
	(1,'Bon','Jean','3 Rue des Fleurs','Nancy',''),
	(2,'Lumière','Alphonse','6 Chemin des Pierres','Lyon',''),
	(3,'Curie','Marie','7bis Rue des Camélias','Paris',''),
	(4,'Curie','Pierre','7bis Rue des Camélias','Paris',''),
	(5,'Crepe','Pata','6 Avenue des Fleurs','Montepellier',''),
	(6,'BIbi','bsfd','3','ksd',''),
	(7,'16/11/15','Pata','dss','d','');

/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table facture
# ------------------------------------------------------------

DROP TABLE IF EXISTS `facture`;

CREATE TABLE `facture` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `client_id` int(10) DEFAULT NULL,
  `total` int(10) DEFAULT NULL,
  `paye` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKFacture14832` (`client_id`),
  CONSTRAINT `FKFacture14832` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `facture` WRITE;
/*!40000 ALTER TABLE `facture` DISABLE KEYS */;

INSERT INTO `facture` (`id`, `client_id`, `total`, `paye`)
VALUES
	(2,1,0,0),
	(3,1,0,0),
	(4,2,0,0),
	(5,2,0,0),
	(6,2,0,0),
	(7,2,0,0),
	(8,2,0,0);

/*!40000 ALTER TABLE `facture` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table hotel
# ------------------------------------------------------------

DROP TABLE IF EXISTS `hotel`;

CREATE TABLE `hotel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classe_id` int(10) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKHotel108708` (`classe_id`),
  CONSTRAINT `FKHotel108708` FOREIGN KEY (`classe_id`) REFERENCES `classe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;

INSERT INTO `hotel` (`id`, `classe_id`, `nom`, `adresse`)
VALUES
	(1,1,'Hôtel de la Chouette','5 Rue des Champignons'),
	(2,1,'Hôtel du Renard','16 Avenue du Flan'),
	(3,1,'Le José Palace','5 Rue de la moustache'),
	(4,2,'Cookie Palace','187 Grand Rue'),
	(5,2,'Hôtel des Myrtilles','136 bis Rue du Maréchal Gourmand'),
	(6,2,'Hôtel du Milk-Shake','23 Avenue Chocolat'),
	(7,3,'Grand Budapest Hotel','2 Chemin des Nuggets'),
	(8,3,'Hôtel Calypso','65 Avenue Malibu');

/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table prestation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `prestation`;

CREATE TABLE `prestation` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `classe_id` int(10) DEFAULT NULL,
  `categorie_id` int(10) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `tarif_jour` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKPrestation706059` (`categorie_id`),
  KEY `FKPrestation527323` (`classe_id`),
  CONSTRAINT `FKPrestation527323` FOREIGN KEY (`classe_id`) REFERENCES `classe` (`id`),
  CONSTRAINT `FKPrestation706059` FOREIGN KEY (`categorie_id`) REFERENCES `categorie` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `prestation` WRITE;
/*!40000 ALTER TABLE `prestation` DISABLE KEYS */;

INSERT INTO `prestation` (`id`, `classe_id`, `categorie_id`, `libelle`, `tarif_jour`)
VALUES
	(1,1,1,'Wifi',5),
	(2,1,1,'Parking',2),
	(3,1,1,'Coffre-fort',2),
	(4,1,1,'Vélo',3),
	(5,1,2,'Wifi',5),
	(6,1,2,'Parking',2),
	(7,1,2,'Coffre-fort',2),
	(8,1,2,'Vélo',3),
	(9,2,1,'Wifi',5),
	(10,2,1,'Parking',2),
	(11,2,1,'Coffre-fort',2),
	(12,2,1,'Vélo',3),
	(13,2,2,'Wifi',5),
	(14,2,2,'Parking',2),
	(15,2,2,'Coffre-fort',2),
	(16,2,2,'Vélo',3),
	(17,2,3,'Wifi',5),
	(18,2,3,'Parking',2),
	(19,2,3,'Coffre-fort',2),
	(20,2,3,'Vélo',3),
	(21,2,4,'Wifi',5),
	(22,2,4,'Parking',2),
	(23,2,4,'Coffre-fort',2),
	(24,2,4,'Vélo',3),
	(25,3,1,'Wifi',0),
	(26,3,1,'Parking',0),
	(27,3,1,'Coffre-fort',2),
	(28,3,1,'Vélo',3),
	(29,3,1,'Spa',30),
	(30,3,1,'Salle de fitness',20),
	(31,3,2,'Wifi',0),
	(32,3,2,'Parking',0),
	(33,3,2,'Coffre-fort',2),
	(34,3,2,'Vélo',3),
	(35,3,2,'Spa',30),
	(36,3,2,'Salle de fitness',20),
	(37,3,3,'Wifi',0),
	(38,3,3,'Parking',0),
	(39,3,3,'Coffre-fort',2),
	(40,3,3,'Vélo',3),
	(41,3,3,'Spa',30),
	(42,3,3,'Salle de fitness',20),
	(43,3,4,'Wifi',0),
	(44,3,4,'Parking',0),
	(45,3,4,'Coffre-fort',0),
	(46,3,4,'Vélo',3),
	(47,3,4,'Spa',30),
	(48,3,4,'Salle de fitness',20),
	(49,3,5,'Wifi',0),
	(50,3,5,'Parking',0),
	(51,3,5,'Coffre-fort',0),
	(52,3,5,'Vélo',0),
	(53,3,5,'Spa',30),
	(54,3,5,'Salle de fitness',20);

/*!40000 ALTER TABLE `prestation` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table reservation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `reservation`;

CREATE TABLE `reservation` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `chambre_id` int(10) DEFAULT NULL,
  `facture_id` int(10) DEFAULT NULL,
  `client_id` int(10) DEFAULT NULL,
  `nb_pers` int(10) DEFAULT NULL,
  `date_deb` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKreservatio983777` (`client_id`),
  KEY `FKreservatio785211` (`facture_id`),
  KEY `FKreservatio780195` (`chambre_id`),
  CONSTRAINT `FKreservatio780195` FOREIGN KEY (`chambre_id`) REFERENCES `chambre` (`id`),
  CONSTRAINT `FKreservatio785211` FOREIGN KEY (`facture_id`) REFERENCES `facture` (`id`),
  CONSTRAINT `FKreservatio983777` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;

INSERT INTO `reservation` (`id`, `chambre_id`, `facture_id`, `client_id`, `nb_pers`, `date_deb`, `date_fin`)
VALUES
	(5,1,3,1,1,'1992-04-12','1992-04-15'),
	(10,80,8,2,2,'1992-01-10','1992-01-13');

/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tarif
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tarif`;

CREATE TABLE `tarif` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nb_jours` int(10) DEFAULT NULL,
  `total_prest` int(10) DEFAULT NULL,
  `facture_id` int(10) DEFAULT NULL,
  `prestation_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKTarif555783` (`facture_id`),
  KEY `FKTarif685935` (`prestation_id`),
  CONSTRAINT `FKTarif555783` FOREIGN KEY (`facture_id`) REFERENCES `facture` (`id`),
  CONSTRAINT `FKTarif685935` FOREIGN KEY (`prestation_id`) REFERENCES `Prestation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table tarif_chambre
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tarif_chambre`;

CREATE TABLE `tarif_chambre` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `prix_b_1` int(11) DEFAULT NULL,
  `prix_b_2` int(11) DEFAULT NULL,
  `prix_h_1` int(11) DEFAULT NULL,
  `prix_h_2` int(11) DEFAULT NULL,
  `classe_id` int(11) DEFAULT NULL,
  `categorie_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `classe_id` (`classe_id`),
  KEY `categorie_id` (`categorie_id`),
  CONSTRAINT `tarif_chambre_ibfk_1` FOREIGN KEY (`classe_id`) REFERENCES `classe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tarif_chambre_ibfk_2` FOREIGN KEY (`categorie_id`) REFERENCES `categorie` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `tarif_chambre` WRITE;
/*!40000 ALTER TABLE `tarif_chambre` DISABLE KEYS */;

INSERT INTO `tarif_chambre` (`id`, `prix_b_1`, `prix_b_2`, `prix_h_1`, `prix_h_2`, `classe_id`, `categorie_id`)
VALUES
	(1,55,100,65,110,1,1),
	(2,55,100,65,110,1,2),
	(3,65,115,80,140,2,1),
	(4,65,115,80,140,2,2),
	(5,80,125,90,165,2,3),
	(6,100,170,120,190,2,4),
	(7,70,120,90,165,3,1),
	(8,70,120,90,165,3,2),
	(9,90,140,115,200,3,3),
	(10,110,190,130,210,3,4),
	(11,145,220,170,300,3,5);

/*!40000 ALTER TABLE `tarif_chambre` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
