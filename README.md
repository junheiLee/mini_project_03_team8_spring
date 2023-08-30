
# 4번째 미니 프로젝트(team8)
<br>

## 목차
<br>

1. 리팩토링
   - 페이징 클래스 분리
   - 스프링 프레임워크 적용
   - 비즈니스 로직 서비스로 이동
   - aop 사용

<br>

2. 트러블 슈팅
   - asdf

<br>
   
## 리팩토링
<br>

### 페이징 클래스 분리
1. 단순화된 페이징 로직
2. 유연한 확장성
3. 재사용 가능한 모듈

### 속성
1. min: 현재 페이지 범위의 최소 페이지 번호
2. max: 현재 페이지 범위의 최대 페이지 번호
3. prevPage: 이전 페이지 번호
4. nextPage: 다음 페이지 번호
5. pageCnt: 전체 페이지 개수
6. currentPage: 현재 페이지 번호

### 생성자
1. PageBean(int productCnt, int currentPage, int listCnt, int paginationCnt): 페이지 정보를 초기화하고 계산합니다.
2. productCnt: 전체 데이터 개수
3. currentPage: 현재 페이지 번호
4. listCnt: 한 페이지당 보여줄 데이터 개수
5. paginationCnt: 한 번에 표시할 페이지 번호 개수

### 리팩토링
1. Action 클래스에 있는 비즈니스 로직을 Service 클래스로 추출
2. 유사한 요청들을 분리 및 처리하는 Controller 클래스 생성
3. 각 요청마다 url 경로를 알맞게 지정(jsp, js 내 경로 수정)

<br>

### 스프링 프레임워크 적용

1. maven 설정
   - lib ()
2. 

<br>

### 비즈니스 로직 서비스로 이동

1. DTO를 Controller에서 다룸
<br>
   -> DTO의 내부는 service만 알 수 있도록 변경
<br>

> 기존 Controller 예시
```java
else if (action.equals("/orderNowInsert")) {
    nextPage = "/orders/orderList";
    HttpSession session = request.getSession();
    MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
    if (loginUser == null) {
        nextPage = "/members/loginForm.do";
    } else {
        CartVO cartVO = new CartVO();
        String id = loginUser.getId();
        int pseq = Integer.parseInt(request.getParameter("pseq"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        cartVO.setId(id);
        cartVO.setPseq(pseq);
        cartVO.setQuantity(quantity);

        ArrayList<CartVO> cartList = new ArrayList<CartVO>();
        cartList.add(cartVO);
        int maxOseq = orderService.insertOrder(cartList, loginUser.getId());
        nextPage = "/orders/orderList?oseq=" + maxOseq;
    }
}
```
<br>

> 현재 OrderController - OrderService
```java
//OrderController
@RequestMapping(value = { "/mypage", "" })
public String listOrderInProgress(HttpServletRequest request) {

    String userId = getUserIdFromSession(request);

    List<OrderVO> orderList = orderService.findAllOrderInProgressByUserId(userId);

    request.setAttribute("title", "진행 중인 주문 내역");
    request.setAttribute("orderList", orderList);

    return "mypage/orderList";
}
```
```java
//OrderService
@Transactional
public List<OrderVO> findAllOrderInProgressByUserId(String userId) {
		
    List<Integer> oseqList = orderDAO.findOseqInProgressByUserId(userId);
    List<OrderVO> orderList = new ArrayList<OrderVO>();
    
    for (int oseq : oseqList) {
        List<OrderVO> orderListIng = findAllDetail(oseq, userId, "1");
        OrderVO orderVO = orderListIng.get(0);
        orderVO.setPname(orderVO.getPname() + " 외 " + orderListIng.size() + "건");

        int totalPrice = 0;
        for (OrderVO ovo : orderListIng) {
            totalPrice += ovo.getPrice2() * ovo.getQuantity();
        }
        orderVO.setPrice2(totalPrice);
        orderList.add(orderVO);
    }
		
    return orderList;
}

```
<br>

2. transaction을 위해 DAO에서 setAutoCommit하고, 한 메서드에서 많은 쿼리 실행
<br>
   -> service에서 @Transactional 애노테이션을 사용
<br>
   -> DAO 하나의 메서드는 하나의 쿼리 실행

> 기존 OrderDAO class 예시
>
```java
public int insertOrder(ArrayList<CartVO> cartList, String id) {
    int maxOseq = 0;
    Connection conn = null;
    try {
        conn = dataFactory.getConnection();
        conn.setAutoCommit(false); // 트랜잭션 시작

        String selectMaxOseq = "SELECT MAX(oseq) FROM orders";
        pstmt = conn.prepareStatement(selectMaxOseq);
        ResultSet rs = pstmt.executeQuery();
    
        if (rs.next()) {
            maxOseq = rs.getInt(1) + 1;
        }
        pstmt.close();

        String insertOrder = "INSERT INTO orders (oseq, id) VALUES (?, ?)";
        pstmt = conn.prepareStatement(insertOrder);
        pstmt.setInt(1, maxOseq);
        pstmt.setString(2, id);
        pstmt.executeUpdate();
        pstmt.close();
    
        for (CartVO cartVO : cartList) {
            insertOrderDetail(conn, cartVO, maxOseq);
        }
        conn.commit(); // 트랜잭션 종료
        conn.close();
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("insertOrder() ERR : " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    return maxOseq;
}
```
<br>

> 현재 OrderService - OrderDAO

```java
// OrderService
@Transactional
public int insert(List<CartVO> cartList, String userId) {
        int maxOseq = 0;
        maxOseq = orderDAO.findMaxOseq();
        orderDAO.insert(userId);

        for (CartVO cart : cartList) {
        orderDAO.insertDetail(cart, maxOseq);
        orderDAO.updateCart(cart);
        }

        return maxOseq;
}
```

```java
// OrderDAO
public void insert(String userId) {
    sqlSession.insert("mapper.order.insert", userId);
}
```
<br>

### aop 적용

1. log 사용 코드가 모든 bean에서 공통으로 사용
<br>
   -> aop 클래스 사용

> LogAdvisor 코드

```java
package com.team8.shopping.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvisor {

   private static Logger log = LoggerFactory.getLogger(LogAdvisor.class);

   @Before("* com.team8.shopping.controller..*(..)")
   public void controller(JoinPoint joinPoint) {
      log.info("controller={}", joinPoint.getSignature());
   }

   @Before("* com.team8.shopping.service..*(..)")
   public void service(JoinPoint joinPoint) {
      log.info("service={}", joinPoint.getSignature());
   }

}
```
