name := "kantan-csv-samples"

version := "0.1"

scalaVersion := "2.12.7"

val kantanCsvVersion = "0.5.0"

libraryDependencies ++= Seq(
  "com.nrinaudo" %% "kantan.csv" % kantanCsvVersion,
  "com.nrinaudo" %% "kantan.csv-enumeratum" % kantanCsvVersion,
  "com.nrinaudo" %% "kantan.csv-generic" % kantanCsvVersion
)