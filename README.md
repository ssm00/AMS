# AMS
 입시학원 성적 관리 시스템
<img width="958" alt="teacher main" src="https://github.com/ssm00/AMS/assets/97657265/1a468abc-8939-4369-94c5-ce5c8d0878ea">

<br>
<br>


## 프로젝트 기획 배경
저희는 소프트웨어가 해결할 수 있는 문제를 가까운 곳에서 찾고자 하였습니다.

학원에서 학생들의 성적은 채점 된 당일 확인과 함께 사라집니다.

또한 채점 과정에서 조교는 많은 학생의 시험지를 채점 해야 합니다.

<br>

학생들의 성적을 기록하여 자신의 성적을 쉽게 확인할 수 있도록, 

조교와 선생님은 채점의 부담을 줄이고 성적을 시각적으로 확인할 수 있도록 하고자 하였습니다.

<br>

## Preview
<br>
<img src="https://github.com/ssm00/AMS/assets/97657265/d51f59af-cd36-4305-9cec-72b49dadca45" width="500" height="300">
<img src="https://github.com/ssm00/AMS/assets/97657265/8f75d422-1c44-4a62-933d-26336242a22d" width="500" height="300">
<br>

#### 메인페이지

<br>
<img src="https://github.com/ssm00/AMS/assets/97657265/3a3323eb-434b-402b-84e7-dd95d037197f" width="500" height="300">
<img src="https://github.com/ssm00/AMS/assets/97657265/9c83683a-dd90-4d7c-bf0d-1427a924854a" width="500" height="300">
<br>

#### 정답입력 페이지

<br>
<br>


## Project structrue
<details><summary><b>front end</b></summary>

                      
```jsx
.
│  app-config.js
│  AppRouter.js
│  index.js
│  reportWebVitals.js
│  setupTests.js
│  
├─assets
│  ├─img
│  │      
│  └─styles
│          index.css
│          tailwind.css
│          
├─auth
│      Login.js
│      SignUp.js
│      
├─components
│  ├─Cards
│  │      CardBarChart.js
│  │      CardLineChart.js
│  │      CardPageVisits.js
│  │      CardProfile.js
│  │      CardSettings.js
│  │      CardSocialTraffic.js
│  │      CardStats.js
│  │      CardTable.js
│  │      
│  ├─Dropdowns
│  │      IndexDropdown.js
│  │      NotificationDropdown.js
│  │      PagesDropdown.js
│  │      TableDropdown.js
│  │      UserDropdown.js
│  │      
│  ├─Footers
│  │      Footer.js
│  │      FooterAdmin.js
│  │      FooterSmall.js
│  │      
│  ├─Headers
│  │      HeaderStats.js
│  │      
│  ├─Maps
│  │      MapExample.js
│  │      
│  ├─Navbars
│  │      AdminNavbar.js
│  │      AuthNavbar.js
│  │      IndexNavbar.js
│  │      
│  └─Sidebar
│          Sidebar.js
│          
├─config
│      api-config.js
│      
├─router
│      AppRouter.js
│      
├─service
│      ApiService.js
│      
├─student
│  ├─component
│  │  ├─AnswerInput
│  │  │      StudentAnswerTr.js
│  │  │      StudentEngAnswerForm.js
│  │  │      
│  │  ├─Cards
│  │  │      CardGradeGraph.js
│  │  │      CardGradeRanking.js
│  │  │      CardStudentGrade.js
│  │  │      CardStudentScoreRanking.js
│  │  │      CardWrongRateRanking.js
│  │  │      
│  │  ├─Header
│  │  │      StudentAnswerHeader.js
│  │  │      StudentEngStat.js
│  │  │      StudentExamNumSelect.js
│  │  │      StudentHeaderStats.js
│  │  │      
│  │  └─Sidebar
│  │          StudentSidebar.js
│  │          
│  └─views
│          StudentEngAnswer.js
│          StudentEngMain.js
│          StudentMathAnswer.js
│          StudentMathMain.js
│          
└─teacher
    │  TeacherEngMain.js
    │  TeacherMathMain.js
    │  
    ├─component
    │  ├─AnswerInput
    │  │      TeacherAnswerForm.js
    │  │      TeacherAnswerTr.js
    │  │      TeacherModifyForm.js
    │  │      
    │  ├─Cards
    │  │      TeacherEngAverageChart.js
    │  │      TeacherEngBarChart.js
    │  │      TeacherNormChart.js
    │  │      TeacherTopUserName.js
    │  │      TeacherTopWrongRate.js
    │  │      
    │  ├─Header
    │  │      TeacherAnswerHeader.js
    │  │      TeacherEngStat.js
    │  │      TeacherExamNumSelect.js
    │  │      TeacherExamNumStat.js
    │  │      TeacherHeader.js
    │  │      
    │  └─Sidebar
    │          TeacherEngSidebar.js
    │          
    └─views
            TeacherEngAnswerMain.js
            TeacherEngModifyMain.js
            TeacherMathAnswerMain.js
            TeacherMathModifyMain.js

```
</details>

