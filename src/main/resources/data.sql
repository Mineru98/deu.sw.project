SET
foreign_key_checks = FALSE;

TRUNCATE `Role`;
INSERT INTO `Role` (`id`, `roleName`, `description`)
VALUES (1, 'ROLE_CEO', '대표이사'),
       (2, 'ROLE_MANAGER', '부서관리자'),
       (3, 'ROLE_STAFF', '직원');

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
       (2, '개발팀'),
       (3, '마케팅팀');

TRUNCATE `User`;
INSERT INTO `User` (`id`, `username`, `password`, `name`, `birthDay`, `dateOfJoin`, `dateOfLeave`, `contactNumber`,
                    `networkMacAddress`, `isManager`, `isOfficer`, `companyId`, `teamId`, `rankId`)
VALUES (1, 'user1',
        'daa7f4d0512d94d07b875e8d9842b818079cd71a13c82b5b36a287bc7230a3e249807a7ec75d06a2bcdb175d926d5dcc5cefa67e5534285ceb10c307c84931e1',
        '김대표', '1982-01-01', '2013-01-01', null, '01012341234', '4b-8d-e7-39-08-54', FALSE, TRUE, 1, 1, 1),
       (2, 'user2',
        'daa7f4d0512d94d07b875e8d9842b818079cd71a13c82b5b36a287bc7230a3e249807a7ec75d06a2bcdb175d926d5dcc5cefa67e5534285ceb10c307c84931e1',
        '박팀장', '1991-10-09', '2015-03-12', null, '01012341234', '15-d8-60-5d-b9-65', True, True, 1, 2, 2),
       (3, 'user3',
        'daa7f4d0512d94d07b875e8d9842b818079cd71a13c82b5b36a287bc7230a3e249807a7ec75d06a2bcdb175d926d5dcc5cefa67e5534285ceb10c307c84931e1',
        '이사원', '1994-03-13', '2022-08-28', null, '01012341234', '6b-19-26-2a-86-f9', FALSE, True, 1, 2, 3),
       (4, 'user4',
        'daa7f4d0512d94d07b875e8d9842b818079cd71a13c82b5b36a287bc7230a3e249807a7ec75d06a2bcdb175d926d5dcc5cefa67e5534285ceb10c307c84931e1',
        '허사원', '1993-11-12', '2021-07-13', '2023-01-01', '01012341234', 'a8-26-cc-0d-95-bc', FALSE, True, 1, 2, 3),
       (5, 'user5',
        'daa7f4d0512d94d07b875e8d9842b818079cd71a13c82b5b36a287bc7230a3e249807a7ec75d06a2bcdb175d926d5dcc5cefa67e5534285ceb10c307c84931e1',
        '신팀장', '1991-08-02', '2017-06-21', null, '01012341234', 'a7-b8-67-55-cc-42', True, True, 1, 3, 2),
       (6, 'user6',
        'daa7f4d0512d94d07b875e8d9842b818079cd71a13c82b5b36a287bc7230a3e249807a7ec75d06a2bcdb175d926d5dcc5cefa67e5534285ceb10c307c84931e1',
        '김대리', '1994-09-28', '2019-07-08', null, '01012341234', '00-d6-22-08-fa-64', FALSE, True, 1, 3, 3),
       (7, 'user7',
        'daa7f4d0512d94d07b875e8d9842b818079cd71a13c82b5b36a287bc7230a3e249807a7ec75d06a2bcdb175d926d5dcc5cefa67e5534285ceb10c307c84931e1',
        '차사원', '1993-11-01', '2021-12-23', null, '01012341234', '18-72-9f-b0-e5-a1', FALSE, True, 1, 3, 3),
       (8, 'user8',
        'daa7f4d0512d94d07b875e8d9842b818079cd71a13c82b5b36a287bc7230a3e249807a7ec75d06a2bcdb175d926d5dcc5cefa67e5534285ceb10c307c84931e1',
        '박경리', '1988-05-13', '2021-12-23', null, '01012341234', '2c-6d-26-06-9f-4d', FALSE, True, 1, 2, 3);

TRUNCATE `UserAndRole`;
INSERT INTO `UserAndRole` (id, userId, roleId)
VALUES (1, 1, 1),
       (2, 2, 2),
       (3, 2, 3),
       (4, 3, 3),
       (5, 4, 3),
       (6, 5, 2),
       (7, 5, 3),
       (8, 6, 3),
       (9, 7, 3),
       (10, 8, 3);

SET
foreign_key_checks = TRUE;