package etc

/*
	Go의 포인의에 대해서 헷갈리는 부분 정리
	1. 인터페이스와 구현체에서의 리시버 사용 차이점
		< 문제 상황 제시 >
		- 개인 프로젝트 중 인터페이스와 구현체를 구현하고 인터페이스 타입 변수에서 구현체를 값 구조체로 받아서 사용하려 했을 때, 포인터 리시버 메서드는 호출할 수 없었음.

		< 원인 >
		- 일반적으로 값 구조체를 만들었을 때, 값 리시버나 포인터 리시버 모두 사용할 수 있다.
			d := Dog{name: "Max"}
			d.Eat("Sushi") -> 잘 동작함.
			d = &Dog{name: "Max"}
			d.Eat("Gogi") -> 잘 동작함.
		- 근데 제시된 문제 상황을 본다면 reference를 이용하지 않는 값 구조체는 Eat 메서드를 그냥 실행할 수 없어야한다.
		- 사실 이거는 Go에서 컴파일 시에 d.Eat("Sushi")를 (&d).Eat("Sushi")로 내부적으로 자동 변환을 수행한다. 그래서 에러가 발생하지 않는다.
		- 하지만 인터페이스의 경우에는 이러한 자동 변환을 지원해주지 않기 때문에 이를 수행할 수 없다. -> 이런 Go 언어의 특별한 편의 기능인데 인터페이스와 구현체 사이에는 적용되지 않음.
*/

import "fmt"

type Animal interface {
	Eat(string)
	Move(int, int)
	Sleep(int)
}

type Dog struct {
	name string
}

func (d *Dog) Eat(food string) {
	fmt.Printf("%s eat %s", d.name, food)
}

func (d *Dog) Move(x, y int) {
	fmt.Printf("%s move to (%d, %d) coordinate", d.name, x, y)
}

func (d *Dog) Sleep(t int) {
	fmt.Printf("%s sleep during %d seconds", d.name, t)
}
