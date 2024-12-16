package baranfit.ir.Data

data class WorkoutPlan(
    val coachName: String,
    val athleteName: String,
    val startDate: String,
    val endDate: String,
    val coachPhone: String,
    val instructions: List<Instruction>,
    val dailyWorkouts: List<DailyWorkout>,
    val importantNotes: List<Note>
) {
    data class Instruction(
        val number: Int,
        val description: String
    )

    data class DailyWorkout(
        val day: String,
        val exercises: List<Exercise>
    ) {
        data class Exercise(
            val number: Int,
            val name: String,
            val sets: String,
            val notes: String = " ",
            val title: titles = titles("شماره" , "حرکات تمرینی" , "تعداد ست", "توضیحات")
        ) {
            data class titles (
                val number: String,
                val name : String,
                val sets: String,
                val notes: String
            )
        }
    }

    data class Note(
        val number: Int,
        val description: String
    )
}

// Example usage:
val workoutPlan = WorkoutPlan(
    coachName = "باران حیدری",
    athleteName = "مادر کیان",
    startDate = "1403/09/16",
    endDate = "1403/10/16",
    coachPhone = "09184280519",
    instructions = listOf(
        WorkoutPlan.Instruction(1, "گرم کردن در ابتدای تمرین: ۴ دقیقه گرم کردن عمومی بدن و ۳ دقیقه اجرای حرکات با وزنه بسیار سبک روی عضلاتی که قصد تمرین دادنشان را دارید."),
        WorkoutPlan.Instruction(2, "استراحت بین ست ها: 60 ثانیه"),
        WorkoutPlan.Instruction(3, "استراحت بین حرکات: 90ثانیه"),
        WorkoutPlan.Instruction(4, "اگر در حرکات سنگین مثل اسکات یا در سوپر ست‌ها احساس کردید به استراحت بیشتری نیاز دارید میتونید تا پایین آمدن ضربان قلب تا ۱۰۰ ضربه در دقیقه استراحت کنید."),
        WorkoutPlan.Instruction(5, "تا ۱۰ دقیقه بعد از تمرین هیچ غذایی مصرف نشود."),
        WorkoutPlan.Instruction(6, "سعی کنید بدن خود را به تمرین در یک ساعت معین عادت دهید.")
    ),
    dailyWorkouts = listOf(
        WorkoutPlan.DailyWorkout(
            day = "روز اول",
            exercises = listOf(
                WorkoutPlan.DailyWorkout.Exercise(1, "جلو ران", "2x12-2x10-10/10", "ست اخر دامنه دار"),
                WorkoutPlan.DailyWorkout.Exercise(2, "اسکات اسمیت", "15-12-12-10"),
                WorkoutPlan.DailyWorkout.Exercise(3, "پرس پا", "3x15"),
                WorkoutPlan.DailyWorkout.Exercise(4, "هاگ + ساق 7/8", "12-10-8-8", "ساق 20 تکرار 4 ست دو ست 7 دو ست 8"),
                WorkoutPlan.DailyWorkout.Exercise(5, "شکم خلبانی + پلانک", "3x20/x")
            )
        ),
        WorkoutPlan.DailyWorkout(
            day = "روز دوم",
            exercises = listOf(
                WorkoutPlan.DailyWorkout.Exercise(1, "زیر بغل بارفیکس دست باز", "3x12"),
                WorkoutPlan.DailyWorkout.Exercise(2, "لت دست جمع + استرالیایی", "3x15/12"),
                WorkoutPlan.DailyWorkout.Exercise(3, "اره ای دمبل دامنه دار", "3x12/12"),
                WorkoutPlan.DailyWorkout.Exercise(4, "سرشانه هالتر", "4x12"),
                WorkoutPlan.DailyWorkout.Exercise(5, "نشر جانب + نشر خم هالتر", "4x12/15"),
                WorkoutPlan.DailyWorkout.Exercise(6, "شکم ضبدری + پلانک چرخشی", "3x15/30")
            )
        ),
        WorkoutPlan.DailyWorkout(
            day = "روز سوم",
            exercises = listOf(
                WorkoutPlan.DailyWorkout.Exercise(1, "پشت پا خوابیده", "4x15", "هرست سنگین"),
                WorkoutPlan.DailyWorkout.Exercise(2, "هیپ تراست هالتر", "4x10", "هر ست سنگین"),
                WorkoutPlan.DailyWorkout.Exercise(3, "کیک بک باسن کراس", "3x13"),
                WorkoutPlan.DailyWorkout.Exercise(4, "لیفت تی بار", "4x13"),
                WorkoutPlan.DailyWorkout.Exercise(5, "ساق + شکم نیمه + شکم پا 90", "4x20/15/20")
            )
        )
    ),
    importantNotes = listOf(
        WorkoutPlan.Note(1, "با توجه به تعداد حرکات و ست‌ها، تمرین بدنسازی شما نهایتا باید ۷۰ دقیقه طول بکشد."),
        WorkoutPlan.Note(2, "در هر هفته تلاش کنید کمی سنگین‌تر وزنه بزنید."),
        WorkoutPlan.Note(3, "در صورتی که حس کردید در حین تمرین، روی عضله خاصی از بدنتان به خوبی فشار نمی‌آید، حتما این مورد را به من گزارش دهید."),
        WorkoutPlan.Note(4, "تمرین هوازی: بعد تمرین 40 دقیقه هرکاردیو که دوست دارید (نه دوید!) با ضربان قلب ۱۲۰.")
    )
)
