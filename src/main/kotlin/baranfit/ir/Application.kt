package baranfit.ir

import baranfit.ir.Baran.baranApp
import baranfit.ir.Baran.baranAppCss
import baranfit.ir.tmp.configureRouting
import baranfit.ir.tmp.configureTemplating
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureTemplating()
    configureRouting()
    baranApp()
    baranAppCss()

}
