package tn.esprit.persistance;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Dictionnaire", catalog = "troc")
public class Dictionnaire implements Serializable {

	/**
	 * 
	 */
	public Dictionnaire(Integer idDic, String label) {
		super();
		this.idDic = idDic;
		this.label = label;
	}
	public Dictionnaire() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer idDic;	
	private String label;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Integer getIdDic() {
		return idDic;
	}
	public void setIdDic(Integer idDic) {
		this.idDic = idDic;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	

	
	
}
