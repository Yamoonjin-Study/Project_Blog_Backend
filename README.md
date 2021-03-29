# blog

### how to develop
- package
  - 기본 패키지는 application, config, domain, infra, web으로 구성됨. \
    config 제외한 다른 패키지들은 참조 구조가 infra -> domain -> application -> web 순임. config는 어느 레이어든 참조되거나 참조할 수 있음.
  - config: 모든 configuration 파일들만 작성가능. 예컨대, properties 설정이나 bean 등록 등 사용됨.
  - infra: 외부 모듈 사용하는 레이어. 다른 모든 레이어는 infra 레이어를 참조하면 안됨. infra는 domain 레이어만 참조 가능. 즉, import application/web 코드 존재하면 안됨 domain은 있어도됨.
  - domain: 모든 domain 로직은 domain에서 작성함. application만 domain 레이어를 참조할 수 있음. 즉, import infra/application/web 코드 존재하면 안됨.
  - application: domain 레이어에서 작성된 service 등을 종합하여 최종 controller로 보낼 메소드를 작성하는 레이어. web 레이어만 application 레이어를 참조할 수 있음. 즉, import infra/web 있으면 안됨.
  - web: 최상단 레이어로, controller, exception handler 등 작성. 다른 모든 레이어는 web을 참조할 수 없음. 즉, import infra/domain 있으면 안됨.

- conventions & rules
  - 해당 프로젝트에서는 백엔드만 작성한다(RestController). 프론트는 알아서 다른 Repository를 이용하여 연동한다. 프론트 작업에 대한 리뷰는 하지 않는다.
  - 네이밍 준수하여야함. 코드를 처음 보는 사람도 이해할 수 있게끔 변수명 작성 요함.
  - 모든 변수명은 camelCase.
  - 모든 Json Property는 snake_case.
  - if문은 최대한 줄이자(단, 값 비교 등 꼭 필요한 곳에선 사용할 수 있음). for문도 최대한 없애고, stream을 이용하자.
  - db는 관계형을 쓰자. MySQL, MariaDB, PostgreSql 등. 그리고 db는 docker를 사용해 구축하다.
  - docker-compose로 처음 보는 사람도 한번에 코드 실행 및 db 셋업을 할 수 있도록 하자.
  - Domain Driven Design을 항상 지키자.
  - 모든 entity는 domain 레이어에 작성한다.
  - Swagger UI로 문서 자동화를 이용하자.
  - 중복을 없애자.
  - 생성자에 lombok 사용은 지양하자.
  - 쓸데없는 코드는 모두 버리자. 깔끔하게, 리뷰어가 한 눈에 알아볼 수 있게끔 정리하고 리뷰요청을 한다.
  - Error 핸들링을 잘 하자. 이유없이 throw하지 않는다. 모든 에러메세지는 소문자로 작성한다.
  - domain 레이어에선 service 어노테이션을 붙히지 않는다.
  - 절대, 모든 코드에 이유가 있어야한다. 어노테이션 하나 달 때에도 이유를 생각하자.
  - setter는 필요한 곳에만 생성하고, lombok의 setter는 웬만하면 사용하지 않는다.
  - 커밋 올리기 전에는 Code Formatting을 한 번 씩 돌려주자. 단, import들이 합쳐지지 않도록 ide에서 설정해두자.
  - IDE는 무조건 IntelliJ를 사용하자. 돈 얼마 안한다.
  - main 브랜치는 건들지 않는다. 무조건 자신의 브랜치에 작업을 하고 PR을 올리도록 한다. PR 승인 없이 main 브랜치로의 merge는 절대 하지 않는다.
  - 1일 50줄(100번 양보해서..) 이상의 코드를 커밋하고 PR을 올린다. 지켜지지 않을 시 리뷰를 포기한다.
  - 이 컨벤션에 추가할 사항이 있으면 리뷰어가 구두로, 또는 리뷰를 통해 설명할 예정이다.