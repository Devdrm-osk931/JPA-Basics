package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();//트랜잭션을 얻어온다
        tx.begin();  //트랜잭션 시작


        try {
            // 회원 등록
            /*
            Member member = new Member();

            member.setId(2L);
            member.setName("HelloB");
            em.persist(member);
             */

            // 조회
            /*
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember = " + findMember.getId());
            System.out.println("findMember = " + findMember.getName());
            */

            // 삭제
            /*
            Member findMember = em.find(Member.class, 2L);
            em.remove(findMember);
            */

            // 수정
            /*
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");
//            em.persist(findMember);  // persist 해줄 필요 없음 @Entity들을 JPA가 관리하면서 트랜잭션 커밋 시점에 변동을 감지하여 업데이트 쿼리를 커밋 직전에 날린다!
            */

            // JPQL
            // 테이블을 대상으로 쿼리를 짜는것이 아니라 객체를 대상으로 쿼리를 작성한다
            /*List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();

            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }*/

            // 영속성 컨텍스트 생명주기
           /*
           // 엔티티를 생성한 상태(new, 비영속)
            Member member = new Member();
            member.setId(2L);
            member.setName("PersistenceContextTest");

            // 영속(managed)
            em.persist(member);

            // 준영속(detached); 엔티티를 영속성 컨텍스트에서 분리
            em.detach(member);

            // 삭제
            em.remove(member);
            */

            /*
            //1차 캐시에서 조회
            //비영속
            Member member = new Member();
            member.setId(2L);
            member.setName("1stCache");

            //영속
            em.persist(member);

            //조회
            // 영속성 컨텍스트의 1차 캐시에서 조회를 해오기 때문에 셀렉트 쿼리문이 나가지 않는다
            Member findMember = em.find(Member.class, 2L);

            */

            /*
            // 영속성 컨텍스트에 반영하지 않고 바로 조회를 하는 경우에는 SQL 셀렉트 쿼리문이 나가게 됨
            Member findMember1 = em.find(Member.class, 2L);
            // 두번째 조회하는 경우엔 영속성 컨텍스트에서 조회하기 때문에 쿼리가 나가면 안된다!
            Member findMember2 = em.find(Member.class, 2L);
            System.out.println("(findMember2 == findMember1) = " + (findMember2 == findMember1));
            */

            /*Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            em.persist(member1);
            em.persist(member2);
            System.out.println("===========================");
            // insert 쿼리는 commit() 이후에 발생한다*/

            /*//Dirty Checking
            Member findMember = em.find(Member.class, 150L);
            findMember.setName("ChangeDetection");*/

            /*// Flush
            Member member = new Member(200L, "member200");
            em.persist(member);


            // 직접 flush 호출 - tx.commit 이전에 sql문이 날아감
            em.flush();*/


            // 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUserName("UserA");
            //연관관계 주인인 멤버에 유관테이블 정보 추가
            member.setTeam(team);
            em.persist(member);


            em.flush();
            em.clear();



            tx.commit();

        }catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}
