package de.htwg.se.Skip_Bo.controller

import de.htwg.se.Skip_Bo.util.Command

class PushCardHelpCommand (i: Int,j:Int,n: Int, controller: Controller) extends Command{

  override def doStep: Unit = controller.game = controller.game.pushCardHelp(i, j, n)

  override def undoStep: Unit = {}

  override def redoStep: Unit = controller.game = controller.game.pushCardHelp(i, j, n)
}
