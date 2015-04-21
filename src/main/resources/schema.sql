create table OAUTH_ACCESS_TOKEN (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONGVARBINARY,
  refresh_token VARCHAR(256)
);

create table OAUTH_REFRESH_TOKEN (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication LONGVARBINARY
);