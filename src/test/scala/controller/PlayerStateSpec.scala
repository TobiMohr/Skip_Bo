package controller

import de.htwg.se.Skip_Bo.controller.controllerComponent.{PlayerA, PlayerB, PlayerState}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerStateSpec extends AnyWordSpec with Matchers {
  "A PlayerState" when {
    "used" should {
    var playerState:PlayerState = PlayerA
      var playerState2:PlayerState = PlayerB
    "correctly" in {
      playerState.getPlayer should be(0)
      playerState.name should be("A")
      playerState.turnChange should be(PlayerB)
      playerState2.getPlayer should be(1)
      playerState2.name should be("B")
      playerState2.turnChange should be(PlayerA)
    }
    }
  }
}
