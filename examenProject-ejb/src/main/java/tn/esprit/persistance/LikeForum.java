package tn.esprit.persistance;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "likeforum", catalog = "troc")
public class LikeForum implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idLikeForum;
	private Forum forum;
	private Swapper swapper;
	private Integer nombre;
	private Date date;
	public LikeForum() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LikeForum(Integer idLikeForum, Forum forum, Swapper swapper, Integer nombre, Date date) {
		super();
		this.idLikeForum = idLikeForum;
		this.forum = forum;
		this.swapper = swapper;
		this.nombre = nombre;
		this.date = date;
	}
	public LikeForum(Forum forum, Swapper swapper, Integer nombre, Date date) {
		super();
		this.forum = forum;
		this.swapper = swapper;
		this.nombre = nombre;
		this.date = date;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Integer getIdLikeForum() {
		return idLikeForum;
	}
	public void setIdLikeForum(Integer idLikeForum) {
		this.idLikeForum = idLikeForum;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "forumID", nullable = false)
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "swapperID", nullable = false)
	public Swapper getSwapper() {
		return swapper;
	}
	public void setSwapper(Swapper swapper) {
		this.swapper = swapper;
	}
	public Integer getNombre() {
		return nombre;
	}
	public void setNombre(Integer nombre) {
		this.nombre = nombre;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false, length = 19)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

}
