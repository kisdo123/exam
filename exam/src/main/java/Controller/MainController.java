package Controller;

import java.awt.color.CMMException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.runtime.DotClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.xdevapi.JsonArray;

import Admin.DTO.Admin;
import Admin.Service.AdminService;
import Commute.DAO.CommuteDAO;
import Commute.DTO.Commute;
import Commute.DTO.DateData;
import Commute.Service.CommuteService;
import Exception.AlreadyAttend;
import Exception.FailAttendCheck;
import Exception.FailInsertCommute;
import Exception.FailJoinUser;
import Exception.FailUpdateCommute;
import Exception.NotFoundCommtues;
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

	@Autowired
	private AdminService adminService;

	
	@Scheduled(cron = "0 2 18 * * *")
	public void main() {
		try {
			String access_token = "ZQAAAbCOKETePdyxkSVv-Pdqs14uIw12YBTV5mFNpTHLAxbrcXn7PQfSaqCZSPGAi3JBQxzSaOOsZRVo1MOUQhAri1DK8V7_xkQEcnN5KeDuLdWC";
			String sendToken = "access_token="+URLEncoder.encode(access_token, "UTF-8");
			String band_key = "AACJ3FlYYxAvdo3pvqiEzAqL";
			String sendKey = "band_key="+URLEncoder.encode(band_key, "UTF-8");
			
			List<Admin> admins = adminService.getAllData();
			
			String content = null;
			for(Admin admin : admins) {
				content += admin.getName();
				content += admin.getAttend();
				content += admin.getVacation();
				content += admin.getTextData() + "\n";
			}
			System.out.println(content);
			
			String sendContent = "content="+URLEncoder.encode(content, "UTF-8");
			
			URL url = new URL("https://openapi.band.us/v2.2/band/post/create");
			URLConnection conn = url.openConnection();

			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			DataOutputStream out = null;

			try {
				out = new DataOutputStream(conn.getOutputStream());
				out.writeBytes(sendToken);
				out.writeBytes(sendKey);
				out.writeBytes(sendContent);
				out.flush();
			} finally {
				if (out != null)
					out.close();
			}

			InputStream is = conn.getInputStream();
			Scanner scan = new Scanner(is);

			int line = 1;
			while (scan.hasNext()) {
				String str = scan.nextLine();
				System.out.println((line++) + ":" + str);
			}
			scan.close();

		} catch (MalformedURLException e) {
			System.out.println("The URL address is incorrect.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("It can't connect to the web page.");
			e.printStackTrace();
		}
	}

	// 밴드
	@RequestMapping("/bandForm.do")
	public String bandForm(Model model) {
		List<Admin> admins = adminService.getAllData();
		model.addAttribute("admins", admins);
		return "bandPage";
	}

	// loginFilter 결과페이지로 리턴
	@RequestMapping("/loginFilter.do")
	public String loginFilter(HttpServletRequest request) {
		request.setAttribute("loginFilter", true);
		return "result/pageFail";
	}

	// loginCheckFilter 결과페이지로 리턴
	@RequestMapping("/logging.do")
	public String loging(HttpServletRequest request) {
		request.setAttribute("logging", true);
		return "result/pageFail";
	}

	// adminFilter 결과페이지로 리턴
	@RequestMapping("/adminFilter.do")
	public String adminFilter(HttpServletRequest request) {
		request.setAttribute("adminFilter", true);
		return "result/pageFail";
	}

	// 메인화면으로 보냄
	@RequestMapping("/main.do")
	public String main(Model model, HttpServletRequest request) {
		return "main";
	}

	// 로그인폼 요청
	@RequestMapping("/loginForm.do")
	public String loginForm(Model model) {
		return "pagelogin";
	}

	// 로그인
	@RequestMapping("/login.do")
	public String login(Model model, HttpServletRequest request, @RequestParam("id") String id,
			@RequestParam("pw") String pw) {
		try {
			// 아이디와 비밀번호확인후 로그인
			User loginUser = userService.login(id, pw);

			// 로그인후 세션에 저장
			request.getSession().setAttribute("loginUser", loginUser);
			request.setAttribute("login", true);
			return "result/pageSuccess";
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

	// 회원가입폼 요청
	@RequestMapping("/joinForm.do")
	public String joinForm(Model model) {
		return "pagejoin";
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

	// 중복검사
	@RequestMapping("/idDuplication.do")
	@ResponseBody
	public String idDuplication(@RequestParam("id") String id) {
		// 결과를 boolean으로 받는다
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
		// 세션에 로그인 정보 삭제
		request.getSession().setAttribute("loginUser", null);
		// 모든 쿠키 제거
		deleteCookies(request, response);
		return "redirect:/loginForm.do";
	}

	// 출근하기
	@RequestMapping("/attendForm.do")
	public String attend(Model model, HttpServletRequest request) {
		// 세션에 저장된 로그인 정보를 가져옴
		User loginUser = (User) request.getSession().getAttribute("loginUser");

		// 회원번호 저장
		int userNo = loginUser.getUserNo();
		String name = loginUser.getName();

		User user = new User();
		user.setUserNo(userNo);
		user.setName(name);

		try {
			// 출퇴근 insert
			Commute comm = commuteService.commuteInsert(user);
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
		} catch (NotFoundCommtues e) {
			e.printStackTrace();
			request.setAttribute("NotFoundCommtues", true);
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
			Commute commute = commuteService.checkAndUpdate(userNo);
			String leaveTime = commute.getLeaved();
			request.setAttribute("leave", true);
			request.setAttribute("leaveTime", leaveTime);
			return "result/pageSuccess";

		} catch (FailAttendCheck e) {
			e.printStackTrace();
			request.setAttribute("FailAttendCheck", true);
			request.setAttribute("ret", "/exam/main.do");

		} catch (FailUpdateCommute e) {
			e.printStackTrace();
			request.setAttribute("FailUpdateCommute", true);
			request.setAttribute("ret", "/exam/main.do");
		} catch (NotFoundCommtues e) {
			e.printStackTrace();
			request.setAttribute("NotFoundCommtues", true);
			request.setAttribute("ret", "/exam/main.do");
		}
		return "result/pageFail";
	}

	// 달 이동
	@RequestMapping("/moveCommute.do")
	public String moveCommute(Model model, HttpServletRequest request) {

		// 세션에 저장된 로그인 정보를 가져옴
		User loginUser = (User) request.getSession().getAttribute("loginUser");

		// 회원정보 저장
		int userNo = loginUser.getUserNo();
		String checkDate = request.getParameter("checkDate");
		String toYear;
		String toMonth;
		// String toTime = null;
		try {

			// 파라미터가 존재 확인
			if (checkDate != null) {
				// 존재하면 가공
				toYear = checkDate.substring(0, 4);
				toMonth = checkDate.substring(5, 7);
			} else {
				// 파라미터가 존재하지않으면 셋팅
				Calendar cal = Calendar.getInstance();
				int year = cal.get(cal.YEAR);
				int month = cal.get(cal.MONTH) + 1;

				// 형변환
				toYear = Integer.toString(year);
				toMonth = Integer.toString(month);

			}

			// DateData에 셋팅
			DateData dateData = new DateData();
			dateData.setToYear(toYear);
			dateData.setToMonth(toMonth);
			dateData.setUserNo(userNo);
			// dateData.setToTime(toTime);

			// 출근일을 가져온후 main에 보낸다.
			List<Commute> commutes = commuteService.dateCompare(dateData);
			model.addAttribute("commutes", commutes);
			return "main";

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("CalendarException", true);
			request.setAttribute("ret", "/exam/main.do");
		}
		return "result/pageFail";
	}

	// 관리자페이지로 이동
	@RequestMapping("/adminPage.do")
	public String adminPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		// 모든 쿠키 제거
		deleteCookies(request, response);
		request.setAttribute("adminPage", true);
		return "result/pageSuccess";
	}

	// 관리자 달력 이동
	@RequestMapping("/adminAllCommute.do")
	public String adminAllCommute(Model model, HttpServletRequest request) {
		String checkDate = request.getParameter("checkDate");
		String toYear;
		String toMonth;
		try {

			// 파라미터가 존재 확인
			if (checkDate != null) {
				// 존재하면 가공
				toYear = checkDate.substring(0, 4);
				toMonth = checkDate.substring(5, 7);
			} else {
				// 파라미터가 존재하지않으면 셋팅
				Calendar cal = Calendar.getInstance();
				int year = cal.get(cal.YEAR);
				int month = cal.get(cal.MONTH) + 1;

				// 형변환
				toYear = Integer.toString(year);
				toMonth = Integer.toString(month);

			}

			// DateData에 셋팅
			DateData dateData = new DateData();
			dateData.setToYear(toYear);
			dateData.setToMonth(toMonth);
			// dateData.setToTime(toTime);

			// 출근일을 가져온후 main에 보낸다.
			List<Commute> commutes = adminService.getAllUserData(dateData);
			model.addAttribute("commutes", commutes);
			return "adminPage";

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("CalendarException", true);
			request.setAttribute("ret", "/exam/main.do");
		}
		return "result/pageFail";
	}

	public void deleteCookies(HttpServletRequest request, HttpServletResponse response) {
		// 모든 쿠키 제거
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}
		}
	}

}
