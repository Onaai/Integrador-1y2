CREATE TABLE pelicula (
    codigo VARCHAR(10) PRIMARY KEY,
    titulo VARCHAR(100),
    url VARCHAR(255),
    imagen VARCHAR(255),
    generos VARCHAR(255)
);

INSERT INTO pelicula (codigo, titulo, url, imagen, generos) VALUES
('001', '2001: Odisea del Espacio', 'http://www.example.com/2001', 'http://www.example.com/2001.jpg', 'Ciencia ficción,Drama'),
('002', 'El Padrino', 'http://www.example.com/padrino', 'http://www.example.com/padrino.jpg', 'Crimen,Drama'),
('003', 'La Guerra de las Galaxias', 'http://www.example.com/starwars', 'http://www.example.com/starwars.jpg', 'Acción,Aventura,Fantasia');
