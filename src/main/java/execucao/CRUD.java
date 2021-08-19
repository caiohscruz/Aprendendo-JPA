package execucao;
import classes.Estudante;
import classes.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CRUD {
    // os atributos e métodos aqui são apenas para proporcionar alguma variabilidades nas execuções
    public static String HIBERNATE = "usando-hibernate";
    public static String ECLIPSE ="usando-eclipselink";

    public static String opt = HIBERNATE;

    public static String[] nomes = {"Marcelo","Roberto", "Bianca","Gabriel", "Rodolfo", "Mariano", "Mario", "Isabela"};

    public static String nomeX(){
        return nomes[(int) (Math.random() * (nomes.length-1))];
    }
    public static int idadeX(){
        return (int) ((Math.random() * 90) + 1);
    }
    public static Estado geraEstadoRandom(){
        EntityManagerFactory entityManagerFactoryEstadoRandom = Persistence.createEntityManagerFactory(opt);
        EntityManager entityManagerEstadoRandom = entityManagerFactoryEstadoRandom.createEntityManager();
        Estado estadoEncontrado = entityManagerEstadoRandom.find(Estado.class, (int) ((Math.random() * 4) + 1));
        entityManagerEstadoRandom.close();
        entityManagerFactoryEstadoRandom.close();
        return estadoEncontrado;
    }
    public static Estudante geraEstudanteRandom(){
        return new Estudante(nomeX(),idadeX(),geraEstadoRandom());
    }
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(opt);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("\nVamos incluir um estudante no banco de dados");
        Estudante estudanteParaAdicionar = geraEstudanteRandom();
        System.out.println("O estudante a ser incluído é o "+ estudanteParaAdicionar);

        entityManager.getTransaction().begin();
        entityManager.persist(estudanteParaAdicionar);
        entityManager.getTransaction().commit();

        System.out.println("\nVamos agora verificar se há algum estudante chamado Henrique usando JPQL");
        String jpql = "select a from Estudante a where a.nome = :nome";
        Estudante estudanteJPQL = entityManager.createQuery(jpql, Estudante.class).setParameter("nome", "Henrique").getSingleResult();
        System.out.println("Resultado: " + estudanteJPQL);

        Estado estadoX = geraEstadoRandom();
        System.out.println("\nQuem será que é de "+ estadoX.getNome() + " ?");
        String jpqlList = "select a from Estudante a where a.estado = :estado";
        List<Estudante> estudanteJPQLList = entityManager.createQuery(jpqlList, Estudante.class)
                .setParameter("estado", estadoX).getResultList();

        estudanteJPQLList.forEach(System.out::println);

        System.out.println("\nAgora, que tal ver o que tem na tabela Estudante utilizando SQL Nativo");
        String sqlList = "SELECT * FROM Estudante";
        List<Estudante> estudanteSQLList = entityManager.createNativeQuery(sqlList, Estudante.class).getResultList();
        estudanteSQLList.forEach(System.out::println);

        System.out.println("\nVamos agora deletar aquele estudante que incluimos no comecinho");
        entityManager.getTransaction().begin();
        entityManager.remove(estudanteParaAdicionar);
        entityManager.getTransaction().commit();

        String nomeX = nomeX();
        int idadeX = idadeX();
        System.out.println("E porque não também trocar o nome e a idade do estudante de id=1 para "+ nomeX + " e "+ idadeX);
        Estudante estudanteEncontrado = entityManager.find(Estudante.class, 1);
        entityManager.getTransaction().begin();
        estudanteEncontrado.setNome(nomeX);
        estudanteEncontrado.setIdade(idadeX);
        entityManager.getTransaction().commit();

        System.out.println("\nE por fim, vamos consultar a tabela pra ver como ficou no final das contas usando o Criteria API");
        CriteriaQuery<Estudante> criteriaQueryList = entityManager.getCriteriaBuilder().createQuery(Estudante.class);
        Root<Estudante> estudanteRootList = criteriaQueryList.from(Estudante.class);
        List<Estudante> estudanteAPICriteriaList = entityManager.createQuery(criteriaQueryList).getResultList();
        estudanteAPICriteriaList.forEach(System.out::println);

        entityManager.close();
        entityManagerFactory.close();

    }
}