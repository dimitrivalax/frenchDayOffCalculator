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

    Ok(Json.obj("isAfrenchDayOff" -> isDayOff(year, month, day))).as("application/json").withHeaders(ACCESS_CONTROL_ALLOW_ORIGIN -> "*")

  }

  def isDayOff(year: Int, month: Int, day: Int) =
    isLundiDePaques(year, month, day) || isAscension(year, month, day) || isPentecote(year, month, day) || isAFixedDayOff(month, day)

  def isLundiDePaques(year: Int, month: Int, day: Int) = {

    val mondayOfPaques = computePaquesDate(year).plusDays(1) // the tomorrow is off
    month == mondayOfPaques.getMonthOfYear && day == mondayOfPaques.getDayOfMonth
  }

  def isAscension(year: Int, month: Int, day: Int) = {
    val ascension = computePaquesDate(year).plusDays(39)
    month == ascension.getMonthOfYear && day == ascension.getDayOfMonth
  }

  def isPentecote(year: Int, month: Int, day: Int) = {
    val pentecote = computePaquesDate(year).plusDays(50)
    month == pentecote.getMonthOfYear && day == pentecote.getDayOfMonth

  }

  def isAFixedDayOff(month: Int, day: Int): Boolean = (day, month) match {
    case (1, 1) | (1, 5) | (8, 5) | (14, 7) | (15, 8) | (1, 11) | (11, 11) | (25, 12) => true
    case _ => false
  }

  // increment this counter if you tried to understand the following code
  // and you finally let it as is
  // count = 2 (Dimitri, Nicolas)
  private def computePaquesDate(year: Int) = {

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

    val monthNumber = if (r <= 31) {
      3
    } else 4
    val dayNumber = if (r <= 31) {
      r
    } else r - 31

    new DateTime(year, monthNumber, dayNumber, 0, 0) // the sunday of Paques

  }
  
}