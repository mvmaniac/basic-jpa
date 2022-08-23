# Basic JPA

### 1. 강의 및 블로그 실습 예제 기반

-

### 2. 차이점

- Spring Boot 2, Gradle 7 기반

### 3. TODO

-

### 4. 참고

- 아래 VM 옵션을 주어서 실행
  `-Djasypt.encryptor.password=dbgmltlr`

- multi datasource 구성 시 entity나 repository는 별도의 패키지로 구성하는게 나을 듯
  - entity는 각 업무별 domain 패키지 아래서 mysql, postgresql 패키지로 구분함
  - repository는 어노테이션으로 처리 했으나 entity와 마찬가지로 패키지로 구분하는게 심플 할 듯
