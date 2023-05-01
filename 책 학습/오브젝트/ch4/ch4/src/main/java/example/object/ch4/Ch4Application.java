package example.object.ch4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ch4Application {

	public static void main(String[] args) {
		SpringApplication.run(Ch4Application.class, args);
	}

}
// 객체지향 설계의 핵심은 책임, 역할, 협력이다.
// 그 중 책임이 가장 중요하고 애플리케이션 전체 품질을 좌우한다. 객체지향 설계란 올바른 객체에게 올바른 책임을 할당하면서 낮은 결합도와 응집도를 가진 구조를 창조하는 활동이기 때문이다.
// 이번 장은 영화 예매 시스템을 책임이 아닌 상태를 표현하는 데이터 중심의 설계를 보고 객체지향적 구조와의 차이점을 살펴보자.

// 데이터 중심의 영화 예매 시스템
//	- 데이터 중심 관점에서 객체는 자신이 포함하고 있는 데이터를 조작하기 위한 오퍼레이션을 가진다.
//	- 반면 책임 중심 관점에서는 객체는 다른 객체가 요청할 수 있는 오퍼레이션을 위해 필요한 상태를 보관한다.
//	=> 상태는 구현에 속해 변하고 이를 중심으로 삼으면 구현에 대한 세부사항이 인터페이스까지 영향을 끼쳐 캡슐화의 원칙이 무너진다.

// 데이터 준비
