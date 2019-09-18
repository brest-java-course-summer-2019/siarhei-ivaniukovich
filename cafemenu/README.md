### CafeMenu ```devrep```

>Builds: 
[![Build Status](https://travis-ci.org/sergeblr/devrep.svg?branch=master)](https://travis-ci.org/sergeblr/devrep)
[![Coverage Status](https://coveralls.io/repos/github/sergeblr/devrep/badge.svg?branch=master)](https://coveralls.io/github/sergeblr/devrep?branch=master)
[![buddy pipeline](https://app.buddy.works/sergeblr/devrep/pipelines/pipeline/207563/badge.svg?token=b66c7e74848c94b4f57f172d0ac8a16b1d44347935104f2bfa878a3a9fb93df8 "buddy pipeline")](https://app.buddy.works/sergeblr/devrep/pipelines/pipeline/207563)

>Chckrs:
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/bcbc4a4f91e24705a2aaccee6151cd58)](https://app.codacy.com/app/sergeblr/devrep?utm_source=github.com&utm_medium=referral&utm_content=sergeblr/devrep&utm_campaign=Badge_Grade_Dashboard)
[![codebeat badge](https://codebeat.co/badges/751a85b1-78ad-477e-9a83-083fb97bfff2)](https://codebeat.co/projects/github-com-sergeblr-devrep-master)
[![MIT License](https://img.shields.io/badge/license-MIT-green.svg?style=flat)](https://github.com/sergeblr/devrep/blob/master/cafemenu/README.md)


### Project description
 Cafemenu - application for cafe employees to control of making orders (adding/editing items), filter them by date&time, editing and summarize by items price inside & item quantity.

### Prerequisites
- jdk11
- maven 3+
- tomcat9

***
**Installing**  
 - Preinstalling:
   - <details>
       <summary>install jdk11</summary>
       
       prepare: `sudo apt update`
       
       install: `sudo apt install openjdk-11-jre-headless`
       
       check: `java -version`
     </details>
   - <details>
       <summary>install maven</summary>
       
       prepare: `sudo apt update`   
       
       install: `sudo apt install maven`
       
       check: `mvn -version`
       </details>

 - Download project from github
 URL: `https://github.com/brest-java-course-summer-2019/siarhei-ivaniukovich.git`
 - Run at root of unpacked project /cafemenu folder to build project:
 `mvn clean install`
 
**Running app**
 - Using plugin jetty:
   - inside /cafemenu/rest-app dir run: `mvn jetty:run`, then open in browser: `http://localhost:8082` for REST API with examples
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

