

/**
 * @author Administrator

 */

import scala.math._
import scala.util._

class Cost(flow_weight:Double,bandwidth_weight:Double,privacy_weight:Double) {
  private val flow_w_f = 1.0
  private val flow_q_is = 1.0
  private val flow_w_t = 1.0
  private val flow_w_r = 1.0
  
  private val bandwidth_delta_t = 1.0
  
  
  def flow_cost:Double = 
    math.pow((flow_w_f * flow_q_is + flow_w_t),flow_w_r)   
  
  
  val bandwidth_cost:Double = 
    flow_cost/bandwidth_delta_t  
  
  
  val privacy_cost:Double = 
    1.0
  
    
  def cost():Double = {
    flow_weight * flow_cost + bandwidth_weight * bandwidth_cost 
    privacy_weight * privacy_cost
  }
  
  def compute_profile_policy_fixed_price(quote_price:Double) = {
    quote_price - cost()
  }
  
  
  
}