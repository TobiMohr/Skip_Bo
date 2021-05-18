package controller
import de.htwg.se.Skip_Bo.controller.Controller
import de.htwg.se.Skip_Bo.model.{Board, Card, Colour, Stack}
import de.htwg.se.Skip_Bo.util.Observer

import scala.language.reflectiveCalls
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers {

  "A Controller" when {
    "observed by an Observer" should {
      val stack = new Stack(List(Card(Colour.green, 1), Card(Colour.red, 1)))
      val card = new Card(Colour.blue,2)
      val board = new Board()
      val controller = new Controller(stack, card, board)
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Unit = {updated = true; updated}
      }
      controller.add(observer)
      "notify its Observer after making Stack" in {
        controller.makeStack(List(Card(Colour.green, 1), Card(Colour.red, 1)))
        observer.updated should be(true)
      }
      "notify its Observer after making Card" in {
        controller.makeCard(Colour.blue, 2)
        observer.updated should be(true)
      }
      "notify its Observer after putting Card on p1" in {
        controller.p1() should be()
        observer.updated should be(true)
      }
      "notify its Observer after putting Card on p2" in {
        controller.p2() should be()
        observer.updated should be(true)
      }
      "notify its Observer after putting Card on p3" in {
        controller.p3() should be()
        observer.updated should be(true)
      }
      "notify its Observer after putting Card on p4" in {
        controller.p4() should be()
        observer.updated should be(true)
      }
      "notify its Observer after putting Card on m1" in {
        controller.m1() should be()
        observer.updated should be(true)
      }
      "notify its Observer after putting Card on m2" in {
        controller.m2() should be()
        observer.updated should be(true)
      }
      "notify its Observer after putting Card on m3" in {
        controller.m3() should be()
        observer.updated should be(true)
      }
      "notify its Observer after putting Card on m4" in {
        controller.m4() should be()
        observer.updated should be(true)
      }
      "notify its Observer after ending turn" in {
        controller.Beenden should be()
        observer.updated should be(true)
      }
      "print a Card" in {
        controller.printCard should be("a blue 2")
      }
      "print the board" in {
        controller.boardtoString() should be("""-------Hilfe---------
| p1 | p2 | p3 | p4 |

| m1 | m2 | m3 | m4 |
---------------------
""")
      }
    }
  }
}