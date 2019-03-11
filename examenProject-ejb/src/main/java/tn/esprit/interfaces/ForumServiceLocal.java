package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.persistance.Comments;
import tn.esprit.persistance.Dictionnaire;
import tn.esprit.persistance.Forum;
import tn.esprit.persistance.ForumClaim;
import tn.esprit.persistance.LikeCom;
import tn.esprit.persistance.LikeForum;
import tn.esprit.persistance.Swapper;




@Local
public interface ForumServiceLocal {
	
	List<Forum> findAllForums();
	List<Dictionnaire> findAllWords();
	List<Comments> findAllComments();
	List<Comments> findAllCommentsFrm(int id);
	List<Forum> findAllForums2(int id);
	void addOrUpdateForum(Forum frm);
	void addOrUpdateWord(Dictionnaire dic);
	void addComment(Comments cm);
	void addForumClaim(ForumClaim frc);
	void addLikeForum(LikeForum lf);
	void addLikeComment(LikeCom lc);
	void deleteForum(Forum frm);
	void deleteWord(Dictionnaire dic);
	void deleteCom(Comments cm);
	Forum findForum(int id);
	void liked(Forum frm);
	void likedCom(Comments cm);
	Long NombreCom(int id);
	Long NombreClaim(int id);
	Long NombreCategoriForum(String str);
	Long NombreCategorieClaim(String str);
	
	Boolean VerifClaim(int s, int f);
	Boolean VerifLikeForum(int s, int f);
	Boolean VerifLikeCom(int s, int c);
	public Swapper authenticate(String login, String password);
}
