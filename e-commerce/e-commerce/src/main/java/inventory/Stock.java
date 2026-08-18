package inventory;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity(name="stock")
public class Stock {
	
	@Id
	private Integer productId;
	
	private Integer available;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	public Stock(Integer productId, Integer available) {
		super();
		this.productId = productId;
		this.available = available;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Stock [productId=" + productId + ", available=" + available + ", product=" + product + "]";
	}

}
