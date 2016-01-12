CXF Hibernate XA: demonstrates RESTful web services with CXF and Hibernate Persistence with XA Transactions
===========================================================================================================
Author: Matt Robson 

Technologies: Fuse, OSGi, CXF, Hibernate, Blueprint, Karaf Features, Fuse BOM, Aries Auto Enlist XA Transactions, Swagger, JSON, ExceptionMapper 

Product: Fuse 6.2.1

Breakdown
---------
This code example shows how to leverage RESTful (JAX-RS) web services using CXF and persist Entities using Hibernate. It demonstrates how to wire an EntityManager to a JPA Context and on to a Service. It also makes use of Aries Auto Enlistment for XA Transactions.  Other interesting aspects include the use of the Fuse BOM, Karaf Features, Blueprint Placeholders, Swagger and  ExceptionMapper.

For more information see:

* <https://access.redhat.com/site/documentation/JBoss_Fuse/> for more information about using JBoss Fuse

System Requirements
-------------------
Before building and running this quick start you need:

* Maven 3.2 or higher
* Java 1.7 or 1.8
* JBoss Fuse 6.2.1

Build and Deploy
----------------

1) clone this project

	git clone https://github.com/mrobson/fuse-cxf-hibernate-xa.git

2) change to project directory 

	cd fuse-cxf-hibernate-xa

3) update your username and password

	vi xa-datasource/src/main/resources/OSGI-INF/blueprint/datasource.xml
	<cm:property name="datasource.username" value="username" />
	<cm:property name="datasource.password" value="password" />

4) build

	mvn clean install

5) start JBoss Fuse 6.2.1

	./fuse or ./start

6) start Oracle database (refer to vendor documentation if you need to do this, for easy testing I recommend using Oracle XE)

7) deploy Oracle JDBC driver

Download the latest driver from Oracle and install it to your local maven repository (account required):

	mvn install:install-file -Dfile=ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=12.1.0.1 -Dpackaging=jar

8) add the features file

	features:addurl mvn:org.mrobson.example.hibernatetx/features/1.0-SNAPSHOT/xml/features

9) install

	features:install hibernate-jpa-example

10) verify

	osgi:list
	[ 329] [Active     ] [Created     ] [       ] [   90] hibernatetx :: XA-Datasource (1.0.0.SNAPSHOT)
	[ 330] [Active     ] [            ] [       ] [   95] hibernatetx :: Datamodel (1.0.0.SNAPSHOT)
	[ 331] [Active     ] [Created     ] [       ] [  100] hibernatetx :: CXF Hibernate (1.0.0.SNAPSHOT)

Usage
-----

You can verify your CXF web services are available at:

	http://localhost:8181/cxf

With a correct deployment, this example has an endpoint address of:

	http://localhost:8181/cxf/hibernatetx/

To view the WADL, you can use the following address:

	http://localhost:8181/cxf/hibernatetx/?_wadl

As a quick test, you can use the GET operations directly from the browser:

	http://localhost:8181/cxf/hibernatetx/person/findById/1

Run a Test
----------

1) change to cxfhibernate directory

	cd fuse-cxf-hibernate-xa/cxfhibernate

2) run included test which persists a person and then looks up that person

	mvn -Dtest=PersonTest test

Note: reference json file can be found at: cxfhibernate/src/test/resources/person.json

Make it Fail
------------

To test transaction rollback (and see the ExceptionMapper in action) , you can make a request fail.

From fuse-cxf-hibernate-xa/cxfhibernate:

	vi src/test/resources/person.json

Delete the "firstName" value to make it blank/null:

	{"id":null,"version":null,"firstName":"","lastName":"R","country":"Canada","addresses":[{"id":null,"version":null,"city":"Toronto"}]}

You will see a HTTP 400 returned along with a detailed description of the issue:

	Response-Code: 400
	Content-Type: application/json
	Headers: {Content-Type=[application/json], Date=[Thu, 30 Apr 2015 21:40:04 GMT]}
	Payload: Could not save person: [FirstName= LastName=R Country=Canada Addresses=[[City=Toronto]]] Cause: javax.transaction.RollbackException: Unable to commit: transaction marked for rollback Error: SQLIntegrityConstraintViolationException: ORA-01400: cannot insert NULL into ("PRODUCTCONFIG"."PERSON"."FIRSTNAME")

Remove the Services
-------------------

To remove the bundle we installed, you can simply uninstall the feature:

1) uninstall the feature

	features:uninstall hibernate-jpa-example

Fabric Install
--------------

To install this example into a fabric environment, ensure the build code is in a maven repository accessible by your fabric:

1) Create a new profile with karaf as the parent

	profile-create --parents karaf --version 77.02 hibernate-cxfxa

2) Add the feature repositories to the profile

	profile-edit --repositories mvn:org.mrobson.example.hibernatetx/features/1.0-SNAPSHOT/xml/features hibernate-cxfxa 77.02
	profile-edit --repositories mvn:org.jboss.fuse/jboss-fuse/6.1.0.redhat-379/xml/features hibernate-cxfxa 77.02

3) Add the feature to the profile
	
	profile-edit --features hibernate-jpa-example hibernate-cxfxa 77.02

4) Add the JDBC Driver Bundle to the profile

	profile-edit --bundles wrap:mvn:com.oracle/ojdbc6/12.1.0.1 hibernate-cxfxa 77.02

5) Assign to profile to a container

	container-add-profile fuse-services1 hibernate-cxfxa

6) Verify deployment

	JBossFuse:admin@root> container-list 
	[id]                           [version] [connected] [profiles]                                         [provision status]
	fuse-services2                 77.02     true        default, hibernate-cxfxa                           success

	Fabric8:admin@fuse-services2> osgi:list
	[1942] [Active     ] [Created     ] [   60] hibernatetx :: CXF Hibernate (1.0.0.SNAPSHOT)
	[1943] [Active     ] [Created     ] [   60] hibernatetx :: Datamodel (1.0.0.SNAPSHOT)
	[1944] [Active     ] [Created     ] [   60] hibernatetx :: XA-Datasource (1.0.0.SNAPSHOT)

7) Done!
