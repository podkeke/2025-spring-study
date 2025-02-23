package hello.hello_spring.domain;

public class Member {
    private Long id; //고객 id(x)/저장하기위한 시스템아이디(o)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
