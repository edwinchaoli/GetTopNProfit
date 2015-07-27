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
    "flow_irs" -> Random.nextDouble(),
    "bandwidth_delta_t" -> 0.5,
    "privacy_d" -> 4,
    "flow_weight" -> 1,
    "bandwidth_weight" -> 1,
    "privacy_weight" -> 1
    
  )
  
  val data_quality:scala.collection.mutable.Map[String,Double] = scala.collection.mutable.Map(
    "recognition" -> 0.0,
    "realtime" -> 0.0,
    "accuracy" -> 0.0
  )
  
  

   private var profit:Double = 0.0
   def Profit = profit
   private var data_value = 0.0
   def DataValue = data_value
  
 
  def flow_cost: Double =
    0.138 * cost_pam("flow_irs")
  
  def bandwidth_cost: Double = { 
    flow_cost / cost_pam("bandwidth_delta_t")
    
  }
      
  def privacy_cost: Double =
    math.pow(math.E, -math.Pi * cost_pam("d") * cost_pam("d") ) * (math.pow(math.E, math.Pi * cost_pam("d") * cost_pam("d") ) - 1) / (math.Pi * cost_pam("d"))

  def cost: Double = {
    cost_pam("flow_weight") * flow_cost + cost_pam("bandwidth_weight") * bandwidth_cost + cost_pam("privacy_weight") * privacy_cost
    
  }

  def compare_least_requirement(q_ir: Double, delta_t: Double, q_la: Double) = {
   
    val delta_1 = 0.5
    val delta_2 = 0.5

    val self_q_ir = 1 - math.pow(delta_1, cost_pam("flow_q_is") / delta_2)
    val self_q_la = 0.5

    data_quality("recognition")=self_q_ir
    

    data_quality("accuracy") = self_q_la

    cost_pam("bandwidth_delta_t") = delta_t
    
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