package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InquireDAO extends DataBaseInfo{
	public void inquireReplyUpdate(InquireDTO dto) {
		con = getConnection();
		sql = " update goods_inquire, set inquire_answer =?"
				+ " ,emp_num =?, inquire_answer_date = now()"
				+ " where inquire_num=? ";
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, dto.getInquireAnswer());
			pstmt.setString(2, dto.getEmpNum());
			pstmt.setLong(3, dto.getInquireNum());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		
	}
	public void inquireUpdate(String inquireNum, String inquireSubject, String inquireContent) {
		con = getConnection();
		sql = " update goods_inquire " 
		    + " set INQUIRE_SUBJECT = ?" 
			+ "    ,INQUIRE_CONTENT = ? " 
		    + " where inquire_Num = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inquireSubject);
			pstmt.setString(2, inquireContent);
			pstmt.setLong(3, Long.parseLong(inquireNum));
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 수정되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}

	public InquireDTO selectOne(String inquireNum) {
		InquireDTO dto = null;
		con = getConnection();
		sql = " select INQUIRE_NUM, MEMBER_NUM, GOODS_NUM, INQUIRE_SUBJECT"
				+ "       ,INQUIRE_CONTENT, inquire_kind, inquire_date "
				+ "       ,inquire_answer, inquire_answer_date,emp_num" 
				+ " from goods_inquire "
				+ " where INQUIRE_NUM = ? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, Long.parseLong(inquireNum));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new InquireDTO();
				dto.setEmpNum(rs.getString("emp_num"));
				dto.setGoodsNum(rs.getString("GOODS_NUM"));
				dto.setInquireAnswer(rs.getString("inquire_answer"));
				dto.setInquireContent(rs.getString("INQUIRE_CONTENT"));
				dto.setInquireDate(rs.getDate("inquire_date"));
				dto.setInquireKind(rs.getString("inquire_kind"));
				dto.setInquireNum(rs.getLong("INQUIRE_NUM"));
				dto.setInquireSubject(rs.getString("INQUIRE_SUBJECT"));
				dto.setMemberNum(rs.getString("MEMBER_NUM"));
				dto.setInquireAnswerDate(rs.getDate("inquire_answer_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return dto;
	}
	public void inquireDelete(String inquireNum) {
		con = getConnection();
		sql = " delete from goods_inquire " + " where  inquire_Num = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, Long.parseLong(inquireNum));
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 살제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	public List<InquireDTO> inquireSelectAll(String goodsNum){
		List<InquireDTO> list = new ArrayList<InquireDTO>();
		String condition = "";
		if(goodsNum != null) {
			condition=" and goods_num=?";
		}
		con = getConnection();
		sql = " select inquire_num, member_num, goods_num, inquire_subject"
				+ " , inquire_content, inquire_kind, inquire_date, inquire_answer, inquire_answer_date"
				+ "	, emp_num "
				+ " from goods_inquire"
				+ " where 1=1 " + condition;
		
		try {
			pstmt = con.prepareStatement(sql);
			if(goodsNum != null) {
				pstmt.setString(1, goodsNum);
			}
			rs = pstmt.executeQuery();
			while(rs.next()){
				InquireDTO dto = new InquireDTO();
				dto.setEmpNum(rs.getString("emp_num"));
				dto.setGoodsNum(rs.getString("goods_num"));
				dto.setInquireSubject(rs.getString("inquire_subject"));
				dto.setInquireContent(rs.getString("inquire_content"));
				dto.setInquireKind(rs.getString("inquire_kind"));
				dto.setInquireDate(rs.getDate("inquire_date"));
				dto.setInquireAnswer(rs.getString("inquire_answer"));
				dto.setInquireAnswerDate(rs.getDate("inquire_answer_date"));
				dto.setInquireNum(rs.getLong("inquire_num"));
				dto.setMemberNum(rs.getString("member_num"));				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public void inquireInsert(InquireDTO dto) {
		con = getConnection();
		sql = " insert into goods_inquire( member_num, goods_num, inquire_subject, inquire_content, inquire_kind, inquire_date"
				+ " values(?,?,?,?,?,now())";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMemberNum());
			pstmt.setString(2, dto.getGoodsNum());
			pstmt.setString(3, dto.getInquireSubject());
			pstmt.setString(4, dto.getInquireContent());
			pstmt.setString(5, dto.getInquireKind());
			
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 삽입되었습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
}
