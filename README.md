Maggie
======

Maggie the little sister of lisa, acts like a memory cache for FsXML elements. Using the Mojo talks to 
lisa, smithers to provide getting/searching for data-elements. Currently still hardcoded for the euscreen
project and mostly a placeholder.

1) Check out Maggie in Eclipse
2) Adjust the config in GlobalConfig class
3) Build a war using the 'deploy-war' task with the provided build.xml
4) Deploy the war on a Tomcat server

After these steps you can access your videos like this:

http://[yourhost]:[yourport]/edna/domain/[image url]

This will give you a image and cache it based on the input params (see the manual)
