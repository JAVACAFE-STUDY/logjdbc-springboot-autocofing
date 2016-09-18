package net.chandol;

import net.chandol.sample.Member;
import net.chandol.sample.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Autowired
	MemberRepository memberRepository;

	@Override
	public void run(String... strings) throws Exception {
		memberRepository.save(new Member("aaa", "test1@gmail.com"));
		memberRepository.save(new Member("bbb", "test2@gmail.com"));
		memberRepository.save(new Member("ccc", "test3@gmail.com"));
		memberRepository.save(new Member("ddd", "test4@gmail.com"));

		memberRepository.flush();

        List<Member> members = memberRepository.findAll();

        System.out.println(members);
    }
}
