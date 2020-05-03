# 삼성SDS 멀티캠퍼스 파이널 프로젝트

## 빅데이터 기반 커넥티드카 관제 및 반응형 광고 서비스

### (부제 : 디지털사이니지를 활용한 반응형 광고 서비스 및 CAR PAYMENT 시스템 구축)

### 제안 배경

- 현대의 광고산업에서 빅데이터 기반 사용자 맞춤형 광고가 온라인 위주로 발전하고 있는 반면 오프라인 거점광고는 여전히 효율적인 소비자 타겟팅이 부족한 상태
- 자료를 분석해본 결과 거점 별로 연령, 성별, 직업, 가구형태 등에 따른 유의미한 기호집단형성이 되어있음을 발견하여 현재 비효율적으로 진행되고 있는 오프라인 거점 광고에 기호집단 관련 빅데이터 분석을 도입하여 보다 효율적인 광고집행을 목표로 함
- 분석을 통한 광고 서비스와 더불어 차량 내 CAR PAYMENT 시스템을 구현함으로써, 미래형 커넥티드카 내 원격 제어 및 결제기능을 통해 광고서비스, 구매기능까지 한번에 이루어지는 플랫폼으로서의 역할을 가능하게 함

### 제안 목적

- 목표 1: 사거리 전광판 광고 주변 교통량 및 운전자의 정보를 분석하여 광고 집행 시 주변 운전자들의 기호에 맞는 광고를 송출
- 목표 2: 운전자의 어플리케이션, 차량 Lcd모니터, 광고주 관리자페이지를 연동하여 실시간으로 차량의 상태 및 운전자의 기호를 광고판에 반영
- 목표 3: 소비자의 기호를 정제된 로그로 변환하여 Decision tree 알고리즘으로 분석, 광고송출 내용 및 순서등에 반영
- 목표 4 : 미래형 커넥티드카의 원격 제어를 통해 광고시청, 구매기능까지 한번에 이루어지는 원 클릭 플랫폼 구축

### 기대효과

- 경제적 측면

  - 광고의 노출도 향상으로 인한 수익률 증가 예상
  - 소비자 표적 광고 연결을 통해 소비활동 증가 예상
  - 광고 노출도 : 해당 지역의 유동차량/유동인구
  - 광고 평균 비용 : 주요 지역 약 800/하루 20초 100회이상 노출 기준

- 기술적 측면

  - 미래형 커넥티드카 내 원격제어 및 결제기능을 통해 광고서비스, 구매기능까지 한번에 이루어지는 플랫폼으로서의 역할

- 서비스 측면

  - 수요자 중심의 서비스 질 향상
  - 고객의 이용만족도 개선

- CAR PAYMENT시스템을 통해 일상생활에서의 편리성을 증진

### 서비스 구성도

![서비스소개](https://user-images.githubusercontent.com/36683607/80897608-7097c880-8d35-11ea-9a0d-f9d4a0bc29f1.png)

### 시스템 구성도

![시스템 구성도](https://user-images.githubusercontent.com/36683607/80897609-7392b900-8d35-11ea-815e-76cef11ba382.png)

### 기술스택

![기술스택](https://user-images.githubusercontent.com/36683607/80897600-5e1d8f00-8d35-11ea-859a-06576320c03f.png)

### ERD

<img width="511" alt="ERD 보여주기용" src="https://user-images.githubusercontent.com/36683607/79118189-1d7ec580-7dc8-11ea-9b9b-dc743e31d09e.PNG">

### 개발방안(알고리즘의 구현 요소)

- Decision Tree를 활용한 전광판 광고 송출
  - 정제된 로그를 이용하여 광고를 송출하는 순서를 변경
  - 광고 접속률의 상향평준화를 위한 광고 재생시간 가중치 조절

### 데모 화면

> 관리자 페이지
>
> <img width="1280" alt="main" src="https://user-images.githubusercontent.com/36683607/78957622-d1f6be00-7b20-11ea-8e39-baf8f2ad6468.png">
>
> <img width="1278" alt="userlist" src="https://user-images.githubusercontent.com/36683607/78957319-03bb5500-7b20-11ea-9d48-4cab5da84e2b.png">
>
> <img width="1277" alt="chart1" src="https://user-images.githubusercontent.com/36683607/78957571-a83d9700-7b20-11ea-8eb2-ab90d508c07f.png">
>
> <img width="1279" alt="chart2" src="https://user-images.githubusercontent.com/36683607/78957331-074edc00-7b20-11ea-9035-6f16f2e13725.png">
>
> <img width="1280" alt="maps" src="https://user-images.githubusercontent.com/36683607/78957313-00c06480-7b20-11ea-9ce5-3f3023435be9.png">
>
> <img width="1277" alt="adlist" src="https://user-images.githubusercontent.com/36683607/78957322-04ec8200-7b20-11ea-8b7a-aa1a1d2176c2.png">
>
> <img width="1276" alt="sn" src="https://user-images.githubusercontent.com/36683607/78957316-0322be80-7b20-11ea-92a7-027d9fbdadfa.png">
>
> 클라이언트 앱
>
> ![cardregi](https://user-images.githubusercontent.com/36683607/80897745-1861c600-8d37-11ea-815f-8e6af2935913.gif)
>
> ![apppay](https://user-images.githubusercontent.com/36683607/80897677-4d214d80-8d36-11ea-99d2-0f6a3722f891.gif)
>
> 인포매틱스
>
> <img width="1218" alt="pad1" src="https://user-images.githubusercontent.com/36683607/80897614-7c838a80-8d35-11ea-892d-16e2c1fcc1fc.png">
>
> <img width="1214" alt="pad2" src="https://user-images.githubusercontent.com/36683607/80897628-b05eb000-8d35-11ea-909b-396e4cf70059.png">
>
> <img width="1170" alt="pad3" src="https://user-images.githubusercontent.com/36683607/80897643-ea2fb680-8d35-11ea-91a3-39e5000f840f.png">
>
> ECU
>
> ![KakaoTalk_20200429_192155015](https://user-images.githubusercontent.com/36683607/80897666-311dac00-8d36-11ea-8739-569809548eb3.gif)
>
> ![KakaoTalk_20200429_192153707](https://user-images.githubusercontent.com/36683607/80897663-2f53e880-8d36-11ea-8bb2-9f3c6b2ed63a.gif)
>
> ![KakaoTalk_20200429_192153517](https://user-images.githubusercontent.com/36683607/80897679-57434c00-8d36-11ea-83d7-7dca8d89bfa0.gif)

### 수행일정

<img width="470" alt="t" src="https://user-images.githubusercontent.com/36683607/78957317-03bb5500-7b20-11ea-80c9-68e0b6e5ef4f.PNG">

### 기타(알고리즘, Decision Tree)

<img width="419" alt="dt" src="https://user-images.githubusercontent.com/36683607/78957332-07e77280-7b20-11ea-8bbf-1e26bf9a29db.PNG">
