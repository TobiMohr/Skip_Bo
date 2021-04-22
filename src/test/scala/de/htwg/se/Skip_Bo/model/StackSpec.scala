package de.htwg.se.Skip_Bo.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StackSpec extends AnyWordSpec with Matchers{
  "A Stack" when {
    "new" should {
      val mtStack: List[Card] = (Nil)

      "be empty" in {
        mtStack.contains(Card(Colour.red, 1)) should be(false)
        mtStack.contains() should be(false)
        mtStack.isEmpty should be(true)
      }

    }
  }
}
