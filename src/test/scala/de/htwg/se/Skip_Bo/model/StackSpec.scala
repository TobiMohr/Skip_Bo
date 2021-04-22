package de.htwg.se.Skip_Bo.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StackSpec extends AnyWordSpec with Matchers{
  "A Stack" when {
    "new" should {
      val mtStack = Stack(Nil)
      val redStack = Stack(List(Card(Colour.red, 1), Card(Colour.red, 2)))
      val greenStack = Stack(List(Card(Colour.green, 1), Card(Colour.green, 2)))
      val blueStack = Stack(List(Card(Colour.blue, 1), Card(Colour.blue, 2)))

      "should have a top Card" in {
        redStack.topCard() should be("a red 1")
        greenStack.topCard() should be("a green 1")
        blueStack.topCard() should be("a blue 1")
      }
      "or be empty" in {
        mtStack.empty() should be(true)
        redStack.empty() should be(false)
        greenStack.empty() should be(false)
        blueStack.empty() should be(false)
      }

    }
  }
}
