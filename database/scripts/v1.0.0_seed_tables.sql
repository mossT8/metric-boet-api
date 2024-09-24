DO $$
DECLARE
    AdminRoleId INTEGER;
    SystemAutoRoleId INTEGER;
    ModeratorRoleId INTEGER;
    UserRoleId INTEGER;
    UserSystemAutoId INTEGER;
BEGIN
    -- Insert roles and get their IDs
    INSERT INTO roles("name")
    VALUES('ROLE_ADMIN')
    RETURNING id INTO AdminRoleId;

    INSERT INTO roles("name")
    VALUES('ROLE_SYSTEM_AUTO')
    RETURNING id INTO SystemAutoRoleId;

    INSERT INTO roles("name")
    VALUES('ROLE_MODERATOR')
    RETURNING id INTO ModeratorRoleId;

    INSERT INTO roles("name")
    VALUES('ROLE_USER')
    RETURNING id INTO UserRoleId;

    -- Insert user and get the user ID
    INSERT INTO "users" (
        "account_code",
        "email",
        "first_name",
        "last_name",
        "password",
        "phone",
        "username"
    )
    VALUES (
        'S001',
        'systen@test.com',
        'System',
        'Auto',
        '$2a$10$wyRxHWfDWNB4mRT0Rb8czO7Oq4PCYaI3HLb8zGvppijzpS0oRXsiq',
        '0605281163',
        'System Auto'
    )
    RETURNING id INTO UserSystemAutoId;

    -- Insert user roles
    INSERT INTO user_roles(
        "user_id",
        "role_id"
    )
    VALUES(
        UserSystemAutoId,
        SystemAutoRoleId
    );

    -- Insert HTML pages
    INSERT INTO "html_pages" (
        "url",
        "html",
        "user_created_id",
        "user_updated_id"
    )
    VALUES (
        'home',
        '<h1><strong>Welcome to Your Farm Metric Application</strong></h1><p>With this application, you can record metrics from devices on your farm, query them, set up alerts, and add devices as needed.</p><p>Farmers have always relied on their experience and intuition to manage their farms. However, as farming practices become more complex and the demand for food increases, the need for accurate and up-to-date data is becoming increasingly important. By recording metrics such as weather patterns, crop yields, and livestock health, farmers can gain insights into the performance of their farm and make data-driven decisions about how to improve efficiency and productivity. These metrics can also be used to track progress over time and identify areas for improvement. Additionally, by setting up alerts for their farm, farmers can be notified of potential issues before they become major problems. For example, if there is a sudden drop in temperature, an alert can be sent to the farmer to take action to protect their crops or livestock. By being proactive and staying informed, farmers can minimize risk and maximize their yields.</p>',
        UserSystemAutoId,
        UserSystemAutoId
    ),
    (
        'admin',
        '<h1><strong>Admin view</strong></h1><p></p><pre><code>To do </code></pre>',
        UserSystemAutoId,
        UserSystemAutoId
    ),
    (
        'moderator',
        '<h1><strong>Moderator view</strong></h1><p></p><pre><code>To do </code></pre>',
        UserSystemAutoId,
        UserSystemAutoId
    ),
    (
        'user',
        '<h1><strong>User view</strong></h1><p></p><pre><code>To do </code></pre>',
        UserSystemAutoId,
        UserSystemAutoId
    );
END $$;
