package baranfit.ir.Baran

import baranfit.ir.data.WorkoutPlan
import baranfit.ir.data.trainingProgram
import baranfit.ir.data.workoutPlan
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.css.thead
import kotlinx.html.*


fun Application.baranApp2() {

    routing {
        get("/2") {
            call.respondHtml {
                baranHead2()
                body {
                    div(classes = "container-wrapper") {
                        div(classes = "container") {
                            workoutContent2(workoutPlan)
                        }
                    }
                }
            }

        }

    }
}


private fun HTML.baranHead2() {
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
fun DIV.workoutContent2(workoutPlan: WorkoutPlan) {
    h1 { +trainingProgram.title }
    h2 {
        +"مربی: "
        span(classes = "coach-name") { +trainingProgram.athleteName }
    }

    div(classes = "info") {
        p {
            strong { +"نام ورزشکار:" }
            span(classes = "athlete-name") { +trainingProgram.trainerName }
        }
        p {
            strong { +"تاریخ شروع:" }
            span(classes = "start-date") { +trainingProgram.startDate }
        }
        p {
            strong { +"تاریخ پایان:" }
            span(classes = "end-date") { +trainingProgram.endDate }
        }
        p {
            strong { +"شماره مربی:" }
            span(classes = "coach-phone") { trainingProgram.athletePhone }
        }
    }


    h3 { +trainingProgram.notesTitle }

    table {
        thead {
            tr {
                th { +trainingProgram.notes[0].number }
                th { +trainingProgram.notes[0].description }
            }
        }
        tbody {
            trainingProgram.notes.forEachIndexed() { index, note ->
                if (index == 0) return@forEachIndexed // Skip the first note
                tr {
                    td { +note.number }
                    td { +note.description }
                }
            }

        }
    }



    trainingProgram.days.forEachIndexed() { index, day ->
        h3 { +day.title }
        table {
            day.exercises.forEachIndexed() { index, exercise ->
                if (index == 0) {
                    thead {
                        tr {
                            th { +exercise.number }
                            th { +exercise.exerciseName }
                            th { +exercise.sets }
                            val l = exercise.notes?.length ?: 0

                            td { +l }
                        }
                    }
                } else {
                    tbody {
                        tr {
                            td { +exercise.number }
                            td { +exercise.exerciseName }
                            td { +exercise.sets }
                            val l = exercise.notes?.length ?: 0

                            td { +l }
                        }
                    }
                }
            }
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

