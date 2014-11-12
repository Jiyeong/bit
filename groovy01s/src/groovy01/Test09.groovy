package groovy01

// 자바스크립트의 each() 흉내
// each(배열, function(index, item){})
scores = ["홍길동", 100, 80, 100]

// 메서드 호출할 때 괄호 생략 할 수 있다.
//scores.each{value -> println value}
//scores.each(){value -> println value})
scores.each { //each란 함수 정의한듯 하지만 아님. each란 메서드 호출.
  value -> println value
} // {..} -> 파라미터 값
//                      코드
// scores.each( (value) -> println value) // 자바의 람다

println "-----------------------------"
scores.eachWithIndex ({value, index -> println value + "," + index})
println "-----------------------------"

// 메서드 정의
def plus (x, y) {
  x + y // 맨 끝 문장의 실행 결과가 리턴된다. 굳이 리턴 안써도 됨.
}

int minus (int x, int y) {
  return x - y;
}

println(plus(10, 20)) // 메서드 호출 시 괄호 사용 => 고유 문법
println plus(10, 20) // 메서드 호출 시 괄호 생략 => 그루비 기본 문법
result = minus 100, 50 // 괄호 생략 예제
println result

println "-----------------------------"
// 클로저 정의
//multiple = {x, y -> x * y} // Use closure
multiple = {x, y ->
  println "이것이 클로저다!"
  println "오호라..그렇군..!"
  x * y
}

//자바스크립트로 위의 문장을 표현한다면,
/* 
 multiple = function(x,y) {
   console.log("이것이 클로저다!")
   console.log("오호라..그렇군..!")
   return x * y;
 }
 */

println multiple (30, 20)

result = multiple 30, 20
println result






