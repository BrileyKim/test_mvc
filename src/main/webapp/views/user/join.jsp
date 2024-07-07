<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="../resources/css/style.css">
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
</head>
<body>
	<main>
		<article>
			<div class="wrapper">
	                	<form class="form-signin">
	                	<h2 class="form-signin-heading text-center">회원가입</h2>
						  <div class="form-group" style="margin-bottom:20px;">
						    <label for="user_id">아이디</label>
						    <input type="text" class="form-control" placeholder="아이디를 입력하세요" id="user_id">
						  </div>
						  <div class="form-group">
						    <label for="user_pw">비밀번호</label>
						    <input type="password" class="form-control" placeholder="비밀번호를 입력하세요" id="user_pw">
						  </div>
						  <div class="form-group">
						    <label for="user_name">닉네임</label>
						    <input type="text" class="form-control" placeholder="닉네임을 입력하세요" id="user_name">
						  </div>
						  <div class="form-group mt-2 text-center">
							  <button type="submit" class="btn btn-primary">가입하기</button>
						  </div>
						</form>
			</div>
		</article>
	</main>
</body>
</html>