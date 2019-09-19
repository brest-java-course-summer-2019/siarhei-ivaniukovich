[![Build Status](https://travis-ci.org/brest-java-course-summer-2019/siarhei-ivaniukovich.svg?branch=master)](https://travis-ci.org/brest-java-course-summer-2019/siarhei-ivaniukovich)
[![Coverage Status](https://coveralls.io/repos/github/brest-java-course-summer-2019/siarhei-ivaniukovich/badge.svg?branch=master)](https://coveralls.io/github/brest-java-course-summer-2019/siarhei-ivaniukovich?branch=master)


### Project description
 Cafemenu - application for cafe employees to control of making orders (adding/editing items), filter them by date&time, editing and summarize by items price inside & item quantity.

### Prerequisites
- jdk11
- maven 3+
- tomcat9

***
**Installing**  
 - Download project from github
 URL: `https://github.com/brest-java-course-summer-2019/siarhei-ivaniukovich.git`
 - Run at root of unpacked project folder to build project:
 `mvn clean install`
 
**Running app**
 - Using plugin jetty:
   - inside /cafemenu/rest-app dir run: `mvn jetty:run`, then open in browser: `http://localhost:8082/cafemenurest` for REST API examples
   - inside /cafemenu/web-app dir run: `mvn jetty:run`, then open in browser: `http://localhost:8083` for WEB startpage
 
 - Using server tomcat9:
   - Install, configure & run tomcat 9 server: `https://www.howtoforge.com/tutorial/ubuntu-apache-tomcat/`
   - deploy *.WARs via tomcat web-interface to server
   - open CafeMenu web-app via browser at address as WARs named

**Running the tests**  
 `mvn clean test`

**Reports**  
`mvn site`

---

**External server**
 For testing ability (based on buddy.works autodeploy to own server):
   - rest-app `http://***.***.***.71:8082`
   - web-app `http://***.***.***.71:8083`

***