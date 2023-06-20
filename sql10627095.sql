-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Servidor: sql10.freesqldatabase.com
-- Tiempo de generación: 20-06-2023 a las 00:26:39
-- Versión del servidor: 5.5.62-0ubuntu0.14.04.1
-- Versión de PHP: 7.0.33-0ubuntu0.16.04.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sql10627095`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `categoria_id` int(11) NOT NULL,
  `categoria_descripcion` varchar(250) NOT NULL,
  `categoria_genero` tinyint(1) NOT NULL,
  `categoria_img` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`categoria_id`, `categoria_descripcion`, `categoria_genero`, `categoria_img`) VALUES
(1, 'Blusa dama', 1, 'https://www.sabzswimwear.com/peru/wp-content/uploads/2022/04/cf333.jpg'),
(2, 'Polera Hombre', 0, 'https://http2.mlstatic.com/D_NQ_NP_957343-MPE32009165560_082019-W.jpg'),
(3, 'Pantalon Hombre', 0, 'https://tiendasel.vteximg.com.br/arquivos/ids/4369337-460-450/-pantalon-drill-moda--vicente.jpg?v=637536648736830000'),
(4, 'Jogger', 0, 'https://oechsle.vteximg.com.br/arquivos/ids/7463513-1000-1000/1921614.jpg?v=637817725636670000'),
(5, 'Casaca impermeable', 0, 'https://www.pionier.pe/assets/upload/thumb/producto/1000/5040203153_1.jpg'),
(6, 'Conjunto ', 1, 'https://i.pinimg.com/236x/a2/8f/7c/a28f7c01eabadc6d0fdb0ff2b964c0dd.jpg'),
(7, 'Polera', 0, 'https://runastore.pe/media/catalog/product/cache/1/image/1600x/040ec09b1e35df139433887a97daa66f/n/f/nf0a48kk-ssj_1_1.jpg'),
(8, 'Polo', 0, 'https://home.ripley.com.pe/Attachment/WOP_5/2016291034882/2016291034882_2.jpg'),
(9, 'Zapatillas', 1, 'https://falabella.scene7.com/is/image/FalabellaPE/19019290_1?wid=800&hei=800&qlt=70'),
(10, 'Botas', 1, 'https://plazavea.vteximg.com.br/arquivos/ids/12344525-450-450/imageUrl_1.jpg?v=637902367498070000'),
(11, 'Vestido blanco verano', 1, 'https://i.pinimg.com/236x/a9/44/c0/a944c099292980601fc7633083975716.jpg'),
(12, 'Chompa tejida', 1, 'https://www.estilos.com.pe/203550-home_default/chompa-hilo-jaydy-.jpg'),
(13, 'Enterizo vino', 1, 'https://cf.shopee.com.mx/file/5fee2794dbfe024fcf26ab4fd9e52d64_tn'),
(14, 'Enterizo de baño', 1, 'https://cf.shopee.com.mx/file/51817e764b1d748994c1426bf93f5848_tn');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_ordenes`
--

