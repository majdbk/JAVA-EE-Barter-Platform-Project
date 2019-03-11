package tn.esprit.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.interfaces.ForumServiceLocal;
import tn.esprit.persistance.Comments;
import tn.esprit.persistance.Dictionnaire;
import tn.esprit.persistance.Forum;
import tn.esprit.persistance.ForumClaim;
import tn.esprit.persistance.LikeCom;
import tn.esprit.persistance.LikeForum;
import tn.esprit.persistance.Swapper;


@Stateless
@LocalBean
public class ForumService implements ForumServiceLocal {

	

	public ForumService() {
		
		
	}


	@PersistenceContext
	EntityManager entityManager;
	
	
	@Override
	public List<Forum> findAllForums() {
		return entityManager.createQuery
				("select l from Forum l GROUP BY l.date",
				Forum.class)
				.getResultList();
	}


	@Override
	public void addOrUpdateForum(Forum frm) {
		entityManager.merge(frm);
		
	}


	@Override
	public void deleteForum(Forum frm) {
		entityManager.remove(entityManager.merge(frm));

		
	}


	@Override
	public Forum findForum(int id) {
		return entityManager.find(Forum.class, id);

	}


	@Override
	public List<Forum> findAllForums2(int id) {
		return entityManager.createQuery
				("select l from Forum l where l.swapper.idSwapper =:cur GROUP BY l.date",
				Forum.class)
				.setParameter("cur", id)
				.getResultList();
	}


	@Override
	public void liked(Forum frm) {
	int a=frm.getNote();
	int b= a+1;
	frm.setNote(b);
	entityManager.merge(frm);
		
	}


	@Override
	public List<Comments> findAllComments() {
		return entityManager.createQuery
				("select l from Comments l GROUP BY l.date",
				Comments.class)
				.getResultList();
	}


	@Override
	public void addComment(Comments cm) {
		entityManager.merge(cm);
		
	}


	@Override
	public void likedCom(Comments cm) {
		int a=cm.getNote();
		int b= a+1;
		cm.setNote(b);
		entityManager.merge(cm);
		
	}


	@Override
	public List<Comments> findAllCommentsFrm(int id) {
		return entityManager.createQuery
				("select l from Comments l where l.forum.idForum =:cur GROUP BY l.date",
				Comments.class)
				.setParameter("cur", id)
				.getResultList();
	}


	@Override
	public Long NombreCom(int id) {
		Long a= entityManager.createQuery
				("select COUNT(l.idComments) from Comments l where l.forum.idForum =:cur",
				Long.class)
				.setParameter("cur", id)
				.getSingleResult();
		return a;
	}


	@Override
	public void deleteCom(Comments cm) {
		entityManager.remove(entityManager.merge(cm));
		
	}


	@Override
	public void addForumClaim(ForumClaim frc) {
		
		
		if (VerifClaim(frc.getSwapper().getIdSwapper(),frc.getForum().getIdForum())==false)
	
		entityManager.merge(frc);
		
	}


	@Override
	public Boolean VerifClaim(int s, int f) {

		
		
		String requete="select COUNT(e.idForumClaim) from ForumClaim e where e.forum.idForum=:x "
				+ "and e.swapper.idSwapper =:y";
		
		Long a=entityManager.createQuery(requete,Long.class)
		.setParameter("x", f)
		.setParameter("y", s)
		.getSingleResult();
			
			if (a>=1) return true;
			else return false;
	
		
	}


	@Override
	public Swapper authenticate(String login, String password) {
		Swapper found = null;
		String jpql = "select u from Swapper u where u.login=:login and u.password=:password";
		TypedQuery<Swapper> query = entityManager.createQuery(jpql, Swapper.class);
		query.setParameter("login", login);
		query.setParameter("password", password);
		try {
			found = query.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println("pas de résultat");
		}
		return found;
	}


	@Override
	public Long NombreClaim(int id) {
		Long a= entityManager.createQuery
				("select COUNT(l.idForumClaim) from ForumClaim l where l.forum.idForum =:cur",
				Long.class)
				.setParameter("cur", id)
				.getSingleResult();
		return a;
	}


	@Override
	public List<Dictionnaire> findAllWords() {

		return entityManager.createQuery
				("select l from Dictionnaire l",
				Dictionnaire.class)
				.getResultList();
		
	}


	@Override
	public void addOrUpdateWord(Dictionnaire dic) {
		entityManager.merge(dic);
		
	}


	@Override
	public void deleteWord(Dictionnaire dic) {
		entityManager.remove(entityManager.merge(dic));
		
	}


	@Override
	public void addLikeForum(LikeForum lf) {
		entityManager.merge(lf);
		
	}


	@Override
	public Boolean VerifLikeForum(int s, int f) {
		
		String requete="select COUNT(e.idLikeForum) from LikeForum e where e.forum.idForum=:x "
				+ "and e.swapper.idSwapper =:y";
		
		Long a=entityManager.createQuery(requete,Long.class)
		.setParameter("x", f)
		.setParameter("y", s)
		.getSingleResult();
			
			if (a>=1) return true;
			else return false;
		
	}


	@Override
	public void addLikeComment(LikeCom lc) {
		entityManager.merge(lc);
		
	}


	@Override
	public Boolean VerifLikeCom(int s, int c) {
		String requete="select COUNT(e.idLikeCom) from LikeCom e where e.comment.idComments=:x "
				+ "and e.swapper.idSwapper =:y";
		
		Long a=entityManager.createQuery(requete,Long.class)
		.setParameter("x", c)
		.setParameter("y", s)
		.getSingleResult();
			
			if (a>=1) return true;
			else return false;
	}


	@Override
	public Long NombreCategoriForum(String str) {
		Long a= entityManager.createQuery
				("select COUNT(l.idForum) from Forum l where l.category =:cur",
				Long.class)
				.setParameter("cur", str)
				.getSingleResult();
		return a;
	}


	@Override
	public Long NombreCategorieClaim(String str) {
		Long a= entityManager.createQuery
				("select COUNT(l.idForumClaim) from ForumClaim l where l.cause =:cur",
				Long.class)
				.setParameter("cur", str)
				.getSingleResult();
		return a;
	}
	


	

}
