TCP Akka Streams Commons POC
==================================
Inception year: **2020**

## Overview
It's Commons crosscut library for akka streams TCP PoC.
Main classes:  
- **ConfigCommonConstants** contains default values and property names shared across tiers.
- **HoconConfigUtil** is set of utils simplifying working with Lightbend [HOCON](https://github.com/lightbend/config/blob/master/HOCON.md) config files.  
- **StringUtil** is set of String utils

## Build
To build the project, sbt 1.3.8+ is needed. Install sbt launcher and add setup dir in $PATH.
If you want to check cross compilation, use commands with '+' e.g. for compile:
```
sbt '+ compile'
```

## Test
For unit testing usual junit can be used:
```
sbt test
```

## Publish
In order to publish in local repo you can use:
```
sbt publishLocal
```
To publish into  [bintray repo](https://bintray.com/pragmarad-tech/tcom-scala-akka/tcom-crosscut-commons-akka), use:
```
sbt publish
```

## Release
In order to release new version use:
```
sbt release
```
## Usage
Main interesting util to use is **HoconConfigUtil**.

### Config loading variations
**HoconConfigUtil** reads config content from different sources. First, config content can be passed as a string (in HOCON format).
Then, (remaining) config values can be loaded from a file. Name/path of this file is selected with this sequence:
1. As an argument (e.g. **custom_tcom.conf**).
2. If the argument not found, the system tries loaded it from system property (for instance, if **-Dtcom.conf.file=other_tcom.conf** is set in command line, it will be **other_tcom.conf**).
3. If still not found, default name **application.tcom.conf** will be used to search in classpath.

## Logging
SLF4J with logback impl used. If you need to provide with custom log file (can be handy for different env-s), you can use 
system property in form of **-Dlogback.configurationFile=/path/to/custom_logback_config.xml**.

## Status
NOTE: better source of history is git itself, only basic  / major updates are mentioned here.
* 2020-03-07 - 0.0.1-SNAPSHOT - Init.
* 2020-03-20 - [0.0.1](https://bintray.com/pragmarad-tech/tcom-scala-akka/tcom-crosscut-commons-akka/0.0.1) - ConfigLoader and StringUtil first version.
* 2020-03-25 - [0.1.0] Args parser lib added, package name set to tcom for common naming conventions etc   
* 2020-03-25 - [0.1.1] Support of tcom.conf.file system property for config file added.  

# Roadmap
1. (DONE) Add common utils and constants to this module.
2. (DONE) Make things more configurable.
3. (DONE) Prepare for local deployments.
4. (DONE) Prepare for remote deployments (maven? bintray?).
