package exp

import scala.collection.mutable.ArrayBuffer



/**
 * @param quote_price 一口价
 * @param q_ir 分辨率最低要求
 * @param delta_t 实时性最低要求
 * @param q_la  准确性最低要求*
 *
 */
class Server(quote_price:Double,q_ir:Double,delta_t:Double,q_la:Double) {
 
  def get_fixed_quote_price = quote_price

  def get_least_recognition = q_ir
  def get_least_time = delta_t
  def get_least_accuracy = q_la
  
  var value_sum = 0.0
  
  def get_top_n_client(clients:ArrayBuffer[Client],n:Int):Array[Client]={
    val sorted_clients = clients.sortWith((a,b)=>a.DataValue > b.DataValue)
    val top_array_length = if (n > clients.length) clients.length else n
    val top_n_client = new Array[Client](top_array_length)
    for (i <- 0 until top_array_length){
      top_n_client(i) = sorted_clients(i)
      sorted_clients(i).cost_pam("total_flow") -= sorted_clients(i).flow_cost
      value_sum += sorted_clients(i).DataValue
    }
    top_n_client
  }
  

}