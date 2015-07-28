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
    "total_flow" -> 0.0, 
    "flow_irs" -> 10,
    "bandwidth_tin" -> 0.5,
    "bandwidth_threshold_tin" -> 1,
    
    "privacy_lamda" -> 0.01,
    "privacy_cp" -> 10,
    "privacy_d" -> 20,
    "privacy_threshold_d" -> 40,
    "flow_weight" -> 1,
    "bandwidth_weight" -> 1,
    "privacy_weight" -> 1,
    "cost_theta" -> 1
    
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
   
   var can_upload = false
   
  
 
  def flow_cost: Double =
    0.138 * cost_pam("flow_irs")
  
  def bandwidth_cost: Double = { 
    flow_cost / cost_pam("bandwidth_tin")
    
  }
      
  def privacy_cost: Double =
    cost_pam("privacy_cp") * math.pow(math.E, -math.Pi * cost_pam("privacy_lamda") *  cost_pam("privacy_d") * cost_pam("privacy_d") ) * (math.pow(math.E, math.Pi * cost_pam("privacy_lamda") *  cost_pam("privacy_d") * cost_pam("privacy_d") ) - 1) / (math.Pi * cost_pam("privacy_lamda") * cost_pam("privacy_d") * cost_pam("privacy_d"))

  def cost: Double = {
    (cost_pam("flow_weight") * flow_cost + cost_pam("bandwidth_weight") * bandwidth_cost + cost_pam("privacy_weight") * privacy_cost)/cost_pam("cost_theta")
  }
  
  
  def recognition_quality:Double = {
    val delta_1 = 0.9997
    val delta_2 = 0.3097
    data_quality("recognition_quality") = 1 - math.pow(delta_1, 1000000 * cost_pam("flow_irs") / delta_2)
    data_quality("recognition_quality")
  }
  
  
  def realtime_quality:Double = {
    data_quality("realtime") = 1 - cost_pam("bandwidth_tin") / cost_pam("bandwidth_threshold_tin")
    data_quality("realtime")
  }
  
  def privacy_quality:Double = {
    data_quality("accuracy") = 1 - cost_pam("privacy_d") / cost_pam("privacy_threshold_d")
    data_quality("accuracy") 
  }
    
  

  def compare_least_requirement(q_ir: Double, q_rt: Double, q_la: Double) = {
    val satisfied = (recognition_quality >= q_ir &&  realtime_quality >= q_rt &&  privacy_quality >= q_la )
    satisfied
  }

  def compute_profit_by_fixed_price_policy(quote_price: Double):Double = {
      profit = quote_price - cost
      profit
     
  }
  
  def compute_data_value:Double = {
    data_value = math.log(1 + recognition_quality) + math.log(1 + realtime_quality)+ math.log(1 +privacy_quality)
    data_value
  }
 

}