CREATE TABLE movie_category (
    movie_id INTEGER,
    category_id INTEGER,
    CONSTRAINT fk_movie_category_movie FOREIGN KEY(movie_id) REFERENCES tb_movies(id),
    CONSTRAINT fk_movie_category_category FOREIGN KEY(category_id) REFERENCES tb_category(id)
);