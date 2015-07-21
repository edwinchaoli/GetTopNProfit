package exp


class Server {
  private val quote_price = 5.0 //一口价
  
  private val q_ir = 0.5 //分辨率最低要求
  private val delta_t = 0.5 //实时性最低要求
  private val q_la = 0.5 //准确性最低要求
  
  
  
  
  def get_fixed_quote_price = quote_price

  
  def get_least_recognition = q_ir
  def get_least_time = delta_t
  def get_least_accuracy = q_la
  
  
  
  def get_top_n_client(clients:Array[Client])={
    
  }
  

}