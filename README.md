# Notion Social Media Post Automation
A tool that reads a scheduled event in your Notion.so and post it on your social media.

## Run

``` 
java -jar 
    -DnotionBearerToken=<TOKEN>
    -DnotionShareDatabaseId=<ID> 
    -DnotionApiUrl=https://api.notion.com
    file.jar 
```

## Make Script

On the root folder you can find the `MakeFile` with shortcuts to the main commands as following.

- `make build` Build the application in a fast way without tests or addition validation checks.
- `make check` Build the application completely running tests and additional validation checks.
- `make run` Execute build without tests and run the application with local-container profile.
- `make test` Run the test in multiple threads.
- `make sonar-up` Create the container with the sonarqube.
- `make sonar-down` Destroy the container with the sonarqube.
- `make sonar` Start the container with the sonarqube and build the application to collect metrics. After build the application access the Security report at `target/dependency-check-report.html` and the sonarqube running locally [here](http://127.0.0.1:9000/projects?sort=-analysis_date). The sonnarqube user is `admin`and password is `admin`.