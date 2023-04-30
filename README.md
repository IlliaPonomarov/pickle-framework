# The Pickle

## Table of Contents
1. [Pickle Philosophy](#1-pickle-philosophy)
2. [Pickle Installation](#2-pickle-installation)
    1. [JAR files](#21-jar-files)
    2. [Docker](#22-docker)

3. [Pickle TechStack](#3-pickle-techstack)
4. [How to test endpoints ?](#4-how-to-test-endpoints-)
5. [Pickle file structure](#5-pickle-file-structure)
    1. [YAML](#51-yaml)
    2. [JSON](#52-json)
    3. [XML](#53-xml)
6. [How to import pre-defined files ?](#6-how-to-import-pre-defined-files-)

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

> Windows: <br/>
> 3.1 With pickle-test.yaml```pickle.bat -t /path/to/test/pickle-test.yaml -r /path/to/result/folder``` <br/>
> 3.2 Without pickle-test.yaml  <br/>```pickle.bat --http-request --method GET/get --url http://example.com/ --header --accept application/json --content-type application/json  --authorizartion "Bearer 1234" --user-agent "Mozilla/5.0" --request-data /path/to/request-data.yaml --expected-response --status 200 --body /path/to/excpected-body.yaml --result /path/to/result/folder -r /path/to/result/folder``` <br/>

> Linux: <br/>
> 3.1 With pickle-test.yaml```pickle.sh -t /path/to/test/pickle-test.yaml -r /path/to/result/folder``` <br/>
> 3.2 Without pickle-test.yaml <br/>```pickle.sh --http-request --method GET/get --url http://example.com/ --header --accept application/json --content-type application/json  --authorizartion "Bearer 1234" --user-agent "Mozilla/5.0" --request-data /path/to/request-data.yaml --expected-response --status 200 --body /path/to/excpected-body.yaml --result /path/to/result/folder -r /path/to/result/folder``` <br/>


## 4. Pickle TechStack
> 1. Java 11
> 2. Kotlin 1.5
> 3. Maven 3.6.3
> 4. JUnit 5
> 5. Spring Core 5.3.8

## 5. How to test endpoints ?
> 1. Create a new folder which will contain your test files. <br/>
> 2. Create a new ```pickle-test``` file with the extension ``` .json / .yaml / .xml ``` <br/>
> 3. Your pickle file should be contain ```http-request, header, request-data,  excpected-response``` fields. <br/>
>> 3.1. Of course , you can write path to already existing file which contains field data. For example you can have ```http-request.json``` file
>> which contain already defined elements. <br/> Notes: ```http-request``` and another pre-defined files should have equal extension as a pickle file <br/>
> 4. When you run the Pickle, you should specify the path to the pickle file and the path to the folder where the result will be saved. <br/>
> 5. After the test is completed, you will see the result in the specified folder. pickle-success-report.yaml and pickle-failed-report.yaml will be created in your pre-defined path. <br/>

## 6. Pickle file structure
### 6.1 YAML
```
   http-request:
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

### 6.2 JSON
```
    "http-request": {
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
```

### 6.3 XML
```
<pickle>
    <http-request>
        <method>GET</method>
        <url>https://example.com/hello</url>
    </http-request>
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
</pickle>
```

## 7. How to import pre-defined files ?
> For example, you have a ```http-request / header / request-data / excpected-response ``` file, with ```.jsom / .yaml / .xml``` file extension, which contains ```http-request``` field. <br/>
> You can import this file to your pickle file by using ```import``` field. <br/>
> ```import``` field should contain path to your file. <br/>

#### 7.1 YAML:
```
http_request:
    import: /path/to/http-request.yaml
header:
    import: /path/to/header.yaml
request-data:
    import: /path/to/request-data.yaml
expected-response:
    import: /path/to/expected-response.yaml
```

#### 7.2 JSON:
```
"http-request": {
    "import": "/path/to/http-request.json"
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
<http-request>
    <import>/path/to/http-request.xml</import>
</http-request>
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

## 8. Test Results
> Results of your tests will be saved in ```/your/path/to/folder``` folder. <br/>
> pickle-folder will contain ```/pickle-reports/pickle-failed-report.yaml``` file which will contain all information about your failed tests. <br/>
> pickle-folder will contain ```/pickle-reports/pickle-success-report.json``` file which will contain all information about your success tests. <br/>

## 9. Maven pom.xml
``` EMPTY ```