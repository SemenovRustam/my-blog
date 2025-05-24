DROP TABLE IF EXISTS comments, posts;

CREATE TABLE IF NOT EXISTS posts(
        id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        title VARCHAR(256) NOT NULL,
        image_path VARCHAR(512),
        text VARCHAR NOT NULL,
        likes_count BIGINT DEFAULT 0,
        tags VARCHAR[]
);

CREATE TABLE IF NOT EXISTS comments(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    post_id BIGINT NOT NULL,
    text VARCHAR(256) NOT NULL,
    FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE
);

insert into posts(title, image_path, text, likes_count, tags)
values ('Погода', '', 'Прогноз погоды', 2, '{tag1,tag2}'),
       ('Новости', '', 'Новости пятницы', 3, '{tag1}'),
       ('Путешествия', '', 'Горящие туры', 1, '{tag1,tag4}');

insert into comments(post_id, text)
values (1, 'Отличная погода'),
       (2, 'Миру мир!'),
       (3, 'В тай за 200 евро');