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

            tx.commit();

        }catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}
