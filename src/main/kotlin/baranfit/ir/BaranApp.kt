package baranfit.ir

import baranfit.ir.Data.workoutPlan
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.css.*
import kotlinx.html.*

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
                                    dailyWorkout.exercises.forEachIndexed { index, exercise ->
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


        get("/baran.css") {
            call.respondCss {
/*                rule(":root") {
                    property("--primary-color", "#C61C1E")
                    property("--primary-light", "#FAD4D5")
                    property("--primary-dark", "#960000")
                    property("--text-on-primary", "#ffffff")
                    property("--secondary-color", "#333333")
                    property("--background-color", "#F5F5F5")
                    property("--surface-color", "#ffffff")
                    property("--error-color", "#D32F2F")
                    property("--divider-color", "#E0E0E0")
                }*/

                // Global Styles
                body {
                    fontFamily = "'Vazirmatn', sans-serif"
                    direction = Direction.rtl
                    margin = "0"
                    padding = "0"
                    backgroundColor = Color("#F5F5F5")
                    color = Color("#333333")
                }

                ".container-wrapper" {
                    maxWidth = 1200.px
                    marginTop = 20.px
                    marginBottom = 20.px
                    padding = "16px"
                }

                ".container" {
                    backgroundColor = Color("#ffffff")
                    borderRadius = 12.px
//                    boxShadow("0 4px 8px rgba(0, 0, 0, 0.1)")
//                    overflow = "hidden"
                    padding = "16px"
                }

                "#training-image" {
                    width = 200.px
                    height = LinearDimension.auto
                    display = Display.block
                }

                "h1, h2, h3" {
                    textAlign = TextAlign.center
                    color = Color("#C61C1E")
                }

                h1 {
                    fontSize = 1.8.rem
                    marginBottom = 16.px
                }

                h2 {
                    fontSize = 1.4.rem
                    marginBottom = 8.px
                }

                h3 {
                    fontSize = 1.2.rem
                    marginTop = 16.px
                    marginBottom = 8.px
                    color = Color("#960000")
                }

                ".info" {
                    marginBottom = 16.px
                    padding = "16px"
                    backgroundColor = Color("#FAD4D5")
                    borderRadius = 8.px
                }

                ".info p" {
                    fontSize = 1.rem
                    marginTop = 4.px
                    marginBottom = 4.px
//                    lineHeight = 1.5.px
                    color = Color("#960000")
                }

                table {
                    width = 100.pct
                    borderCollapse = BorderCollapse.collapse
                    marginTop = 16.px
                    marginBottom = 16.px
                    borderRadius = 8.px
//                    overflow = "hidden"
                }

//                table, "th", "td" {
//                border = "1px solid #E0E0E0"
//            }

                th {
                    backgroundColor = Color("#C61C1E")
                    color = Color("#ffffff")
                    fontSize = 0.9.rem
                    padding = "8px"
                }

                td {
                    fontSize = 0.9.rem
                    padding = "8px"
                    textAlign = TextAlign.center
                }

                "tbody tr:nth-child(odd)" {
                    backgroundColor = Color("#F5F5F5")
                }

                "tbody tr:nth-child(even)" {
                    backgroundColor = Color("#ffffff")
                }

                ".fab" {
                    position = Position.fixed
                    bottom = 20.px
                    right = 20.px
                    backgroundColor = Color("#C61C1E")
                    color = Color("#ffffff")
                    fontSize = 1.5.rem
                    width = 56.px
                    height = 56.px
                    borderRadius = 50.pct
                    display = Display.flex
                    alignItems = Align.center
                    justifyContent = JustifyContent.center
//                    boxShadow("0 4px 8px rgba(0, 0, 0, 0.2)")
                    cursor = Cursor.pointer
                }

                // Responsive Design
/*                media(maxWidth = 768.px) {
                    h1 {
                        fontSize = 1.5.rem
                    }
                    h2 {
                        fontSize = 1.2.rem
                    }
                    th, td {
                    fontSize = 0.8.rem
                    padding = "6px"
                }
                }*/

                // Splash screen styles
                "#splash-screen-pdf" {
                    position = Position.fixed
                    top = 0.px
                    left = 0.px
                    width = 100.pct
                    height = 100.pct
                    backgroundColor = Color("rgba(0, 0, 0, 0.7)")
                    display = Display.flex
                    justifyContent = JustifyContent.center
                    alignItems = Align.center
                    color = Color.white
                    fontSize = 24.px
                    display = Display.none // Initially hidden
                }

                // Splash Screen
                "#splash-screen" {
                    position = Position.fixed
                    top = 0.px
                    left = 0.px
                    width = 100.pct
                    height = 100.pct
                    backgroundColor = Color("#C61C1E")
                    color = Color("#ffffff")
                    display = Display.none
                    flexDirection = FlexDirection.column
                    justifyContent = JustifyContent.center
                    alignItems = Align.center
                    zIndex = 1000
                    fontFamily = "'Vazirmatn', sans-serif"
                }

                "#splash-screen p" {
                    marginTop = 16.px
                    fontSize = 1.2.rem
                }

                // Loading Spinner
                ".loading-spinner" {
                    width = 50.px
                    height = 50.px
                    border = "5px solid #ffffff"
                    borderTop = "5px solid #960000"
                    borderRadius = 50.pct
//                    animation("spin", 1.s, TimingFunction.linear, IterationCount.infinite)
                }

//                keyframes("spin") {
//                    from { transform = "rotate(0deg)" }
//                    to { transform = "rotate(360deg)" }
//                }

                // Button styles
                "#export-pdf" {
                    marginTop = 20.px
                    marginBottom = 20.px
                    padding = "10px 20px"
                    backgroundColor = Color("#C61C1E")
                    color = Color("#ffffff")
                    border = "none"
                    borderRadius = 8.px
                    cursor = Cursor.pointer
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
        styleLink("/baran.css")  // If you want to externalize the styles
    }
}
