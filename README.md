# jcs-exercise
[![Build Status](https://travis-ci.org/matt-marman/jcs-exercise.svg?branch=main)](https://travis-ci.org/matt-marman/jcs-exercise)

### How to generate jacoco.exec
>mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent package

### How to convert jacoco.exec in report

#### Before create the direcotry as showed below
>mkdir -p target/jacoco-gen/jcs-coverage/

>java -jar ../jacoco-0.8.5/lib/jacococli.jar report target/jacoco.exec --classfiles ./jcs-1.3.jar --sourcefiles ./src/ --html ./target/jacoco-gen/jcs-coverage/ --xml ./target/jacoco-gen/jcs-coverage/file.xml --csv ./target/jacoco-gen/jcs-coverage/file.csv

