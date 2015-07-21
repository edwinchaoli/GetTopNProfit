package exp

/**
 * @author Administrator
 *
 */

import scala.math._
import scala.util._

import scala.util._

class Client {
  
  val node_info:Map[String,String] = Map(
    "num" -> ""    
  
  )
  

  val cost_pam:Map[String,Double] = Map(
   
    "flow_q_is" -> Random.nextDouble(),
    "flow_weight" -> Random.nextDouble(),
    "bandwidth_weight" -> Random.nextDouble(),
    "privacy_weight" -> Random.nextDouble()
    
  )
  
  val data_quality:Map[String,Double] = Map(
    "recognition" -> 0.0,
    "accuracy" -> 0.0
  )
  
  
  
  
  private val flow_w_f = 1.0
 // private val flow_q_is = Random.nextDouble()
  private val flow_w_t = 1.0
  private val flow_w_r = 1.0

  /*
  private val flow_weight = Random.nextDouble()
  private val bandwidth_weight = Random.nextDouble()
  private val privacy_weight = Random.nextDouble()
  */
  
 // private val bandwidth_delta_t = 1.0

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

    val satisfied = (self_q_ir > q_ir)

    satisfied
  }

  def compute_profit_by_fixed_price_policy(quote_price: Double):Double = {
     quote_price - cost
  }
  
  def compute_data_value(client_q_ir:Double,delta_t:Double,client_q_la:Double) = {
    data_value = client_q_ir + delta_t + client_q_la
  }
 

}