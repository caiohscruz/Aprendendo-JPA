package classes;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Estudante.class)
public abstract class Estudante_ {

	public static volatile SingularAttribute<Estudante, Integer> idade;
	public static volatile SingularAttribute<Estudante, Estado> estado;
	public static volatile SingularAttribute<Estudante, String> nome;
	public static volatile SingularAttribute<Estudante, Integer> id;

	public static final String IDADE = "idade";
	public static final String ESTADO = "estado";
	public static final String NOME = "nome";
	public static final String ID = "id";

}

