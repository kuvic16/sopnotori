CREATE TABLE emis.`location` (
  `id` varchar(36) NOT NULL,
  `created_by_date` datetime DEFAULT NULL,
  `created_by_id` varchar(36) DEFAULT NULL,
  `created_by_name` varchar(150) DEFAULT NULL,
  `last_modified_by_date` datetime DEFAULT NULL,
  `last_modified_by_id` varchar(36) DEFAULT NULL,
  `last_modified_by_name` varchar(150) DEFAULT NULL,
  `alternativ_name` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `hierarchy_id` varchar(1000) DEFAULT NULL,
  `hierarchy_name` varchar(1000) DEFAULT NULL,
  `latitude` decimal(19,2) DEFAULT NULL,
  `longitude` decimal(19,2) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(36) DEFAULT NULL,
  `location_code` int(36) DEFAULT NULL,
  `parent_location_id` varchar(36) DEFAULT NULL,
  `hrd_id` int(11) DEFAULT NULL,
  `mis_code` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Index_name` (`name`),
  KEY `Index_parent_id` (`parent_id`)
)
ENGINE=FEDERATED
DEFAULT CHARSET=utf8
CONNECTION='mysql://root:99.9%available@brachubdb.brac.net:3306/brachub/location';