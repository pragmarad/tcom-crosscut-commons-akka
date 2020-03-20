TCP Akka Streams Commons POC
==================================
Inception year: **2020**

## Status
* 2020-03-07 - 0.0.1-SNAPSHOT - Init.

## Overview
It's Commons crosscut for akka streams TCP PoC. 

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

## Logging
SLF4J with logback impl used.

# Roadmap
1. Add common utils and constants to this module.
2. Make things more configurable.
3. Prepare for local deployments.
4. Prepare for remote deployments (maven? bintray?).