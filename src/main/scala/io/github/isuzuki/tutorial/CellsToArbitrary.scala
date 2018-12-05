package io.github.isuzuki.tutorial

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import kantan.csv._
import kantan.csv.generic._
import kantan.csv.ops._

object CellsToArbitrary extends App {
  lazy val format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")

  case class Item(
    id: Int,
    name: String,
    category: String,
    subCategory: Option[String],
    price: Price,
    startAt: Option[LocalDateTime]
  )

  implicit val intCellDecoder: CellDecoder[Int] =
    CellDecoder.from(s => DecodeResult(s.toInt + 12345))

  implicit val localDateTimeCellDecoder: CellDecoder[LocalDateTime] =
    CellDecoder.from(s => DecodeResult(LocalDateTime.parse(s, format)))

  case class Price(value: Int) extends AnyVal
  object Price {
    implicit val priceCellDecoder: CellDecoder[Price] =
      CellDecoder.from(s => DecodeResult(Price(s.toInt + 1000)))
  }

  val file = new File(getClass.getResource("/items.csv").getPath)
  file.asCsvReader[Item](rfc.withHeader).foreach(println)
}
