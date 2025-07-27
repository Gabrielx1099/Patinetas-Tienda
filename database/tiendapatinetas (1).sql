-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-07-2025 a las 09:22:25
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tiendapatinetas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

CREATE TABLE `carrito` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL DEFAULT 1,
  `fecha_agregado` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `carrito`
--

INSERT INTO `carrito` (`id`, `usuario_id`, `producto_id`, `cantidad`, `fecha_agregado`) VALUES
(15, 11, 27, 1, '2025-07-15 14:10:56'),
(16, 27, 26, 1, '2025-07-15 15:20:10'),
(17, 27, 34, 2, '2025-07-15 15:20:17');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`) VALUES
(1, 'Skates'),
(2, 'Tablas'),
(3, 'Ruedas'),
(4, 'Accesorios');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_ventas`
--

CREATE TABLE `detalle_ventas` (
  `id` int(11) NOT NULL,
  `venta_id` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio_unitario` decimal(10,2) NOT NULL,
  `subtotal` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcas`
--

CREATE TABLE `marcas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `marcas`
--

INSERT INTO `marcas` (`id`, `nombre`) VALUES
(9, 'Sketchers'),
(10, 'Xtreme'),
(11, 'Tabla Brava'),
(12, 'Nike'),
(13, 'Skate Mental');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `precio` decimal(10,2) NOT NULL,
  `stock` int(11) NOT NULL DEFAULT 0,
  `imagen_url` varchar(255) DEFAULT NULL,
  `categoria_id` int(11) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `marca_id` int(11) DEFAULT NULL,
  `subcategoria_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `nombre`, `descripcion`, `precio`, `stock`, `imagen_url`, `categoria_id`, `fecha_creacion`, `marca_id`, `subcategoria_id`) VALUES
