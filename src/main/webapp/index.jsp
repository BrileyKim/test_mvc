<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
</head>
<body>
	<main>
		<article>
			<div class="wrapper">
			    <form class="form-signin text-center">       
			        <h2 class="form-signin-heading text-center">게시판</h2>
			        <input type="text" class="form-control" name="user_id" placeholder="아이디">
			        <input type="password" class="form-control" name="user_pw" placeholder="비밀번호">      
			    <a class="btn btn-lg btn-success btn-block" href="/user/join">회원가입</a>   
			    <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>  
			    </form>
			</div>
		</article>
	</main>
</body>
</html>