#!/bin/bash

script_dir="$(dirname "$(readlink -f "$0")")"

# Define the project directory and Docker directory
project_dir="$script_dir/../DemoApp/demo2" 
docker_dir="$script_dir/../DemoApp/docker/be"

cd "$project_dir"

mvn clean package

# Check if build was successful
if [ $? -eq 0 ]; then
    echo "Build successful. Proceeding to copy the jar file."
	
	 rm -f "$docker_dir"/*.jar
    echo "Old jar files deleted from Docker directory."

    jar_file=$(find target -type f -name "*.jar")
	
	new_file_name="my-java-app.jar"

	cp "$jar_file" "$docker_dir/$new_file_name"

    echo "New jar file has been copied and renamed in Docker directory."
else
    echo "Build failed. Jar file not copied."
fi
