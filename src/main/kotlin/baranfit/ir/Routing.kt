package baranfit.ir

import baranfit.ir.Data.workoutPlan
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {

        workoutPlan.dailyWorkouts.forEachIndexed { index, dailyWorkout ->
            dailyWorkout.day
            dailyWorkout.exercises.forEach { exercise ->
//                exercise.
            }
        }

        get("/") {
            call.respondHtml {
                baranHead()

                body {
                    div(classes = "container-wrapper") {
                        div(classes = "container") {
//                            img(classes = "training-image") {
//                                src = "https://raw.githubusercontent.com/rezajax/note/refs/heads/main/logoname2.svg"
//                                alt = "Training Image"
//                            }
                            h1 {
                                +"برنامه تمرینی"
                            }
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
                                            td { +dailyWorkout.exercises[index].title.number }
                                            td { +dailyWorkout.exercises[index].title.name }
                                            td { +dailyWorkout.exercises[index].title.sets }
                                            td { +dailyWorkout.exercises[index].title.notes }
                                        }
                                    }
                                    dailyWorkout.exercises.forEachIndexed { index , exercise ->
                                        tr {
                                            td { +(index + 1).toString() }
                                            td { +exercise.name }
                                            td { +exercise.sets }
                                            td { +exercise.notes }


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


                            // Repeat similar table structure for other days (day1, day2, etc.)
                        }
                    }
                }
            }

        }
    }


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
//        styleLink("/styles.css")  // If you want to externalize the styles
    }
}
