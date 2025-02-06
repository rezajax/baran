package baranfit.ir

import baranfit.ir.baran.baranApp
import baranfit.ir.baran.baranApp2
import baranfit.ir.baran.baranApp3
import baranfit.ir.baran.baranAppCss
import baranfit.ir.tmp.configureRouting
import baranfit.ir.tmp.configureTemplating
import io.ktor.network.tls.certificates.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory
import java.io.File

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
/*    embeddedServer(Netty, applicationEnvironment { log = LoggerFactory.getLogger("ktor.application") }, {
        envConfig()
    }, module = Application::module).start(wait = true)*/
}

fun Application.module() {
    configureTemplating()
    configureRouting()
    baranApp()
    baranApp2()
    baranApp3()

    baranAppCss()

/*    routing {
        get("/ss") {
            call.respondText("Hello, world!")
        }
    }*/
}




/*
private fun ApplicationEngine.Configuration.envConfig() {

    val keyStoreFile = File("build/keystore.jks")
    val keyStore = buildKeyStore {
        certificate("sampleAlias") {
            password = "foobar"
            domains = listOf("127.0.0.1", "0.0.0.0", "localhost")
        }
    }
    keyStore.saveToFile(keyStoreFile, "123456")

    connector {
        port = 8080
    }
    sslConnector(
        keyStore = keyStore,
        keyAlias = "sampleAlias",
        keyStorePassword = { "123456".toCharArray() },
        privateKeyPassword = { "foobar".toCharArray() }) {
        port = 8443
        keyStorePath = keyStoreFile
    }
}

*/
