<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
   <head>
	 <meta charset="utf-8">
	 <meta http-equiv="X-UA-Compatible" content="IE=edge">
	 <meta name="viewport" content="width=device-width, initial-scale=1">
   </head>
   <body>
	<div class="modal-content">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	    <h4 class="modal-title" id="myModalLabel">���ҷ�������/�༭</h4>
	  </div>
	  <div class="modal-body">
	
	    <!-- ����Ԫ ���� -->
	    <form id="objectForm" class="form-horizontal" method="post">
	      <div class="form-group">
	        
	        <input type="hidden" name="c_id" value="${country.c_id}"/>
	        <label for="c_name" class="col-sm-2 control-label"><span style="color:red;"> * </span>��������</label>
	        <div class="col-sm-3">
	          <input type="text" class="form-control" id="c_name" name="c_name" value="${country.c_name}">
	        </div>
	      </div>    
	    </form>
	  </div>
	  <div class="modal-footer">
	  <div class="form-group">
	    <button type="button" class="btn btn-success btn-white btn-round" onclick="objectSubmit();"> <i class="ace-icon fa fa-floppy-o bigger-125"></i>����</button>
	    <button type="button" class="btn btn-danger btn-white btn-round" data-dismiss="modal"><i class="ace-icon fa fa-times bigger-125"></i>�ر�</button>
	  </div>
	  </div>
	</div>
	
	<script>

 	 function objectSubmit(){  
 	 	var url= null;
		if(${type}==1){//��������
			url = "/tour/country/new";
		}else{
			url = "/tour/country/edit";
		}
     	$.ajax({
		  type: "post",
		  url: url,
		  async:false,
		  data: JSON.stringify($('#objectForm').serializeObject()),
		  dataType: "json",
		  contentType:"application/json",  
		  success: function(result){
		         if(result.status){
		         	$('#objectForm').parents('.modal').modal('hide');
		         	bootbox.confirm('�ɹ�!',function (result) {
	        	    	location.reload();
	        	    });
		         }else{
		         	bootbox.alert('����ʧ��');	
		         }
		  }
         });
      } 
	</script>
	</body>
</html>
