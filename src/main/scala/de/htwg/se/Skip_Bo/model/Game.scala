package de.htwg.se.Skip_Bo.model

import scala.collection.mutable.ListBuffer
import de.htwg.se.Skip_Bo.model.{Colour, Value}

import scala.util.{Failure, Random, Success, Try}

case class Game( stack:List[List[Card]] = (0 until 4).map(_=>List.empty).toList,
                 helpstack:List[List[Card]] = (0 until 4).map(_=>List.empty).toList,
                 player:List[Player] = List.empty,
                 cardsCovered:List[Card] = List.empty
               ) {

  //baut Grundspiel auf
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

  //legt Karte auf Ablegestapel von Spieler A
  def pushCardHand1A(i: Int,j: Int,n: Int,helpst :Boolean): Try[Game] = {
    val s = if(helpst) helpstack(i) else stack(i)
    val p = player(n)
    p.getCard(j) match{
      case Failure(exception) => Failure(exception)
      case Success((card, newpl))=>
        if(!checkCardHand(card,s))  return Failure(InvalidMove)
        val s2 = card +: s
        Success(if(helpst)
          copy(helpstack=helpstack.updated(i,s2), player=player.updated(n,newpl))
        else copy(stack=stack.updated(i,s2), player=player.updated(n,newpl)))
    }
  }

  //legt Karte vom Spielerstapel auf Ablegestapel ab
  def pushCardStapel1A(): Game = {
    stack1 = plAstack.head +: stack1
    plAstack = plAstack.drop(1)
    this
  }
  def pushCardStapel2A(): Game = {
    stack2 = plAstack.head +: stack2
    plAstack = plAstack.drop(1)
    this
  }
  def pushCardStapel3A(): Game = {
    stack3 = plAstack.head +: stack3
    plAstack = plAstack.drop(1)
    this
  }
  def pushCardStapel4A(): Game = {
    stack4 = plAstack.head +: stack4
    plAstack = plAstack.drop(1)
    this
  }

  //legt Karte von Hilfstapel auf Ablegestapel
  def pushCardH1A1A(): Game = {
    stack1 = helpAstack1.head +: stack1
    helpAstack1 = helpAstack1.drop(1)
    this
  }
  def pushCardH1A2A(): Game = {
    stack2 = helpAstack1.head +: stack2
    helpAstack1 = helpAstack1.drop(1)
    this
  }
  def pushCardH1A3A(): Game = {
    stack3 = helpAstack1.head +: stack3
    helpAstack1 = helpAstack1.drop(1)
    this
  }
  def pushCardH1A4A(): Game = {
    stack4 = helpAstack1.head +: stack4
    helpAstack1 = helpAstack1.drop(1)
    this
  }
  def pushCardH2A1A(): Game = {
    stack1 = helpAstack2.head +: stack1
    helpAstack1 = helpAstack2.drop(1)
    this
  }
  def pushCardH2A2A(): Game = {
    stack2 = helpAstack2.head +: stack2
    helpAstack1 = helpAstack2.drop(1)
    this
  }
  def pushCardH2A3A(): Game = {
    stack3 = helpAstack2.head +: stack3
    helpAstack1 = helpAstack2.drop(1)
    this
  }
  def pushCardH2A4A(): Game = {
    stack4 = helpAstack2.head +: stack4
    helpAstack1 = helpAstack2.drop(1)
    this
  }
  def pushCardH3A1A(): Game = {
    stack1 = helpAstack3.head +: stack1
    helpAstack1 = helpAstack3.drop(1)
    this
  }
  def pushCardH3A2A(): Game = {
    stack2 = helpAstack3.head +: stack2
    helpAstack1 = helpAstack3.drop(1)
    this
  }
  def pushCardH3A3A(): Game = {
    stack3 = helpAstack3.head +: stack3
    helpAstack1 = helpAstack3.drop(1)
    this
  }
  def pushCardH3A4A(): Game = {
    stack4 = helpAstack3.head +: stack4
    helpAstack1 = helpAstack3.drop(1)
    this
  }
  def pushCardH4A1A(): Game = {
    stack1 = helpAstack4.head +: stack1
    helpAstack1 = helpAstack4.drop(1)
    this
  }
  def pushCardH4A2A(): Game = {
    stack2 = helpAstack4.head +: stack2
    helpAstack1 = helpAstack4.drop(1)
    this
  }
  def pushCardH4A3A(): Game = {
    stack3 = helpAstack4.head +: stack3
    helpAstack1 = helpAstack4.drop(1)
    this
  }
  def pushCardH4A4A(): Game = {
    stack4 = helpAstack4.head +: stack4
    helpAstack1 = helpAstack4.drop(1)
    this
  }

  //füllt Karten auf, so dass Spieler A wieder 5 Karten hat
  def pullA() : Game ={
    while(plACards.length<5){
      plACards += Card(cardsCovered.head.value)
      cardsCovered = cardsCovered.drop(1)
    }
    this
  }


  //legt Karte auf Ablegestapel von Spieler B
  def pushCard1B(int: Int): Game = {
        stack1 = plBCards(int) +: stack1
        plBCards = plBCards.take(int) ++ plBCards.drop(int + 1)
    this
  }

  def pushCard2B(int: Int): Game = {
        stack2 = plBCards(int) +: stack2
        plBCards = plBCards.take(int) ++ plBCards.drop(int + 1)
    this
  }

  def pushCard3B(int: Int): Game = {
        stack3 = plBCards(int) +: stack3
        plBCards = plBCards.take(int) ++ plBCards.drop(int + 1)
    this
  }

  def pushCard4B(int: Int): Game = {
        stack4 = plBCards(int) +: stack4
        plBCards = plBCards.take(int) ++ plBCards.drop(int + 1)
    this
  }

  //legt Karte auf Hilfsstapel von Spieler B
  def ablegen1B(stapel: Int): Game={
    helpBstack1 = plBCards(stapel) +: helpBstack1
    plBCards = plBCards.take(stapel) ++ plBCards.drop(stapel + 1)
    this
  }

  def ablegen2B(stapel: Int): Game={
    helpBstack2 = plBCards(stapel) +: helpBstack1
    plBCards = plBCards.take(stapel) ++ plBCards.drop(stapel + 1)
    this
  }

  def ablegen3B(stapel: Int): Game={
    helpBstack3 = plBCards(stapel) +: helpBstack1
    plACards = plACards.take(stapel) ++ plACards.drop(stapel + 1)
    this
  }

  def ablegen4B(stapel: Int): Game={
    helpBstack4 = plBCards(stapel) +: helpBstack1
    plBCards = plBCards.take(stapel) ++ plBCards.drop(stapel + 1)
    this
  }

  //füllt Karten auf, so dass Spieler A wieder 5 Karten hat
  def pullB() : Game ={
    while(plBCards.length<5){
      plBCards += Card(cardsCovered.head.value)
      cardsCovered = cardsCovered.drop(1)
    }
    this
  }

  //check Karte ob erlaubt zu legen
  def checkCardHand(card: Card, stack: List[Card]): Boolean={
    if(plACards(i).toString != "J") {
      if (((plACards(i).toString.toInt) - 1 == stack1.head.toString.toInt) || ((plACards(i).toString.toInt) - 1 == stack2.head.toString.toInt)
        || ((plACards(i).toString.toInt) - 1 == stack3.head.toString.toInt) || ((plACards(i).toString.toInt) - 1 == stack4.head.toString.toInt)) {
        return true;
      }
    }
    if(plACards(i).toString == "J") {
     return true
    }
    false
  }

  override def toString: String = {

    val l = for (i <- 1 to plACards.length) yield
        ("| " + plACards(i - 1).toString + " | ")
    val b = ("| " + helpAstack1.head.toString + " | ")
    val c = ("| " + helpAstack2.head.toString + " | ")
    val d = ("| " + helpAstack3.head.toString + " | ")
    val e = ("| " + helpAstack4.head.toString + " | ")
    val f = ("| " + plAstack.head.toString + " | ")
    val g = ("| " + stack1.head.toString + " | ")
    val h = ("| " + stack2.head.toString + " | ")
    val j = ("| " + stack3.head.toString + " | ")
    val k = ("| " + stack4.head.toString + " | ")

    val playField = l + "\n\n" + b + "\t" + c + "\t" + d + "\t" + e + "\t" + f + "\n\n" + g + "\t" +
      h + "\t" + j + "\t" + k + "\t"

    playField
  }
}
