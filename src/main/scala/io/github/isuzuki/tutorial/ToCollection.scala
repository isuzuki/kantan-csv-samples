package io.github.isuzuki.tutorial

import java.io.File

import kantan.csv._
import kantan.csv.ops._

object ToCollection extends App {
  val file = new File(getClass.getResource("/nums.csv").getPath)

  file.asCsvReader[List[Float]](rfc).foreach(println)

  // read first column only
  file.asCsvReader[Float](rfc).foreach(println)

  // first and second row read error
  file.asCsvReader[List[Int]](rfc).foreach(println)
}
