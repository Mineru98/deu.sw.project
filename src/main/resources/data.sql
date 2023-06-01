SET
foreign_key_checks = FALSE;

TRUNCATE `Role`;
INSERT INTO `Role` (`id`, `roleName`, `description`)
VALUES (1, 'ROLE_CEO', '대표이사'),
       (2, 'ROLE_STAFF', '직원'),
       (3, 'ROLE_MANAGER', '부서관리자');

TRUNCATE `Rank`;
INSERT INTO `Rank` (`id`, `rankName`)
VALUES (1, '대표이사'),
       (2, '팀장'),
       (3, '사원');

TRUNCATE `Company`;
INSERT INTO `Company` (`id`, `companyName`, `settlementDate`, `address`, `isMain`)
VALUES (1, '바디메이트(본사)', '2021-01-01', '', 1),
       (2, '바디메이트(부산지사)', '2021-10-01', '', 0);

TRUNCATE `Team`;
INSERT INTO `Team` (`id`, `teamName`)
VALUES (1, '경영팀'),
       (2, '개발팀');

SET
foreign_key_checks = TRUE;