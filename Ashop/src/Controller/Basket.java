package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CDAO;
import DAO.ODAO;
import DAO.PDAO;
import DTO.CDTO;
import DTO.ODTO;

/**
 * Servlet implementation class Madd
 */
@WebServlet("/Basket.do")
public class Basket extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Basket() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		String command = request.getParameter("command");
		String view = "Index.jsp";
		CDAO DAO = CDAO.getInstance();
		ODAO Odao = ODAO.getInstance();
		PDAO pdao = PDAO.getInstance();

		if (command.equals("Cartadd")) {// 장바구니

			request.setCharacterEncoding("UTF-8");

			System.out.println("sublit : cartadd들어옴 ");

			CDTO DTO = new CDTO();

			DTO.setId(request.getParameter("id"));
			DTO.setPnum(Integer.parseInt(request.getParameter("pnum")));
			DTO.setTitle(request.getParameter("title"));
			DTO.setPictureurl(request.getParameter("pictureurl"));
			DTO.setPcnt(Integer.parseInt(request.getParameter("cnt")));
			DTO.setPrice(Integer.parseInt(request.getParameter("price")));
			DTO.setSsize(request.getParameter("sml"));
			DTO.setColor(request.getParameter("color"));
			DTO.setChoice(0);

			DAO.insert(DTO);

			System.out.println("장바구니 등록 완료");

			view = "Basket.do?command=Cartlist";

		} else if (command.equals("Cartlist")) { // 장바구니 List

			request.setCharacterEncoding("UTF-8");

			String id = request.getParameter("id");

			/*
			 * 장바구니 출력 전에는 choice가 1이던 것을 모두 0으로 바꿔주기 > 왜냐 구매를 했다면 삭제가 되었을텐데 남아있다는 것은 구매를
			 * 안했던 못했던 일테니 다시 reset 시켜줘야지
			 */
			DAO.choicereupdate(id);
			List<CDTO> List = DAO.List(id);
			request.setAttribute("List", List);

			view = "Cart.jsp";

		} else if (command.equals("CheckBoxChoice")) {// 구매 할 상품 체크박스 선택한 num의 choice update

			System.out.println("checkbox들어옴");

			request.setCharacterEncoding("UTF-8");

			String id = request.getParameter("id");
			String[] choice = request.getParameterValues("choice");

			DAO.choiceupdate(id, choice);

			view = "Basket.do?command=CheckoutList&id =" + id;

		} else if (command.equals("CheckoutList")) {// order Info작성 페이지 옆 주문 내역 리스트
			System.out.println("CheckList로 들어옴!!");

			request.setCharacterEncoding("UTF-8");

			int choice = 1;
			String id = request.getParameter("id");

			// choice가 1인것만 가져오기
			List<CDTO> BuyList = DAO.BuyList(id, choice);

			// jsp에 보여줘야 하기 때문에 List형태로 title및 여러가지가 필요
			request.setAttribute("List", BuyList);
			view = "Checkout.jsp";

		} else if (command.equals("CheckOut")) { // 구매
			// checkout.jsp에서 주소랑 내용들을 받아옴(리스트)

			System.out.println("Show me Chekckout");
			request.setCharacterEncoding("UTF-8");

			int choice = 1;

			String id = request.getParameter("id");

			/*
			 * checkout으로 넘어오는 부분 >> choice가 1인 것의 주문 info+form으로 작성 넘어온것 : sorder에 insert
			 * delete choice가 1인 내역 삭제 >> 수량 update
			 */

			// checkout.jsp에 띄운 리스트를 다시 한번 DB에서 받아오기
			List<CDTO> BuyList = DAO.BuyList(id, choice);

			/*
			 * id별이 아닌 전체 주문내역의 마지막 최종(최신의) order넘버 받아오기 >> 받아온 넘버의 +1이 이번 주문 리스트의 주문 넘버가 됌
			 */

			int ordernum = Odao.selectordernum() + 1;

			ArrayList<Integer> SuccessList = new ArrayList<Integer>();

			for (int i = 0; i < BuyList.size(); i++) {

				request.setCharacterEncoding("UTF-8");

				ODTO Odto = new ODTO();
				Odto.setBuynum(ordernum);
				Odto.setId(BuyList.get(i).getId());
				Odto.setName(request.getParameter("name"));
				Odto.setEmail(request.getParameter("email"));
				Odto.setPhone(request.getParameter("phone"));
				Odto.setAddress(request.getParameter("address"));
				Odto.setOrdernote(request.getParameter("ordernote"));
				Odto.setPnum(BuyList.get(i).getPnum());
				Odto.setTitle(BuyList.get(i).getTitle());
				Odto.setPrice(BuyList.get(i).getPrice());
				Odto.setPictureurl(BuyList.get(i).getPictureurl());
				Odto.setSsize(BuyList.get(i).getSsize());
				Odto.setColor(BuyList.get(i).getColor());
				Odto.setPcnt(BuyList.get(i).getPcnt());

				int re = Odao.insert(Odto);

				/*
				 * 구매하면 delete / 실패하면 choice re update (CHOICE번호 다시 0으로 돌려주기 > 구매시 다시 선택)
				 */
				if (re == 1) {// 성공
					DAO.delete(BuyList.get(i).getId(), BuyList.get(i).getNum(), BuyList.get(i).getChoice());

					/*
					 * 주문내역 insert> 장바구니에서는 delete > id와 장바구니num(:choice가 1) insertx> 다시 장바구니로 돌아가지만
					 * checkbox 선택 다시 할 수 있게 choice를 0으로 바꿔주기
					 */

					/*
					 * 구매수량 check및 update 구절 : 상품번호와 기존의 구매수량 그리고 새로 추가 된 수량을 알아야하는 구매수량이 update
					 * 되었다는 건 order에 방금 insert 되었다는 거 > 위에꺼에서 받아도 되고, 다시 last주문번호를 검색해도 됌 : *상품번호는
					 * 1개!
					 */

					List<ODTO> list = Odao.OneSelect(ordernum);

					for (int j = 0; j < list.size(); j++) {
						
						/*
						 * 장바구니의 pnum을 select로 보내서 해당하는 상품의 기존의 구매수를 가져와
						 * imsinum에 저장 저장된 구매수와 새로 추가 구매 된 pcnt수를 더해서 historynum에 저장후 db 업데이트
						 */

						int imsinum = pdao.SelectHistory(list.get(j).getPnum());
						int historynum = imsinum + list.get(j).getPcnt();
						pdao.HistoryUpdate(list.get(j).getPnum(), historynum);

					}

				}

			}
			request.setCharacterEncoding("UTF-8");

			System.out.println("Success CheckOut");

			view = "Basket.do?command=List&id =" + id + "&buynum=" + ordernum;

		} else if (command.equals("List")) { // 사용자 주문확인(order) List
			System.out.println("OrderList");

			request.setCharacterEncoding("UTF-8");

			String id = request.getParameter("id");
			int ordernum = Integer.parseInt(request.getParameter("buynum"));

			List<ODTO> List = Odao.List(id);

			for (int i = 0; i < List.size(); i++) {

				if (i > 0) {
					/*
					 * 주문번호를 누를 경우 자세한 주문 내역이 나오기 때문에
					 * >같은 주문 번호일 경우 임의적으로 List에서 한 개를 삭제하여 하나의 주문번호만 나올 수
					 */
					if (List.get(i).getBuynum() == List.get(i - 1).getBuynum()) {
						List.remove(i);
					}
				}

			}

			System.out.println("ORDERLIST SIZE : " + List.size());
			request.setAttribute("MOList", List);
			view = "MemberOrderList.jsp";
			System.out.println("MemberOrderList를 출력하였습니다");

		} else if (command.equals("MemberList")) {
			System.out.println("MemberList");

			request.setCharacterEncoding("UTF-8");

			String id = request.getParameter("id");
			List<ODTO> CartList = Odao.MemberList(id);
			request.setAttribute("MList", CartList);
			view = "MemberList.jsp";
			
			System.out.println("MemberList를 출력하였습니다");

		} else if (command.equals("CartUpdate")) {// 장바구니 리스트에서 수량 수정
			//장바구니리스트에서 변경을 클릭시 수량 update

			System.out.println("CartUpdate!!!!");
			
			request.setCharacterEncoding("UTF-8");

			int pcnt = Integer.parseInt(request.getParameter("changecnt")); // 변경 할 상품의 개수
			int pnum = Integer.parseInt(request.getParameter("pnum")); // 상품번호
			int num = Integer.parseInt(request.getParameter("num")); // 장바구니번호
			String id = request.getParameter("id"); // 장바구니를 가진 사용자의 id

			DAO.cartupdate(id, pcnt, pnum, num);

			// update후에는 다시 리스트를 출력해야함
			view = "Basket.do?command=Cartlist";

		} else if (command.equals("OrderList")) { // 관리자페이지 > 회원들의 주문내역리스트(최신순)
			request.setCharacterEncoding("UTF-8");

			List<ODTO> List = Odao.AdminOrderList();
			for (int i = 0; i < List.size(); i++) {

				if (i > 0) {// 주문번호를 누를 경우 자세한 주문 내역이 나오기 때문에> 같은 주문 번호일 경우 List에서 한 개를 삭제하여 하나의 주문번호만 나올 수
							// 있게 함
					if (List.get(i).getBuynum() == List.get(i - 1).getBuynum()) {
						List.remove(i);
					}
				}

			}
			request.setAttribute("OList", List);

			view = "OrderList.jsp";

		} else if (command.equals("OrderDetailList")) { // 관리자 주문내역 > 주문번호 눌렀을 시 : detail 주문내역 출력
			request.setCharacterEncoding("UTF-8");

			int ordernum = Integer.parseInt(request.getParameter("buynum"));
			System.out.println("ORDERNUM: " + ordernum);

			List<ODTO> OrderDetailList = Odao.AdminOrderDetailList(ordernum);
			request.setAttribute("OList", OrderDetailList);

			view = "OrderDetailList.jsp";

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
