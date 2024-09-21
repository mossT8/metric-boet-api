-- Insert role entries
INSERT INTO `roles` (name) VALUE ('ROLE_ADMIN');
SET @AdminRoleId = last_insert_id();
INSERT INTO `roles` (name) VALUE ('ROLE_SYSTEM_AUTO');
SET @SystemAutoRoleId = last_insert_id();
INSERT INTO `roles` (name) VALUE ('ROLE_MODERATOR');
SET @ModeratorRoleId = last_insert_id();
INSERT INTO `roles` (name) VALUE ('ROLE_USER');
SET @UserRoleId = last_insert_id();
-- Insert permissions entries
INSERT INTO `permissions` (name) VALUE ('update');
SET @UpdatePermissionId = last_insert_id();
INSERT INTO `permissions` (name) VALUE ('read');
SET @ReadPermissionId = last_insert_id();
INSERT INTO `permissions` (name) VALUE ('create');
SET @CreatePermissionId = last_insert_id();
INSERT INTO `permissions` (name) VALUE ('delete');
SET @DeletePermissionId = last_insert_id();
INSERT INTO `permissions` (name) VALUE ('reset');
SET @ResetPermissionId = last_insert_id();
-- Insert role_permissions entries for ADMIN
INSERT INTO `role_permissions` (roleId, permissionId)
VALUES (@AdminRoleId, @UpdatePermissionId),
    (@AdminRoleId, @ReadPermissionId),
    (@AdminRoleId, @CreatePermissionId),
    (@AdminRoleId, @ResetPermissionId),
    (@AdminRoleId, @DeletePermissionId);
-- Insert role_permissions entries for System Auto
INSERT INTO `role_permissions` (roleId, permissionId)
VALUES (@SystemAutoRoleId, @UpdatePermissionId),
    (@SystemAutoRoleId, @ReadPermissionId),
    (@AdminRoleId, @CreatePermissionId);
-- Insert role_permissions entries for Moderator
INSERT INTO `role_permissions` (roleId, permissionId)
VALUES (@ModeratorRoleId, @UpdatePermissionId),
    (@ModeratorRoleId, @ReadPermissionId),
    (@ModeratorRoleId, @ResetPermissionId);
-- Insert role_permissions entries for User
INSERT INTO `role_permissions` (roleId, permissionId)
VALUES (@UserRoleId, @UpdatePermissionId),
    (@UserRoleId, @ReadPermissionId),
    (@UserRoleId, @ResetPermissionId),
    (@UserRoleId, @DeletePermissionId);
INSERT INTO users (
        roleId,
        account_code,
        email,
        first_name,
        last_name,
        password,
        phone
    ) VALUE (
        @SystemAutoRoleId,
        'S001',
        'system.user@test.com',
        'System',
        'User',
        '$2a$10$wyRxHWfDWNB4mRT0Rb8czO7Oq4PCYaI3HLb8zGvppijzpS0oRXsiq',
        '0605281163'
    );
SET @SystemUserId = last_insert_id();
-- Insert Html Pages into new Table
INSERT INTO `html_pages` (
        route,
        html,
        user_created_id,
        user_updated_id
    )
VALUES (
        'home',
        '<h1><strong>Welcome to Your Farm Metric Application</strong></h1><p>With this application, you can record metrics from devices on your farm, query them, set up alerts, and add devices as needed.</p><p>Farmers have always relied on their experience and intuition to manage their farms. However, as farming practices become more complex and the demand for food increases, the need for accurate and up-to-date data is becoming increasingly important. By recording metrics such as weather patterns, crop yields, and livestock health, farmers can gain insights into the performance of their farm and make data-driven decisions about how to improve efficiency and productivity. These metrics can also be used to track progress over time and identify areas for improvement. Additionally, by setting up alerts for their farm, farmers can be notified of potential issues before they become major problems. For example, if there is a sudden drop in temperature, an alert can be sent to the farmer to take action to protect their crops or livestock. By being proactive and staying informed, farmers can minimize risk and maximize their yields.</p>',
        @SystemUserId,
        @SystemUserId
    ),
    (
        'admin',
        '<h1><strong>Admin view</strong></h1><p></p><pre><code>To do </code></pre>',
        @SystemUserId,
        @SystemUserId
    ),
    (
        'moderator',
        '<h1><strong>Moderator view</strong></h1><p></p><pre><code>To do </code></pre>',
        @SystemUserId,
        @SystemUserId
    ),
    (
        'user',
        '<h1><strong>User view</strong></h1><p></p><pre><code>To do </code></pre>',
        @SystemUserId,
        @SystemUserId
    );