package baranfit.ir.Baran

import baranfit.ir.data.WorkoutPlan
import baranfit.ir.data.workoutPlan
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import java.io.ByteArrayOutputStream
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder


fun Application.baranApp() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        get("/") {
            call.respondHtml {
                baranHead()
                body {
                    div(classes = "container-wrapper") {
                        div(classes = "container") {
                            workoutContent(workoutPlan)
                        }
                    }
                }
            }

        }

        get("/pdf") {
            // Generate HTML using Ktor's HTML DSL
            val htmlContent = buildString {
                appendHTML().html {
                    head {
//                        meta(charset = "UTF-8")
//                        meta(name = "viewport", content = "width=device-width, initial-scale=1.0")
                        title("برنامه تمرینی")
//                        link(
//                            href = "https://fonts.googleapis.com/css2?family=Vazirmatn:wght@400;500;700&display=swap",
//                            rel = "stylesheet"
//                        )
        styleLink("/baran.css")  // If you want to externalize the styles
                    }

                    body {
                        div(classes = "container-wrapper") {
                            div(classes = "container") {
                                h1 { +"hi" }
                            }
                        }
                    }
                }
            }

            // Convert HTML to PDF using Flying Saucer
            val pdfBytes = htmlToPdf(htmlContent)

            // Send the PDF as a response
            call.response.header(HttpHeaders.ContentDisposition, "attachment; filename=example.pdf")
            call.respondBytes(pdfBytes, ContentType.Application.Pdf)
        }

    }


}

fun htmlToPdf(htmlContent: String): ByteArray {
    val outputStream = ByteArrayOutputStream()
    val builder = PdfRendererBuilder()

    builder.useFastMode()
    builder.withHtmlContent(htmlContent, null) // Pass HTML content
    builder.toStream(outputStream) // Set output stream
    builder.run() // Generate PDF

    return outputStream.toByteArray()
}


private fun HTML.baranHead() {
    head {
        meta(charset = "UTF-8")
        meta(name = "viewport", content = "width=device-width, initial-scale=1.0")
        title("برنامه تمرینی")
        link(
            href = "https://fonts.googleapis.com/css2?family=Vazirmatn:wght@400;500;700&display=swap",
            rel = "stylesheet"
        )
        styleLink("/baran.css")  // If you want to externalize the styles
    }
}


// Generates the content of the workout plan
fun DIV.workoutContent(workoutPlan: WorkoutPlan) {
    h1 { +"برنامه تمرینی" }
    h2 {
        +"مربی: "
        span(classes = "coach-name") { +workoutPlan.coachName }
    }

    div(classes = "info") {
        p {
            strong { +"نام ورزشکار:" }
            span(classes = "athlete-name") { +"اسم ورزشکار" }
        }
        p {
            strong { +"تاریخ شروع:" }
            span(classes = "start-date") { +"تاریخ شروع" }
        }
        p {
            strong { +"تاریخ پایان:" }
            span(classes = "end-date") { +"تاریخ پایان" }
        }
        p {
            strong { +"شماره مربی:" }
            span(classes = "coach-phone") { +"شماره مربی" }
        }
    }

    workoutPlan.dailyWorkouts.forEachIndexed { index, dailyWorkout ->
        h3 { +dailyWorkout.day }
        table {
            thead {
                tr {
                    th { +"شماره" }
                    th { +"تمرین" }
                    th { +"تعداد ست‌ها" }
                    th { +"یادداشت‌ها" }
                }
            }
            tbody {
                dailyWorkout.exercises.forEachIndexed { exerciseIndex, exercise ->
                    tr {
                        td { +(exerciseIndex + 1).toString() }
                        td { +exercise.name }
                        td { +exercise.sets }
                        td { +exercise.notes }
                    }
                }
            }
        }
    }

    h3 { +"نکات و دستور‌العمل‌ها" }
    table {
        thead {
            tr {
                th { +"شماره" }
                th { +"توضیحات" }
            }
        }
        tbody {
            tr {
                td { +"1" }
                td { +"توضیحات 1" }
            }
        }
    }
}

val headers = buildString {
    appendHTML().html {
        head {
            meta(charset = "UTF-8")
            meta(name = "viewport", content = "width=device-width, initial-scale=1.0")
            title("برنامه تمرینی")
            link(
                href = "https://fonts.googleapis.com/css2?family=Vazirmatn:wght@400;500;700&display=swap",
                rel = "stylesheet"
            )
            styleLink("/baran.css")  // If you want to externalize the styles
        }
    }
}
