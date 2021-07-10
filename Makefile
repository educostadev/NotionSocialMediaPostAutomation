MAVEN_PROJECT_VERSION    := $(shell mvn org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.version -q -DforceStdout | grep -v '\[' )
MAVEN_PROJECT_ARTIFACTID := $(shell mvn org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.artifactId -q -DforceStdout | grep -v '\[' )
JAR_FILE_NAME := $(MAVEN_PROJECT_ARTIFACTID)-$(MAVEN_PROJECT_VERSION)

# build the application without tests and other checks
build:
	@echo Building $(MAVEN_PROJECT_ARTIFACTID) Version $(MAVEN_PROJECT_VERSION)
	mvn clean install -DskipTests

# build the full application running all test and checks
check:
	mvn clean install

# execute build without tests and run the application with local-container profile
run: build
	java -jar target/$(JAR_FILE_NAME).jar

# Running tests in 4 threads
test:
	mvn -T 4 surefire:test

# start the container with the sonarqube
sonar-up:
	docker-compose -f docker-compose.sonar.yml up --build -d

# destroy the container with the sonarqube
sonar-down:
	docker-compose -f docker-compose.sonar.yml down

# Collect quality metrics and publish to sonar. Check the file target/dependency-check-report.htm for vulnerabilities warnings.
sonar: sonar-up
	mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent -f pom.xml package sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.projectKey=$(MAVEN_PROJECT_ARTIFACTID) -Dsonar.projectName=$(MAVEN_PROJECT_ARTIFACTID)
