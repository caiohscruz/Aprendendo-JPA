package classes;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Estado.class)
public abstract class Estado_ {

	public static volatile SingularAttribute<Estado, String> sigla;
	public static volatile ListAttribute<Estado, Estudante> estudantes;
	public static volatile SingularAttribute<Estado, String> nome;
	public static volatile SingularAttribute<Estado, Integer> id;

	public static final String SIGLA = "sigla";
	public static final String ESTUDANTES = "estudantes";
	public static final String NOME = "nome";
	public static final String ID = "id";

}

