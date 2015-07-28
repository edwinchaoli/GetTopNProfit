package exp

import scala.util._
import com.mongodb.casbah.Imports._
import scala.collection.mutable.ArrayBuffer
import java.io._

import exp._

object Main {

  def exp_1(times: Int) {
    val client = new Client
    val server = new Server(100, 0.5, 0.5, 0.5)

    val filename = "exp1.txt"
    val exp_out_to_file = new java.io.PrintWriter(filename)

    var result_line_placeholder: String = ""
    var result_line: String = ""

    for (i <- 0 until times) {
      client.cost_pam("cost_theta") = Random.nextDouble()
      client.compare_least_requirement(server.get_least_recognition, server.get_least_time, server.get_least_accuracy)

      val profit = if (client.compute_profit_by_fixed_price_policy(server.get_fixed_quote_price) > 0) client.Profit else 0

      var out = printf("cost_theta = %.3f\tCost = %.3f\tProfit = %.3f", client.cost_pam("cost_theta"), client.cost, profit)
      println(out)

      result_line_placeholder = "%.3f\t%.3f"
      result_line = result_line_placeholder.format(client.cost_pam("cost_theta"),profit)
      exp_out_to_file.println(result_line)

    }
    exp_out_to_file.close()

  }

  def exp_2(filname: String) {
    val server = new Server(5.0, 0.5, 0.5, 0.5)
    val clients = new ArrayBuffer[Client]()

    for (i <- 0 until 5) {
      val client = new Client
      client.node_info("num") = i.toString()

      if (client.compare_least_requirement(server.get_least_recognition, server.get_least_time, server.get_least_accuracy)) {
        if (client.compute_profit_by_fixed_price_policy(server.get_fixed_quote_price) > 0) {
          client.compute_data_value

          clients += client
        }
      }

    }
    val top_n_client = server.get_top_n_client(clients, 4)

    top_n_client.foreach { client => println(client.node_info("num") + ", datavalue = " + client.DataValue + " profit = " + client.Profit) }

  }

  def exp_3_1(flow_acount:Double){
    val client = new Client
    val server = new Server(100, 0.5, 0.5, 0.5)
    
    
    client.cost_pam("privacy_d") =  20 + 10 * Random.nextDouble()
    client.cost_pam("bandwidth_tin") =  0.05 + Random.nextDouble()
    val filename = "exp3_1.txt"
    val exp_out_to_file = new java.io.PrintWriter(filename)

    var result_line_placeholder: String = ""
    var result_line: String = ""
    
    val client_share_times:Int = (flow_acount / client.flow_cost).toInt
    var data_value_sum = 0.0
    
    for (i <- 1 to client_share_times){
      client.cost_pam("privacy_d") =  Random.nextDouble()
      client.cost_pam("bandwidth_tin") =  Random.nextDouble()
      
      data_value_sum += client.compute_data_value
      var out = printf("privacy_d = %.3f\tbandwidth_tin = %.3f\tcompute_data_value = %.3f\tdata_value_sum = %.3f",  client.cost_pam("privacy_d"), client.cost_pam("bandwidth_tin"), client.DataValue, data_value_sum)
      println(out)
      result_line_placeholder = "%d\t%.3f"
      result_line = result_line_placeholder.format(i,data_value_sum)
      exp_out_to_file.println(result_line)
    }
    
      exp_out_to_file.close()

    
    
    
    
    
    
    
  }
  
  def exp_3_2(client_count:Int,flow_acount:Double){
    val server = new Server(100, 0.5, 0.5, 0.5)
    val clients = new ArrayBuffer[Client]()
    for (i <- 0 until client_count) {
      val client = new Client
      client.cost_pam("total_flow") = flow_acount
      client.cost_pam("privacy_d") =  20 + 10 * Random.nextDouble()
      client.cost_pam("bandwidth_tin") =  0.05 + Random.nextDouble()
      
      if (client.compare_least_requirement(server.get_least_recognition, server.get_least_time, server.get_least_accuracy)) {
        if (client.compute_profit_by_fixed_price_policy(server.get_fixed_quote_price) > 0) {
          client.can_upload = true
          client.compute_data_value
          clients += client
        }
      }
    }
    var data_value_sum = 0.0
    val live_clients = new ArrayBuffer[Client]()
    clients.foreach { client => if ((client.cost_pam("total_flow")/client.flow_cost >= 1) && (client.can_upload == true)  ) live_clients += client }
    while(live_clients.length == 0){
       val top_n_client = server.get_top_n_client(live_clients, 3)

       top_n_client.foreach { client => println(client.node_info("num") + ", datavalue = " + client.DataValue + " profit = " + client.Profit) }
    }
      
      

    
  }
  
  
  def main(args: Array[String]) {
   // exp_1(500)
    exp_3_1(30)

  }
}