<details><summary><b>back end</b></summary>

```jsx
.
├─main
│  ├─java
│  │  └─com
│  │      └─ams
│  │          └─amsbackend
│  │              │  AmsBackendApplication.java
│  │              │  
│  │              ├─config
│  │              │      WebMvcConfig.java
│  │              │      WebSecurityConfig.java
│  │              │      
│  │              ├─controller
│  │              │  │  RankingController.java
│  │              │  │  TeacherController.java
│  │              │  │  UserController.java
│  │              │  │  
│  │              │  └─dto
│  │              │          EachScoreInfo.java
│  │              │          EachStudentInfo.java
│  │              │          EachWrongRateInfo.java
│  │              │          GetTopFiveScoreRes.java
│  │              │          PostExamInfoReq.java
│  │              │          PostLogInReq.java
│  │              │          PostLogInRes.java
│  │              │          PostSignUpReq.java
│  │              │          PostSignUpRes.java
│  │              │          PostTopFiveStudentInfoRes.java
│  │              │          PostTopFiveWrongRateRes.java
│  │              │          TeacherDto.java
│  │              │          UserDto.java
│  │              │          
│  │              ├─domain
│  │              │      ExamAnswerEntity.java
│  │              │      Role.java
│  │              │      SchoolType.java
│  │              │      StudentAnswerEntity.java
│  │              │      StudentEntity.java
│  │              │      Subject.java
│  │              │      TeacherEntity.java
│  │              │      UserEntity.java
│  │              │      
│  │              ├─repository
│  │              │      ExamAnswerRepository.java
│  │              │      StudentAnswerRepository.java
│  │              │      StudentRepository.java
│  │              │      TeacherRepository.java
│  │              │      UserRepository.java
│  │              │      
│  │              ├─security
│  │              │      JwtAuthenticationFilter.java
│  │              │      TokenProvider.java
│  │              │      
│  │              ├─service
│  │              │      RankingService.java
│  │              │      TeacherService.java
│  │              │      UserService.java
│  │              │      
│  │              └─util
│  │                      BaseException.java
│  │                      BaseResponse.java
│  │                      BaseResponseStatus.java
│  │                      
│  └─resources
│          application.yml
│          
└─test
    └─java
        └─com
            └─ams
                └─amsbackend
                    │  AmsBackendApplicationTests.java
                    │  
                    └─repository
                            StudentAnswerRepositoryTest.java
                            
```
</details>
<br>
<br>

## ERD
<p align="center">
  <br>
  <img src="./image/common/erd.jpg">
  <br>
</p>

<br>


## API DOC
https://documenter.getpostman.com/view/23387103/2s946feDGM

<br>
<br>

## 구현 기능
<details><summary><b>학생 페이지</b></summary>

<hr>

### 학생 메인 페이지
과목별(영어 & 수학) 회차별 시험에 대한 다양한 정보를 제공하는 페이지입니다. 페이지에서 제공하는 차트별 기능은 다음과 같습니다.

#### 1. 명예의 전당
- 해당 회차에서 가장 높은 점수를 받은 학생의 명단을 나타내는 기능으로 학생의 이름과 순위, 점수를 제공하는 차트입니다.

