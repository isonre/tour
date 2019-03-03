package org.lwt.tour.Controller;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lwt.tour.jackson.mixin.LineMixin;
import org.lwt.tour.jackson.mixin.ScenicMixin;
import org.lwt.tour.model.Account;
import org.lwt.tour.model.Admin;
import org.lwt.tour.model.City;
import org.lwt.tour.model.Country;
import org.lwt.tour.model.Line;
import org.lwt.tour.model.LineClass;
import org.lwt.tour.model.Province;
import org.lwt.tour.model.Scenic;
import org.lwt.tour.model.ScenicClass;
import org.lwt.tour.model.User;
import org.lwt.tour.service.impl.AccountManager;
import org.lwt.tour.service.impl.LineClassManager;
import org.lwt.tour.service.impl.LineManager;
import org.lwt.tour.service.impl.UserManager;
import org.lwt.tour.tool.MessageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/line")
@SessionAttributes("currUser")
public class LineController {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	private LineManager lineMgr;
	private LineClassManager lineClassMgr;

	public LineManager getLineMgr() {
		return lineMgr;
	}
	
	@Autowired
	@Qualifier("lineMgr")
	public void setLineMgr(LineManager lineMgr) {
		this.lineMgr = lineMgr;
	}

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
		List<Line> lineList = lineMgr.getLineDao().getAllObject();
		List<LineClass> lineClassList = lineClassMgr.getLineClassDao().getAllObject();
		model.addAttribute("lineList",lineList);
		model.addAttribute("lineClassList",lineClassList);
		model.addAttribute("admin",admin);
		return "admin/line";
	}
	
	//��������
	@RequestMapping(value="/view/new",method=RequestMethod.GET)
	public String newObjectInfo( Model model) {
		Line line = new Line();
		List<LineClass> lineClassList = lineClassMgr.getLineClassDao().getAllObject();
		model.addAttribute("line",line);
		model.addAttribute("lineClassList",lineClassList);
		model.addAttribute("type",1);
		return "admin/lineForm";
	}
	
	//�༭����
	@RequestMapping(value="/view/edit",params="id",method=RequestMethod.GET)
	public String editObjectInfo(@RequestParam int id, Model model) {
		Line line =lineMgr.getLineDao().get(id);
		List<LineClass> lineClassList = lineClassMgr.getLineClassDao().getAllObject();
		model.addAttribute("line",line);
		model.addAttribute("lineClassList",lineClassList);
		model.addAttribute("type",2);
		return "admin/lineForm";
	}
	
	//����
	@RequestMapping(value="/revealObject") 
	public String revealObject(HttpServletRequest request,Model model) {
		
		String classword = request.getParameter("classword");
		String keyword = request.getParameter("keyword");
		
		List<Line> lineList = lineMgr.getLineDao().revealObject(classword,keyword);
		List<LineClass> lineClassList = lineClassMgr.getLineClassDao().getAllObject();
		model.addAttribute("lineList",lineList);
		model.addAttribute("lineClassList",lineClassList);
		return "admin/line";
	}
	
	//******************** user view **********************//
	@RequestMapping(value="/index/view/{id}",method=RequestMethod.GET)
	public String indexLineView(@PathVariable int id, Model model) {
		Line line = lineMgr.getLineDao().get(id);
		model.addAttribute("line",line);
		return "line";
	}
	
	@RequestMapping(value="/index/all",method=RequestMethod.GET)
	public String indexAllLine( Model model) {
		List<Line> lineList = lineMgr.getLineDao().getAllObject();
		List<LineClass> lineClassList = lineClassMgr.getLineClassDao().getAllObject();
		model.addAttribute("lineList",lineList);
		model.addAttribute("lineClassList",lineClassList);
		return "allLine";
	}
	
	@RequestMapping(value="/index/revealObject") 
	public String revealIndexObject(HttpServletRequest request,Model model) {
		
		String classword = request.getParameter("classword");
		String keyword = request.getParameter("keyword");
		
		List<Line> lineList = lineMgr.getLineDao().revealObject(classword,keyword);
		List<LineClass> lineClassList = lineClassMgr.getLineClassDao().getAllObject();
		model.addAttribute("lineList",lineList);
		model.addAttribute("lineClassList",lineClassList);
		return "allLine";
	}
	//***************  data controller ********************//
	
	// ��������
	@ResponseBody
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	private MessageObject newObjectInfo( @RequestBody Line line, Model model) {
		MessageObject messageObject = new MessageObject();
		try {
			lineMgr.saveObject(line);
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
	private MessageObject editObjectInfo( @RequestBody Line line, Model model) {
		MessageObject messageObject = new MessageObject();
		try {
			lineMgr.updateObject(line);
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
			lineMgr.delObject(ids);
			messageObject.setMessage("ɾ���ɹ�!");
			messageObject.setStatus(true);
		} catch (Exception e) {
			// TODO: handle exception
			messageObject.setMessage("ɾ��ʧ��!");
			messageObject.setStatus(false);
		}
		return messageObject;
	}
	
	//���ݾ�����෵�ؾ���
	@RequestMapping(value="/getObjectByCtg/{ctgId}",method=RequestMethod.GET) 
	@ResponseBody
	public String getObjectByCtg(@PathVariable String ctgId, HttpServletRequest request,HttpServletResponse response) {
		objectMapper.addMixInAnnotations(Line.class, LineMixin.BasicInfo.class);
		List<Line> lineList = lineMgr.getObjectByCtg(ctgId);
		String json = ""; 
		try {
			json = objectMapper.writeValueAsString(lineList);
			
			ServletOutputStream os = response.getOutputStream(); //��ȡ�����  
            os.write(json.getBytes(Charset.forName("UTF-8"))); //��json����д������  
            os.flush(); 
            
		}catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
