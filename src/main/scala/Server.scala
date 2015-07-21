package exp

import scala.collection.mutable.ArrayBuffer

class Server {
  private val quote_price = 5.0 //一口价
  
  private val q_ir = 0.5 //分辨率最低要求
  private val delta_t = 0.5 //实时性最低要求
  private val q_la = 0.5 //准确性最低要求
  
  
  
  
  def get_fixed_quote_price = quote_price

  
  def get_least_recognition = q_ir
  def get_least_time = delta_t
  def get_least_accuracy = q_la
  
  
  
  def get_top_n_client(clients:ArrayBuffer[Client],n:Int):Array[Client]={
    val sorted_clients = clients.sortWith((a,b)=>a.DataValue > b.DataValue)
    val top_array_length = if (n > clients.length) clients.length else n
    val top_n_client = new Array[Client](top_array_length)
    for (i <- 0 until top_array_length){
      top_n_client(i) = sorted_clients(i)
    }
    top_n_client
  }
  

}