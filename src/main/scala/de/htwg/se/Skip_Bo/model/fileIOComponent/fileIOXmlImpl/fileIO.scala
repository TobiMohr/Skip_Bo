package de.htwg.se.Skip_Bo.model.fileIOComponent.fileIOXmlImpl

import de.htwg.se.Skip_Bo.model.CardComponent.Card
import de.htwg.se.Skip_Bo.model.GameComponent.GameBaseImpl.Game
import de.htwg.se.Skip_Bo.model.GameComponent.GameInterface
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerInterface
import de.htwg.se.Skip_Bo.model.fileIOComponent.fileIOInterface

import scala.xml.{Elem, PrettyPrinter}

class fileIO extends fileIOInterface {

  override def load: GameInterface = {
    var game: GameInterface = null
    val file = scala.xml.XML.loadFile("game.xml")










    game
  }


  override def save(game: GameInterface): Unit = saveString(game)

  def saveString(game: GameInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("game.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(gameToXml(game))
    pw.write(xml)
    pw.close
  }

  def gameToXml(game: GameInterface): Elem = {

    <game>
      <gstacks>{stacksToXml(game.stack)}</gstacks>
      <players>{playerToXml(game.player)}</players>
      <cardsCovered>{stackToXml(game.cardsCovered)}</cardsCovered>
    </game>

  }

  def stacksToXml(stack: List[List[Card]]): Elem = {
    val stack1 = stack(0)
    val stack2 = stack(1)
    val stack3 = stack(2)
    val stack4 = stack(3)

    <stack>
      <stack1>{
        for(card <- 0 to stack1.size) yield stack1(card)
        }
        </stack1>
      <stack2>{
      for(card <- 0 to stack2.size) yield stack2(card)
      
      }
    </stack2>
      <stack3>{
      for(card <- 0 to stack3.size) yield stack3(card)
      }
    </stack3>
      <stack4>{
      for(card <- 0 to stack4.size) yield stack4(card)
      }
    </stack4>
    </stack>
  }

  def playerToXml(player: List[PlayerInterface]): Elem  = {
    val player1 = player(0)
    val player2 = player(1)

<player>
  <p1>
    <name>{player1.name}</name>
    <cards>{stackToXml(player1.cards)}</cards>
    <helpstacks>{stacksToXml(player1.helpstack)}</helpstacks>
    <stack>{stackToXml(player1.stack)}</stack>
  </p1>
  <p2>
    <name>{player2.name}</name>
    <cards>{stackToXml(player2.cards)}</cards>
    <helpstacks>{stacksToXml(player2.helpstack)}</helpstacks>
    <stack>{stackToXml(player2.stack)}</stack>
  </p2>
</player>
  }
  
  def stackToXml(stack: List[Card]): Elem = {
    <cardsCovered>{
      for (card <- 0 to stack.size) yield stack(card)
      }</cardsCovered>
  }

}
