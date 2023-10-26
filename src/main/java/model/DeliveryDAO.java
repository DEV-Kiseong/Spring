package model;

import java.sql.SQLException;

public class DeliveryDAO extends DataBaseInfo{
	public void deliveryUpdate(DeliveryDTO dto) {
		con= getConnection();
		sql = " update delivery set delivery_num =?, delivery_state =?"
				+ " where purchase_num=? ";
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, dto.getDeliveryNum());
			pstmt.setString(2, dto.getDeliveryState());
			pstmt.setString(3, dto.getPurchaseNum());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	public DeliveryDTO deliverySelect(String purchaseNum) {
		DeliveryDTO dto =null;
		con = getConnection();
		sql= " select delivery_num, purchase_num, delivery_date, delivery_state "
				+ " from delivery where purchase_num = ?";
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			rs = pstmt.executeQuery();
			rs.next();
			dto = new DeliveryDTO();
			dto.setDeliveryDate(rs.getDate("delivery_date"));
			dto.setDeliveryNum(rs.getString("delivery_num"));
			dto.setPurchaseNum(rs.getString("purchase_num"));
			dto.setDeliveryState(rs.getString("delivery_state"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public void deliveryInsert(String purchaseNum, String deliveryNum) {
		con = getConnection();
		sql=" insert into DELIVERY ( purchase_num, delivery_num, delivery_date, delivery_state)"
				+ " values(?,?,now(),'배송중')";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			pstmt.setString(2, deliveryNum);
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 삽입되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{			
			close();
		}
	}
}