CREATE TABLE `detalle_ordenes` (
  `orden_id` int(11) NOT NULL,
  `producto_id` int(11) NOT NULL,
  `detalle_orden_precio` decimal(10,0) NOT NULL,
  `detalle_orden_cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalle_ordenes`
--

INSERT INTO `detalle_ordenes` (`orden_id`, `producto_id`, `detalle_orden_precio`, `detalle_orden_cantidad`) VALUES
(1, 2, '840', 2),
(2, 9, '300', 2),
(3, 3, '269', 4),
(4, 10, '464', 2),
(5, 2, '2100', 5),
(6, 6, '951', 6),
(7, 5, '564', 3),
(8, 7, '285', 2),
(9, 8, '341', 4),
(10, 10, '654', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordenes`
--

CREATE TABLE `ordenes` (
  `orden_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `orden_fecha_creacion` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ordenes`
--

INSERT INTO `ordenes` (`orden_id`, `usuario_id`, `orden_fecha_creacion`) VALUES
(1, 1, '2022-12-07 00:26:45'),
(2, 2, '2022-12-07 19:05:16'),
(3, 5, '2022-12-07 19:05:16'),
(4, 7, '2022-12-07 19:05:16'),
(5, 3, '2022-12-07 00:36:50'),
(6, 8, '2022-12-07 19:05:16'),
(7, 4, '2022-12-07 19:05:16'),
(8, 6, '2022-12-07 19:06:37'),
(9, 9, '2022-12-07 19:06:37'),
(10, 10, '2022-12-07 19:06:37');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `producto_id` int(11) NOT NULL,
  `categoria_id` int(11) NOT NULL,
  `producto_precio` decimal(10,0) NOT NULL,
  `producto_descripcion` varchar(250) NOT NULL,
  `producto_imagen` varchar(250) NOT NULL,
  `producto_estado` tinyint(1) NOT NULL,
  `producto_inventario` int(11) DEFAULT NULL,
  `producto_fecha_creacion` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`producto_id`, `categoria_id`, `producto_precio`, `producto_descripcion`, `producto_imagen`, `producto_estado`, `producto_inventario`, `producto_fecha_creacion`) VALUES
(1, 2, '69', 'Polera Negra Gamarra', 'https://http2.mlstatic.com/D_NQ_NP_957343-MPE32009165560_082019-W.jpg', 0, 5, '2022-12-06 16:19:06'),
(2, 3, '420', 'Pantalon Gucci Gang', 'https://tiendasel.vteximg.com.br/arquivos/ids/4369337-460-450/-pantalon-drill-moda--vicente.jpg?v=637536648736830000', 0, 6, '2022-12-06 16:26:44'),
(3, 1, '125', 'Blusa Amira', 'https://www.sabzswimwear.com/peru/wp-content/uploads/2022/04/cf333.jpg', 0, 7, '2022-12-07 17:33:22'),
(4, 4, '56', 'Jogger Militar', 'https://oechsle.vteximg.com.br/arquivos/ids/7463513-1000-1000/1921614.jpg?v=637817725636670000', 0, 8, '2022-12-07 17:34:48'),
(5, 5, '64', 'Casaca impermeable', 'https://www.pionier.pe/assets/upload/thumb/producto/1000/5040203153_1.jpg', 0, 4, '2022-12-07 17:34:48'),
(6, 6, '225', 'Conjunto Mirena', 'https://i.pinimg.com/236x/a2/8f/7c/a28f7c01eabadc6d0fdb0ff2b964c0dd.jpg', 0, 9, '2022-12-07 17:36:04'),
(7, 7, '88', 'Polera Tka Glacier', 'https://runastore.pe/media/catalog/product/cache/1/image/1600x/040ec09b1e35df139433887a97daa66f/n/f/nf0a48kk-ssj_1_1.jpg', 0, 6, '2022-12-07 17:36:04'),
(8, 8, '62', 'Polo Fila', 'https://home.ripley.com.pe/Attachment/WOP_5/2016291034882/2016291034882_2.jpg', 0, 5, '2022-12-07 17:37:55'),
(9, 9, '145', 'Adidas zapatillas', 'https://falabella.scene7.com/is/image/FalabellaPE/19019290_1?wid=800&hei=800&qlt=70', 0, 10, '2022-12-07 17:37:55'),
(10, 10, '106', 'Botas con detalle de cadena', 'https://plazavea.vteximg.com.br/arquivos/ids/12344525-450-450/imageUrl_1.jpg?v=637902367498070000', 0, 12, '2022-12-07 17:55:31'),
(11, 11, '55', 'Vestido blanco verano', 'https://i.pinimg.com/236x/a9/44/c0/a944c099292980601fc7633083975716.jpg', 0, 0, '2022-12-07 19:40:57'),
(12, 12, '64', 'Chompa tejida', 'https://www.estilos.com.pe/203550-home_default/chompa-hilo-jaydy-.jpg', 0, 0, '2022-12-07 19:40:57'),
(13, 13, '85', 'Enterizo vino ', 'https://cf.shopee.com.mx/file/5fee2794dbfe024fcf26ab4fd9e52d64_tn', 0, 0, '2022-12-07 19:40:57'),
(14, 14, '75', 'Enterizo de baño amarrillo', 'https://cf.shopee.com.mx/file/51817e764b1d748994c1426bf93f5848_tn', 0, 0, '2022-12-07 19:40:57'),
(15, 2, '85', 'Polera Deportiva', 'https://oechsle.vteximg.com.br/arquivos/ids/14320215-1500-1500/2183580.jpg?v=638167703962800000', 0, 5, '2023-06-19 19:23:56');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `usuario_id` int(11) NOT NULL,
  `usuario_nombre` varchar(250) NOT NULL,
  `usuario_telefono` varchar(9) NOT NULL,
  `usuario_email` varchar(250) NOT NULL,
  `usuario_nivel` tinyint(1) NOT NULL,
  `usuario_password` varchar(250) NOT NULL,
  `usuario_fecha_creacion` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`usuario_id`, `usuario_nombre`, `usuario_telefono`, `usuario_email`, `usuario_nivel`, `usuario_password`, `usuario_fecha_creacion`) VALUES
(1, 'Joseph Cavero', '923762873', 'joseph@gmail.com', 1, 'joseph123', '2022-12-05 15:07:08'),
(2, 'Karina Mendoza', '958743432', 'karina23@gmail.com', 1, 'karina123', '2022-12-07 18:06:08'),
(3, 'Karla Valverde', '948752249', 'kar_334@gmail.com', 1, '1234', '2022-12-05 23:06:29'),
(4, 'Luis Rodriguez', '991234232', 'lurod@gmail.com', 1, 'luis123', '2022-12-07 18:06:08'),
(5, 'Oscar Peña', '946852845', 'pena@gmail.com', 1, 'oscar123', '2022-07-15 03:07:50'),
(6, 'Cesar	Montes \r\n', '956094141', 'cesar2984@hotmail.com\r\n', 1, 'cesar123', '2022-12-07 18:10:18'),
(7, 'Carlos	Alavedra \r\n', '924750033', 'carlos123@gmail.com', 1, 'carlos123', '2022-12-07 18:10:18'),
(8, 'Alejandro	Arenas \r\n', '999152672', 'aleare@gmail.com', 1, 'ale123', '2022-12-07 18:12:02'),
(9, 'Pedro	Arias\r\n', '914413504', 'ped123@gmail.com', 1, 'ped123', '2022-12-07 18:12:02'),
(10, 'Douglas	Bernizon \r\n', '955003698', 'dou123b@gmail.com', 1, 'dou123', '2022-12-07 18:13:03'),
(12, 'alvaro', '56756765', 'zz@gmail.com', 0, 'sdf3232', '2023-06-18 19:22:40'),
(13, 'Pedro', '956456543', 'pedro@gmail.com', 0, '1234', '2023-06-19 00:48:47'),
(14, 'Gilberto', '954732345', 'gilberto@gmail.com', 0, '1234', '2023-06-19 00:48:47'),
(15, 'MARCO', '122334445', 'alo@gmail.com', 0, 'de12', '2023-06-18 19:22:40'),
(16, 'franco', '23423525', 'assa@gmail.com', 0, '32fr23r23', '2023-06-18 19:22:40'),
(17, 'Fortunato', '946321857', 'fortunato@gmail.com', 0, '1234', '2023-06-19 23:47:58'),
(18, 'Cavero', '957321748', 'cavero@gmail.com', 1, '1234', '2023-06-22 00:00:00');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`categoria_id`);

--
-- Indices de la tabla `detalle_ordenes`
--
ALTER TABLE `detalle_ordenes`
  ADD PRIMARY KEY (`orden_id`,`producto_id`),
  ADD KEY `producto_id` (`producto_id`),
  ADD KEY `orden_id` (`orden_id`);

--
-- Indices de la tabla `ordenes`
--
ALTER TABLE `ordenes`
  ADD PRIMARY KEY (`orden_id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`producto_id`),
  ADD KEY `categoria_id` (`categoria_id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`usuario_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `categoria_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT de la tabla `ordenes`
--
ALTER TABLE `ordenes`
  MODIFY `orden_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `producto_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `usuario_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
