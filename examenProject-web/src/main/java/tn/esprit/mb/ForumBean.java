package tn.esprit.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import tn.esprit.interfaces.ForumServiceLocal;
import tn.esprit.persistance.Comments;
import tn.esprit.persistance.Dictionnaire;
import tn.esprit.persistance.Forum;
import tn.esprit.persistance.ForumClaim;
import tn.esprit.persistance.LikeCom;
import tn.esprit.persistance.LikeForum;
import tn.esprit.persistance.Swapper;





@ManagedBean
@ViewScoped
public class ForumBean {

	private List<Forum> forums = new ArrayList<Forum>();
	private List<Forum> forumsperso = new ArrayList<Forum>();
	private List<Comments> comments = new ArrayList<Comments>();
	private Forum forum;
	private Comments comment;
	private ForumClaim frmc;
	private Boolean displayForm;
	private Boolean displayFormCom;
	private Boolean displayCom;
	private Boolean displayButton;
	private Boolean displayClaim;
	private Boolean displayFormDic;
	
	public Boolean getDisplayFormDic() {
		return displayFormDic;
	}


	public void setDisplayFormDic(Boolean displayFormDic) {
		this.displayFormDic = displayFormDic;
	}


	private List<Dictionnaire> dics = new ArrayList<Dictionnaire>();
	private Dictionnaire dic;
	
	private Swapper user;

	@ManagedProperty("#{loginBean}")
	private LoginBean LB;
	
	
	
	
	

	public LoginBean getLB() {
		return LB;
	}


	public void setLB(LoginBean lB) {
		LB = lB;
	}


	public Boolean getDisplayClaim() {
		return displayClaim;
	}


	public void setDisplayClaim(Boolean displayClaim) {
		this.displayClaim = displayClaim;
	}


	public Swapper s= new Swapper(1);
	
	
	
	

	
	public Swapper getS() {
		return s;
	}


	public void setS(Swapper s) {
		this.s = s;
	}


	@EJB
	ForumServiceLocal service;
	
	
	@PostConstruct
	public void init() {
		
		//Swapper s= new Swapper();
		//s.setIdSwapper(1);
		//forum = new Forum();
		//forums = new ArrayList<Forum>();
		forums = service.findAllForums();
		comment= new Comments();
		frmc= new ForumClaim();
		dics=service.findAllWords();
		
			
	}
	
	
public List<Forum> ListPerso() {
		
		//Swapper s= new Swapper();
		//s.setIdSwapper(1);
		//forum = new Forum();
		//forums = new ArrayList<Forum>();
	    user=LB.getUser();
		forumsperso = service.findAllForums2(user.getIdSwapper());
	   //forumsperso = service.findAllForums2(1);
		for (Iterator<Forum> iterator = forumsperso.iterator(); iterator.hasNext();) {
			Forum test2=iterator.next();
		   Long a = service.NombreClaim(test2.getIdForum());
		   if (a>=3){
			   iterator.remove();
		   }
		}
		return forumsperso;
		
	}
	
	
	
	
	@PostConstruct
	public void init2(int id) {
		
		//Swapper s= new Swapper();
		//s.setIdSwapper(1);
		//forum = new Forum();
		user=LB.getUser();
		forums = service.findAllForums2(user.getIdSwapper());
		comment= new Comments();
		
		comments=service.findAllCommentsFrm(id);
		setDisplayCom(true);
		frmc= new ForumClaim();
	}
	
	public void Show(int id) {
		setDisplayCom(true);
		//comments=new ArrayList<Comments>();
		comments=service.findAllCommentsFrm(id);
		init();
	}
	
	public void Show2(int id) {
		setDisplayCom(true);
		
		//comments=new ArrayList<Comments>();
		comments=service.findAllCommentsFrm(id);
		init2(id);
		
		
		
	}
	
	public Long NbCom(int id) {
		
		Long a=service.NombreCom(id);
		return a;
	}
	
	public void doNew() {
		setDisplayForm(true);
		forum=new Forum();
	}
	
	public void doNewCom() {
		setDisplayFormCom(true);
		comment=new Comments();
	}	
	
	public void doNewClaim() {
		setDisplayClaim(true);
		frmc= new ForumClaim();
	}	
	
	public void doNewWord() {
		setDisplayFormDic(true);
		dic= new Dictionnaire();
	}	
	
	public void doAddOrUpdate() {
		Date dateobj = new Date();
		forum.setDate(dateobj);
		user=LB.getUser();
		forum.setSwapper(user);
		service.addOrUpdateForum(forum);
		setDisplayForm(false);
		init();

	}
	
	
	
	public void doAddOrUpdateWord() {
		
		service.addOrUpdateWord(dic);
		setDisplayFormDic(false);
		init();

	}
	
	
	public void doAdd() {
		Date dateobj = new Date();
		comment.setDate(dateobj);
		user=LB.getUser();
		String string = comment.getComment();
		String[] parts = string.split(" ");
		//String part1 = parts[0]; 
		
		dics=service.findAllWords();
		int cp = 0;
		for(int i = 0; i < parts.length; i++)
		{
			//System.out.println("wselna 1");
			
			for (Iterator<Dictionnaire> iterator = dics.iterator(); iterator.hasNext();){
				Dictionnaire d=iterator.next();
				//System.out.println(d.getLabel()+" == "+parts[i]);
				if (parts[i].equals(d.getLabel())){
					//System.out.println("wsel");
					
					
					cp ++;
					
				}
				
			}
			
			
		}
		
		

		
		
		
		
		
		//System.out.println(cp);
		//System.out.println(part1);
		
		
		if(cp==0){
			
			comment.setSwapper(user);
			//Forum frm = new Forum();
			//frm.setIdForum(1);
			//frm.setSwapper(s);
			comment.setForum(forum);
			service.addComment(comment);
			//setDisplayFormCom(false);
			//comment= new Comments();
			//comments=service.findAllCommentsFrm(forum.getIdForum());
			//setDisplayCom(true);
			//init2(forum.getIdForum());
			init();
		}
		
		setDisplayFormCom(false);
	}
	
	
	
