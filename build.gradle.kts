val kotlin_version: String by project
val kotlinx_html_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.1.0"
    id("io.ktor.plugin") version "3.0.2"
}

group = "baranfit.ir"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers") }
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-thymeleaf-jvm")
    implementation("io.ktor:ktor-server-html-builder-jvm")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinx_html_version")
    implementation("org.jetbrains:kotlin-css-jvm:1.0.0-pre.129-kotlin-1.4.20")
    implementation("io.ktor:ktor-server-host-common-jvm")
    implementation("io.ktor:ktor-server-status-pages-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-config-yaml-jvm")
    testImplementation("io.ktor:ktor-server-test-host-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

//    implementation("org.xhtmlrenderer:flying-saucer-core:9.1.22")
//    implementation("org.xhtmlrenderer:flying-saucer-pdf-openpdf:9.1.22")

    // https://mvnrepository.com/artifact/com.openhtmltopdf/openhtmltopdf-pdfbox
    implementation("com.openhtmltopdf:openhtmltopdf-pdfbox:1.0.10")

}


// Custom deployment task
tasks.register("deployToServer") {
    group = "deployment"
    description = "Builds the Shadow JAR, installs JDK 21 on the server, and deploys the JAR."

    doLast {
        // Server details
        val serverHost = "your.server.ip.or.hostname"
        val serverUser = "us"
        val remotePath = "/home/us/app/"
        val jarFileName = "your-jar-name-1.0.0-all.jar"


        val installJdkCommand = """
    sudo apt update && \\
    sudo apt install -y wget apt-transport-https gpg && \\
    wget -qO - https://packages.adoptium.net/artifactory/api/gpg/key/public | gpg --dearmor | sudo tee /etc/apt/trusted.gpg.d/adoptium.gpg > /dev/null && \\
    echo "deb https://packages.adoptium.net/artifactory/deb \$(awk -F= '/^VERSION_CODENAME/{print\$2}' /etc/os-release) main" | sudo tee /etc/apt/sources.list.d/adoptium.list && \\
    sudo apt update && \\
    sudo apt install -y temurin-17-jdk && \\
    java -version
""".trimIndent()


        /*
                // Commands to be executed
                val installJdkCommand = """
                    sudo apt update && \
                    sudo apt install -y openjdk-21-jdk && \
                    sudo update-alternatives --config java && \
                    java -version
                """.trimIndent()
        */

        val buildShadowJarCommand = "./gradlew shadowJar"
        val copyJarToServerCommand = "scp build/libs/$jarFileName $serverUser@$serverHost:$remotePath"
        val createRunScriptCommand = """
            echo '#!/bin/bash' > $remotePath/run.sh
            echo 'java -jar $remotePath$jarFileName' >> $remotePath/run.sh
            chmod +x $remotePath/run.sh
        """.trimIndent()

        // Function to run commands
        fun runCommand(command: String): Boolean {
            val process = ProcessBuilder(*command.split(" ").toTypedArray()).redirectErrorStream(true).start()
            val exitCode = process.waitFor()
            process.inputStream.bufferedReader().use { reader ->
                reader.lines().forEach { println(it) }
            }
            return exitCode == 0
        }

        // Build the Shadow JAR locally
        println("Building Shadow JAR...")
        if (!runCommand(buildShadowJarCommand)) {
            println("Failed to build the JAR file.")
            return@doLast
        }

        // Install JDK 21 (Temurin) on the server
        println("Installing JDK 21 (Temurin) on the server...")
        if (!runCommand(installJdkCommand)) {
            println("Failed to install JDK on the server.")
            return@doLast
        }

        // Copy the JAR file to the server
        println("Copying the JAR file to the server...")
        if (!runCommand(copyJarToServerCommand)) {
            println("Failed to copy the JAR file to the server.")
            return@doLast
        }

        // Create a script to run the JAR on the server
        println("Creating the run script on the server...")
        if (!runCommand(createRunScriptCommand)) {
            println("Failed to create the run script.")
            return@doLast
        }

        println("Deployment complete. To run your application, SSH into the server and execute: $remotePath/run.sh")
    }
}
