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
@Table(name = "forumclaim", catalog = "troc")
public class ForumClaim implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idForumClaim;
	private Forum forum;
	private Swapper swapper;
	private String cause;
	private Date date;
	public ForumClaim(Integer idForumClaim, Forum forum, Swapper swapper, String cause, Date date) {
		super();
		this.idForumClaim = idForumClaim;
		this.forum = forum;
		this.swapper = swapper;
		this.cause = cause;
		this.date = date;
	}
	public ForumClaim(Forum forum, Swapper swapper, String cause, Date date) {
		super();
		this.forum = forum;
		this.swapper = swapper;
		this.cause = cause;
		this.date = date;
	}
	public ForumClaim() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Integer getIdForumClaim() {
		return idForumClaim;
	}
	public void setIdForumClaim(Integer idForumClaim) {
		this.idForumClaim = idForumClaim;
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
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
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
