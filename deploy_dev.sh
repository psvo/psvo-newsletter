#!/bin/bash -e

./gradlew build
version=$(date +%s)
war="newsletter##$(date +%s).war"
echo -n "Deploying $war..."
sudo cp build/libs/psvo-newsletter-0.1.0.war "/var/lib/tomcat7/webapps/$war"
echo "DONE."
