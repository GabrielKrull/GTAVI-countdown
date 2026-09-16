#!/bin/sh
#
# Gradle wrapper script. Requires gradle/wrapper/gradle-wrapper.jar.
# If the jar is missing (fresh clone), generate it once with a local
# Gradle install:  gradle wrapper --gradle-version 8.9
# Or open the project in Android Studio, which offers to generate it.
#
APP_BASE_NAME=${0##*/}
APP_HOME=$(cd "${0%/*}" >/dev/null 2>&1 && pwd -P)

CLASSPATH="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"

if [ ! -f "$CLASSPATH" ]; then
  echo "gradle/wrapper/gradle-wrapper.jar not found." >&2
  echo "Generate it with: gradle wrapper --gradle-version 8.9" >&2
  echo "or open this project in Android Studio." >&2
  exit 1
fi

exec java -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
