run: build
	java -jar ./target/my-application-1.0-SNAPSHOT.jar /workspaces/spdx-reader-test-1/src/main/resources/spdx.json

build:
	mvn clean package