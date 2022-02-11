# uostime_springboot
SpringBoot로 UOSTime의 기능을 점차적으로 구현할 프로젝트

# 개발 환경
- BE(Backend) : Java SpringBoot
- DB(Database) : MySQL, (Redis)
    - Instance Management : AWS RDS
- (Web Server) : NginX
- (Cloud Server) : AWS EC2(Amazon Web Service Elastic Compute Cloud)
- (Deploy : Docker)
- java version : 11
- gradle
- MySQL db 버전 : 8.0.27

# 개발 절차
### issue rule
1. 목표로 한 개발/배포에 대해 issue를 작성합니다.
2. issue에 대한 branch를 생성 후, 코드 작성한 것을 develop branch에 commit & push 합니다.
3. issue에 대한 기능이 완료된 경우, PR을 하여 코드 리뷰 후에 approve됨에따라 develop branch에 merge합니다.
    - develop -> new branch -> commit&push to develop -> pr -> merge to develop

# rule
- Git-flow 전략을 이용하여 develop/feature~ 브랜치에서 기능을 통합합니다.
- issue에 대한 새로운 branch를 생성해야할 경우, feature/{issue-number} 형식을 따릅니다.
    - ex) feature/2
- pr을 보낼 때 해당 브랜치에서 하는 기능에 대해 설명합니다.
    - branch 이름에서 충분히 설명이 가능한 경우, 생략해도 됩니다.

# 참고 사항
- git desktop 버전 다운로드 받으시면 매우 편하답니다^^
