package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import controllers.Application



/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends Specification {


  "isAfrenchDayOff(year: Int, month: Int, day: Int): JsValue \n" +
    " doit retourner {\"isAfrenchDayOff\" -> true} si jours ferier, {\"isAfrenchDayOff\" -> false} sinon" in {
    running(FakeApplication()) {

      // paque

      val jsontrue = Application.isAfrenchDayOff(2013, 3, 31)

      val truebool = (jsontrue \ "isAfrenchDayOff").as[Boolean]

      truebool mustEqual(true)
      truebool mustNotEqual(false)

      val jsonfalse = Application.isAfrenchDayOff(2013, 3, 30)

      val falsebool = (jsonfalse \ "isAfrenchDayOff").as[Boolean]

      falsebool mustEqual(false)
      falsebool mustNotEqual(true)

      val jsonTrue2014 = Application.isAfrenchDayOff(2014, 4, 20)

      val boolTrue2014 = (jsonTrue2014 \ "isAfrenchDayOff").as[Boolean]

      boolTrue2014 mustEqual(true)
      boolTrue2014 mustNotEqual(false)

      val jsonFalse2014 = Application.isAfrenchDayOff(2014, 3, 20)

      val boolFalse2014 = (jsonFalse2014 \ "isAfrenchDayOff").as[Boolean]

      boolFalse2014 mustEqual(false)
      boolFalse2014 mustNotEqual(true)

      // 01/01
      val newYeartrue = Application.isAfrenchDayOff(2013, 1, 1)

      val trueNewYearbool = (newYeartrue \ "isAfrenchDayOff").as[Boolean]

      trueNewYearbool mustEqual(true)
      trueNewYearbool mustNotEqual(false)

      // 01/05
      val firstMaytrue = Application.isAfrenchDayOff(2013, 5, 1)

      val trueFistMaybool = (firstMaytrue \ "isAfrenchDayOff").as[Boolean]

      trueFistMaybool mustEqual(true)
      trueFistMaybool mustNotEqual(false)

      // 08/05
      val eightMaytrue = Application.isAfrenchDayOff(2013, 5, 1)

      val trueEightMaybool = (eightMaytrue \ "isAfrenchDayOff").as[Boolean]

      trueEightMaybool mustEqual(true)
      trueEightMaybool mustNotEqual(false)

      // 14/07
      val true1407 = Application.isAfrenchDayOff(2013, 7, 14)

      val true1407Bool = (true1407 \ "isAfrenchDayOff").as[Boolean]

      true1407Bool mustEqual(true)
      true1407Bool mustNotEqual(false)

      // 15/08
      val true1508 = Application.isAfrenchDayOff(2013, 7, 14)

      val true1508Bool = (true1508 \ "isAfrenchDayOff").as[Boolean]

      true1508Bool mustEqual(true)
      true1508Bool mustNotEqual(false)

      // 01/11
      val true0111 = Application.isAfrenchDayOff(2013, 7, 14)

      val true0111Bool = (true0111 \ "isAfrenchDayOff").as[Boolean]

      true0111Bool mustEqual(true)
      true0111Bool mustNotEqual(false)

      // 11/11
      val true1111 = Application.isAfrenchDayOff(2013, 7, 14)

      val true1111Bool = (true1111 \ "isAfrenchDayOff").as[Boolean]

      true1111Bool mustEqual(true)
      true1111Bool mustNotEqual(false)

      // 25/12
      val true2512 = Application.isAfrenchDayOff(2013, 7, 14)

      val true2512Bool = (true2512 \ "isAfrenchDayOff").as[Boolean]

      true2512Bool mustEqual(true)
      true2512Bool mustNotEqual(false)
    }
  }

}