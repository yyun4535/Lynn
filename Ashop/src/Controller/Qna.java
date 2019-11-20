package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.QDAO;
import DAO.QRDAO;
import DTO.QDTO;
import DTO.QRDTO;

/**
 * Servlet implementation class Madd
 */
@WebServlet("/Qna.do")
public class Qna extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Qna() {
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
		String view = "Qna.do?command=Qnalist";
		QDAO qdao = QDAO.getInstance();
		QRDAO qrdao = QRDAO.getInstance();

		if (command.equals("Qnaadd")) {
			request.setCharacterEncoding("UTF-8");

			Date now = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd/ E요일 a hh:mm");
			String today = sf.format(now);

			QDTO qdto = new QDTO();

			qdto.setId(request.getParameter("id"));
			qdto.setContent(request.getParameter("content"));
			qdto.setPum(Integer.parseInt(request.getParameter("pnum")));
			qdto.setQdate(today);
			qdto.setTitle(request.getParameter("title"));
			qdto.setPwd(request.getParameter("pwd"));
			qdao.insert(qdto);

			System.out.println("qna들어옴");

			view = "Padd.do?command=Detail&pnum=" + qdto.getPum();

		} else if (command.equals("password")) {
			request.setCharacterEncoding("UTF-8");

			String id = request.getParameter("id");
			String qnum = request.getParameter("qnum");
			String pnum = request.getParameter("pnum");

			request.setAttribute("id", id);
			request.setAttribute("qnum", qnum);
			request.setAttribute("pnum", pnum);

			System.out.println("비밀번호 ");

			if (id.equals("admin")) {
				System.out.println("비밀번호 Admin");
				view = "Padd.do?command=qna&qnum=" + qnum + "&pnum=" + pnum;
			
			} else {
				System.out.println("비밀번호 일반관리자");
				view = "passwordCheck.jsp";
			}

		} else if (command.equals("chk")) {
			request.setCharacterEncoding("UTF-8");

			String pwd = request.getParameter("pwd");
			int pnum = Integer.parseInt(request.getParameter("pnum"));
			int qnum = Integer.parseInt(request.getParameter("qnum"));
			String id = request.getParameter("id");
			int chk = qdao.pwdchk(pwd, qnum);

			if (chk == 1) {
				System.out.println("완료");
				view = "Padd.do?command=qna&qnum=" + qnum + "&pnum=" + pnum + "&id=" + id;
			} else if (chk == 0) {
				System.out.println("실패");
				view = "Qna.do?command=password";
			}

		} else if (command.equals("QnaRadd")) {
			System.out.println("대댓글");
			request.setCharacterEncoding("UTF-8");

			Date now = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd/ E요일 a hh:mm");
			String today = sf.format(now);

			QRDTO qrdto = new QRDTO();

			qrdto.setContent(request.getParameter("content"));
			qrdto.setPnum(Integer.parseInt(request.getParameter("pnum")));
			qrdto.setQrdate(today);
			qrdto.setQnum(Integer.parseInt(request.getParameter("qnum")));
			qrdao.insert(qrdto);

			System.out.println("qnar들어옴");
			view = "Qna.do?command=QnaRview&pnum=" + qrdto.getPnum() + "&qnum" + qrdto.getQnum();

		} else if (command.equals("QnaRview")) {
			System.out.println("qnarview");
			int pnum = Integer.parseInt(request.getParameter("pnum"));
			int qnum = Integer.parseInt(request.getParameter("qnum"));

			view = "Padd.do?command=qna&qnum=" + qnum + "&pnum=" + pnum;

		} else if (command.equals("Del")) {
			request.setCharacterEncoding("UTF-8");
			System.out.println("Del들어옴");
			String id = request.getParameter("id");
			int pnum = Integer.parseInt(request.getParameter("pnum"));
			int qnum = Integer.parseInt(request.getParameter("qnum"));
			qdao.delete(id, pnum, qnum);

			view = "Padd.do?command=Detail";

		} else if (command.equals("Update")) {
			request.setCharacterEncoding("UTF-8");
			System.out.println("Update들어옴");
			QDTO qdto = new QDTO();
			String id = request.getParameter("id");
			int pnum = Integer.parseInt(request.getParameter("pnum"));
			int qnum = Integer.parseInt(request.getParameter("qnum"));
			qdto.setTitle(request.getParameter("title"));
			qdto.setContent(request.getParameter("content"));
			qdao.update(qdto, id, pnum, qnum);

			view = "Qna.do?command=QnaRview&pnum=" + pnum + "&qnum" + qnum;

		} else if (command.equals("Send")) {
			String id = request.getParameter("id");
			int pnum = Integer.parseInt(request.getParameter("pnum"));
			int qnum = Integer.parseInt(request.getParameter("qnum"));

			request.setAttribute("id", id);
			request.setAttribute("pnum", pnum);
			request.setAttribute("qnum", qnum);
			view = "update.jsp";
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
