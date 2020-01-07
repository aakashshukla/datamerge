# Data sorting and filtering

Read the 3 input files reports.json, reports.csv, reports.xml and output a combined CSV file with the following characteristics:

- The same column order and formatting as reports.csv
- All report records with packets-serviced equal to zero should be excluded
- records should be sorted by request-time in ascending order

Additionally, the application should print a summary showing the number of records in the output file associated with each service-guid.

Please provide source, documentation on how to run the program and an explanation on why you chose the tools/libraries used.

## Submission

You may fork this repo, commit your work and let us know of your project's location, or you may email us your project files in a zip file.

## Approach (Instructions)

The DataMerge application works to generate one CSV file by reading 3 files in different format (i.e CSV, JSON and XML). Each of these files have unique and different data stored in it. Filtering is done by sorting (request-time) field in ascending order and the records generated in the CSV file meet a condition where (packets-serviced) not equal to 0. It also generates a Summary file that prints records associated with each service-guid.

## Getting Started ##

* Check this project out into a directory in your Eclipse workspace
* Use File->Import->Existing Projects Into Workspace and follow the prompts to import the project
* If there exists any error related to package name or any imports, perform the below mentioned steps:

	* right click the project->Build Path->Configure Build Path
		- under 'Source' Tab, remove all the source folder
		- Then click on Add Folder and checkmark the 'java' and 'resources' checkboxs under src->main and also checkmark 'java' checkbox under src->test
		- click ok
		- click Apply->ok		
		
	* right click the project->Maven->Update
		- checkmark the 'Force Update of Snapshots/Releases' checkbox
		- select ok		
		
	* If the error still persists, restart eclipse	

### Prerequisites ###

* Eclipse Java EE IDE for Web Developers or any other IDE that supports Java and Maven

### Development And Testing ###

* Clone the project into a workspace folder on your machine and then checkout the branch you will be working on
* To compile:
	* highlight the project name under Package Explorer
	* select Run->Run As->Maven Build...
	* enter a goal of "compile" 
	* click Apply then Run
* To Run:
	* select Run->Run As->Java Application
	* OR run mvn clean install from command line. It will generate results.csv file on successful run.
* To Test:
	* select Run->Run As->JUnit Test
	* OR run mvn clean test from command line. It will run two test cases and it will generate results.csv file on successful run.

```
#bash
git clone git@github.com:aakashshukla/datamerge.git
git checkout <my-branch-name>
```

## Deployment ##

* To Create a Deployment Package (jar file):
	* select Run->Run As->Maven Build...
	* enter a goal of compile 
	* click Apply then Run
	* the jar file will be created under the target directory


## Built With ##

* Java 8
* Maven
* JUnit

## Authors ##

* created by Aakash Shukla
