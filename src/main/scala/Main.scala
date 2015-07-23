package exp

import scala.util._
import com.mongodb.casbah.Imports._
import scala.collection.mutable.ArrayBuffer
import java.io._

import exp._

object Main {

  def exp_1(times: Int) {
    val client = new Client
    val server = new Server(5, 0.1, 0.4, 0.1)
    client.cost_pam("bandwidth_weight") = 0.2
    client.cost_pam("privacy_weight") = 0.2

    var out = printf("[bandwidth_weight = %.3f \tprivacy_weight = %.3f]\n", client.cost_pam("bandwidth_weight"), client.cost_pam("privacy_weight"))
    println(out)

  //  val filename_placeholder = "exp1-bweight=%.3f-pweight=%.3f.txt"
 //   val filename = filename_placeholder.format(client.cost_pam("bandwidth_weight"), client.cost_pam("privacy_weight"))
 //   val exp_out_to_file = new java.io.PrintWriter(filename)

 //   var result_line_placeholder: String = ""
 //   var result_line: String = ""
    for (i <- 0 until times) {
      client.cost_pam("flow_weight") = Random.nextDouble()
        client.compare_least_requirement(server.get_least_recognition, server.get_least_time, server.get_least_accuracy) 

        val profit = if (client.compute_profit_by_fixed_price_policy(server.get_fixed_quote_price) > 0) client.Profit else 0

        out = printf("flow_weight = %.3f\tCost = %.3f\tProfit = %.3f", client.cost_pam("flow_weight"), client.cost, profit)
        println(out)

//        result_line_placeholder = "%.3f\t%.3f"
 //       result_line = result_line_placeholder.format(client.cost_pam("flow_weight"), profit)
 //       exp_out_to_file.println(result_line)

      

    }
  //  exp_out_to_file.close()

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

  def main(args: Array[String]) {
    exp_1(50)

  }
}