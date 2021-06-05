package de.htwg.se.Skip_Bo.model


import de.htwg.se.Skip_Bo.model.{Value}

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

    // erstellt Handkarten und Spielerstapel von den Spielern
    val (cards,player) = List("A","B").foldLeft((c,List.empty[Player]))((t,plname)=>{
      val (plcards,cards)= t._1.splitAt(numOfPlayerCards)
      val (plstack,cards2)= cards.splitAt(30)
      val p = Player(name=plname,cards=plcards,stack=plstack)
      (cards2, t._2:+p)
    })

    copy(cardsCovered=cards,player=player)
  }

  //legt Handkarte auf Ablegestapel oder Hilfstapel von Spieler
  //i=Welcher Hilfs- oder Ablagestapel (Index), j=Index Handkarten, n=Spieler, helpst=(true=Hilfsstapel),(false=Ablegestapel)
  def pushCardHand(i: Int,j: Int,n: Int,helpst :Boolean): Try[Game] = {
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

  //legt Karte vom Hilfsstapel auf Ablegestapel
  def pushCardHelp(i: Int,j:Int,n: Int) : Try[Game] ={
    val s = stack(j)
    val p = player(n)
    p.helpCard(i) match {
      case Failure(exception) => Failure(exception)
      case Success((card, newpl)) =>
        if(!checkCardHand(card, s)){
          Failure(InvalidMove)
        }
        val s2 = card +: s
        Success(copy(stack=stack.updated(j, s2), player=player.updated(n,newpl)))
    }
  }

  def pushCardPlayer(i: Int, n: Int): Try[Game] = {
    val s = stack(i)
    val p = player(n)
    p.stackCard() match{
      case Failure(exception) => Failure(exception)
      case Success((card, newpl)) =>
        if(!checkCardHand(card, s)){
          Failure(InvalidMove)
        }
        val s2 = card +: s
        Success(copy(stack=stack.updated(i, s2), player=player.updated(n, newpl)))
    }
  }

  //füllt Karten auf, so dass Spieler A wieder 5 Karten hat
  def pullA() : Game ={
    while(plACards.length<5){
      plACards += Card(cardsCovered.head.value)
      cardsCovered = cardsCovered.drop(1)
    }
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
