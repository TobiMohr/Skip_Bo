import de.htwg.se.Skip_Bo.model.{Card, Colour}


case class Stack(List: Card) {

  val names: List[Card] = Nil
  def test: Unit = {
    for (name <- names) {
      println(name.toString)
    }
  }}