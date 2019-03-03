package org.lwt.tour.Controller;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lwt.tour.jackson.mixin.CityMixin;
import org.lwt.tour.jackson.mixin.ProvinceMixin;
import org.lwt.tour.jackson.mixin.ScenicMixin;
import org.lwt.tour.model.Admin;
import org.lwt.tour.model.City;
import org.lwt.tour.model.Country;
import org.lwt.tour.model.Province;
import org.lwt.tour.model.Scenic;
import org.lwt.tour.model.ScenicClass;
import org.lwt.tour.service.impl.CityManager;
import org.lwt.tour.service.impl.CountryManager;
import org.lwt.tour.service.impl.ProvinceManager;
import org.lwt.tour.service.impl.ScenicClassManager;
import org.lwt.tour.service.impl.ScenicManager;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/scenic")
@SessionAttributes("currUser")
public class ScenicController {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	private CityManager cityMgr;
	private ProvinceManager provinceMgr;
	private CountryManager countryMgr;
	private ScenicClassManager scenicClassMgr;
	private ScenicManager scenicMgr;

	public ProvinceManager getProvinceMgr() {
		return provinceMgr;
	}

	@Autowired
	@Qualifier("provinceMgr")
	public void setProvinceMgr(ProvinceManager provinceMgr) {
		this.provinceMgr = provinceMgr;
	}
	
	public CityManager getCityMgr() {
		return cityMgr;
	}
	
	@Autowired
	@Qualifier("cityMgr")
	public void setCityMgr(CityManager cityMgr) {
		this.cityMgr = cityMgr;
	}

	public CountryManager getCountryMgr() {
		return countryMgr;
	}

	@Autowired
	@Qualifier("countryMgr")
	public void setCountryMgr(CountryManager countryMgr) {
		this.countryMgr = countryMgr;
	}
	
	public ScenicClassManager getScenicClassMgr() {
		return scenicClassMgr;
	}

	@Autowired
	@Qualifier("scenicClassMgr")
	public void setScenicClassMgr(ScenicClassManager scenicClassMgr) {
		this.scenicClassMgr = scenicClassMgr;
	}
	
	public ScenicManager getScenicMgr() {
		return scenicMgr;
	}

	@Autowired
	@Qualifier("scenicMgr")
	public void setScenicMgr(ScenicManager scenicMgr) {
		this.scenicMgr = scenicMgr;
	}
	
	//***************  view controller ********************//
	
	//�������
	@RequestMapping(value="/view") 
	public String objectView(@ModelAttribute("currUser") Admin admin,Model model) {
		List<Scenic> scenicList = scenicMgr.getScenicDao().getAllObject();
		List<Country>  countryList = countryMgr.getCountryDao().getAllObject();
		List<ScenicClass>  scenicClassList = scenicClassMgr.getScenicClassDao().getAllObject();
		model.addAttribute("scenicList",scenicList);
		model.addAttribute("countryList",countryList);
		model.addAttribute("scenicClassList",scenicClassList);
		model.addAttribute("admin",admin);
		return "admin/scenic";
	}
	
	//��������
	@RequestMapping(value="/view/new",method=RequestMethod.GET)
	public String newObjectInfo( Model model) {
		Scenic scenic = new Scenic();
		List<Country> countryList = countryMgr.getCountryDao().getAllObject();
		List<ScenicClass>  scenicClassList = scenicClassMgr.getScenicClassDao().getAllObject();
		model.addAttribute("scenic",scenic);
		model.addAttribute("countryList",countryList);
		model.addAttribute("scenicClassList",scenicClassList);
		model.addAttribute("type",1);
		return "admin/scenicForm";
	}
	
	//�༭����
	@RequestMapping(value="/view/edit",params="id",method=RequestMethod.GET)
	public String editObjectInfo(@RequestParam int id, Model model) {
		Scenic scenic = scenicMgr.getScenicDao().get(id);
		List<Country> countryList = countryMgr.getCountryDao().getAllObject();
		List<ScenicClass>  scenicClassList = scenicClassMgr.getScenicClassDao().getAllObject();
		model.addAttribute("scenic",scenic);
		model.addAttribute("countryList",countryList);
		model.addAttribute("scenicClassList",scenicClassList);
		model.addAttribute("type",2);
		return "admin/scenicForm";
	}
	
	//��������
	@RequestMapping(value="/revealObject") 
	public String revealObject(HttpServletRequest request,Model model) {
		String cityword = request.getParameter("cityword");
		String classword = request.getParameter("classword");
		List<Scenic> scenicList = scenicMgr.getScenicDao().revealObject(cityword,classword);
		List<Country>  countryList = countryMgr.getCountryDao().getAllObject();
		List<ScenicClass>  scenicClassList = scenicClassMgr.getScenicClassDao().getAllObject();
		model.addAttribute("scenicList",scenicList);
		model.addAttribute("countryList",countryList);
		model.addAttribute("scenicClassList",scenicClassList);
		return "admin/scenic";
	}
	
