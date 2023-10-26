package controller.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.AuthInfoDTO;
import model.ItemDAO;
import model.MemberDTO;
import model.MemberMyDAO;
import model.PurchaseInfoDTO;

public class PurchaseListService {
	public void execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		MemberMyDAO memDao = new MemberMyDAO();
		MemberDTO memDto = memDao.memberInfo(auth.getUserId());
		
		ItemDAO dao = new ItemDAO();
		List<PurchaseInfoDTO> list = dao.purchaseItemSelect(memDto.getMemberNum());
		request.setAttribute("list", list);
	}
}
