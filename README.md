Welcome to our QA project focused on testing the social network "WeAre." We've strived to adhere to best QA practices and techniques to ensure the software is up to par, meeting client specifications and ready for user engagement.

## Table of Contents

1. [Testing Methods and Tools for the 'Weare' Social Network](#testing-methods-and-tools-for-the-weare-social-network)
    - 1.1. [Test Plan](#test-plan)
    - 1.2. [Test Cases](#test-cases)
    - 1.3. [Bug Reports](#bug-reports)
    - 1.4. [Manual Testing](#manual-testing)
    - 1.5. [Automated Testing](#automated-testing)

2. [API Testing](#api-testing)
    - 2.1. [REST Assured for API Testing](#rest-assured-for-api-testing)
    - 2.2. [Postman for API Testing](#postman-for-api-testing)

3. [Selenium WebDriver for Frontend Testing](#selenium-webdriver-for-frontend-testing)

4. [Performance Testing](#performance-testing)


<a id="testing-methods-and-tools-for-the-weare-social-network"></a>
## 1. Testing Methods and Tools for the 'Weare' Social Network
<a id="test-plan"></a>
### 1.1. Test Plan
   A comprehensive document detailing our entire testing strategy. Learn more [here](https://docs.google.com/document/d/1qusFY8wjJVw9leNYCJ7APmfFNE0kEHXNIHZeShnHOB4/edit#heading=h.ko13e0l973xx)

<a id="test-cases"></a>
### 1.2. Test Cases
   We craft our tests based on well-defined structures. Check out our template [here](https://docs.google.com/document/d/1YDBI0sy6ODsjIGpJVKsuTKlMj6wY28wweYQ9745Uajk/edit) 

<a id="bug-reports"></a>
### 1.3. Bug Reports
  When we identify issues, we keep detailed records. Check out our template [here](https://docs.google.com/document/d/1v188BdkWdBaDQyoSngY2v4bKK9Iwz23AxYw_GooJ2b0/edit)


<a id="manual-testing"></a>
### 1.4. Manual Testing
   We utilize Xray to guide and document our testing process. Check out our high-level tests [here](https://docs.google.com/document/d/13JbtgMkoewZqYxkTAWf48Ov5XlUKCpQye8NzAHrx_f4/edit#heading=h.guc4o2h0okqz).
   For a detailed overview of our test cases in Xray, please see [this link](https://tsvetan-iliev.atlassian.net/jira/software/c/projects/WEAR/boards/4/timeline).

<a id="automated-testing"></a>
### 1.5. Automated Testing
   We use tools to help speed up our testing:
   - Backend Testing: Using tools like Postman and REST Assured
   - Frontend Testing: We use Selenium WebDriver
 
<a id="api-testing"></a>
## 2. API Testing
 We employ rigorous API testing to ensure optimal performance and reliability.
<a id="rest-assured-for-api-testing"></a>
### 2.1. REST Assured for API Testing


 
<a id="postman-for-api-testing"></a>
### 2.2. Postman for API Testing
   If you want to see our **API TESTING through Postman**, please follow these steps:
   - Get the files from our 'Postman - API testing' folder.
   - Start Postman.
   - Add the "WeAre_Social Network Project.postman_collection.json" and "Rest-controller.postman_environment.json" files.
   - To run the entire collection or specific folders, select the desired folder or collection, and then click "Run".
 To run with Command Line:
 ```
To run with Command Line:
Go to where the files are on your computer. Type this:
```shell
newman run "WeAre_Social Network Project.postman_collection.json" -e "Rest-controller.postman_environment.json" -r htmlextra --reporter-htmlextra-export "./WeAre_API_Testing.html"
```

<a id="selenium-webdriver-for-frontend-testing"></a>

## 3. Selenium WebDriver for Frontend Testing
We employ Selenium WebDriver as our primary tool for executing end-to-end frontend tests, validating the functionality and responsiveness of our web applications.

<a id="performance-testing"></a>

## 4. Performance Testing
For ensuring optimal user experience, we conduct performance tests to measure and optimize the responsiveness, scalability, and stability of our system under different loads.


