package com.human.main;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("love")
public class MyController {

	private MDAO mdao;
	private BDAO bdao;
	private PDAO pdao;

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private Upload upload;

	@RequestMapping("/Main")
	public String Main() {
		System.out.println("Main");
		return "redirect:List";
		// 홈화면
	}

	@RequestMapping("/Product")
	public String Product() {

		return "/Board/product";
		// 상품등록
	}

	@RequestMapping("/List")
	public String ProductList(HttpServletRequest HttpServletRequest, Model model) {
		pdao = sqlSession.getMapper(PDAO.class);
		System.out.println("제품나가신다!");
		ArrayList<PDTO> plist = pdao.selectP();
		System.out.println("plist size : " + plist.size());
		model.addAttribute("plist", plist);

		bdao = sqlSession.getMapper(BDAO.class);
		System.out.println("공지사항!");
		ArrayList<BDTO> nlist = bdao.selectB();
		System.out.println("nlist size : " + nlist.size());
		model.addAttribute("nlist", nlist);

		return "/Board/main";
	}

	// 상품등록
	@RequestMapping("/ProductInput")
	public String ProductInput(HttpServletRequest HttpServletRequest, MultipartHttpServletRequest mRequest) {
		PDTO pdto = new PDTO();
		System.out.println("상품등록한다.");
		pdao = sqlSession.getMapper(PDAO.class);
		String title = HttpServletRequest.getParameter("title");
		pdto.setTitle(title);
		System.out.println("title : " + title);

		String price = HttpServletRequest.getParameter("price");
		pdto.setPrice(price);
		System.out.println("price: " + price);

		int no = Integer.parseInt(HttpServletRequest.getParameter("no"));
		pdto.setNo(no);
		System.out.println("no: " + no);

		String detail = HttpServletRequest.getParameter("detail");
		pdto.setDetail(detail);
		System.out.println("detail: " + detail);

		// 사진 업로드 구간
		String pic = upload.fileUpload(mRequest);
		System.out.println(pic);

		if (pic != null) {
			System.out.println("상품업로드성공!!");
			pdto.setPic(pic);
			System.out.println("pic: " + pic);
		} else {
			System.out.println("상품 업로드 실패!!");
			return "redirect : product";
		}

		pdao.inputP(pdto);

		return "redirect:List";
	}

	// 공지사항입력
	@RequestMapping("/NoticeInput")
	public String NoticeInput(HttpServletRequest HttpServletRequest, Model model) {
		BDTO bdto = new BDTO();
		bdao = sqlSession.getMapper(BDAO.class);
		String id = HttpServletRequest.getParameter("id");
		bdto.setId(id);
		System.out.println("id : " + id);

		String date = DateTime.now().toString("yyyy년MM월dd일");
		bdto.setDate(date);
		System.out.println("date : " + date);

		String content = HttpServletRequest.getParameter("content");
		bdto.setContent(content);
		System.out.println("content : " + content);

		String title = HttpServletRequest.getParameter("title");
		bdto.setTitle(title);

		int no = Integer.parseInt(HttpServletRequest.getParameter("no"));
		bdto.setNo(no);

		bdao.inputB(bdto);

		return "redirect:List";
	}

	// 공지사항 상세보기
	@RequestMapping("/NoticeDetail")
	public String contact(HttpServletRequest HttpServletRequest, Model model) {
		System.out.println("NoticeDetail");
		String id = HttpServletRequest.getParameter("id");
		String nid = HttpServletRequest.getParameter("nid");
		System.out.println("id :  " + id);
		int no = Integer.parseInt(HttpServletRequest.getParameter("no"));
		System.out.println("no :  " + no);
		String reUrl = "";
		if (id == null) {
			System.out.println("id없자네!");
			reUrl = "redirect:Join";
		} else {
			System.out.println("notice 진입 성공");
			bdao = sqlSession.getMapper(BDAO.class);
			BDTO bdto = new BDTO();
			bdto.setId(nid);
			bdto.setNo(no);
			ArrayList<BDTO> nlist = bdao.detailN(bdto);
			System.out.println("nListsize : " + nlist.size());
			model.addAttribute("nlist", nlist);
			reUrl = "/Board/notice";
		}

		return reUrl;
		// 공지사항 상세하게 보기
	}

	// 가입 view
	@RequestMapping("/Join")
	public String Join() {
		System.out.println("join");
		return "/Board/join";
	}

	// 가입정보 입력
	@RequestMapping("/Joininput")
	public String Joininput(HttpServletRequest HttpServletRequest, Model model) {
		MDTO mdto = new MDTO();
		mdao = sqlSession.getMapper(MDAO.class);
		String id = HttpServletRequest.getParameter("id");
		mdto.setId(id);
		System.out.println("id : " + id);
		String pwd = HttpServletRequest.getParameter("pwd");
		mdto.setPwd(pwd);
		System.out.println("pwd : " + pwd);
		String name = HttpServletRequest.getParameter("name");
		mdto.setName(name);
		System.out.println("name : " + name);
		mdao.inputM(mdto);

		return "/Board/main";
	}

	// session 사용하기
	// login 성공 유무
	@RequestMapping(value = "/LoginPro", method = RequestMethod.POST)
	public String loginP(HttpSession session, @RequestParam("id") String id, @RequestParam("pwd") String pwd) {
		System.out.println("LoginPro Controller");
		// db에서 자료를 가져와야한다.
		// 아래의 소스처럼 mapper로 DAO소스를 가져와야한다. 하지만 지금은 사용안함ㅋ
		// member m = mapper.select(id);
		// 기존에 login이란 세션 값이 존재한다면
		// 기존값을 제거해 준다.
		if (session.getAttribute("id") != null) {
			session.removeAttribute("id");
		}
		MDTO mdto = new MDTO();
		mdao = sqlSession.getMapper(MDAO.class);
		mdto = mdao.selectOne(id);

		String reUrl = "";
		if (mdto != null) {
			session.setAttribute("id", id);
			System.out.println("LoginPro" + id);
			reUrl = "redirect:Main";
			System.out.println("로그인성공");
		} else {
			reUrl = "redirect:Join";
			System.out.println("로그인실패");
		}
		return reUrl;
	}

	@RequestMapping("/Logout")
	public String Logout(HttpSession session) {
		session.invalidate();

		return "redirect:Main";
		// logout
	}
}
