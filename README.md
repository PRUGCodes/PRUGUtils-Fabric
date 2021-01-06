# PRUGUtils-Fabric
A collection of fabric mc mod loader classes to make mod development a little easier!

## Standard fabric mod gradle building stuff:

To build:
```
gradle build
```    
## To add as a dependency to another mod:
#### 1. Add a version reference in gradle.properties
```Java Properties
# PRUGUtils
	prugutils_version=0.1.0
```
#### 2. Make reference to PRUGCodes Maven repo in build.gradle
```java
repositories {
    maven {
        url 'https://repo.repsy.io/mvn/hmorin/prugutils/'
    }
}
```
#### 3. Add PRUGUtils-Fabric as a dependency in build.gradle
```java
dependencies {

        //Note: There will be other dependencies here just add this one to those
	modImplementation "net.prugcodes.prugutils-fabric:PRUGUtils-Fabric:${project.prugutils_version}"

}
```
#### 4. Add PRUGUtils as a depend in fabric.mod.json
```json
"depends": {
    "fabricloader": ">=0.7.4",
    "fabric": "*",
    "minecraft": "1.16.x",
    "prugutils": "0.1.0"
}
  ```
