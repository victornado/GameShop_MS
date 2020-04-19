
CREATE TABLE `proveedor` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(50) NOT NULL,
  `NIF` varchar(9) NOT NULL,
  `telefono` int(11) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
);



CREATE TABLE `producto` (
	`ID` int(11) NOT NULL AUTO_INCREMENT,
	`nombre` varchar(45) NOT NULL,
	`descripcion` varchar(150) NOT NULL,
	`PVP` float NOT NULL,
	`stock` int(11) NOT NULL,
	`IDProveedor` int(11) NOT NULL,
	`activo` tinyint(1) NOT NULL,
	`unidadesProv` int (11) NOT NULL,

	`genero` varchar(45),

	`marca` varchar(45),
	`color` varchar(45),
    
  `tipo` varchar(30) NOT NULL,
	
	PRIMARY KEY (`ID`),
	KEY `IDProveedor_idx` (`IDProveedor`),
	CONSTRAINT `IDProveedorP` FOREIGN KEY (`IDProveedor`) REFERENCES `proveedor` (`ID`)
);


CREATE TABLE `ticket` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha` TIMESTAMP NOT NULL,
  `precioFinal` FLOAT NOT NULL,
  PRIMARY KEY (`ID`)
);


CREATE TABLE `asociado` (
  `IDProducto` INT(11) NOT NULL,
  `IDTicket` INT(11) NOT NULL,
  `cantidad` INT(10) NOT NULL,
  `precio`  DOUBLE(10,1) NOT NULL,
  PRIMARY KEY (`IDProducto`, `IDTicket`),
  
  KEY `IDTicket_idx` (`IDTicket`),
  KEY `IDProducto_idx` (`IDProducto`),

  CONSTRAINT `IDTicketP` FOREIGN KEY (`IDTicket`) REFERENCES `ticket` (`ID`),
  CONSTRAINT `IDProductoP` FOREIGN KEY (`IDProducto`) REFERENCES `producto` (`ID`)
);

CREATE TABLE `conferencia` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `asistentes` int(4) NOT NULL,
  `fecha` timestamp NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `tematica` varchar(50) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `version` int(4) NOT NULL,
  PRIMARY KEY (`id`,`nombre`,`fecha`)
);

CREATE TABLE `departamento` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `facturacion` double NOT NULL,
  `numPlanta` int(2) NOT NULL,
  `activo` tinyint(1) NOT NULL,
   `version` int(4) NOT NULL,
  PRIMARY KEY (`id`,`nombre`)
);

CREATE TABLE `empleado` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `nif` char(9) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nombre` varchar(70) NOT NULL,
  `sueldoBase` double NOT NULL,
  `turno` varchar(20) NOT NULL,
  `depto_id` int(4) ,
  `dtype` varchar(45) NOT NULL,
  `activo` tinyint(1) NOT NULL,
   `version` int(4) NOT NULL,
  PRIMARY KEY (`id`,`nif`),
  KEY `id_idx` (`depto_id`),
  CONSTRAINT `id` FOREIGN KEY (`depto_id`) REFERENCES `departamento` (`id`)
);

CREATE TABLE `comercial` (
  `nVentas` int(4) NOT NULL,
  `id` int(4) NOT NULL,
  KEY `idEmp_idx` (`id`),
  CONSTRAINT `idEmp` FOREIGN KEY (`id`) REFERENCES `empleado` (`id`)
);

CREATE TABLE `tecnico` (
  `especialidad` varchar(70) NOT NULL,
  `sobresueldo` double NOT NULL,
  `id` int(11) NOT NULL,
  KEY `idEmp_idx` (`id`),
  CONSTRAINT `idEmpTecnico` FOREIGN KEY (`id`) REFERENCES `empleado` (`id`)
);

CREATE TABLE `realiza` (
  `duracion` int(3) NOT NULL,
  `version` int(4) NOT NULL,
  `conferencia` int(4) NOT NULL,
  `empleado` int(4) NOT NULL,
  `conferencia_id` int(4) NOT NULL,
  `empleado_id` int(4) NOT NULL,
  PRIMARY KEY(`conferencia`,`empleado`),
  KEY `empleado_id` (`empleado`),
  KEY `conferencia_id` (`conferencia`),
  CONSTRAINT `conferencia_id` FOREIGN KEY (`conferencia`) REFERENCES `conferencia` (`id`),
  CONSTRAINT `empleado_id` FOREIGN KEY (`empleado`) REFERENCES `empleado` (`id`)
);