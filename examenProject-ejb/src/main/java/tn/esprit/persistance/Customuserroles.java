package tn.esprit.persistance;
// Generated 19 nov. 2016 13:17:14 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Customuserroles generated by hbm2java
 */
@Entity
@Table(name = "customuserroles", catalog = "troc")
public class Customuserroles implements java.io.Serializable {

	private Integer id;
	private Customroles customroles;
	private Swapper swapper;
	private int userId;
	private int roleId;
	private static final long serialVersionUID = 1L;

	public Customuserroles() {
	}

	public Customuserroles(int userId, int roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public Customuserroles(Customroles customroles, Swapper swapper, int userId, int roleId) {
		this.customroles = customroles;
		this.swapper = swapper;
		this.userId = userId;
		this.roleId = roleId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CustomRole_Id")
	public Customroles getCustomroles() {
		return this.customroles;
	}

	public void setCustomroles(Customroles customroles) {
		this.customroles = customroles;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "swapper_Id")
	public Swapper getSwapper() {
		return this.swapper;
	}

	public void setSwapper(Swapper swapper) {
		this.swapper = swapper;
	}

	@Column(name = "UserId", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "RoleId", nullable = false)
	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}