	//******************** user view **********************//
	@RequestMapping(value="/index/view/{id}",method=RequestMethod.GET)
	public String indexScenicView(@PathVariable int id, Model model) {
		Scenic scenic = scenicMgr.getScenicDao().get(id);
		model.addAttribute("scenic",scenic);
		return "scenic";
	}
	
	@RequestMapping(value="/index/all",method=RequestMethod.GET)
	public String indexAllScenic( Model model) {
		List<Scenic> scenicList = scenicMgr.getScenicDao().getAllObject();
		List<Country>  countryList = countryMgr.getCountryDao().getAllObject();
		List<ScenicClass>  scenicClassList = scenicClassMgr.getScenicClassDao().getAllObject();
		model.addAttribute("scenicList",scenicList);
		model.addAttribute("countryList",countryList);
		model.addAttribute("scenicClassList",scenicClassList);
		return "allScenic";
	}
	
	//��������
	@RequestMapping(value="/index/revealObject") 
	public String revealIndexObject(HttpServletRequest request,Model model) {
		String cityword = request.getParameter("cityword");
		String classword = request.getParameter("classword");
		List<Scenic> scenicList = scenicMgr.getScenicDao().revealObject(cityword,classword);
		List<Country>  countryList = countryMgr.getCountryDao().getAllObject();
		List<ScenicClass>  scenicClassList = scenicClassMgr.getScenicClassDao().getAllObject();
		model.addAttribute("scenicList",scenicList);
		model.addAttribute("countryList",countryList);
		model.addAttribute("scenicClassList",scenicClassList);
		return "allScenic";
	}
	
	//***************  data controller ********************//
	
	// ��������
	@ResponseBody
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	private MessageObject newObjectInfo( @RequestBody Scenic scenic, Model model) {
		MessageObject messageObject = new MessageObject();
		try {
			scenicMgr.saveObject(scenic);
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
	private MessageObject editObjectInfo( @RequestBody Scenic scenic, Model model) {
		MessageObject messageObject = new MessageObject();
		try {
			scenicMgr.updateObject(scenic);
			messageObject.setMessage("�޸ĳɹ�!");
			messageObject.setStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
			messageObject.setMessage("�޸�ʧ��!");
			messageObject.setStatus(false);
		}
		
		return messageObject;
	}
	
	//ɾ��
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	private MessageObject delObjectInfo(@RequestParam("id") int[] ids) {
		MessageObject messageObject = new MessageObject();
		try {
			scenicMgr.delObject(ids);
			messageObject.setMessage("ɾ���ɹ�!");
			messageObject.setStatus(true);
		} catch (Exception e) {
			// TODO: handle exception
			messageObject.setMessage("ɾ��ʧ��!");
			messageObject.setStatus(false);
		}
		return messageObject;
	}
	
	//���ݹ��ҷ���ʡ��
	@RequestMapping(value="/getProvinceBycountry", method=RequestMethod.GET)
	@ResponseBody
	public String getProvinceBycountry(HttpServletRequest request,HttpServletResponse response){
		String cId = request.getParameter("cId");
		objectMapper.addMixInAnnotations(Province.class, ProvinceMixin.BasicInfo.class);
		List<Province> provinceList = provinceMgr.getProvinceBycountry(cId);
		String json = ""; 
		try {
			json = objectMapper.writeValueAsString(provinceList);
			
			ServletOutputStream os = response.getOutputStream(); //��ȡ�����  
            os.write(json.getBytes(Charset.forName("UTF-8"))); //��json����д������  
            os.flush(); 
            
		}catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//����ʡ�᷵��city
	@RequestMapping(value="/getCityByProvince", method=RequestMethod.GET)
	@ResponseBody
	public String getCityByProvince(HttpServletRequest request,HttpServletResponse response){
		String pId = request.getParameter("pId");
		objectMapper.addMixInAnnotations(City.class, CityMixin.BasicInfo.class);
		List<City> cityList =cityMgr.getCityByProvince(pId);
		String json = ""; 
		try {
			json = objectMapper.writeValueAsString(cityList);
			
			ServletOutputStream os = response.getOutputStream(); //��ȡ�����  
            os.write(json.getBytes(Charset.forName("UTF-8"))); //��json����д������  
            os.flush(); 
            
		}catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//���ݾ�����෵�ؾ���
	@RequestMapping(value="/getObjectByCtg/{ctgId}",method=RequestMethod.GET) 
	@ResponseBody
	public String getObjectByCtg(@PathVariable String ctgId, HttpServletRequest request,HttpServletResponse response) {
		objectMapper.addMixInAnnotations(Scenic.class, ScenicMixin.BasicInfo.class);
		List<Scenic> scenicList = scenicMgr.getObjectByCtg(ctgId);
		String json = ""; 
		try {
			json = objectMapper.writeValueAsString(scenicList);
			
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
