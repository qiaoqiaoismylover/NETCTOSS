<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script language="javascript" type="text/javascript">
            //排序按钮的点击事件
            function sort(btnObj) {
                if (btnObj.className == "sort_desc")
                    btnObj.className = "sort_asc";
                else
                    btnObj.className = "sort_desc";
            }

            //启用
            function startFee(id) {
                var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
                if(r) {
	            	window.location.href = "startCost.do?cost_id="+id;
            	}
            }
            //暂停
            function pauseFee(id) {
                var r = window.confirm("确定要暂停此资费吗？资费启用后将不能修改和删除。");
                if(r) {
	            	window.location.href = "pauseCost.do?cost_id="+id;
            	}
            }
            
            //删除
            function deleteFee(id) {
                var r = window.confirm("确定要删除此资费吗？");
               	if(r) {
               		window.location.href = "deleteCost1.do?cost_id="+id;
               	}
            }
        </script>        
    </head>
    <body>
        <!--Logo区域开始-->
       	<div id="header">
		<c:import url="../logo.jsp"></c:import>
	</div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">  
        	<jsp:include page="/WEB-INF/main/menu.jsp" />                      
            <!-- <ul id="menu">
                <li><a href="../index.html" class="index_off"></a></li>
                <li><a href="../role/role_list.html" class="role_off"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="../fee/fee_list.html" class="fee_on"></a></li>
                <li><a href="../account/account_list.html" class="account_off"></a></li>
                <li><a href="../service/service_list.html" class="service_off"></a></li>
                <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
                <li><a href="../report/report_list.html" class="report_off"></a></li>
                <li><a href="../user/user_info.html" class="information_off"></a></li>
                <li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>
            </ul> -->            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="" method="">
                <!--排序-->
                <div class="search_add">
                    <div>
                        <!--<input type="button" value="月租" class="sort_asc" onclick="sort(this);" />-->
                        <input type="button" value="基费" class="sort_asc" onclick="sort(this);" />
                        <input type="button" value="时长" class="sort_asc" onclick="sort(this);" />
                    </div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='toAddCost.do';" />
                </div> 
                <!--启用操作的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div>    
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th>资费ID</th>
                            <th class="width100">资费名称</th>
                            <th>基本时长</th>
                            <th>基本费用</th>
                            <th>单位费用</th>
                            <th>创建时间</th>
                            <th>开通时间</th>
                            <th class="width50">状态</th>
                            <th class="width200"></th>
                        </tr>                     
                        <c:forEach items="${costs }" var="cost">
	                        <tr>
	                            <td>${cost.cost_id }</td>
	                            <td>${cost.name }</td>
	                            <td>${cost.base_duration }</td>
	                            <td>${cost.base_cost }</td>
	                            <td>${cost.unit_cost }</td>
	                            <td><fmt:formatDate value="${cost.creatime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                            <td><fmt:formatDate value="${cost.startime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                            <td>
	                            	<c:choose>
	                            	<c:when test="${cost.status==0 }">开通</c:when>
	                        		<c:when test="${cost.status==1 }">暂停</c:when>
	                        		<c:otherwise>删除</c:otherwise>
	                            	</c:choose>
	                            </td>
	                            <td class="td_modi">
	                        	<c:choose>
	                        		<c:when test="${cost.status==0 }">
	                        			<input type="button" value="暂停" class="btn_pause" onclick="pauseFee(${cost.cost_id });" />
			                            <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateCost.do?id=${cost.cost_id}';" />
			                            <input type="button" value="删除" class="btn_delete" onclick="deleteFee(${cost.cost_id });" />
	                        		</c:when>
	                       		<c:when test="${cost.status==1 }">
	                        			<input type="button" value="开通" class="btn_start" onclick="startFee(${cost.cost_id });" />
			                            <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateCost.do?id=${cost.cost_id}';"  />
			                            <input type="button" value="删除" class="btn_delete" onclick="deleteFee(${cost.cost_id });" />
	                        		</c:when>
	                     		<c:otherwise>
	                        			
	                        		</c:otherwise>
	                        	</c:choose>
	                        </td>
	                        </tr>
                        </c:forEach>
                    </table>
                    <p>业务说明：<br />
                    1、创建资费时，状态为暂停，记载创建时间；<br />
                    2、暂停状态下，可修改，可删除；<br />
                    3、开通后，记载开通时间，且开通后不能修改、不能再停用、也不能删除；<br />
                    4、业务账号修改资费时，在下月底统一触发，修改其关联的资费ID（此触发动作由程序处理）
                    </p>
                </div>
                <!--分页-->
                <div id="pages">
                	<c:choose>
                		<c:when test="${costPage.currentPage==1 }">
                			<a href="#">上一页</a>
                		</c:when>
                		<c:otherwise>
                			<a href="findCost.do?currentPage=${costPage.currentPage-1 }">上一页</a>
                		</c:otherwise>
                	</c:choose>
        	        
        	        <c:forEach begin="1" end="${costPage.totalPage }" var="p">
        	        	<c:choose>
        	        		<c:when test="${p==costPage.currentPage }">
        	        			<a href="findCost.do?currentPage=${p }" class="current_page">${p }</a>
        	        		</c:when>
        	        		<c:otherwise>
        	        			<a href="findCost.do?currentPage=${p }">${p }</a>
        	        		</c:otherwise>
        	        	</c:choose>
                    </c:forEach>
                    
                    <c:choose>
                		<c:when test="${costPage.currentPage==costPage.totalPage }">
                			<a href="#">下一页</a>
                		</c:when>
                		<c:otherwise>
                			<a href="findCost.do?currentPage=${costPage.currentPage+1 }">下一页</a>
                		</c:otherwise>
                	</c:choose>
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>