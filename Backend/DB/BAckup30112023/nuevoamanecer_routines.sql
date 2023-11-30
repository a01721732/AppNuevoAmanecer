-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: nuevoamanecer
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping routines for database 'nuevoamanecer'
--
/*!50003 DROP PROCEDURE IF EXISTS `procAlumno` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `procAlumno`(
	_iIdAlumno int
)
BEGIN
	select a.iIdAlumno, a.sNombre as sNombreAlumno, a.sApellido as sApellidoAlumno, a.iEdad, a.iIdNivelCognitivo , b.sNombre as sNivelCognitivo, a.bActivo
    from tAlumno a
    inner join tNivelCognitivo b ON a.iIdNivelCognitivo = b.iIdNivelCognitivo
	where a.bActivo = 1
    and iIdAlumno = case when _iIdAlumno is NULL or _iIdAlumno = 0 then iIdAlumno else _iIdAlumno end;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `procAlumnoABC` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `procAlumnoABC`(
	_iIdAlumno int,
    _sNombre varchar(50),
    _sApellido varchar(100),
    _iEdad int,
    _iIdNivelCognitivo int,
    _bActivo bit,
    _bDelete bit
)
BEGIN
	IF((select iIdAlumno from tAlumno where iIdAlumno = _iIdAlumno limit 1) is null) 
		Then
			set _iIdAlumno = (select MAX(iIdAlumno)+1 from tAlumno limit 1);
			insert into tAlumno(iIdAlumno, sNombre, sApellido, iEdad, iIdNivelCognitivo, bActivo)
            values(_iIdAlumno, _sNombre, _sApellido, _iEdad, _iIdNivelCognitivo, _bActivo);
	ELSE       
		IF(_bDelete = 0) THEN
			update tAlumno
				set sNombre = _sNombre, 
				sApellido = _sApellido, 
				iEdad = _iEdad, 
				iIdNivelCognitivo =_iIdNivelCognitivo, 
				bActivo = _bActivo
			where iIdAlumno = _iIdAlumno;
		ELSE
			Update tAlumno
				set bActivo = 0
			where iIdAlumno = _iIdAlumno;
        END IF;
	END IF;
	


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `procAlumnoPsicologo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `procAlumnoPsicologo`(
	_iIdPsicologo int
)
BEGIN
	
	select  a.iIdAlumno,a.sNombre, a.sApellido, a.iIdNivelCognitivo, a.bActivo from tAlumno a
	inner join tAlumnoPsicologo b ON a.iIdAlumno = b.iIdAlumno
	inner join tPsicologo c ON b.iIdPsicologo = c.iIdPsicologo
	where a.bActivo = 1
    and c.iIdPsicologo = _iIdPsicologo;
    #where iIdAlumno = case when _iIdAlumno is NULL then iIdAlumno when _iIdAlumno = 0 then iIdAlumno else _iIdAlumno end;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `procAlumnoPsicologoUpdate` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `procAlumnoPsicologoUpdate`(
	_iIdPsicologo int,
    _iIdAlumno int
)
BEGIN
	if((select iIdAlumnoPsicologo from tAlumnoPsicologo where iIdPsicologo = _iIdPsicologo and iIdAlumno = _iIdAlumno limit 1) is not null) THEN
		delete from tAlumnoPsicologo 
        where iIdPsicologo = _iIdPsicologo and iIdAlumno = _iIdAlumno;
	Else
		insert into tAlumnoPsicologo(iIdPsicologo, iIdAlumno)
        values(_iIdPsicologo, _iIdAlumno);
        
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `procAlumnoPsicologoView` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `procAlumnoPsicologoView`(
	_iIdPsicologo int
)
BEGIN
	select iIdAlumno, sNombre, sApellido, case when iIdAlumno not in(
		select iIdAlumno from talumnopsicologo
		where iIdPsicologo = 1) then 0 else 1 end as bPsicologo
	from tAlumno
    where bActivo = 1
    order by bPsicologo desc;
    








    #where iIdAlumno = case when _iIdAlumno is NULL then iIdAlumno when _iIdAlumno = 0 then iIdAlumno else _iIdAlumno end;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `procAlumnoUpdateNivel` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `procAlumnoUpdateNivel`(
	_iIdAlumno int,
	_iIdNivelCognitivo int
)
BEGIN
	update tAlumno
		set iIdNivelCognitivo = _iIdNivelCognitivo
	where iIdAlumno = _iIdAlumno;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `procContacto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `procContacto`(
	_iIdAlumno int
)
BEGIN
	select sNombreContacto, sApellidoContacto, iTelefono, sEmail, sRelacionAlumno
    from tContacto
    where iIdAlumno = _iIdAlumno;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `procCurso` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `procCurso`()
BEGIN
	select sNombreCurso, sDescripcionCurso
    from tCurso;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `procCursoUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `procCursoUsuario`(
	_iIdPsicologo int,
    _iIdAlumno	  int
)
BEGIN
	select a.iIdCurso, b.sNombreCurso
    from tCursoUsuario a
    inner join tCurso b ON a.iIdCurso = b.iIdCurso
    where a.iIdPsicologo = case when _iIdPsicologo = NULL then a.iIdPsicologo else _iIdPsicologo end
    and a.iIdAlumno = case when _iIdAlumno = NULL then a.iIdAlumno else _iIdAlumno end;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `procNivelCognitivo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `procNivelCognitivo`()
BEGIN
	select iIdNivelCognitivo, sNombre
    from tNivelCognitivo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `procPsicologo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `procPsicologo`(
	_iIdPsicologo int
)
BEGIN
	
	select  iIdPsicologo, sUserNamePsicologo, sPasswordPsicologo, sNombrePsicologo, sApellidoPsicologo, iTelefonoPsicologo, sEmailPsicologo, bActivo
    from tPsicologo a
	where a.bActivo = 1
    and iIdPsicologo = case when _iIdPsicologo is NULL or _iIdPsicologo = 0 then iIdPsicologo else _iIdPsicologo end;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `procPsicologoABC` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `procPsicologoABC`(
	_iIdPsicologo int,
	_sUserName varchar(100),
    _sPassword varchar(100),
    _sNombre varchar(50),
    _sApellido varchar(100),
    _iTelefono varchar(15),
    _sEmail varchar(50),
    _bActivo bit,
    _bDelete bit
)
BEGIN
	IF((select iIdPsicologo from tPsicologo where iIdPsicologo = _iIdPsicologo limit 1) is null) 
		Then
			set _iIdPsicologo = (select MAX(iIdPsicologo)+1 from tPsicologo limit 1);
			insert into tPsicologo(iIdPsicologo,sUserNamePsicologo, sPasswordPsicologo, sNombrePsicologo, sApellidoPsicologo, iTelefonoPsicologo, sEmailPsicologo, bActivo)
            values(_iIdPsicologo, _sUserName, _sPassword, _sNombre, _sApellido, _iTelefono, _sEmail, _bActivo);
	ELSE       
		IF(_bDelete = 0) THEN
			update tPsicologo
				set sUserNamePsicologo = _sUserName, 
				sPasswordPsicologo = _sPassword, 
				sNombrePsicologo = _sNombre, 
				sApellidoPsicologo = _sApellido, 
				iTelefonoPsicologo = _iTelefono, 
				sEmailPsicologo =_sEmail , 
				bActivo = _bActivo
			where iIdPsicologo = _iIdPsicologo;
		ELSE
			Update tPsicologo
				set bActivo = 0
			where iIdPsicologo = _iIdPsicologo;
        END IF;
	END IF;
	


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `procPsicologoLogin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `procPsicologoLogin`(
	_sUserNamePsicologo varchar(100)
    ,_sPasswordPsicologo varchar(255)
)
BEGIN
	select iIdPsicologo, sNombrePsicologo
    from tPsicologo
    where sUserNamePsicologo = _sUserNamePsicologo
    and sPasswordPsicologo = _sPasswordPsicologo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-30  9:59:55
