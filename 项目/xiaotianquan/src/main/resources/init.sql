drop table if exists file_meta;
CREATE TABLE IF NOT EXISTS file_meta (
name VARCHAR(50) NOT NULL ,
path VARCHAR(1000) not null ,
is_directory BOOLEAN not null ,
pinyin VARCHAR(50),
pinyin_first VARCHAR(50),
size BIGINT not null ,
last_modified TIMESTAMP not null
);