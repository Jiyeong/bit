<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 
바이너리 데이터를 서버에 보내려면,
form의 enctype을 multipart/form-data으로 설정해야 한다. 

멀티파트 형식의 요청 프로토콜 예)

------WebKitFormBoundaryzuHD6yFB4rJ2cgSW
Content-Disposition: form-data; name="name"

aa
------WebKitFormBoundaryzuHD6yFB4rJ2cgSW
Content-Disposition: form-data; name="age"

1
------WebKitFormBoundaryzuHD6yFB4rJ2cgSW
Content-Disposition: form-data; name="photo"; filename="a.jpg"
Content-Type: image/jpeg

여기에 바이너리 데이터가 있다!!!!!
------WebKitFormBoundaryzuHD6yFB4rJ2cgSW--

--%>
<form action="upload.jsp" method="post"
      enctype="multipart/form-data">
이름 : <input type="text" name="name"><br>
나이 : <input type="text" name="age"><br>
사진 : <input type="file" name="photo"><br>
<button>등록</button>
</form>
</body>
</html>