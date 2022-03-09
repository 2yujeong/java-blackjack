# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 🔥 기능 목록

- 카드
  - [ ] 모양 : 다이아몬드, 스페이스, 하트, 클로버
  - [ ] 숫자 : ACE, 2, 3, 4, 5, 6, 7, 8, 9, J, Q, K
    - ACE만 1(Hard Hand) 또는 11(Soft Hand)로 사용 가능
    - J, Q, K는 10
  - [ ] 블랙잭에서 사용할 카드는 1벌(52장)만 사용

- 카드 발급
  - 딜러
    - [ ] 첫 2장 중 첫번째 카드만 공개
    - [ ] 딜러는 발급 시에만 ACE를 11로 처리 (Soft Hand)
    - [ ] 점수 계산 시 BUST가 발생하면 ACE를 1로 처리 가능 (Hard Hand)
    - [ ] 딜러는 ACE를 11로 처리했을 떄의 점수가 16이하인 경우 무조건 카드를 받음
    - [ ] 딜러는 17이상인 경우 멈춤
  - 플레이어
    - [ ] 받은 모든 카드 공개
    - [ ] 최적화된 점수가 20이하까지 자유롭게 카드 발급 -> 추가 카드를 받을 경우 히트, 원하지 않을 경우 스텐드(스테이)
    - [ ] 21이 넘어가는 경우 패배 -> 버스트

- 승부 규칙
  - [x] 딜러와 플레이어 간의 카드 합으로 비교 (ACE를 1 또는 11로 사용해 유리한 점수를 선택)
  - [x] 딜러와 플레이어가 BUST가 아닌 경우 21에 가장 가까운 사람이 이김
  - [x] 딜러만 BUST인 경우 플레이어가 이김
  - [x] 딜러와 플레이어 모두 BUST인 경우 딜러가 이김
  - [x] 동점인 경우는 무승부

- 뷰
  - 이름 입력
    - [ ] "" 또는 공백 이름 사용 불가
    - [ ] 구분자는 무조건 컴마(,) 사용
    - [ ] 잘못된 구분자 사용 불가
  - 발급된 카드 공개
    - [ ] 딜러만 제일 처음 받은 카드 한장만 공개, 나머지는 모두 공개
  - 카드 발급
    - [ ] 발급 여부 y/n 선택
    - [ ] 플레이어 선 발급 / 딜러 후 발급
  - 최종 결과 출력
    - [ ] 각 플레이어(딜러 포함) 카드 합 결과 출력
    - [ ] 최종 승패 출력
  