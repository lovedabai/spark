package com.icey

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Launch {
  
  def main(args: Array[String]): Unit = {
    
    val conf = new SparkConf().setAppName(Launch.getClass.getSimpleName).set("spark.driver.memory", "1g").setMaster("local");
    val sc = new SparkContext(conf)
    val textFile = sc.textFile("text.txt")
    val words = textFile.flatMap(line => line.split(" "))
    val wordPairs = words.map(word => (word, 1))
    val wordCounts = wordPairs.reduceByKey((a,b) => a + b)
    println("wordCounts: ")
    wordCounts.collect().foreach(println)
  }
}