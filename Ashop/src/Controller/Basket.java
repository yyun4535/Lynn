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

		if (command.equals("Cartadd")) {// ��ٱ���

			request.setCharacterEncoding("UTF-8");

			System.out.println("sublit : cartadd���� ");

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

			System.out.println("��ٱ��� ��� �Ϸ�");

			view = "Basket.do?command=Cartlist";

		} else if (command.equals("Cartlist")) { // ��ٱ��� List

			request.setCharacterEncoding("UTF-8");

			String id = request.getParameter("id");

			/*
			 * ��ٱ��� ��� ������ choice�� 1�̴� ���� ��� 0���� �ٲ��ֱ� > �ֳ� ���Ÿ� �ߴٸ� ������ �Ǿ����ٵ� �����ִٴ� ���� ���Ÿ�
			 * ���ߴ� ���ߴ� ���״� �ٽ� reset ���������
			 */
			DAO.choicereupdate(id);
			List<CDTO> List = DAO.List(id);
			request.setAttribute("List", List);

			view = "Cart.jsp";

		} else if (command.equals("CheckBoxChoice")) {// ���� �� ��ǰ üũ�ڽ� ������ num�� choice update

			System.out.println("checkbox����");

			request.setCharacterEncoding("UTF-8");

			String id = request.getParameter("id");
			String[] choice = request.getParameterValues("choice");

			DAO.choiceupdate(id, choice);

			view = "Basket.do?command=CheckoutList&id =" + id;

		} else if (command.equals("CheckoutList")) {// order Info�ۼ� ������ �� �ֹ� ���� ����Ʈ
			System.out.println("CheckList�� ����!!");

			request.setCharacterEncoding("UTF-8");

			int choice = 1;
			String id = request.getParameter("id");

			// choice�� 1�ΰ͸� ��������
			List<CDTO> BuyList = DAO.BuyList(id, choice);

			// jsp�� ������� �ϱ� ������ List���·� title�� ���������� �ʿ�
			request.setAttribute("List", BuyList);
			view = "Checkout.jsp";

		} else if (command.equals("CheckOut")) { // ����
			// checkout.jsp���� �ּҶ� ������� �޾ƿ�(����Ʈ)

			System.out.println("Show me Chekckout");
			request.setCharacterEncoding("UTF-8");

			int choice = 1;

			String id = request.getParameter("id");

			/*
			 * checkout���� �Ѿ���� �κ� >> choice�� 1�� ���� �ֹ� info+form���� �ۼ� �Ѿ�°� : sorder�� insert
			 * delete choice�� 1�� ���� ���� >> ���� update
			 */

			// checkout.jsp�� ��� ����Ʈ�� �ٽ� �ѹ� DB���� �޾ƿ���
			List<CDTO> BuyList = DAO.BuyList(id, choice);

			/*
			 * id���� �ƴ� ��ü �ֹ������� ������ ����(�ֽ���) order�ѹ� �޾ƿ��� >> �޾ƿ� �ѹ��� +1�� �̹� �ֹ� ����Ʈ�� �ֹ� �ѹ��� ��
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
				 * �����ϸ� delete / �����ϸ� choice re update (CHOICE��ȣ �ٽ� 0���� �����ֱ� > ���Ž� �ٽ� ����)
				 */
				if (re == 1) {// ����
					DAO.delete(BuyList.get(i).getId(), BuyList.get(i).getNum(), BuyList.get(i).getChoice());

					/*
					 * �ֹ����� insert> ��ٱ��Ͽ����� delete > id�� ��ٱ���num(:choice�� 1) insertx> �ٽ� ��ٱ��Ϸ� ���ư�����
					 * checkbox ���� �ٽ� �� �� �ְ� choice�� 0���� �ٲ��ֱ�
					 */

					/*
					 * ���ż��� check�� update ���� : ��ǰ��ȣ�� ������ ���ż��� �׸��� ���� �߰� �� ������ �˾ƾ��ϴ� ���ż����� update
					 * �Ǿ��ٴ� �� order�� ��� insert �Ǿ��ٴ� �� > ���������� �޾Ƶ� �ǰ�, �ٽ� last�ֹ���ȣ�� �˻��ص� �� : *��ǰ��ȣ��
					 * 1��!
					 */

					List<ODTO> list = Odao.OneSelect(ordernum);

					for (int j = 0; j < list.size(); j++) {
						
						/*
						 * ��ٱ����� pnum�� select�� ������ �ش��ϴ� ��ǰ�� ������ ���ż��� ������
						 * imsinum�� ���� ����� ���ż��� ���� �߰� ���� �� pcnt���� ���ؼ� historynum�� ������ db ������Ʈ
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

		} else if (command.equals("List")) { // ����� �ֹ�Ȯ��(order) List
			System.out.println("OrderList");

			request.setCharacterEncoding("UTF-8");

			String id = request.getParameter("id");
			int ordernum = Integer.parseInt(request.getParameter("buynum"));

			List<ODTO> List = Odao.List(id);

			for (int i = 0; i < List.size(); i++) {

				if (i > 0) {
					/*
					 * �ֹ���ȣ�� ���� ��� �ڼ��� �ֹ� ������ ������ ������
					 * >���� �ֹ� ��ȣ�� ��� ���������� List���� �� ���� �����Ͽ� �ϳ��� �ֹ���ȣ�� ���� ��
					 */
					if (List.get(i).getBuynum() == List.get(i - 1).getBuynum()) {
						List.remove(i);
					}
				}

			}

			System.out.println("ORDERLIST SIZE : " + List.size());
			request.setAttribute("MOList", List);
			view = "MemberOrderList.jsp";
			System.out.println("MemberOrderList�� ����Ͽ����ϴ�");

		} else if (command.equals("MemberList")) {
			System.out.println("MemberList");

			request.setCharacterEncoding("UTF-8");

			String id = request.getParameter("id");
			List<ODTO> CartList = Odao.MemberList(id);
			request.setAttribute("MList", CartList);
			view = "MemberList.jsp";
			
			System.out.println("MemberList�� ����Ͽ����ϴ�");

		} else if (command.equals("CartUpdate")) {// ��ٱ��� ����Ʈ���� ���� ����
			//��ٱ��ϸ���Ʈ���� ������ Ŭ���� ���� update

			System.out.println("CartUpdate!!!!");
			
			request.setCharacterEncoding("UTF-8");

			int pcnt = Integer.parseInt(request.getParameter("changecnt")); // ���� �� ��ǰ�� ����
			int pnum = Integer.parseInt(request.getParameter("pnum")); // ��ǰ��ȣ
			int num = Integer.parseInt(request.getParameter("num")); // ��ٱ��Ϲ�ȣ
			String id = request.getParameter("id"); // ��ٱ��ϸ� ���� ������� id

			DAO.cartupdate(id, pcnt, pnum, num);

			// update�Ŀ��� �ٽ� ����Ʈ�� ����ؾ���
			view = "Basket.do?command=Cartlist";

		} else if (command.equals("OrderList")) { // ������������ > ȸ������ �ֹ���������Ʈ(�ֽż�)
			request.setCharacterEncoding("UTF-8");

			List<ODTO> List = Odao.AdminOrderList();
			for (int i = 0; i < List.size(); i++) {

				if (i > 0) {// �ֹ���ȣ�� ���� ��� �ڼ��� �ֹ� ������ ������ ������> ���� �ֹ� ��ȣ�� ��� List���� �� ���� �����Ͽ� �ϳ��� �ֹ���ȣ�� ���� ��
							// �ְ� ��
					if (List.get(i).getBuynum() == List.get(i - 1).getBuynum()) {
						List.remove(i);
					}
				}

			}
			request.setAttribute("OList", List);

			view = "OrderList.jsp";

		} else if (command.equals("OrderDetailList")) { // ������ �ֹ����� > �ֹ���ȣ ������ �� : detail �ֹ����� ���
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
