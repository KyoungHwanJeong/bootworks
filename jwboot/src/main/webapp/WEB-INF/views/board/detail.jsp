<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보기...</title>
<link rel="stylesheet" href="../static/resources/css/style.css"/>
</head>
<body>
	<div class="wrap">
		<h1>글 상세보기</h1>
		<table class="tbl_write">
			<tbody>
				<tr>
					<td>
					<label>글 번호</label>
					<input type="text" name="id"
						value="${board.id}">
					</td>
				</tr>
				<tr>
					<td>
					<label>글 제목</label>
					<input type="text" name="title"
						value="${board.title}">
					</td>
				</tr>
				<tr>
					<td>
					<label>작성자</label>
					<input type="text" name="writer"
						value="${board.writer}" >
					</td>
				</tr>
				<tr>
					<td>
					<textarea rows="5" cols="60" 
						name="content" required>${board.content}</textarea>
					</td>
				</tr>
				<tr>
					<td>
						<a href="/board/">
							<button type="button">목록</button>
						</a>
						<a href="/board/update?id=${board.id}">
							<button type="button">수정</button>
						</a>
						<a href="/board/delete?id=${board.id}"
							onclick="return confirm('해당 글을 삭제하시겠습니까?')">
							<button type="button">삭제</button>
						</a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>