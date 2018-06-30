-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: saveotron_db
-- ------------------------------------------------------
-- Server version	5.7.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- -----------------------------------------------------
-- Schema saveotron_db
-- -----------------------------------------------------

DROP SCHEMA IF EXISTS `saveotron_db` ;

CREATE SCHEMA IF NOT EXISTS `saveotron_db` DEFAULT CHARACTER SET latin1 ;
USE `saveotron_db` ;

--
-- Table structure for table `code_snippets`
--

DROP TABLE IF EXISTS `code_snippets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code_snippets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `text_field` longtext,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(100) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_id_idx` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_snippets`
--

LOCK TABLES `code_snippets` WRITE;
/*!40000 ALTER TABLE `code_snippets` DISABLE KEYS */;
INSERT INTO `code_snippets` VALUES (14,'Odd or even?','int start = -3;\r\nint end = 6;\r\n\r\nfor (int val = start; val < end; val++)\r\n{\r\n    // Condition to Check Even, Not condition (!) will give Odd number\r\n    if (val % 2 == 0) \r\n    {\r\n        System.out.println(\"Even\" + val);\r\n    }\r\n    else\r\n    {\r\n        System.out.println(\"Odd\" + val);\r\n    }\r\n','2018-05-31 09:30:31','2018-06-30 12:18:04','',4),(15,'String to date','SimpleDateFormat format = new SimpleDateFormat( \"dd.MM.yyyy\" );\r\nDate date = format.parse( myString );','2018-05-31 09:33:57','2018-06-30 12:18:04','Converting String to date in Java',4),(16,'Java Date to SQL Date','java.util.Date utilDate = new java.util.Date();\r\njava.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());','2018-05-31 09:37:01','2018-06-30 12:18:04','Converting Java util.Date to sql.Date',4),(18,'int to String and String to int','String a = String.valueOf(2);   //integer to numeric string\r\nint i = Integer.parseInt(a); //numeric string to an int','2018-05-31 09:39:55','2018-06-30 12:18:04','Conver int to String and String to int',4),(19,'JDBC -> Oracle','public class OracleJdbcTest\r\n{\r\n    String driverClass = \"oracle.jdbc.driver.OracleDriver\";\r\n \r\n    Connection con;\r\n     \r\n    public void init(FileInputStream fs) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException\r\n    {\r\n        Properties props = new Properties();\r\n        props.load(fs);\r\n        String url = props.getProperty(\"db.url\");\r\n        String userName = props.getProperty(\"db.user\");\r\n        String password = props.getProperty(\"db.password\");\r\n        Class.forName(driverClass);\r\n \r\n        con=DriverManager.getConnection(url, userName, password);\r\n    }\r\n     \r\n    public void fetch() throws SQLException, IOException\r\n    {\r\n        PreparedStatement ps = con.prepareStatement(\"select SYSDATE from dual\");\r\n        ResultSet rs = ps.executeQuery();\r\n         \r\n        while (rs.next())\r\n        {\r\n            // do the thing you do\r\n        }\r\n        rs.close();\r\n        ps.close();\r\n    }\r\n \r\n    public static void main(String[] args) \r\n    {\r\n        OracleJdbcTest test = new OracleJdbcTest();\r\n        test.init();\r\n        test.fetch();\r\n    }\r\n}','2018-05-31 09:44:34','2018-06-30 12:18:04','Using Java JDBC to connect to Oracle',4),(21,'Numbers from x to 0','public void showNumbersFromToX(int x) {\r\n  		if(x<0) {\r\n  			System.out.println(\"Sorry, number which u inserted is zero or below zero.\");\r\n  			return;\r\n  		}\r\n  		while(x>=0) {\r\n  			System.out.println(x);\r\n  			x--;\r\n  		} \r\n}',NULL,'2018-06-30 12:18:04','Print numbers',4),(28,'Using Java to send an email','import javax.mail.*;\r\nimport javax.mail.internet.*;\r\nimport java.util.*;\r\n \r\npublic void postMail( String recipients[ ], String subject, String message , String from) throws MessagingException\r\n{\r\n    boolean debug = false;\r\n \r\n     //Set the host smtp address\r\n     Properties props = new Properties();\r\n     props.put(\"mail.smtp.host\", \"smtp.example.com\");\r\n \r\n    // create some properties and get the default Session\r\n    Session session = Session.getDefaultInstance(props, null);\r\n    session.setDebug(debug);\r\n \r\n    // create a message\r\n    Message msg = new MimeMessage(session);\r\n \r\n    // set the from and to address\r\n    InternetAddress addressFrom = new InternetAddress(from);\r\n    msg.setFrom(addressFrom);\r\n \r\n    InternetAddress[] addressTo = new InternetAddress[recipients.length]; \r\n    for (int i = 0; i < recipients.length; i++)\r\n    {\r\n        addressTo[i] = new InternetAddress(recipients[i]);\r\n    }\r\n    msg.setRecipients(Message.RecipientType.TO, addressTo);\r\n    \r\n \r\n    // Optional : You can also set your custom headers in the Email if you Want\r\n    msg.addHeader(\"MyHeaderName\", \"myHeaderValue\");\r\n \r\n    // Setting the Subject and Content Type\r\n    msg.setSubject(subject);\r\n    msg.setContent(message, \"text/plain\");\r\n    Transport.send(msg);\r\n}','2018-06-06 09:25:57','2018-06-30 12:18:04','',4),(29,'PDF Generation in Java','import java.io.File;\r\nimport java.io.FileOutputStream;\r\nimport java.io.OutputStream;\r\nimport java.util.Date;\r\n\r\nimport com.lowagie.text.Document;\r\nimport com.lowagie.text.Paragraph;\r\nimport com.lowagie.text.pdf.PdfWriter;\r\n\r\npublic class GeneratePDF {\r\n\r\n    public static void main(String[] args) {\r\n        try {\r\n            OutputStream file = new FileOutputStream(new File(\"C:\\\\Test.pdf\"));\r\n\r\n            Document document = new Document();\r\n            PdfWriter.getInstance(document, file);\r\n            document.open();\r\n            document.add(new Paragraph(\"Hello Kiran\"));\r\n            document.add(new Paragraph(new Date().toString()));\r\n\r\n            document.close();\r\n            file.close();\r\n\r\n        } catch (Exception e) {\r\n\r\n            e.printStackTrace();\r\n        }\r\n    }\r\n}','2018-06-06 09:27:57','2018-06-30 12:18:04','PDF Generation in Java using iText JAR',4),(30,'Screen shoots','import java.awt.Dimension;\r\nimport java.awt.Rectangle;\r\nimport java.awt.Robot;\r\nimport java.awt.Toolkit;\r\nimport java.awt.image.BufferedImage;\r\nimport javax.imageio.ImageIO;\r\nimport java.io.File;\r\n\r\n...\r\n\r\npublic void captureScreen(String fileName) throws Exception {\r\n\r\n   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();\r\n   Rectangle screenRectangle = new Rectangle(screenSize);\r\n   Robot robot = new Robot();\r\n   BufferedImage image = robot.createScreenCapture(screenRectangle);\r\n   ImageIO.write(image, \"png\", new File(fileName));\r\n\r\n}\r\n...','2018-06-06 09:33:53','2018-06-30 12:18:04','Capture screen shots in Java',4),(31,'Array -> Map in Java','import java.util.Map;\r\nimport org.apache.commons.lang.ArrayUtils;\r\n \r\npublic class Main {\r\n \r\n  public static void main(String[] args) {\r\n    String[][] countries = { { \"United States\", \"New York\" }, { \"United Kingdom\", \"London\" },\r\n        { \"Netherland\", \"Amsterdam\" }, { \"Japan\", \"Tokyo\" }, { \"France\", \"Paris\" } };\r\n \r\n    Map countryCapitals = ArrayUtils.toMap(countries);\r\n \r\n    System.out.println(\"Capital of Japan is \" + countryCapitals.get(\"Japan\"));\r\n    System.out.println(\"Capital of France is \" + countryCapitals.get(\"France\"));\r\n  }\r\n}',NULL,'2018-06-30 12:18:04','Convert Array to Map in Java',4);
/*!40000 ALTER TABLE `code_snippets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'admin','$2a$10$xENgbYDIIOcrcvhlT/CguOOV2DqDkOmtwqB7aV68JM9tRWG42KQha','Patryk','Wojciechowski','patrickwww94@gmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_ROLE_idx` (`role_id`),
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (4,1);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-30 14:27:46
