
INSERT INTO `auth_permission` (`id`, `description`, `name`, `pid`, `url`)
VALUES
	(1, '用户', 'user', 0, NULL),
	(2, '权限', 'security', 0, NULL),
	(3, '角色', 'role', 0, NULL),
	(4, '后台管理', 'admin:index', 1, '/admin/index'),
	(5, '用户列表', 'user:list', 4, '/admin/user/list'),
	(6, '用户角色', 'user:role', 4, '/admin/user/*/role'),
	(7, '用户删除', 'user:delete', 4, '/admin/user/*/delete'),
	(8, '权限列表', 'permission:list', 5, '/admin/permission/list'),
	(9, '权限添加', 'permission:add', 5, '/admin/permission/add'),
	(10, '权限编辑', 'permission:edit', 5, '/admin/permission/*/edit'),
	(11, '权限删除', 'permission:delete', 5, '/admin/permission/*/delete'),
	(12, '角色列表', 'role:list', 6, '/admin/role/list'),
	(13, '角色添加', 'role:add', 6, '/admin/role/add'),
	(14, '角色编辑', 'role:edit', 6, '/admin/role/*/edit'),
	(15, '角色删除', 'role:delete', 6, '/admin/role/*/delete');

INSERT INTO `auth_role` (`id`, `description`, `name`)
VALUES
	(1,'超级管理员','admin'),
	(2,'管理员','sadmin'),
	(3,'会员','user');

INSERT INTO `auth_role_permission` (`role_id`, `permission_id`)
VALUES
	(1, 7),
	(2, 7),
	(1, 8),
	(2, 8),
	(1, 9),
	(2, 9),
	(1, 10),
	(2, 10),
	(1, 11),
	(2, 11),
	(1, 12),
	(2, 12),
	(1, 13),
	(1, 14),
	(1, 15);

INSERT INTO `auth_user` (`id`, `password`, `username`, `token`)
VALUES
	(1,'$2a$10$KkUG107R3ASTHfAHei.bweXWXgCa4cE1KhK.F0odzfE0r97aeeTXC','admin','d20b9a5c-8693-41a6-8943-ddb2cb78eebd');

INSERT INTO `auth_user_role` (`user_id`, `role_id`)
VALUES
	(1,1);
