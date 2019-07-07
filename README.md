# Zopa Technical Task

This application has been written to resolve a recruitment task for Software Developer position at Zopa.

Description of the exercise can be found in docs/ folder with a filename "***Zopa DevTechnicalTest .pdf***".

Corresponding data set can be found in docs/ folder with file name "***MarketDataforExercise-csv.csv***".

The outcome of the exercise is a Command Line (CLI) Application that can be run on any system that fulfills the requirements.

# Pre-requisites

Software installed and added to a class path (as a system variable):
 1. Java 11
 2. Maven

In addition, to run the application you will need any console/terminal of your choice, or your favorite Java IDE.

## Building

In order to build an executable JAR file, please do the following:

 1. Open your terminal and navigate to the folder with the source code.
 2. Run `mvn package -DskipTests`

Apache Maven will create a target/ folder with the executable jar called: **zopa-technical-test-{version-SNAPSHOT}-jar-with-dependencies.jar**.

## Executing

To run the JAR file please locate the executable JAR and pass two additional arguments - location of the CSV file with the data set and the amount that you would like to get a quote for.

Format:

    java -jar location-of-executable-jar.jar location-of-csv-file amount
Example (with absolute path as a parameter):

    java -jar target/zopa-technical-test-1.0-SNAPSHOT-jar-with-dependencies.jar C:/Users/User/Downloads/MarketDataforExercise-csv.csv 1000
In the above example, application is being run from the app root folder, pointing to a data set file in Downloads/ folder.

Example (with relative path as a parameter):

    java -jar zopa-technical-test-1.0-SNAPSHOT-jar-with-dependencies.jar MarketDataforExercise-csv.csv 1000
In the above example application is being run from the same location where data set file exists.

## Data set format
Data set that is being provided, has to be a CSV file with a header (containing Lender, Rate and Available columns), as well as the actual data.

Example data set:
> Lender,Rate,Available
> 
> Bob,0.075,640
> 
> Jane,0.069,480

## Outcome
If all parameters are valid, application should print quote in the following format:

> Requested amount: £{amount}
> 
> Annual Interest Rate: {rate}%
> 
> Monthly repayment: £{monthlyRepayment}
> 
> Total repayment: £{totalRepayment}

For instance, running application with data set provided in /docs folder and 1000 as a requested amount will print the following:

> Requested amount: £1000.00
> 
> Annual Interest Rate: 7.0%
> 
> Monthly repayment: £30.78
> 
> Total repayment: £1108.10

### Validation
If provided parameters will be invalid application will print an exception.
1. File URL that is passed as an argument has to exist.
2. Amount provided as a CLI parameter has to be multiplicity of £100
3. Amount provided as a CLI parameter has to be between £1000 and £15000
4. Amount provided as a CLI parameter has to be available (i.e. sum of available amounts in CSV has to be equal or less than amount requested).
5. Rate provided in CSV file has to be positive.

## Testing
In order to run unit & integration tests, please execute the Maven test command:

    mvn test