#### 2. 오답률 순위
- 해당 회차에서 가장 높은 오답률을 나타내는 문항의 정보를 나타내는 기능으로 해당 문항의 번호와 오답률, 정답, 해당 문항의 번호별 선택 비율을 제공하는 차트입니다.

#### 3. 개인 성적표
- 해당 회차에서 로그인한 학생의 성적표(점수, 등수)와 오답표(문항별 정답, 학생이 선택한 답, 해당 문항의 전체 오답률)를 제공하는 기능입니다.

#### 4. 성적 그래프
- 학생이 치룬 모든 시험의 회차별 정보(점수, 등수)를 그래프로 제공하여 자신의 성적 변화 추이를 확인할 수 있는 기능입니다.

#### 5.개인 성적 순위
- 학생이 치룬 모든 시험 중에서 가장 높은 점수를 받은 시험의 회차 정보(점수, 등수)를 제공하는 기능입니다.

<hr>

### 학생 정답 입력 페이지
- 학생이 시험을 치룬 회차의 정답을 번호별로 입력하는 기능을 제공합니다.

</details>
<br>

<details><summary><b>선생 페이지</b></summary>

  <hr>
  
### 선생 메인 페이지
- 과목별(영어 & 수학) 회차별 시험에 대한 다양한 정보를 제공하는 페이지입니다. 페이지에서 제공하는 차트별 기능은 다음과 같습니다.

#### 1. 명예의 전당
- 해당 회차에서 가장 높은 점수를 받은 학생의 명단을 나타내는 기능으로 학생의 이름과 순위, 점수를 제공하는 차트입니다.

#### 2. 오답률 순위
- 해당 회차에서 가장 높은 오답률을 나타내는 문항의 정보를 나타내는 기능으로 해당 문항의 번호와 오답률, 정답, 해당 문항의 번호별 선택 비율을 제공하는 차트입니다.

#### 3. 성적 그래프
- 해당 회차 시험을 치룬 학생들의 이름과 점수를 막대 그래프로 제공하는 기능입니다.

#### 4. 성적 정규 분포
- 해당 회차 시험에서 점수별 학생들의 수를 제공하는 기능으로 학생들의 점수가 어떻게 분포되어 있는지를 확인할 수 있습니다.

#### 5.평균 점수
- 회차별로 학생들의 평균 점수를 제공하는 기능입니다. 회차별 평균 점수를 그래프로 제공하여 회차별 평균 비교와 변화 추이를 한눈에 파악할 수 있습니다.

<hr>

### 선생 정답 입력 페이지
- 시험을 치룬 회차의 정답표를 만드는 페이지로 문항별 정답과 배점을 입력하고 저장할 수 있는 기능을 제공합니다. 또한 아래 채점하기 기능을 제공하며 해당 회차의 채점이 완료되지 않은 학생 수를 제공하여 채점 현황을 파악할 수 있는 기능을 제공합니다.

<hr>

### 선생 정답 수정 페이지
- 이미 정답표가 존재하는 회차의 정답표를 수정할 수 있는 페이지로 문항별 정답과 배점을 입력하여 정답표를 수정할 수 있는 기능을 제공합니다.

</details>
<br>

## 기술 스택
### Front-end
|  React  |  TailWind  | JavaScript |  Node JS |  Chart.JS  |
| :-----: | :---------:| :--------: | :------: | :--------: |
| ![react]| ![tailwind]|   ![js]    | ![nodejs]| ![chartjs] |

### Back-end
|   Java   |   Spring  |  SpringBoot |  JPA  | Spring Data JPA |  MariaDB  |
| :------: | :-------: | :---------: | :---: |  :------------: | :-------: |
|  ![java] | ![spring] |    ![sb]    | ![jpa]|     ![sdj]      |   ![mdb]  |

<br>



[react]: /image/icons/react.svg
[tailwind]: /image/icons/tailWindCSS.png
[js]: /image/icons/javascript.svg
[nodejs]: /image/icons/nodejs.png
[chartjs]: /image/icons/chartjs.svg

[java]: /image/icons/java.png
[spring]: /image/icons/spring.png
[sb]: /image/icons/springboot.png
[jpa]: /image/icons/jpa.png
[sdj]: /image/icons/springdatajpa.png
[mdb]: /image/icons/mariadb.png

