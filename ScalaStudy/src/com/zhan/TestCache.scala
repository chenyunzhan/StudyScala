package com.zhan


import org.apache.spark.SparkContext
import org.apache.spark.SparkConf


object TestCache {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "C:\\zhan\\run\\hadoop-2.7.0");
    val sc = new SparkContext(new SparkConf().setAppName("TestCache").setMaster("local"))
    val rdd1 = sc.textFile("BigLog.txt", 3)
    
    val start1 = System.currentTimeMillis()
    val rdd2 = rdd1.cache()
    rdd1.count()
    val end1 = System.currentTimeMillis()
    
    
    val start2 = System.currentTimeMillis()
    rdd2.count()
    val end2 = System.currentTimeMillis()
    
    
    println("job1:"+(end1-start1))
    println("job2:"+(end2-start2))
    
    sc.stop()

  }
}