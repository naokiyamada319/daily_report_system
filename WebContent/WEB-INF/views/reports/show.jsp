<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
	<c:param name="content">
		<c:if test="${flush != null}">
			<div id="flush_success">
				<c:out value="${flush}"></c:out>
			</div>
		</c:if>
		<c:choose>
			<c:when test="${report != null}">
				<h2>日報　詳細ページ</h2>
				<c:if test="${sessionScope.login_employee.id != report.employee.id}">
					<c:choose>
						<c:when test="${followStatus}">
							<form method="POST" action="/daily_report_system/unfollow">
								<input type="hidden" name="followee_id" value="${report.employee.id}" />
								<button type="submit">フォロー解除</button>
							</form>
						</c:when>
						<c:otherwise>
							<form method="POST" action="/daily_report_system/follow">
								<input type="hidden" name="followee_id" value="${report.employee.id}" />
								<button type="submit">フォローする</button>
							</form>
						</c:otherwise>
					</c:choose>
				</c:if>
				<table>
					<tbody>
						<tr>
							<th>氏名</th>
							<td><c:out value="${report.employee.name}" /></td>
						</tr>
						<tr>
							<th>日付</th>
							<td><fmt:formatDate value="${report.report_date}" pattern="yyyy-MM-dd" /></td>
						</tr>
						<tr>
							<th>内容</th>
							<td>
								<pre><c:out value="${report.content}" /></pre>
							</td>
						</tr>
						<tr>
							<th>登録日時</th>
							<td>
								<fmt:formatDate value="${report.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
						<tr>
							<th>更新日時</th>
							<td>
								<fmt:formatDate value="${report.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
						</tr>
					</tbody>
				</table>

				<c:if test="${sessionScope.login_employee.id == report.employee.id}">
					<p><a href="<c:url value="/reports/edit?id=${report.id}" />">この日報を編集する</a></p>
				</c:if>
			</c:when>
			<c:otherwise>
				<h2>お探しのデータは見つかりませんでした。</h2>
			</c:otherwise>
		</c:choose>

		<p><a href="<c:url value="/reports/index" />">一覧に戻る</a></p>
	</c:param>
</c:import>