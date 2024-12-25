/*
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.concurrent.TimeUnit

// Server details
val serverHost = "your.server.ip.or.hostname"
val serverUser = "us"
val remotePath = "/home/us/app/"
val jarFileName = "your-jar-name-1.0.0-all.jar"

// Install OpenJDK 21 (Temurin) on the server
val installJdkCommand = """
    sudo apt update && \
    sudo apt install -y openjdk-21-jdk && \
    sudo update-alternatives --config java && \
    java -version
""".trimIndent()

// Build Shadow JAR locally
val buildShadowJarCommand = "gradle shadowJar"

// Deploy the JAR file
val copyJarToServerCommand = "scp build/libs/$jarFileName $serverUser@$serverHost:$remotePath"

val createRunScriptCommand = """
    echo '#!/bin/bash' > $remotePath/run.sh
    echo 'java -jar $remotePath$jarFileName' >> $remotePath/run.sh
    chmod +x $remotePath/run.sh
""".trimIndent()

// Execute the commands

fun runCommand(command: String): Boolean {
    val process = ProcessBuilder(*command.split(" ").toTypedArray())
        .redirectErrorStream(true)
        .start()
    val exitCode = process.waitFor(30, TimeUnit.SECONDS)
    process.inputStream.bufferedReader().use { reader ->
        reader.lines().forEach { println(it) }
    }
    return exitCode
}

// 1. Build the Shadow JAR locally
println("Building Shadow JAR...")
if (!runCommand(buildShadowJarCommand)) {
    println("Failed to build the JAR file.")
    return
}

// 2. Install JDK on the server
println("Installing JDK 21 (Temurin) on the server...")
if (!runCommand(installJdkCommand)) {
    println("Failed to install JDK on the server.")
    return
}

// 3. Copy the JAR file to the server
println("Copying the JAR file to the server...")
if (!runCommand(copyJarToServerCommand)) {
    println("Failed to copy the JAR file to the server.")
    return
}

// 4. Create a script to run the JAR on the server
println("Creating the run script on the server...")
if (!runCommand(createRunScriptCommand)) {
    println("Failed to create the run script.")
    return
}

println("Deployment complete. To run your application, SSH into the server and execute: $remotePath/run.sh")
*/
