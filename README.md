# 사용설명서
## 1. 이름 및 패스워드 입력
<a href="https://imgbb.com/"><img src="https://i.ibb.co/yNGZqVH/Intro.png" size = "200" alt="Intro" border="0"></a>  
 프로그램을 실행하면 이름을 입력하라고 나온다.  

  - **이미 저장된 이름일 경우**  
	   <a href="https://imgbb.com/"><img src="https://i.ibb.co/dkSDKDh/image.png" alt="image" border="0"></a>    
	   패스워드를 입력해달라고 뜬다.  
	   - **올바른 패스워드를 입력하면**  
		  <a href="https://imgbb.com/"><img src="https://i.ibb.co/g35mwgQ/image.png" alt="image" border="0"></a>    
		  "확인되었습니다." 라는 메세지가 뜨고 게임창이 열린다.  
		  
	   - **잘못된 패스워드를 입력하면**  
	   <a href="https://imgbb.com/"><img src="https://i.ibb.co/tKQRwmm/image.png" alt="image" border="0"></a>  
	   패스워드를 다시 요청한다.  <br>
	    <a href="https://imgbb.com/"><img src="https://i.ibb.co/SwCX3J2/image.png" alt="image" border="0"></a>  
	   만약 3회 연속 실패할 경우, 사진처럼 접속 거부 당한다.  
- **새로운 이름일 경우**  
	   <a href="https://imgbb.com/"><img src="https://i.ibb.co/LQpymjk/image.png" alt="image" border="0"></a>    
	   패스워드 설정을 요구하고 패스워드를 작성하면 게임창이 열린다.  

## 2. 게임 진행
- **배팅 시간**    
<a href="https://ibb.co/k2mDXV5"><img src="https://i.ibb.co/SwfmBD5/image.png" width = "400" alt="image" border="0"></a>    
현재 보유한 칩이 우측 상단에 뜨며 배팅을 하기 전까지는 딜러 카드 뿐만 아니라 플레이어의 카드까지도 한 장만 보여준다.  
우측 프레임의 **UP, DOWN 버튼으로 배팅액을 조절**할 수 있다.   
배팅액은 최저 0부터 최대 보유한 칩의 개수까지이다.  
적절한 액수를 골랐다면 **우측 하단의 START 버튼을 누르면 게임이 진행된다.**  

- **블랙잭 시작**  
<a href="https://ibb.co/X2WJVG3"><img src="https://i.ibb.co/ZLfTNbY/image.png" width = "400" alt="image" border="0"></a>  
배팅이 끝났으니 배팅액만큼 칩이 없어지고, 플레이어 카드는 두 장 다 보여준다.  
게임 진행은 MORE 버튼 또는 STOP 버튼을 선택해서 누를 수 있다.  
**MORE 버튼을 누를 경우 카드를 한 장 받는다.**  
**STOP 버튼을 누를 경우 카드를 더 이상 받지 않고 승패 판정을 한다.**
<br>
	- **MORE 버튼을 누른 경우**      
	 <a href="https://ibb.co/DRrB7nS"><img src="https://i.ibb.co/RDjXCWV/image.png" width = "400" alt="image" border="0"></a>  
	 카드를 한 장 받고 화면에 보여준다.  
	 아래 라벨에는 현재 카드의 총합을 보여줘야한다.  
	 버스트만 아니라면 플레이어는 다시 MORE 버튼을 누를건지 STOP 버튼을 누를건지 고민한다.  
	<br>
	- **STOP 버튼을 누른 경우**    
	<a href="https://ibb.co/QHQfwZb"><img src="https://i.ibb.co/c831nPg/image.png" width = "400" alt="image" border="0"></a>  
	딜러의 점수가 16을 넘을 때까지 카드를 받아 오픈하고, 플레이어와 점수 비교를 한 뒤, 아래 라벨에 게임 결과를 출력해준다. **( 게임 결과 : ( 플레이어 점수 : 딜러 점수 ))**  
	결과가 나오면 배팅액과 결과에 따라 칩의 개수가 바뀐다.  
	다음 게임을 더 진행하고 싶으면 **STOP 버튼을 한번 더 누르면 된다. 그러면 다시 배팅 시간부터 게임이 시작된다.**

위의 게임 방식이 계속 반복되며 게임이 진행된다.    
**게임 결과는 총 여섯 가지로 각각의 내용과 보상은 아래와 같다**
- **블랙잭**    
	처음 카드 두 장의 합이 21인 경우로, **배팅액 * 2 + 10 개**의 칩을 준다.
- **플레이어 승리**    
	승패를 따져본 결과, 플레이어의 점수 합이 딜러의 점수 합보다 높은 상황으로 **배팅액 * 2개**의 칩을 준다.  
- **딜러 승리**    
	승패를 따져본 결과, 딜러의 점수 합이 플레이어의 점수 합보다 높은 상황으로 이 경우 **주는 칩은 없다**.  
- **플레이어 버스트**    
	MORE 버튼을 누른 결과, 플레이어의 총합 점수가 21을 넘은 상황으로, 딜러의 패와는 상관없이 패배한다. **이 경우 주는 칩은 없다.**   
- **딜러 버스트**    
	딜러가 카드를 받은 결과, 딜러의 총합 점수가 21을 넘은 상황으로, 플레이어가 먼저 버스트한 것만 아니라면 플레이어가 승리한다. 이 경우 **배팅액 * 2개**의 칩을 준다.   
- **무승부**    
	승패를 따져본 결과, 플레이어의 점수 합과 딜러의 점수 합이 같은 상황으로 이 경우 **배팅액을 돌려준다**. 
  
## 3. 게임 종료  
플레이하던 중 게임을 종료하고 싶다면 우측 하단에 있는 END 버튼을 눌러 종료하면 된다.    
정상적으로 종료할 경우, **pData.csv 파일에 플레이어 이름과 칩 개수, 비밀번호가 저장된다.**  
이후 게임을 다시 실행할 때, 같은 이름으로 접속하면 칩 개수가 이어지는 것을 확인할 수 있다.    