	public void doAddClaim() {
		Date dateobj = new Date();
		frmc.setDate(dateobj);
		user=LB.getUser();
		
		frmc.setSwapper(user);
		//Forum frm = new Forum();
		//frm.setIdForum(1);
		//frm.setSwapper(s);
		frmc.setForum(forum);
		
		
		if(service.VerifClaim(frmc.getSwapper().getIdSwapper(), frmc.getForum().getIdForum())==true){
			FacesContext.getCurrentInstance()
			.addMessage("form:btn",
					new FacesMessage("bad credantials"));
			
			
		}
		else{ 
			service.addForumClaim(frmc);
			FacesContext.getCurrentInstance()
			.addMessage("form:btn",
					new FacesMessage("succees"));
			setDisplayClaim(false);
		
			init();
			
		}
		//setDisplayClaim(false);
		//comment= new Comments();
		//comments=service.findAllCommentsFrm(forum.getIdForum());
		//setDisplayCom(true);
		//init2(forum.getIdForum());
		//init();

	}
	
	public void dolike() {
		
		Date dateobj = new Date();
		
		
		LikeForum lf= new LikeForum();
		lf.setDate(dateobj);
		
		user=LB.getUser();
		
		lf.setSwapper(user);
		//Forum frm = new Forum();
		//frm.setIdForum(1);
		//frm.setSwapper(s);
		lf.setForum(forum);
		
		
		if(service.VerifLikeForum(lf.getSwapper().getIdSwapper(),lf.getForum().getIdForum())==true){
			FacesContext.getCurrentInstance()
			.addMessage("form:btn",
					new FacesMessage("already liked by you"));
			
	
		}
		else{ 
			service.addLikeForum(lf);;
			service.liked(forum);
			
		
			init();
			
		}

		
	
		init();

	}
	
	
public void dolikeCom() {
		
	Date dateobj = new Date();
	LikeCom lc = new LikeCom();
	user=LB.getUser();
	lc.setDate(dateobj);
	lc.setSwapper(user);
	lc.setComment(comment);
	
	
	
	
	
	if(service.VerifLikeCom(lc.getSwapper().getIdSwapper(),lc.getComment().getIdComments())==true){
		FacesContext.getCurrentInstance()
		.addMessage("form:btn",
				new FacesMessage("already liked by you"));
		

	}
	else{ 
		service.addLikeComment(lc);
		
		service.likedCom(comment);
		
	
		init();
		
	}

	

		init();

	}
	
     public void dodeleteCom() {
	service.deleteCom(comment);
	//setDisplayForm(false);
	//setDisplayCom(false);
	init();
       }

     public void dodeleteWord() {
 		service.deleteWord(dic);;
 		setDisplayFormDic(false);
 		
 		init();
 	}

	public void dodelete() {
		service.deleteForum(forum);
		setDisplayForm(false);
		setDisplayCom(false);
		init();
	}

	public void doCancel() {
		setDisplayForm(false);
		setDisplayCom(false);
	}
	
	
	
	
public List<Dictionnaire> ListWords() {
		
		//Swapper s= new Swapper();
		//s.setIdSwapper(1);
		//forum = new Forum();
		//forums = new ArrayList<Forum>();
		dics = service.findAllWords();
		
		
		return dics;
		
	}
	
	
	public Boolean veriflike(int s, int f){
		
		if(service.VerifLikeForum(s, f)==true)
			return true;
		else return false;
					
		
		
		
	}
	
	
	
public Boolean veriflikeCom(int s, int c){
		
		if(service.VerifLikeCom(s,c)==true)
			return true;
		else return false;
		
		
	}


	public List<Forum> getForums() {
		return forums;
	}


	public void setForums(List<Forum> forums) {
		this.forums = forums;
	}


	public Forum getForum() {
		return forum;
	}


	public void setForum(Forum forum) {
		this.forum = forum;
	}


	public Boolean getDisplayForm() {
		return displayForm;
	}


	public void setDisplayForm(Boolean displayForm) {
		this.displayForm = displayForm;
	}


	public Boolean getDisplayCom() {
		return displayCom;
	}


	public void setDisplayCom(Boolean displayCom) {
		this.displayCom = displayCom;
	}


	public List<Comments> getComments() {
		comments=service.findAllCommentsFrm(forum.getIdForum());
		return comments;
	}


	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	public Comments getComment() {
		return comment;
	}
	public void setComment(Comments comment) {
		this.comment = comment;
	}
	public Boolean getDisplayFormCom() {
		return displayFormCom;
	}
	public void setDisplayFormCom(Boolean displayFormCom) {
		this.displayFormCom = displayFormCom;
	}


	public Boolean getDisplayButton() {
		return displayButton;
	}


	public void setDisplayButton(Boolean displayButton) {
		this.displayButton = displayButton;
	}


	public ForumClaim getFrmc() {
		return frmc;
	}


	public void setFrmc(ForumClaim frmc) {
		this.frmc = frmc;
	}


	public List<Dictionnaire> getDics() {
		return dics;
	}


	public void setDics(List<Dictionnaire> dics) {
		this.dics = dics;
	}


	public Dictionnaire getDic() {
		return dic;
	}


	public void setDic(Dictionnaire dic) {
		this.dic = dic;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
