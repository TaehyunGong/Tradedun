# Tradedun
### **https://tradedun.com**
> 불편한 경매장 아바타 검색을 간편히 조회할수 있는 웹 서비스

***
## Contents
#### 1. Rare Avatar Search
<이미지>

게임내의 불편한 레어아바타 검색을 한번에 조회 할수 있도록 시스템 화

#### 2. Showroom WishList Search
<이미지>

던전앤파이터 쇼룸 서비스로 자기만의 아바타를 한번에 경매장에서 검색하여 가격이나 엠블렘, 아바타여부를 조회

## Environments

### 1. Default Server & Framework
- Tomcat8.5
- Apache3
- Spring MVC 4.3
- Mariadb 10.4.7

### 2. Product Server
- Apache와 Tomcat은 <code>MS Azure</code> 가상머신에서 운영
- MariaDB는 AWS의 <code>가상머신</code>에서 운영

### 3. ETC
- **SSL Free 인증서 적용**
  - 90일 뒤 인증서 교체 필요, 만료기간 2020-03-15
- **web server와 was를 분리 후 mod_jk로 통신**
  - WEB서버에 SSL 적용 함으로 WEB서버와 통신하는 모든 서버에 SSL적용하기 위함
- **web server & was 를 Docker 컨테이너에 import**
  - 컨테이너를 사용하여 보다 원활하며 간편한 환경셋팅이 가능함
  - [DockerHub](https://hub.docker.com) 를 사용함으로 컨테이너화 된 서버들을 자체백업 시키는게 가능함
- **Database & API KEY Configuration 은 최상단 루트 /upLoad 에 보관**
  - 윈도우의 경우 : <code>\<Context docBase="D:\upLoad/" path="/upLoad/" /></code>
  - 리눅스의 경우 : <code>\<Context docBase="/upLoad/" path="/upLoad/" /></code>

## Apply Run in Local 
### IDE 이클립스 기준
1. 로컬에 clone 실행 <code>git clone https://github.com/TaehyunGong/Tradedun.git</code>
2. eclipse 실행 후 Workspace는 원하는 아무경로로 생성
3. import -> **Existing Maven Projects** -> Root Directory에서 clone를 받은 프로젝트 선택
4. <code>/Tradedun/pom.xml</code> 선택 후 Finished
5. eclipse에서 Servers 추가 -> tomcat 8.5 -> Tradedun 프로젝트로 docBase 선택
6. Directory에 추가된 Servers -> Tomcat v8.5 Server at localhost-config -> server.xml 열기
7. 최하단의 <Host> 태그 안에 Context를 우측으로 변경<code>\<Context docBase="tradedun" path="/tradedun" reloadable="true"        source="org.eclipse.jst.jee.server:tradedun"/></code>
8. Configuration.properties 의 위치를 지정하기 위해 추가 <code>\<Context docBase="D:\upLoad/" path="/upLoad/" /></code>
9. Tradedun/src/main/resources/Configuration.properties 파일을 Context /upLoad/로 셋팅한 위치로 이동 후 내용 수정
10. DB 테이블 DDL 추가 (password : 57pn54) http://aquerytool.com:80/aquerymain/index/?rurl=b7d43aa2-0134-41cb-82c8-67e65cbca9a7
11. 
