package io.github.isuzuki.tutorial

import java.io.File

import kantan.csv._
import kantan.csv.ops._

object ToTuple extends App {
  val file = new File(getClass.getResource("/items.csv").getPath)

  file.asCsvReader[(Int, String, String, Option[String], Int)](rfc.withHeader).foreach(println)

  // header row include error
  file.asCsvReader[(Int, String, String, Option[String], Int)](rfc).foreach(println)
}
