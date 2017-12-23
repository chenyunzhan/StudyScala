package com.zhan

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import collection.mutable.Set

object TestBroadcatInsteadJoin {
  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "C:\\zhan\\run\\hadoop-2.7.0");
    val sc = new SparkContext(new SparkConf().setAppName("TestBroadcatInsteadJoin").setMaster("local"))
    val rdd1 = sc.makeRDD(Array((1,1),(3,3)))
    val rdd2 = sc.makeRDD(Array((1,4),(1,2),(2,5),(3,6)))
    val rdd3 = rdd1.join(rdd2)
    rdd3.foreach(x=>{
      println(x)
    })
    
    
    val rdd10 = rdd1.collect()
    
    val broadcast = sc.broadcast(rdd10)
    
    val rdd11 = rdd2.map(x=>{
      val arr = broadcast.value
      var temp = 0
      for(y<-arr) {
        if (x._1==y._1){
          temp = y._2
        }
      }
      if (temp>0) {
        (x._1,(temp,x._2))
      }
    })

    
    rdd11.foreach(x=>{
      println(x)
    })
    
    
    
    sc.stop()
   
  }
}