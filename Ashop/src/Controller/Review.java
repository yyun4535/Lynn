package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import DAO.QDAO;
import DAO.RDAO;
import DTO.ODTO;
import DTO.PDTO;
import DTO.QDTO;
import DTO.RDTO;

/**
 * Servlet implementation class Madd
 */
@WebServlet("/Review.do")
public class Review extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Review() {
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
		String command = request.getParameter("command");
		String view = "Index.jsp";
		CDAO cDAO = CDAO.getInstance();
		ODAO oDAO = ODAO.getInstance();
		RDAO rDAO = RDAO.getInstance();
		PDAO pDAO = PDAO.getInstance();
		QDAO qDAO = QDAO.getInstance();

		if (command.equals("Reviewadd")) {
			request.setCharacterEncoding("UTF-8");
			Date now = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd/ E¿äÀÏ a hh:mm");
			String today = sf.format(now);

			RDTO DTO = new RDTO();
			DTO.setId(request.getParameter("id"));
			DTO.setContent(request.getParameter("content"));
			DTO.setPictureurl(request.getParameter("pictureurl"));
			DTO.setPnum(Integer.parseInt(request.getParameter("pnum")));
			DTO.setRdate(today);
			DTO.setSsize(request.getParameter("ssize"));
			DTO.setTitle(request.getParameter("title"));
			DTO.setWeight(Integer.parseInt(request.getParameter("weight")));
			DTO.setColor(request.getParameter("color"));
			DTO.setContent(request.getParameter("content"));
			DTO.setHeight(Integer.parseInt(request.getParameter("height")));
			rDAO.insert(DTO);
			System.out.println("Reviewµé¾î¿È");
			request.setAttribute("pnum", DTO.getPnum());
			view = "Padd.do?command=Detail";

		} else if (command.equals("Reviewlist")) {
			request.setCharacterEncoding("UTF-8");

			int pnum = Integer.parseInt(request.getParameter("pnum"));

			List<RDTO> List = rDAO.List(pnum);
			request.setAttribute("RList", List);
			view = "Padd.do?command=Detail&pnum=" + pnum;

		} else if (command.equals("List")) {
			request.setCharacterEncoding("UTF-8");
			String id = request.getParameter("id");
			int pnum = Integer.parseInt(request.getParameter("pnum"));
			List<ODTO> RList = oDAO.OneList(id, pnum);
			request.setAttribute("RList", RList);
			view = "WritingReview.jsp";

		} else if (command.equals("chksize")) {
			request.setCharacterEncoding("UTF-8");
			String pnum = (String) request.getParameter("pnum");
			int pnum1 = Integer.parseInt(request.getParameter("pnum"));

			List<PDTO> List = pDAO.detail(pnum);
			ArrayList<QDTO> QList = qDAO.List(pnum1);
			List<RDTO> RList = rDAO.List(pnum1);

			int height = Integer.parseInt(request.getParameter("height"));
			int weight = Integer.parseInt(request.getParameter("weight"));

			ArrayList<RDTO> ChkList = rDAO.SizeList(height, weight);

			int S = 0;
			int M = 0;
			int L = 0;

			for (int i = 0; i < ChkList.size(); i++) {
				if (ChkList.get(i).getSsize().equals("S")) {
					S++;
					System.out.println("S" + S);
				} else if (ChkList.get(i).getSsize().equals("M")) {
					M++;
					System.out.println("M" + M);
				} else if (ChkList.get(i).getSsize().equals("L")) {
					L++;
					System.out.println("L" + L);
				}
			}
			request.setAttribute("S", S);
			request.setAttribute("M", M);
			request.setAttribute("L", L);
			request.setAttribute("RList", RList);
			request.setAttribute("List", List);
			request.setAttribute("QList", QList);
			view = "Detail.jsp";
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
