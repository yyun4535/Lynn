package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MDAO;
import DAO.PDAO;
import DTO.MDTO;
import DTO.PDTO;

/**
 * Servlet implementation class Madd
 */
@WebServlet("/Madd.do")
public class Madd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Madd() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");
		String view = "Madd.do?command=Index";
		MDAO DAO = MDAO.getInstance();
		
		PDAO Pdao = PDAO.getInstance();
		request.setCharacterEncoding("UTF-8");

		if (command == null) {
			command = "Index";

		} else if (command.equals("Login")) {

			request.setCharacterEncoding("UTF-8");

			System.out.println("Login으로 들어옴!");
			view = "Login.jsp";

			int re = -1;

			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");

			re = DAO.loginchk(id, pwd);

			if (re == 0) {
				System.out.println("아이디 틀림");
			} else if (re == 1) {
				System.out.println("비밀번호 틀림");
			} else if (re == 2) {
				System.out.println("성공");
				request.setAttribute("id", id);
				// HttpSession session = request.getSession();
				view = "Madd.do?command=LoginSuc";
			} else {
				System.out.println("실패");
			}

		} else if (command.equals("Join")) {
			request.setCharacterEncoding("UTF-8");

			view = "Join.jsp";

			int admin = -1;
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String phone = request.getParameter("phone");
			String emailid = request.getParameter("emailid");
			String emailaddress = request.getParameter("emailaddress");
			String email = emailid + "@" + emailaddress;
			String address = request.getParameter("address");

			if (id != null) {
				if (id.equals("admin")) {
					admin = 1;
				} else {
					admin = 0;
				}
				request.setCharacterEncoding("UTF-8");
				MDTO DTO = new MDTO();
				DTO.setAdmin(admin);
				DTO.setId(id);
				DTO.setEmail(email);
				DTO.setPwd(pwd);
				DTO.setPhone(phone);
				DTO.setAddress(address);
				DAO.insert(DTO);
				System.out.println("가입완료");
				view = "Login.jsp";
			}

		} else if (command.equals("Index")) {
			request.setCharacterEncoding("UTF-8");

			System.out.println("Index");

			// 최신상품 가져오기 limit 6
			List<PDTO> newList = Pdao.NewestList();

			// Best상품 가져오기 (구매수 많은 순서)
			List<PDTO> BestList = Pdao.BestList();

			request.setAttribute("newList", newList);
			request.setAttribute("BestList", BestList);

			view = "Index.jsp";

		} else if (command.equals("LoginSuc")) {
			request.setCharacterEncoding("UTF-8");

			System.out.println("LoginSuc");

			String id = request.getParameter("id");
			request.setAttribute("AA", 1);
			request.setAttribute("id", id);
			view = "Login.jsp";
			
		} else if (command.equals("Idchk")) {
			request.setCharacterEncoding("UTF-8");

			System.out.println("IdChk 시작");

			String id = request.getParameter("id");
			int chk = DAO.idchk(id);

			if (chk == 1) {
				request.setAttribute("chk", chk);
			} else if (chk != 1) {
				request.setAttribute("chk", chk);
				request.setAttribute("id", id);
			}
			view = "js_aa.jsp";

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
