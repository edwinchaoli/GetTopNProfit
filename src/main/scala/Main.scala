package exp


import scala.util.Random._
import com.mongodb.casbah.Imports._
import scala.collection.mutable.ArrayBuffer

import exp._

object Main {

  def main(args: Array[String]) {
    val server = new Server
    val clients = new ArrayBuffer[Client]()

    for (i <- 0 until 5) {
      
      val client = new Client
      client.node_info.updated("num", i.toString())
      if (client.compare_least_requirement(server.get_least_recognition, server.get_least_time, server.get_least_accuracy)){
         if (client.compute_profit_by_fixed_price_policy(server.get_fixed_quote_price) > 0 ){
           clients += client
         }
      }
      server.get_top_n_client(clients)
        
     
    }

   


  }
}