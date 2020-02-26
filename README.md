Framework Design:
I have built a BDD framework to automate the rest services which are built for the application.

Technologies:
Language: Java
Built With: Maven
REST Assured
TestNG
Cucumber

SetUp:
To run this project, import the project and download the dependencies mentioned in the pom.xml file of the Project

Running the tests:
run all: tests
        mvn test

with tags:
        mvn test -Dcucumber.options="--tags @success_test"
tags : @success_test, @error


Where to check reports:
Reports will be generated on below path 
RestCarTask/target/cucumber-reports/cucumber-jvm-reports/cucumber-html-reports/
* report-feature_CarSelection-feature.html
* overview-features.html

Where to check logs:
The logs are logged under log folder at below path
RestCarTask\src\test\log

Found below observation while testing the api:
Observations:
1. Key should be passed in header not as a query parameter.
2. Call does’t fails when manufacturer/main-type is given wrong and it returns empty response
3. When wrong url is hit it should be 404 but it gives 200 and empty array in body 
http://api-aws-eu-qa-1.auto1-test.com/v1/car-types/manufacturer/wrong-service?wa_key=coding-puzzle-client-449cc9d
4. Protocol used is http it should be https {Currently it works with both http and https}
5. In main-types and built-types 'wkda' response contains id=value which is incorrect. == NO 
6. In case of POST http://api-aws-eu-qa-1.auto1-test.com/v1/car-types/manufacturer?wa_key=coding-puzzle-client-449cc9d
    call its giving the trace details which can be vulnerable for security.
    Conclusion: ***POST call for all the three endpoint gives trace which may be a security issue if the exception 
    stacktrace returned from server***
7. Get call request header changes in case of http and https protocol


Genarate allure Report :  
1. allure serve
2. allure generate -c ./allure-results