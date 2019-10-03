CREATE TABLE `empleado` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `NIF` varchar(9) NOT NULL,
  `turno` varchar(15) NOT NULL,
  `activo` tinyint(1) NOT NULL,

  PRIMARY KEY (`ID`)
);

CREATE TABLE `plataforma` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
);


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
	`IDPlataforma` int(11) NOT NULL,
	`activo` tinyint(1) NOT NULL,
	`unidadesProv` int (11) NOT NULL,

	`genero` varchar(45),

	`marca` varchar(45),
	`color` varchar(45),
    
    `tipo` varchar(30) NOT NULL,
	
	PRIMARY KEY (`ID`),
	KEY `IDPlataforma_idx` (`IDPlataforma`),
	KEY `IDProveedor_idx` (`IDProveedor`),
	CONSTRAINT `IDPlataformaP` FOREIGN KEY (`IDPlataforma`) REFERENCES `plataforma` (`ID`),
	CONSTRAINT `IDProveedorP` FOREIGN KEY (`IDProveedor`) REFERENCES `proveedor` (`ID`)
);


CREATE TABLE `ticket` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `IDEmpleado` INT(11) NOT NULL,
  `fecha` TIMESTAMP NOT NULL,
  `precioFinal` FLOAT NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `IDEmpleado` FOREIGN KEY (`ID`) REFERENCES `empleado` (`ID`)
);


CREATE TABLE `asociado` (
  `IDProducto` INT(11) NOT NULL,
  `IDTicket` INT(11) NOT NULL,
  `IDEmpleado` INT(11) NOT NULL,
  `cantidad` INT(10) NOT NULL,
  PRIMARY KEY (`IDProducto`, `IDTicket`, `IDEmpleado`),
  
  KEY `IDEmpleado_idx` (`IDEmpleado`),
  KEY `IDTicket_idx` (`IDTicket`),
  KEY `IDProducto_idx` (`IDProducto`),

  CONSTRAINT `IDEmpleadoP` FOREIGN KEY (`IDEmpleado`) REFERENCES `empleado` (`ID`),
  CONSTRAINT `IDTicketP` FOREIGN KEY (`IDTicket`) REFERENCES `ticket` (`ID`),
  CONSTRAINT `IDProductoP` FOREIGN KEY (`IDProducto`) REFERENCES `producto` (`ID`)
);


----------------------------- 	PROVIDER   ---------------------------------------

INSERT INTO proveedor(ID,direccion,NIF,telefono,activo) values (1,'calle igual','76664094B',622237470,true);
INSERT INTO proveedor(ID,direccion,NIF,telefono,activo) values (2,'calle igual','16374546H',999888777,false);
INSERT INTO proveedor(ID,direccion,NIF,telefono,activo) values (3,'calle igual','99296921X',222333444,false);
INSERT INTO proveedor(ID,direccion,NIF,telefono,activo) values (4,'calle igual','45176319H',622237470,false);

----------------------------- 	PLATFORM   ---------------------------------------

INSERT INTO `plataforma` (`ID`, `nombre`, `activo`) VALUES ('1', 'XBOX360', '0');
INSERT INTO `plataforma` (`ID`, `nombre`, `activo`) VALUES ('2', 'NINTENDO-SWITCH', '1');
INSERT INTO `plataforma` (`ID`, `nombre`, `activo`) VALUES ('3', 'ORACLE', '1');

----------------------------- 	PRODUCT   ---------------------------------------

INSERT INTO `producto` (`ID`, `nombre`, `descripcion`, `PVP`, `stock`, `IDProveedor`, `IDPlataforma`, `activo`, `unidadesProv`, `genero`, `tipo`) VALUES ('1', 'Fifa', 'descripcion', '2', '6', '1', '1', '1', '6', 'Horror', 'VideoGame');
INSERT INTO `producto` (`ID`, `nombre`, `descripcion`, `PVP`, `stock`, `IDProveedor`, `IDPlataforma`, `activo`, `unidadesProv`, `genero`, `tipo`)
						VALUES ('2', 'FORTNITE', 'descripcion', '20', '6', '1', '2', '1', '6', 'Shooter', 'VideoGame');

----------------------------- 	EMPLOYEE   ---------------------------------------


INSERT INTO `empleado` (`ID`, `nombre`, `NIF`, `turno`, `activo`) VALUES ('1', 'joaquin', '76664094B', 'Early shift', '1');
INSERT INTO `empleado` (`ID`, `nombre`, `NIF`, `turno`, `activo`) VALUES ('2', 'pepe', '94311944F','Early shift', '0');
INSERT INTO `empleado` (`ID`, `nombre`, `NIF`, `turno`, `activo`) VALUES ('3', 'pablo', '74769725Z','Early shift', '1');


