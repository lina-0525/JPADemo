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

	//���Person��Ϣ
	@Test
	public void save() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
		//SessionException->session->bean����
		EntityManager em  = factory.createEntityManager();
		em.getTransaction().begin(); //��������
		em.persist(new Person("��������"));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	//��ȡPerson��Ϣ
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
	
	//����Person��Ϣ
	@Test
	public void updatePerson(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
		//SessionException->session->bean����
		EntityManager em  = factory.createEntityManager();
		em.getTransaction().begin(); //��������
		Person person = em.getReference(Person.class, 2);
		person.setName("����");
		em.getTransaction().commit();
		em.close();
		factory.close();
		
	}
	
	//new 
	//managed(�й�)
	//���루�ѹܣ�
	//ɾ��
	@Test
	public void updatePerson2(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
		//SessionException->session->bean����
		EntityManager em  = factory.createEntityManager();
		em.getTransaction().begin(); //��������
		Person person = em.find(Person.class, 3);
		em.clear();//��ʵ�������������ʵ��������״̬
		person.setName("����");
		em.merge(person);
		em.getTransaction().commit();
		em.close();
		factory.close();
		
	}
	
	//ɾ��Person��Ϣ
	@Test
	public void delete(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
		//SessionException->session->bean����
		EntityManager em  = factory.createEntityManager();
		em.getTransaction().begin(); //��������
		Person person = em.find(Person.class, 3);
		em.remove(person);
		em.getTransaction().commit();
		em.close();
		factory.close();
		
	}
	
	//��ѯPerson����
	@Test
	public void query(){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
		//SessionException->session->bean����
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
		//SessionException->session->bean����
		EntityManager em  = factory.createEntityManager();
		em.getTransaction().begin(); //��������
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
		//SessionException->session->bean����
		EntityManager em  = factory.createEntityManager();
		em.getTransaction().begin(); //��������
		Query query = em.createQuery("update Person o  set o.name=:name where o.id=:id");
		query.setParameter("name","��������xxxx");
		query.setParameter("id",1);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		factory.close();
		
	}
}
