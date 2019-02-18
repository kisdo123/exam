package Controller;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.xdevapi.JsonArray;

import Commute.DAO.CommuteDAO;
import Commute.DTO.Commute;
import Commute.DTO.DateData;
import Commute.Service.CommuteService;
import Exception.AlreadyAttend;
import Exception.FailAttendCheck;
import Exception.FailInsertCommute;
import Exception.FailJoinUser;
import Exception.FailUpdateCommute;
import Exception.PasswordNotMatch;
import Exception.UserNotFoundException;
import User.DTO.User;
import User.Service.UserService;

@Controller
public class MainController {

	@Autowired
	private UserService userService;

	@Autowired
	private CommuteService commuteService;

	// loginFilter 결과페이지로 리턴
	@RequestMapping("/loginFilter.do")
	public String loginFilter(HttpServletRequest request) {
		request.setAttribute("loginFilter", true);
		return "result/pageFail";
	}

	// 로그인폼 요청
	@RequestMapping("/loginForm.do")
	public String loginForm(Model model) {
		return "pagelogin";
	}

	// 회원가입폼 요청
	@RequestMapping("/joinForm.do")
	public String joinForm(Model model) {
		return "pagejoin";
	}

	// 중복검사
	@RequestMapping("/idDuplication.do")
	@ResponseBody
	public String idDuplication(@RequestParam("id") String id) {
		//결과를 boolean으로 받는다.
		Boolean res = userService.idDuplicate(id);
		String result = "";
		if (res) {
			result = "true";
		} else {
			result = "false";
		}
		return result;
	}

	// 로그아웃
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		//세션에 로그인 정보 삭제
		request.getSession().setAttribute("loginUser", null);
		
		//모든 쿠키 제거
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}
		}
		
		return "redirect:/loginForm.do";
	}

	// 회원가입
	@RequestMapping("/join.do")
	public String join(Model model, HttpServletRequest request, @RequestParam("id") String id,
			@RequestParam("pw") String pw, @RequestParam("name") String name) {
		try {
			// 객체 생성후 데이터 삽입
			User user = new User();
			user.setId(id);
			user.setPw(pw);
			user.setName(name);

			// 회원가입
			userService.join(user);
			request.setAttribute("join", true);
			return "result/pageSuccess";
		} catch (FailJoinUser e) {
			e.printStackTrace();
			request.setAttribute("registerFail", true);
			request.setAttribute("ret", "/exam/loginForm.do");
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			request.setAttribute("userExist", true);
		}
		return "result/pageFail";
	}

	// 메인화면으로 보냄
	@RequestMapping("/main.do")
	public String main(Model model, HttpServletRequest request) {
		return "main";
	}

	// 출근하기
	@RequestMapping("/attendForm.do")
	public String attend(Model model, HttpServletRequest request) {
		// 세션에 저장된 로그인 정보를 가져옴
		User loginUser = (User) request.getSession().getAttribute("loginUser");

		// 회원번호 저장
		int userNo = loginUser.getUserNo();

		// Commute객체에 회원번호 셋팅
		Commute commute = new Commute();
		commute.setUserNo(userNo);

		try {
			// 출퇴근 insert
			Commute comm = commuteService.commuteInsert(userNo);
			String attendTime = comm.getAttend();
			request.setAttribute("attend", true);
			request.setAttribute("attendTime", attendTime);
			return "result/pageSuccess";

		} catch (FailInsertCommute e) {
			e.printStackTrace();
			request.setAttribute("FailInsertCommute", true);
			request.setAttribute("ret", "/exam/main.do");
		} catch (AlreadyAttend e) {
			e.printStackTrace();
			request.setAttribute("AlreadyAttend", true);
			request.setAttribute("ret", "/exam/main.do");
		}
		return "result/pageFail";

	}

	// 퇴근폼 요청
	@RequestMapping("/leaveForm.do")
	public String leave(Model model, HttpServletRequest request) {

		// 세션에 저장된 로그인 정보를 가져옴
		User loginUser = (User) request.getSession().getAttribute("loginUser");

		// 회원정보 저장
		int userNo = loginUser.getUserNo();

		try {
			// 퇴근 update
			commuteService.checkAndUpdate(userNo);
			request.setAttribute("leave", true);
			return "result/pageSuccess";
		} catch (FailAttendCheck e) {
			e.printStackTrace();
			request.setAttribute("FailAttendCheck", true);
			request.setAttribute("ret", "/exam/main.do");

		} catch (FailUpdateCommute e) {
			e.printStackTrace();
			request.setAttribute("FailUpdateCommute", true);
			request.setAttribute("ret", "/exam/main.do");
		}
		return "result/pageFail";
	}

	// 달 이동
	@RequestMapping(value = "/moveCommute.do", method = RequestMethod.GET)
	public String moveCommute(Model model, HttpServletRequest request) {

		// 세션에 저장된 로그인 정보를 가져옴
		User loginUser = (User) request.getSession().getAttribute("loginUser");

		// 회원정보 저장
		int userNo = loginUser.getUserNo();

		try {
			String checkDate = request.getParameter("checkDate");

			String toYear = checkDate.substring(0, 4);
			String toMonth = checkDate.substring(5, 7);

			// DateData에 셋팅
			DateData dateData = new DateData();
			dateData.setToYear(toYear);
			dateData.setToMonth(toMonth);
			dateData.setUserNo(userNo);

			// 출근일을 가져온후 main에 보낸다.
			List<Commute> comm = commuteService.dateCompare(dateData);
			model.addAttribute("comm", comm);
			return "main";

		} catch (NullPointerException e) {
			e.printStackTrace();
			request.setAttribute("CookieNotFoundException", true);
			request.setAttribute("ret", "/exam/main.do");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("CalendarException", true);
			request.setAttribute("ret", "/exam/main.do");
		}
		return "result/pageFail";
	}

	// 로그인
	@RequestMapping("/login.do")
	public String login(Model model, HttpServletRequest request, @RequestParam("id") String id,
			@RequestParam("pw") String pw) {
		try {
			// 아이디와 비밀번호확인후 로그인
			User user = userService.login(id, pw);

			// 로그인후 세션에 저장
			request.getSession().setAttribute("loginUser", user);

			// 세션에 저장한 User정보중 회원번호를 가져옴
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			int userNo = loginUser.getUserNo();

			// 현재 년,월을 가져옴
			Calendar cal = Calendar.getInstance();
			int year = cal.get(cal.YEAR);
			int month = cal.get(cal.MONTH) + 1;

			// 형변환
			String toYear = Integer.toString(year);
			String toMonth = Integer.toString(month);

			// DateData에 셋팅
			DateData dateData = new DateData();
			dateData.setToYear(toYear);
			dateData.setToMonth(toMonth);
			dateData.setUserNo(userNo);

			// 출근날짜를 화면에 보냄
			List<Commute> commutes = commuteService.dateCompare(dateData);
			model.addAttribute("commutes", commutes);

			return "main";
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			request.setAttribute("userNotFound", true);
			request.setAttribute("ret", "loginForm.do");

		} catch (PasswordNotMatch e) {
			e.printStackTrace();
			request.setAttribute("PasswordNotMatch", true);
			request.setAttribute("ret", "loginForm.do");
		}

		return "result/pageFail";
	}
}