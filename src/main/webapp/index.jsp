<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<br/>
<br/>
<form action="${pageContext.request.contextPath}/api/saving-products/fetch-saveDeposit" method="GET">
  <button type="submit" style="padding: 10px; background-color: lightblue;">API에서 예금 데이터 받아오기</button>
</form>
<form action="/api/stock/fetch" method="get">
  <button type="submit">주식 DB 추가하기</button>
</form>
<br/>
<br/>
<form action="${pageContext.request.contextPath}/api/saving-products/fetch-saveSaving" method="GET">
  <button type="submit" style="padding: 10px; background-color: lightblue;">API에서 적금 데이터 받아오기</button>
</form>
<br/>
<br/>
<form action="${pageContext.request.contextPath}/api/fund-products/upload" method="post" enctype="multipart/form-data">
<label for="file">CSV 파일 선택:</label>
<input type="file" id="file" name="file" accept=".csv">
<br><br>
<button type="submit" style="padding: 10px; background-color: lightblue;">파일 업로드</button>
</form>
<!-- 채권 데이터 DB에 저장하는 버튼 -->
<form action="${pageContext.request.contextPath}/api/bond/fetch-save" method="GET">
  <button type="submit" style="padding: 10px 20px;">채권 데이터 받아오기</button>
</form>
  <form action="/api/portfolio" method="GET">
  <label for="portfolioID">Portfolio ID:</label>
  <input type="text" id="portfolioID" name="portfolioID" required>
  <button type="submit">Submit</button>
  </form>

<form action="/api/stock/fetch" method="get">
  <button type="submit">주식 DB 추가하기</button>
</form>

<form action="/api/stock/data" method="get">
  <button type="submit">주식 json 추출</button>
</form>

<h3>주식 검색</h3>
<!-- 검색 폼: 서버로 검색어를 전송 -->
<input type="text" id="searchTerm" placeholder="주식명 또는 주식코드를 입력하시오">
<button onclick="searchStock()">Search</button>

<table id="results" border="1">
  <!-- 검색 결과가 여기에 출력됩니다 -->
</table>

<script>
  function searchStock() {
    const searchTerm = document.getElementById('searchTerm').value;

    if (!searchTerm) {
      alert('검색어를 입력하세요!');
      return;
    }

    // JSON 데이터를 fetch로 가져와 화면에 렌더링
    fetch(`/api/stock/searchStock?searchTerm=` + encodeURIComponent(searchTerm))  // 템플릿 리터럴 대신 + 연산자 사용
            .then(response => response.json())
            .then(data => {
              const resultsTable = document.getElementById('results');
              resultsTable.innerHTML = ''; // 이전 검색 결과 초기화

              // 테이블 헤더 추가
              let tableHeader = `<tr>
                    <th>주식 코드</th>
                    <th>주식명</th>
                    <th>종가</th>
                    <th>전일비</th>
                    <th>등락률</th>
                </tr>`;
              resultsTable.innerHTML = tableHeader;

              // 결과를 콘솔에 출력 및 표로 출력
              data.forEach(stock => {
                // 콘솔에 검색된 주식 데이터 출력
                console.log(stock);  // 이 부분에서 각 stock 객체를 출력

                // 결과를 표로 출력
                let row = `<tr>
                        <td>${stock.stockCode}</td>
                        <td>${stock.stockName}</td>
                        <td>${stock.clpr}</td>
                        <td>${stock.vs}</td>
                        <td>${stock.fltRt}%</td>
                    </tr>`;
                resultsTable.innerHTML += row;
              });
            })
            .catch(error => {
              console.error('Error fetching data:', error);
            });
  }
</script>
    </body>
</html>