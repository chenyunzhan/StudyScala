package com.zhan

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object FilterMostCompany {
  
  def main(args: Array[String]): Unit = {
    
    System.setProperty("hadoop.home.dir", "C:\\zhan\\run\\hadoop-2.7.0");

    
    val sc = new SparkContext(new SparkConf().setAppName("FilterMostCompany").setMaster("local"))
    
    val rdd1 = sc.textFile("log.txt", 3)
    val rdd2 = rdd1.map(x=>{
      x.split("\t")(1)
    })
    val rdd3 = rdd2.map(x=>{
      (x,1)
    })
    val rdd4 = rdd3.reduceByKey((x,y)=>{
      x+y
    })
    val rdd5 = rdd4.map((x)=>{
      (x._2,x._1)
    })
    val rdd6 = rdd5.sortByKey(false, 3)
    val arr = rdd6.take(1)
    val mostCompany = arr(0)._2
    
    
    println("mostCompany:" + mostCompany)

    
    val rdd7 = rdd1.filter(x=>{
      !mostCompany.equals(x.split("\t")(1))
    })
    rdd7.foreach(x=>{
      println(x)
    })
  }
  
}