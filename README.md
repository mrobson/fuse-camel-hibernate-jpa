Camel Hibernate JPA: demonstrates Camel JPA and Hibernate Persistence
=====================================================================
Author: Matt Robson 

Technologies: Fuse, OSGi, Camel, Hibernate, Blueprint, Karaf Features, Fuse BOM, Aries Auto Enlist XA Transactions

Product: Fuse 6.2.1

Breakdown
---------
This code example shows how to leverage Camel JPA and persist Entities using Hibernate. It demonstrates how to wire an EntityManager to a JPA Context and on to a Camel JPA component. It also makes use of Aries Auto Enlistment for XA Transactions.  Other interesting aspects include the use of the Fuse BOM, Karaf Features and Blueprint Placeholders.

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

	git clone https://github.com/mrobson/fuse-camel-hibernate-jpa.git

2) change to project directory 

	cd fuse-camel-hibernate-jpa

3) update your username and password

	vi camel-hibernate-xa-datasource/src/main/resources/OSGI-INF/blueprint/datasource.xml
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

	features:install hibernate-camel-jpa-example

10) verify

	osgi:list
	[ 353] [Active     ] [Created     ] [       ] [   90] camel-hibernate-jpa :: XA-Datasource (1.0.0.SNAPSHOT)
	[ 354] [Active     ] [Created     ] [       ] [   95] camel-hibernate-jpa :: Datamodel (1.0.0.SNAPSHOT)
	[ 355] [Active     ] [Created     ] [       ] [  100] camel-hibernate-jpa :: Camel JPA Hibernate (1.0.0.SNAPSHOT)

Usage
-----

Copy the example person XML from the project to the processing directory where Camel will pick it up, unmarshall and persist the person to the DB

	cp camel-hibernate-route/src/test/resources/person.xml $GUSE_HOME/data/camel/

Create a new file (with a new name) and copy it in, Camel will pick up any file placed into that directory

Remove the Services
-------------------

To remove the bundle we installed, you can simply uninstall the feature:

1) uninstall the feature

	features:uninstall hibernate-camel-jpa-example

Done!
