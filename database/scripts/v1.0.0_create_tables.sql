-- Create permissions table
CREATE TABLE `permissions` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(50),
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create roles table
CREATE TABLE `roles` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(20),
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create role permissions table
CREATE TABLE `role_permissions` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `roleId` INT NOT NULL,
  `permissionId` INT NOT NULL,
  `active` TINYINT DEFAULT '1',
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `role_permissions_roleId_IDX` (`roleId`),
  KEY `role_permissions_permissionId_IDX` (`permissionId`),
  KEY `role_permissions_active_IDX` (`active`)
);

-- Create users table
CREATE TABLE `users` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `roleId` INT NOT NULL,
  `account_code` VARCHAR(50) NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `password` VARCHAR(120) NOT NULL,
  `active` TINYINT NOT NULL DEFAULT '1',
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `users_unique_email_CON` UNIQUE (`email`),
  CONSTRAINT `users_unique_account_code_CON` UNIQUE (`account_code`),
  KEY `users_roleId_IDX` (`roleId`),
  KEY `users_active_IDX` (`active`)
);

-- Create devices table
CREATE TABLE `devices` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `uuid` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `type` VARCHAR(255) NOT NULL,
  `location` VARCHAR(255) NOT NULL,
  `active` TINYINT NOT NULL DEFAULT '1',
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_created_id` INT NOT NULL,
  `user_updated_id` INT NOT NULL,
  KEY `devices_user_created_id_IDX` (`user_created_id`),
  KEY `devices_user_updated_id_IDX` (`user_updated_id`),
  KEY `devices_uuid_IDX` (`uuid`),
  KEY `devices_active_IDX` (`active`)
);

-- Create html_pages table
CREATE TABLE `html_pages` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `route` VARCHAR(255) NOT NULL,
  `html` TEXT NOT NULL,
  `visible_for_users` BOOLEAN NOT NULL DEFAULT TRUE,
  `visible_for_moderators` BOOLEAN NOT NULL DEFAULT TRUE,
  `visible_on_nav` BOOLEAN NOT NULL DEFAULT TRUE,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_created_id` INT NOT NULL,
  `user_updated_id` INT NOT NULL,
  `active` TINYINT NOT NULL DEFAULT '1',
  CONSTRAINT `html_pages_unique_route_CON` UNIQUE (`route`),
  KEY `html_pages_user_created_id_IDX` (`user_created_id`),
  KEY `html_pages_user_updated_id_IDX` (`user_updated_id`),
  KEY `html_pages_active_IDX` (`active`)
);
