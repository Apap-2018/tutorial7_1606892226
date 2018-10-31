package com.apap.tutorial7.model;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="car")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CarModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement , identity : sry
	private long id;

	@NotNull
	@Size(max = 50)
	@Column(name = "brand", nullable = false)
	private String brand;

	@NotNull
	@Size(max = 50)
	@Column(name = "type", nullable = false, unique = true)
	private String type;

	@NotNull
	@Column(name = "price", nullable = false)
	private String price;

	@NotNull
	@Column(name = "amount", nullable = false)
	private String amount;

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "dealer_id", referencedColumnName = "id", nullable = false)
	@OnDelete(action =OnDeleteAction.NO_ACTION)
	private DealerModel dealer;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public DealerModel getDealer() {
		return dealer;
	}

	public void setDealer(DealerModel dealer) {
		this.dealer = dealer;
	}

	public void setAmount(int parseInt) {
		// TODO Auto-generated method stub

	}

	public void setPrice(Long valueOf) {
		// TODO Auto-generated method stub

	}



}