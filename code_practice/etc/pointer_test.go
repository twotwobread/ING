package etc

import "testing"

func TestDog_Eat(t *testing.T) {
	type fields struct {
		name string
	}
	type args struct {
		food string
	}
	tests := []struct {
		name   string
		fields fields
		args   args
	}{
		{
			name:   "Eat Test",
			fields: fields{name: "Max"},
			args:   args{food: "Melon"},
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			var d Animal
			d = &Dog{ // 만약 값 구조체를 준다면 이는 타입 불일치로 감지됨.
				name: tt.fields.name,
			}
			d.Eat(tt.args.food)
		})
	}
}
