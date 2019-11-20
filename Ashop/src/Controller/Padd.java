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

import DAO.ODAO;
import DAO.PDAO;
import DAO.QDAO;
import DAO.QRDAO;
import DAO.RDAO;
import DTO.ODTO;
import DTO.PDTO;
import DTO.QDTO;
import DTO.QRDTO;
import DTO.RDTO;

/**
 * Servlet implementation class Madd
 */
@WebServlet("/Padd.do")
public class Padd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * l
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public Padd() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String command = request.getParameter("command");
		String view = null;
		PDAO DAO = PDAO.getInstance();
		ODAO odao = ODAO.getInstance();
		QDAO qDAO = QDAO.getInstance();
		RDAO rDAO = RDAO.getInstance();
		QRDAO qrDAO = QRDAO.getInstance();

		request.setCharacterEncoding("UTF-8");

		if (command.equals("EnterP")) {// 관리자페이지 > 상품등록

			view = "EnterP.jsp";

			request.setCharacterEncoding("UTF-8");
			String pictureurl = request.getParameter("pictureurl");
			String title = request.getParameter("title");
			int price = Integer.parseInt(request.getParameter("price"));
			String sort = request.getParameter("sort");
			String detail = request.getParameter("detail");
			int pcnt = Integer.parseInt(request.getParameter("pcnt"));
			String detailpic = request.getParameter("detailpic");
			String ssize = request.getParameter("ssize");
			String color = request.getParameter("color");

			request.setCharacterEncoding("UTF-8");

			PDTO DTO = new PDTO();
			DTO.setTitle(title);
			DTO.setSort(sort);
			DTO.setPrice(price);
			DTO.setPictureurl(pictureurl);
			DTO.setDetail(detail);
			DTO.setDetailpic(detailpic);
			DTO.setSsize(ssize);
			DTO.setColor(color);
			DTO.setPcnt(pcnt);
			DAO.insert(DTO);

			System.out.println("상품정보등록");

			if (sort.equals("Outer")) {
				view = "Padd.do?command=Outer";
				System.out.println("Outer");

			} else if (sort.equals("Top")) {
				view = "Padd.do?command=Top";

			} else if (sort.equals("Bottom")) {
				view = "Padd.do?command=Bottom";

			} else if (sort.equals("Skirt")) {
				view = "Padd.do?command=Skirt";

			}

		} else if (command.equals("Outer")) {

			request.setCharacterEncoding("UTF-8");

			String sort = (String) request.getParameter("sort");
			List<PDTO> List = DAO.List(sort);

			ArrayList<String> sumlist = new ArrayList<String>();
			String soldout = null;
			int sum = -1;

			for (int i = 0; i < List.size(); i++) {
				sum = List.get(i).getPcnt() - List.get(i).getHistory();

				if (sum == 0) {
					soldout = "품절";
				} else if (sum != 0) {
					soldout = " ";
				}

				sumlist.add(soldout);
			}

			request.setAttribute("List", List);
			request.setAttribute("sumlist", sumlist);
			view = "Outer.jsp";

		} else if (command.equals("Top")) {

			request.setCharacterEncoding("UTF-8");

			String sort = (String) request.getParameter("sort");
			List<PDTO> List = DAO.List(sort);

			ArrayList<String> sumlist = new ArrayList<String>();
			String soldout = null;
			int sum = -1;
			for (int i = 0; i < List.size(); i++) {
				sum = List.get(i).getPcnt() - List.get(i).getHistory();
				if (sum == 0) {
					soldout = "품절";
				} else if (sum != 0) {
					soldout = " ";
				}

				sumlist.add(soldout);
			}

			request.setAttribute("List", List);
			request.setAttribute("sumlist", sumlist);
			view = "Top.jsp";

		} else if (command.equals("Bottom")) {

			request.setCharacterEncoding("UTF-8");

			String sort = (String) request.getParameter("sort");
			List<PDTO> List = DAO.List(sort);
			ArrayList<String> sumlist = new ArrayList<String>();
			String soldout = null;
			int sum = -1;

			for (int i = 0; i < List.size(); i++) {
				sum = List.get(i).getPcnt() - List.get(i).getHistory();

				if (sum == 0) {
					soldout = "품절";
				} else if (sum != 0) {
					soldout = " ";
				}

				sumlist.add(soldout);
			}

			request.setAttribute("List", List);
			request.setAttribute("sumlist", sumlist);
			view = "Bottom.jsp";

		} else if (command.equals("Skirt")) {

			request.setCharacterEncoding("UTF-8");

			String sort = (String) request.getParameter("sort");
			List<PDTO> List = DAO.List(sort);
			ArrayList<String> sumlist = new ArrayList<String>();
			String soldout = null;
			int sum = -1;
			for (int i = 0; i < List.size(); i++) {
				sum = List.get(i).getPcnt() - List.get(i).getHistory();
				if (sum == 0) {
					soldout = "품절";
				} else if (sum != 0) {
					soldout = " ";
				}

				sumlist.add(soldout);
			}

			request.setAttribute("List", List);
			request.setAttribute("sumlist", sumlist);
			view = "Skirt.jsp";

		} else if (command.equals("Detail")) {

			request.setCharacterEncoding("UTF-8");

			String pnum = (String) request.getParameter("pnum");
			int pnum1 = Integer.parseInt(request.getParameter("pnum"));

			/*
			 * Detail로 들어올때마다 조회수가 늘어나야하는! : DB UPDATE!! >>조회수를 알려면 우선 조회수 값을 받아와야하는
			 */
			int renum = DAO.SelectClick(pnum1) + 1;
			DAO.Update(pnum1, renum);

			List<PDTO> List = DAO.detail(pnum);
			String soldout = null;
			ArrayList<String> sumlist = new ArrayList<String>();

			for (int i = 0; i < List.size(); i++) {
				int sum = List.get(i).getPcnt() - List.get(i).getHistory();

				if (sum == 0) {
					soldout = "★품절★";
				} else if (sum != 0) {
					soldout = " ";
				}
				sumlist.add(soldout);

			}

			/*
			 * 리뷰와 QNA
			 */
			ArrayList<QDTO> QList = qDAO.List(pnum1);
			List<RDTO> RList = rDAO.List(pnum1);

			List<PDTO> RandomList = DAO.RandomList(); // Detail및 Md픽! 리스트

			request.setAttribute("sumlist", sumlist);
			request.setAttribute("List", List);
			request.setAttribute("QList", QList);
			request.setAttribute("RList", RList);
			request.setAttribute("RandomList", RandomList);

			view = "Detail.jsp";

		} else if (command.equals("AdminProductList")) {// 관리자페이지> 상품분석 List

			request.setCharacterEncoding("UTF-8");

			List<PDTO> AdminproductList = DAO.AdminProduct();
			String ssize = null;
			String color = null;

			// SSIZE : SIZE별 판매 된 수량 파악
			for (int i = 0; i < AdminproductList.size(); i++) {
				List<ODTO> AdminproductSsizeList = odao.AdminProductList(AdminproductList.get(i).getPnum());

				System.out.println("size : " + AdminproductSsizeList.size());

				if (AdminproductSsizeList.size() == 0) {
					ssize = " ";
					color = " ";

					AdminproductList.get(i).setSsize(ssize);
					AdminproductList.get(i).setColor(color);

				} else if (AdminproductSsizeList.size() == 1) {

					System.out.println("여기1");

					for (int j = 0; j < AdminproductSsizeList.size(); j++) {

						System.out.println("결과 : " + AdminproductSsizeList.get(j).getSsize());

						if (!(AdminproductSsizeList.get(j).getSsize().equals("S"))
								& !(AdminproductSsizeList.get(j).getSsize().equals("M"))
								& !(AdminproductSsizeList.get(j).getSsize().equals("L"))) {

							System.out.println("color");
							color = AdminproductSsizeList.get(j).getSsize() + ": "
									+ AdminproductSsizeList.get(j).getOrdernote();
							System.out.println(color);
							AdminproductList.get(i).setColor(color);

						} else {
							ssize = AdminproductSsizeList.get(j).getSsize() + ": "
									+ AdminproductSsizeList.get(j).getOrdernote();

							AdminproductList.get(i).setSsize(ssize);
						}

					}

				} else if (AdminproductSsizeList.size() > 1) {
					System.out.println("여기2");
					// size에 값이 있을 때는 해당 SsizeList를 for문으로 돌려 값을 가져오기

					for (int j = 0; j < AdminproductSsizeList.size(); j++) {
						
						System.out.println("결과 : " + AdminproductSsizeList.get(j).getSsize());
						
						if (j == 0) {

							if (!(AdminproductSsizeList.get(j).getSsize().equals("S"))
									& !(AdminproductSsizeList.get(j).getSsize().equals("M"))
									& !(AdminproductSsizeList.get(j).getSsize().equals("L"))) {

								System.out.println("color 여기 ");

								color = AdminproductSsizeList.get(j).getSsize() + ": "
										+ AdminproductSsizeList.get(j).getOrdernote();
								System.out.println(color);

							} else {
								System.out.println("size 여기 ");
								ssize = AdminproductSsizeList.get(j).getSsize() + ": "
										+ AdminproductSsizeList.get(j).getOrdernote();
								System.out.println(ssize);
							}

						} else if (j > 0) {

							if (!(AdminproductSsizeList.get(j).getSsize().equals("S"))
									& !(AdminproductSsizeList.get(j).getSsize().equals("M"))
									& !(AdminproductSsizeList.get(j).getSsize().equals("L"))) {

								System.out.println("color 여기 ");

								color = AdminproductSsizeList.get(j).getSsize() + ": "
										+ AdminproductSsizeList.get(j).getOrdernote() + "/ " + color;

								System.out.println(color);

							} else {
								System.out.println("size 여기 ");
								ssize = AdminproductSsizeList.get(j).getSsize() + ": "
										+ AdminproductSsizeList.get(j).getOrdernote() + "/ " + ssize;
								System.out.println(ssize);

							}

						}
					}

					AdminproductList.get(i).setSsize(ssize);
					AdminproductList.get(i).setColor(color);

					System.out.println("최종size : " + AdminproductList.get(i).getSsize());
					System.out.println("최종 color : " + AdminproductList.get(i).getColor());
				}

			}
			request.setAttribute("AdminList", AdminproductList);
			view = "AdminproductList.jsp";

		} else if (command.equals("qna")) {
			int pnum = Integer.parseInt(request.getParameter("pnum"));
			int qnum = Integer.parseInt(request.getParameter("qnum"));
			String id = request.getParameter("id");

			ArrayList<QDTO> QList = qDAO.QList(pnum, qnum);
			ArrayList<QRDTO> QrList = qrDAO.List(pnum, qnum);

			request.setAttribute("QrList", QrList);
			request.setAttribute("QList", QList);
			request.setAttribute("pnum", pnum);
			request.setAttribute("qnum", qnum);
			view = "QnaDetail.jsp";

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
