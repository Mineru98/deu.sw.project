SET foreign_key_checks = FALSE;
TRUNCATE `Role`;

INSERT INTO `Role` (`id`, `roleName`, `description`) VALUES
(1, 'ROLE_CEO', '대표이사'),
(2, 'ROLE_STAFF', '직원'),
(3, 'ROLE_MANAGER', '부서관리자');

SET foreign_key_checks = TRUE;