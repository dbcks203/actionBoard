package member;

import java.sql.SQLException;
import java.util.List;

public interface MemberDAOInterface {

	public List<MemberBean> listMembers(String text, int pageNo,int listSize);
	
	public int memberUpdate(MemberBean member) ;
	
	public int insertMember(MemberBean memberBean) throws SQLException; 

	public int dropMember(String userid) throws SQLException; 
	
	public MemberBean findMember(String key, Boolean code) throws SQLException;
	
	public MemberBean getMember(String userid) throws SQLException; 

	public boolean isExisted(MemberBean member); 
	
	public void close() throws Exception;
}
