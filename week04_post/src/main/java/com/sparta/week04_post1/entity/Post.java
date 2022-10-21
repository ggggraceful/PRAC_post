package  com.sparta.week04_post1.entity;

//생성

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter //데이터를 읽어오는 역할, get 함수를 일괄적으로 만들어줌. (Lombok)
@Entity // DB 테이블 역할, 테이블과 연계됨을 스프링에게 알려줍니다.
public class Post extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다. 상속받음.

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
                              //GenerationTye.AUTO: id 값을 올려 나가면서 숫자를 달리함
                              //GenerationType.IDENTITY:
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    //@Column: Column 값으로 각 멤버변수에 적용, null이면 저장하지 않겠다.(null이될수없다 = false)
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String password;


//*****생성자 추가 //생성자 : return 값이 없음
    //title, content, author, password 의 값을  파라미터로 하는 생성자 추가
    public Post(String title, String content, String author, String password) {
        this.title    = title;
        this.content  = content;
        this.author   = author;
        this.password = password;

    }

    //PostRequestDto: Post 객체에 변경.새롭게 생성할 데이터를 담는 그릇
    // post 라고 하는 진짜 클래스 안에 DTO 를 파라미터로하는 생성자 추가


//*****메소드
    //다른 곳에서 호출되는 update 는 여기서 가져다 사용됨
    public void update(String title, String content, String author, String password) {
        this.title    = title;
        this.content  = content;
        this.author   = author;
        this.password = password;

    }

    public boolean passwordMatch(String password){

        return this.password.equals(password);
    }

}