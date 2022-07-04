package come.geekbrains.vitekm.m_2090_3.utils

import java.util.*

class CalendarUtils
val c = Calendar.getInstance()
val year = c.get(Calendar.YEAR).toString()
val month = (c.get(Calendar.MONTH) + 1).toString()
val day = c.get(Calendar.DAY_OF_MONTH).toString()


val i = c.add(GregorianCalendar.DAY_OF_MONTH, -1)

val year1 = c.get(Calendar.YEAR).toString()
val month1 = (c.get(Calendar.MONTH) + 1).toString()
val yesterday = c.get(Calendar.DAY_OF_MONTH).toString()


val i2 =  c.add(GregorianCalendar.DAY_OF_MONTH, -1)
val beforeYesterday = c.get(Calendar.DAY_OF_MONTH).toString()
val year2 = c.get(Calendar.YEAR).toString()
val month2 = (c.get(Calendar.MONTH) + 1).toString()
