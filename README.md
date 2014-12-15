API Rest para localização de pontos de interesse(POIs)
======================================================

Aplicação para cadastro e buscas de POIs.

Pré-requisitos para execução
-------------------------------------------------------
 * Java 1.7+
 * MySQL 5.6
 * Apache Maven
 * Apache Tomcat 7+

Configuração
-------------------------------------------------------
* Execute o arquivo de scripts [POI_Script.sql.](https://github.com/danielcoelho11/xy-inc/blob/master/sqlScripts/POI_Script.sql)
* Após realizar o download do projeto altere a url de conexão, usuário e senha de acesso ao DB em [hibernate.cfg.xml](https://github.com/danielcoelho11/xy-inc/blob/master/poi-api/src/main/resources/hibernate.cfg.xml)
 
Execução
-------------------------------------------------------
Após o deploy da aplicação as seguintes operações estarão disponíveis:

* Cadastrar POIs
  * Endpoint: http://localhost:8080/poi-api/api/rest/pois/
  * Método: POST
  * Content-Type: application/json
  * Ex de parâmetros: {"name" : "Cafeteria", "pointX" : 19, "pointY" : 10}

* Recuperar todos os POIs
  * Endpoint: http://localhost:8080/poi-api/api/rest/pois/
  * Método: GET
  
  * Buscar POIs por proximidade
  * Endpoint: http://localhost:8080/poi-api/rest/pois/nearest?pointX=&pointY=&maxDistance=
  * Método: GET
  * Exmplo de chamada: http://localhost:8080/poi-api/rest/pois/nearest?pointX=20&pointY=10&maxDistance=10
