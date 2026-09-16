@rem Gradle wrapper script. Requires gradle\wrapper\gradle-wrapper.jar.
@rem If the jar is missing (fresh clone), generate it once with a local
@rem Gradle install:  gradle wrapper --gradle-version 8.9
@rem Or open the project in Android Studio, which offers to generate it.
@echo off
set APP_HOME=%~dp0
set WRAPPER_JAR=%APP_HOME%gradle\wrapper\gradle-wrapper.jar
if not exist "%WRAPPER_JAR%" (
  echo gradle\wrapper\gradle-wrapper.jar not found.
  echo Generate it with: gradle wrapper --gradle-version 8.9
  echo or open this project in Android Studio.
  exit /b 1
)
java -classpath "%WRAPPER_JAR%" org.gradle.wrapper.GradleWrapperMain %*
