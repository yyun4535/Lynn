package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.NDAO;
import DTO.CDTO;
import DTO.NDTO;

/**
 * Servlet implementation class Madd
 */
@WebServlet("/Notice.do")
public class Notice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Notice() {
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
		String view = "Index.jsp";

		NDAO DAO = NDAO.getInstance();

		if (command.equals("Noticeadd")) {//관리자페이지> 공지사항 등록

			request.setCharacterEncoding("UTF-8");

			NDTO DTO = new NDTO();

			DTO.setId(request.getParameter("Nid"));
			DTO.setNtype(request.getParameter("Ntype"));
			DTO.setNtitle(request.getParameter("Ntitle"));
			DTO.setNcontent(request.getParameter("Ncontent"));
			DTO.setNdate(request.getParameter("Ndate"));

			DAO.insert(DTO);

			view = "Notice.do?command=Noticelist";
			
		} else if (command.equals("Noticelist")) {

			request.setCharacterEncoding("UTF-8");

			List<NDTO> List = DAO.List();

			request.setAttribute("NList", List);

			view = "NoticeList.jsp"; //따로 보낼 값이나 서블릿으로 연결 할 사항 없음, VIEW형태로 보여주기

		} else if (command.equals("NoticeDeatailList")) {

			request.setCharacterEncoding("UTF-8");

			int nnum = Integer.parseInt(request.getParameter("nnum"));

			List<NDTO> List = DAO.DetailList(nnum);
			request.setAttribute("NList", List);

			view = "NoticeDetailList.jsp";
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
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);

	}

}
