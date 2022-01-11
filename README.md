# Notion Social Media Post Automation
A Tools that read a Notion Social Media database like [this template](https://educostadev.notion.site/a62980e346124c04b662b02d85f3c0b1?v=05f50fac3e564b66ac202ada5aba94f0) and share the scheduled post on Linkedin and Twitter. 

## Run

``` 
java -jar \
    -DnotionBearerToken=<VALUE> \
    -DnotionShareDatabaseId=<ID> \
    -DnotionApiUrl=https://api.notion.com \
    -DlinkedinUserId=<VALUE> \
    -DlinkedinAPIKey=<VALUE> \
    -DlinkedinAPISecret=<VALUE> \
    -DlinkedinCallbackURL=<URL> \
    -DlinkedinAccessToken=<VALUE> \
    -DtwitterApiKey=<VALUE> \
    -DtwitterApiSecretKey=<VALUE> \
    -DtwitterAccessToken=<VALUE> \
    -DtwitterAccessTokenSecret=<VALUE> \
    -Dinterval=<MINUTES> \
    -DshouldPostOnLinkedin=<FALSE|TRUE> \
    -DshouldPostOnTwitter=<FALSE|TRUE> \
    -DrequestLinkedinTokenOnly=<FALSE|TRUE> \
    file.jar 
```

## Parameters  

### For the Notion
- `-DnotionBearerToken=<VALUE>` The notion bearer token. Something like: secret_XXXX1or2ufXXXcWCxxxx0x46FeMXJVoQC9xxxxRY5
- `-DnotionShareDatabaseId=<VALUE>` The Shared database id from Notion. Something like: XXxxXXxa977b4deXXXXd7edcd62577xx 
- `-DnotionApiUrl=https://api.notion.com` The URL for Notion API. 


- See [here](https://developers.notion.com/docs) how to get start with notion API and get your credentials.
- See [here](https://www.postman.com/notionhq/) the postman collection for notion.


### For the Linkedin
- `-DlinkedinUserId=<VALUE>` The user id. Use the `-DrequestLinkedinTokenOnly` parameter to discover your user id. Something like: Xxx1OXXGGg
- `-DlinkedinAPIKey=<VALUE>` The API key from the developer portal. Something like: 123xx2fy0x9e99
- `-DlinkedinAPISecret=<VALUE>` The API Secret from the developer portal. Something like: xx0SEXXY7M4Xxx9U
- `-DlinkedinCallbackURL=<URL>` The call back URL you specified in the developer portal. 
- `-DlinkedinAccessToken=<VALUE>` The access token (code) you get after the authorization. Something like XXXxxXX788xx1223-aM3gKIdhM7r1xLKCFKvxDBiZD5te3BJKrUmAlD2_XXXxx123sssxXX7X_hnYoxqlYbKWYtH5_UEyXlA0kpvtI7MpG3LDGuzYp6RY6xMEAKdxTMwG4faGyEv_AQljyf8bLAhnVGhxp2vtj4Q8COSE5em70O5sKVvUeO7Qyq8P5Fvy4VY2dNIizrD6sXjJyDhuuba9a0OPSEaGr_eq98LCvICUq4W-EEHZXjZqc_D01rvMRCictKPZN4xWZ9XKrBrUq_JIOaAVizmy9RHT56x8NZG0kooNXVltO2Ax8-ZSp1-Tbv9R5ll1qx6MSjxA8_IdRaw4i1mhc_XXX


- See [here](https://www.jcchouinard.com/get-your-oauth-credentials-for-linkedin-api/) how to get the linkedin credentials.
- See [here](https://github.com/scribejava/scribejava) The lib used to get the access token.
- See [here](https://github.com/ebx/ebx-linkedin-sdk) The lib used to post on linkedin.
- See [here](https://docs.microsoft.com/en-us/linkedin/marketing/integrations/community-management/shares/share-api?tabs=http) The api documentation. 

### For the Twitter
- `-DtwitterApiKey=<VALUE>` The API key from the developer portal. Something like: xXr123HyVEXXZhr3DUoLvTxxx
- `-DtwitterApiSecretKey=<VALUE>` The API Secret from the developer portal. Something like: XXuGx123xFi7mMWGn3NhxxxOz4suyWE9my1cBfkHIHlns6xxxx
- `-DtwitterAccessToken=<VALUE>` Something like: 1234567892065214466-xXXXvBJl6uB97Pf3zJl5Q999lH71xx
- `-DtwitterAccessTokenSecret=<VALUE>` The access token from the developer portal. Something like: xXx132BBk4NcXiEq617pTn55btSFRO2si6dAs6detXXxx


- See [here](https://developer.twitter.com/en/docs/tutorials/step-by-step-guide-to-making-your-first-request-to-the-twitter-api-v2) how to get you credentials.
- See [here](https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/post-statuses-update) the API.

### For the Application 
- `-Dinterval=<MINUTES>` Amount of minutes to be added to the current time to check if pages from Notion is between NOW and NOW + INTERVAL. If not specified the default value is 5 minutes.
- `-DshouldPostOnLinkedin=<FALSE|TRUE>` Turn off the last step to post on Linkedin. Default value is TRUE when not specified.
- `-DshouldPostOnTwitter=<FALSE|TRUE>` Turn off the last step to post on Linkedin. Default value is TRUE when not specified.
- `requestLinkedinTokenOnly` This is an application ARGS parameter." Run the application only to request the linkedin token. Give you the URL for authorization, you should use the browser for complete the process.

## Make Script

On the root folder you can find the `MakeFile` with shortcuts to the main commands as following.

- `make build` Build the application in a fast way without tests or addition validation checks.
- `make check` Build the application completely running tests and additional validation checks.
- `make run` Execute build without tests and run the application with local-container profile.
- `make test` Run the test in multiple threads.
- `make sonar-up` Create the container with the sonarqube.
- `make sonar-down` Destroy the container with the sonarqube.
- `make sonar` Start the container with the sonarqube and build the application to collect metrics. After build the application access the Security report at `target/dependency-check-report.html` and the sonarqube running locally [here](http://127.0.0.1:9000/projects?sort=-analysis_date). The sonnarqube user is `admin`and password is `admin`.