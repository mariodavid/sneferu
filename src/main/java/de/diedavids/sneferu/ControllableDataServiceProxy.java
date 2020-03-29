package de.diedavids.sneferu;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.web.testsupport.TestContainer;
import com.haulmont.cuba.web.testsupport.proxy.DataServiceProxy;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Predicate;

public class ControllableDataServiceProxy extends DataServiceProxy {



    private Map<String, List<? extends Entity>> loadListEntities = new LinkedHashMap<>();
    private Map<Object, Entity> committedEntities = new LinkedHashMap<>();

    public ControllableDataServiceProxy(TestContainer container) {
        super(container);
    }

    public <E extends Entity> void returnEntitiesOnLoadList(Class<E> entityMetaClass, List<E> entities) {

        Metadata metadata = AppBeans.get(Metadata.class);

        loadListEntities.put(metadata.getClass(entityMetaClass).getName(), entities);
    }

    @Override
    public <E extends Entity> List<E> loadList(LoadContext<E> context) {

        if (loadListEntities.get(context.getEntityMetaClass()) != null) {
            return (List<E>) loadListEntities.get(context.getEntityMetaClass());
        }
        else {
            return Collections.emptyList();
        }
    }

    @Override
    public Set<Entity> commit(CommitContext context) {
        Set<Entity> committedEntities = super.commit(context);

        persistCommit(committedEntities);

        return committedEntities;
    }

    void persistCommit(Set<Entity> committedEntities) {
        committedEntities.stream()
                .forEach(entity -> this.committedEntities.put(entity.getId(), entity));
    }

    public <E extends Entity> E committed(Class<E> entityClass, Object entityId) {
        return (E) committedEntities.get(entityId);
    }

    public <E extends Entity> Optional<E> committed(Class<E> entityClass, Predicate<E> entityPredicate) {
        return (Optional<E>) committedEntities.entrySet()
            .stream()
            .map(Entry::getValue)
            .filter(entry -> entityPredicate.test((E) entry))
            .findFirst();
    }

}
