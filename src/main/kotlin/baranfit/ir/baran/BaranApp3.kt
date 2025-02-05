@file:Suppress("LABEL_NAME_CLASH")

package baranfit.ir.baran

import baranfit.ir.data.trainingProgram
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.*
import baranfit.ir.db.*
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction


fun Application.baranApp3() {
    initDb()

    insertTrainingProgram(trainingProgram)



    routing {
        get("/") {
            val scriptContent = """
            function hi(athleteName) {
                alert("Transaction athleteName: " + athleteName);
            }
        """.trimIndent()

            call.respondHtml {
                baranHead3()
                body {
                    transaction {

                        ul (classes = "scrolled") {
                            TrainingPrograms.selectAll().forEach { row ->
                                val athleteName = row[TrainingPrograms.athleteName].toString()
                                li { +athleteName }
                                button {
                                    onClick = "hi('$athleteName')"
                                    +athleteName // Display the athlete name on the button
                                }
                            }
                        }
                    }
                    div(classes = "container-wrapper") {
                        div(classes = "container") {
                            workoutContent3()
                        }
                    }
                    script {
                        unsafe {
                            +scriptContent
                        }
                    }
                }
            }
        }
    }
}


private fun HTML.baranHead3() {
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


//fun DIV.workoutContent2( workoutPlan: WorkoutPlan) {
// Generates the content of the workout plan
fun DIV.workoutContent3() {
    h1 { +trainingProgram.title }
    h2 {
        +"مربی: "
        span(classes = "coach-name") { +trainingProgram.athleteName }
    }

    div(classes = "info") {
        p {
            strong { +"نام ورزشکار: " }
            span(classes = "athlete-name") { +trainingProgram.trainerName }
        }
        p {
            strong { +"تاریخ شروع: " }
            span(classes = "start-date") { +trainingProgram.startDate }
        }
        p {
            strong { +"تاریخ پایان: " }
            span(classes = "end-date") { +trainingProgram.endDate }
        }
        p {
            strong { +"شماره مربی: " }
            span(classes = "coach-phone") { +trainingProgram.athletePhone }
        }
    }


    // header notes
    h3 { +trainingProgram.notesTitle }
    table {
        thead {
            tr {
                th { +trainingProgram.notes[0].number }
                th { +trainingProgram.notes[0].description }
            }
        }
        tbody {
            trainingProgram.notes.forEachIndexed { index, note ->
                if (index == 0) return@forEachIndexed // Skip the first note
                tr {
                    td { +note.number }
                    td {
                        input(type = InputType.text) {
                            value = note.description
                            style = "border: none; outline: none;" // Remove the border and outline
                        }
                    }
//                    td { +note.description }
                }
            }
        }
    }



    trainingProgram.days.forEachIndexed { index, day ->
        h3 { +day.title }
        table {
            thead {
                day.exercises.forEachIndexed() { i, exercise ->
                    if (i == 0) {
                        tr {
                            th { +exercise.number }
                            th { +exercise.exerciseName }
                            th { +exercise.sets }
                            th { +(exercise.notes ?: "توضیحات") }
                        }
                    }

                }
            }
            tbody {
                day.exercises.forEachIndexed { index, exercise ->
                    if (index == 0) return@forEachIndexed // Skip the first exercise
                    tr {
                        td { +exercise.number }
                        td { +exercise.exerciseName }
                        td { +exercise.sets }
                        td { +(exercise.notes ?: "") }
                    }
                }
            }
        }
    }

    h3 { +trainingProgram.importantNotesTitle }
    table {
        thead {
            tr {
                th { +trainingProgram.importantNotes[0].number }
                th { +trainingProgram.importantNotes[0].description }
            }
        }
        tbody {
            trainingProgram.importantNotes.forEachIndexed() { index, note ->
                if (index == 0) return@forEachIndexed // Skip the first note
                tr {
                    td { +note.number }
                    td { +note.description }
                }
            }
        }
    }
}

