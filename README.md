# AutomationBot

This automation frame work is based on **BDD** approach for testing **REST API** endpoints.
I have created a **feature** file and documented test scenerios in it and then I linked it to **step definition** file.
Also configured the **circle ci** pipline.

## Developement Enviroment/Libararies
Below are the libararies I have used with latest version avialble to now<br/>
**Java Version** 9 &nbsp;&nbsp;
**Maven Compiler** 1.7&nbsp;&nbsp;
**Cucumber Java 6.10.4 &nbsp &nbsp;&nbsp;
**Cucumber Junit**3.10.4 &nbsp;&nbsp;
**Resst Assured**4.4.4 &nbsp;&nbsp;
**Log4j** 2.14.0 &nbsp;&nbsp;
**Junit** 4.13.1 
**hamcrest** 2.2 &nbsp;&nbsp;
**circleci** 2.1 &nbsp;&nbsp;

## Achitecture
The whole frame work can be broken down to following packages(modules) <br/>
&nbsp;&nbsp; 1) **starter** Its purpose to run the test cases with least configuration and filteration on tags <br/>
&nbsp;&nbsp; 2) **steps_binder** This module will on contains step definitions files(.java) linked to feature files(.feature). <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Each feature file will have only one step definition file.<br/>
&nbsp;&nbsp; 3) **http_request_processor** This module will handel all of the http request and Responses (GET,POST,DELETE,PUT etc). <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Its a wrapper over "rest assured libarary", it also contains response extartors <br/>
&nbsp;&nbsp; 4) **validator** Its contains class will will process the respponse or any input which needs specific processing. Always resturn true or false </br>

All of the above packages are under "src/test/java" and each module contains group of classes depending up role <br>

## Project Directories

#### /AutomationBot/src/test/resources <br/>
&nbsp;&nbsp; 1) You will find feature files under **"/AutomationBot/src/test/resources/features/comment.feature"** <br/>
&nbsp;&nbsp; 2) Static test data files like json schema under **"/AutomationBot/src/test/resources/testData"** <br/>
&nbsp;&nbsp; 3) Log4j2 configuration xml file **"/AutomationBot/src/test/resources/log4j2.xml"** <br/>
#### /AutomationBot/src/test/resources <br/>
&nbsp;&nbsp; 4) You will find all of the packages
#### /AutomationBot/target <br/>
&nbsp;&nbsp; 5) All of the logs under **"/AutomationBot/src/test/resources/testData"** <br/>
&nbsp;&nbsp; 6) All of test reports under **"/AutomationBot/target/test/reports"** <br/>
#### /AutomationBot/target <br/>
&nbsp;&nbsp; 7) Circleci config.yml under **"/AutomationBot/.circleci/"** <br/>
&nbsp;&nbsp; 8) Maven configuration pom.xml under **"/AutomationBot/"** <br/>
