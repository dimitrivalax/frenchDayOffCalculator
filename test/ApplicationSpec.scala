package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import controllers.Application

import play.api.libs.json._
import play.api.libs.json.Json._
import play.api.test.FakeApplication
import play.api.Logger


/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends Specification {


  "isAfrenchDayOff(year: Int, month: Int, day: Int): JsValue \n" +
    " doit retourner {\"isAfrenchDayOff\" -> true} si jours ferier, {\"isAfrenchDayOff\" -> false} sinon" in {
    running(FakeApplication()) {


      // paques
      var result = controllers.Application.isAfrenchDayOff(2013, 3, 31)(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")

      val jsontrue: JsValue = Json.parse(contentAsString(result))

      val truebool  = (jsontrue \ "isAfrenchDayOff").as[Boolean]

      truebool mustEqual(true)
      truebool mustNotEqual(false)

      result = controllers.Application.isAfrenchDayOff(2013, 3, 30)(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")

      val jsonfalse = Json.parse(contentAsString(result))

      val falsebool = (jsonfalse \ "isAfrenchDayOff").as[Boolean]

      falsebool mustEqual(false)
      falsebool mustNotEqual(true)

      result = controllers.Application.isAfrenchDayOff(2014, 4, 20)(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")

      val jsonTrue2014 = Json.parse(contentAsString(result))

      val boolTrue2014 = (jsonTrue2014 \ "isAfrenchDayOff").as[Boolean]

      boolTrue2014 mustEqual(true)
      boolTrue2014 mustNotEqual(false)

      result = controllers.Application.isAfrenchDayOff(2013, 3, 20)(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")

      val jsonFalse2014 = Json.parse(contentAsString(result))

      val boolFalse2014 = (jsonFalse2014 \ "isAfrenchDayOff").as[Boolean]

      boolFalse2014 mustEqual(false)
      boolFalse2014 mustNotEqual(true)

      // 01/01
      result = controllers.Application.isAfrenchDayOff(2013, 1, 1)(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")

      val newYeartrue = Json.parse(contentAsString(result))

      val trueNewYearbool = (newYeartrue \ "isAfrenchDayOff").as[Boolean]

      trueNewYearbool mustEqual(true)
      trueNewYearbool mustNotEqual(false)

      // 01/05
      result = controllers.Application.isAfrenchDayOff(2013, 5, 1)(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")

      val firstMaytrue = Json.parse(contentAsString(result))

      val trueFistMaybool = (firstMaytrue \ "isAfrenchDayOff").as[Boolean]

      trueFistMaybool mustEqual(true)
      trueFistMaybool mustNotEqual(false)

      // 08/05
      result = controllers.Application.isAfrenchDayOff(2013, 5, 1)(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")

      val eightMaytrue = Json.parse(contentAsString(result))

      val trueEightMaybool = (eightMaytrue \ "isAfrenchDayOff").as[Boolean]

      trueEightMaybool mustEqual(true)
      trueEightMaybool mustNotEqual(false)

      // 14/07
      result = controllers.Application.isAfrenchDayOff(2013, 7, 14)(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")

      val true1407 = Json.parse(contentAsString(result))

      val true1407Bool = (true1407 \ "isAfrenchDayOff").as[Boolean]

      true1407Bool mustEqual(true)
      true1407Bool mustNotEqual(false)

      // 15/08
      result = controllers.Application.isAfrenchDayOff(2013, 8, 15)(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")
      val true1508 = Json.parse(contentAsString(result))

      val true1508Bool = (true1508 \ "isAfrenchDayOff").as[Boolean]

      true1508Bool mustEqual(true)
      true1508Bool mustNotEqual(false)

      // 01/11
      result = controllers.Application.isAfrenchDayOff(2013, 11, 1)(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")

      val true0111 = Json.parse(contentAsString(result))

      val true0111Bool = (true0111 \ "isAfrenchDayOff").as[Boolean]

      true0111Bool mustEqual(true)
      true0111Bool mustNotEqual(false)

      // 11/11
      result = controllers.Application.isAfrenchDayOff(2013, 11, 11)(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")

      val true1111 = Json.parse(contentAsString(result))

      val true1111Bool = (true1111 \ "isAfrenchDayOff").as[Boolean]

      true1111Bool mustEqual(true)
      true1111Bool mustNotEqual(false)

      // 25/12
      result = controllers.Application.isAfrenchDayOff(2013, 12, 25)(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")

      val true2512 = Json.parse(contentAsString(result))

      val true2512Bool = (true2512 \ "isAfrenchDayOff").as[Boolean]

      true2512Bool mustEqual(true)
      true2512Bool mustNotEqual(false)

      // pentecote
      result = controllers.Application.isAfrenchDayOff(2013, 5, 20)(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")

      val truePentecote2013 = Json.parse(contentAsString(result))

      val truePentecote2013Bool = (truePentecote2013 \ "isAfrenchDayOff").as[Boolean]

      truePentecote2013Bool mustEqual(true)
      truePentecote2013Bool mustNotEqual(false)

      result = controllers.Application.isAfrenchDayOff(2014, 6, 9)(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")

      val truePentecote2014 = Json.parse(contentAsString(result))

      val truePentecote2014Bool = (truePentecote2014 \ "isAfrenchDayOff").as[Boolean]

      truePentecote2014Bool mustEqual(true)
      truePentecote2014Bool mustNotEqual(false)

      // ascension
      result = controllers.Application.isAfrenchDayOff(2013, 5, 9)(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")

      val trueAscension2013 = Json.parse(contentAsString(result))

      val trueAscension2013Bool = (trueAscension2013 \ "isAfrenchDayOff").as[Boolean]

      trueAscension2013Bool mustEqual(true)
      trueAscension2013Bool mustNotEqual(false)

      result = controllers.Application.isAfrenchDayOff(2014, 5, 29)(FakeRequest())

      status(result) must equalTo(OK)
      contentType(result) must beSome("application/json")

      val trueAscension2014 = Json.parse(contentAsString(result))

      val trueAscension2014Bool = (trueAscension2014 \ "isAfrenchDayOff").as[Boolean]

      trueAscension2014Bool mustEqual(true)
      trueAscension2014Bool mustNotEqual(false)
    }
  }

}