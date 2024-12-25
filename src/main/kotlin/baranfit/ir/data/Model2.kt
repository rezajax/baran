package baranfit.ir.data

data class TrainingProgram(
    val title: String,  // Title of the entire training program
    val trainerName: String,
    val athleteName: String,
    val startDate: String, //can replay with LocalDate
    val endDate: String,
    val athletePhone: String,

    val notesTitle: String,
    val notes: List<Instruction>,

    val days: List<Day>,

    val importantNotesTitle: String,
    val importantNotes: List<ImportantNote>
)

data class Instruction(
    val number: String,
    val description: String
)

data class Day(
    val title: String,  // Represents the title of the day (e.g., "Day 1")
    val exercises: List<Exercise>  // Exercises for that day
)

data class Exercise(
    val number: String,
    val exerciseName: String,
    val sets: String,
    val notes: String? = "N/A"
)

data class ImportantNote(
    val number: String,
    val description: String
)



// Example usage:
val trainingProgram = TrainingProgram(
    title = "Training Program for مادر کیان",
    trainerName = "باران حیدری",
    athleteName = "مادر کیان",
    startDate = "1403/09/16",
    endDate = "1403/10/16",
    athletePhone = "09184280519",
    notesTitle = "نکات و دستورالعمل‌ها",
    notes = listOf(
        Instruction("شماره", "توضیحات"),
        Instruction("1", "گرم کردن در ابتدای تمرین: ۴ دقیقه گرم کردن عمومی بدن و ۳ دقیقه اجرای حرکات با وزنه بسیار سبک روی عضلاتی که قصد تمرین دادنشان را دارید."),
        Instruction("2", "استراحت بین ست ها: 60 ثانیه"),
        Instruction("3", "استراحت بین حرکات: 90ثانیه"),
        Instruction("4", "اگر در حرکات سنگین مثل اسکات یا در سوپر ست‌ها احساس کردید به استراحت بیشتری نیاز دارید میتونید تا پایین آمدن ضربان قلب تا ۱۰۰ ضربه در دقیقه استراحت کنید."),
        Instruction("5", "تا ۱۰ دقیقه بعد از تمرین هیچ غذایی مصرف نشود."),
        Instruction("6", "سعی کنید بدن خود را به تمرین در یک ساعت معین عادت دهید.")
    ),
    days = listOf(
        Day(
            title = "روز اول",
            exercises = listOf(
                Exercise("شماره", "حرکات تمرینی", "تعداد ست", "توضیحات"),

                Exercise("1", "جلو ران", "2x12-2x10-10/10", "ست اخر دامنه دار"),
                Exercise("2", "اسکات اسمیت", "15-12-12-10", null),
                Exercise("3", "پرس پا", "3x15", null),
                Exercise("4", "هاگ + ساق 7/8", "12-10-8-8", "ساق 20 تکرار 4 ست دو ست 7 دو ست 8"),
                Exercise("5", "شکم خلبانی + پلانک", "3x20/x", null)
            )
        ),
        Day(
            title = "روز دوم",
            exercises = listOf(
                Exercise("شماره", "حرکات تمرینی", "تعداد ست", "توضیحات"),

                Exercise("1", "زیر بغل بارفیکس دست باز", "3x12", null),
                Exercise("2", "لت دست جمع + استرالیایی", "3x15/12", null),
                Exercise("3", "اره ای دمبل دامنه دار", "3x12/12", null),
                Exercise("4", "سرشانه هالتر", "4x12", null),
                Exercise("5", "نشر جانب + نشر خم هالتر", "4x12/15", null),
                Exercise("6", "شکم ضبدری + پلانک چرخشی", "3x15/30", null)
            )
        ),
        Day(
            title = "روز سوم",
            exercises = listOf(
                Exercise("شماره", "حرکات تمرینی", "تعداد ست", "توضیحات"),

                Exercise("1", "پشت پا خوابیده", "4x15", "هرست سنگین"),
                Exercise("2", "هیپ تراست هالتر", "4x10", "هر ست سنگین"),
                Exercise("3", "کیک بک باسن کراس", "3x13", null),
                Exercise("4", "لیفت تی بار", "4x13", null),
                Exercise("5", "ساق + شکم نیمه + شکم پا 90", "4x20/15/20", null)
            )
        )
    ),
    importantNotesTitle = "Important Notes",
    importantNotes = listOf(
        ImportantNote("شماره", "توضیحات"),
        ImportantNote("1", "با توجه به تعداد حرکات و ست‌ها، تمرین بدنسازی شما نهایتا باید ۷۰ دقیقه طول بکشد."),
        ImportantNote("2", "در هر هفته تلاش کنید کمی سنگین‌تر وزنه بزنید."),
        ImportantNote("3", "در صورتی که حس کردید در حین تمرین، روی عضله خاصی از بدنتان به خوبی فشار نمی‌آید، حتما این مورد را به من گزارش دهید."),
        ImportantNote("4", "تمرین هوازی: بعد تمرین 40 دقیقه هرکاردیو که دوست دارید (نه دوید!) با ضربان قلب ۱۲۰.")
    )
)

