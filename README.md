# Tizen Wearable SDK Gradle Plugin

Gradle wrapper for Tizen Wearable SDK build tools, tested with a Samsung Gear S. This is simple wrapper around the Java toolset in the SDK, tasks and options are basically those documented in [https://developer.tizen.org/dev-guide/2.2.1/org.tizen.web.appprogramming/html/ide_sdk_tools/command_line_interface.htm](https://developer.tizen.org/dev-guide/2.2.1/org.tizen.web.appprogramming/html/ide_sdk_tools/command_line_interface.htm)

You need to install the SDK first and go through the exquisitely painful certificate signing process with their Eclipse IDE. Remember this next time you want to buy a Samsung device.

Enable plugin:

    buildscript {
        repositories {
            maven {
                url "http://4thline.org/m2/"
            }
        }
        dependencies {
            classpath "org.fourthline:tizen-gradle-plugin:1.0"
        }
    }

    apply plugin: "tizen"

    tizen {
        sdkPath = "/Users/cb/work/tizen/tizen-wearable-sdk/"
    }

Show installed apps on default device:

    ./gradlew tizenQuery

Generate a new project:

    tizen {
        ...
        generate {
            name = "foo"
            path = "src"
        }
    }

Call `./gradlew tizenGenerate` once.

Configure the generated application identifier from `config.xml` and the build input path:

    tizen {
        ...
        applicationId = "DuQY8drTGI.foo"

        build {
            input = "src/foo"
        }
    }

Build, re-install and run app:

    ./gradlew clean tizenPackaging tizenInstall tizenRun

Enable debugging console:

    ./gradlew tizenDebug

See `org.fourthline.tizen.gradle.Tizen` for all available settings.
