package exp

import com.mongodb.casbah.Imports._
import scala.io.Source
import java.text.SimpleDateFormat

import exp._

/**
 * @author Administrator
 */



object ImportData {
  
  val mongoClient =  MongoClient("172.16.20.202",27017)
  val db = mongoClient("sensorsdb")
  val coll = db("taxi")
   
  
  def import_data_from_line(line:String){
    val items = line.split(",")
    if (items.length ==9){
      val num:String = items(0)
      val state:Int = items(2).toInt   
      val timestampFormat = new SimpleDateFormat("yyyyMMddHHmmss")
      val timestamp = timestampFormat.parse(items(3))
      println(timestamp)      
      val gps = Map("lat"->items(4).toFloat,"lon"->items(5).toFloat)
      val speed = items(6).toInt      
      val record = MongoDBObject("num"->num,"state"->state,"timestamp"->timestamp,"gps"->gps,"speed"->speed)      
      coll.insert(record)   
    }

  }
  
  /*
  def main(args:Array[String]){
    val file = Source.fromFile("taxi-test")
    for(line<-file.getLines()){
      import_data_from_line(line)
    }
    println("Import Done!")
  }
  */
  
  
}