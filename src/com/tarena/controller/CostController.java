package com.tarena.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tarena.dao.CostDao;
import com.tarena.entity.Account;
import com.tarena.entity.Cost;
import com.tarena.entity.page.CostPage;


@RequestMapping("/cost")
@Controller
@SessionAttributes("costPage")
public class CostController extends BaseController {

	@Resource
	private CostDao costDao;

//	@RequestMapping("/findCost.do")
//	public String find(Model model) {
//		List<Cost> list = costDao.findAll();
//		model.addAttribute("costs", list);
//		return "cost/cost_list";
//	}
	
	@RequestMapping("/findCost.do")
	public String find(CostPage page, Model model) {
		page.setRows(costDao.findRows());
		model.addAttribute("costPage", page);
		
		List<Cost> list = costDao.findByPage(page);
		model.addAttribute("costs", list);
		
		return "cost/cost_list";
	}
	@RequestMapping("/startCost.do")
	public String updateStart(@RequestParam("cost_id") int id) {
		//‘›Õ£’ÀŒÒ’À∫≈
		Cost c = new Cost();
		c.setCost_id(id);
		c.setStatus("0");
		costDao.updateStatus(c);
		return "redirect:findCost.do";
	}
	
	@RequestMapping("/pauseCost.do")
	public String updatePause(@RequestParam("cost_id") int id) {
		//‘›Õ£’ÀŒÒ’À∫≈
		Cost c = new Cost();
		c.setCost_id(id);
		c.setStatus("1");
		costDao.updateStatus(c);
		return "redirect:findCost.do";
	}
	
	  @RequestMapping("/deleteCost1.do") 
	  public String updateDelete(@RequestParam("cost_id") int id) {
	  Cost c = new Cost();
	  c.setCost_id(id); 
	  c.setStatus("2"); 
	  costDao.updateStatus(c);
	  
	  return "redirect:findCost.do";
	  }
	 
	
	
	@RequestMapping("/toAddCost.do")
	public String toAdd() {
		return "cost/add_cost";
	}
	
	@RequestMapping("/addCost.do")
	public String add(Cost cost) {
		cost.setStatus("1");
		cost.setCreatime(
			new Timestamp(System.currentTimeMillis()));
		costDao.save(cost);
		return "redirect:findCost.do";
	}
	
	@RequestMapping("/toUpdateCost.do")
	public String toUpdate(
		@RequestParam("id") int id, Model model) {
		Cost cost = costDao.findById(id);
		model.addAttribute("costs", cost);
		return "cost/update_cost";
	}

	@RequestMapping("/updateCost.do")
	public String update(Cost cost) {
		costDao.update(cost);
		return "redirect:findCost.do";
	}
	
	@RequestMapping("/deleteCost.do")
	public String delete(@RequestParam("id") int id) {
		costDao.delete(id);
		return "redirect:findCost.do";
	}
	
}
