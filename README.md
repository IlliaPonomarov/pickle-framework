# The Pickle
![Logo](./img/logo.png)
## Table of Contents
1. [Pickle Philosophy](#1-pickle-philosophy)
2. [Pickle Installation](#2-pickle-installation)
    1. [JAR files](#21-jar-files)
    2. [Docker](#22-docker)
3. [How to run Pickle ?](#3-how-to-run-pickle-)
    1. [Windows](#31-windows-)
    2. [Linux](#32-linux-)
4. [Pickle TechStack](#4-pickle-techstack)
5. [How to test endpoints ?](#5-how-to-test-endpoints-)
6. [Pickle file structure](#6-pickle-file-structure)<br/>
6.1 [REST](#61-rest)
    1. [YAML](#611-yaml)
    2. [JSON](#622-json)
    3. [XML](#623-xml)

   6.2 [SOAP](#62-soap)
   4. [YAML](#621-yaml)
   5. [JSON](#622-json)
   6. [XML](#623-xml)
7. [How to import pre-defined files](#7-how-to-import-pre-defined-files-)
    1. [YAML](#71-yaml)
    2. [JSON](#72-json)
    3. [XML](#73-xml)
8. [com.pickle.Test Results](#8-test-results)
9. [Maven Dependencies](#9-maven-dependencies)


## 1. Pickle Philosophy
> Main goal of Pickle is to provide a simple and easy test of your endpoints based on your JSON / XML / YAML configurations.

## 2. Pickle Installation
> 1. Install Java 11 and Kotlin 1.5 versions <br/> To check the version of Maven, run the following command in the terminal:
     ```java --version``` or  ```kotlin --version```<br/>
     
> 2. Install Maven 3.6.3. <br/> To check the version of Maven, run the following command in the terminal:
```mvn --version```

### 2.1 JAR files:
> 1. Download the latest version of Pickle from [here](


### 2.2 Docker
> EMPTY

## 3. How to run Pickle ?
> Run the following command in the terminal:<br/>

### 3.1 Windows: <br/>
> 3.1.1 With pickle-test.yaml```pickle.bat -t /path/to/test/pickle-test.yaml -r /path/to/result/folder``` <br/>
> 3.1.2 Without pickle-test.yaml  <br/>```pickle.bat --request --method GET/get --url http://example.com/ --header --accept application/json --content-type application/json  --authorizartion "Bearer 1234" --user-agent "Mozilla/5.0" --request-data /path/to/request-data.yaml --expected-response --status 200 --body /path/to/excpected-body.yaml --result /path/to/result/folder -r /path/to/result/folder``` <br/>

### 3.2 Linux: <br/>
> 3.2.1 With pickle-test.yaml```pickle.sh -t /path/to/test/pickle-test.yaml -r /path/to/result/folder``` <br/>
> 3.2.2 Without pickle-test.yaml <br/>```pickle.sh --request --method GET/get --url http://example.com/ --header --accept application/json --content-type application/json  --authorizartion "Bearer 1234" --user-agent "Mozilla/5.0" --request-data /path/to/request-data.yaml --expected-response --status 200 --body /path/to/excpected-body.yaml --result /path/to/result/folder -r /path/to/result/folder``` <br/>


## 4. Pickle TechStack
> 1. Java 11
> 2. Kotlin 1.5
> 3. Maven 3.6.3
> 4. JUnit 5
> 5. Spring Core 5.3.8

## 5. How to test endpoints ?
> 1. Create a new folder which will contain your test files. <br/>
> 2. Create a new ```pickle-test``` file with the extension ``` .json / .yaml / .xml ``` <br/>
> 3. Your pickle file should be contain ```request, header, request-data,  excpected-response``` fields. <br/>
>> 3.1. Of course , you can write path to already existing file which contains field data. For example you can have ```request.json``` file
>> which contain already defined elements. <br/> Notes: ```request``` and another pre-defined files should have equal extension as a pickle file <br/>
> 4. When you run the Pickle, you should specify the path to the pickle file and the path to the folder where the result will be saved. <br/>
> 5. After the test is completed, you will see the result in the specified folder. pickle-success-report.yaml and pickle-failed-report.yaml will be created in your pre-defined path. <br/>

## 6. Pickle file structure
### 6.1 REST
#### 6.1.1 YAML
```
   rest:
      request:
            method: GET
            url: https://example.com/hello
      header:
            Accept: application/json
            Content-Type: application/json
            Authorization: "Bearer 1234567890" ( Optional )
            User-Agent: "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)" ( Optional )
      request-data ( Optional ):
            name: "John"
            surname: "Doe"
      expected-response:
               status: 200
               body:
                   name: "John"
                   surname: "Doe"
```

### 6.1.2 JSON
```
   "rest": {
       "request": {
           "method": "GET",
           "url": "https://example.com/hello"
       },
       "header": {
           "Accept": "application/json",
           "Content-Type": "application/json",
           "Authorization": "Bearer 1234567890",
           "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)"
       },
       
       "request-data": {
           "name": "John",
           "surname": "Doe"
       },
       
       "expected-response": {
           "status": 200,
           "body": {
               "name": "John",
               "surname": "Doe"
           }
       }
    }
```

### 6.1.3 XML
```
<rest>
    <request>
        <method>GET</method>
        <url>https://example.com/hello</url>
    </request>
    <header>
        <Accept>application/json</Accept>
        <Content-Type>application/json</Content-Type>
        <Authorization>Bearer 1234567890</Authorization>
        <User-Agent>Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)</User-Agent>
    </header>
    
    <request-data>
        <name>John</name>
        <surname>Doe</surname>
    </request-data>
    
    <expected-response>
        <status>200</status>
        <body>
            <name>John</name>
            <surname>Doe</surname>
        </body>
    </expected-response>
</rest>
```

## 6.2 SOAP
### 6.2.1 YAML
```
soap:
     request:
       url: https://example.com/soap-api
       soap-action: http://example.com/GetUserData
     headers:
       Content-Type: text/xml;charset=UTF-8
       Authorization: "Bearer 1234567890"
       User-Agent: "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)"
     body: |
       <?xml version="1.0" encoding="UTF-8"?>
       <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope">
         <soap:Body>
           <GetUserDataRequest xmlns="http://example.com/">
             <UserId>123456</UserId>
           </GetUserDataRequest>
         </soap:Body>
       </soap:Envelope>
     expected-response:
       status: 200
       headers:
         Content-Type: text/xml;charset=UTF-8
       body: |
         <?xml version="1.0" encoding="UTF-8"?>
         <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope">
           <soap:Body>
             <GetUserDataResponse xmlns="http://example.com/">
               <UserName>John Doe</UserName>
               <Email>john.doe@example.com</Email>
             </GetUserDataResponse>
           </soap:Body>
         </soap:Envelope>
```
### 6.2.2 JSON
```
{
  "soap": {
   "request": {
      "url": "https://example.com/soap-api",
      "soap-action": "http://example.com/GetUserData",
    },
    "headers": {
      "Content-Type": "text/xml;charset=UTF-8",
      "Authorization": "Bearer 1234567890",
      "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)"
    },
    "body": "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\"><soap:Body><GetUserDataRequest xmlns=\"http://example.com/\"><UserId>123456</UserId></GetUserDataRequest></soap:Body></soap:Envelope>",
    "expected-response": {
      "status": 200,
      "headers": {
        "Content-Type": "text/xml;charset=UTF-8"
      },
      "body": "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\"><soap:Body><GetUserDataResponse xmlns=\"http://example.com/\"><UserName>John Doe</UserName><Email>john.doe@example.com</Email></GetUserDataResponse></soap:Body></soap:Envelope>"
    }
  }
}

```
### 6.2.3 XML
```
<soap>
   <request>
     <url>https://example.com/soap-api</url>
     <soap-action>http://example.com/GetUserData</soap-action>
   </request>
  <headers>
    <Content-Type>text/xml;charset=UTF-8</Content-Type>
    <Authorization>Bearer 1234567890</Authorization>
    <User-Agent>Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)</User-Agent>
  </headers>
  <body><![CDATA[<?xml version="1.0" encoding="UTF-8"?><soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope"><soap:Body><GetUserDataRequest xmlns="http://example.com/"><UserId>123456</UserId></GetUserDataRequest></soap:Body></soap:Envelope>]]></body>
  <expected-response>
    <status>200</status>
    <headers>
      <Content-Type>text/xml;charset=UTF-8</Content-Type>
    </headers>
    <body><![CDATA[<?xml version="1.0" encoding="UTF-8"?><soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope"><soap:Body><GetUserDataResponse xmlns="http://example.com/"><UserName>John Doe</UserName><Email>john.doe@example.com</Email></GetUserDataResponse></soap:Body></soap:Envelope>]]></body>
  </expected-response>
</soap-request>
```


## 7. How to import pre-defined files ?
> For example, you have a ```request / header / request-data / excpected-response ``` file, with ```.json / .yaml / .xml``` file extension, which contains ```request``` field. <br/>
> You can import this file to your pickle file by using ```import``` field. <br/>
> ```import``` field should contain path to your file. <br/>

#### 7.1 YAML:
```
request:
    import: /path/to/request.yaml
header:
    import: /path/to/header.yaml
request-data:
    import: /path/to/request-data.yaml
expected-response:
    import: /path/to/expected-response.yaml
```

#### 7.2 JSON:
```
"request": {
    "import": "/path/to/request.json"
},
"header": {
    "import": "/path/to/header.json"
},
"request-data": {
    "import": "/path/to/request-data.json"
},
"expected-response": {
    "import": "/path/to/expected-response.json"
}
```

#### 7.3 XML:
```
<request>
    <import>/path/to/request.xml</import>
</request>
<header>
    <import>/path/to/header.xml</import>
</header>
<request-data>
    <import>/path/to/request-data.xml</import>
</request-data>
<expected-response>
    <import>/path/to/expected-response.xml</import>
</expected-response>
```

## 8. com.pickle.Test Results
> Results of your tests will be saved in ```/your/path/to/folder``` folder. <br/>
> pickle-folder will contain ```/pickle-reports/pickle-failed-report.yaml``` file which will contain all information about your failed tests. <br/>
> pickle-folder will contain ```/pickle-reports/pickle-success-report.json``` file which will contain all information about your success tests. <br/>

## 9. Maven Dependency
``` EMPTY ```