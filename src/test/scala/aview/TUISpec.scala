package aview

import de.htwg.se.Skip_Bo.aview.TUI
import de.htwg.se.Skip_Bo.controller.Controller
import de.htwg.se.Skip_Bo.model.{Board, Card, Colour, Stack}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TUISpec extends AnyWordSpec with Matchers{

  "A TUI " when {
    "new" should {
      val stack = new Stack(List(Card(Colour.red, 1), Card(Colour.green, 1)))
      val card = new Card(Colour.blue,2)
      val board = new Board()
      val controller = new Controller(stack, card, board)
      val tui = new TUI(controller)
      "make a Stack with input d" in {
        tui.processInputLine("d")
        controller.makeStack(List(Card(Colour.red, 1), Card(Colour.green, 1))) should be()
        controller.stack should be(Stack(List(Card(Colour.red, 1), Card(Colour.green, 1))))
      }
      "make a Card with inoput c" in {
        tui.processInputLine("c")
        controller.makeCard(Colour.blue, 2) should be()
        controller.card should be(Card(Colour.blue, 2))
      }
      "put a card on player stapel 1 with input p1" in {
        tui.processInputLine("p1")
        controller.p1() should be()
      }

      "put a card on player stapel 2 with input p2" in {
        tui.processInputLine("p2")
        controller.p2() should be()
      }

      "put a card on player stapel 3 with input p3" in {
        tui.processInputLine("p3")
        controller.p3() should be()
      }

      "put a card on player stapel 4 with input p4" in {
        tui.processInputLine("p4")
        controller.p4() should be()
      }

      "put a card on hilfsstapel 1 with input p1" in {
        tui.processInputLine("m1")
        controller.m1() should be()
      }

      "put a card on hilfsstapel 2 with input p1" in {
        tui.processInputLine("m2")
        controller.m2() should be()
      }

      "put a card on hilfsstapel 3 with input p1" in {
        tui.processInputLine("m3")
        controller.m3() should be()
      }

      "put a card on hilfsstapel 4 with input p1" in {
        tui.processInputLine("m4")
        controller.m4() should be()
      }
      "end turn with Input end" in {
        tui.processInputLine("end")
        controller.Beenden should be()
      }
      "end tui with Input exit" in {
        tui.processInputLine("exit")
      }
      "error on wrong input" in {
        tui.processInputLine("") should be()
      }
    }
  }

}
