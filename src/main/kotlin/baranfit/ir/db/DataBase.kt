package baranfit.ir.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.transactions.transaction
import baranfit.ir.data.TrainingProgram
import org.jetbrains.exposed.sql.*

// Database connection setup
fun initDb() {
    Database.connect("jdbc:h2:./baranDB", driver = "org.h2.Driver")
    transaction {
        SchemaUtils.create(TrainingPrograms, Instructions, Days, Exercises, ImportantNotes)
    }
}

// Define tables for Exposed
object TrainingPrograms : IntIdTable() {
    val title = varchar("title", 255)
    val trainerName = varchar("trainerName", 255)
    val athleteName = varchar("athleteName", 255)
    val startDate = varchar("startDate", 255)
    val endDate = varchar("endDate", 255)
    val athletePhone = varchar("athletePhone", 255)
    val notesTitle = varchar("notesTitle", 255)
    val importantNotesTitle = varchar("importantNotesTitle", 255)
}

object Instructions : IntIdTable() {
    val programId = reference("programId", TrainingPrograms)
    val number = varchar("number", 50)
    val description = varchar("description", 255)
}

object Days : IntIdTable() {
    val programId = reference("programId", TrainingPrograms)
    val title = varchar("title", 255)
}

object Exercises : IntIdTable() {
    val dayId = reference("dayId", Days)
    val number = varchar("number", 50)
    val exerciseName = varchar("exerciseName", 255)
    val sets = varchar("sets", 255)
    val notes = varchar("notes", 255).nullable()
}

object ImportantNotes : IntIdTable() {
    val programId = reference("programId", TrainingPrograms)
    val number = varchar("number", 50)
    val description = varchar("description", 255)
}



fun insertTrainingProgram(trainingProgram: TrainingProgram) {
    transaction {
        // Insert training program
        val programId1 = TrainingPrograms
            .insertAndGetId {
                it[title] = trainingProgram.title
                it[trainerName] = trainingProgram.trainerName
                it[athleteName] = trainingProgram.athleteName
                it[startDate] = trainingProgram.startDate
                it[endDate] = trainingProgram.endDate
                it[athletePhone] = trainingProgram.athletePhone
                it[notesTitle] = trainingProgram.notesTitle
                it[importantNotesTitle] = trainingProgram.importantNotesTitle
            }

        // Insert notes
        trainingProgram.notes.forEach { instruction ->
            Instructions.insert {
                it[programId] = programId1 // programId is already the correct EntityID type here
                it[number] = instruction.number
                it[description] = instruction.description
            }
        }

        // Insert days and exercises
        trainingProgram.days.forEach { day ->
            val dayId1 = Days.insertAndGetId {
                it[programId] = programId1 // programId is the correct EntityID type
                it[title] = day.title
            }

            day.exercises.forEach { exercise ->
                Exercises.insert {
                    it[dayId] = dayId1 // dayId is correct EntityID type
                    it[number] = exercise.number
                    it[exerciseName] = exercise.exerciseName
                    it[sets] = exercise.sets
                    it[notes] = exercise.notes
                }
            }
        }

        // Insert important notes
        trainingProgram.importantNotes.forEach { importantNote ->
            ImportantNotes.insert {
                it[programId] = programId1 // programId is the correct EntityID type
                it[number] = importantNote.number
                it[description] = importantNote.description
            }
        }
    }
}
