# Final QA Project by Darina, Radostin, and Tsvetan

Welcome to our final QA project presentation. Our job is to test the 'Weare' social network using QA methods and techniques, ensuring our clients get a product that's ready for the market.

## Testing Methods and Tools for the 'Weare' Social Network:

1. **Test Plan:**
   A comprehensive document detailing our entire testing strategy. You can find more about it in the link provided. Learn more [[here](https://docs.google.com/document/d/1qusFY8wjJVw9leNYCJ7APmfFNE0kEHXNIHZeShnHOB4/edit#heading=h.ko13e0l973xx)](#).

2. **Test Cases:**
   We use a planned method to create our tests. Check out our template [[here](https://docs.google.com/document/d/1YDBI0sy6ODsjIGpJVKsuTKlMj6wY28wweYQ9745Uajk/edit)](#).

3. **Bug Reports:**
   When we find something wrong, we write it down. Check out our template [[here](https://docs.google.com/document/d/1v188BdkWdBaDQyoSngY2v4bKK9Iwz23AxYw_GooJ2b0/edit)](#).

2. **Test Cases:**
   We use a planned method to create our tests. Check out our template [[here](https://docs.google.com/document/d/1YDBI0sy6ODsjIGpJVKsuTKlMj6wY28wweYQ9745Uajk/edit)](#).
   
5. **Automated Testing:**
   We use tools to help speed up our testing:
   - **Backend Testing:** Using tools like Postman and REST Assured, we ensure our APIs are robust and meet expected standards.
   - **Frontend Testing:** We use Selenium Webdriver to test what users see many times.
  

     

If you want to see our **API TESTING through Postman**, please follow these steps:

- Get the files from our 'Postman - API testing' folder.
- Start Postman.
- Add the "WeAre_Social Network Project.postman_collection.json" and "Rest-controller.postman_environment.json" files.
- To run the entire collection or specific folders, select the desired folder or collection, and then click "Run".


**To run with Command Line:**
Go to where the files are on your computer. Type this:
```shell
newman run "WeAre_Social Network Project.postman_collection.json" -e "Rest-controller.postman_environment.json" -r htmlextra --reporter-htmlextra-export "./WeAre_API_Testing.html"




