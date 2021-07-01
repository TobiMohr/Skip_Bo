import com.google.inject.Inject
import com.google.inject.name.Named
import de.htwg.se.Skip_Bo.model.CardComponent.{Card, Value}
import de.htwg.se.Skip_Bo.model.{CardComponent, InvalidHandCard, InvalidMove}
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerBaseImpl.Player
import de.htwg.se.Skip_Bo.model.PlayerComponent.PlayerInterface
import de.htwg.se.Skip_Bo.util.Util

import scala.util.{Failure, Success, Try}

case class Player @Inject() (name: String,
                             @Named("cards") cards: List[Card],
                             @Named("stacks") helpstack: List[List[Card]],
                             @Named("cards") stack: List[Card]) extends PlayerInterface {

  def getCard(int: Int): Try[(Card, Player)] = {
    if (int >= cards.size) {
      Failure(InvalidHandCard(int))
    } else {
      val card = cards(int)
      val x = Util.listRemoveAt(cards, int)
      Success(card, copy(cards = x))
    }
  }

  def stackCard(): (Card, Player) = {
    val card = stack.head
    val x = Util.listRemoveAt(stack, 0)
    (card, copy(stack = x))
  }

  def helpCard(int: Int): Try[(Card, Player)] = {
    if (helpstack(int).isEmpty) {
      Failure(InvalidMove)
    } else {
      val card = helpstack(int).head
      val x = Util.listRemoveAt(helpstack(int), 0)
      Success(card, copy(helpstack = helpstack.updated(int, x)))
    }
  }

  def putInHelp(int: Int, card: Card): Player = {
    val s2 = card +: helpstack(int)
    copy(helpstack = helpstack.updated(int, s2))
  }

  def draw2(card: List[Card]): Player = {
    val s2 = card ++ cards
    copy(cards = s2)
  }
}

val player = Player("Your Name", List(CardComponent.Card(Value.Eight), CardComponent.Card(Value.Five), CardComponent.Card(Value.Joker),
  CardComponent.Card(Value.Seven), CardComponent.Card(Value.Six)),
  List(List(CardComponent.Card(Value.Four)), Nil, Nil, Nil),
  List(CardComponent.Card(Value.Five), CardComponent.Card(Value.Seven), CardComponent.Card(Value.Joker), CardComponent.Card(Value.Six),
    CardComponent.Card(Value.Eight), CardComponent.Card(Value.Twelve), CardComponent.Card(Value.Two), CardComponent.Card(Value.Seven)))

player.getCard(1)