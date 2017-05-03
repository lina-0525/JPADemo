package junit.test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.BeforeClass;
import org.junit.Test;

import cn.test.bean.Person;

public class PersonTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	//添加Person信息
	@Test
	public void save() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
		//SessionException->session->bean事物
		EntityManager em  = factory.createEntityManager();
		em.getTransaction().begin(); //开启事物
		em.persist(new Person("北京盖网"));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	//获取Person信息
	@Test
	public void getPerson() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
		EntityManager em  = factory.createEntityManager();
		Person person = em.find(Person.class, 1);
		System.out.println("person.getName = "+person.getName());
		em.close();
		factory.close();
	}

	@Test
	public void getPerson2() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
		EntityManager em  = factory.createEntityManager();
		Person person = em.getReference(Person.class, 2);
		System.out.println("person.getName = "+person.getName());
		em.close();
		factory.close();
	}
	
	//更新Person信息
	@Test
	public void updatePerson(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
		//SessionException->session->bean事物
		EntityManager em  = factory.createEntityManager();
		em.getTransaction().begin(); //开启事物
		Person person = em.getReference(Person.class, 2);
		person.setName("张三");
		em.getTransaction().commit();
		em.close();
		factory.close();
		
	}
	
	//new 
	//managed(托管)
	//游离（脱管）
	//删除
	@Test
	public void updatePerson2(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
		//SessionException->session->bean事物
		EntityManager em  = factory.createEntityManager();
		em.getTransaction().begin(); //开启事物
		Person person = em.find(Person.class, 3);
		em.clear();//把实体管理器中所有实体编程游离状态
		person.setName("张三");
		em.merge(person);
		em.getTransaction().commit();
		em.close();
		factory.close();
		
	}
	
	//删除Person信息
	@Test
	public void delete(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
		//SessionException->session->bean事物
		EntityManager em  = factory.createEntityManager();
		em.getTransaction().begin(); //开启事物
		Person person = em.find(Person.class, 3);
		em.remove(person);
		em.getTransaction().commit();
		em.close();
		factory.close();
		
	}
	
	//查询Person数据
	@Test
	public void query(){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
		//SessionException->session->bean事物
		EntityManager em  = factory.createEntityManager();
		Query query = em.createQuery("select o from Person o where o.id=?1");
		query.setParameter(1, 2);
		Person person = (Person)query.getSingleResult();
		System.out.println("Person = " + person.getName());
		em.close();
		factory.close();
		
	}
	
	@Test
	public void deleteQuery(){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
		//SessionException->session->bean事物
		EntityManager em  = factory.createEntityManager();
		em.getTransaction().begin(); //开启事物
		Query query = em.createQuery("delete from Person o where o.id=?1");
		query.setParameter(1, 2);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		factory.close();
		
	}
	
	@Test
	public void updateQuery(){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
		//SessionException->session->bean事物
		EntityManager em  = factory.createEntityManager();
		em.getTransaction().begin(); //开启事物
		Query query = em.createQuery("update Person o  set o.name=:name where o.id=:id");
		query.setParameter("name","北京盖网xxxx");
		query.setParameter("id",1);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		factory.close();
		
	}
}
