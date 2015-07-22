package exp

/**
 * @author Administrator
 *
 */

import scala.math._

import scala.util._

import scala.collection.mutable.Map

class Client {
  
  val node_info:scala.collection.mutable.Map[String,String] = scala.collection.mutable.Map(
    "num" -> ""     
  )
  

  val cost_pam:scala.collection.mutable.Map[String,Double] = scala.collection.mutable.Map(
    "flow_q_is" -> Random.nextDouble(),
    "bandwidth_delta_t" -> 0.0,
    "flow_weight" -> Random.nextDouble(),
    "bandwidth_weight" -> Random.nextDouble(),
    "privacy_weight" -> Random.nextDouble()
    
  )
  
  val data_quality:scala.collection.mutable.Map[String,Double] = scala.collection.mutable.Map(
    "recognition" -> 0.0,
    "realtime" -> cost_pam("bandwidth_delta_t"),
    "accuracy" -> 0.0
  )
  
  
  
  
  private val flow_w_f = 1.0
  private val flow_w_t = 1.0
  private val flow_w_r = 1.0


   private var profit:Double = 0.0
   def Profit = profit
   private var data_value = 0.0
   def DataValue = data_value
  
 
  def flow_cost: Double =
    math.pow((flow_w_f * cost_pam("flow_q_is") + flow_w_t), flow_w_r)
  
  val bandwidth_cost: Double =
    flow_cost / cost_pam("bandwidth_delta_t")

  val privacy_cost: Double =
    1.0

  def cost: Double = {
    cost_pam("flow_weight") * flow_cost + cost_pam("bandwidth_weight") * bandwidth_cost
    cost_pam("privacy_weight") * privacy_cost
  }

  def compare_least_requirement(q_ir: Double, delta_t: Double, q_la: Double) = {
    val delta_1 = 0.5
    val delta_2 = 0.5

    val self_q_ir = 1 - math.pow(delta_1, cost_pam("flow_q_is") / delta_2)
    val self_q_la = 0.5

    data_quality("recognition")=self_q_ir
    

    data_quality("accuracy") = self_q_la

    cost_pam("bandwidth_delta_t") = delta_t

    println("self_q_ir = " + self_q_ir +  ", q_ir = " + q_ir + ", self_q_la = " + self_q_la + ", q_la = " + q_la)
    val satisfied = (self_q_ir >= q_ir && self_q_la >= q_la)

    satisfied
  }

  def compute_profit_by_fixed_price_policy(quote_price: Double):Double = {
      profit = quote_price - cost
      profit
  }
  
  def compute_data_value = {
    data_value = data_quality("recognition") + data_quality("realtime") + data_quality("accuracy")
  }
 

}