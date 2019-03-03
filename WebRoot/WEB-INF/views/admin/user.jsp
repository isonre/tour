<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="/tour/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/tour/resources/css/dashboard.css" rel="stylesheet">
    <script src="/tour/resources/js/jquery.min.js" type="text/javascript"></script>

  </head>
    
    <body>

      		<!-- add and edit modal -->
		    <div class="modal fade" id="addOrEditDialog" tabindex="-1" role="dialog" aria-labelledby="ũ��Ʒ����༭" data-backdrop="static">
		      <div class="modal-dialog" role="document">
		        <div class="modal-content">
		          <!-- content -->
		        </div>
		      </div>
		    </div>
		    
 			<h3 class="page-header">�û�����<small style="float:right"><button type="button" name="delEntity" class="btn btn-danger btn-sm">ɾ��</button></small></h3>
  			<div class="table-responsive col-sm-12">
            <table class="table table-striped">
              <thead>
                <tr style="background-color:#D9EDF7;color:#000">
                  <th><input type="checkbox" id="row_all_selector">id��</th>
                  <th>�û���</th>
                  <th>�绰</th>
                  <th>email</th>
                  <th>��ַ</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach var="user" items="${userList}">
		       <tr id="${user.u_id}">
		       <td><input type="checkbox" class="js-row_selector">${user.u_id }</td>
		       <td>${user.u_name }</td>
		       <td>${user.telephone}</td>
		       <td>${user.email }</td>
		       <td>${user.address }</td>
		       </tr>
		      </c:forEach>
              </tbody>
            </table>
          </div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="/tour/resources/js/jquery-1.11.2.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/tour/resources/js/bootstrap.min.js"></script>
    <script src="/tour/resources/js/validation/jquery.validate.min.js"></script>
    <script src="/tour/resources/js/validation/localization/messages_zh.min.js"></script>
    <script src="/tour/resources/js/jquery.serialize-object.min.js"></script>
    <script src="/tour/resources/js/jquery.form.js"></script>
    <script src="/tour/resources/js/bootbox/bootbox.min.js"></script> 
    <script>
    $(document).ready(function () {
	    
	    $('button[name="editEntity"]').click(function (){
	    	var id = $(this).parents('tr').attr('id');
	         var url = '/tour/user/admin/view/edit?id='+id;
   	         showModal(url);
	    });
	    
	    //action del
        $('button[name="delEntity"]').click(function (){
          var ids = getSelectedRowsId();
          if (!ids || ids.length <= 0) {
            bootbox.alert("��ѡ�������Ŀ��!");
          }else{
            bootbox.confirm('�˲������ἶ��ɾ����������������ݣ�ȷ��Ҫɾ����?',function(result){
              if(result) { 
                params = ids.map(function (index,id){
                  return 'id='+id;
                }).toArray().join('&');

                var url = '/tour/user/del?' + params;
                $.post(url, function (result){
                  if(result.status){
                    bootbox.confirm('ɾ���ɹ�!',function (result) {
	        	    	location.reload();
	        	    });
                  }else{
                    location.reload();
                  }
                }); 
              }
            });
          }
        });
        
      // row all selectos init
      $('#row_all_selector').click(function (){
        if($(this).prop('checked')){
          getRowSelectors().prop('checked',true);
        }else{
          getRowSelectors().prop('checked',false);
        }
      });
      
    });
    
     function showModal(url, callback){
        $.get(url, function (data){
          var modal = $('#addOrEditDialog');
          var modal_content =  modal.find('.modal-content');
          modal_content.html(data);
          if(callback){
          	callback();
          }
          modal.modal('show');
        });
      }
            
      function getRowSelectors(node){
          return $('.js-row_selector');
      }
      
      function getSelectedRowsId(node){
          return $('.js-row_selector:checked').parents('tr').map(function(){
            return $(this).prop('id');
          });
        }
    </script>
  </body>
</html>
