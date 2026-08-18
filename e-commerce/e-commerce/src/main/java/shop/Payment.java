package shop;

import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity(name="payment")
public class Payment {
	
	public Payment() {
		
	}
	
	@Id
	private Integer orderId;
	
	private String status;
	
	private BigDecimal amount;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "order_id", nullable = false)
    private Order order;

	public Payment(Integer orderId, String status, BigDecimal amount, Order order) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.amount = amount;
		this.order = order;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Payment [orderId=" + orderId + ", status=" + status + ", amount=" + amount + ", order=" + order + "]";
	}

}
