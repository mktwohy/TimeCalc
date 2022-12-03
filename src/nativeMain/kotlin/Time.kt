import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun getCurrentTimeOfDay(): Time =
    Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .run {
            val amPm = if (hour < 12) "am" else "pm"
            "${hour % 12}:$minute$amPm"
        }
        .toTime()

fun String.toTime(): Time {
    val pm = this.trim().endsWith("pm", ignoreCase = true)

    with (this.remove("am", "pm", ignoreCase = true).trim()) {
        if (':' !in this) {
            return Time(this.toIntOrZero().to12Hour(pm))
        }

        val (hour, minute) = this
            .split(':')
            .map(String::toIntOrZero)

        return Time(hour.to12Hour(pm), minute)
    }
}

fun String.remove(vararg values: String, ignoreCase: Boolean): String {
    var ret = this
    values.forEach { ret = ret.replace(it, "", ignoreCase) }
    return ret
}

fun String.toIntOrZero(): Int =
    this.trim().toIntOrNull() ?: 0

fun Int.to12Hour(pm: Boolean): Int =
    if (pm) this + 12 else this

class Time(val hour: Int, val minute: Int = 0) {
    companion object {
        val now get() = getCurrentTimeOfDay()

        fun fromMinutes(minutes: Int): Time =
            Time(minutes / 60, minutes % 60)
    }

    init {
        if (hour !in 0..24) error("Invalid hour: $hour")
        if (minute !in 0..60) error("Invalid minute: $minute")
    }

    val asMinutes = (hour * 60) + minute

    operator fun plus(other: Time): Time =
        Time.fromMinutes(this.asMinutes + other.asMinutes)

    operator fun minus(other: Time): Time =
        Time.fromMinutes(this.asMinutes - other.asMinutes)

    override fun toString(): String {
        val amPm = if (hour < 12) "am" else "pm"
        val h = hour % 12
        val m = minute.toString().padStart(2, '0')
        return "$h:$m$amPm"
    }
}
