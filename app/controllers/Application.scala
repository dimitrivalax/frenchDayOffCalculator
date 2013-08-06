package controllers

import play.api.mvc._
import play.api.libs.json.{Json}
import org.joda.time.DateTime
import play.api.Logger

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def isAfrenchDayOff(year: Int, month: Int, day: Int) = Action {

    var result = isPaquesAscensionPentecote(year, month, day);

    result = result || isAFixedDayOff(month, day)

    Ok(Json.obj("isAfrenchDayOff" -> result)).as("application/json").withHeaders(ACCESS_CONTROL_ALLOW_ORIGIN -> "*")

  }

  private def isAFixedDayOff(month: Int, day: Int) : Boolean = (month, day) match {
    case (1, 1) | (5, 1) | (5, 8) | (7, 14) | (8, 15) | (11, 1) | (11, 11) | (12, 25) => true
    case _ => false
  }

  private def isPaquesAscensionPentecote(year: Int, month: Int, day: Int): Boolean = {

    var result = false

    // Paques

    val g = year % 19

    val c: Int = year / 100
    val c4: Int = c / 4
    val e: Int = ((8 * c) + 13) / 25

    val h: Int = ((19 * g) + c - c4 - e + 15) % 30
    val k: Int = h / 28
    val p: Int = 29 / (h + 1)
    val q: Int = (21 - g) / 11
    val i: Int = (k * p * q - 1) * k + h
    val b: Int = (year / 4) + year
    val j1: Int = b + i + 2 + c4 - c
    val j2: Int = j1 % 7
    val r = 28 + i - j2

    val monthNumber = if (r <= 31){
      3
    } else 4
    val dayNumber = if (r <= 31){
      r
    } else r - 31
    Logger.debug(" r : " + r)
    Logger.debug(" monthNumber : " + monthNumber)
    Logger.debug(" dayNumber : " + dayNumber)

    val paques =  new DateTime(year, monthNumber, dayNumber, 0, 0)
    Logger.debug(" paques : " + paques)
    result = result || (month == paques.plusDays(1).getMonthOfYear & day == paques.plusDays(1).getDayOfMonth)

    // ascension

    val ascension = paques.plusDays(39)
    result = result || (month == ascension.getMonthOfYear & day == ascension.getDayOfMonth)

    // pentecote
    val pentecote = paques.plusDays(50)
    Logger.debug(" pentecote : " + pentecote)
    result = result || (month == pentecote.getMonthOfYear & day == pentecote.getDayOfMonth)




     result
  }
  
}