package de.htwg.se.Skip_Bo.controller

import de.htwg.se.Skip_Bo.util.Command

class PushCardPlayerCommand (i: Int, n: Int, controller: Controller) extends Command{

  override def doStep: Unit = {controller.game = controller.game.pushCardPlayer(i, n)}

  override def undoStep: Unit = {}

  override def redoStep: Unit = {controller.game = controller.game.pushCardPlayer(i, n)}
}
