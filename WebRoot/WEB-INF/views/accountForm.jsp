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
	    <h4 class="modal-title" id="myModalLabel">�޸�����</h4>
	  </div>
	  <div class="modal-body">
	
	    <!-- ����Ԫ ���� -->
	    <form id="accountInfoForm" class="form-horizontal" modelAttribute="account" method="post">
	      <div class="form-group">
	        
	        <input type="hidden" name="a_id" value="${account.a_id}"/>
	        <input type="hidden" name="a_name" value="${account.a_name}"/>
	        <label for="password" class="col-sm-3 control-label"><span style="color:red;"> * </span>������������</label>
	        <div class="col-sm-3">
	          <input type="password" class="form-control" id="password" name="password">
	        </div>
	      </div>
	      <div class="form-group">
	        <label class="col-sm-3 control-label">�ٴ�ȷ������</label>
	        <div class="col-sm-3">
	          <input type="password" class="form-control" id="password1">
	        </div>
	      </div>    
	    </form>
	  </div>
	  <div class="modal-footer">
	  <div class="form-group">
	    <button type="button" class="btn btn-success btn-white btn-round" onclick="accountInfoSubmit();"> <i class="ace-icon fa fa-floppy-o bigger-125"></i>����</button>
	    <button type="button" class="btn btn-danger btn-white btn-round" data-dismiss="modal"><i class="ace-icon fa fa-times bigger-125"></i>�ر�</button>
	  </div>
	  </div>
	</div>
	
	<script>

 	 function accountInfoSubmit(){
	 	 if($('#password').val()==$('#password1').val())  {
	 	      	$.ajax({
			  type: "post",
			  url: "/tour/account/password/edit",
			  async:false,
			  data: JSON.stringify($('#accountInfoForm').serializeObject()),
			  dataType: "json",
			  contentType:"application/json",  
			  success: function(result){
			         if(result.status){
			         	$('#accountInfoForm').parents('.modal').modal('hide');
			         	alert('�޸ĳɹ�');
			         }else{
			         	bootbox.alert('����ʧ��');	
			         }
			  }
	         });
	 	 }else{
	 	 	alert('�����������벻һ��');
	 	 }
      } 
	</script>
	</body>
</html>
