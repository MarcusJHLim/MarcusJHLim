=============================================================
Table Of Content
=============================================================
Deployment Guide
Introduction
Technology Used
Directory layout
Deployment Guide
Possible Enhancements


=============================================================
Introduction
=============================================================
Spring Boot was used because I have a current version with dependencies sorted out.


=============================================================
Technology Used
=============================================================
Sprint Tool Suite 4
SUN JDK 1.16


=============================================================
Directory layout
=============================================================
{PROJECT_HOME}/assessment.sqlite
              /POM.xml
              
              
=============================================================    
Deployment Guide
=============================================================
Download from GitHub
Import Maven project POM.xml
Run As Maven Build to compile
Run As Spring Boot App to run in embedded web container


=============================================================
Open a REST Client to test
=============================================================
1. Call GET Endpoint for answer No 1
GET 
http://localhost:8080/employees/employee/teh

2. Call PUT EndPoint for answer No 2
PUT 
http://localhost:8080/employees/update/teh
Header: Content-Type: application/json
Body Content:
{
	"name" : "teh",
	"monthlySalary" : 91.120
}





=============================================================
Possible Enhancements
=============================================================
- Move REST services to a secured webapp. Only accessible by truested callers
- Authentications
- 

Assumptions
Tax calculation doesn't consider beyond RM60k

