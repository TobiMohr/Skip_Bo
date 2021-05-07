package de.htwg.se.Skip_Bo.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class BoardSpec extends AnyWordSpec with Matchers {

  "A Board" when{
    "new" should {
      val board = new Board

      "have a String representation" in {
        board.toString() should be("""-------Hilfe---------
| p1 | p2 | p3 | p4 |

| m1 | m2 | m3 | m4 |
---------------------
""")
      }

    }

  }
}
