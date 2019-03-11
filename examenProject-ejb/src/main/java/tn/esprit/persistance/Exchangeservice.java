package tn.esprit.persistance;
// Generated 19 nov. 2016 13:17:14 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Exchangeservice generated by hbm2java
 */
@Entity
@Table(name = "exchangeservice", catalog = "troc")
public class Exchangeservice implements java.io.Serializable {

	private Integer idExchangeservice;
	private Service serviceByIdserviceOffre;
	private Service serviceByIdExchange;
	private Date dateStart;
	private Date dateFinish;
	private int status;
	private Set<Claim> claims = new HashSet<Claim>(0);
	private static final long serialVersionUID = 1L;

	public Exchangeservice() {
	}

	public Exchangeservice(Service serviceByIdserviceOffre, Service serviceByIdExchange, Date dateStart,
			Date dateFinish, int status) {
		this.serviceByIdserviceOffre = serviceByIdserviceOffre;
		this.serviceByIdExchange = serviceByIdExchange;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		this.status = status;
	}

	public Exchangeservice(Service serviceByIdserviceOffre, Service serviceByIdExchange, Date dateStart,
			Date dateFinish, int status, Set<Claim> claims) {
		this.serviceByIdserviceOffre = serviceByIdserviceOffre;
		this.serviceByIdExchange = serviceByIdExchange;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		this.status = status;
		this.claims = claims;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_exchangeservice", unique = true, nullable = false)
	public Integer getIdExchangeservice() {
		return this.idExchangeservice;
	}

	public void setIdExchangeservice(Integer idExchangeservice) {
		this.idExchangeservice = idExchangeservice;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idserviceOffre", nullable = false)
	public Service getServiceByIdserviceOffre() {
		return this.serviceByIdserviceOffre;
	}

	public void setServiceByIdserviceOffre(Service serviceByIdserviceOffre) {
		this.serviceByIdserviceOffre = serviceByIdserviceOffre;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_exchange", nullable = false)
	public Service getServiceByIdExchange() {
		return this.serviceByIdExchange;
	}

	public void setServiceByIdExchange(Service serviceByIdExchange) {
		this.serviceByIdExchange = serviceByIdExchange;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_start", nullable = false, length = 19)
	public Date getDateStart() {
		return this.dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_finish", nullable = false, length = 19)
	public Date getDateFinish() {
		return this.dateFinish;
	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}

	@Column(name = "status", nullable = false)
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "exchangeservice")
	public Set<Claim> getClaims() {
		return this.claims;
	}

	public void setClaims(Set<Claim> claims) {
		this.claims = claims;
	}

}