(26, 'Skateboard Supreme', 'Skate de alta calidad para profesionales', 45.00, 7, 'assets/Productos/03868eeb-2dba-412c-beb3-6cdbb5cf3207.jpg', 1, '2025-07-15 06:26:26', 10, 3),
(27, 'Skates Madera Fina', 'Skate de alta calidad echo en pura madera de abeto', 56.00, 6, 'assets/Productos/d915bdaf-2c9a-4f78-b956-35e2b38fa952.png', 1, '2025-07-15 06:28:40', 11, 3),
(29, 'Skate XOM', 'Skate classic', 35.00, 8, 'assets/Productos/cdcb66c9-6d85-4b13-92d6-0a2a858866ba.jpg', 1, '2025-07-15 06:33:22', 13, 2),
(30, 'Mini Skate UKM', 'Para niños tranquilos', 45.00, 5, 'assets/Productos/c89bf708-672f-4d94-b386-395f90de5f8c.jpg', 1, '2025-07-15 06:34:40', 9, 4),
(31, 'Yerbera Skate', 'Tabla piola', 45.00, 5, 'assets/Productos/b97b4144-58a9-423d-932b-9cd999650b8d.jpg', 1, '2025-07-15 06:36:32', 10, 3),
(32, 'Skate Lover Yourself', 'Calidad-gatitos-amor', 56.00, 6, 'assets/Productos/f61e4ffd-9564-4d73-8e97-075c367d8b86.jpg', 1, '2025-07-15 06:38:30', 11, 3),
(33, 'Tabla Marfil', 'Marfil', 45.00, 6, 'assets/Productos/6de817e2-05a3-43a6-9a5c-52c8a5e2d5ae.jpg', 2, '2025-07-15 06:39:19', 9, 5),
(34, 'Tabla Yamashita', 'Yamashita', 15.00, 4, 'assets/Productos/c903e401-f790-4eb1-999c-80056bd70bfd.jpg', 2, '2025-07-15 06:42:02', 9, 5),
(35, 'Tabla Oak MCX', 'Larga', 26.00, 5, 'assets/Productos/1a2bc095-e023-4a1b-b995-e891834cbe5d.jpg', 2, '2025-07-15 06:46:32', 11, 6),
(36, 'Ruedas Goma', 'Goma', 9.00, 6, 'assets/Productos/04c8c0b0-736e-4a15-8d53-6229f59e552e.jpg', 3, '2025-07-15 06:48:52', 13, 10),
(37, 'Ruedas Goma XFK', 'Gomita', 13.00, 5, 'assets/Productos/d0693cce-c3b5-404b-987d-0c96e69a4521.jpg', 3, '2025-07-15 06:51:08', 11, 10),
(38, 'Ruedas White', 'BLANCAS', 14.00, 5, 'assets/Productos/793e8309-3442-475a-b8d3-db696321aa47.jpg', 3, '2025-07-15 06:52:08', 10, 9),
(39, 'Destornillador', 'Amarillo', 8.00, 5, 'assets/Productos/83774bf1-32de-49f5-8274-e541ee92c5f7.jpg', 4, '2025-07-15 07:28:24', 11, 13),
(40, 'Rodamientos TXT', 'Titanio', 14.00, 6, 'assets/Productos/ed213147-ce34-426e-81da-d63e15f11966.jpg', 4, '2025-07-15 07:29:31', 10, 12),
(41, 'Rodamientos XCS', 'Titanio', 12.00, 8, 'assets/Productos/3cb4e6b1-b8fe-4ef1-af47-9f4390c7b0a3.jpg', 4, '2025-07-15 07:30:34', 11, 12),
(42, 'Tabla XMT', 'Lomboard', 19.00, 6, 'assets/Productos/edf1c6d6-4cbd-4a43-bf47-1166e71e1ad8.jpeg', 2, '2025-07-15 14:24:08', 11, 6),
(43, 'Tabla Candiens', 'Canadience', 15.00, 6, 'assets/Productos/c53c6c9a-c5e4-4e3a-b825-d34e801bfda1.jpeg', 2, '2025-07-15 14:25:41', 9, 5),
(44, 'Ruedas BlueLabel', 'Azules', 18.00, 5, 'assets/Productos/d7f292fd-cb5c-4d6e-b833-c6c053d823fc.jpg', 3, '2025-07-15 14:27:40', 10, 14),
(45, 'White wheels', 'LLantas blancas', 18.00, 5, 'assets/Productos/8f6bf237-580a-4906-b895-3c59f35e335f.jpeg', 3, '2025-07-15 14:29:31', 13, 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subcategorias`
--

CREATE TABLE `subcategorias` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `id_categoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `subcategorias`
--

INSERT INTO `subcategorias` (`id`, `nombre`, `id_categoria`) VALUES
(1, 'Completos', 1),
(2, 'Principiantes', 1),
(3, 'Profesionales', 1),
(4, 'Niños', 1),
(5, 'Skateboard', 2),
(6, 'Lomboard', 2),
(7, 'Cruiser', 2),
(8, 'Streetboard', 2),
(9, 'BEARING WHEELS S/C', 3),
(10, 'BEARING WHEELS BLACK', 3),
(11, 'SuperGomas S/C', 4),
(12, 'Rodamientos de Titanio Backle', 4),
(13, 'Herramienta', 4),
(14, 'Lomboard', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol` enum('admin','cliente') NOT NULL DEFAULT 'cliente',
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp(),
  `genero` varchar(10) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `apellido`, `email`, `password`, `rol`, `fecha_registro`, `genero`, `telefono`) VALUES
(11, 'Brenda', 'Leona', 'brendanicole19@hotmail.com', '$2a$10$bLyPZMmeNPaDGFiqk9K.u.q7Ow7YsGN524adxVn5yb5CFxn7TyWE.', 'cliente', '2025-07-13 22:18:30', 'Femenino', '987467372'),
(23, 'Gabriel', 'Vicente', 'felixleonxx@gmail.com', '$2a$10$WjV4jDZx/rN7PvSyMRumS.VOG9RJJyVDTDi.RYSkcZ.qXv5QQlTd2', 'admin', '2025-07-13 05:00:00', 'Masculino', '987467374'),
(26, 'Yeiden', 'Juarez', 'YeiJua@gmail.com', '$2a$10$r9ufIXWYQCf4Qb7635HhoOCvi8hwKmS8QVRflQZIBMHmhG1gEZDHy', 'admin', '2025-07-15 05:00:00', 'masculino', '987467372'),
(27, 'Maria', '205042', 'gv250204@gmail.com', '$2a$10$ZsJ.Lw9i/MC7Y6WqJXfWW.t5r7hDZ6rFSc0FtOnX7hDjQdnWWwv82', 'cliente', '2025-07-15 05:00:00', 'Otro', '963285644');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `fecha_venta` timestamp NOT NULL DEFAULT current_timestamp(),
  `total` decimal(12,2) NOT NULL,
  `estado` enum('pendiente','completada','cancelada') DEFAULT 'pendiente',
  `direccion_envio` text NOT NULL,
  `tipo_entrega` enum('recojo','delivery') DEFAULT 'recojo',
  `costo_delivery` decimal(8,2) DEFAULT 0.00,
  `telefono_entrega` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_user_product` (`usuario_id`,`producto_id`),
  ADD KEY `producto_id` (`producto_id`);

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `venta_id` (`venta_id`),
  ADD KEY `producto_id` (`producto_id`);

--
-- Indices de la tabla `marcas`
--
ALTER TABLE `marcas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoria_id` (`categoria_id`),
  ADD KEY `productos_ibfk_marca` (`marca_id`),
  ADD KEY `subcategoria_id` (`subcategoria_id`);

--
-- Indices de la tabla `subcategorias`
--
ALTER TABLE `subcategorias`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoria_id` (`id_categoria`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `carrito`
--
ALTER TABLE `carrito`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `marcas`
--
ALTER TABLE `marcas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT de la tabla `subcategorias`
--
ALTER TABLE `subcategorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD CONSTRAINT `carrito_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `carrito_ibfk_2` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  ADD CONSTRAINT `detalle_ventas_ibfk_1` FOREIGN KEY (`venta_id`) REFERENCES `ventas` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `detalle_ventas_ibfk_2` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`),
  ADD CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`subcategoria_id`) REFERENCES `subcategorias` (`id`),
  ADD CONSTRAINT `productos_ibfk_marca` FOREIGN KEY (`marca_id`) REFERENCES `marcas` (`id`);

--
-- Filtros para la tabla `subcategorias`
--
ALTER TABLE `subcategorias`
  ADD CONSTRAINT `subcategorias_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
