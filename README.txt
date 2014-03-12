BodyStyles Vaadin UI Extension Add On

Server side API to modify body element style names

Demo application: http://siika.fi:8080/BodyStylesDemo/
Source code: https://github.com/alump/BodyStyles
Vaadin Directory: https://vaadin.com/directory#addon/bodystyles
License: Apache License 2.0

This project can be imported to Eclipse with m2e.

Simple Maven tutorials:


***** How to compile add on jar package for your project *****

> cd bodystyles-addon
> mvn package

add on can be found at: picker-addon/target/BodyStyles-<version>.jar
zip package used at Vaadin directory can be found at:
picker-addon/target/BodyStyles-<version>.zip

***** How to install BodyStyles to your Maven repository *****

To install addon to your local repository, run:

> cd bodystyles-addon
> mvn install


***** How to run test application *****

First compile and install addon (if not already installed)
> cd bodystyles-addon
> mvn install

Then compile demo widgetset and start HTTP server
> cd ../bodystyles-demo
> mvn vaadin:compile
> mvn jetty:run

Demo application is running at http://localhost:8080/bodystyles



***** How to compile test application WAR *****

First compile and install addon (if not already installed)
> cd bodystyles-addon
> mvn install

Then construct demo package (this should automatically compile widgetset)
> cd ../bodystyles-demo
> mvn package

War package can be now found at bodystyles-demo/target/BodyStylesDemo.war
