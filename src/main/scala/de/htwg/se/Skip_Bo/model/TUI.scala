package de.htwg.se.Skip_Bo.model

class TUI {
  while(true) {
    val scanner = new java.util.Scanner(System.in)
    val line = scanner.nextLine
    line match {
      case "p1" => println("lege Karte auf 1. Spieler Stack")
      case "p2" => println("lege Karte auf 2. Spieler Stack")
      case "p3" => println("lege Karte auf 3. Spieler Stack")
      case "p4" => println("lege Karte auf 4. Spieler Stack")
      case "m1" => println("lege Karte auf 1. Mittel Stack")
      case "m2" => println("lege Karte auf 2. Mittel Stack")
      case "m3" => println("lege Karte auf 3. Mittel Stack")
      case "m4" => println("lege Karte auf 4. Mittel Stack")
    }
  }
}
