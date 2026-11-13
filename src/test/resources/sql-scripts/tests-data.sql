CREATE TABLE IF NOT EXISTS movies(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    minutes INT NOT NULL,
    genre VARCHAR(50) NOT NULL
    );

INSERT INTO movies (id, name, minutes, genre) VALUES
                                                  (1, 'Dark Knight', 152, 'ACTION'),
                                                  (2, 'Memento', 113, 'DRAMA'), -- Asegúrate de que aquí diga DRAMA (o cambia el test a THRILLER)
                                                  (3, 'Matrix', 136, 'ACTION');