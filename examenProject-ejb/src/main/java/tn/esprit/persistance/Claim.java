package tn.esprit.persistance;
// Generated 19 nov. 2016 13:17:14 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Claim generated by hbm2java
 */
@Entity
@Table(name = "claim", catalog = "troc")
public class Claim implements java.io.Serializable {

	private Integer idClaim;
	private Exchangeservice exchangeservice;
	private Swapper swapper;
	private String cause;
	private String description;
	private Date date;
	private String status;
	private static final long serialVersionUID = 1L;

	public Claim() {
	}

	public Claim(Exchangeservice exchangeservice, Swapper swapper, Date date) {
		this.exchangeservice = exchangeservice;
		this.swapper = swapper;
		this.date = date;
	}

	public Claim(Exchangeservice exchangeservice, Swapper swapper, String cause, String description, Date date,
			String status) {
		this.exchangeservice = exchangeservice;
		this.swapper = swapper;
		this.cause = cause;
		this.description = description;
		this.date = date;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_claim", unique = true, nullable = false)
	public Integer getIdClaim() {
		return this.idClaim;
	}

	public void setIdClaim(Integer idClaim) {
		this.idClaim = idClaim;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exchangeserviceID", nullable = false)
	public Exchangeservice getExchangeservice() {
		return this.exchangeservice;
	}

	public void setExchangeservice(Exchangeservice exchangeservice) {
		this.exchangeservice = exchangeservice;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "swapperID", nullable = false)
	public Swapper getSwapper() {
		return this.swapper;
	}

	public void setSwapper(Swapper swapper) {
		this.swapper = swapper;
	}

	@Column(name = "cause")
	public String getCause() {
		return this.cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false, length = 19)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "status")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}