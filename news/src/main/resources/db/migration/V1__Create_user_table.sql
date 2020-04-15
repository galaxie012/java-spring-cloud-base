CREATE TABLE news (
  id          varchar(36)     NOT NULL,
  author_id    varchar(36)     NOT NULL,
  title       varchar(140)    NOT NULL,
  body        varchar(140)    NOT NULL,
  PRIMARY KEY (id)
);
