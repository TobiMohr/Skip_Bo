import de.htwg.se.Skip_Bo.model.CardComponent.{Card, Value}
import de.htwg.se.Skip_Bo.model.GameComponent.GameImpl.Game
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerImpl.Player

import scala.util.Random

def startGame(numOfPlayerCards: Int): Game = {
  //erstellt Kartendeck
  val c = Random.shuffle(Value.values.toList.flatMap(v => {
    val count = v match {
      case Value.Joker => 18
      case _ => 12
    }
    (1 to count).map(_ => Card(v))
  }))


  val (cards,player) = List("A","B").foldLeft((c,List.empty[Player]))((t,plname)=>{
    val (plcards,cards)= t._1.splitAt(numOfPlayerCards)
    val (plstack,cards2)= cards.splitAt(30)
    val p = Player(name=plname,cards=plcards,stack=plstack)
    (cards2, t._2:+p)
  })

  copy(cardsCovered=cards,player=player)
}