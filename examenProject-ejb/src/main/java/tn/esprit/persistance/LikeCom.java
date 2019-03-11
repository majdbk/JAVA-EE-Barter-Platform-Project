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
@Table(name = "likecom", catalog = "troc")
public class LikeCom implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idLikeCom;
	private Comments comment;
	private Swapper swapper;
	private Date date;
	public LikeCom() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LikeCom(Comments comment, Swapper swapper, Date date) {
		super();
		this.comment = comment;
		this.swapper = swapper;
		this.date = date;
	}
	public LikeCom(Integer idLikeCom, Comments comment, Swapper swapper, Date date) {
		super();
		this.idLikeCom = idLikeCom;
		this.comment = comment;
		this.swapper = swapper;
		this.date = date;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Integer getIdLikeCom() {
		return idLikeCom;
	}
	public void setIdLikeCom(Integer idLikeCom) {
		this.idLikeCom = idLikeCom;
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "commentID", nullable = false)
	public Comments getComment() {
		return comment;
	}
	public void setComment(Comments comment) {
		this.comment = comment;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "swapperID", nullable = false)
	public Swapper getSwapper() {
		return swapper;
	}
	public void setSwapper(Swapper swapper) {
		this.swapper = swapper;
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
