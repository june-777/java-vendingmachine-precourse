
기능 목록
### 비즈니스 기능
**비즈니스1. 자판기 초기화 서비스**
- 보유 금액을 자판기 동전으로 거스름하는 기능 
    - 보유 금액으로 동전을 거스름할 수 있는지 검증하는 기능
    - 동전 금액 중 하나를 무작위로 생성하는 기능
    - 보유 금액 만큼 동전을 거스름하는 기능

- 자판기에 상품을 등록하는 기능
    - 상품명, 가격, 수량 입력으로 상품을 추가할 수 있다.
    - 가격은 최소 100원이며, 10원 단위로 나누어 떨어져야한다.
<br>
  
**비즈니스2. 자판기 상품 구매 서비스**
- 상품을 구매하는 기능
  - 남은 금액이 상품의 최저 가격보다 적거나, 모든 상품이 소진된 경우 바로 잔돈을 돌려주고 끝낸다.
- 잔돈을 돌려주는 기능
  - 현재 보유한 최소 개수의 동전으로 돌려준다.
  - 남은 금액이 상품의 최저 가격보다 적거나, 모든 상품이 소진된 경우 바로 돌려준다.
  - 반환할 수 없는 경우 잔돈으로 반환할 수 있는 금액만 반환한다.
  - 반환되지 않은 금액은 자판기에 남는다.

### 입력 화면 기능
- 자판기 보유 금액을 입력하는 기능
  - 자판기가 보유하고 있는 금액을 입력해 주세요.
  - [입력]
- 자판기 상품 정보를 입력하는 기능
  - 상품명과 가격, 수량을 입력해 주세요.
  - [입력]
- 투입 금액을 입력하는 기능
  - 투입 금액을 입력해 주세요.
  - [입력]
- 현재 금액정보와 구매 상품명을 입력하는 기능
  - 투입 금액: `남은금액` 
  - 구매할 상품명을 입력해 주세요.
  - [입력]

### 출력 화면 기능