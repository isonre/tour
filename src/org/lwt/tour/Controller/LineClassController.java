package org.lwt.tour.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.lwt.tour.model.Admin;
import org.lwt.tour.model.LineClass;
import org.lwt.tour.service.impl.LineClassManager;
import org.lwt.tour.tool.MessageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/lineClass")
@SessionAttributes("currUser")
public class LineClassController {
	
	private LineClassManager lineClassMgr;
	

	public LineClassManager getLineClassMgr() {
		return lineClassMgr;
	}

	@Autowired
	@Qualifier("lineClassMgr")
	public void setLineClassMgr(LineClassManager lineClassMgr) {
		this.lineClassMgr = lineClassMgr;
	}

	//***************  view controller ********************//
	
	//�������
	@RequestMapping(value="/view") 
	public String objectView(@ModelAttribute("currUser") Admin admin,Model model) {
		List<LineClass> lineClassList = lineClassMgr.getLineClassDao().getAllObject();
		model.addAttribute("lineClassList",lineClassList);
		model.addAttribute("admin",admin);
		return "admin/lineClass";
	}

	//��������
	@RequestMapping(value="/view/new",method=RequestMethod.GET)
	public String newObjectInfo( Model model) {
		LineClass lineClass = new LineClass();
		model.addAttribute("lineClass",lineClass);
		model.addAttribute("type",1);
		return "admin/lineClassForm";
	}
	
	//�༭����
	@RequestMapping(value="/view/edit",params="id",method=RequestMethod.GET)
	public String editObjectInfo(@RequestParam int id, Model model) {
		LineClass lineClass = lineClassMgr.getLineClassDao().get(id);
		model.addAttribute("lineClass",lineClass);
		model.addAttribute("type",2);
		return "admin/lineClassForm";
	}
	
	//����ͼ�����
	@RequestMapping(value="/revealObject") 
	public String revealObject(HttpServletRequest request,Model model) {
		String keyword = request.getParameter("keyword");
		
		List<LineClass> lineClassList = lineClassMgr.getLineClassDao().revealObject(keyword);
		model.addAttribute("lineClassList",lineClassList);
		return "admin/lineClass";
	}
	
	//***************  data controller ********************//
	
	// ��������
	@ResponseBody
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	private MessageObject newObjectInfo( @RequestBody LineClass lineClass, Model model) {
		MessageObject messageObject = new MessageObject();
		try {
			lineClassMgr.saveObject(lineClass);
			messageObject.setMessage("����ɹ�!");
			messageObject.setStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
			messageObject.setMessage("����ʧ��!");
			messageObject.setStatus(false);
		}
		
		return messageObject;
	}
	
	// �༭����
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	private MessageObject editObjectInfo( @RequestBody LineClass lineClass, Model model) {
		MessageObject messageObject = new MessageObject();
		try {
			lineClassMgr.updateObject(lineClass);
			messageObject.setMessage("�޸ĳɹ�!");
			messageObject.setStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
			messageObject.setMessage("�޸�ʧ��!");
			messageObject.setStatus(false);
		}
		
		return messageObject;
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	//ɾ��
	private MessageObject delObjectInfo(@RequestParam("id") int[] ids) {
		MessageObject messageObject = new MessageObject();
		try {
			lineClassMgr.delObject(ids);
			messageObject.setMessage("ɾ���ɹ�!");
			messageObject.setStatus(true);
		} catch (Exception e) {
			// TODO: handle exception
			messageObject.setMessage("ɾ��ʧ��!");
			messageObject.setStatus(false);
		}
		return messageObject;
	}
}
