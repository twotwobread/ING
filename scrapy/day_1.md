https://www.youtube.com/watch?v=mBoX_JCKZTE

<aside>
❓ - 위 유튜브 링크의 ‘초보자들을 위한 파이썬 웹 스크래핑’이라는 ‘Scrapy’ 강좌를 정리했다.
</aside>

# What is Scrapy

- 빠르고 단순하게 웹 사이트를 크롤링할 수 있게 도와주는 오픈 소스 프레임워크
- requests와 beautiful soup를 이용해서 크던 작던 어떤 웹 사이트든 쉽게 데이터를 파싱해올 수 있게 도와준다.
- scrapy를 이용하면 concurrency를 고려하여 여러 사이트를 하나의 코드로 동시에 파싱할 수 있다.
- 오픈 소스라서 계속 더 발전하고 있다.

# Scrapy project

- scrapy startproject <project name>을 하게 되면 scrapy 프로젝트의 기본 골격을 가진 프로젝트 디렉토리를 생성.

scrapy 프로젝트는 아래와 같이 구성되어있다.

### Spider

- Spider는 어떤 url에 접근해서 어떻게 데이터를 가지고 올 지를 구현할 클래스이다.
- 그래서 default하게 start_requests, parse와 같은 메서드를 구현하게 된다. 그리고 해당 크롤링 데이터를 Item으로 구성하여 pipeline으로 보낸다.

### Item

- 이는 DTO와 같이 생각할 수 있다. 크롤링한 데이터를 Object 형태로 바꾸기 위한 Object 클래스이다.
- 크롤링한 데이터를 가지는 오브젝트라고 생각하자.
- 해당 오브젝트를 Spider에서는 반환한다. → 물론 꼭 Item class를 반환해야 하는 것은 아니지만 쉽게 구성할 수 있겠지.

### Pipeline

- 파이프라인은 반환된 데이터를 어떻게 얻을지를 표현하는 곳이다.
- 예를 들어서 Spider에서 반환한 Item에 대한 테이블을 만들고 데이터를 insert하는 과정들이 일어날 수 있다. → db와의 connection, 테이블 생성과 같은 로직이 init에서 발생할 것이고 process_item에서 반환받은 데이터를 삽입할 것이다.

### Middleware

- 미들웨어는 Spider가 작동하기를 원하는 방식을 정확히 파악할 수 있는 기능으로, 요청을 할 때 어떤 헤더를 보낼지 요청 시간을 초과하는 등 다양한 작업을 제어 가능.
- 요청할 때 요청할 때 사용할 사용자 에이전트를 사용해야 하는 경우 미들웨어 섹션에서 여러 번 재시도하는 등의 작업을 수행 가능.
- 여러 가지 기본값 제공. 원하는 작업을 업데이트하거나 원하는 작업을 직접 생성 가능.
- 미들웨어에는 2개의 타입 존재.
    - downloader middleware
    - spider middleware
    - 대부분의 작업은 다운로드 미들웨어로 진행되지만 스파이더 미들웨어는 요청을 추가하거나 삭제하는 등의 작업을 수행할 수도 있고, 스파이더가 그러한 작업을 처리하는 데 오류가 발생할 경우 다른 예외를 처리하는 항목을 처리할 수 있음.
