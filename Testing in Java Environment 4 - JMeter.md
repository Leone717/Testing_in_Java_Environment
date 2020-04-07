# Testing in Java Environment 4 - Apache JMeter

- download: https://jmeter.apache.org/ --> *Download Releases* --> Binaires, .zip file
- copy into Program Files folder

```
C:\Program Files\apache-jmeter-5.2.1\bin

jmeter.bat --> for windows
jmeter.sh  --> for linux
```
- run the program

#### We would test a certain webpage, 200 people can visit it at the same time. We send "f1 test" to Google and Google send back a html page. I will stress test this link:

https://www.google.com/search?client=firefox-b-d&ei=9kuLXr_7MKHqrgTNlLOwAw&q=f1+test&oq=f1+test&gs_lcp=CgZwc3ktYWIQAzIECAAQQzICCAAyBAgAEEMyAggAMgIIADICCAAyAggAMgIIADICCAAyAggAOgQIABBHOgYIABAHEB5KEQgXEg00LTlnMjM5ZzBnMTYzSg0IGBIJNC0zZzFnMGcxUK8fWPYjYNkoaABwA3gAgAGKAogBjgWSAQUwLjEuMpgBAKABAaoBB2d3cy13aXo&sclient=psy-ab&ved=0ahUKEwi_zvr-j9ToAhUhtYsKHU3KDDYQ4dUDCAs&uact=5

- new -> *Google Test Laboratorium* -> save
- right click -> add -> threads -> Thread Group -> *Google Resarch Room* -> save
- right click -> add -> Sampler -> HTTP Request:

Server name or IP: **google.com**,

Method: **GET**,    

Path: **after/**:  /search?client=firefox-b-d&ei=9kuLXr_7MKHqrgTNlLOwAw&q=f1+test&oq=f1+test&gs_lcp=CgZwc3ktYWIQAzIECAAQQzICCAAyBAgAEEMyAggAMgIIADICCAAyAggAMgIIADICCAAyAggAOgQIABBHOgYIABAHEB5KEQgXEg00LTlnMjM5ZzBnMTYzSg0IGBIJNC0zZzFnMGcxUK8fWPYjYNkoaABwA3gAgAGKAogBjgWSAQUwLjEuMpgBAKABAaoBB2d3cy13aXo&sclient=psy-ab&ved=0ahUKEwi_zvr-j9ToAhUhtYsKHU3KDDYQ4dUDCAs&uact=5

- start(green button) -> *We don't get anything, because we didn't ask any values.*
- right click on *Google Research room* -> Add -> Listener -> View Results Tree
- right click on *Google Research room* -> Add -> Listener -> View Results in Table
- start(green button)
- check the Tree and Result
- because we **skipped www.** in our query, you will find an other one in:
HTTP Request->  HTTP Request-0, HTTP Request-1 --> Response Data, Response Boday, 0-> *301 Moved*
- **we always use the www.** rewrite the query(www.google.com)
You won't see 0 and 1.

## JMeter assertion

We can expand our parameter by body, numbers of query etc.

- *Google Resarch Room* -> **Number of Threads(users)** ->number of users: *10*
- start for 10 people(green button)->*View Results in Table*

You will see 10 queries here. We would like to **execute the query at the same time:**

- Ramp-Up Period(in second): **10** -> run

**You see 1 query in every second.**

- try 50 users/60 second
- loop count: **infinite**
- Right click on *Google research room* -> add -> Assertions -> Duration Assertion -> *Duration in millisecond: 500*

We setted the maximum time limit is 500.

- run -> And you found a failed query.
- right click on *Google research room* -> add -> Assertions -> Response Assertion -> *Response Code, type 200 in the window* -> path: **/** -> run
- Right click on *Google research room* -> add -> Assertions -> Size Assertion -> *Repsonse Body, Size in bytes: 13000 <* -> run

## JMeter report

We do the test with more parameters.   
number of Thread(users): *500*  
Ramp-Up Period(in seconds): *3*  
Duration in millisecond: *500*  
Response Assertion: *200*  
Response body, Size in bytes: *13000 <*

- right click on *Google research room* -> add -> Listener -> **Summary Report**
- right click on *Google research room* -> add -> Listener -> **Aggregate Report**
- start

You can save queries in a file or excel. It is eaiser to make reports. **You determinate the test parameters(Sample Result Save Configuration)**

## Traffic Record with Firefox & JMeter

We save the traffic in **Firefox browser**
- open
- beállítások -> általános beállítások -> beállítások(hálózati proxy)
- kézi proxybeállítás:
HTTP-proxy: **127.0.0.1** Port: **8888** -> ok
- adatvédelem és biztonság --> tanúsítványok megtekintése --> **importálás**

- apache folder/bin --> .crt
- open Apache JMeter --> templates --> **Recording**
- you will see User Definied Variables, *HTTP Request Defaults*-> Server Name: https://www.cointheatrecompany.com/, path: **/**
- HTTP(S) Test Script Recorder -->Port: 8888 --> start
- go to https://www.cointheatrecompany.com/ **in browser**
- check --> **Recording controller**&**HTTP(S)Test Script Recorder** You see a lot of elements.

## Chrome Blazemeter

- open Chrome
- extensions (bővítmények) --> **blazemeter**
- set up and open the icon in the Chrome browser
- record, stop, save as JMeter or json
- open Apache JMeter, open the **jmx** record  

## JMeter in Terminal

```
                  Linux Server

        The cronjob runs every week
              cronjob
                |
                V
      The script calls the test
            .sh
              |
              V
      JMeter test         Results
          .jmx    ----->    .csv
```
Here is the opportunity to make a test in GUI and save it. You add this file for your Linux server, optimize when runs and save the test result. Last step, your server sends datas every week how changed the load of Application.  

- right click on *Thread group* -> add -> Sampler -> HTTP Request
*Server name:* https://www.cointheatrecompany.com/
*Path:* **/**
- right click on *Thread group* -> add -> Listener -> Summary Report
- start
- save as *Summary Report.jmx*
- go to *HTTP(S) Request* and add paramters to Name: **HTTP Request1 - ${__time(YYYY.MM.dd.HH.mm.ss)}**
- run test, go to *Summary Report*, you see the date
- **save**
```
C:\Program Files\apache-jmeter-5.2.1\bin
jmeter -n
jmeter -n -t ./SummaryReport.jmx -l
touch TestResult.csv
jmeter -n -t./SummaryReport.jmx -l ./TestResult.csv
```

You will see the excel report.
