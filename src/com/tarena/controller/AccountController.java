package com.tarena.controller;

import java.sql.Timestamp;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.tarena.dao.AccountDao;
import com.tarena.dao.ServiceDao;
import com.tarena.entity.Account;
import com.tarena.entity.page.AccountPage;

@Controller
@RequestMapping("/account")
@SessionAttributes("accountPage")
public class AccountController extends BaseController {
	
	@Resource
	private AccountDao accountDao;
	
	@Resource
	private ServiceDao serviceDao;

	@RequestMapping("/findAccount.do")
	public String find(AccountPage page, Model model) {
		page.setRows(accountDao.findRows(page));
		model.addAttribute("accountPage", page);
		
		List<Account> list = accountDao.findByPage(page);
		model.addAttribute("accounts", list);
		
		return "account/account_list";
	}
	
	@RequestMapping("/startAccount.do")
	public String updateStart(@RequestParam("account_id") int id) {
		//开通账务账号
		Account a = new Account();
		a.setAccount_id(id);
		a.setStatus("0");
		accountDao.updateStatus(a);
		return "redirect:findAccount.do";
	}
	
	@RequestMapping("/pauseAccount.do")
	public String updatePause(@RequestParam("account_id") int id) {
		//暂停账务账号
		Account a = new Account();
		a.setAccount_id(id);
		a.setStatus("1");
		accountDao.updateStatus(a);
		
		//暂停其下属的业务账号
		serviceDao.pauseByAccount(id);
		
		return "redirect:findAccount.do";
	}
	
	@RequestMapping("/deleteAccount.do")
	public String updateDelete(@RequestParam("account_id") int id) {
		//暂停账务账号
		Account a = new Account();
		a.setAccount_id(id);
		a.setStatus("2");
		accountDao.updateStatus(a);
		
		//删除其下属的业务账号
		serviceDao.deleteByAccount(id);
		
		return "redirect:findAccount.do";
	}
	
	@RequestMapping("/toUpdateAccount.do")
	public String toUpdate(@RequestParam("account_id") int id, Model model) {
		Account a = accountDao.findById(id);
		model.addAttribute("account", a);
		return "account/update_account";
	}
	
	@RequestMapping("/updateAccount.do")
	public String update(Account account) {
		accountDao.update(account);
		return "redirect:findAccount.do";
	}
	
	@RequestMapping("/toAddAccount.do")
	public String toAdd() {
		return "account/add_account";
	}
	
	@RequestMapping("/addAccount.do")
	public String add(Account account) {
		account.setStatus("0");
		account.setCreate_date(
			new Timestamp(System.currentTimeMillis()));
		accountDao.save(account);
		return "redirect:findAccount.do";
	}

}
