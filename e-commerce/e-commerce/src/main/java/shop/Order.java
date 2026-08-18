package shop;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name="order")
public class Order {
	
	public Order() {
		
	}
	
	@Id
	@GeneratedValue
	private Integer orderId;
	
	private BigDecimal total;

	public Order(Integer orderId, BigDecimal total) {
		super();
		this.orderId = orderId;
		this.total = total;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", total=" + total + "]";
	}
	
	

}
